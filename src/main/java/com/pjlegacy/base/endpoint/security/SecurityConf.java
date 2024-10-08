package com.pjlegacy.base.endpoint.security;

import com.pjlegacy.base.model.User;
import com.pjlegacy.base.model.exception.ForbiddenException;
import com.pjlegacy.base.service.UserService;
import com.pjlegacy.base.service.firebase.FirebaseApi;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConf {
  private final HandlerExceptionResolver exceptionResolver;
  private final AuthProvider provider;
  private final UserService userService;
  private final FirebaseApi firebaseApi;

  public SecurityConf(
      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
      AuthProvider auth,
      UserService userService,
      FirebaseApi firebase) {
    exceptionResolver = resolver;
    provider = auth;
    this.userService = userService;
    this.firebaseApi = firebase;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.authenticationProvider(provider)
        .addFilterBefore(
            bearerFilter(
                new NegatedRequestMatcher(
                    new OrRequestMatcher(
                        new AntPathRequestMatcher("/ping"),
                        new AntPathRequestMatcher("/messages"),
                        new AntPathRequestMatcher("/signin"),
                        new AntPathRequestMatcher("/signup"),
                        new AntPathRequestMatcher("/posts", GET.name()),
                        new AntPathRequestMatcher("/categories", GET.name()),
                        new AntPathRequestMatcher("/categories/*", GET.name()),
                        new AntPathRequestMatcher("/**", OPTIONS.toString())
                    ))),
            AnonymousAuthenticationFilter.class)
        .authorizeHttpRequests(
            (authorize) ->
                authorize
                    .requestMatchers("/ping")
                    .permitAll()
                    .requestMatchers(POST, "/signin")
                    .permitAll()
                    .requestMatchers(POST, "/signup")
                    .permitAll()
                    .requestMatchers(GET, "/posts")
                    .permitAll()
                    .requestMatchers(POST,"/messages")
                    .authenticated()
                    .requestMatchers(GET,"/messages/**")
                    .authenticated()
                    .requestMatchers(PUT, "/posts/*")
                    .authenticated()
                    .requestMatchers(GET, "/posts/*")
                    .authenticated()
                    .requestMatchers(GET, "/posts/*/fundsraised")
                    .authenticated()
                    .requestMatchers(DELETE, "/posts/*")
                    .authenticated()
                    .requestMatchers(GET, "/categories")
                    .permitAll()
                    .requestMatchers(GET, "/categories/*")
                    .permitAll()
                    .requestMatchers(PUT, "/transactions")
                    .authenticated()
                    .requestMatchers(GET, "/users/*")
                    .authenticated()
                    .requestMatchers(GET, "/users/*/posts")
                    .authenticated()
                    .requestMatchers(PUT, "/users/*")
                    .authenticated()
                    .anyRequest()
                    .denyAll())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .cors(AbstractHttpConfigurer::disable);
    return http.build();
  }

  private Exception forbiddenWithRemoteInfo(Exception e, HttpServletRequest req) {
    log.info(
        String.format(
            "Access is denied for remote caller: address=%s, host=%s, port=%s",
            req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
    return new ForbiddenException(e.getMessage());
  }

  private AuthFilter bearerFilter(RequestMatcher requestMatcher) throws Exception {
    AuthFilter bearerFilter = new AuthFilter(requestMatcher, userService, firebaseApi);
    bearerFilter.setAuthenticationManager(authenticationManager());
    bearerFilter.setAuthenticationSuccessHandler(
        (httpServletRequest, httpServletResponse, authentication) -> {});
    bearerFilter.setAuthenticationFailureHandler(
        (req, res, e) ->
            exceptionResolver.resolveException(req, res, null, forbiddenWithRemoteInfo(e, req)));
    return bearerFilter;
  }

  protected AuthenticationManager authenticationManager() {
    return new ProviderManager(provider);
  }
}
