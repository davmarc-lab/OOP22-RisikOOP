package it.unibo.view.movement.api;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public interface MovementFrame {

    void startPopup();

    void closePopup();

    void createPanel();

    // BUTTONS
    JButton getButtonUp();

    void addActionListenerButtonUp(ActionListener listener);

    JButton getButtonDown();

    void addActionListenerButtonDown(ActionListener listener);

    JButton getConfirmButton();

    void addActionListenerConfirmButton(ActionListener listener);

    JButton getCancelButton();

    void addActionListenerCancelButton(ActionListener listener);
    // BUTTONS

    int getSourceTroops();

    void setSourceTroops(int value);

    int getInputNumberTroops();

    void setInputNumberTroops(int value);

    String getCurrentTerritoryStatus();

    void setCurrentTerritoryStatus(String status);

    String getDestinationTerritory();

    void setDestinationTerritory(String name);

    String getSourceTerritory();

    void setSourceTerritory(String name);

}
