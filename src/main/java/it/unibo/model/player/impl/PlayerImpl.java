package it.unibo.model.player.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import it.unibo.model.player.api.Player;
import it.unibo.model.territory.api.Territory;

public class PlayerImpl implements Player {

    private int id;
    private Set<Territory> territories = new HashSet<>();

    public PlayerImpl(final int id) {
        this.id = id;
    }

    public PlayerImpl(final int id, final Set<Territory> territories) {
        this(id);
        this.territories = territories;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getArmy(final Territory t) {
        return this.territories.stream().filter(x -> x.equals(t)).findFirst().get().getArmy();
    }

    @Override
    public void addTerritory(Stream<Territory> territory) {
        this.territories.addAll(territory.toList());
    }

    @Override
    public void removeTerritory(Stream<Territory> territory) {
        this.territories.removeAll(territory.toList());
    }

    @Override
    public Set<Territory> getTerritories() {
        return this.territories;
    }

    @Override
    public String toString() {
        return new String("ID -> " + this.getId() + "\nNumTerritory -> " + this.getTerritories().size()
            + "\nTerritories -> " + this.getTerritories());
    }
    
}
