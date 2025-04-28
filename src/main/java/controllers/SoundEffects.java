package controllers;

import javafx.scene.media.AudioClip;
import java.util.Objects;

public class SoundEffects {

    private static final AudioClip eatSound = new AudioClip(Objects.requireNonNull(SoundEffects.class.getResource("/snake_eating.mp3")).toExternalForm());
    private static final AudioClip dieSound = new AudioClip(Objects.requireNonNull(SoundEffects.class.getResource("/snake_dying.mp3")).toExternalForm());

    /**
     * eating sound plays. sound originally from Minecraft's eating sounds
     */
    public static void playEat() {
        eatSound.play();
    }

    /**
     * dying sound plays. sound originally from Roblox's oof
     */
    public static void playDie() {
        dieSound.play();
    }

}
