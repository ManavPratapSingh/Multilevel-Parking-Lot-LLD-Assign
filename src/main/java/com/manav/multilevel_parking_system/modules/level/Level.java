package com.manav.multilevel_parking_system.modules.level;

public class Level {
    private final int levelNumber;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int levelNumber() {
        return this.levelNumber;
    }
}

