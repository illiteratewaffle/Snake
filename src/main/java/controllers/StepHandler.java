package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// Fires the next game-step each time the Timeline ticks.
public class StepHandler implements EventHandler<ActionEvent> {

    private final BoardController controller;

    public StepHandler(BoardController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        controller.nextStep();
    }
}
