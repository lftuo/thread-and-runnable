package com.std.tickets;

/**
 * Runnable内的资源其他线程可见，ticketsCount为统一资源
 */
class MyRunnableThread implements Runnable{
    private int ticketsCount = 5;

    @Override
    public void run() {
        while (ticketsCount > 0){
            ticketsCount--;
            System.out.println(Thread.currentThread().getName() + "卖了1张票，剩余票数为："+ticketsCount);
        }
    }
}

public class TicketsRunnable {

    public static void main(String[] args){

        MyRunnableThread myRunnableThread = new MyRunnableThread();
        Thread t1 = new Thread(myRunnableThread,"w1");
        Thread t2 = new Thread(myRunnableThread,"w2");
        Thread t3 = new Thread(myRunnableThread,"w3");

        t1.start();
        t2.start();
        t3.start();
    }
}
