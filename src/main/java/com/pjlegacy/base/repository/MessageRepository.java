package com.pjlegacy.base.repository;

import com.pjlegacy.base.model.chatModel.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, String> {
    MessageModel findMessageModelBySenderIdAndReceiverId(String senderId, String receiverId);
}
