package net.awhipple.zombiebird.gameobjects;

public class Hero implements net.awhipple.zombiebird.gameinterfaces.Healable {
  private float currentHP, maxHP;
  private int xPos, yPos;
  private boolean dead;

  public Hero() {
    this.maxHP = 100f;
    this.currentHP = maxHP;
    this.xPos = this.yPos = 0;
    this.dead = false;
  }

  public void dealDamage(float damage) {
    currentHP -= damage;
    if(currentHP <= 0) {
      currentHP = 0;
      dead = true;
    }
  }

  public void heal(float healAmount) {
    if(!dead) {
      currentHP += healAmount;
      if(currentHP > maxHP) currentHP = maxHP;
    }
  }

  public float getCurrentHP() { return currentHP; }
  public float getMaxHP() { return maxHP; }
  public float getHPPercentage() { return currentHP / maxHP; }

  public int getXPos() { return xPos; }
  public int getYPos() { return yPos; }

  public boolean isDead() { return dead; }

  public void setXPos(int xPos) { this.xPos = xPos; }
  public void setYPos(int yPos) { this.yPos = yPos; }
}
