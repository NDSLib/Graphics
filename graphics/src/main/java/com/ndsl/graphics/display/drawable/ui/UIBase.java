package com.ndsl.graphics.display.drawable.ui;

import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class UIBase extends GUIBase {
    public UIBase(Object o, Pos left_up) {
        super(o, left_up);
    }

    public UIBase(Object o, Rect rect) {
        super(o, rect);
    }

    public UIBase(Object o, Pos pos, String id) {
        super(o, pos, id);
    }

    public UIBase(Object o, Rect rect, String id) {
        super(o, rect, id);
    }

    public UIBase(String s) {
        super(s);
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
}
