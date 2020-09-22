package com.ndsl.graphics.display.drawable.img;

import java.awt.*;

public class ImageTool {
    public static ImageTool INSTANCE = new ImageTool();

    public static ImageTool getDefaultToolkit() {
        return INSTANCE;
    }

    public GImage convert(Image image) {
        return new GImage(image);
    }
}
