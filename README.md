# Multilevel Parking Lot System

A robust, Java-based Multilevel Parking Lot System designed with modern architectural patterns. This system manages multiple entry gates, levels, and parking slots while optimizing for distance and availability.

## Features

-   **Multilevel Support**: Efficiently manages parking across multiple levels.
-   **Multi-Gate Entry**: Handles multiple entry points simultaneously.
-   **Automated Slot Prioritization**: Uses a **Min-Priority Queue** strategy to find the best available slot based on distance from the entry gate and level.
-   **Vehicle Diversity**: Supports different vehicle types (`SMALL`, `MEDIUM`, `LARGE`).
-   **Automated Fee Calculation**: Tracks entry/exit times and calculates costs based on the slot's hourly rate.
-   **Receipt Generation**: Prints detailed receipts upon vehicle exit.
-   **Extensible Architecture**: Built with the **Strategy Pattern**, **Factory Pattern**, and **State Management (Register)**.

## Architecture

-   **Strategy Pattern (`IParkingStrategy`)**: Decouples the slot allocation logic from the parking lot itself.
-   **Register (`Register`)**: Manages the global state of slot priorities and ensures all gates have updated views of available slots.
-   **Factory (`ParkingLotFactory`)**: Handles the complex initialization and dependency injection of the system.
-   **Immutable Records**: Uses Java Records for `DTOVehicleDetails` and `Ticket` to ensure data integrity.

## Prerequisites

-   Java 17 or higher
-   Maven

## How to Run the Simulation

To see the system in action, run the built-in simulation:

```bash
.\mvnw.cmd spring-boot:run
```

The simulation will:
1.  Initialize a 2-level parking lot with 2 entry gates.
2.  Assign distances from each gate to every slot.
3.  Simulate parking for different vehicles at different gates.
4.  Generate receipts with calculated stay durations and total fees.

## Core Components

-   **`MutlilevelParkingLot`**: The main controller managing create, park, and exit operations.
-   **`PrimaryParkingStrategy`**: Implementation that prioritizes slots based on a weighted sum of distance, level, and availability.
-   **`Slot`**: Represents a parking space with specific vehicle type support and pricing.
-   **`Ticket`**: A record representing a successful parking event.

## Performance Note

The current implementation uses `PriorityQueue` with an $O(n)$ removal complexity to refresh slot priorities across multiple gates. This is well-suited for medium-sized parking lots and ensures consistent state across the system.
