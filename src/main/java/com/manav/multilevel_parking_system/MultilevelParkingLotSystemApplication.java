package com.manav.multilevel_parking_system;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manav.multilevel_parking_system.modules.DTOVehicleDetails;
import com.manav.multilevel_parking_system.modules.Ticket;
import com.manav.multilevel_parking_system.modules.VehicleType;
import com.manav.multilevel_parking_system.modules.entryGate.EntryGate;
import com.manav.multilevel_parking_system.modules.level.Level;
import com.manav.multilevel_parking_system.modules.parkingLot.IParkingLot;
import com.manav.multilevel_parking_system.modules.parkingLot.ParkingLotFactory;
import com.manav.multilevel_parking_system.modules.slot.Slot;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultilevelParkingLotSystemApplication {

    public static void main(String[] args) {
        // Run Spring Boot logic
        // SpringApplication.run(MultilevelParkingLotSystemApplication.class, args);

        System.out.println("\n--- Starting Multilevel Parking Lot System Simulation ---");

        // 1. Initialise Parking Lot
        IParkingLot parkingLot = ParkingLotFactory.createParkingLot();

        // 2. Setup Levels and Slots
        Level l1 = new Level(1);
        Level l2 = new Level(2);

        // slots for level 1 (id 0, 1)
        Slot s1 = new Slot(0, 1, new int[2], VehicleType.SMALL, 50);
        Slot s2 = new Slot(1, 1, new int[2], VehicleType.MEDIUM, 100);

        // slots for level 2 (id 2, 3)
        Slot s3 = new Slot(2, 2, new int[2], VehicleType.SMALL, 50);
        Slot s4 = new Slot(3, 2, new int[2], VehicleType.LARGE, 150);

        Map<Level, List<Slot>> levels = new HashMap<>();
        levels.put(l1, List.of(s1, s2));
        levels.put(l2, List.of(s3, s4));

        // 3. Setup Distances (2 gates, 4 slots)
        // gate 0 (Gate 1): s1=5, s2=10, s3=15, s4=20
        // gate 1 (Gate 2): s1=20, s2=15, s3=10, s4=5
        int[][] distances = {
                { 5, 10, 15, 20 },
                { 20, 15, 10, 5 }
        };

        // 4. Create Parking Lot Geometry
        parkingLot.create(levels, distances);

        // 5. Simulation Tests
        EntryGate g1 = new EntryGate(0);
        EntryGate g2 = new EntryGate(1);

        System.out.println("TEST 1: Parking a SMALL car at Gate 1 (expected front of queue)");
        DTOVehicleDetails car1 = new DTOVehicleDetails("KA-01-HH-1234", VehicleType.SMALL);
        Ticket t1 = parkingLot.park(car1, g1, LocalTime.now().minusHours(2));

        System.out.println("\nTEST 2: Parking a LARGE truck at Gate 2 (expected slot closer to Gate 2)");
        DTOVehicleDetails truck1 = new DTOVehicleDetails("KA-05-GT-8888", VehicleType.LARGE);
        Ticket t2 = parkingLot.park(truck1, g2, LocalTime.now().minusHours(5));

        System.out.println("\nTEST 3: Exiting Car 1 and printing receipt");
        if (t1 != null) {
            parkingLot.exit(t1, LocalTime.now());
        }

        System.out.println("TEST 4: Exiting Truck 1 and printing receipt");
        if (t2 != null) {
            parkingLot.exit(t2, LocalTime.now());
        }

        System.out.println("--- End of Simulation ---\n");
    }

}
