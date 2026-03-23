package com.manav.multilevel_parking_system.modules.parkingLot;

import com.manav.multilevel_parking_system.modules.Register;

public class ParkingLotFactory {
    public static IParkingLot createParkingLot() {
        Register register = new Register();
        IParkingStrategy strategy = new PrimaryParkingStrategy(register);
        return new MutlilevelParkingLot(register, strategy);
    }
}


