package it.unibo.view.game_screen.api;

public interface SideZone {

    GameZone getParentEntity();

    ButtonZone getButtonPanel();

    CardZone getCardPanel();

    InfoZone getInfoPanel();
}
