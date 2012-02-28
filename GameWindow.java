package br.com.mateus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameWindow extends JFrame implements LoopSteps {
	private MainLoop loop = new MainLoop(this);

	private DrawSnakeBody snake;
	private DrawFood food;
	
	static final int cellSize = 20;
	static final int rowsNumber = 30;
	static final int columnsNumber = 30;
	static final int frameWidth = cellSize * rowsNumber;
	static final int frameHeight = cellSize * columnsNumber;
	
	public ChangePosition changePosition = ChangePosition.RIGHT;

	public GameWindow() {
		super("Snake Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		setSize(frameWidth, frameHeight);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				loop.stop();
			}
		});

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					changePosition = ChangePosition.DOWN;
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					changePosition = ChangePosition.RIGHT;
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					changePosition = ChangePosition.LEFT;
				if (e.getKeyCode() == KeyEvent.VK_UP)
					changePosition = ChangePosition.UP;
			}
		});
	}

	public void startMainLoop() {
		new Thread(loop, "Main Loop").start();
	}

	@Override
	public void setup() {
		// Cria o double buffering
		createBufferStrategy(2);

		// Subtrai a decoração da janela da largura e altura máximas percorridas
		// pela snake
		snake = new DrawSnakeBody(getWidth() - getInsets().left
				- getInsets().right, getHeight() - getInsets().top
				- getInsets().bottom);

		// Desenha a comida
		food = new DrawFood();
	}

	@Override
	public void processLogics() {
		// Chama o update da snake
		snake.update(changePosition);

		if (!snake.isGameOver()) {
			if(food.getX() == snake.getX() && food.getY() == snake.getY()){
				// Chama o update da comida
				snake.growSnakeBody();
				food.update();
			}
		} else {
			loop.stop();
		}
	}

	@Override
	public void renderGraphics() {
		// TODO Auto-generated method stub
		Graphics g = getBufferStrategy().getDrawGraphics();

		// Cria um contexto gráfico sem levar em consideração as bordas
		Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth()
				- getInsets().left, getHeight() - getInsets().bottom);

		// Limpa a tela
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		
		// Desenha a snake
		snake.draw((Graphics2D) g2);

		// Desenha a comida
		food.draw((Graphics2D) g2);

		// Libera os contextos criados
		g.dispose();
	}

	@Override
	public void paintScreen() {
		// TODO Auto-generated method stub
		if (!getBufferStrategy().contentsLost())
			getBufferStrategy().show();
	}

	@Override
	public void tearDown() {
		// Esse método só seria realmente necessário se o jogo tivesse mais
		// fases. Seria chamado no final de uma fase.
		snake = null;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameWindow gW = new GameWindow();
				gW.setVisible(true);
				gW.startMainLoop();
			}
		});
	}
	
}
