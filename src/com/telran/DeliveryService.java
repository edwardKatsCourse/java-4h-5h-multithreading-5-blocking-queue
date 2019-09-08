package com.telran;

import java.util.LinkedList;
import java.util.Queue;

//Blocking Queue
public class DeliveryService<T> {

    //bread == null - грузовик пуст, фабрика может его грузить товаром
    //bread != null - грузовик полон, супермаркет может забирать товар
    private T bread = null;

    //private Queue<T> queue = new LinkedList<>();

    //Queue - FIFO (first in, first out)
    //put | pop

    //blocked-set
    public synchronized /*this*/ void getBreadFromFactory(T breadId) throws InterruptedException {
        while (this.bread != null) {
            this.wait(); //wait-set
        }

        this.bread = breadId;
        this.notifyAll();
    }
    //bf 1
    //bf 2


    public synchronized /*this*/ T deliverBreadToSupermarket() throws InterruptedException {
        while (bread == null) {
            this.wait(); //wait-set
        }

        T breadId = this.bread;
        this.bread = null;
        this.notifyAll();

        return breadId;
    }
    //supermarket 1
    //supermarket 2

}
