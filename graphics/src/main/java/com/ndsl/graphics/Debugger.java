package com.ndsl.graphics;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.base.GUIBase;
import com.ndsl.graphics.display.drawable.non_sync.ui.StringGui;
import com.ndsl.graphics.display.fps.FPSLimiter;
import com.ndsl.graphics.display.key.KeyInputHandler;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

public class Debugger {
    public FPSLimiter limiter;
    public KeyInputHandler keyInputHandler;
    public MouseInputHandler mouseInputHandler;

    public Debugger(FPSLimiter limiter, KeyInputHandler keyHandler, MouseInputHandler mouseInputHandler) {
        this.limiter = limiter;
        this.keyInputHandler = keyHandler;
        this.mouseInputHandler = mouseInputHandler;
    }

    public void setDebug(Display display) {
        display.addDrawable(genLimiterGui(display));
        display.addDrawable(genKeyInputGui(display));
        display.addDrawable(genMouseInputGui(display));
    }

    public void setDebug(Display display, String layer_id) {
        display.addDrawable(genLimiterGui(display), layer_id);
        display.addDrawable(genKeyInputGui(display), layer_id);
        display.addDrawable(genMouseInputGui(display), layer_id);
    }

    private Drawable genMouseInputGui(Display display) {
        return new Drawable(new GUIBase(new StringGui("MouseButton:" + display.mouseInputHandler.getButton(), new Rect(0, 100, 100, 140), "mouse_gui")));
    }

    private Drawable genLimiterGui(Display display) {
        return new Drawable(new StringGui("FPS:" + limiter.getFPS() + "\n" + "MaxFPS:" + limiter.MaxFPS + "\n" + "LimitedFPS:" + limiter.limitedFPS + "\n" + "FramesCount:" + limiter.FPSCount + "\n" + "GoodFrames:" + limiter.GoodFrameCount + "\n" + "FrameHealth:" + limiter.getFrameHealth() + "\n" + "DrawableCounts:" + getDrawableCount(display), new Rect(new Pos(10, 30)), "debugger_view"));
    }

    private Drawable genKeyInputGui(Display display) {
        return new Drawable(new StringGui(keyInputHandler.getActives(), new Rect(new Pos(10, 200)), "debugger_view_key"));
    }

    private long getDrawableCount(Display display) {
        long l = 0;
        for (Layer layer : display.layerManager.layers.values()) {
            l += layer.drawableList.size();
        }
        return l;
    }
}
