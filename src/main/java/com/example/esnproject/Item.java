package com.example.esnproject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Item {
    @Id
    private Long id;
    private String text;
    private boolean done;
}
