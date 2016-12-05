package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Raid;

public class Heal extends Spell {

  private Healable target;

  private static Sprite icon;
  static {
    icon = new Sprite(new Texture(Gdx.files.internal("icons/flashHeal.png")));
  }
  public static Sprite getIcon() { return icon; }

  public Heal(Healable target) {
    super(1.0f);
    this.target = target;
  }

  @Override
  public void resolveSpell() {
    target.heal(40.0f);
  }

  public static class Factory extends SpellFactory {
    @Override
    public Spell getSpell(Raid raid) {
      return raid.getHealer().getTarget() != null ? new Heal(raid.getHealer().getTarget()) : null;
    }
    @Override
    public Sprite getIcon() { return Heal.getIcon(); }
  }
}