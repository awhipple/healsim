package net.awhipple.zombiebird.gamehelpers;

import com.badlogic.gdx.graphics.*;
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
      batch.draw(sprite, x, ZBGame.SCREEN_H - y - h, w, h);
      batch.end();
    }
  }

  public static void drawSprite(Sprite sprite, Batch batch, float x, float y, float w, float h, float a) {
    if(sprite != null) {
      batch.begin();
      batch.setColor(1.0f, 1.0f, 1.0f, a);
      batch.draw(sprite, x, ZBGame.SCREEN_H - y - h, w, h);
      batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
      batch.end();
    }
  }

}
