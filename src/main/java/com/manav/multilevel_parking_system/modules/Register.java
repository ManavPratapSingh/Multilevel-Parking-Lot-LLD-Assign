package com.manav.multilevel_parking_system.modules;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

import com.manav.multilevel_parking_system.modules.entryGate.EntryGate;
import com.manav.multilevel_parking_system.modules.slot.Slot;

public class Register {
    List<PriorityQueue<Slot>> entryGatesSlots = new ArrayList<>();

    public void createEntryGatesSlots(List<EntryGate> entryGates, List<Slot> slots) {
        for (EntryGate entryGate : entryGates) {
            PriorityQueue<Slot> pq = new PriorityQueue<>(
                    (slot1, slot2) -> calculatePriority(slot1, entryGate) - calculatePriority(slot2, entryGate));
            for (Slot slot : slots) {
                pq.add(slot);
            }
            entryGatesSlots.add(pq);
        }
    }

    public List<PriorityQueue<Slot>> getEntryGatesSlots() {
        return this.entryGatesSlots;
    }

    public void updateEntryGatesSlots(Slot slot) {
        slot.setAvailable(1 - slot.available());
        for (PriorityQueue<Slot> pq : entryGatesSlots) {
            pq.remove(slot);
            pq.add(slot);
        }
    }

    private int calculatePriority(Slot slot, EntryGate entryGate) {
        int a = 10;
        int b = 2;
        int c = 100;
        return a * slot.distanceFromEntryGate(entryGate.gateNumber()) + b * slot.level() + c * (1 - slot.available());
    }

}
