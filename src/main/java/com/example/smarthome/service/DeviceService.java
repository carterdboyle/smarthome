package com.example.smarthome.service;

import com.example.smarthome.domain.Device;
import com.example.smarthome.repo.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository repo;

    public DeviceService(DeviceRepository repo) { this.repo = repo; }

    public List<Device> listAll() { return repo.findAll(); }

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
