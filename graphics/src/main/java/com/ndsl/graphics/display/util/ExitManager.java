package com.ndsl.graphics.display.util;

import com.ndsl.graphics.display.sub.SubWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ExitManager {
    public List<ExitAttitude> exit=new ArrayList<>();

    public ExitManager add(ExitAttitude exit_){
        exit.add(exit_);
        return this;
    }

    public void onExit(WindowEvent e){
        for(ExitAttitude manager:exit){
            manager.onClose(e);
        }
        if (!(e.getWindow() instanceof SubWindow)){
            System.exit(0);
        }else{
            e.getWindow().dispose();
        }
    }
    public windowListener WL=new windowListener(this);
    public class windowListener extends WindowAdapter{
        public ExitManager e;
        public windowListener(ExitManager e){
            this.e=e;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            this.e.onExit(e);
        }
    }
}
