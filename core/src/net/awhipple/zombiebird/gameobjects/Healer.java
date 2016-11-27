package net.awhipple.zombiebird.gameobjects;

import com.badlogic.gdx.Input;

import net.awhipple.zombiebird.gamehelpers.Pair;
import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.spells.Heal;
import net.awhipple.zombiebird.spells.Rejuvination;
import net.awhipple.zombiebird.spells.Spell;
import net.awhipple.zombiebird.spells.SpellFactory;
import net.awhipple.zombiebird.spells.Trainquility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Healer {
  private final static float HEAL_AMOUNT = 40, GLOBAL_COOL_DOWN = 1.0f;

  private Raid raid;
  private Hero healerHero;
  private Healable target, healTarget;
  private Spell castingSpell, queuedSpell;
  private float globalCoolDown;

  private List<Pair<Integer, SpellFactory>> skillSet;

  public Healer(Raid raid, Hero healerHero) {
    this.raid = raid;
    this.healerHero = healerHero;
    this.globalCoolDown = 0f;
    this.skillSet = generateSkillSet();
  }

  public void target(Healable target) {
    this.target = target;
  }

  public void update(float delta) {

    globalCoolDown -= delta;
    if(globalCoolDown < 0) globalCoolDown = 0;
    if(castingSpell == null && queuedSpell != null && globalCoolDown == 0) {
      castingSpell = queuedSpell;
      queuedSpell = null;
      globalCoolDown = GLOBAL_COOL_DOWN;
    }

    if(castingSpell != null) {
      castingSpell.update(delta);
      if(castingSpell.castStatus() == 1.0f) {
        castingSpell = null;
      }
    }

    Iterator<Pair<Integer, SpellFactory>> itr = skillSet.iterator();
    while(itr.hasNext()) {
      SpellFactory spellFactory = itr.next().getRight();
      spellFactory.reduceCooldown(delta);
    }
  }

  public void startCast(Spell spell) {
    if(spell == null) return;
    if(castingSpell == null && globalCoolDown <= 0f) {
      castingSpell = spell;
      globalCoolDown = GLOBAL_COOL_DOWN;
    } else if (castingSpell != null && castingSpell.castStatus() >= 0.7f || (globalCoolDown > 0 && globalCoolDown <= GLOBAL_COOL_DOWN * 0.333f)) {
      queuedSpell = spell;
    }
  }

  public void stopCast() {
    castingSpell = null;
    queuedSpell = null;
  }

  public Hero getHero() { return healerHero; }

  public float getCastPercentage() { return castingSpell != null ? castingSpell.displayCastStatus() : 0; }
  public float getCooldownTime() { return globalCoolDown; }
  public float getCoolDownPercent() { return globalCoolDown / 1.0f; }
  public Healable getTarget() { return target; }

  public List<Pair<Integer, SpellFactory>> getSkillSet() { return skillSet; }

  private List<Pair<Integer, SpellFactory>> generateSkillSet() {
    List<Pair<Integer, SpellFactory>> keyBinds = new ArrayList<Pair<Integer, SpellFactory>>();

    keyBinds.add(generateSkill(Input.Keys.NUM_1, new Heal.Factory()));
    keyBinds.add(generateSkill(Input.Keys.NUM_2, new Rejuvination.Factory()));
    keyBinds.add(generateSkill(Input.Keys.NUM_3, new Trainquility.Factory()));

    return keyBinds;
  }

  private static Pair<Integer, SpellFactory> generateSkill(int key, SpellFactory spellFactory) {
    return new Pair(new Integer(key), spellFactory);
  }
}
