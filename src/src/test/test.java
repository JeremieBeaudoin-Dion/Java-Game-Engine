package test;

import core.Game;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class test {

    public static void main(String[] args){
        String save = "newSavedGame";

        Game game;

        try {
            game = new Game();

            game.saveGame(save);

            game.loadGame(save);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
