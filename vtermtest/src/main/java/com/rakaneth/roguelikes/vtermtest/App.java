package com.rakaneth.roguelikes.vtermtest;

import java.io.IOException;

import com.valkryst.VTerminal.Screen;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        final Screen screen = new Screen();
        screen.addCanvasToFrame();
        for (int x=0; x<10; x++) 
        {
        	for (int y=0; y<10; y++) 
        	{
        		screen.getTileAt(x, y).setCharacter('x');
        	}
        }
        
        screen.draw();
    }
}
