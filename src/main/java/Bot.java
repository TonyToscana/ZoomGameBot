import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Oreki on 25/09/2018.
 */
public class Bot {
    private Robot robot;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean stop = new AtomicBoolean(false);
    private static final Point middlePoint = new Point(447,520);
    // Compensate difference in position between the mouse and the cross
    private static int OFFSET = 2;
    // Compensate difference between BufferedImage coordinates and global coordinates
    static final int xFieldCompensation = 9;
    static final int yFieldCompensation = 53;
    private static final int xCompensation = OFFSET + xFieldCompensation;
    private static final int yCompensation = OFFSET + yFieldCompensation;


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
            Thread runningThread = new Thread(this::startSearch);
            runningThread.start();
        }
    }

    private void startSearch() {
        try {
            int x;
            int y;
            int color;
            Point location;

            Thread.sleep(100);

            while (!stop.get()) {
                location = MouseInfo.getPointerInfo().getLocation();
                x = location.x - xCompensation;
                y = location.y - yCompensation;
                location.setLocation(x,y);

                if(outOfBounds(location)) {
                    location.setLocation(middlePoint);
                }

                BufferedImage img = robot.createScreenCapture(PixelSearch.area);
                color = img.getRGB(x, y);

                if (PixelSearch.CURSOR_COLOR <= color) {
                    Point p = PixelSearch.searchPixel(img, location);
                    if (p != null) {
                        robot.mouseMove(p.x + xCompensation, p.y + yCompensation);
                    } else {
                        System.err.println("p is null");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            running.set(false);
            stop.set(false);
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

    private boolean outOfBounds(Point p) {
        int xLimit = 150 - xFieldCompensation;
        int yLimit = 150 - yFieldCompensation;
        return p.x > (middlePoint.x + xLimit) || p.x < (middlePoint.x - xLimit) ||
                p.y > (middlePoint.y + yLimit) || p.y < (middlePoint.y - yLimit);

    }
}
