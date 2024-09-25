package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

import java.io.IOException;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        List<Measurement> result;
        ObjectMapper objectMapper = new ObjectMapper();
        try (var resource = ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            result = objectMapper.readerForListOf(Measurement.class).readValue(resource);
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
        return result;
    }
}