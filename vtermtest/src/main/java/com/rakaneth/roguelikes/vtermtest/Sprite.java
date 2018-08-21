package com.rakaneth.roguelikes.vtermtest;

import com.valkryst.VTerminal.misc.ColorFunctions;
import lombok.Getter;

import java.awt.Color;

public enum Sprite {
	UNKNOWN('?', null, null),
	FLOOR_WOOD('.', Color.WHITE, Color.YELLOW),
	WALL_STONE('#', Color.WHITE, Color.GRAY);
	
	@Getter private final char glyph;
	@Getter private final Color bg;
	@Getter private final Color fg;
	@Getter private final Color darkBG;
	@Getter private final Color darkFG;
	
	Sprite(final char glyph, final Color fg, final Color bg) {
		this.glyph = glyph;
		this.bg = (bg == null) ? Color.MAGENTA : bg;
		this.fg = (fg == null) ? Color.MAGENTA : fg;
		
		darkBG = ColorFunctions.shade(this.bg, 0.5);
		darkFG = ColorFunctions.shade(this.fg, 0.5);
	}
}
