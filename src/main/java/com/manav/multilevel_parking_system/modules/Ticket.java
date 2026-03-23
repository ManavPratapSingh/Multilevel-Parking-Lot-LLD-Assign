package com.manav.multilevel_parking_system.modules;

import java.time.LocalTime;

import com.manav.multilevel_parking_system.modules.slot.Slot;

public record Ticket(String vehicleNumber, VehicleType vehicleType, Slot slot, LocalTime entryTime) {
}
