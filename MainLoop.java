package br.com.mateus;

public class MainLoop implements Runnable {
	public static final int default_ups = 1;
	public static final int default_no_delays_per_yield = 16;
	public static final int default_max_frame_skips = 5;

	private LoopSteps game;
	private long desiredUpdateTime;
	private boolean running;

	private long totalTime;
	private long beforeTime = System.nanoTime();

	private long overSleepTime = 0;
	private long excessTime = 0;

	private int noDelaysPerYield = default_no_delays_per_yield;
	private int maxFrameSkips = default_max_frame_skips;

	int noDelays = 0;

	public MainLoop(LoopSteps game) {
		super();
		this.game = game;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		running = true;
		try {
			game.setup();
			while (running) {
				beforeTime = System.nanoTime();
				// skipFramesInExcessTime();

				game.processLogics();
				game.renderGraphics();
				game.paintScreen();
				totalTime = beforeTime - System.nanoTime();

				if (totalTime <= 150000000) {
					Thread.sleep((150000000 - totalTime) / 1000000L);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Excessão durante o loop", e);
		} finally {
			running = false;
			game.tearDown();
			System.exit(0);
		}
	}

	/*
	 * 
	 */
	public void stop() {
		// TODO Auto-generated method stub
		running = false;
	}

	private void skipFramesInExcessTime() {
		// TODO Auto-generated method stub
		int skips = 0;
		while ((excessTime > desiredUpdateTime) && (skips < maxFrameSkips)) {
			excessTime -= desiredUpdateTime;
			game.processLogics();
			skips++;
		}
	}

}
