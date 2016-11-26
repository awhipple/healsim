package net.awhipple.zombiebird.gameobjects;

public class Raid {

  private Hero[] heroes;
  private Healer healer;

  public Raid(int raidSize) {
    this.heroes = new Hero[raidSize];
    for(int i = 0; i < raidSize; i++) {
      heroes[i] = new Hero();
    }
    healer = new Healer(this);
  }

  public Hero[] getHeroes() { return heroes; }
  public Healer getHealer() { return healer; }
}
