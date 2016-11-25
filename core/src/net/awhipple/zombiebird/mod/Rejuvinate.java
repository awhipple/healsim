package net.awhipple.zombiebird.mod;

import net.awhipple.zombiebird.gameinterfaces.Healable;

public class Rejuvinate extends Modification {

  private float HEAL_PER_TICK = 10.0f;

  private Healable target;
  private float nextHeal;

  public Rejuvinate(Healable target) {
    super(15.0f);
    nextHeal = 12.0f;

    this.target = target;
    target.heal(HEAL_PER_TICK);
  }

  @Override
  public void update(float delta) {
    super.update(delta);

    if(timer <= nextHeal) {
      target.heal(10);
      nextHeal -= 3.0f;
    }
  }
}
