package net.awhipple.zombiebird.mod;

import net.awhipple.zombiebird.gameinterfaces.Healable;

public class Rejuvinate extends Modification {

  private Healable target;

  public Rejuvinate(Healable target) {
    super(15.0f);

    this.target = target;
  }

  @Override
  public void update(float delta) {
    super.update(delta);

    target.heal(delta*3);
  }
}
