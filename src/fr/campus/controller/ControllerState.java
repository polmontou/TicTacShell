package fr.campus.controller;

/**
 * Enumeration representing the different states of the game controller.
 * Used to manage the application flow and state transitions.
 */
public enum ControllerState {
    NEW,
    INITIALIZED,
    PLAYING,
    ENDED,
    EXIT
}