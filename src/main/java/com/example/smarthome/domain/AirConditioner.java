package com.example.smarthome.domain;

public class AirConditioner extends Device {
    public enum Mode { OFF, COOL, HEAT, AUTO }
    private Mode mode = Mode.OFF;

    public AirConditioner() { super(); }
    public AirConditioner(String name) { super(name, DeviceType.AIR_CONDITIONER); }

    @Override public boolean isOff() { return mode == Mode.OFF; }
    @Override public void turnOff() { mode = Mode.OFF; }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
