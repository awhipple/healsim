package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class Trainquility extends Spell {

  private static float HEAL_PER_SECOND = 15.0f;

  Healable[] heroes;
  private float nextHeal;

  private static Sprite icon;
  static {
    icon = new Sprite(new Texture(Gdx.files.internal("icons/spell_nature_tranquility.png")));
  }
  public static Sprite getIcon() { return icon; }

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

  public static class Factory extends SpellFactory {
    private static float cooldown = 0;
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
    public Sprite getIcon() { return Trainquility.getIcon(); }
    @Override
    public float getCooldownTime() { return cooldown; }
    @Override
    public float getCooldownPercent() { return cooldown / BASE_COOLDOWN; }

    public static void reduceCooldown(float amount) {
      cooldown -= amount;
      if(cooldown < 0) cooldown = 0;
    }
  }
}
