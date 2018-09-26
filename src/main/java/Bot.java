import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Oreki on 25/09/2018.
 */
public class Bot {
    private Robot robot;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean stop = new AtomicBoolean(false);
    Thread runningThread;

    public Bot(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        if(!running.get()) {
            running.set(true);
            runningThread = new Thread(() -> {
                try {
                    int x;
                    int y;
                    Color color;
                    Point location;

                    Thread.sleep(1000);
                    System.out.println("I've been on the thread");

                    while (!stop.get()) {
                        location = MouseInfo.getPointerInfo().getLocation();
                        x = location.x;
                        y = location.y;
                        color = robot.getPixelColor(x, y);

                        if (PixelSearch.WHITE <= color.getRGB()) {
                            Point p = PixelSearch.searchPixel(robot, location);
                            if (p != null) {
                                robot.mouseMove(p.x, p.y);
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    running.set(false);
                    stop.set(false);
                }
            });
            runningThread.start();
        }
    }

    public void stop() {
        if (running.get()) {
            stop.compareAndSet(false, true);
            System.out.println("stopped");
        }
    }

    public boolean isRunning() {
        return running.get();
    }
}
