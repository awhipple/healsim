package net.awhipple.zombiebird.gameobjects;

public class Healer {
  private final static float HEAL_AMOUNT = 48;

  Healable target;
  float cast = 0;

  public void target(Healable target) {
    this.target = target;
  }

  public void update() {
    cast += 1;
    if(cast >= 100) {
      target.heal(HEAL_AMOUNT);
      cast = 0;
    }
  }

  public float getCastPercentage() { return cast / 100; }
}
