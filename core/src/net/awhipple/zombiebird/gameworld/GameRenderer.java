package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.bosses.Boss;
import net.awhipple.zombiebird.gamehelpers.Color;
import net.awhipple.zombiebird.gamehelpers.Pair;
import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Healer;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;
import net.awhipple.zombiebird.mod.Modification;
import net.awhipple.zombiebird.spells.SpellFactory;

import java.util.Iterator;
import java.util.List;

public class GameRenderer {

  public static final int HERO_BAR_WIDTH = 300, HERO_BAR_HEIGHT = 30;
  private static final int HERO_BAR_MIN_SPACING = 20, HERO_BAR_Y_OFFSET = 100;

  private static final int HEROES_PER_ROW = (int)((ZBGame.SCREEN_W - HERO_BAR_MIN_SPACING) / (HERO_BAR_WIDTH + HERO_BAR_MIN_SPACING));
  private static final int HERO_BAR_HORIZONTAL_SPACING = (ZBGame.SCREEN_W - HERO_BAR_WIDTH * HEROES_PER_ROW) / (HEROES_PER_ROW + 1);

  private static final Color UNTARGETED_BORDER_COLOR = new Color(255, 255, 255);
  private static final Color TARGETED_BORDER_COLOR = new Color(255, 0, 0);
  private static final Color FILL_COLOR = new Color(0, 255, 0);

  private GameWorld world;
  private OrthographicCamera cam;
  private ShapeRenderer shapeRenderer;
  private Healer healer;
  private SpriteBatch batch;

  public GameRenderer(GameWorld world) {
    this.world = world;
    this.healer = world.getRaid().getHealer();

    cam = new OrthographicCamera();
    cam.setToOrtho(true, ZBGame.SCREEN_W, ZBGame.SCREEN_H);

    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(cam.combined);

    batch = new SpriteBatch();
  }

  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    Raid raid = world.getRaid();
    Hero[] heroes = raid.getHeroes();
    Boss boss = world.getBoss();

    renderBar(boss.getHealthPercent(), 10, 10, ZBGame.SCREEN_W - 20, HERO_BAR_HEIGHT, UNTARGETED_BORDER_COLOR, new Color(255, 128, 128));

    for(int i = 0; i < heroes.length; i++) {
      renderHeroPortraits(heroes, healer.getTarget());
    }

    if(healer.getCastPercentage() > 0) {
      renderBar(healer.getCastPercentage(), 770, 800, HERO_BAR_WIDTH, HERO_BAR_HEIGHT, UNTARGETED_BORDER_COLOR, new Color(67, 149, 204));
    }

    renderSpellBar(849, ZBGame.SCREEN_H-64);
  }

  public static void setHeroPortraitLocations(Hero[] heroes) {
    for(int i = 0; i < heroes.length; i++) {
      heroes[i].setXPos(HERO_BAR_HORIZONTAL_SPACING + (i % HEROES_PER_ROW) * (HERO_BAR_WIDTH + HERO_BAR_HORIZONTAL_SPACING));
      heroes[i].setYPos(HERO_BAR_Y_OFFSET + HERO_BAR_MIN_SPACING + ((int)(i / HEROES_PER_ROW) * (HERO_BAR_HEIGHT + HERO_BAR_MIN_SPACING)));
    }
  }

  private void renderHeroPortraits(Hero[] heroes, Healable healTarget) {
    for(int i = 0; i < heroes.length; i++) {
      renderBar(heroes[i].getHPPercentage(),
                heroes[i].getXPos(),
                heroes[i].getYPos(),
                HERO_BAR_WIDTH, HERO_BAR_HEIGHT,
                heroes[i] == healTarget ? TARGETED_BORDER_COLOR : UNTARGETED_BORDER_COLOR,
                FILL_COLOR,
                heroes[i] == healTarget ? 3 : 1
      );
      Iterator<Modification> itr = heroes[i].getMods().iterator();
      int buffOffset = 0;
      while(itr.hasNext()) {
        Modification mod = itr.next();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(heroes[i].getXPos() + buffOffset * 15, heroes[i].getYPos() + HERO_BAR_HEIGHT + 5, 10, 10);
        shapeRenderer.end();
        buffOffset++;
      }
    }
  }

  private void renderBar(float percentage, int x, int y, int width, int height, Color borderColor, Color fillColor) {
    renderBar(percentage, x, y, width, height, borderColor, fillColor, 1);
  }

  private void renderBar(float percentage, int x, int y, int width, int height, Color borderColor, Color fillColor, int borderThickness) {
    shapeRenderer.begin(ShapeType.Filled);
    shapeRenderer.setColor(fillColor.getR(), fillColor.getG(), fillColor.getB(), 1);
    shapeRenderer.rect(x, y, width * percentage, height);
    shapeRenderer.end();

    for (int i = 0; i < borderThickness; i++) {
      shapeRenderer.begin(ShapeType.Line);
      shapeRenderer.setColor(borderColor.getR(), borderColor.getG(), borderColor.getB(), 1);
      shapeRenderer.rect(x - i, y - i, width + i * 2, height + i * 2);
      shapeRenderer.end();
    }
  }

  private void renderSpellBar(int x, int y) {
    List<Pair<Integer, SpellFactory>> keyBinds = world.getRaid().getHealer().getSkillSet();
    Iterator<Pair<Integer, SpellFactory>> itr = keyBinds.iterator();
    int xOffset = 0;
    while(itr.hasNext()) {
      Pair<Integer, SpellFactory> keyBind = itr.next();
      SpellFactory spellFactory = keyBind.getRight();
      batch.begin();
      Sprite sprite = spellFactory.getIcon();
      sprite.setPosition(x + xOffset - sprite.getWidth() / 2, ZBGame.SCREEN_H - y - sprite.getHeight() / 2);
      sprite.draw(batch);
      batch.end();

      float displayCooldown;
      if(healer.getHero().isDead()) {
        displayCooldown = 1.0f;
      } else {
        displayCooldown = healer.getCooldownTime() > spellFactory.getCooldownTime() ? healer.getCoolDownPercent() : spellFactory.getCooldownPercent();
      }

      Gdx.gl.glEnable(GL20.GL_BLEND);
      Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
      float start = (270 + 360 * (1.0f - displayCooldown)) % 360, size = 360.0f * displayCooldown;
      shapeRenderer.begin(ShapeType.Filled);
      shapeRenderer.setColor(0, 0, 0, 0.7f);
      shapeRenderer.arc(x + xOffset, y, 46, start, size);
      shapeRenderer.end();
      Gdx.gl.glDisable(GL20.GL_BLEND);
      xOffset += 79;
    }
  }
}
