package com.thehecklers.copilot;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final DeveloperRepository repo;

    public DataLoader(DeveloperRepository repo) {
        this.repo = repo;

        repo.saveAll(List.of(
                new Developer(UUID.randomUUID().toString(), "Julien Dubois"),
                new Developer(UUID.randomUUID().toString(), "Mark Heckler"),
                new Developer(UUID.randomUUID().toString(), "Dave Syer"),
                new Developer(UUID.randomUUID().toString(), "Josh Long")))
                .forEach(System.out::println);
    }
}
