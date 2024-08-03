package com.greenmark.database.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "spaz")
public class Spaz implements Serializable {
    private static final long serialVersionUID = -3779377594651740105L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "extid", length = 36, nullable = false, unique = true)
    private String extid;

    @Column(name = "symbol", length = 8, nullable = false, unique = true)
    private String symbol;

    @Column(name = "name", length = 64, nullable = false)
    private String name;
}