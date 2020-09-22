package com.ndsl.graphics.display.drawable.img;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Image Clazz
 */
public class GImage {
    public static final Pos left_zero_zero = new Pos(0, 0);

    public BufferedImage inputImage;
    public BufferedImage exportedImage;

    public static GImage get(String path) throws IOException {
        return get(new File(path));
    }

    public static GImage get(File file) throws IOException {
        return new GImage(file);
    }

    public static GImage[] getAll(String... path) throws IOException {
        List<File> files = new ArrayList<>();
        for (String path_ : path) {
            files.add(new File(path_));
        }
        return getAll(files.toArray(new File[0]));
    }

    public static GImage[] getAll(File... files) throws IOException {
        List<GImage> images = new ArrayList<>();
        for (File file : files) {
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

    public GImage(Image image) {
        this.inputImage = copyTo(image);
        this.exportedImage = inputImage;
        this.size_rect = new Rect(new Pos(0, 0), new Pos(image.getWidth(null), image.getHeight(null)));
    }

    public GImage trim(Rect trimRect) {
        this.size_rect = trimRect;
        this.ext.trim(trimRect);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Image export() {
        return exportedImage;
    }

    /**
     * 会議のほうじゃないよ
     */
    public GImage zoom(double zoomScale) {
        this.size_rect.zoom(zoomScale);
        this.ext.zoom(zoomScale);
        return this;
    }

    public Rect getSize() {
        return this.size_rect;
    }

    public Image getCachedImage() {
        if (exportedImage == null) export();
        return exportedImage;
    }

    private BufferedImage copyTo(Image image) {
        BufferedImage img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
        img.getGraphics().drawImage(image, 0, 0, null);
        return img;
    }

    public Extractor ext = new Extractor(this);

    public static class Extractor {
        public GImage img;

        public Extractor(GImage Image) {
            this.img = Image;
        }

        public void trim(Rect r) {
            if (getImageRect(img.exportedImage).contain(r)) {
                img.exportedImage = rasterToImage((WritableRaster) img.exportedImage.getData(r.castTo()));
            } else {
                throw new IllegalArgumentException("Trim size is bigger than CurrentImage.");
            }
        }

        public void zoom(double zoomScale) {
            double width = img.exportedImage.getWidth(null) * zoomScale;
            double height = img.exportedImage.getHeight(null) * zoomScale;
            int width_i = (int) Math.floor(width);
            int height_i = (int) Math.floor(height);
            BufferedImage bi = img.exportedImage;
            img.exportedImage = new BufferedImage(width_i, height_i, BufferedImage.TYPE_3BYTE_BGR);
            img.exportedImage.getGraphics().drawImage(bi, 0, 0, width_i, height_i, null);
        }

        private Rect getImageRect(Image image) {
            return new Rect(0, 0, image.getWidth(null), image.getHeight(null));
        }

        private BufferedImage rasterToImage(WritableRaster raster) {
            return new BufferedImage(img.inputImage.getColorModel(), raster, true, null);
        }
    }
}
