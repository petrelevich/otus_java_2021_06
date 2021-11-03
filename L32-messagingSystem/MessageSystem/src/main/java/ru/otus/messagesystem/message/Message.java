package ru.otus.messagesystem.message;

import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.client.ResultDataType;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Message<T extends ResultDataType> {

    private final MessageId id;
    private final String from;
    private final String to;
    private final MessageId sourceMessageId;
    private final MessageType type;
    private final T data;
    private final MessageCallback<T> callback;

    Message(@Nonnull MessageId messageId, String from, String to, MessageId sourceMessageId, MessageType type,
            T data, MessageCallback<T> callback) {
        this.id = messageId;
        this.from = from;
        this.to = to;
        this.sourceMessageId = sourceMessageId;
        this.type = type;
        this.data = data;
        this.callback = callback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message<?> message = (Message<?>) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", sourceMessageId=" + sourceMessageId +
                ", type='" + type + '\'' +
                '}';
    }

    public MessageId getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public MessageType getType() {
        return type;
    }

    public T getData() {
        return data;
    }

    public MessageCallback<T> getCallback() {
        return callback;
    }
}
