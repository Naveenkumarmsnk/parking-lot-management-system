package com.parking.service;

import com.parking.model.Ticket;
import com.parking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ParkingService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket issueTicket(String vehicleNumber, String type) {
        Ticket ticket = new Ticket();
        ticket.setVehicleNumber(vehicleNumber);
        ticket.setVehicleType(type);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setPaid(false);
        return ticketRepository.save(ticket);
    }

    public Ticket processExit(String vehicleNumber) {
        return ticketRepository.findByVehicleNumberAndIsPaidFalse(vehicleNumber)
            .map(ticket -> {
                ticket.setExitTime(LocalDateTime.now());
                long minutes = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
                
                // Logic: ₹20 base price + ₹1 per minute
                double fee = 20.0 + (minutes * 1.0);
                
                ticket.setTotalFee(fee);
                ticket.setPaid(true);
                return ticketRepository.save(ticket);
            }).orElseThrow(() -> new RuntimeException("Active ticket not found for vehicle"));
    }
}
