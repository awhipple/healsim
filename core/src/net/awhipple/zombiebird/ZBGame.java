package net.awhipple.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gamehelpers.SpriteLoader;
import net.awhipple.zombiebird.screens.GameScreen;

public class ZBGame extends Game {
  public static int SCREEN_W = 1024, SCREEN_H = 768;

  @Override
  public void create() {
    Gdx.app.log("ZBGame", "created");

    Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
    Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
    pm.dispose();

    setScreen(new GameScreen());
  }
}