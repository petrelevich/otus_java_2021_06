package ru.outs.chain;

import java.util.ArrayList;
import java.util.List;

class Application {
    private final List<String> history = new ArrayList<>();

    void addHistoryRecord(String record) {
        history.add(record);
    }

    void printHistory() {
        System.out.println(history);
    }
}
