package com.pjlegacy.base.model.chatModel;

import com.pjlegacy.base.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageModel {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    private String message;
    private LocalDate messageDate;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;
}
