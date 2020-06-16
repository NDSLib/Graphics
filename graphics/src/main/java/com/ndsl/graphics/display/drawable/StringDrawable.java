package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.Objects;

public class StringDrawable implements ICustomDrawable{
    public static final String Default_Font_String="Meiryo UI";
    public static final int Default_Font_Size=12;
    public static final Font Default_Font=new Font(Default_Font_String, Font.PLAIN, Default_Font_Size);

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

    public String data;
    public Font font=null;
    public int size=-1;

    public StringDrawable(String s,Font f){
        this.data=s;
        this.font=f;
    }

    public StringDrawable(String s,int size){
        this.data=s;
        this.size=size;
    }

    public StringDrawable(String s){
        this(s,Default_Font_Size);
    }

    public void drawString(String data,Graphics g,Rect rect){
        String[] datas=data.split("\n");
        for (int i = 0; i <datas.length; i++) {
            g.drawString(datas[i],rect.left_up.x,rect.left_up.y+i*Default_Font_Size);
        }
    }

    public void drawString(String data,Graphics g,Rect rect,int fontsize){
        String[] datas=data.split("\n");
        for (int i = 0; i <datas.length; i++) {
            g.drawString(datas[i],rect.left_up.x,rect.left_up.y+i*fontsize);
        }
    }
}
