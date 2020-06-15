package com.ndsl.graphics.display;

import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.fps.FPSAttitude;
import com.ndsl.graphics.display.fps.FPSLimiter;
import com.ndsl.graphics.display.key.KeyInputHandler;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Display extends JFrame {

    public FPSLimiter limiter =new FPSLimiter(120, FPSAttitude.KEEP_UP_FPS);

    public BufferStrategy bufferStrategy;
    public KeyInputHandler keyHandler;
    public MouseInputHandler mouseInputHandler;

    public Display(String title, int bufferSize, Rect displayBound){
        this.setTitle(title);
        this.setBounds(displayBound.left_up.x,displayBound.left_up.y,displayBound.getWidth(),displayBound.getHeight());
        this.setVisible(true);
        this.createBufferStrategy(bufferSize);
        this.bufferStrategy = this.getBufferStrategy();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.keyHandler=new KeyInputHandler(this);
        this.mouseInputHandler=new MouseInputHandler(this);
//        this.setAlwaysOnTop(true);
    }

    public List<Drawable> drawableList=new ArrayList<>();
    public List<GUIBase> guiList=new ArrayList<>();

    public boolean isShowing(Drawable drawable){
        return drawable.getShowingRect().contain(getDisplayShowingRect());
    }

    public boolean isShowing(GUIBase guiBase){
        return true;
    }

    public Rect getDisplayShowingRect(){
        return new Rect(new Pos(0,0),new Pos(this.getWidth(),this.getHeight()));
    }

    @Nullable
    public Graphics getGraphic() {
        return bufferStrategy.getDrawGraphics();
    }

    public void update(){
        if(bufferStrategy.contentsLost()) bufferStrategy=this.getBufferStrategy();
        switch (attitude){
            case AlwaysUpdate:
            update(getGraphic());
            case NoFocusNoUpdate:
                if(this.hasFocus()){
                    update(getGraphic());
                }
        }
    }

    @Override
    public void update(Graphics g) {
        boolean need_draw=false;
        for(Drawable d:drawableList){
            if(isShowing(d)){
                if(!need_draw) clear();
                d.onDraw(g);
                need_draw=true;
            }
        }
        for(GUIBase d:guiList){
            if(isShowing(d)){
                if(!need_draw) clear();
                d.onDraw(g);
                need_draw=true;
            }
        }
        if(need_draw) repaint();
    }

    private void clear() {
        Graphics g = getGraphic();
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
    }

    public void repaint() {
        Toolkit.getDefaultToolkit().sync();
        if(!bufferStrategy.contentsLost()) bufferStrategy.show();
    }

    public Display addDrawable(Drawable e){
        if(isExist(e.getID())){
//            System.out.println("DrawableRemoved!");
            this.drawableList.remove(e);
        }
        this.drawableList.add(e);
        return this;
    }

    public Display addGui(GUIBase e){
        if(isExist(e.getID())){
            this.guiList.remove(e);
        }
        this.guiList.add(e);
        return this;
    }

    @Nullable
    public Drawable getDrawableWithID(String id){
        for(Drawable drawable:drawableList){
            if(drawable.getID()==null) continue;
            if(drawable.getID().equals(id)){
                return drawable;
            }
        }
        return null;
    }

    @Nullable
    public GUIBase getGuiWithID(String gui_id){
        for(GUIBase drawable:guiList){
            if(drawable.getID()==null) continue;
            if(drawable.getID().equals(gui_id)){
                return drawable;
            }
        }
        return null;
    }

    public boolean isExist(String drawable_id){
        return getDrawableWithID(drawable_id)!=null || getGuiWithID(drawable_id)!=null;
    }


    /**
     * Attitude
     */
    public DisplayAttitude attitude=DisplayAttitude.NoFocusNoUpdate;
    public void setAttitude(DisplayAttitude a){
        attitude=a;
    }


    public Display setMaxFPS(int maxFPS){
        limiter.setMaxFPS(maxFPS);
        return this;
    }

    public boolean isKeyPressing(int key_code){
        return this.keyHandler.isKeyPressing(key_code);
    }
}
