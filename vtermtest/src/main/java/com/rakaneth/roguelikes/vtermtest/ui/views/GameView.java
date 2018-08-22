package com.rakaneth.roguelikes.vtermtest.ui.views;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import com.rakaneth.roguelikes.vtermtest.ui.Grafix;

import lombok.Getter;

public abstract class GameView {
  protected final Grafix grafix;
  protected final List<EventListener> listeners;
  @Getter protected final String name;
  
  public GameView(final String name, final Grafix grafix) {
    this.grafix = grafix;
    listeners = new ArrayList<>(0);
    this.name = name;
    initListeners();
  }
  
  public void addListeners() {
    for (final EventListener e: listeners) {
      grafix.getScreen().addListener(e);
    }
  }
  
  public void removeListeners() {
    for (final EventListener e: listeners) {
      grafix.getScreen().removeListener(e);
    }
  }
  
  abstract public void render();
  abstract protected void initListeners();
}
