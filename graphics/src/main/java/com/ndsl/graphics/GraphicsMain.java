package com.ndsl.graphics;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.RectDrawable;
import com.ndsl.graphics.display.drawable.StringDrawable;
import com.ndsl.graphics.display.drawable.img.ImageDrawable;
import com.ndsl.graphics.display.drawable.ui.Button;
import com.ndsl.graphics.display.drawable.ui.UIBase;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphicsMain {
    public static final Color Default_Color=new Color(0, 0, 0);
    public final File BunFaceFile=new File("graphics\\src\\main\\java\\com\\ndsl\\graphics\\display\\drawable\\img\\bun_face.jpg");
    public Image BunFace = null;

    public Display display;

    public static void main(String[] args){
        new GraphicsMain().onRun();
    }

    public void onRun(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        try {
            BunFace = ImageIO.read(BunFaceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
          drawable remove test
          !PASSED!
         */

//        display.addDrawable(new Drawable("A",new Pos(100,100),"test_drawable"));
//        System.out.println(display.drawableList);
//        System.out.println("GetWithID:"+display.getDrawableWithID("test_drawable"));
//        display.addDrawable(new Drawable("test_drawable"));
//        System.out.println(display.drawableList);

        display.addDrawable(new Drawable(new StringDrawable("え",new Font(StringDrawable.Default_Font_String,Font.BOLD,12)),new Pos(200,200),"Test_Font"));
        display.addDrawable(new Drawable(new ImageDrawable(BunFace),new Pos(200,200),"bun_face"));
        //noinspection InfiniteLoopStatement
        while (true){
            display.debugger.setDebug(display);
            display.mouseInputHandler.setDebugDrawable();
            Button.genAndAddButton(new Rect(new Pos(150,100),new Pos(200,150)),display,"button_id");
            display.addDrawable(new Drawable(new RectDrawable(Color.CYAN),new Rect(new Pos(150,100),new Pos(200,150)),"button_rect"));
            if (display.limiter.onUpdate()) display.update();
        }
    }
}
