
package CronScheluling;package CronScheluling;
import net.sf.cron4j.Scheduler;
import net.sf.cron4j.Task;
import net.sf.cron4j.TaskExecutionContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CronSchedulerExample {

    public static void main(String[] args) {
        // Create a ScheduledExecutorService with a single thread
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Schedule a task to run every minute
        scheduler.scheduleAtFixedRate(new MyTask(), 0, 1, TimeUnit.MINUTES);

        // Optionally, you can use cron expressions with the help of libraries like cron4j or quartz
        // Here's an example using cron4j library
        CronTask task = new CronTask();
        Scheduler cronScheduler = new Scheduler();
        cronScheduler.schedule("* * * * *", task);
        cronScheduler.start();

        // Add a shutdown hook to gracefully shutdown the scheduler when the program exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scheduler.shutdown();
            cronScheduler.stop();
            System.out.println("Scheduler shutdown");
        }));
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            // Your task logic goes here
            System.out.println("Fixed-rate Task executed at: " + System.currentTimeMillis());
        }
    }

    static class CronTask extends Task {
        @Override
        public void execute(TaskExecutionContext context) throws RuntimeException {
            // Your cron task logic goes here
            System.out.println("Cron Task executed at: " + System.currentTimeMillis());
        }
    }
}
