package com.ndsl.graphics.display.drawable.base;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class GUIBase implements IDrawable {
    public IDrawable drawObject;
    public String id;
    public GUIBase(IDrawable drawObject) {
        this.drawObject=drawObject;
        this.id=drawObject.getID();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GUIBase){
            if(((GUIBase) obj).getID()!=null) {
                if (((GUIBase) obj).getID().equals(getID())) return true;
            }
            if (((GUIBase)obj).getShowingRect().left_up.equals(getShowingRect().left_up)){
                return ((GUIBase)obj).drawObject.equals(drawObject);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        drawObject.onDraw(g,getShowingRect());
    }

    @Override
    public Rect getShowingRect() {
        return drawObject.getShowingRect();
    }

    @Override
    public boolean isShowing(Display display) {
        return true;
    }

    @Override
    public String getID() {
        return id;
    }
}
