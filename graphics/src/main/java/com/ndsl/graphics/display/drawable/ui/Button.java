package com.ndsl.graphics.display.drawable.ui;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.*;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Button implements IDrawable {
    public Rect rect;
    public MouseInputListener listener;
    public String id;
    public Button(Rect rect,Display display,String id){
        this.listener=new MouseInputListener(display,rect);
        this.rect=rect;
        this.id=id;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        if(listener.isClicking()){
            drawString("isClicking",g,showingRect,12);
            onClick();
        }else {
            drawString("isNotClicking",g,showingRect,12);
        }
    }

    public void onClick(){

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

    public static Drawable genButtonUI(Rect rect,Display display,String id){
        return new Drawable(new Button(rect,display,id));
    }

    public static Drawable genAndAddButton(Rect rect,Display display,String id){
        Drawable drawable=genButtonUI(rect, display, id);
        display.addDrawable(drawable);
        return drawable;
    }

    public void drawString(@NotNull String data, Graphics g, Rect rect, int fontsize){
        DrawableUtil.drawString(data,g,rect,fontsize);
    }
}
