package it.unibo.controller.popup.api;

public interface PopupController<X, Y> {
    X getFirstTypeObgject();

    Y getSecondTypeObject();

    int getValue();

    void setValue(int value);

    abstract void startPopup();

    abstract void closePopup();
}
