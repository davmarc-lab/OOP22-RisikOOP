package it.unibo.model.combat;

import java.util.stream.Stream;

import it.unibo.model.combat.api.Combat;
import it.unibo.model.combat.impl.CombatImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.territory.api.Territory;
import it.unibo.model.territory.impl.TerritoryImpl;

public class UseCombat {

    private static final int MAX_ATTACK_DEFEND_ARMY = 3;
    
    private static void debug(String s) {
        System.out.println(s);
    }
    public static void main(String[] args) {
        final Player p1 = new PlayerImpl(1);
        final Player p2 = new PlayerImpl(2);
        final Territory t1 = new TerritoryImpl("Europe");
        final Territory t2 = new TerritoryImpl("Africa");
        final Territory t3 = new TerritoryImpl("America");
        final Territory t4 = new TerritoryImpl("Russia");
        final Territory t5 = new TerritoryImpl("China");
        final Territory t6 = new TerritoryImpl("Japan");

        p1.addTerritory(Stream.of(t1, t5, t4, t3));
        p2.addTerritory(Stream.of(t2, t6));
        var s = 2;
        t1.addArmy(s);
        var d = 3;
        t2.addArmy(3);

        while (s > t1.getArmy() || s > MAX_ATTACK_DEFEND_ARMY || d > t2.getArmy() || d > MAX_ATTACK_DEFEND_ARMY) {
            debug("Operation invalid");
        }
        Combat c1 = new CombatImpl(t1, s, t2, d);
        var result = c1.attack();
        for (var r: result) {
            if (r.equals(Combat.Results.WIN)) {
                t2.addArmy(-1);
            } else if (r.equals(Combat.Results.LOSE)) {
                t1.addArmy(-1);
            }
        }
        System.out.println(t1.getName() + " -> " + t1.getArmy());
        System.out.println(t2.getName() + " -> " + t2.getArmy());
    }
}
