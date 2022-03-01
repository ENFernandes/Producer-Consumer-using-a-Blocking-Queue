package org.academiadecodigo.bootcamp.concurrency;

import org.academiadecodigo.bootcamp.concurrency.bqueue.BQueue;
import org.academiadecodigo.bootcamp.concurrency.bqueue.Pizza;

/**
 * Consumer of integers from a blocking queue
 */
public class Consumer implements Runnable {

    private final BQueue<Pizza> queue;
    private int elementNum;
    private String name;

    /**
     * @param queue the blocking queue to consume elements from
     * @param elementNum the number of elements to consume
     */
    public Consumer(BQueue queue, int elementNum,String name) {
        this.queue = queue;
        this.elementNum = elementNum;
        this.name = name;
    }

    @Override
    public void run() {

            for (int i = 0; i < elementNum; i++) {
                try {

                    synchronized (queue) {
                        System.out.println(getClass().getSimpleName()+" "+name + " "+ queue.poll());
                        Thread.sleep(500);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

}
