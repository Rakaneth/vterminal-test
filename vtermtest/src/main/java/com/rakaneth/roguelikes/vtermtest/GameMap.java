package com.rakaneth.roguelikes.vtermtest;

import java.util.HashMap;
import java.util.Map;

import com.rakaneth.roguelikes.vtermtest.ui.Sprite;

import lombok.Getter;
import squidpony.ArrayTools;
import squidpony.squidmath.Coord;

public class GameMap {
  private char[][] tiles;
  private static final Map<Character, Sprite> tileMap = new HashMap<Character, Sprite>() {
    private static final long serialVersionUID = 2449540743259404144L;
    {
      put('#', Sprite.WALL_STONE);
      put('.', Sprite.FLOOR_WOOD);
      put('?', Sprite.UNKNOWN);
    }
  };
  private boolean[][] visited;
  @Getter private String ID;

  public GameMap(final String id, final char[][] tiles) {
    this.ID = id;
    this.tiles = tiles;
    visited = new boolean[tiles.length][tiles[0].length];
    ArrayTools.fill(visited, false);
  }

  public boolean isOOB(Coord c) {
    return !GameUtils.between(c.x, 0, getWidth() - 1) ||
           !GameUtils.between(c.y, 0, getHeight());
  }

  public int getWidth() {
    return tiles.length;
  }

  public int getHeight() {
    return tiles[0].length;
  }

  public char getTile(Coord c) {
    return isOOB(c) ? '?' : tiles[c.x][c.y];
  }

  public void setTile(char c, Coord pt) {
    tiles[pt.x][pt.y] = c;
  }

  public boolean isVisited(Coord c) {
    return isOOB(c) ? false : visited[c.x][c.y];
  }

  public void visit(Coord c) {
    visited[c.x][c.y] = true;
  }

  public void visit() {
    ArrayTools.fill(visited, true);
  }

  public void forget(Coord c) {
    visited[c.x][c.y] = false;
  }

  public void forget() {
    ArrayTools.fill(visited, false);
  }

  public Sprite getSprite(Coord c) {
    char tile = getTile(c);
    return tileMap.getOrDefault(tile, Sprite.UNKNOWN);
  }

  private int camCalc(int pt, int mapDim, int screenDim) {
    return GameUtils.clamp(pt - screenDim / 2, 0,
                           Math.max(0, mapDim - screenDim));
  }

  public Coord cam(Coord c, int screenWidth, int screenHeight) {
    int left = camCalc(c.x, getWidth(), screenWidth);
    int top = camCalc(c.y, getHeight(), screenHeight);
    return Coord.get(left, top);
  }
}
