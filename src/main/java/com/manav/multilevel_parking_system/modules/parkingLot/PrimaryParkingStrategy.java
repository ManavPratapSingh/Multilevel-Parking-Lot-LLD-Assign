package com.manav.multilevel_parking_system.modules.parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.manav.multilevel_parking_system.modules.Register;
import com.manav.multilevel_parking_system.modules.VehicleType;
import com.manav.multilevel_parking_system.modules.entryGate.EntryGate;
import com.manav.multilevel_parking_system.modules.slot.Slot;

public class PrimaryParkingStrategy implements IParkingStrategy {
    Register register;
    List<PriorityQueue<Slot>> entryGatesSlots;

    public PrimaryParkingStrategy(Register register) {
        this.register = register;
        entryGatesSlots = register.getEntryGatesSlots();
    }

    @Override
    public Slot getSlot(VehicleType vehicleType, EntryGate entryGate) {
        List<Slot> fallbackSlots = new ArrayList<>();
        PriorityQueue<Slot> pq = entryGatesSlots.get(entryGate.gateNumber());
        Slot slot;
        
        while ((slot = pq.poll()) != null) {
            if (slot.VehicleType() == vehicleType) {
                break;
            }
            fallbackSlots.add(slot);
        }

        for (Slot fallbackSlot : fallbackSlots) {
            pq.add(fallbackSlot);
        }

        if (slot != null) {
            register.updateEntryGatesSlots(slot);
        }
        
        return slot;
    }


}
