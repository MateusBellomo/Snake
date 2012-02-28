package br.com.mateus;

import java.awt.Graphics2D;

public interface Sprite {
	void update(ChangePosition changePosition);
	void draw(Graphics2D g2d);
}
