package com.ndsl.graphics.display.fps;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.base.GUIBase;
import com.ndsl.graphics.display.drawable.non_sync.ui.StringGui;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

public class FPSLimiter {
    public static final long one_sec = 1000000000;
    public static final long acceptable_range_frame = 10;
    public static final long Keep_UP_Health = 90;
    public static final long Keep_DOWN_Health = 50;

    public int limitedFPS;
    public long FPSCount;
    public long perNanoMill;
    public long MaxFPS;
    public long LatestFPS;
    public long GoodFrameCount;
    public long BadFrameCount;

    public FPSAttitude attitude;
    public long Keep_up_cooldown;
    public Long[] timeStamps = new Long[100];

    public FPSLimiter(int limitedFPS, FPSAttitude attitude) {
        setMaxFPS(limitedFPS);
        this.attitude = attitude;
    }

    public boolean onUpdate() {
//        System.out.println("PastTime:"+(System.nanoTime() - LatestTime));
        boolean b = (System.nanoTime() - getLastTime()) > perNanoMill;
//        System.out.println("b:"+b);
//        System.out.println("time:"+(System.nanoTime() - getLastTime()));
//        System.out.println("perNano:"+perNanoMill);
        if (b) {
            addStamp();
            ++FPSCount;
            if (getFPS() >= limitedFPS - acceptable_range_frame) {
                GoodFrameCount++;
            } else {
                BadFrameCount++;
            }
        }
//        if ((attitude.equals(FPSAttitude.KEEP_UP_FPS) || attitude.equals(FPSAttitude.KEEP_UP_AND_DOWN_FPS)) && getFrameHealth() > Keep_UP_Health) {
//            Keep_UP();
//        } else if (attitude.equals(FPSAttitude.KEEP_UP_AND_DOWN_FPS) && getFrameHealth() < Keep_DOWN_Health) {
//            Keep_Down();
//        }
        return b;
    }

    private void Keep_UP() {
        if (Keep_up_cooldown == 0) {
            setMaxFPS(limitedFPS + 1);
            Keep_up_cooldown = 1000;
        } else {
            Keep_up_cooldown--;
        }
    }

    private void Keep_Down() {
        if (Keep_up_cooldown == 0) {
            setMaxFPS(limitedFPS - 1);
            Keep_up_cooldown = 1000;
        } else {
            Keep_up_cooldown--;
        }
    }

    public void setDrawable(Display display) {
        display.addDrawable(genDrawable(display));
    }

    private Drawable genDrawable(Display display) {
        return new Drawable(new GUIBase(new StringGui("FPS:" + getFPS() + "\n" + "MaxFPS:" + MaxFPS + "\n" + "LimitedFPS:" + limitedFPS + "\n" + "FramesCount:" + FPSCount + "\n" + "GoodFrames:" + GoodFrameCount + "\n" + "FrameHealth:" + getFrameHealth() + "\n" + "DrawableCounts:" + getDrawableCount(display), new Rect(new Pos(10, 40)), "FPS_Mater")));
    }

    private long getDrawableCount(Display display) {
        long l = 0;
        for (Layer layer : display.layerManager.layers.values()) {
            l += layer.drawableList.size();
        }
        return l;
    }

    public long getFPS() {
//        if (FPSCount == 0) return 0;
        if (getLastTime() - getFirstTime() == 0) return 0;
//        if (((getLastTime() - getFirstTime()) / one_sec) == 0) return 0;
//        System.out.println("PerFrame:"+((getLastTime() - getFirstTime()) / getCount()));
        long fps = (one_sec / ((getLastTime() - getFirstTime()) / getCount()));
        if (fps > MaxFPS) MaxFPS = fps;
        LatestFPS = fps;
        return fps;
    }

    public long getFrameHealth() {
        if (FPSCount == 0) return 0;
        return (long) ((double) GoodFrameCount / (double) FPSCount * 100);
    }

    public void setMaxFPS(int maxFPS) {
        limitedFPS = maxFPS;
        this.perNanoMill = one_sec / limitedFPS;
    }

    private void addStamp() {
        shift();
        timeStamps[0] = System.nanoTime();
    }

    private void shift() {
        Long[] copy = new Long[100];
        System.arraycopy(timeStamps, 0, copy, 1, timeStamps.length - 1);
        timeStamps = copy;
    }

    private long getFirstTime() {
        long l = 0;
        for (int i = 0; i < timeStamps.length; i++) {
            if (timeStamps[i] != null) l = timeStamps[i];
        }
        return l;
    }

    public long getLastTime() {
        return timeStamps[0] == null ? 0 : timeStamps[0];
    }

    private long getCount() {
        long l = 0;
        for (int i = 0; i < timeStamps.length; i++) {
            if (timeStamps[i] != null) l += 1;
        }
        return l;
    }
}
