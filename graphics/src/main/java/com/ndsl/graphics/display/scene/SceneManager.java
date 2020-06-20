package com.ndsl.graphics.display.scene;

import com.ndsl.graphics.display.Display;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    public List<Scene> scenes=new ArrayList<>();

    public SceneManager addScene(Scene scene) {
        if(isExist(scene.id)) scenes.remove(scene);
        scenes.add(scene);
        return this;
    }

    public boolean isExist(String id){
        for(Scene scene: scenes){
            if(scene.id.equals(id)){
                return true;
            }
        }
        return false;
    }

    public SceneManager remove(Scene scene){
        return remove(scene.id);
    }

    public SceneManager remove(String id){
        for(Scene scene: scenes){
            if(scene.id.equals(id)){
                scene.remove(scene);
                break;
            }
        }
        return this;
    }

    @Nullable
    public Scene getScene(String id){
        for(Scene scene: scenes){
            if(scene.id.equals(id)){
                return scene;
            }
        }
        return null;
    }

    public void setScene(String id, Display display){
        if(isExist(id)){
            setScene(getScene(id),display);
        }else{
            System.out.println("Scene:"+id+" is not found");
        }
    }

    public void setScene(Scene scene,Display display){
        scene.copyToDisplay(display);
    }
}
