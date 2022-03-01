package org.academiadecodigo.bootcamp.concurrency;

import org.academiadecodigo.bootcamp.concurrency.bqueue.BQueue;
import org.academiadecodigo.bootcamp.concurrency.bqueue.Pizza;

/**
 * Produces and stores integers into a blocking queue
 */
public class Producer implements Runnable {

    private final BQueue<Pizza> queue;
    private String name;
    private int elementNum;

    /**
     * @param queue the blocking queue to add elements to
     * @param elementNum the number of elements to produce
     */
    public Producer(BQueue queue, int elementNum,String name) {
        this.queue = queue;
        this.elementNum = elementNum;
        this.name=name;
    }
    @Override
    public void run() {

            for (int i = 0; i < elementNum; i++) {
                try {

                    synchronized (queue) {
                        System.out.println(getClass().getSimpleName()+" "+name);
                        queue.offer(new Pizza());
                        Thread.sleep(500);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
}
