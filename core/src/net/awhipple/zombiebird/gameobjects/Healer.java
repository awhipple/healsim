package net.awhipple.zombiebird.gameobjects;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.spells.Spell;

public class Healer {
  private final static float HEAL_AMOUNT = 40;

  private Raid raid;
  private Healable target, healTarget;
  private Spell castingSpell, queuedSpell;

  public Healer(Raid raid) {
    this.raid = raid;
  }

  public void target(Healable target) {
    this.target = target;
  }

  public void update(float delta) {

    if(castingSpell == null && queuedSpell != null) {
      castingSpell = queuedSpell;
      queuedSpell = null;
    }

    if(castingSpell != null) {
      castingSpell.update(delta);
      if(castingSpell.castStatus() == 1.0f) {
        castingSpell = null;
      }
    }

  }

  public void startCast(Spell spell) {
    if(castingSpell == null) {
      castingSpell = spell;
    } else if (castingSpell.castStatus() >= 0.7f) {
      queuedSpell = spell;
    }
  }

  public void stopCast() {
    castingSpell = null;
    queuedSpell = null;
  }

  public float getCastPercentage() { return castingSpell != null ? castingSpell.castStatus() : 0; }
  public Healable getTarget() { return target; }
}
