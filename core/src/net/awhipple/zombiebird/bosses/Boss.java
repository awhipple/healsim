package net.awhipple.zombiebird.bosses;

import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class Boss {
  private float currentHP, maxHP;

  private Raid targetRaid;
  private float meleeTimer;
  private float fireBallTimer;

  public Boss(float hitPoints, Raid raid) {
    this.currentHP = this.maxHP = hitPoints;
    this.targetRaid = raid;

    this.meleeTimer = 2.0f;
    this.fireBallTimer = 1.0f;
  }

  public void update(float delta) {
    Hero[] heroes = targetRaid.getHeroes();

    if(currentHP > 0) {
      fireBallTimer -= delta;
      if (fireBallTimer < 0 && targetRaid.getHeroesAlive() > 0) {
        Hero targetHero = targetRaid.getRandomHero();
        if (targetHero != null) targetHero.dealDamage(30.0f);
        fireBallTimer += 1.0f;
      }
      meleeTimer -= delta;
      if (meleeTimer <= 0) {
        if (heroes[0].isDead()) {
          Hero targetHero = targetRaid.getRandomHero();
          if (targetHero != null) targetHero.dealDamage(100.0f);
          meleeTimer += 3.0f;
        } else {
          heroes[0].dealDamage(15.0f);
          meleeTimer += 2.0f;
        }
      }
    }

    dealDamage(targetRaid.getHeroesAlive() * 0.1f * delta);
  }

  public void dealDamage(float damage) {
    currentHP -= damage;
    if(currentHP < 0) currentHP = 0;
  }

  public float getHealthPercent() { return currentHP / maxHP; }
  public boolean isDead() { return currentHP == 0; }
}
