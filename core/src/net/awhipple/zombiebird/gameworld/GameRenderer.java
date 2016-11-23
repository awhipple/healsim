package net.awhipple.zombiebird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.awhipple.zombiebird.ZBGame;
import net.awhipple.zombiebird.gamehelpers.Color;
import net.awhipple.zombiebird.gameinterfaces.Healable;
import net.awhipple.zombiebird.gameobjects.Hero;
import net.awhipple.zombiebird.gameobjects.Raid;

public class GameRenderer {

  public static final int HERO_BAR_WIDTH = 300, HERO_BAR_HEIGHT = 30;
  private static final int HERO_BAR_MIN_SPACING = 20;

  private static final int HEROES_PER_ROW = (int)((ZBGame.SCREEN_W - HERO_BAR_MIN_SPACING) / (HERO_BAR_WIDTH + HERO_BAR_MIN_SPACING));
  private static final int HERO_BAR_HORIZONTAL_SPACING = (ZBGame.SCREEN_W - HERO_BAR_WIDTH * HEROES_PER_ROW) / (HEROES_PER_ROW + 1);

  private static final Color UNTARGETED_BORDER_COLOR = new Color(255, 255, 255);
  private static final Color TARGETED_BORDER_COLOR = new Color(255, 0, 0);
  private static final Color FILL_COLOR = new Color(0, 255, 0);

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
      renderHeroPortraits(heroes, world.getHealer().getTarget());
    }

    renderBar(world.getHealer().getCastPercentage(), 810, 800, HERO_BAR_WIDTH, HERO_BAR_HEIGHT, UNTARGETED_BORDER_COLOR, FILL_COLOR);
  }

  public static void setHeroPortraitLocations(Hero[] heroes) {
    for(int i = 0; i < heroes.length; i++) {
      heroes[i].setXPos(HERO_BAR_HORIZONTAL_SPACING + (i % HEROES_PER_ROW) * (HERO_BAR_WIDTH + HERO_BAR_HORIZONTAL_SPACING));
      heroes[i].setYPos(HERO_BAR_MIN_SPACING + ((int)(i / HEROES_PER_ROW) * (HERO_BAR_HEIGHT + HERO_BAR_MIN_SPACING)));
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
}
