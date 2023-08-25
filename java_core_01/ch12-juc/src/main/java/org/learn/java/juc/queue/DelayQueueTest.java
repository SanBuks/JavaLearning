package org.learn.java.juc.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Delayed {
    private String taskName;
    private long delayTime;

    public DelayedTask(String taskName, long delayTime) {
        this.taskName = taskName;
        this.delayTime = System.currentTimeMillis() + delayTime;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = delayTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (this.delayTime < ((DelayedTask) other).delayTime) {
            return -1;
        } else if (this.delayTime > ((DelayedTask) other).delayTime) {
            return 1;
        }
        return 0;
    }
}

public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();

        // 添加延迟任务
        delayQueue.add(new DelayedTask("Task 1", 2000));
        delayQueue.add(new DelayedTask("Task 2", 2000));
        delayQueue.add(new DelayedTask("Task 3", 6000));

        // 执行任务调度
        while (!delayQueue.isEmpty()) {
            try {
                DelayedTask task = delayQueue.take();
                System.out.println("Executing task: " + task.getTaskName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
