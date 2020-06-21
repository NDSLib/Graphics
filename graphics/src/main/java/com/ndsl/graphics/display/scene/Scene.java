package com.ndsl.graphics.display.scene;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.Drawable;
import com.ndsl.graphics.display.drawable.GUIBase;
import com.ndsl.graphics.display.drawable.RealTimeDrawable;
import com.ndsl.graphics.display.drawable.ui.UIBase;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    public String id;
    public Scene(String id) {
        this.id=id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Scene) {
            return ((Scene)obj).id==this.id;
        }
        return false;
    }


    public List<Drawable> drawableList=new ArrayList<>();
    public List<GUIBase> guiList=new ArrayList<>();
    public List<UIBase> uiList=new ArrayList<>();
    public List<RealTimeDrawable> realTimeDrawables=new ArrayList<>();

    public Scene add(Object o){
        if(o instanceof UIBase){
            uiList.add((UIBase)o);
        }else if(o instanceof GUIBase){
            guiList.add((GUIBase)o);
        }else if(o instanceof Drawable){
            drawableList.add((Drawable)o);
        }else if (o instanceof RealTimeDrawable){
            realTimeDrawables.add((RealTimeDrawable)o);
        }
        return this;
    }

    public Scene remove(Object o) {
        if(o instanceof UIBase){
            uiList.remove((UIBase)o);
        }else if(o instanceof GUIBase){
            guiList.remove((GUIBase)o);
        }else if (o instanceof RealTimeDrawable){
            realTimeDrawables.remove((RealTimeDrawable)o);
        }else if(o instanceof Drawable){
            drawableList.remove((Drawable)o);
        }
        return this;
    }

    public boolean isExistDrawable(String id){
        for(Drawable drawable:drawableList){
            if(drawable.getID()==null) continue;
            if(drawable.getID().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean isExistGui(String id){
        for(GUIBase guiBase:guiList){
            if(guiBase.getID()==null) continue;
            if(guiBase.getID().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean isExistGUI(String id){
        for(GUIBase guiBase:guiList){
            if(guiBase.getID()==null) continue;
            if(guiBase.getID().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean isExistRealTimeDrawable(String id){
        for(RealTimeDrawable d:realTimeDrawables){
            if(d.getID()==null) continue;
            if(d.getID().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Drawable getDrawableWithID(String id){
        for(Drawable drawable:drawableList){
            if(drawable.getID()==null) continue;
            if(drawable.getID().equals(id)){
                return drawable;
            }
        }
        System.out.println("Not Found in Scene:"+id);
        return null;
    }

    @Nullable
    public UIBase getUIWithID(String id){
        for(UIBase uiBase:uiList){
            if(uiBase.getID()==null) continue;
            if(uiBase.getID().equals(id)){
                return uiBase;
            }
        }
        System.out.println("Not Found in Scene:"+id);
        return null;
    }

    @Nullable
    public GUIBase getGUIWithID(String id){
        for(GUIBase guiBase:guiList){
            if(guiBase.getID()==null) continue;
            if(guiBase.getID().equals(id)){
                return guiBase;
            }
        }
        System.out.println("Not Found in Scene:"+id);
        return null;
    }

    @Nullable
    public RealTimeDrawable getRealTimeDrawableWithID(String id){
        for(RealTimeDrawable d:realTimeDrawables){
            if(d.getID()==null) continue;
            if(d.getID().equals(id)){
                return d;
            }
        }
        System.out.println("Not Found in Scene:"+id);
        return null;
    }


    /**
     * for Display
     */
    private void clearDisplay(Display display) {
        display.uiList.clear();
        display.guiList.clear();
        display.drawableList.clear();
        display.realTimeDrawables.clear();
    }

    private void addAllToDisplay(Display display){
        for(Drawable draw:drawableList){
            display.addDrawable(draw);
        }
        for(UIBase ui:uiList){
            display.addUI(ui);
        }
        for(GUIBase guiBase:guiList){
            display.addGui(guiBase);
        }
        for(RealTimeDrawable d:realTimeDrawables){
            display.addRealTimeDrawable(d);
        }
    }

    public void copyToDisplay(Display display){
        clearDisplay(display);
        addAllToDisplay(display);
    }
}
