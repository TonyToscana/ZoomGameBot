import java.awt.*;

/**
 * Created by Oreki on 25/09/2018.
 */
public class Bot {
    private static final int WHITE_VALUE = -8978432;
    private Robot robot;

    public Bot(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            int x;
            int y;
            Color color;
            Point location;
            Thread.sleep(1000);
            boolean temp_condition = true;

            while(temp_condition) {
                location = MouseInfo.getPointerInfo().getLocation();
                x = location.x;
                y = location.y;
                color = robot.getPixelColor(x, y);
                if(WHITE_VALUE <= color.getRGB()) {
                    Point p = PixelSearch.searchPixel(robot, location);
                    if(p != null) {
                        robot.mouseMove(p.x, p.y);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
