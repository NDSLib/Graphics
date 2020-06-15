package com.ndsl.graphics;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.PointDrawable;
import com.ndsl.graphics.display.drawable.StringDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class GraphicsMain {
    public static final Color Default_Color=new Color(0, 0, 0);
    public static Display display=new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
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
            display.mouseInputHandler.setDebugDrawable();
//            display.addDrawable(new PointDrawable(display.mouseInputHandler.getNow_mouse_pos(),"point_drawable"));
            display.limiter.setDrawable(display);
            display.keyHandler.setDebugGUI();
            if (display.limiter.onUpdate()) display.update();
        }
    }
}
