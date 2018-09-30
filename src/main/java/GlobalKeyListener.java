import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Oreki on 26/09/2018.
 * Based on the example from https://github.com/kwhat/jnativehook/wiki/Keyboard
 */
public class GlobalKeyListener implements NativeKeyListener {
    private Bot bot;

    public GlobalKeyListener(Bot robot) {
        bot = robot;
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        //System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        //Exit program ESC key
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            stop();
        }
        //Start bot UP key
        if (e.getKeyCode() == NativeKeyEvent.VC_UP) {
            bot.start();
        }
        //Stop bot DOWN key
        if (e.getKeyCode() == NativeKeyEvent.VC_DOWN) {
            bot.stop();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void start() {
        // Disable log
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(this);
    }

    public void stop() {
            GlobalScreen.removeNativeKeyListener(this);
            System.exit(0);
    }
}
