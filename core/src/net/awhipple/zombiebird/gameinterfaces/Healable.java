package net.awhipple.zombiebird.gameinterfaces;

import net.awhipple.zombiebird.mod.Modification;

import java.util.Vector;

public interface Healable {
  public void heal(float healAmount);
  public void addMod(Modification mod);
  public void removeMod(Modification mod);
  public Vector<Modification> getMods();

  public boolean isDead();
}
