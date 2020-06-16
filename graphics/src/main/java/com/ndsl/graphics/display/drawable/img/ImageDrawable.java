package com.ndsl.graphics.display.drawable.img;

import com.ndsl.graphics.display.drawable.ICustomDrawable;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class ImageDrawable implements ICustomDrawable {
    public Image image;
    public ImageDrawable(Image image){
        this.image=image;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        g.drawImage(image,showingRect.left_up.x,showingRect.left_up.y,null);
    }
}
