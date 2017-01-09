package net.awhipple.zombiebird.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.awhipple.zombiebird.gamehelpers.SpriteLoader;
import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Raid;
import net.awhipple.zombiebird.mod.Modification;
import net.awhipple.zombiebird.mod.Rejuvinate;

import java.util.Iterator;
import java.util.Vector;

public class Rejuvination extends Spell {

  private static String ICON_NAME = "icons.rejuvenation";
  private static Sprite l_icon;
  static { l_icon = icon = SpriteLoader.getSprite(ICON_NAME); }

  private Healable target;

  public Rejuvination(Healable target) {
    super(0f);
    this.target = target;
  }

  @Override
  public void resolveSpell() {
    int count = 0;
    float shortestDuration = 1000000.0f;
    Modification shortestDurationMod = null;
    Vector<Modification> mods = target.getMods();
    Iterator<Modification> itr = mods.iterator();
    while(itr.hasNext()) {
      Modification mod = itr.next();
      if(mod.getModName() == Rejuvinate.class.getName()) {
        if(mod.getTimer() < shortestDuration) {
          shortestDuration = mod.getTimer();
          shortestDurationMod = mod;
        }
        count++;
      }
    }
    if(count >= 2) {
      target.removeMod(shortestDurationMod);
    }
    target.addMod(new Rejuvinate(target));
  }

  public static class Factory extends SpellFactory {
    @Override
    public Spell getSpell(Raid raid) {
      return new Rejuvination(raid.getHealer().getTarget());
    }
    @Override
    public Sprite getIcon() { return l_icon; }
  }
}