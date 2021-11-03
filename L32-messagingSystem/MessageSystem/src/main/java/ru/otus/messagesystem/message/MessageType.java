package ru.otus.messagesystem.message;

public enum MessageType {
    VOID_MESSAGE("voidMessage"),
    USER_DATA("UserData")
    ;

    private final String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
