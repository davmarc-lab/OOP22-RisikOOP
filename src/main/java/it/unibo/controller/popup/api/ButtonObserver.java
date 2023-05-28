package it.unibo.controller.popup.api;

public interface ButtonObserver {

    void addValue(int n);

    void confirmMovement();

    void cancelMovement();
}
