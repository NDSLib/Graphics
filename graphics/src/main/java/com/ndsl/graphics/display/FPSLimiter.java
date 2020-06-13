package com.ndsl.graphics.display;

import com.ndsl.graphics.pos.Pos;

public class FPSLimiter {
    public static final long one_sec=1000000000;

    public int limitedFPS;
    public long StartTime;
    public long FPSCount;
    public long LatestTime;
    public long perNanoMill;
    public long MaxFPS;

    public FPSLimiter(int limitedFPS){
        this.limitedFPS=limitedFPS;
        this.StartTime=System.nanoTime();
        this.LatestTime=System.nanoTime();
        this.perNanoMill=one_sec / limitedFPS ;
//        System.out.println("perNanoMills:"+perNanoMill);
    }

    public boolean onUpdate(){
//        System.out.println("PastTime:"+(System.nanoTime() - LatestTime));
        boolean b=(System.nanoTime() - LatestTime)>perNanoMill;
        if(b){LatestTime=System.nanoTime();++FPSCount;}
        return b;
    }

    public void setDrawable(Display display){
        display.addDrawable(genDrawable());
    }

    private Drawable genDrawable() {
//        System.out.println("FPS:"+getFPS());
        return new Drawable("FPS:"+getFPS()+" "+"MaxFPS:"+MaxFPS,new Pos(100,100),"FPS_Mater");
    }

    public long getFPS(){
        if(FPSCount==0) return 0;
        if(System.nanoTime() - StartTime==0) return 0;
        if(((System.nanoTime() - StartTime)/one_sec)==0) return 0;
        long fps=(FPSCount/((System.nanoTime() - StartTime)/one_sec));
        if(fps>MaxFPS) MaxFPS=fps;
        return fps;
    }
}
