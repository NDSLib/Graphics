package com.ndsl.graphics.display.drawable.img;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Custom Image Clazz
 */
public class GImage {
    public static final Pos left_zero_zero=new Pos(0,0);

    @Deprecated
    public Image image;
    public BufferedImage bufferedImage;
    /**
     * Default left_up is (0,0)
     * Default right_down is (Img.width,Img.height)
     */
    public Rect size_rect;
    public GImage(Image image){
        this.image=image;
        this.bufferedImage= (BufferedImage) image;
        this.size_rect=new Rect(new Pos(0,0),new Pos(image.getWidth(null),image.getHeight(null)));
    }

    public boolean isTrimed;

    public GImage trim(Rect trimRect){
        this.size_rect=trimRect;
        isTrimed=true;
        return this;
    }

    public Image export(){
        Image img=bufferedImage;
        if (isTrimed){
            img=bufferedImage.getSubimage(size_rect.left_up.x,size_rect.left_up.y,size_rect.getWidth(),size_rect.getWidth());
        }
        return img;
    }

    private boolean isZoomed(){
        return bufferedImage.getWidth(null)!=size_rect.getWidth() || bufferedImage.getHeight(null)!=size_rect.getHeight();
    }

    /**
     * 会議のほうじゃないよ
     */
    public GImage zoom(double zoomScale){
        this.size_rect.zoom(zoomScale);
        return this;
    }
}
