package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class StringGui implements ICustomGui{
    public static final String Default_Font_String="Arial";
    public static final int Default_Font_Size=12;
    public static final Font Default_Font=new Font(Default_Font_String, Font.PLAIN, Default_Font_Size);


    public String s;
    public Font font;
    public int size=-1;

    public StringGui(String s,Font f,int size){
        this.s=s;
        this.font=f;
        this.size=size;
    }

    public StringGui(String s){
        this(s,Default_Font,Default_Font_Size);
    }

    public StringGui(String s,Font f){
        this(s,f,Default_Font_Size);
    }

    public StringGui(String s,int size){
        this(s,Default_Font,size);
    }

    public void drawString(String data, Graphics g, Rect rect){
        drawString(data, g, rect,Default_Font_Size);
    }

    public void drawString(@NotNull String data, Graphics g, Rect rect, int fontsize){
        String[] datas=data.split("\n");
        for (int i = 0; i <datas.length; i++) {
            g.drawString(datas[i],rect.left_up.x,rect.left_up.y+i*fontsize);
        }
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.setFont(Objects.requireNonNullElseGet(font, () -> new Font(Default_Font_String, Font.PLAIN, size)));
        if(size!=-1){
            drawString(s,g,showingRect,size);
        }else{
            drawString(s,g,showingRect);
        }
        g.setFont(Default_Font);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringGui){
            return ((StringGui) obj).s.equals(s) && ((StringGui) obj).size==size;
        }return false;
    }
}
