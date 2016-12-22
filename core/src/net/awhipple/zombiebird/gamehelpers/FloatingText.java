package net.awhipple.zombiebird.gamehelpers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import net.awhipple.zombiebird.ZBGame;

public class FloatingText {
  BitmapFont font;
  String text;
  float x, y, a, t;

  public FloatingText(BitmapFont font, String text, float x, float y) {
    this.font = font;
    this.text = text;
    this.x = x;
    this.y = y;
    this.a = 0;
    this.t = 0.8f;
  }

  public void update(float delta) {
    if(t > 0) {
      a += delta*2;
    } else {
      a -= delta*2;
    }
    if(a > 1f) a = 1f;
    if(a < 0) a = 0;
    if(a == 1f) t -= delta;
    if(t < 0) t = 0;
    this.y -= delta * 20;
  }

  public void draw(Batch batch) {
    float proxy_a;
    proxy_a = a > 1 ? 2f - a : a;
    if(proxy_a < 0) proxy_a = 0;
    font.setColor(0, 1, 0, proxy_a);
    font.draw(batch, text, x, ZBGame.SCREEN_H - y);
  }

  public boolean animationComplete() {
    return a == 0 && t == 0;
  }
}
