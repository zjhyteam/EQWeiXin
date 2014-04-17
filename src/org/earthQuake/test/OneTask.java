package org.earthQuake.test;

import java.util.TimerTask;

public class OneTask extends TimerTask{
  
    private int id;
    public OneTask(int id){
        this.id = id;
    }
     
    /**
     * 定时发送测试
     */
    @Override
    public void run() {
        System.out.println("线程"+ id +":  正在 执行。。"); 
        //System.gc();
    }   
}