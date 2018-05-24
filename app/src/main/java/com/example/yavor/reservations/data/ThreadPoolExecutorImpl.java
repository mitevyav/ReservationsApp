package com.example.yavor.reservations.data;

import android.support.annotation.NonNull;

import java.util.concurrent.*;

public class ThreadPoolExecutorImpl implements Executor {
    private static final int CORE_POOL_SIZE = 3;

    private static final int MAX_POOL_SIZE = 5;

    private static final int KEEP_ALIVE_TIME = 120;

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolExecutorImpl() {
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                WORK_QUEUE);
    }


    @Override
    public void execute(@NonNull Runnable command) {
        threadPoolExecutor.execute(command);
    }
}
