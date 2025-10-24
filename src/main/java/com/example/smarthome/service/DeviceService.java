package com.example.smarthome.service;

import com.example.smarthome.domain.AirConditioner;
import com.example.smarthome.domain.Device;
import com.example.smarthome.domain.Fan;
import com.example.smarthome.domain.Light;
import com.example.smarthome.repo.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository repo;

    public DeviceService(DeviceRepository repo) { this.repo = repo; }

    public List<Device> listAll() { return repo.findAll(); }

    public void setLightSwitch(Long id, boolean on) {
        Device d = repo.findById(id).orElseThrow();
        if (!(d instanceof Light l)) throw new IllegalArgumentException("Not a light");
        l.setSwitchOn(on);
        repo.save(l);
    }

    public void setFanSpeed(Long id, int speed) {
        Device d = repo.findById(id).orElseThrow();
        if (!(d instanceof Fan f)) throw new IllegalArgumentException("Not a fan");
        f.setSpeed(speed);
        repo.save(f);
    }

    public void setAcMode(Long id, AirConditioner.Mode mode) {
        Device d = repo.findById(id).orElseThrow();
        if (!(d instanceof AirConditioner ac)) throw new IllegalArgumentException("Not an AC");
        ac.setMode(mode);
        repo.save(ac);
    }

    public void setAcSetpoint(Long id, int setpointC) {
        Device d = repo.findById(id).orElseThrow();
        if (!(d instanceof AirConditioner ac)) throw new IllegalArgumentException("Not an ac");
        if (setpointC < 16 || setpointC > 30) {
            throw new IllegalArgumentException("Setpoint must be 16-30°C");
        }
        ac.setSetpointC(setpointC);
        repo.save(ac);
    }

    public Device toggleOff(Long id) {
        Device d = repo.findById(id).orElseThrow();
        d.turnOff();
        return repo.save(d);
    }

    public void turnOffAllForUpdate() {
        repo.findAll().forEach(Device::turnOff);
        // Sync with DB
        repo.flush();
    }
}
