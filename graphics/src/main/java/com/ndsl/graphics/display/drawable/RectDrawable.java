package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class RectDrawable implements IDrawable{
    public Color c= GraphicsMain.Default_Color;
    public Rect r;
    public String id;
    public RectDrawable(Rect r,Color c,String id){
        this.c=c;
        this.r=r;
        this.id=id;
    }
    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.setColor(c);
        g.drawRect(showingRect.left_up.x,showingRect.left_up.y,showingRect.getWidth(),showingRect.getHeight());
    }

    @Override
    public Rect getShowingRect() {
        return r;
    }

    @Override
    public boolean isShowing(Display display) {
        return display.isShowing(getShowingRect());
    }

    @Override
    public String getID() {
        return id;
    }
}
