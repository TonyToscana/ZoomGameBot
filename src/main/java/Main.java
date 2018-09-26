/**
 * Created by Oreki on 24/09/2018.
 */
public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot();

        GlobalKeyListener listener = new GlobalKeyListener(bot);
        listener.start();
    }
}
