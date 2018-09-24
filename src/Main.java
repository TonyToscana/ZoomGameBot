import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Oreki on 24/09/2018.
 */
public class Main {
    private static final int WHITE_VALUE = -8978432;
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            int x;
            int y;
            Color color;
            Point location;
            Thread.sleep(1000);

            for (int i = 0; i < 10000; i++) {
                location = MouseInfo.getPointerInfo().getLocation();
                x = location.x;
                y = location.y;
                color = robot.getPixelColor(x, y);
                //System.out.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
                if(WHITE_VALUE <= color.getRGB()) {
                    searchPixel(robot, location);
                }
                //System.out.println("iterated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("finished");
    }

    private static void searchPixel(Robot robot, Point location) {
        Queue<Point> cola = new LinkedList<>();
        cola.add(location);
        List<Point> visited = new ArrayList<>();
        visited.add(location);

        while(!cola.isEmpty()) {
            //System.out.println("stuck");
            Point current = cola.poll();

            if(robot.getPixelColor(current.x, current.y).getRGB() < WHITE_VALUE) {
                robot.mouseMove(current.x,current.y);
                break;
            }
            for (Point next:
                 neighbors(current)) {

                if(!visited.contains(next)) {
                    cola.add(next);
                    visited.add(next);
                }
            }
        }
    }

    private static List<Point> neighbors(Point current) {
        List<Point> neighbors = new ArrayList<>();

        neighbors.add(new Point(current.x+1, current.y));
        neighbors.add(new Point(current.x-1, current.y));
        neighbors.add(new Point(current.x, current.y+1));
        neighbors.add(new Point(current.x, current.y-1));

        return neighbors;
    }
}
