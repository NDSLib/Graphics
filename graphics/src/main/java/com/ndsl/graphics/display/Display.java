package com.ndsl.graphics.display;

import com.ndsl.graphics.Debugger;
import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.base.GUIBase;
import com.ndsl.graphics.display.fps.FPSAttitude;
import com.ndsl.graphics.display.fps.FPSLimiter;
import com.ndsl.graphics.display.key.KeyInputHandler;
import com.ndsl.graphics.display.layer.Layer;
import com.ndsl.graphics.display.layer.LayerManager;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.display.scene.SceneManager;
import com.ndsl.graphics.display.util.ExitManager;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    public FPSLimiter limiter =new FPSLimiter(120, FPSAttitude.KEEP_UP_FPS);
    public long Start_Time;
    public BufferStrategy bufferStrategy;
    public KeyInputHandler keyHandler;
    public MouseInputHandler mouseInputHandler;
    public Debugger debugger;
    public ExitManager exitManager=new ExitManager();
    public int bufferSize;

    public boolean isDebuggingMode = false;
    public Display setDebugMode(boolean isDebuggingMode){
        this.isDebuggingMode=isDebuggingMode;
        return this;
    }
    public Color DebugColor=Color.MAGENTA;
    public Display setDebugColor(Color color){
        DebugColor=color;
        return this;
    }
    public SceneManager sceneManager=new SceneManager();

    public Display(String title, int bufferSize, @NotNull Rect displayBound) {
        this.setTitle(title);
        this.setBounds(displayBound.left_up.x,displayBound.left_up.y,displayBound.getWidth(),displayBound.getHeight());
        this.setVisible(true);
        this.createBufferStrategy(bufferSize);
        this.bufferSize=bufferSize;
        this.bufferStrategy = this.getBufferStrategy();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this.exitManager.WL);
        this.keyHandler=new KeyInputHandler(this);
        this.mouseInputHandler=new MouseInputHandler(this);
        this.debugger=new Debugger(limiter,keyHandler,mouseInputHandler);
        Start_Time=System.currentTimeMillis();
    }

    /**
     * If use this,You must init yourself.
     * like above.
     */
    protected Display(){

    }

    public long getDeltaTime(){
        return System.currentTimeMillis()-Start_Time;
    }

    public boolean isShowing(Rect r){
        return getDisplayShowingRect().contain(r);
    }

    public boolean isShowing(Drawable drawable){
        return getDisplayShowingRect().contain(drawable.getShowingRect());
    }

    public boolean isShowing(GUIBase guiBase){
        return true;
    }

    public Rect getDisplayShowingRect(){
        return new Rect(0,0,this.getWidth(),this.getHeight());
    }

    @Nullable
    public Graphics getGraphic() {
        return bufferStrategy.getDrawGraphics();
    }

    public LayerManager layerManager=LayerManager.INSTANCE;

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
        for(Layer layer:layerManager.getAll()){
            for(Drawable d:layer.drawableList){
                if(isShowing(d)){
                    if(!need_draw) clear();
                    d.onDraw(g);
                    if(isDebuggingMode) drawDebugRect(d.getShowingRect(),g);
                    resetGraphics(g);
                    need_draw=true;
                }
            }
        }
        if(need_draw) repaint();
    }

    protected void drawDebugRect(Rect showingRect, Graphics g) {
        g.setColor(DebugColor);
        g.drawRect(showingRect.left_up.x,showingRect.left_up.y,showingRect.getWidth(),showingRect.getHeight());
    }

    protected void resetGraphics(Graphics graphics){
        graphics.setColor(GraphicsMain.Default_Color);
    }

    protected void clear() {
        Graphics g = getGraphic();
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
    }

    public void repaint() {
        Toolkit.getDefaultToolkit().sync();
        if(!bufferStrategy.contentsLost()) bufferStrategy.show();
    }
    public Display addDrawable(Drawable e){return addDrawable(e,"default");}
    public Display addDrawable(Drawable e,String layer_id){
        Layer layer=layerManager.get(layer_id);
        if(layer.isExist(e)){
            layer.drawableList.remove(e);
        }
        layer.drawableList.add(e);
        return this;
    }

    public Display addLayer(Layer layer){
        this.layerManager.set(layer,layerManager.getLatest());
        return this;
    }

    @Nullable
    public Drawable getDrawableWithID(String id){
        for(Layer layer:layerManager.getAll()) {
            for (Drawable drawable : layer.drawableList.toArray(new Drawable[0])) {
                if (drawable.getID() == null) continue;
                if (drawable.getID().equals(id)) {
                    return drawable;
                }
            }
        }
        return null;
    }

    public boolean isExist(String drawable_id){
        return getDrawableWithID(drawable_id)!=null;
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
