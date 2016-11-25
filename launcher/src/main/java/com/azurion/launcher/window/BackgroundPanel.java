package com.azurion.launcher.window;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class BackgroundPanel extends JComponent{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
    
    public BackgroundPanel(Image image) {
        this.image = image;
    }
    
    public BackgroundPanel(String imageName, int width, int height){
    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(imageName);
		
		try {
			this.image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
   
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
