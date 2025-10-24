package com.example.smarthome.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
public class AirConditioner extends Device {
    public enum Mode { OFF, COOL, HEAT, AUTO }
    private Mode mode = Mode.OFF;

    @Min(16) @Max(30)
    private Integer setpointC = 22;

    public AirConditioner() { super(); }
    public AirConditioner(String name) { super(name, DeviceType.AIR_CONDITIONER); }

    @Override public boolean isOff() { return mode == Mode.OFF; }
    @Override public void turnOff() { mode = Mode.OFF; }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = Objects.requireNonNull(mode);
    }

    public Integer getSetpointC() {
        return setpointC;
    }

    public void setSetpointC(Integer setpointC) {
        this.setpointC = setpointC;
    }
}
