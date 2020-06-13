package com.ndsl.graphics.display;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Display extends JFrame {

    public FPSLimiter limiter =new FPSLimiter(1080);

    public BufferStrategy bufferStrategy;

    public Display(String title, int bufferSize, Rect displayBound){
        this.setTitle(title);
        this.setBounds(displayBound.left_up.x,displayBound.left_up.y,displayBound.getWidth(),displayBound.getHeight());
        this.setVisible(true);
        this.createBufferStrategy(bufferSize);
        this.bufferStrategy = this.getBufferStrategy();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public List<Drawable> drawableList=new ArrayList<>();

    public boolean isShowing(Drawable drawable){
        return drawable.getShowingRect().contain(getDisplayShowingRect());
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
        update(getGraphic());
    }

    @Override
    public void update(Graphics g) {
//        System.out.println("onUpdate");
        boolean need_draw=false;
        for(Drawable d:drawableList){
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
//        if(bufferStrategy.contentsLost()) return;
        Toolkit.getDefaultToolkit().sync();
        if(!bufferStrategy.contentsLost()) bufferStrategy.show();
//        bufferStrategy.dispose();
    }

    public Display addDrawable(Drawable e){
        if(isExist(e.getID())){
            this.drawableList.remove(e);
//            System.out.println("Removed");
        }
        this.drawableList.add(e);
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

    public boolean isExist(String drawable_id){
        for(Drawable drawable:drawableList){
            if(drawable.getID()==null) continue;
            if(drawable.getID().equals(drawable_id)){
                return true;
            }
        }
        return false;
    }
}
