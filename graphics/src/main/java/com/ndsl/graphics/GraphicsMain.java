package com.ndsl.graphics;

import com.ndsl.graphics.display.BorderLessDisplay;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.non_sync.RectDrawable;
import com.ndsl.graphics.display.drawable.non_sync.StringDrawable;
import com.ndsl.graphics.display.drawable.animate.TimeScaledAnimator;
import com.ndsl.graphics.display.drawable.img.GImage;
import com.ndsl.graphics.display.drawable.non_sync.ImageDrawable;
import com.ndsl.graphics.display.drawable.non_sync.ui.Button;
import com.ndsl.graphics.display.drawable.synced.SyncedImageDrawable;
import com.ndsl.graphics.display.drawable.synced.SyncedStringDrawable;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.display.sub.BorderLessSubWindow;
import com.ndsl.graphics.display.sub.SubWindow;
import com.ndsl.graphics.display.util.ExitAttitude;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class GraphicsMain {
    public static final Color Default_Color=new Color(0, 0, 0);
    public final File BunFaceFile=new File("graphics\\src\\main\\java\\com\\ndsl\\graphics\\display\\drawable\\img\\bun_face.jpg");
    public Image BunFace = null;

    public Display display;

    public static void main(String[] args) throws IOException {
        new GraphicsMain().GImageTest();
    }

    public void onRun(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        try {
            BunFace = ImageIO.read(BunFaceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        display.setDebugMode(true);
        display.addDrawable(new Drawable(new StringDrawable("え",new Rect(new Pos(200,200)),StringDrawable.Default_Font,"Test_Font")));
        display.addDrawable(new Drawable(new ImageDrawable(BunFace,new Pos(200,200),"bun_face")));
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

    public void exitTest(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        display.exitManager.add(new EA());
        while(true){
            if (display.limiter.onUpdate()) display.update();
        }
    }

    public class EA implements ExitAttitude {
        @Override
        public void onClose(WindowEvent e) {
            System.out.println("Exit!!!!");
        }
    }

    public void subWindowTest(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        SubWindow sub=new SubWindow("sub-NDSL/Graphics",display);
        SubWindow sub_=new SubWindow("sub_-NDSL/Graphics",display);
        while(true){
            if (display.limiter.onUpdate()) display.update();
            if (sub.limiter.onUpdate()) sub.update();
            if (sub_.limiter.onUpdate()) sub_.update();
        }
    }

    public void borderLessDisplayTest(){
        BorderLessDisplay display_ = new BorderLessDisplay("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        while(true){
            if (display_.limiter.onUpdate()) display_.update();
        }
    }

    public void subBorderLessWindowTest(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        SubWindow window=new BorderLessSubWindow(display);
        while(true){
            if(display.limiter.onUpdate()) display.update();
            if(window.limiter.onUpdate()) window.update();
        }
    }

    public void GImageTest(){
        display = new Display("NDSL/Graphics",3,new Rect(new Pos(100,100),new Pos(600,600)));
        try {
            BunFace = ImageIO.read(BunFaceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GImage image=new GImage(BunFace);
//        PASSED!
        image.zoom(0.5d);
        image.trim(new Rect(0,0,100,100));
        image.zoom(1.5d);
        SyncedImageDrawable img_d=new SyncedImageDrawable(image.export(),new Pos(0,20),"TestBun");
        display.addDrawable(new Drawable(img_d));
        while(true){
            if(display.limiter.onUpdate()) display.update();
            img_d.setImage(image.export());
        }
    }

    private double easing(long time){
        long count=time%2;  //0~2(int)
        if(count==0) return 0.5;
        return count;//1~2(int)
    }
}
