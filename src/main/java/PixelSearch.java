import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by Oreki on 25/09/2018.
 */
class PixelSearch {
    static final int CURSOR_COLOR = -8978432;
    private static final int fieldBounds = 862;
    static final Rectangle area = new Rectangle(Bot.xFieldCompensation, Bot.yFieldCompensation, fieldBounds, fieldBounds);

    /*
     * BFS based search around location for black pixels
     */
    static Point searchPixel(BufferedImage img, Point location) {
        Queue<Point> cola = new LinkedList<>();
        Map<Point, Boolean> visited = new HashMap<>();
        cola.add(location);
        visited.put(location, true);

        while(!cola.isEmpty()) {
            Point current = cola.poll();

            if(img.getRGB(current.x, current.y) < CURSOR_COLOR) {
                return new Point(current.x,current.y);
            }

            for (Point next:
                    neighbors(current)) {
                if(!visited.containsKey(next)) {
                    cola.add(next);
                    visited.put(next, true);
                }
            }
        }

        return null;
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
