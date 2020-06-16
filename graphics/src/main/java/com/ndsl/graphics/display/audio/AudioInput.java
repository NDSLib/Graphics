package com.ndsl.graphics.display.audio;

import javax.sound.sampled.*;
import java.io.InputStream;

public class AudioInput {
    public int line_num;
    public AudioFormat format;
    public Mixer.Info[] mixer_info;
    public DataLine.Info input_info;
    public int buffer_size;
    public TargetDataLine input_line;
    public byte[] Current_Bytes;


    public AudioInput(int line_num) throws LineUnavailableException {
        this.line_num=line_num;
        init();
    }

    public void init(){
//        InputStream stream;
//        AudioInputStream inputStream=new AudioInputStream(, format, );
        //        format=new AudioFormat(8000, 8, 1, true, true);
//        mixer_info= AudioSystem.getMixerInfo();
//        input_info=new DataLine.Info(TargetDataLine.class, format);
//        buffer_size=(int) format.getSampleRate() * format.getFrameSize();
//
//        for (int i = 0; i < mixer_info.length; i++) {
//            System.out.println(i+":"+AudioSystem.getMixer(mixer_info[line_num]).getSourceLines().length);
//        }
//
//        input_line = (TargetDataLine)AudioSystem.getMixer(mixer_info[line_num]).getSourceLines()[0];
//        input_line.open(format, buffer_size);
//        input_line.start();
//        Current_Bytes=new byte[buffer_size];
    }

    public void getBufferInput(){
        input_line.read(Current_Bytes,0,Current_Bytes.length);
    }


}
