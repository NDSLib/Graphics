package com.ndsl.graphics.display.drawable.base;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class DrawableUtil {
    public static void drawString(@NotNull String data, Graphics g, Rect rect, int fontsize){
        String[] datas=data.split("\n");
        for (int i = 0; i <datas.length; i++) {
            g.drawString(datas[i],rect.left_up.x,rect.left_up.y+i*fontsize+fontsize);
        }
    }

    public Rect getStringShowingRect(@NotNull String s,Graphics graphics, Font font,int fontsize){
        return new Rect(font.getStringBounds("hello world!", ((Graphics2D)graphics).getFontRenderContext()));
    }
}
