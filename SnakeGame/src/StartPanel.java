import java.awt.*;
import javax.swing.*;

public class StartPanel extends JPanel {
	protected Snake snake;
	protected PlayButton startButton;
	
	StartPanel(Snake snake) {
		this.snake = snake;
		
		this.setBounds(0, 0, this.snake.actualWidth, this.snake.actualHeight / 2);
		this.setLayout(null);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		
		g2D.setPaint(Color.black);
		g2D.setFont(new Font("Ink Free", Font.BOLD, 50));
		g2D.drawString("Play a Game", 35, 60);
		g2D.drawString("of Snake?", 65, 110);
	}
}
