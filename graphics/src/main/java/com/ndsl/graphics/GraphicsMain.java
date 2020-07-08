package com.ndsl.graphics;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.non_sync.RectDrawable;
import com.ndsl.graphics.display.drawable.non_sync.StringDrawable;
import com.ndsl.graphics.display.drawable.animate.TimeScaledAnimator;
import com.ndsl.graphics.display.drawable.img.GImage;
import com.ndsl.graphics.display.drawable.img.ImageDrawable;
import com.ndsl.graphics.display.drawable.non_sync.ui.Button;
import com.ndsl.graphics.display.drawable.synced.SyncedStringDrawable;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GraphicsMain {
    public static final Color Default_Color=new Color(0, 0, 0);
    public final File BunFaceFile=new File("graphics\\src\\main\\java\\com\\ndsl\\graphics\\display\\drawable\\img\\bun_face.jpg");
    public Image BunFace = null;

    public Display display;

    public static void main(String[] args) throws IOException {
        new GraphicsMain().syncTest();
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
        display.setDebugMode(true);
        display.addDrawable(new Drawable(new StringDrawable("え",new Rect(new Pos(200,200)),StringDrawable.Default_Font,"Test_Font")));
        display.addDrawable(new Drawable(new ImageDrawable(BunFace,new Pos(200,200),"bun_face")));
        //noinspection InfiniteLoopStatement
        while (true){
            display.debugger.setDebug(display);
            display.mouseInputHandler.setDebugDrawable();
            Button.genAndAddButton(new Rect(new Pos(150,100),new Pos(200,150)),display,"button_id");
            display.addDrawable(new Drawable(new RectDrawable(new Rect(new Pos(150,100),new Pos(200,150)),Color.CYAN,"button_rect")));
            if (display.limiter.onUpdate()) display.update();
        }
    }

    public void layer_test(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        display.layerManager.set(new Layer("Second_Layer"),2);
        display.layerManager.get("default").add(new Drawable(new RectDrawable(new Rect(100,100,300,300),Color.BLUE,"first_rect")));
        display.layerManager.get("Second_Layer").add(new Drawable(new RectDrawable(new Rect(200,200,400,400),Color.RED,"second_rect")));
        while (true){
            if (display.limiter.onUpdate()) display.update();
        }
    }

    public void animatorTest() throws IOException {
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        display.layerManager.set(new Layer("Animator"),1);
        display.layerManager.get("Animator").add(new Drawable(new TimeScaledAnimator("anime",1000,new Pos(100,100), GImage.getAll("graphics\\src\\main\\java\\com\\ndsl\\graphics\\display\\drawable\\img\\bun_face.jpg","graphics\\src\\main\\java\\com\\ndsl\\graphics\\display\\drawable\\img\\bun_face_privacy.jpg"))));
        while (true){
            if (display.limiter.onUpdate()) display.update();
        }
    }

    public void syncTest(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        int count=0;
        SyncedStringDrawable s_d=new SyncedStringDrawable("Counts:"+count,new Rect(100,100,200,200),"id");
        display.layerManager.get("default").add(new Drawable(s_d));
        while (true){
            if(display.keyHandler.isKeyPressing(KeyEvent.VK_ENTER)){
                ++count;
                s_d.setText("Counts:"+count);
            }
            if (display.limiter.onUpdate()) display.update();
        }
    }
}
