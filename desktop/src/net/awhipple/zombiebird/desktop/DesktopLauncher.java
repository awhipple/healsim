package net.awhipple.zombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.awhipple.zombiebird.ZBGame;


public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Zombie Bird";
    config.width = ZBGame.SCREEN_W;
    config.height = ZBGame.SCREEN_H;
    new LwjglApplication(new ZBGame(), config);
  }
}
