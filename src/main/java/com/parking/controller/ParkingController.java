package com.parking.controller;

import com.parking.model.Ticket;
import com.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/entry")
    public Ticket enterVehicle(@RequestParam String vehicleNumber, @RequestParam String type) {
        return parkingService.issueTicket(vehicleNumber, type);
    }

    @PostMapping("/exit")
    public Ticket exitVehicle(@RequestParam String vehicleNumber) {
        return parkingService.processExit(vehicleNumber);
    }
}
