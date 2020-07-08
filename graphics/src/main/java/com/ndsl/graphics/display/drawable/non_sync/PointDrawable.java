package com.ndsl.graphics.display.drawable.non_sync;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class PointDrawable implements IDrawable {
    public Color drawColor= GraphicsMain.Default_Color;
    public Pos pos;
    public Rect rect;
    public String id;
    public PointDrawable(Pos pos, Color color,String id){
//        super((Object)pos,pos);
        this.pos=pos;
        this.drawColor=color;
        this.rect=new Rect(pos,pos);
        this.id=id;
    }

    public PointDrawable(Pos pos,String id){
        this(pos,GraphicsMain.Default_Color,id);
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.setColor(drawColor);
        g.drawRect(showingRect.left_up.x,showingRect.left_up.y,showingRect.getWidth(),showingRect.getHeight());
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
}
