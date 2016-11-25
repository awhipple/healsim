package net.awhipple.zombiebird.mod;

public class Modification {
  private float timer;

  public Modification(float timer) {
    this.timer = timer;
  }

  public void update(float delta) {
    timer -= delta;
  }

  public float getTimer() { return timer; }
}
