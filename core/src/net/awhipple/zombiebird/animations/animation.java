package net.awhipple.zombiebird.animations;

public abstract class Animation {

  protected float progress, duration, x, y, a;

  public Animation(float duration, float x, float y, float a) {
    this.progress = 0;
    this.duration = duration;
    this.x = x;
    this.y = y;
    this.a = a;
  }

  public abstract void update(float delta);

  public boolean animationComplete() {
    return progress >= duration;
  }

  public float getX() { return x; }
  public float getY() { return y; }
  public float getA() { return a; }

}
