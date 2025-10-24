package com.example.smarthome.config;

import com.example.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Value;
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
    @Scheduled(cron = "${smarthome.update.cron}", zone="America/Moncton")
    public void annualUpdate() {
        deviceService.turnOffAllForUpdate();
        System.out.println("[AnnualUpdate] All devices turned off by scheduler.");
        // That's all for update
    }
}
