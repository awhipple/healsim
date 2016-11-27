package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gameobjects.Raid;

public abstract class SpellFactory {
  public abstract Spell getSpell(Raid raid);
  public abstract Sprite getIcon();

  public float getCooldownTime() {
    return 0;
  }

  public float getCooldownPercent() {
    return 0;
  }

  public void reduceCooldown(float amount) { }
}
