package net.awhipple.zombiebird.gameinterfaces;

import net.awhipple.zombiebird.mod.Modification;

public interface Healable {
  void heal(float healAmount);
  void addMod(Modification mod);
  boolean isDead();
}
