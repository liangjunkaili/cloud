package com.liangjun.servicelucy.util;

import java.util.concurrent.*;

public class ScheduledThreadPoolExecutorUtil {
    public static final ScheduledExecutorService schedule = Executors
            .newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    public static void main(String[] args) {
        schedule(10000,10000,55000,new OneTask());
    }

    /**
     *  单位都是毫秒
     * @param initialDelay 延长多久开始执行任务
     * @param period 每隔多久执行一次
     * @param delay 延长多久开始取消任务
     * @param runnable 任务
     */
    public static void schedule(long initialDelay,long period,long delay,Runnable runnable) {
        ScheduledFuture<?> future = schedule.scheduleAtFixedRate(runnable,
                initialDelay, period, TimeUnit.MILLISECONDS);
        schedule.schedule(() ->{
            future.cancel(true);
        }, delay, TimeUnit.MILLISECONDS);
    }
}
class OneTask implements Runnable{

    @Override
    public void run() {
        System.out.println("我被执行了！");
    }
}