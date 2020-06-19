package com.ndsl.graphics.pos;

import java.awt.*;

public class Pos {
    public int x;
    public int y;
    public Pos(int x,int y){
        this.x=x;
        this.y=y;
    }

    public Pos(Point point) {
        this((int) point.getX(),(int) point.getY());
    }

    public boolean contain(Rect displayShowingRect) {
        return displayShowingRect.contain(this);
//        return displayShowingRect.left_up.x<=x && displayShowingRect.left_up.y<=y && displayShowingRect.right_down.x>=x && displayShowingRect.right_down.y >=y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pos){
            return ((Pos) obj).x==x && ((Pos)obj).y==y;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "{x:"+x+",y:"+y+"}";
    }
}
