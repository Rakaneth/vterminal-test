package com.rakaneth.roguelikes.vtermtest.ui;

import java.awt.Color;
import java.io.IOException;

import com.rakaneth.roguelikes.vtermtest.GameMap;
import com.rakaneth.roguelikes.vtermtest.GameUtils;
import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.Tile;
import com.valkryst.VTerminal.builder.LabelBuilder;
import com.valkryst.VTerminal.component.Label;

import lombok.Getter;
import squidpony.squidmath.Coord;

public final class Grafix {
  @Getter private Screen screen;
  private final int SCREENW = 100;
  private final int SCREENH = 40;
  private final int MAPW = 60;
  private final int MAPH = 30;

  public Grafix() {
    try {
      screen = new Screen(SCREENW, SCREENH);
    } catch (IOException e) {
      e.printStackTrace();
    }
    screen.addCanvasToFrame();
  }

  public void draw(Sprite s, int x, int y, boolean dark) {
    Color fg;
    Color bg;
    if (dark) {
      fg = s.getDarkFG();
      bg = s.getDarkBG();
    } else {
      fg = s.getFg();
      bg = s.getBg();
    }
    Tile t = screen.getTileAt(x, y);
    t.setBackgroundColor(bg);
    t.setForegroundColor(fg);
    t.setCharacter(s.getGlyph());
  }

  public void draw(Sprite s, int x, int y) {
    draw(s, x, y, false);
  }

  public void draw(Sprite s, Coord c, boolean dark) {
    draw(s, c.x, c.y, dark);
  }

  public void draw(Sprite s, Coord c) {
    draw(s, c, false);
  }

  public void draw(final GameMap m, final Coord center) {
    int wx, wy;
    Coord wc;
    Coord cm = m.cam(center, MAPW, MAPH);
    for (int y = 0; y < MAPH; y++) {
      for (int x = 0; x < MAPW; x++) {
        wx = x + cm.x;
        wy = y + cm.y;
        wc = Coord.get(wx, wy);
        if (!m.isOOB(wc)) {
          Sprite s = m.getSprite(wc);
          draw(s, x, y);
        }
      }
    }
  }
  
  public void draw(final String s, final Coord c) {
    LabelBuilder lb = new LabelBuilder();
    lb.setDimensions(s.length(), 1);
    lb.setText(s);
    screen.addComponent(lb.build());
  }

  private boolean inBounds(Coord c) {
    return GameUtils.between(c.x, 0, MAPW - 1) &&
           GameUtils.between(c.y, 0, MAPH - 1);
  }

  public void drawOnMap(Sprite s, GameMap m, Coord pt, Coord center) {
    Coord cm = m.cam(center, MAPW, MAPH);
    Coord toDraw = Coord.get(pt.x - cm.x, pt.y - cm.y);
    if (inBounds(toDraw)) {
      draw(s, toDraw);
    }
  }

  public void refresh() {
    screen.draw();
  }
}
