package com.ndsl.graphics.display.sub;

import com.ndsl.graphics.display.Display;

public class Syncer {
    public Object Object;
    public volatile boolean isRead=true;
    public Syncer(){
    }

    public void send(Syncer syncer,Object o){
        syncer.Object=o;
        syncer.isRead=false;
        syncer.onChanged();
    }

    public Object waitForResponse(Syncer syncer,Object o) throws InterruptedException {
        send(syncer,o);
        while (isRead) {
            Thread.onSpinWait();
        }
        return Object;
    }
    
    public void onChanged(){
        //TODO
    }
}
