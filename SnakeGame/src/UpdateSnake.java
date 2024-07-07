public class UpdateSnake implements Runnable {
	protected Snake snake;
	
	UpdateSnake(Snake snake) {
		this.snake = snake;
	}

	@Override
	public void run() {
		if (this.snake.gameRunning) {
			this.snake.removeStartPanel();
			this.snake.removeFinishPanel();
			this.snake.removePlayButton();
			this.snake.updatePanel();
			
			try {
				//Thread.sleep((long)(Math.floor((double)this.snake.updateMS) + 1));
				Thread.sleep(66, 666666);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.snake.setInitialKeyChars();
			this.snake.addKeyListener();
			this.snake.addSnakePanel();
			this.snake.updatePanel();
		}

		long prevTime = System.nanoTime();
		long currTime = System.nanoTime();
		long elapsedTime = 0;
		long totalNS = 66666666; // TIME BETWEEN FRAMES (15FPS)
		//long totalNS = (long)(this.snake.updateMS * 1000000); // TIME BETWEEN FRAMES (UNRESTRICTED FPS)
		
		while (this.snake.gameRunning) {
			elapsedTime += currTime - prevTime;
			
			if (elapsedTime >= totalNS) {
				// Update last coords
				this.snake.snakePanel.updateLastCoords();
				
				// Move snake
				if (this.snake.currKey == 'w') {
					this.snake.snakePanel.moveUp();
				} else if (this.snake.currKey == 's') {
					this.snake.snakePanel.moveDown();
				} else if (this.snake.currKey == 'a') {
					this.snake.snakePanel.moveLeft();
				} else if (this.snake.currKey == 'd') {
					this.snake.snakePanel.moveRight();
				}
				
				// Detect Game-Ending Collisions
				if (this.snake.snakePanel.detectBorderCollision() || this.snake.snakePanel.detectSnakeCollision()) {
					this.snake.changeGameRunningStatus();
				}
				
				// Detect Food Collisions
				if (this.snake.snakePanel.detectFoodCollision()) {
					this.snake.snakePanel.growSnake();
					this.snake.snakePanel.generateFoodLocation();
				}
								
				this.snake.nextKeyCharWindow();
				this.snake.snakePanel.updateSnakePanel();
				elapsedTime = elapsedTime % totalNS;
			}
			
			try {
				Thread.sleep(6, 666666);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			prevTime = currTime;
			currTime = System.nanoTime();
		}
		this.snake.removeKeyListener();
		this.snake.removeSnakePanel();
		
		// Swap from SnakePanel Screen
		if (this.snake.initialLaunch) {
			this.snake.addStartPanel();
			this.snake.addPlayButton();
			this.snake.changeInitialLaunchStatus();
		} else {
			this.snake.addFinishPanel();
			this.snake.addPlayButton();
			this.snake.updatePanel();
		}
	}
}
