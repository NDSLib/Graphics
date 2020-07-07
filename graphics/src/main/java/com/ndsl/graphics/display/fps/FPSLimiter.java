package com.ndsl.graphics.display.fps;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.StringGui;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

public class FPSLimiter {
    public static final long one_sec=1000000000;
    public static final long acceptable_range_frame=10;
    public static final long Keep_UP_Health=90;
    public static final long Keep_DOWN_Health=50;

    public int limitedFPS;
    public long StartTime;
    public long FPSCount;
    public long LatestTime;
    public long perNanoMill;
    public long MaxFPS;
    public long LatestFPS;
    public long GoodFrameCount;
    public long BadFrameCount;

    public FPSAttitude attitude;

    public FPSLimiter(int limitedFPS,FPSAttitude attitude){
        this.limitedFPS=limitedFPS;
        this.StartTime=System.nanoTime();
        this.LatestTime=System.nanoTime();
        this.perNanoMill=one_sec / limitedFPS ;
        this.attitude=attitude;
//        System.out.println("perNanoMills:"+perNanoMill);
    }

    public boolean onUpdate(){
//        System.out.println("PastTime:"+(System.nanoTime() - LatestTime));
        boolean b=(System.nanoTime() - LatestTime)>perNanoMill;
        if(b){
            LatestTime=System.nanoTime();++FPSCount;
            if (getFPS() >= limitedFPS - acceptable_range_frame) {
                GoodFrameCount++;
            } else {
                BadFrameCount++;
            }
        }
        if((attitude.equals(FPSAttitude.KEEP_UP_FPS)||attitude.equals(FPSAttitude.KEEP_UP_AND_DOWN_FPS)) && getFrameHealth() > Keep_UP_Health){
            Keep_UP();
        }else if(attitude.equals(FPSAttitude.KEEP_UP_AND_DOWN_FPS) && getFrameHealth() < Keep_DOWN_Health){
            Keep_Down();
        }
        return b;
    }
    public long Keep_up_cooldown;

    private void Keep_UP() {
        if(Keep_up_cooldown==0) {
            setMaxFPS(limitedFPS + 1);
            Keep_up_cooldown=1000;
        }else{
            Keep_up_cooldown--;
        }
    }

    private void Keep_Down(){
        if(Keep_up_cooldown==0) {
            setMaxFPS(limitedFPS - 1);
            Keep_up_cooldown=1000;
        }else{
            Keep_up_cooldown--;
        }
    }

    public void setDrawable(Display display){
        display.addDrawable(genDrawable(display));
    }

    private Drawable genDrawable(Display display) {
        return new Drawable(new GUIBase(new StringGui("FPS:"+getFPS()+"\n"+"MaxFPS:"+MaxFPS+"\n"+"LimitedFPS:"+limitedFPS+"\n"+"FramesCount:"+FPSCount+"\n"+"GoodFrames:"+GoodFrameCount+"\n"+"FrameHealth:"+getFrameHealth()+"\n"+"DrawableCounts:"+getDrawableCount(display),new Rect(new Pos(10,40)),"FPS_Mater")));
    }

    private long getDrawableCount(Display display){
        long l=0;
        for(Layer layer:display.layerManager.layers.values()){
            l+=layer.drawableList.size();
        }
        return l;
    }

    public long getFPS(){
        if(FPSCount==0) return 0;
        if(System.nanoTime() - StartTime==0) return 0;
        if(((System.nanoTime() - StartTime)/one_sec)==0) return 0;
        long fps=(FPSCount/((System.nanoTime() - StartTime)/one_sec));
        if(fps>MaxFPS) MaxFPS=fps;
        LatestFPS=fps;
        return fps;
    }

    public long  getFrameHealth(){
        if(FPSCount==0) return 0;
        return (long) ((double) GoodFrameCount/ (double) FPSCount * 100);
    }

    public void setMaxFPS(int maxFPS) {
        limitedFPS=maxFPS;
        this.perNanoMill=one_sec / limitedFPS;
    }
}
