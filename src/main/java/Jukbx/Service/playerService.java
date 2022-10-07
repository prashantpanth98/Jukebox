package Jukbx.Service;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class playerService
{
    AudioInputStream audioInputStream;
    Clip clip;
    String status;
    Long currentFrame;
    int id2;
    public void playSong(int id) throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        //opens the song to perform operations like playing
        id2=id;
        audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/" + id + ".wav"));
        clip=AudioSystem.getClip(); //creating a player object
        clip.open(audioInputStream); //makes song available to play
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }
    public void chooseOperation(int optn) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        switch(optn) {
            case 1:
                pause();
                break;
            case 2:
                resume();
                break;
            case 3:
                restart();
                break;
            case 4:
                stopAudio();
                break;
            case 5:
                stopAudio();
                break;
            case 6:
                stopAudio();
                break;
        }
    }
    public void play() //to Play Audio
    {
        clip.start();
        status="play";
    }
    public void pause() //to Pause Audio
    {
        if(status.equalsIgnoreCase("paused")) {
            System.out.println("Audio is already Paused");
            return;
        }
        this.currentFrame=this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
    //to Resume Audio
    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        if (status.equalsIgnoreCase("play"))
        {
            System.out.println("Audio is still already playing");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
    //to restart the audio
    public void restart() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        clip.stop();;
        clip.close();
        resetAudioStream();
        currentFrame=0L;
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
    //stop the audio
    public void stopAudio() throws LineUnavailableException ,UnsupportedAudioFileException, IOException
    {
        currentFrame=0L;
        clip.close();
        clip.stop();

    }
    public void resetAudioStream() throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        audioInputStream=AudioSystem.getAudioInputStream(new File("src/main/resources/" + id2 + ".wav"));
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}