package kz.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        synchronized (this) {
            executorService.submit(
                    () -> {
                        String subject = String.format("Notification {username} to email {email}", user.getUsername(), user.getEmail());
                        String body = String.format("Add a new event to {username}", user.getUsername());
                        send(subject, body, user.getEmail());
                    }
            );
        }
    }

    public void close() {
        synchronized (this) {
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
