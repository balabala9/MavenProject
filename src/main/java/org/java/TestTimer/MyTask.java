package org.java.TestTimer;

import java.util.Timer;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    private Timer timer;
    private MyTaskCallback callback;


    public MyTask(Timer timer, MyTaskCallback callback) {
        this.timer = timer;
        this.callback = callback;

    }

    @Override
    public void run() {
        System.out.println("MyTask up");
        this.callback.callBack();
//        this.timer.cancel();
    }


}
