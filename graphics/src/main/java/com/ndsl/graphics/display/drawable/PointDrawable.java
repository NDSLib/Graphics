package com.ndsl.graphics.display.drawable;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class PointDrawable extends Drawable{
    public Color drawColor= GraphicsMain.Default_Color;

    public PointDrawable(Pos pos, Color color){
        super((Object)pos,pos);
        this.drawColor=color;
    }

    public PointDrawable(Pos pos, Color color,String id){
        super((Object)pos,pos,id);
        this.drawColor=color;
    }

    public PointDrawable(Pos pos){
        super((Object)pos,pos);
    }

    public PointDrawable(Pos pos,String id){
        super((Object)pos,pos,id);
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(drawColor);
        g.drawLine(this.left_up.x,this.left_up.y,this.left_up.x,this.left_up.y);
    }
}
