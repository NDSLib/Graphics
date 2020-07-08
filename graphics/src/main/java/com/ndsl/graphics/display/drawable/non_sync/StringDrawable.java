package com.ndsl.graphics.display.drawable.non_sync;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.base.DrawableUtil;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.Objects;

public class StringDrawable implements IDrawable {
    public static final String Default_Font_String="Meiryo UI";
    public static final int Default_Font_Size=12;
    public static final Font Default_Font=new Font(Default_Font_String, Font.PLAIN, Default_Font_Size);

    public Rect r;
    @Override
    public void onDraw(Graphics g, Rect rect) {
        g.setFont(Objects.requireNonNullElseGet(font, () -> new Font(Default_Font_String, Font.PLAIN, size)));
        if(size!=-1){
            drawString(data,g,rect,size);
        }else{
            drawString(data,g,rect);
        }
        g.setFont(Default_Font);
    }

    @Override
    public Rect getShowingRect() {
        return r;
    }

    @Override
    public boolean isShowing(Display display) {
        return display.isShowing(r);
    }

    @Override
    public String getID() {
        return id;
    }

    public String data;
    public Font font=null;
    public int size=-1;
    public String id;

    public StringDrawable(String s,Rect r,Font f,String id){
        this.data=s;
        this.font=f;
        this.id=id;
        this.r=r;
    }

    public StringDrawable(String s,Rect r,int size,String id){
        this.data=s;
        this.size=size;
        this.id=id;
        this.r=r;
    }

    public StringDrawable(String s,Rect r,String id){
        this(s,r,Default_Font_Size,id);
    }

    public void drawString(String data,Graphics g,Rect rect){
        DrawableUtil.drawString(data,g,rect,Default_Font_Size);
    }

    public void drawString(String data,Graphics g,Rect rect,int fontsize){
        DrawableUtil.drawString(data,g,rect,fontsize);
    }
}
