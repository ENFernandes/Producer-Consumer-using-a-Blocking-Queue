package org.academiadecodigo.bootcamp.concurrency.bqueue;

import com.apple.laf.AquaIcon;

import java.util.*;

/**
 * Blocking Queue
 * @param <T> the type of elements stored by this queue
 */
public class BQueue<T> {
    private int limit;

    private Queue<T> queue = new LinkedList<>();
    /**
     * Constructs a new queue with a maximum size
     * @param limit the queue size
     */
    public BQueue(int limit) {
        this.limit=limit;
    }

    /**
     * Inserts the specified element into the queue
     * Blocking operation if the queue is full
     * @param data the data to add to the queue
     */
    public synchronized void offer(T data) {
        while (queue.size()>=getLimit()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(data);
        notifyAll();
    }

    /**
     * Retrieves and removes data from the head of the queue
     * Blocking operation if the queue is empty
     * @return the data from the head of the queue
     */
    public synchronized T poll() {
        while (getSize()==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        return queue.poll();
    }

    /**
     * Gets the number of elements in the queue
     * @return the number of elements
     */
    public int getSize() {
        return queue.size();
    }

    /**
     * Gets the maximum number of elements that can be present in the queue
     * @return the maximum number of elements
     */
    public int getLimit() {
        return limit;
    }

}
