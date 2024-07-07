import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SnakePanel extends JPanel {
	protected Snake snake;
	protected LinkedList<Point> points;
	protected Point food;
	protected int lastX;
	protected int lastY;
	
	SnakePanel(Snake snake) {
		this.snake = snake;
		this.initializePoints();
		this.generateFoodLocation();
				
		this.setBounds(0, 0, this.snake.actualWidth, this.snake.actualHeight);
		this.setLayout(null);
	}
	
	public void initializePoints() {
		this.points = new LinkedList<>();
		
		this.points.addFirst(new Point((this.snake.actualWidth / 2) - 6, (this.snake.actualHeight / 2) + 6));
		this.points.addFirst(new Point((this.snake.actualWidth / 2) - 6, this.snake.actualHeight / 2));
		this.points.addFirst(new Point(this.snake.actualWidth / 2, this.snake.actualHeight / 2));
		this.points.addFirst(new Point((this.snake.actualWidth / 2) + 6, this.snake.actualHeight / 2));
		this.points.addFirst(new Point((this.snake.actualWidth / 2) + 6, (this.snake.actualHeight / 2) - 6));
		
		// BORDER COLLISION
		//this.points.add(new Point(5, 5));
		//this.points.add(new Point(this.snake.actualWidth - 5, this.snake.actualHeight - 5));
	}
	
	public void updateLastCoords() {
		Point last = this.points.getLast();
		this.lastX = last.x;
		this.lastY = last.y;
	}
	
	public void moveLeft() {
		boolean initialRun = true;
		int prevX = 0, prevY = 0, tempX = 0, tempY = 0;
		
		for (Point point : this.points) {
			if (initialRun) {
				prevX = point.x;
				prevY = point.y;
				point.x -= 6;
				initialRun = !initialRun;
			} else {
				tempX = prevX;
				tempY = prevY;
				prevX = point.x;
				prevY = point.y;
				point.x = tempX;
				point.y = tempY;
			}
		}
	}
	
	public void moveRight() {
		boolean initialRun = true;
		int prevX = 0, prevY = 0, tempX = 0, tempY = 0;
		
		for (Point point : this.points) {
			if (initialRun) {
				prevX = point.x;
				prevY = point.y;
				point.x += 6;
				initialRun = !initialRun;
			} else {
				tempX = prevX;
				tempY = prevY;
				prevX = point.x;
				prevY = point.y;
				point.x = tempX;
				point.y = tempY;
			}
		}
	}
	
	public void moveUp() {
		boolean initialRun = true;
		int prevX = 0, prevY = 0, tempX = 0, tempY = 0;
		
		for (Point point : this.points) {
			if (initialRun) {
				prevX = point.x;
				prevY = point.y;
				point.y -= 6;
				initialRun = !initialRun;
			} else {
				tempX = prevX;
				tempY = prevY;
				prevX = point.x;
				prevY = point.y;
				point.x = tempX;
				point.y = tempY;
			}
		}
	}
	
	public void moveDown() {
		boolean initialRun = true;
		int prevX = 0, prevY = 0, tempX = 0, tempY = 0;
		
		for (Point point : this.points) {
			if (initialRun) {
				prevX = point.x;
				prevY = point.y;
				point.y += 6;
				initialRun = !initialRun;
			} else {
				tempX = prevX;
				tempY = prevY;
				prevX = point.x;
				prevY = point.y;
				point.x = tempX;
				point.y = tempY;
			}
		}
	}
	
	public void growSnake() {
		this.points.addLast(new Point(this.lastX, this.lastY));
	}
	
	public void generateFoodLocation() {
		Point randPoint = null;
		do {
			randPoint = generateRandomPoint();
		} while (!isPointValid(randPoint));
		this.food = randPoint;
	}
	
	public Point generateRandomPoint() {
		int centerX = this.snake.actualWidth / 2; // 167
		int centerY = this.snake.actualHeight / 2; // 155
		int maxRandX = this.snake.actualWidth - 5 - 6;
		int maxRandY = this.snake.actualHeight - 5 - 6;
		
		Random random = new Random();
		int foodX = random.nextInt(maxRandX) + 6;
		int foodY = random.nextInt(maxRandY) + 6;
		
		int diffX = (int)(Math.floor((double)(Math.abs(centerX - foodX) / 6))) * 6;
		int diffY = (int)(Math.floor((double)(Math.abs(centerY - foodY) / 6))) * 6;
		
		if (foodX <= centerX) {
			foodX = centerX - diffX;
		} else {
			foodX = centerX + diffX;
		}
		
		if (foodY <= centerY) {
			foodY = centerY - diffY;
		} else {
			foodY = centerY + diffY;
		}
		
		return new Point(foodX, foodY);
	}
	
	public boolean isPointValid(Point randPoint) {
		int randX = randPoint.x, randY = randPoint.y;
		int pointX, pointY;
		
		for (Point point : this.points) {
			pointX = point.x;
			pointY = point.y;
			
			if (randX == pointX && randY == pointY) {
				return false;
			}
		}
		return true;
	}
	
	public boolean detectBorderCollision() {
		Point head = this.points.getFirst();
		if (head.x <= 5 || head.x >= this.snake.actualWidth - 5 || head.y <= 5 || head.y >= this.snake.actualHeight - 5) {
			return true;
		}
		return false;
	}
	
	public boolean detectSnakeCollision() {
		Point head = null;
		int headX = 0, headY = 0, pointX = 0, pointY = 0;
		
		for(Point point : this.points) {
			if (head != null) {
				pointX = point.x;
				pointY = point.y;
				
				if (headX == pointX && headY == pointY) {
					return true;
				}
			} else {
				head = point;
				headX = head.x;
				headY = head.y;
			}
		}
		return false;
	}
	
	public boolean detectFoodCollision() {
		Point head = this.points.getFirst();
		int headX = head.x, headY = head.y, foodX = this.food.x, foodY = this.food.y;
		
		if (headX == foodX && headY == foodY) {
			return true;
		}
		return false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;

		super.paintComponent(g);
		this.drawBorder(g2D);
		for (Point point : this.points) {
			this.drawPoint(g2D, point);
		}
		this.drawPoint(g2D, this.food);
	}
	
	public void drawPoint(Graphics2D g2D, Point point) {
		int[] xPoints = {point.x - 3, point.x + 3, point.x + 3, point.x - 3};
		int[] yPoints = {point.y - 3, point.y - 3, point.y + 3, point.y + 3};
				
		g2D.setPaint(Color.black);
		g2D.fillPolygon(xPoints, yPoints, 4);
	}
	
	public void drawBorder(Graphics2D g2D) {
		g2D.setPaint(Color.black);
		g2D.setStroke(new BasicStroke(5));
		
		g2D.drawLine(0, 0, this.snake.actualWidth - 1, 0); // Top
		g2D.drawLine(0, 0, 0, this.snake.actualHeight - 1); // Left
		g2D.drawLine(this.snake.actualWidth - 1, 0, this.snake.actualWidth - 1, this.snake.actualHeight - 1); // Right
		g2D.drawLine(0, this.snake.actualHeight - 1, this.snake.actualWidth - 1, this.snake.actualHeight - 1); // Bottom
	}
	
	public void updateSnakePanel() {
		this.repaint();
	}
}
