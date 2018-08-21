package com.rakaneth.roguelikes.vtermtest;

import java.io.IOException;

import lombok.Getter;
import squidpony.squidmath.Coord;
import squidpony.squidmath.RNG;
import squidpony.squidmath.StatefulRNG;

public class App {
  @Getter static final RNG rng = new StatefulRNG(0xDEADBEEF);

  public static void main(String[] args) throws IOException {
    Grafix grafix = new Grafix();
    GameMap m = new MapBuilder("test").setWidth(100).setHeight(100).build();
    Coord crsr = Coord.get(85, 95);
    grafix.draw(m, crsr);
    grafix.drawOnMap(Sprite.CURSOR, m, crsr, crsr);
    grafix.refresh();
  }
}
