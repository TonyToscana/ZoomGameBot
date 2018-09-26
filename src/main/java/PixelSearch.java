import java.awt.*;
import java.util.*;

/**
 * Created by Oreki on 25/09/2018.
 */
class PixelSearch {
    public static final int WHITE = -8978432;

    /*
     * BFS based search around current pointer location for black pixels
     */
    static Point searchPixel(Robot robot, Point location) {
        Queue<Point> cola = new LinkedList<>();
        cola.add(location);
        java.util.List<Point> visited = new ArrayList<>();
        visited.add(location);

        while(!cola.isEmpty()) {
            Point current = cola.poll();

            if(robot.getPixelColor(current.x, current.y).getRGB() < WHITE) {
                return new Point(current.x,current.y);
            }
            for (Point next:
                    neighbors(current)) {

                if(!visited.contains(next)) {
                    cola.add(next);
                    visited.add(next);
                }
            }
        }
        return null;
    }

    private static java.util.List<Point> neighbors(Point current) {
        java.util.List<Point> neighbors = new ArrayList<>();

        neighbors.add(new Point(current.x+1, current.y));
        neighbors.add(new Point(current.x-1, current.y));
        neighbors.add(new Point(current.x, current.y+1));
        neighbors.add(new Point(current.x, current.y-1));

        return neighbors;
    }
}
