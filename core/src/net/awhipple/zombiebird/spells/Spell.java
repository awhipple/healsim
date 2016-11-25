package net.awhipple.zombiebird.spells;

public class Spell {
  protected float castTimeRemaining, originalCastTime;
  protected boolean casting;

  public Spell(float spellCastTime) {
    this.castTimeRemaining = this.originalCastTime = spellCastTime;
    casting = true;
  }

  public void update(float delta) {
    castTimeRemaining -= delta;
    if(castTimeRemaining <= 0) {
      castTimeRemaining = 0;
    }

    if(castStatus() == 1 && casting) {
      resolveSpell();
      casting = false;
    }

  };

  public float castStatus() {
    return originalCastTime == 0 ? 1 : (originalCastTime - castTimeRemaining) / originalCastTime;
  }

  public void resolveSpell() {}
}
