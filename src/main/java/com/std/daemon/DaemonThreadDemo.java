package com.std.daemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 场景：主线程阻塞等待键盘输入，等到键盘输入后停止阻塞；
 *      守护线程写文件操作；
 *      主线程等到键盘输入后开始执行，直至结束，结束后正在写文件的守护线程也会停止运行。
 */


class DaemonThread implements Runnable{

    @Override
    public void run() {
        System.out.println("进入守护线程"+Thread.currentThread().getName());
        try {
            writeoFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("退出守护线程"+Thread.currentThread().getName());
    }

    private void writeoFile() throws IOException, InterruptedException {

        File fileName = new File("daemon.txt");
        FileOutputStream fos = new FileOutputStream(fileName,true);
        int count = 0;
        while (count< 999){
            fos.write(("\r\nword " + count).getBytes());
            System.out.println("守护线程"+Thread.currentThread().getName() + "像文件中写入word"+ count++);
            Thread.sleep(1000);
        }


    }
}

public class DaemonThreadDemo {
    public static void main(String[] args){
        System.out.println("进入主线程"+Thread.currentThread().getName());
        DaemonThread daemonThread = new DaemonThread();
        Thread t = new Thread(daemonThread);
        // 启动之前设置为守护线程
        t.setDaemon(true);
        t.start();

        // 阻塞等待键盘输入
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("退出主线程"+Thread.currentThread().getName());
    }
}
