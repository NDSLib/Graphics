package com.ndsl.graphics.display.audio;

import javax.sound.midi.Sequencer;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class AudioOutput {
    public Map<File,InputClip> fileClipMap=new HashMap<>();

    public AudioOutput (){

    }

    public void load(File file){
        fileClipMap.put(file,loadFromFile(file));
    }

    public InputClip loadFromFile(File file) {
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(file)) {

            AudioFormat af = ais.getFormat();
            DataLine.Info dataLine = new DataLine.Info(Clip.class, af);
            Clip c = (Clip) AudioSystem.getLine(dataLine);
            c.open(ais);

            return new InputClip(ais, c);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isLoaded(File file){
        return fileClipMap.containsKey(file);
    }

    public void start(File file) throws IOException, LineUnavailableException {
        start(fileClipMap.get(file));
    }

    public void start(InputClip clip) throws IOException, LineUnavailableException {
        if (!clip.clip.isOpen()) clip.clip.open(clip.inputStream);
        clip.clip.start();
    }

    public void stop(File file){
        stop(fileClipMap.get(file));
    }

    public void stop(InputClip clip){
        if(clip.clip.isRunning()){
            clip.clip.stop();
        }
    }
}
