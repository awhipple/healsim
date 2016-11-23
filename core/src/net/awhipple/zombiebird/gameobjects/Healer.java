package net.awhipple.zombiebird.gameobjects;

import net.awhipple.zombiebird.gameinterfaces.Healable;

public class Healer {
  private final static float HEAL_AMOUNT = 40;

  Healable target, healTarget;
  float cast = 0;

  public void target(Healable target) {
    this.target = target;
  }

  public void update() {
    if(cast != 0) cast += 1;
    if(cast >= 100) {
      healTarget.heal(HEAL_AMOUNT);
      cast = 0;
    }
  }

  public void startCast() {
    if(cast == 0 && !target.isDead()) {
      healTarget = target;
      cast = 1;
    }
  }

  public float getCastPercentage() { return cast / 100; }
  public Healable getTarget() { return target; }
}
