package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class Drawable {
    public IDrawable drawObject;
    public String Drawable_id=null;

    public Drawable(IDrawable d){
        drawObject=d;
        Drawable_id=d.getID();
    }

    public Drawable(IDrawable d,String id){
        this(d);
        Drawable_id=id;
    }

    public void onDraw(Graphics g){
        if(g==null) {
            System.out.println("Graphics is null");
            return;
        }
        drawObject.onDraw(g,drawObject.getShowingRect());
    }

    public Rect getShowingRect(){
        return drawObject.getShowingRect();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Drawable){
            if(((Drawable) obj).getID()!=null) {
                if (((Drawable) obj).getID().equals(getID())) return true;
            }
            if (((Drawable)obj).getShowingRect().left_up.equals(getShowingRect().left_up)){
                return ((Drawable)obj).drawObject.equals(drawObject);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Nullable
    public String getID(){
        return Drawable_id;
    }
}
