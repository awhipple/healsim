package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class GameRenderer {

  private static final int HERO_BAR_WIDTH = 300, HERO_BAR_HEIGHT = 30;
  private static final int HERO_BAR_MIN_SPACING = 20;

  private static final int HEROES_PER_ROW = (int)((ZBGame.SCREEN_W - HERO_BAR_MIN_SPACING) / (HERO_BAR_WIDTH + HERO_BAR_MIN_SPACING));
  private static final int HERO_BAR_HORIZONTAL_SPACING = (ZBGame.SCREEN_W - HERO_BAR_WIDTH * HEROES_PER_ROW) / (HEROES_PER_ROW + 1);

  private GameWorld world;
  private OrthographicCamera cam;
  private ShapeRenderer shapeRenderer;

  public GameRenderer(GameWorld world) {
    this.world = world;

    cam = new OrthographicCamera();
    cam.setToOrtho(true, ZBGame.SCREEN_W, ZBGame.SCREEN_H);

    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(cam.combined);
  }

  public void render() {
    Gdx.app.log("GameRenderer", "render");

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    Raid raid = world.getRaid();
    Hero[] heroes = raid.getHeroes();
    for(int i = 0; i < heroes.length; i++) {
      renderHeroPortrait(heroes[i], HERO_BAR_HORIZONTAL_SPACING + (i % HEROES_PER_ROW) * (HERO_BAR_WIDTH + HERO_BAR_HORIZONTAL_SPACING), HERO_BAR_MIN_SPACING + ((int)(i / HEROES_PER_ROW) * (HERO_BAR_HEIGHT + HERO_BAR_MIN_SPACING)));
    }

    renderBar(world.getHealer().getCastPercentage(), 810, 800, HERO_BAR_WIDTH, HERO_BAR_HEIGHT);

  }

  private void renderHeroPortrait(Hero hero, int x, int y) {
    renderBar(hero.getHPPercentage(), x, y, HERO_BAR_WIDTH, HERO_BAR_HEIGHT);
  }

  private void renderBar(float percentage, int x, int y, int width, int height) {
    shapeRenderer.begin(ShapeType.Filled);
    shapeRenderer.setColor(0 / 255.0f, 255 / 255.0f, 0 / 255.0f, 1);
    shapeRenderer.rect(x, y, width * percentage, height);
    shapeRenderer.end();

    shapeRenderer.begin(ShapeType.Line);
    shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
    shapeRenderer.rect(x, y, width, height);
    shapeRenderer.end();
  }
}
