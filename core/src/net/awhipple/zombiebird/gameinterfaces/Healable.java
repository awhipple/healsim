package net.awhipple.zombiebird.gameinterfaces;

import net.awhipple.zombiebird.mod.Modification;

public interface Healable {
  public void heal(float healAmount);
  public void addMod(Modification mod);
  public boolean isDead();
}
