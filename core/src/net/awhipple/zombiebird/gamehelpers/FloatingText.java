package net.awhipple.zombiebird.gamehelpers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.animations.Animation;
import net.awhipple.zombiebird.animations.FadeFloat;

public class FloatingText {
  private BitmapFont font;
  private String text;
  private float x, y;
  private Animation animation;

  public FloatingText(BitmapFont font, String text, float x, float y) {
    this.font = font;
    this.text = text;
    this.x = x;
    this.y = y;
    this.animation = new FadeFloat(40f, 0.5f, 1f, 0.5f);
  }

  public void update(float delta) {
    animation.update(delta);
  }

  public void draw(Batch batch) {
    font.setColor(0, 1, 0, animation.getA());
    font.draw(batch, text, x + animation.getX(), ZBGame.SCREEN_H - (y + animation.getY()));
  }

  public boolean animationComplete() {
    return animation.animationComplete();
  }
}
