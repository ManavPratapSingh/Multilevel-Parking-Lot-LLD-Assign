package com.manav.multilevel_parking_system.modules.parkingLot;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.manav.multilevel_parking_system.modules.Ticket;
import com.manav.multilevel_parking_system.modules.entryGate.EntryGate;
import com.manav.multilevel_parking_system.modules.DTOVehicleDetails;
import com.manav.multilevel_parking_system.modules.level.Level;
import com.manav.multilevel_parking_system.modules.slot.Slot;

public interface IParkingLot {
    void create(Map<Level, List<Slot>> levels, int[][] gateSlotDistance);

    Ticket park(DTOVehicleDetails vehicleDetails, EntryGate entryGate, LocalTime entryTime);

    void exit(Ticket ticket, LocalTime exitTime);
}
