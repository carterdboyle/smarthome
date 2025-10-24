package com.example.smarthome.domain;

import jakarta.persistence.Entity;

@Entity
public class Fan extends Device {
    // speed: 0 (off), 1, 2
    private int speed = 0;

    public Fan() { super(); }
    public Fan(String name) { super(name, DeviceType.FAN); }

    @Override public boolean isOff() { return speed == 0; }
    @Override public void turnOff() { speed = 0; }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0 || speed > 2) {
            throw new IllegalArgumentException("Fan speed must be 0, 1 or 2.");
        }
        this.speed = speed;
    }
}
