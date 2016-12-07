package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Spell {
  protected float castTimeRemaining, originalCastTime;
  protected boolean casting;
  protected static Sprite icon;

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

  }

  public float castStatus() {
    return originalCastTime == 0 ? 1 : (originalCastTime - castTimeRemaining) / originalCastTime;
  }

  public float displayCastStatus() {
    return castStatus();
  }

  public void resolveSpell() {}

  public Sprite getIcon() { return icon; }
}
