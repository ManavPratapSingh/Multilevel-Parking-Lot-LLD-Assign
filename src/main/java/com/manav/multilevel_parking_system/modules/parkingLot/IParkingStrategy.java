package com.manav.multilevel_parking_system.modules.parkingLot;

import com.manav.multilevel_parking_system.modules.VehicleType;
import com.manav.multilevel_parking_system.modules.entryGate.EntryGate;
import com.manav.multilevel_parking_system.modules.slot.Slot;

public interface IParkingStrategy {
    Slot getSlot(VehicleType vehicleType, EntryGate entryGate);
}
