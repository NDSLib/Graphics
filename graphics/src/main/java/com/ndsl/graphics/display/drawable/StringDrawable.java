package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.Objects;

public class StringDrawable implements ICustomDrawable{
    public static final String Default_Font_String="Arial";
    public static final Font Default_Font=new Font(Default_Font_String, Font.PLAIN, 12);

    @Override
    public void onDraw(Graphics g, Rect rect) {
        g.setFont(Objects.requireNonNullElseGet(font, () -> new Font(Default_Font_String, Font.PLAIN, size)));
        g.drawString(data,rect.left_up.x,rect.right_down.y);
        g.setFont(Default_Font);
    }

    public String data;
    public Font font=null;
    public int size;

    public StringDrawable(String s,Font f){
        this.data=s;
        this.font=f;
    }

    public StringDrawable(String s,int size){
        this.data=s;
        this.size=size;
    }
}
