package com.manav.multilevel_parking_system.modules.parkingLot;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.manav.multilevel_parking_system.modules.DTOVehicleDetails;
import com.manav.multilevel_parking_system.modules.Register;
import com.manav.multilevel_parking_system.modules.Ticket;
import com.manav.multilevel_parking_system.modules.entryGate.EntryGate;
import com.manav.multilevel_parking_system.modules.level.Level;
import com.manav.multilevel_parking_system.modules.slot.Slot;

public class MutlilevelParkingLot implements IParkingLot {
    private final Register register;
    private final IParkingStrategy strategy;

    public MutlilevelParkingLot(Register register, IParkingStrategy strategy) {
        this.register = register;
        this.strategy = strategy;
    }

    @Override
    public void create(Map<Level, List<Slot>> levels, int[][] gateSlotDistance) {
        List<Slot> allSlots = new ArrayList<>();
        for (List<Slot> levelSlots : levels.values()) {
            allSlots.addAll(levelSlots);
        }

        int gateCount = gateSlotDistance.length;
        List<EntryGate> entryGates = new ArrayList<>();
        for (int i = 0; i < gateCount; i++) {
            entryGates.add(new EntryGate(i));
        }

        for (Slot slot : allSlots) {
            int sIndex = slot.id(); 
            int[] distances = new int[gateCount];
            for (int g = 0; g < gateCount; g++) {
                if (sIndex < gateSlotDistance[g].length) {
                    distances[g] = gateSlotDistance[g][sIndex];
                }
            }
            slot.setDistanceFromEntryGates(distances);
        }

        register.createEntryGatesSlots(entryGates, allSlots);
    }

    @Override
    public Ticket park(DTOVehicleDetails vehicleDetails, EntryGate entryGate, LocalTime entryTime) {
        Slot slot = strategy.getSlot(vehicleDetails.vehicleType(), entryGate);
        if (slot == null) {
            System.out.println("Parking Full for vehicle: " + vehicleDetails.vehicleNumber());
            return null;
        }

        // Mark as booked
        register.updateEntryGatesSlots(slot); 
        
        Ticket ticket = new Ticket(vehicleDetails.vehicleNumber(), vehicleDetails.vehicleType(), slot, entryTime);
        System.out.println("Vehcile Parked. Ticket Generated: " + ticket);
        return ticket;
    }

    @Override
    public void exit(Ticket ticket, LocalTime exitTime) {
        Slot slot = ticket.slot();
        
        // Mark as available
        register.updateEntryGatesSlots(slot);

        // Calculate checkout
        long durationMinutes = Math.abs(Duration.between(ticket.entryTime(), exitTime).toMinutes());
        double hours = Math.ceil(durationMinutes / 60.0);
        double totalAmount = hours * slot.perHourRate();

        System.out.println("\n--- PARKING RECEIPT ---");
        System.out.println("Vehicle Number: " + ticket.vehicleNumber());
        System.out.println("Vehicle Type: " + ticket.vehicleType());
        System.out.println("Slot ID: " + slot.id());
        System.out.println("Entry Time: " + ticket.entryTime());
        System.out.println("Exit Time: " + exitTime);
        System.out.println("Duration: " + durationMinutes + " mins");
        System.out.println("Rate: " + slot.perHourRate() + "/hr");
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("-----------------------\n");
    }
}
