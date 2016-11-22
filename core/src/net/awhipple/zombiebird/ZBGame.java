package net.awhipple.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import net.awhipple.zombiebird.screens.GameScreen;

public class ZBGame extends Game {
  public static int SCREEN_W = 1920, SCREEN_H = 1080;

  @Override
  public void create() {
    Gdx.app.log("ZBGame", "created");
    setScreen(new GameScreen());
  }
}