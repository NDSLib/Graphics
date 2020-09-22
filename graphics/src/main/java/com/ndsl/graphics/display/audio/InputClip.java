package com.ndsl.graphics.display.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class InputClip {
    public AudioInputStream inputStream;
    public Clip clip;

    public InputClip(AudioInputStream inputStream, Clip clip) {
        this.clip = clip;
        this.inputStream = inputStream;
    }
}
