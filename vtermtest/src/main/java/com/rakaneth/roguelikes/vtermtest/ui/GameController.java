package com.rakaneth.roguelikes.vtermtest.ui;

import java.util.HashMap;
import java.util.Map;

import com.rakaneth.roguelikes.vtermtest.ui.views.GameView;

public class GameController {
  private GameView curView;
  private Map<String, GameView> viewMap;
  
  public GameController(final GameView startView) {
    curView = startView;
    curView.addListeners();
    viewMap = new HashMap<>();
  }
  
  public void register(final GameView ...views) {
    for (GameView v: views) {
      viewMap.put(v.getName(), v);
    }
  }
  
  public void changeView(final String viewName) {
    GameView v = viewMap.get(viewName);
    if (v == null) {
      return;
    } else {
      curView.removeListeners();
      curView = v;
      curView.addListeners();
    }
  }
}
