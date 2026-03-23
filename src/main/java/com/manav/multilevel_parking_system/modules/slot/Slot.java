package com.manav.multilevel_parking_system.modules.slot;

import com.manav.multilevel_parking_system.modules.VehicleType;

public class Slot {
    int id;
    int level;
    int[] distanceFromEntryGates;
    VehicleType vehicleType;
    int available;
    int perHourRate;

    public Slot(int id, int level, int[] distanceFromEntryGates, VehicleType vehicleType, int perHourRate) {
        this.id = id;
        this.level = level;
        this.distanceFromEntryGates = distanceFromEntryGates;
        this.vehicleType = vehicleType;
        this.available = 1;
        this.perHourRate = perHourRate;
    }

    public int perHourRate() {
        return this.perHourRate;
    }


    public void setDistanceFromEntryGates(int[] distanceFromEntryGates) {
        this.distanceFromEntryGates = distanceFromEntryGates;
    }


    public void setAvailable(int available) {
        this.available = available;
    }

    public int available() {
        return this.available;
    }

    public int id() {
        return this.id;
    }

    public int level() {
        return this.level;
    }

    public VehicleType VehicleType() {
        return this.vehicleType;
    }

    public int distanceFromEntryGate(int entryGateId) {
        return this.distanceFromEntryGates[entryGateId];
    }

    @Override
    public String toString() {
        return "Slot [id=" + id + ", level=" + level + ", vehicleType=" + vehicleType + ", available=" + available
                + "]";
    }

}
