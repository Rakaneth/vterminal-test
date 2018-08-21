package com.rakaneth.roguelikes.vtermtest;

import squidpony.squidgrid.mapping.SerpentMapGenerator;

public class MapBuilder {
	private int caves;
	private int rooms;
	private int width;
	private int height;
	private String id;
	
	public MapBuilder(String id) {
		caves = 1;
		rooms = 0;
		width = 80;
		height = 40;
		this.id = id;
	}
	
	public MapBuilder setCaves(int caves) {
		this.caves = caves;
		return this;
	}
	
	public MapBuilder setRooms(int rooms) {
		this.rooms = rooms;
		return this;
	}
	
	public MapBuilder setWidth(int width) {
		this.width = width;
		return this;
	}
	
	public MapBuilder setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public GameMap build() {
		SerpentMapGenerator smg = new SerpentMapGenerator(width, height, App.getRng());
		smg.putCaveCarvers(caves);
		smg.putBoxRoomCarvers(rooms);
		return new GameMap(this.id, smg.generate());
	}
}
