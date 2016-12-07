package net.awhipple.zombiebird.gamehelpers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.ZBGame;

public class GameUtility {

  public static void drawSprite(Sprite sprite, Batch batch, float x, float y) {
    drawSprite(sprite, batch, x, y, sprite.getWidth(), sprite.getHeight());
  }

  public static void drawSprite(Sprite sprite, Batch batch, float x, float y, float w, float h) {
    if(sprite != null) {
      batch.begin();
      sprite.setPosition(x, ZBGame.SCREEN_H - y - sprite.getHeight());
      sprite.setSize(w, h);
      sprite.draw(batch);
      batch.end();
    }
  }

}
