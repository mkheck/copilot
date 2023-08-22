package com.thehecklers.copilot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeveloperController {
    private final DeveloperRepository repo;

    public DeveloperController(DeveloperRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, Copilot!";
    }

    @GetMapping("/devs")
    public Iterable<Developer> allDevs() {
        return repo.findAll();
    }
}
