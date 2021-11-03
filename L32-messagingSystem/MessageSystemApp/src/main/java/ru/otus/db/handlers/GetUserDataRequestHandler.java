package ru.otus.db.handlers;

import ru.otus.dto.UserData;
import ru.otus.db.DBService;
import ru.otus.messagesystem.client.ResultDataType;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.RequestHandler;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler {
    private final DBService dbService;

    public GetUserDataRequestHandler(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public <T extends ResultDataType> Optional<Message<T>> handle(Message<T> msg) {
        var userData = (UserData) msg.getData();
        var data = new UserData(userData.getUserId(), dbService.getUserData(userData.getUserId()));
        return Optional.of(MessageBuilder.buildReplyMessage(msg, (T) data));
    }
}
