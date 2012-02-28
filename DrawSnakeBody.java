package br.com.mateus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.StrokeBorder;

public class DrawSnakeBody implements Sprite {

	private int headPositionX = 100;
	private int headPositionY = 100;

	public Direction direction = Direction.RIGHT;
	private boolean gameOver = false;

	public ChangePosition changePosition;
	private GameWindow gameWindow;

	private List<SnakePieces> snakeBody = new ArrayList<SnakePieces>();

	private int screenWidth;
	private int screenHeight;

	private DrawFood food;

	public DrawSnakeBody(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	@Override
	public void update(ChangePosition changePosition) {
		checkCollision();
		updateSnakePieces();

		if (!this.gameOver) {
			if (changePosition == ChangePosition.DOWN) {
				if (checkDirection() != Direction.UP) {
					headPositionY += gameWindow.cellSize;
					direction = Direction.DOWN;
				} else {
					headPositionY -= gameWindow.cellSize;
				}
			}
			if (changePosition == ChangePosition.UP) {
				if (checkDirection() != Direction.DOWN) {
					headPositionY -= gameWindow.cellSize;
					direction = Direction.UP;
				} else {
					headPositionY += gameWindow.cellSize;
				}
			}
			if (changePosition == ChangePosition.LEFT) {
				if (checkDirection() != Direction.RIGHT) {
					headPositionX -= gameWindow.cellSize;
					direction = Direction.LEFT;
				} else {
					headPositionX += gameWindow.cellSize;
				}
			}
			if (changePosition == ChangePosition.RIGHT) {
				if (checkDirection() != Direction.LEFT) {
					headPositionX += gameWindow.cellSize;
					direction = Direction.RIGHT;
				} else {
					headPositionX -= gameWindow.cellSize;
				}
			}

		}
	}

	/*
	 * Aumenta o corpo da minhoca
	 */
	public void growSnakeBody() {
		// TODO Auto-generated method stub
		if (snakeBody.isEmpty()) {
			switch (direction) {
			case UP:
				// Tem que ser com base no ultimo pedaço da minhoca
				// quando
				// houver ou com base na cabeça
				snakeBody.add(new SnakePieces(headPositionX, headPositionY
						- gameWindow.cellSize, direction));
				break;
			case DOWN:
				snakeBody.add(new SnakePieces(headPositionX, headPositionY
						+ gameWindow.cellSize, direction));
				break;
			case RIGHT:
				snakeBody.add(new SnakePieces(headPositionX
						+ gameWindow.cellSize, headPositionY, direction));
				break;
			case LEFT:
				snakeBody.add(new SnakePieces(headPositionX
						- gameWindow.cellSize, headPositionY, direction));
				break;
			}
		} else {
			switch (snakeBody.get(snakeBody.size() - 1).getDirection()) {
			case UP:
				snakeBody.add(new SnakePieces(snakeBody.get(
						snakeBody.size() - 1).getPositionX(), snakeBody.get(
						snakeBody.size() - 1).getPositionY()
						- gameWindow.cellSize, snakeBody.get(
						snakeBody.size() - 1).getDirection()));

				break;
			case DOWN:
				snakeBody.add(new SnakePieces(snakeBody.get(
						snakeBody.size() - 1).getPositionX(), snakeBody.get(
						snakeBody.size() - 1).getPositionY()
						+ gameWindow.cellSize, snakeBody.get(
						snakeBody.size() - 1).getDirection()));
				break;
			case LEFT:
				snakeBody.add(new SnakePieces(snakeBody.get(
						snakeBody.size() - 1).getPositionX()
						- gameWindow.cellSize, snakeBody.get(
						snakeBody.size() - 1).getPositionY(), snakeBody.get(
						snakeBody.size() - 1).getDirection()));

				break;
			case RIGHT:
				snakeBody.add(new SnakePieces(snakeBody.get(
						snakeBody.size() - 1).getPositionX()
						+ gameWindow.cellSize, snakeBody.get(
						snakeBody.size() - 1).getPositionY(), snakeBody.get(
						snakeBody.size() - 1).getDirection()));
				break;
			}
		}
	}

	public void updateSnakePieces() {
		// TODO Auto-generated method stub
		if (!snakeBody.isEmpty()) {
			for (int i = (snakeBody.size() - 1); i >= 0; i--) {
				if (i != 0) {
					snakeBody.get(i).setPositionX(
							snakeBody.get(i - 1).getPositionX());
					snakeBody.get(i).setPositionY(
							snakeBody.get(i - 1).getPositionY());
					snakeBody.get(i).setDirection(
							snakeBody.get(i - 1).getDirection());
				} else {
					snakeBody.get(0).setPositionX(headPositionX);
					snakeBody.get(0).setPositionY(headPositionY);
					snakeBody.get(0).setDirection(direction);
				}
			}
			System.out.println(snakeBody.size());
			System.out.println(snakeBody.get(snakeBody.size() - 1)
					.getPositionX());
			System.out.println(snakeBody.get(snakeBody.size() - 1)
					.getPositionY());
		}
		System.out.println(snakeBody.size());
	}

	public boolean isGameOver() {
		return gameOver;
	}

	private Direction checkDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	private void checkCollision() {
		// TODO Auto-generated method stub
		if (headPositionX < 0 || headPositionY < 0
				|| headPositionX + gameWindow.cellSize > screenWidth
				|| headPositionY + gameWindow.cellSize > screenHeight) {
			this.gameOver = true;
		}
//		for (int i = 0; i < snakeBody.size(); i++) {
//			if (headPositionX == snakeBody.get(i).getPositionX()
//					&& headPositionY == snakeBody.get(i).getPositionY()) {
//				this.gameOver = true;
//			}
//		}
		/*
		 * if (x < 0) { x = screenWidth - size; } else if ((x + size) >
		 * screenWidth) { x = 0; } if (y < 0) { y = screenHeight - size; } else
		 * if ((y + size) > screenHeight) { y = 0; }
		 */
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.green);
		
		BasicStroke basicStroke = new BasicStroke(20.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 100.0f);
		g2d.setStroke(basicStroke);

		g2d.fill(new Rectangle2D.Float(headPositionX, headPositionY,
				gameWindow.cellSize, gameWindow.cellSize));

		for (int i = 0; i < snakeBody.size(); i++) {
			g2d.fill(new Rectangle2D.Float(snakeBody.get(i).getPositionX(),
					snakeBody.get(i).getPositionY(), gameWindow.cellSize,
					gameWindow.cellSize));
		}
	}

	public float getX() {
		return headPositionX;
	}

	public float getY() {
		return headPositionY;
	}
}