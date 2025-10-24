package com.example.smarthome.domain;

import jakarta.persistence.Entity;

@Entity
public class Light extends Device {
    // state: on/off via switch
    private boolean switchOn = false;

    public Light() { super(); }
    public Light(String name) { super(name, DeviceType.LIGHT); }

    @Override public boolean isOff() { return !switchOn; }
    @Override public void turnOff() { switchOn = false; }

    public boolean isSwitchOn() {
        return switchOn;
    }

    public void setSwitchOn(boolean switchOn) {
        this.switchOn = switchOn;
    }
}
