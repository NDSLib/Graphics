package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class RealTimeDrawable extends Drawable{
    public IRealTimeCustomDrawable d;
    public RealTimeDrawable(IRealTimeCustomDrawable d,String id){
        super(d,d.getShowingRect(),id);
        this.d=d;
    }

    @Override
    public Rect getShowingRect() {
        return d.getShowingRect();
    }

    @Override
    public void onDraw(Graphics g){
        if(drawObject instanceof IRealTimeCustomDrawable){
            ((IRealTimeCustomDrawable) drawObject).onDraw(g,getShowingRect());
        }
    }
}
