package net.awhipple.zombiebird.spells;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Raid;

public interface SpellFactory {
  public Spell getSpell(Raid raid);
}
