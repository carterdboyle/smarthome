package com.example.smarthome.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Device {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    protected Device() {}
    protected Device(String name, DeviceType type) { this.name = name; this.type = type; }

    public abstract boolean isOff();
    public abstract void turnOff();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }
}
