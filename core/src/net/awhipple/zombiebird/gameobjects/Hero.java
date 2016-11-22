package net.awhipple.zombiebird.gameobjects;

public class Hero {
  private float currentHP, maxHP;

  public Hero() {
    this.maxHP = 100f;
    this.currentHP = maxHP;
  }

  public void dealDamage(float damage) {
    currentHP -= damage;
    if(currentHP < 0) currentHP = 0;
  }

  public float getCurrentHP() { return currentHP; }
  public float getMaxHP() { return maxHP; }
  public float getHPPercentage() { return currentHP / maxHP; }
}
