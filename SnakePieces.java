package br.com.mateus;

public class SnakePieces {
	private int positionX;
	private int positionY;
	private Direction direction;
	
	
	public SnakePieces(int positionX, int positionY, Direction direction) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
}
