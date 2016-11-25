package net.awhipple.zombiebird.gameobjects;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.spells.Spell;

public class Healer {
  private final static float HEAL_AMOUNT = 40, GLOBAL_COOL_DOWN = 1.0f;

  private Raid raid;
  private Healable target, healTarget;
  private Spell castingSpell, queuedSpell;
  private float globalCoolDown;

  public Healer(Raid raid) {
    this.raid = raid;
    globalCoolDown = 0f;
  }

  public void target(Healable target) {
    this.target = target;
  }

  public void update(float delta) {

    globalCoolDown -= delta;
    if(castingSpell == null && queuedSpell != null && globalCoolDown <= 0) {
      castingSpell = queuedSpell;
      queuedSpell = null;
      globalCoolDown = GLOBAL_COOL_DOWN;
    }

    if(castingSpell != null) {
      castingSpell.update(delta);
      if(castingSpell.castStatus() == 1.0f) {
        castingSpell = null;
      }
    }
  }

  public void startCast(Spell spell) {
    if(castingSpell == null && globalCoolDown <= 0f) {
      castingSpell = spell;
      globalCoolDown = GLOBAL_COOL_DOWN;
    } else if (castingSpell != null && castingSpell.castStatus() >= 0.7f || (globalCoolDown > 0 && globalCoolDown <= GLOBAL_COOL_DOWN * 0.333f)) {
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
