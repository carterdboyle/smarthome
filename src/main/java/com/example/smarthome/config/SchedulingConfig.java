package com.example.smarthome.config;

import com.example.smarthome.service.DeviceService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class SchedulingConfig {
    private final DeviceService deviceService;
    public SchedulingConfig(DeviceService deviceService) { this.deviceService = deviceService; }

    // second minute hour day-of-month month day-of-week
    // defaults to local time zone
    // Jan 1st 1:00 every year
    @Scheduled(cron = "0 0 1 1 1 *")
    public void annualUpdate() {
        deviceService.turnOffAllForUpdate();
        // That's all for update
    }
}
