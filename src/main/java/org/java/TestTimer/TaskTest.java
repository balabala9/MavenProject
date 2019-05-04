package org.java.TestTimer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TaskTest {
    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        MyTask myTask = new MyTask(timer, new MyTaskCallback() {
            @Override
            public void callBack() {
                System.out.println("TaskTest up");
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2018-12-32 18:43:00");


        myTask.cancel();
        timer.schedule(myTask, date);
        System.out.println(" timer.cancel()");

        timer.cancel();


    }
}
