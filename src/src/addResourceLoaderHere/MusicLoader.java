package addResourceLoaderHere;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Loads music in order to be played
 *
 * This class is necessary for the JGame Framework to work.
 * It can load as many images as needed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class MusicLoader {

    private AudioInputStream mainMenu;
    private AudioInputStream fishingGame;
    private AudioInputStream startGame;

    private Clip mainMenuClip;
    private Clip fishingGameClip;
    private Clip startGameClip;

    public MusicLoader() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //loadAllSounds();
        // openAllClips();
    }

    private void loadAllSounds() throws IOException, UnsupportedAudioFileException {
        mainMenu = AudioSystem.getAudioInputStream(new File("Resources/Music/MainTheme.wav"));
        fishingGame = AudioSystem.getAudioInputStream(new File("Resources/Music/GoneFishing.wav"));
        startGame = AudioSystem.getAudioInputStream(new File("Resources/Music/StartOfAnAdventure.wav"));
    }

    private void openAllClips() throws LineUnavailableException, IOException {

        mainMenuClip = AudioSystem.getClip();
        fishingGameClip = AudioSystem.getClip();
        startGameClip = AudioSystem.getClip();

        mainMenuClip.open(mainMenu);
        fishingGameClip.open(fishingGame);
        startGameClip.open(startGame);

    }





}
