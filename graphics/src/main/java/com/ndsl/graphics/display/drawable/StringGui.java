package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class StringGui implements IDrawable{
    public static final String Default_Font_String="Arial";
    public static final int Default_Font_Size=12;
    public static final Font Default_Font=new Font(Default_Font_String, Font.PLAIN, Default_Font_Size);


    public String s;
    public Font font;
    public int size=-1;
    public Rect rect;
    public String id;

    public StringGui(String s,Rect rect,Font f,int size,String id){
        this.s=s;
        this.font=f;
        this.size=size;
        this.rect=rect;
        this.id=id;
    }

    public StringGui(String s,Rect rect,String id){
        this(s,rect,Default_Font,Default_Font_Size,id);
    }

    public void drawString(String data, Graphics g, Rect rect){
        drawString(data, g, rect,Default_Font_Size);
    }

    public void drawString(@NotNull String data, Graphics g, Rect rect, int fontsize){
        DrawableUtil.drawString(data,g,rect,fontsize);
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.setFont(font);
        drawString(s,g,showingRect,size);
        g.setFont(Default_Font);
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringGui){
            return ((StringGui) obj).s.equals(s) && ((StringGui) obj).size==size;
        }return false;
    }
}
