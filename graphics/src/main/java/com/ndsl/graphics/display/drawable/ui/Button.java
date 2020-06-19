package com.ndsl.graphics.display.drawable.ui;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.DrawableUtil;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.StringGui;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Button implements ICustomUI{
    public Rect rect;
    public MouseInputListener listener;
    public Button(Rect rect,Display display){
        this.listener=new MouseInputListener(display,rect);
        this.rect=rect;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        if(listener.isClicking()){
            drawString("isClicking",g,showingRect,12);
        }else {
            drawString("isNotClicking",g,showingRect,12);
        }
    }

    public static UIBase genButtonUI(Rect rect,Display display,String id){
        return new UIBase(new Button(rect,display),rect,id);
    }

    public static UIBase genAndAddButton(Rect rect,Display display,String id){
        UIBase uiBase=genButtonUI(rect, display, id);
        display.addUI(uiBase);
        return uiBase;
    }

    public void drawString(@NotNull String data, Graphics g, Rect rect, int fontsize){
        DrawableUtil.drawString(data,g,rect,fontsize);
    }
}
