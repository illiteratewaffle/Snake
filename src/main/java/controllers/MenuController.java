package controllers;

import game.TopScore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private MediaPlayer menuMusic;

    @FXML
    private Button playButton;
    @FXML
    private Label topScoreDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // generate top score
        topScoreDisplay.setText("TOP SCORE: " + TopScore.getScoreFromFile());

        // play music
        try {
            URL resource = getClass().getResource("/menu_soundtrack.mp3");
            if (resource != null) {
                Media media = new Media(resource.toExternalForm());
                menuMusic = new MediaPlayer(media);
                menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
                menuMusic.setVolume(0.2);
                menuMusic.play();
            } else {
                System.out.println("Menu music file not found!");
            }
        } catch (Exception e) {
            System.out.println("Error loading menu music.");
            e.printStackTrace();
        }
    }

    public void play(ActionEvent event) throws IOException {
        menuMusic.stop();

        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board.fxml")));

        Stage stage = (Stage) playButton.getScene().getWindow();

        stage.setScene(new Scene(newRoot));
        stage.setTitle("Snake");
        stage.show();
    }



}
