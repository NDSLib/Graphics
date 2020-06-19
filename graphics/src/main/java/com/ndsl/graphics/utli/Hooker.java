package com.ndsl.graphics.utli;

public class Hooker {
    public IHookAble hook;
    public Hooker(IHookAble hook){
        this.hook=hook;
    }

    public Hooker hook(){
        hook.onHook();
        return this;
    }
}
