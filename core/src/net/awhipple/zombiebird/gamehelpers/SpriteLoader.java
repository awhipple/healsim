package net.awhipple.zombiebird.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SpriteLoader {

  /*________________________________________________________________________________

    Pass strings into loadAssets to load new sprites.
    3 modes are supported

    mode 1: {folderName}.{imageName} (e.g. icon.flashHeal)

      Any number of folder names are supported. Put a '.' between each one.
      In this example the sprite is loaded from 'folderName/imageName.png'

      Access: SpriteLoader.getSprite("icon.flashHeal");

    mode 2: {folderName}.{imageName}__{tileX}x{tileW}__{tileY}x{tileH}
            (e.g. roles.roles__2x20__2x20)

      Using this method you can load a series of sprites automatically from a sprite sheet
      tileX and tileY are the dimensions of the grid (by sprite)
      tileW and tileH are the width and height of a single sprite

      Access: SpriteLoader.getSprite("roles.roles", 0, 1);

    mode 3: {folderName}.{imageName}__{tileX}x{tileW}__{tileY}x{tileH}__{name1}__{name2}__{name3}
            (e.g. roles.roles__2x20__2x20__flag__healer__tank__dps)

     Using this method you can give a name to each sprite in the sprite sheet.
     The names are given from left to right, and then top to bottom.
     You can still access these sprites using mode 2's access method.

     Access: SpriteLoader.getSprite("roles.roles", "tank");
   */

  private static Map<String, Sprite> spriteMap;
  private static Map<String, Pair<Integer, Integer>> spriteSheetNames;

  static {
    spriteMap = new HashMap<String, Sprite>();
    spriteSheetNames = new HashMap<String, Pair<Integer, Integer>>();
  }

  public static void loadAssets(String[] spriteNames) {
    for(int i = 0; i < spriteNames.length; i++) {
      String[] imageParams = spriteNames[i].split("__");

      String fileName = imageParams[0].replaceAll("\\.", "/") + ".png";
      Texture texture = new Texture(Gdx.files.internal(fileName));

      if(imageParams.length == 1) {
        spriteMap.put(imageParams[0], new Sprite(texture));
        Gdx.app.log("SpriteLoader", imageParams[0] + " loaded");
      } else if(imageParams.length >= 3) {
        loadTileSheet(texture, imageParams);
      }
    }
  }

  public static Sprite getSprite(String key) {
    if(spriteMap.containsKey(key)) {
      return spriteMap.get(key);
    } else {
      Gdx.app.log("SpriteLoader", "Attempted to load missing sprite, " + key);
      return null;
    }
  }

  public static Sprite getSprite(String key, int x, int y) {
    return getSprite(key + "__" + x + "__" + y);
  }

  public static Sprite getSprite(String key, String tileName) {
    Pair<Integer, Integer> tileLocationPair = spriteSheetNames.get(key + "__" + tileName);
    return getSprite(key, tileLocationPair.getLeft(), tileLocationPair.getRight());
  }

  private static void loadTileSheet(Texture texture, String[] imageParams) {
    String[] xDimStr = imageParams[1].split("x");
    String[] yDimStr = imageParams[2].split("x");

    int tileX = Integer.parseInt(xDimStr[0]), tileY = Integer.parseInt(yDimStr[0]);
    int iconX = Integer.parseInt(xDimStr[1]), iconY = Integer.parseInt(yDimStr[1]);

    for(int y = 0; y < tileY; y++) {
      for(int x = 0; x < tileX; x++) {
        String spriteName = imageParams[0] + "__" + x + "__" + y;
        spriteMap.put(spriteName, new Sprite(texture, x * iconX, y * iconY, iconX, iconY));
        if(imageParams.length == 3) Gdx.app.log("SpriteLoader", spriteName + " loaded");
      }
    }

    for(int k = 3; k < imageParams.length; k++) {
      int gridX = (k-3) % tileY, gridY = (k-3) / tileY;
      spriteSheetNames.put(imageParams[0] + "__" + imageParams[k], new Pair<Integer, Integer>(gridX, gridY));
      Gdx.app.log("SpriteLoader", imageParams[0] + "__" + gridX + "__" + gridY + " loaded, named " + imageParams[k]);
    }
  }

}
