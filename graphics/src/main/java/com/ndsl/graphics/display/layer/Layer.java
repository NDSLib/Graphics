package com.ndsl.graphics.display.layer;

import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.scene.Scene;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    public List<Drawable> drawableList = new ArrayList<>();
    public String id;

    public Layer(String id) {
        this.id = id;
    }

    public Layer add(Drawable drawable) {
        drawableList.add(drawable);
        return this;
    }

    @Nullable
    public Drawable get(String id) {
        for (Drawable d : drawableList) {
            if (d.getID().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public List<IDrawable> getAll() {
        List<IDrawable> list = new ArrayList<>();
        for (Drawable d : drawableList) {
            list.add(d.drawObject);
        }
        return list;
    }

    public Scene extractScene(String id, Integer int_id) {
        return new Scene(id).add(this, int_id);
    }

    public boolean isExist(Drawable d) {
        return drawableList.contains(d);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Layer) {
            return ((Layer) obj).id.equals(id);
        }
        return false;
    }
}
