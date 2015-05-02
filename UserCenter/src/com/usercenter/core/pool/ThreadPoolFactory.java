package com.usercenter.core.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author wangq
 * @version $Id: TaskThreadPool.java, v 0.1 2014-9-25 上午11:22:06 wangq Exp $
 */
public class ThreadPoolFactory extends ThreadPoolExecutor {

    /**
     * 下行行消息推送线程
     */
    private static ThreadPoolFactory downPushPool = null;

    /**
     * 上行消息推送线程
     */
    private static ThreadPoolFactory upPushPool   = null;

    /**
     * 业务任务线程
     */
    private static ThreadPoolFactory taskPool     = null;

    public static ThreadPoolFactory getTaskPoolInstance() {
        if (taskPool == null) {
            synchronized ("taskPool") {
                if (taskPool == null) {
                    taskPool = new ThreadPoolFactory(10, 10, 1800, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(5000),
                        new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return taskPool;
    }

    public static ThreadPoolFactory getDownPoolInstance() {
        if (downPushPool == null) {
            synchronized ("downPushPool") {
                if (downPushPool == null) {
                    downPushPool = new ThreadPoolFactory(3, 3, 1800, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(5000),
                        new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return downPushPool;
    }

    public static ThreadPoolFactory getUpPoolInstance() {
        if (upPushPool == null) {
            synchronized ("upPushPool") {
                if (upPushPool == null) {
                    upPushPool = new ThreadPoolFactory(3, 3, 1800, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(5000),
                        new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return upPushPool;
    }

    private ThreadPoolFactory(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                              TimeUnit unit, BlockingQueue<Runnable> workQueue,
                              RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

}
