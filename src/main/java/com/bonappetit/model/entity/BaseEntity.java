package com.bonappetit.model.entity;
import jakarta.persistence.*;


@MappedSuperclass public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public BaseEntity() {
    }

    public void setId(long id) {
        this.id = id;
    }
}
