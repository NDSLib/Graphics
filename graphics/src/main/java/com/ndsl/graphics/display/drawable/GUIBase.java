package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

public class GUIBase extends Drawable{
    public GUIBase(Object o, Pos left_up) {
        super(o, left_up);
    }

    public GUIBase(Object o, Rect rect) {
        super(o, rect);
    }

    public GUIBase(Object o, Pos pos, String id) {
        super(o, pos, id);
    }

    public GUIBase(Object o, Rect rect, String id) {
        super(o, rect, id);
    }

    public GUIBase(String s) {
        super(s);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GUIBase){
            if(((GUIBase) obj).getID()!=null) {
                if (((GUIBase) obj).getID().equals(getID())) return true;
            }
            if (((GUIBase)obj).left_up.equals(left_up)){
                return ((GUIBase)obj).drawObject.equals(drawObject);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
