package OtherComponents;

import javax.sound.sampled.*;

public class MusicMethods {

    public static final String MENU_MUSIC = "/res/menuMusic.wav";
    public static final String GAME_MUSIC = "/res/gameMusic.wav";
    public static final String HOVER_MUSIC = "/res/hoverMusic.wav";
    public static final String CLICK_MUSIC = "/res/clickMusic.wav";

    private Clip clip;
    private boolean musicLoaded = false;

    public void loadMusic(String filePath) {
        try {
            if (!musicLoaded) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(MusicMethods.class.getResource(filePath));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                musicLoaded = true;
                if (filePath.equals(MENU_MUSIC) || filePath.equals(GAME_MUSIC)) {
                    play(true);
                } else {
                    play(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(boolean loop) {
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            if (musicLoaded) {
                setVolume(0.88f); // Set volume to 75%
                clip.start();
            }
        }
    }

    public void stop() {
        clip.stop();
        clip.close();
        musicLoaded = false;
    }

    public void setMusicLoaded(boolean isLoaded) {
        this.musicLoaded = isLoaded;
    }

    // Method to set the volume (0.0 to 1.0)
    private void setVolume(float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }
}