package com.ndsl.graphics.display.util;

import com.ndsl.graphics.display.sub.SubWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ExitManager {
    public List<ExitAttitude> exit=new ArrayList<>();

    public void add(ExitAttitude exit_){
        exit.add(exit_);
    }

    public void onExit(WindowEvent e){
        System.out.println("Exit");
        for(ExitAttitude manager:exit){
            manager.onClose(e);
        }
        if (!(e.getWindow() instanceof SubWindow)){
            System.out.println("Not Sub");
            System.exit(0);
        }else{
            System.out.println("Sub");
            e.getWindow().dispose();
        }
    }
    public windowListener WL= new windowListener(this);
    public static class windowListener extends WindowAdapter{
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
