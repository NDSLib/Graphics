package com.ndsl.graphics;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.Drawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

public class GraphicsMain {
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



        //noinspection InfiniteLoopStatement
        while (true){
            display.limiter.setDrawable(display);
            if (display.limiter.onUpdate()) display.update();
        }
    }
}
