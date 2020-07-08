package com.ndsl.graphics.display.drawable.non_sync;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class RealTimeDrawable implements IDrawable {
    public IDrawable d;
    public Rect rect;
    public String id;
    public RealTimeDrawable(IDrawable d,String id){
        this.d=d;
        this.rect=d.getShowingRect();
        this.id=id;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        d.onDraw(g,showingRect);
    }

    public void onDraw(Graphics g) {
        d.onDraw(g,getShowingRect());
    }

    @Override
    public Rect getShowingRect() {
        return rect;
    }

    @Override
    public boolean isShowing(Display display) {
        return display.isShowing(getShowingRect());
    }

    @Override
    public String getID() {
        return id;
    }

    public RealTimeDrawable setRect(Rect r){
        this.rect=r;
        return this;
    }
}
