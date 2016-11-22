package net.awhipple.zombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.awhipple.zombiebird.ZBGame;


public class DesktopLauncher {
  public static int SCREEN_W = 1920, SCREEN_H = 1080;

  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Zombie Bird";
    config.width = SCREEN_W;
    config.height = SCREEN_H;
    new LwjglApplication(new ZBGame(), config);
  }
}
