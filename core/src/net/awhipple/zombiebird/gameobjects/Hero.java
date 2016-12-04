package net.awhipple.zombiebird.gameobjects;

import com.sun.org.apache.xpath.internal.operations.Mod;

import net.awhipple.zombiebird.mod.Modification;
import net.awhipple.zombiebird.mod.Rejuvinate;

import java.util.Iterator;
import java.util.Vector;

public class Hero implements net.awhipple.zombiebird.gameinterfaces.Healable {
  private float currentHP, maxHP;
  private int xPos, yPos;
  private Vector<Modification> modifications;
  private boolean dead;

  private Raid raid;

  public Hero(Raid raid) {
    this.maxHP = 100f;
    this.currentHP = maxHP;
    this.xPos = this.yPos = 0;
    this.modifications = new Vector<Modification>();
    this.dead = false;

    this.raid = raid;
  }

  public void dealDamage(float damage) {
    currentHP -= damage;
    if(currentHP <= 0 && !dead) {
      currentHP = 0;
      modifications.removeAllElements();
      dead = true;
      raid.updateHeroStats();
    }
  }

  public void heal(float healAmount) {
    if(!dead) {
      currentHP += healAmount;
      if(currentHP > maxHP) currentHP = maxHP;
    }
  }

  public void addMod(Modification mod) {
    modifications.add(mod);
  }

  public void updateMods(float delta) {
    Vector<Modification> modsToBeDeleted = new Vector<Modification>();
    Iterator<Modification> itr = modifications.iterator();
    while(itr.hasNext()) {
      Modification mod = itr.next();
      mod.update(delta);
      if(mod.getTimer() <= 0) {
        modsToBeDeleted.add(mod);
      }
    }
    modifications.removeAll(modsToBeDeleted);
  }

  public float getHPPercentage() { return currentHP / maxHP; }

  public Vector<Modification> getMods() { return modifications; }

  public int getXPos() { return xPos; }
  public int getYPos() { return yPos; }

  public boolean isDead() { return dead; }

  public void setXPos(int xPos) { this.xPos = xPos; }
  public void setYPos(int yPos) { this.yPos = yPos; }
}
