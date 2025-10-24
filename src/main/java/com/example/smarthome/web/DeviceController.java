package com.example.smarthome.web;

import com.example.smarthome.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService service;
    public DeviceController(DeviceService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devices", service.listAll());
        return "devices/index";
    }

    @PostMapping("/{id}/off")
    public String off(@PathVariable Long id) {
        service.toggleOff(id);
        return "redirect:/devices";
    }
}
