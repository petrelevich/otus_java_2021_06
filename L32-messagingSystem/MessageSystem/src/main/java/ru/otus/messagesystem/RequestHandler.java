package ru.otus.messagesystem;


import ru.otus.messagesystem.client.ResultDataType;
import ru.otus.messagesystem.message.Message;

import java.util.Optional;


public interface RequestHandler {
    <T extends ResultDataType> Optional<Message<T>> handle(Message<T> msg);
}
