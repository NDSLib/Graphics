package com.ndsl.graphics;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.StringDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.util.Arrays;

public class GraphicsMain {
    public static final Color Default_Color=new Color(0, 0, 0);
    public static Display display;

    static {
        try {
            display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        /*
          drawable remove test
          !PASSED!
         */
//        display.addDrawable(new Drawable("A",new Pos(100,100),"test_drawable"));
//        System.out.println(display.drawableList);
//        System.out.println("GetWithID:"+display.getDrawableWithID("test_drawable"));
//        display.addDrawable(new Drawable("test_drawable"));
//        System.out.println(display.drawableList);

        display.addDrawable(new Drawable(new StringDrawable("TEST_FONT",new Font(StringDrawable.Default_Font_String,Font.BOLD,12)),new Pos(200,200),"Test_Font"));

        //noinspection InfiniteLoopStatement
        while (true){
            display.debugger.setDebug(display);
            display.mouseInputHandler.setDebugDrawable();
            if (display.limiter.onUpdate()) display.update();
//            display.audioInput.getBufferInput();
//            System.out.println(Arrays.toString(display.audioInput.Current_Bytes));
        }
    }
}
