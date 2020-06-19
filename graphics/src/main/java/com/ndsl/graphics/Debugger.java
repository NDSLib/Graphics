package com.ndsl.graphics;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.StringGui;
import com.ndsl.graphics.display.fps.FPSLimiter;
import com.ndsl.graphics.display.key.KeyInputHandler;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Pos;

public class Debugger {
    public FPSLimiter limiter;
    public KeyInputHandler keyInputHandler;
    public MouseInputHandler mouseInputHandler;

    public Debugger(FPSLimiter limiter, KeyInputHandler keyHandler, MouseInputHandler mouseInputHandler){
        this.limiter=limiter;
        this.keyInputHandler=keyHandler;
        this.mouseInputHandler=mouseInputHandler;
    }

    public void setDebug(Display display){
        display.addGui(genLimiterGui(display));
        display.addGui(genKeyInputGui(display));
        display.addGui(genMouseInputGui(display));
    }

    private GUIBase genMouseInputGui(Display display) {
        return new GUIBase(new StringGui("MouseButton:"+display.mouseInputHandler.getButton()),new Pos(10,300),"mouse_gui");
    }

    private GUIBase genLimiterGui(Display display) {
        return new GUIBase(new StringGui("FPS:"+limiter.getFPS()+"\n"+"MaxFPS:"+limiter.MaxFPS+"\n"+"LimitedFPS:"+limiter.limitedFPS+"\n"+"FramesCount:"+limiter.FPSCount+"\n"+"GoodFrames:"+limiter.GoodFrameCount+"\n"+"FrameHealth:"+limiter.getFrameHealth()+"\n"+"DrawableCounts:"+display.drawableList.size()),new Pos(10,30),"debugger_view");
    }

    private GUIBase genKeyInputGui(Display display){
        return new GUIBase(new StringGui(keyInputHandler.getActives()),new Pos(10,200),"debugger_view_key");
    }
}
