package com.std.tickets;

class MyThread extends Thread{
    private int ticketsCount = 5;
    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (ticketsCount > 0){
            ticketsCount--;
            System.out.println(name + "卖了1张票，剩余票数为："+ticketsCount);
        }
    }
}

public class TicketsThread {

    public static void main(String[] args){
        Thread t1 = new MyThread("w1");
        Thread t2 = new MyThread("w2");
        Thread t3 = new MyThread("w3");
        t1.start();
        t2.start();
        t3.start();
    }
}
