package com.ndsl.graphics.display.drawable.ui;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Rect;

import static com.ndsl.graphics.display.mouse.MouseInputHandler.MOUSE_LEFT_BUTTON;
import static com.ndsl.graphics.display.mouse.MouseInputHandler.MOUSE_RIGHT_BUTTON;

public class MouseInputListener {
    public Rect rect;
    public Display display;
    public MouseInputHandler handler;

    public MouseInputListener(Display display, Rect rect){
        this.rect=rect;
        this.display=display;
        this.handler=display.mouseInputHandler;
    }

    public boolean isOverlap(){
        return handler.getNow_mouse_pos().contain(rect);
    }

    public boolean isClicking(){
        return isOverlap() && handler.isClicking;
    }

    public boolean isRightClicking(){
        return isClicking() && handler.getButton()==MOUSE_RIGHT_BUTTON;
    }

    public boolean isLeftClicking(){
        return isClicking() && handler.getButton()==MOUSE_LEFT_BUTTON;
    }
}
