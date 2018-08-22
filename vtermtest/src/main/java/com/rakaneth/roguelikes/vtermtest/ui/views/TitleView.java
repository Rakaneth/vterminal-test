package com.rakaneth.roguelikes.vtermtest.ui.views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rakaneth.roguelikes.vtermtest.ui.Grafix;

public class TitleView extends GameView {

  public TitleView(final Grafix grafix) {
    super("title", grafix);
  }
  
  @Override
  public void render() {
    

  }

  @Override
  protected void initListeners() {
    final KeyListener keyListener = new KeyListener() {

      @Override
      public void keyPressed(KeyEvent arg0) {}

      @Override
      public void keyReleased(KeyEvent e) {
        System.out.println("Key " + e.toString() + " pressed");
      }

      @Override
      public void keyTyped(KeyEvent arg0) {}
     
    };
    
    listeners.add(keyListener);
  }
}
