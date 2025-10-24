package com.example.smarthome.web;

import com.example.smarthome.domain.AirConditioner;
import com.example.smarthome.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService service;
    private static final ZoneId LOCAL_ZONE = ZoneId.of("America/Moncton");

    public DeviceController(DeviceService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devices", service.listAll());
        model.addAttribute("acModes", AirConditioner.Mode.values());

        ZonedDateTime now = ZonedDateTime.now(LOCAL_ZONE);
        model.addAttribute("now", now);
        model.addAttribute("nextRun", nextAnnualRunAfter(now));
        model.addAttribute("zoneId", LOCAL_ZONE.getId());
        return "devices/index";
    }

    private static ZonedDateTime nextAnnualRunAfter(ZonedDateTime ref) {
        ZonedDateTime thisYears = ZonedDateTime.of(ref.getYear(), 1, 1, 1, 0, 0, 0, ref.getZone());
        if (!ref.isAfter(thisYears)) return thisYears;
        return thisYears.plusYears(1);
    }

    // LIGHT
    @PostMapping("/{id}/light/toggle")
    public String lightSwitch(@PathVariable Long id) {
        service.toggleLight(id);
        return "redirect:/devices";
    }

    // FAN
    @PostMapping("/{id}/fan/speed")
    public String fanSpeed(@PathVariable Long id, @RequestParam("speed") int speed) {
        service.setFanSpeed(id, speed);
        return "redirect:/devices";
    }

    // AC Mode
    @PostMapping("/{id}/ac/mode")
    public String acMode(@PathVariable Long id, @RequestParam("mode") AirConditioner.Mode mode) {
        service.setAcMode(id, mode);
        return "redirect:/devices";
    }

    // AC Setpoint
    @PostMapping("/{id}/ac/setpoint")
    public String acSetpoint(@PathVariable Long id, @RequestParam("setpointC") int setpointC) {
        service.setAcSetpoint(id, setpointC);
        return "redirect:/devices";
    }

    @PostMapping("/{id}/off")
    public String off(@PathVariable Long id) {
        service.toggleOff(id);
        return "redirect:/devices";
    }

    // Turn all off
    @PostMapping("off-all")
    public String offAll(RedirectAttributes ra) {
        service.turnOffAllForUpdate();
        ra.addFlashAttribute("msg", "All devices turned off.");
        return "redirect:/devices";
    }
}
