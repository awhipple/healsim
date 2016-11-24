package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameinterfaces.Healable;

public class Heal extends Spell {

  private Healable target;

  public Heal(Healable target) {
    super(1.0f);

    this.target = target;
  }

  @Override
  public void update(float delta) {
    super.update(delta);

    if(castStatus() == 1 && casting) {
      target.heal(60.0f);
      casting = false;
    }
  }
}
