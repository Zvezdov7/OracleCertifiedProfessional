package ru.zvezdov.ocprof.chapter_7.Concurrency.ManagingConcurrentProcesses.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dmitry Zvezdov
 *         19.04.17.
 */
public class LionPenManager {
    private void removeAnimals(){
        System.out.println("Removing animals");
    }

    private void cleanPen(){
        System.out.println("Cleaning the pen");
    }

    private void addAnimals(){
        System.out.println("Adding animals");
    }

//    public void performTask(){
    public void performTask(CyclicBarrier c1, CyclicBarrier c2){
        try {
            removeAnimals();
            System.out.println("Number of waiting threads " + c1.getNumberWaiting());
            System.out.println("Number of threads to get the barier " + c1.getParties());
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));
            LionPenManager manager = new LionPenManager();
            for (int i = 0; i < 4; i++) {
                Thread.sleep(100);
                service.submit(() -> manager.performTask(c1, c2));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
