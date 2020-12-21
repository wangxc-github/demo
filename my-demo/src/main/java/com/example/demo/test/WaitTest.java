package com.example.demo.test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WaitTest {

    private static int cont = 0;

    private static final int buffCont = 10;

    private static String lock = "lock";

    class Producer implements Runnable{

        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock){
                    while (cont == buffCont){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    cont++;
                    System.out.println(Thread.currentThread().getName()+"-生产者生产，数量是："+cont);
                    lock.notifyAll();
                }
            }
        }
    }

    class Customer implements Runnable{
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock){
                    while (cont == 0){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    cont--;
                    System.out.println(Thread.currentThread().getName()+"-消费者消费，还剩："+cont);
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
//        WaitTest waitTest = new WaitTest();
//        new Thread(waitTest.new Producer()).start();
//        new Thread(waitTest.new Customer()).start();
//        new Thread(waitTest.new Producer()).start();
//        new Thread(waitTest.new Customer()).start();
//        new Thread(waitTest.new Producer()).start();
//        new Thread(waitTest.new Customer()).start();


        List<Map<String,String>> lcconts = null;

        List<String> prtNos = lcconts.stream().map(contInfoMap -> contInfoMap.get("prtno")).collect(Collectors.toList());

        System.out.println(prtNos);
    }

}
