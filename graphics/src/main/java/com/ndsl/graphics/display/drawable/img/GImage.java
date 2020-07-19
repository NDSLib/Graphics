package com.ndsl.graphics.display.drawable.img;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Image Clazz
 */
public class GImage {
    public static final Pos left_zero_zero=new Pos(0,0);

    public BufferedImage bufferedImage;
    public Image exportedImage;
    public boolean isChanged=false;
    public static GImage get(String path) throws IOException {
        return get(new File(path));
    }

    public static GImage get(File file) throws IOException {
        return new GImage(file);
    }

    public static GImage[] getAll(String... path) throws IOException {
        List<File> files=new ArrayList<>();
        for(String path_:path){
            files.add(new File(path_));
        }
        return getAll(files.toArray(new File[0]));
    }

    public static GImage[] getAll(File... files) throws IOException {
        List<GImage> images=new ArrayList<>();
        for(File file:files){
            images.add(new GImage(file));
        }
        return images.toArray(new GImage[0]);
    }
    /**
     * Default left_up is (0,0)
     * Default right_down is (Img.width,Img.height)
     */
    public Rect size_rect;
    public GImage(String file_Path) throws IOException {
        this(new File(file_Path));
    }

    public GImage(File file) throws IOException {
        this(ImageIO.read(file));
    }

    public GImage(Image image){
        this.bufferedImage= (BufferedImage) image;
        this.size_rect=new Rect(new Pos(0,0),new Pos(image.getWidth(null),image.getHeight(null)));
    }

    public boolean isTrimed;

    public GImage trim(Rect trimRect){
        this.size_rect=trimRect;
        isTrimed=true;
        isChanged=true;
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Image export(){
        Image img=bufferedImage;
        if (isTrimed){
            img=bufferedImage.getSubimage(size_rect.left_up.x,size_rect.left_up.y,size_rect.getWidth(),size_rect.getWidth());
        }
        BufferedImage out_image = new BufferedImage(size_rect.getWidth(),size_rect.getHeight() , 1);
        out_image.getGraphics().drawImage(img,0,0,size_rect.getWidth(),size_rect.getHeight(),null);
        this.exportedImage=out_image;
        this.isChanged=false;
        return out_image;
    }

    private boolean isZoomed(){
        return bufferedImage.getWidth(null)!=size_rect.getWidth() || bufferedImage.getHeight(null)!=size_rect.getHeight();
    }

    /**
     * 会議のほうじゃないよ
     */
    public GImage zoom(double zoomScale){
        this.size_rect.zoom(zoomScale);
        this.isChanged=true;
        return this;
    }

    public Rect getSize(){
        return this.size_rect;
    }

    public Image getCachedImage(){
        if(isChanged) export();
        if(exportedImage==null) export();
        return exportedImage;
    }
}
