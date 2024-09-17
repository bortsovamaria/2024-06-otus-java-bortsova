package ru.otus.listener.homework;

import java.time.LocalDateTime;
import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.homework.TimeProvider;

@Slf4j
@RequiredArgsConstructor
public class HistoryListener implements Listener, HistoryReader {

    private static final Map<LocalDateTime, Message> history = new TreeMap<>(Collections.reverseOrder());

    private final TimeProvider timeProvider;

    @Override
    public void onUpdated(Message msg) {
        history.put(timeProvider.getTime(), copy(msg));
        printHistory();
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return history.values().stream().filter(m -> m.getId() == id).findFirst();
    }

    private static Message copy(Message msg) {
        if (msg.getField13() == null) {
            return msg.toBuilder().build();
        } else {
            ObjectForMessage newOfm = new ObjectForMessage();
            newOfm.setData(List.copyOf(msg.getField13().getData()));
            return msg.toBuilder().field13(newOfm).build();
        }
    }

    private void printHistory() {
        for (Map.Entry<LocalDateTime, Message> entry : history.entrySet()) {
            log.info("History entry {}: {}", entry.getKey(), entry.getValue());
        }
    }
}
