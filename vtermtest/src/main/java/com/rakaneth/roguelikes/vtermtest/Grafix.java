package com.rakaneth.roguelikes.vtermtest;

import java.awt.Color;
import java.io.IOException;

import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.Tile;

import squidpony.squidmath.Coord;

public final class Grafix {
	private final Screen screen;
	
	public Grafix() throws IOException {
		screen = new Screen();
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
	
	public void draw(GameMap m, Coord center) {
		int w = screen.getWidth();
		int h = screen.getHeight();
		int wx, wy;
		Coord wc;
		Coord cm = m.cam(center, w, h);
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
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
	
	private boolean inBounds(Coord c) {
		return GameUtils.between(c.x, 0, screen.getWidth() - 1) && GameUtils.between(c.y,  0, screen.getHeight() - 1);	
	}
	
	public void drawOnMap(Sprite s, GameMap m, Coord pt, Coord center) {
		int w = screen.getWidth();
		int h = screen.getHeight();
		Coord cm = m.cam(center, w, h);
		Coord toDraw = Coord.get(pt.x - cm.x, pt.y - cm.y);
		if (inBounds(toDraw)) {
			draw(s, toDraw);
		}
	}
	
	public void refresh() {
		screen.draw();
	}
}
