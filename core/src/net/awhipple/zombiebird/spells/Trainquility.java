package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gamehelpers.SpriteLoader;
import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class Trainquility extends Spell {

  private static String ICON_NAME = "icons.tranquility";
  private static Sprite l_icon;
  static { l_icon = icon = SpriteLoader.loadAsset(ICON_NAME); }

  private static float HEAL_PER_SECOND = 15.0f;

  Healable[] heroes;
  private float nextHeal;

  public Trainquility(Healable[] heroes) {
    super(5.0f);
    this.heroes = heroes;
    this.nextHeal = 4.0f;
  }

  @Override
  public void update(float delta) {
    super.update(delta);

    if(castTimeRemaining <= nextHeal) {
      for(int i = 0; i < heroes.length; i++) {
        heroes[i].heal(HEAL_PER_SECOND);
      }
      nextHeal -= 1.0f;
    }
  }

  @Override
  public float displayCastStatus() {
    return 1.0f - super.castStatus();
  }

  public static class Factory extends SpellFactory {
    private float cooldown = 0;
    private static final float BASE_COOLDOWN = 30.0f;
    @Override
    public Spell getSpell(Raid raid) {
      if(cooldown == 0) {
        cooldown = BASE_COOLDOWN;
        return new Trainquility(raid.getHeroes());
      } else {
        return null;
      }
    }
    @Override
    public Sprite getIcon() { return l_icon; }
    @Override
    public float getCooldownTime() { return cooldown; }
    @Override
    public float getCooldownPercent() { return cooldown / BASE_COOLDOWN; }
    @Override
    public void reduceCooldown(float amount) {
      cooldown -= amount;
      if(cooldown < 0) cooldown = 0;
    }
  }
}
