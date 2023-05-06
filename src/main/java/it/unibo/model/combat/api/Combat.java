package it.unibo.model.combat.api;

import java.util.List;

public interface Combat {

    static enum Results {
        WIN, LOSE, NONE
    }

    List<Results> attack();
}
