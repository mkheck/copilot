package com.thehecklers.copilot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Developer(@Id String id, String name) {
}
