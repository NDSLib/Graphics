package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class RectDrawable implements ICustomDrawable{
    public Color r;
    public RectDrawable(Color r){
        this.r=r;
    }
    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.setColor(r);
        g.drawRect(showingRect.left_up.x,showingRect.left_up.y,showingRect.getWidth(),showingRect.getHeight());
    }
}
