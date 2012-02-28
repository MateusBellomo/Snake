package br.com.mateus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class DrawFood {
	private GameWindow gameWindow;

	private int positionX;
	private int positionY;

	public static boolean foodOnField;

	public static boolean isFoodOnField() {
		return foodOnField;
	}

	public void draw(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.RED);

		if (!foodOnField) {
			Random random = new Random();
			positionX = (int) (random.nextInt(gameWindow.columnsNumber) * gameWindow.cellSize);
			positionY = (int) (random.nextInt(gameWindow.rowsNumber) * gameWindow.cellSize);
		}

		g2d.fill(new Rectangle2D.Float(positionX, positionY,
				gameWindow.cellSize, gameWindow.cellSize));
		// g2d.dispose();
		this.foodOnField = true;
	}

	public void update() {
		// TODO Auto-generated method stub
		this.foodOnField = false;
	}

	public int getX() {
		return positionX;
	}

	public int getY() {
		return positionY;
	}
	
}