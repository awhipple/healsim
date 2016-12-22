package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.bosses.Boss;
import net.awhipple.zombiebird.gamehelpers.FloatingText;
import net.awhipple.zombiebird.gameobjects.Healer;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWorld {

  private Raid raid;
  private Boss boss;

  private BitmapFont font;
  private List<FloatingText> floatingTexts;

  public GameWorld() {
    this.raid = new Raid(20, this);
    this.boss = new Boss(100.0f, this.raid);
    this.font = new BitmapFont(Gdx.files.internal("fonts/csfont.fnt"), Gdx.files.internal("fonts/csfont_0.tga"), false);
    this.font.setColor(0,1,0,1);
    this.floatingTexts = new ArrayList<FloatingText>();
    GameRenderer.setHeroPortraitLocations(raid.getHeroes());
  }

  public void update(float delta) {
    Hero[] heroes = raid.getHeroes();
    for(int i = 0; i < heroes.length; i++) {
      heroes[i].updateMods(delta);
    }

    boss.update(delta);

    if(raid.getHealer().getHero().isDead()) {
      raid.getHealer().stopCast();
    } else {
      raid.getHealer().update(delta);
    }

    List<FloatingText> textToRemove = new ArrayList<FloatingText>();
    Iterator<FloatingText> itr = floatingTexts.iterator();
    while(itr.hasNext()) {
      FloatingText floatingText = itr.next();
      floatingText.update(delta);
      if(floatingText.animationComplete()) {
        textToRemove.add(floatingText);
      }
    }
    floatingTexts.removeAll(textToRemove);
  }

  public void addFloatingText(FloatingText floatingText) {
    floatingTexts.add(floatingText);
  }

  public Raid getRaid() { return raid; }
  public Boss getBoss() { return boss; }

  public BitmapFont getFont() { return font; }
  public List<FloatingText> getFloatingTexts() { return floatingTexts; }
}
