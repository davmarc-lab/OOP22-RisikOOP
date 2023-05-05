package it.unibo.model.territory;

import it.unibo.model.territory.api.TerritoryFactory;
import it.unibo.model.territory.impl.TerritoryFactoryImpl;

public class UseTerritory {

    public static void main(String[] args) {
        TerritoryFactory factory = new TerritoryFactoryImpl();

        factory.createTerritorySet();

        System.out.println(factory.toString());
    }
}
