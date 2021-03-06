package com.evan.rabbitmq.producer.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 发送消息异步队列
 *
 * @author evan
 * @date 2022-03-09
 */
@Slf4j
public class AsyncBaseQueue implements Queue {

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_SIZE = 10000;

    private final ExecutorService SENDER_ASYNC =
            new ThreadPoolExecutor(THREAD_SIZE,
                    THREAD_SIZE,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_SIZE),
                    r -> {
                        Thread t = new Thread(r);
                        t.setName("rabbitmq_client_async_sender");
                        return t;
                    },
                    (r, executor) -> log.error("async sender is error rejected, runnable: {}, executor: {}", r, executor));

    @Override
    public void submit(Runnable runnable) {
        SENDER_ASYNC.submit(runnable);
    }
}
