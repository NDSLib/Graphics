package com.ndsl.graphics.display.drawable.ui;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.RectDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class UIBase extends GUIBase {
    public UIBase(Object o, Pos pos, String id) {
        super(o, pos, id);
    }

    public UIBase(Object o, Rect rect, String id) {
        super(o, rect, id);
    }

    @Override
    public void onDraw(Graphics g) {
        super.onDraw(g);
        if(drawObject instanceof ICustomUI){
            ((ICustomUI) drawObject).onDraw(g,getShowingRect());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UIBase){
            if(((UIBase) obj).getID()!=null) {
                if (((UIBase) obj).getID().equals(getID())) return true;
            }
            if (((UIBase)obj).left_up.equals(left_up)){
                return ((UIBase)obj).drawObject.equals(drawObject);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public UIBase setDebug(Display display){
        display.addDrawable(new Drawable(new RectDrawable(Color.MAGENTA),super.getShowingRect(),getID()+"_debug"));
        return this;
    }
}
