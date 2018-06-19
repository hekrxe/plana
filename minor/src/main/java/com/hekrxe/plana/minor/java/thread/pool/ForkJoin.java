package com.hekrxe.plana.minor.java.thread.pool;

import java.util.concurrent.*;

/**
 * User: tanhuayou
 * Date: 2018/6/19
 */
public class ForkJoin {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        taskTest();
    }


    private static void taskTest() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> future = pool.submit(new SumTask(1, 1001));
        System.out.println(future.get());
        pool.shutdown();
    }

    private static class SumTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 50;
        private int start;
        private int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * The main computation performed by this task.
         *
         * @return the result of the computation
         */
        @Override
        protected Integer compute() {
            if (end - start < THRESHOLD) {
                int sum = 0;
                for (int i = start; i < end; ++i) {
                    sum += i;
                }
                return sum;
            } else {
                int middle = (start + end) / 2;
                SumTask left = new SumTask(start, middle);
                SumTask right = new SumTask(middle, end);
                left.fork();
                right.fork();

                return left.join() + right.join();
            }
        }
    }

    private static void actionTest() throws InterruptedException {
        PrintAction action = new PrintAction(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(action);

        forkJoinPool.awaitTermination(3, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }

    private static class PrintAction extends RecursiveAction {
        private static final int THRESHOLD = 50;
        private int start;
        private int end;

        public PrintAction(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        /**
         * The main computation performed by this task.
         */
        @Override
        protected void compute() {
            if (end - start < THRESHOLD) {
                for (int i = start; i < end; ++i) {
                    System.out.println(Thread.currentThread().getName() + " => " + i);
                }
            } else {
                int mid = (start + end) / 2;
                PrintAction left = new PrintAction(start, mid);
                PrintAction right = new PrintAction(mid, end);
                left.fork();
                right.fork();
            }
        }
    }
}
