package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gamehelpers.SpriteLoader;
import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Raid;
import net.awhipple.zombiebird.mod.Modification;
import net.awhipple.zombiebird.mod.Rejuvinate;

public class Rejuvination extends Spell {

  private static String ICON_NAME = "icons.rejuvenation";
  static { icon = SpriteLoader.loadAsset(ICON_NAME); }

  private Healable target;

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
    public Sprite getIcon() { return icon; }
  }
}