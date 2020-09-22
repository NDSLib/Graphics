package com.ndsl.graphics.display.drawable.animate;

import com.ndsl.graphics.display.drawable.img.GImage;
import com.ndsl.graphics.pos.Pos;

public class TimeScaledAnimator extends Animator {
    public long perFrameTime;
    public long latest_time = System.currentTimeMillis();

    public TimeScaledAnimator(String id, long perFrameTime, GImage... images) {
        super(id, images);
        this.perFrameTime = perFrameTime;
    }

    public TimeScaledAnimator(String id, int startIndex, long perFrameTime, GImage images) {
        super(id, startIndex, images);
        this.perFrameTime = perFrameTime;
    }

    public TimeScaledAnimator(String id, long perFrameTime, Pos left_up, GImage... images) {
        super(id, left_up, images);
        this.perFrameTime = perFrameTime;
    }

    public TimeScaledAnimator(String id, long perFrameTime, int startIndex, Pos left_up, GImage... images) {
        super(id, startIndex, left_up, images);
        this.perFrameTime = perFrameTime;
    }

    @Override
    public void next() {
        if (System.currentTimeMillis() - latest_time > perFrameTime) {
            super.next();
            latest_time = System.currentTimeMillis();
        }
    }
}
