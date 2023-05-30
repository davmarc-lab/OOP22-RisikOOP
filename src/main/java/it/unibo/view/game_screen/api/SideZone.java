package it.unibo.view.game_screen.api;

import it.unibo.view.game_screen.ButtonPanel;
import it.unibo.view.game_screen.impl.CardPanel;
import it.unibo.view.game_screen.impl.InfoPanel;

public interface SideZone {

    ButtonPanel getButtonPanel();

    CardPanel getCardPanel();

    InfoPanel getInfoPanel();
}
