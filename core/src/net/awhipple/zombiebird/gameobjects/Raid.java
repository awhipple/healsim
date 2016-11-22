package net.awhipple.zombiebird.gameobjects;

public class Raid {

  private Hero[] heroes;

  public Raid(int raidSize) {
    this.heroes = new Hero[raidSize];
    for(int i = 0; i < raidSize; i++) {
      heroes[i] = new Hero();
    }
  }

  public Hero[] getHeroes() { return heroes; }
}
