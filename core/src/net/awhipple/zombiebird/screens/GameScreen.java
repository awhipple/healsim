package net.awhipple.zombiebird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import net.awhipple.zombiebird.gamehelpers.InputHandler;
import net.awhipple.zombiebird.gamehelpers.SpriteLoader;
import net.awhipple.zombiebird.gameworld.GameRenderer;
import net.awhipple.zombiebird.gameworld.GameWorld;

public class GameScreen implements Screen {

  private GameWorld world;
  private GameRenderer renderer;
  private InputHandler inputHandler;

  public GameScreen() {
    Gdx.app.log("GameScreen", "Attached");

    SpriteLoader.loadAssets(new String[]{"roles.roles__2x20__2x20__flag__healer__tank__dps"});

    world = new GameWorld();
    renderer = new GameRenderer(world);
    inputHandler = new InputHandler(world);
  }

  @Override
  public void render(float delta) {
    inputHandler.processInput();
    world.update(delta);
    renderer.render();
  }

  @Override
  public void resize(int width, int height) {
    Gdx.app.log("GameScreen", "resizing");
  }

  @Override
  public void show() {
    Gdx.app.log("GameScreen", "show called");
  }

  @Override
  public void hide() {
    Gdx.app.log("GameScreen", "hide called");
  }

  @Override
  public void pause() {
    Gdx.app.log("GameScreen", "pause called");
  }

  @Override
  public void resume() {
    Gdx.app.log("GameScreen", "resume called");
  }

  @Override
  public void dispose() {
    //SpriteLoader.cleanup();
  }
}
