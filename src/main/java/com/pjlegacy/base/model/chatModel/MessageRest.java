package com.pjlegacy.base.model.chatModel;

import com.pjlegacy.base.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageRest {
    private String id;
    private String idSender;
    private String message;
    private LocalDate messageDate;
    private String idReceiver;
}
