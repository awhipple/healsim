package net.awhipple.zombiebird.mod;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Modification {
  protected float timer;
  protected static Sprite icon;

  public Modification(float timer) {
    this.timer = timer;
  }

  public String getModName() { return this.getClass().getName(); }

  public void update(float delta) {
    timer -= delta;
  }

  public float getTimer() { return timer; }

  public Sprite getIcon() { return icon; }
}
