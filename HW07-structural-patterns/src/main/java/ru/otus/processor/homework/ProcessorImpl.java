package ru.otus.processor.homework;

import lombok.RequiredArgsConstructor;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

@RequiredArgsConstructor
public class ProcessorImpl implements Processor {
    private final TimeProvider timeProvider;

    @Override
    public Message process(Message message) {
        if (timeProvider.getTime().getSecond() % 2 == 0) {
            throw new EvenSecondException("Even second throw exception: " + timeProvider.getTime());
        }

        return message;
    }
}