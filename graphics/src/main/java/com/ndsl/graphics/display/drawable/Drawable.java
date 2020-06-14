package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class Drawable {
    public Object drawObject;
    public Pos left_up=null;
    public Drawable(Object o, Pos left_up){
        drawObject=o;
        this.left_up=left_up;
    }

    public Rect drawRect=null;

    public Drawable(Object o, Rect rect){
        drawObject=o;
        this.drawRect=rect;
        this.left_up=rect.left_up;
    }

    public String Drawable_id=null;

    public Drawable(Object o,Pos pos,String id){
        this(o,pos);
        Drawable_id=id;
    }

    public Drawable(Object o,Rect rect,String id){
        this(o,rect);
        Drawable_id=id;
    }

    public Drawable(String s){
        this("ForIDOnlyObject",new Pos(-1000,-1000));
        Drawable_id=s;
    }

    public void onDraw(Graphics g){
        if(g==null) {
            System.out.println("Graphics is null");
            return;
        }
        if(drawObject instanceof Image){
            g.drawImage((Image)drawObject,left_up.x,left_up.y,null);
        }else if(drawObject instanceof String){
            g.drawString((String)drawObject,left_up.x, left_up.y);
        }else if(drawObject instanceof ICustomDrawable){
            ((ICustomDrawable) drawObject).onDraw(g,getShowingRect());
        }
    }

    public Rect getShowingRect(){
        if(left_up==null) return drawRect;
        return new Rect(left_up,left_up);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Drawable){
            if(((Drawable) obj).getID()!=null) {
                if (((Drawable) obj).getID().equals(getID())) return true;
            }
            if (((Drawable)obj).left_up.equals(left_up)){
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
