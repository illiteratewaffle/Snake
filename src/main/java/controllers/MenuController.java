package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button playButton;
    @FXML
    private Label topScoreDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void play(ActionEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board.fxml")));

        Stage stage = (Stage) playButton.getScene().getWindow();

        stage.setScene(new Scene(newRoot));
        stage.setTitle("Snake");
        stage.show();
    }

}
