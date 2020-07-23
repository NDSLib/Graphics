package com.ndsl.graphics.display.sub;

import com.ndsl.graphics.Debugger;
import com.ndsl.graphics.display.BorderBar;
import com.ndsl.graphics.display.DefaultBar;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.key.KeyInputHandler;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class BorderLessSubWindow extends SubWindow {
    public BorderLessSubWindow(Display display){
        this(display.getTitle(),display.bufferSize,new Rect(display.getBounds()));
    }

    public BorderLessSubWindow(String title, int bufferSize, @NotNull Rect displayBound){
        super();
        this.setTitle(title);
        this.setBounds(displayBound.left_up.x,displayBound.left_up.y,displayBound.getWidth(),displayBound.getHeight());
        //*!!THIS!!*//
        this.setUndecorated(true);
        this.setVisible(true);
        this.createBufferStrategy(bufferSize);
        this.bufferSize=bufferSize;
        this.bufferStrategy = this.getBufferStrategy();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this.exitManager.WL);
        this.keyHandler=new KeyInputHandler(this);
        this.mouseInputHandler=new MouseInputHandler(this);
        this.debugger=new Debugger(limiter,keyHandler,mouseInputHandler);
        Start_Time=System.currentTimeMillis();
        this.bar=new DefaultBar(this);
    }

    public BorderLessSubWindow(BorderBar bar, String title, int bufferSize, @NotNull Rect displayBound){
        this(title,bufferSize, displayBound);
        this.bar=bar;
    }

    public BorderBar bar;

    @Override
    public void update(){
        if(bufferStrategy.contentsLost()) bufferStrategy=this.getBufferStrategy();
        switch (attitude){
            case AlwaysUpdate:
                update(getGraphic());
            case NoFocusNoUpdate:
                if(this.hasFocus()){
                    update(getGraphic());
                }
        }
    }

    @Override
    public void update(Graphics g) {
        boolean need_draw=false;
        for(Layer layer:layerManager.getAll()){
            for(Drawable d:layer.drawableList){
                if(isShowing(d)){
                    if(!need_draw) clear();
                    d.onDraw(g);
                    if(isDebuggingMode) drawDebugRect(d.getShowingRect(),g);
                    resetGraphics(g);
                    need_draw=true;
                }
            }
        }
        if(!need_draw) clear();
        bar.onDraw(g);
        repaint();
    }
}
