package com.ndsl.graphics.pos;

public class Rect {
    public Pos left_up;
    public Pos right_down;

    public Rect(Pos left_up,Pos right_down){
        this.left_up=left_up;
        this.right_down=right_down;
    }

    public int getHeight(){
        return right_down.y - left_up.y;
    }

    public int getWidth(){
        return right_down.x - left_up.x;
    }

    public boolean contain(Rect displayShowingRect) {
        for(Pos pos:getAllPoint()){
            if (pos.contain(displayShowingRect)) return true;
        }
        for(Pos pos:displayShowingRect.getAllPoint()){
            if(pos.contain(this)) return true;
        }
        return false;
    }

    public Pos[] getAllPoint(){
        Pos[] pos=new Pos[4];
        pos[0]=left_up;
        pos[1]=new Pos(left_up.x,right_down.y);
        pos[2]=new Pos(right_down.x,left_up.y);
        pos[3]=right_down;
        return pos;
    }
}
