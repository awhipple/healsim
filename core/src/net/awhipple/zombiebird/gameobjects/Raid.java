package net.awhipple.zombiebird.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class Raid {

  private Hero[] heroes;
  private Healer healer;
  private List<Hero> heroesAlive;

  public Raid(int raidSize) {
    this.heroes = new Hero[raidSize];
    for(int i = 0; i < raidSize; i++) {
      heroes[i] = new Hero(this);
    }
    healer = new Healer(this, this.heroes[1]);

    heroesAlive = new ArrayList<Hero>();
    updateHeroStats();
  }

  public void updateHeroStats() {
    heroesAlive.clear();
    for(int i = 0; i < heroes.length; i++) {
      if(!heroes[i].isDead()) {
        heroesAlive.add(heroes[i]);
      }
    }
  }

  public Hero getRandomHero() {
    return heroesAlive.size() > 0 ? heroesAlive.get((int)(Math.random() * heroesAlive.size())) : null;
  }

  public Hero[] getHeroes() { return heroes; }
  public Healer getHealer() { return healer; }
  public int getHeroesAlive() { return heroesAlive.size(); }
}
