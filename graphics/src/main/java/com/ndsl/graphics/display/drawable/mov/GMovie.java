package com.ndsl.graphics.display.drawable.mov;

import com.ndsl.graphics.display.drawable.img.GImage;

public class GMovie {
    public GImage[] data;
    public long perFrameMillTime;
    public long AllFramesCount;

    public GMovie(GImage[] data) {
        this.data = data;
    }
}
