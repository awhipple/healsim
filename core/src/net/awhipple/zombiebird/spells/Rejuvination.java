package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Raid;
import net.awhipple.zombiebird.mod.Modification;
import net.awhipple.zombiebird.mod.Rejuvinate;

public class Rejuvination extends Spell {

  private Healable target;

  private static Sprite icon;
  static {
    icon = new Sprite(new Texture(Gdx.files.internal("icons/spell_nature_rejuvenation.png")));
  }
  public static Sprite getIcon() { return icon; }

  public Rejuvination(Healable target) {
    super(0f);
    this.target = target;
  }

  @Override
  public void resolveSpell() {
    target.addMod(new Rejuvinate(target));
  }

  public static class Factory extends SpellFactory {
    @Override
    public Spell getSpell(Raid raid) {
      return new Rejuvination(raid.getHealer().getTarget());
    }
    @Override
    public Sprite getIcon() { return Rejuvination.getIcon(); }
  }
}