import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class PlayButton extends JButton implements ActionListener {
	protected Snake snake;
	protected final int START_BUTTON_WIDTH = 85;
	protected final int START_BUTTON_HEIGHT = 45;
	
	PlayButton(Snake snake) {
		this.snake = snake;
		this.setStartButtonValues();
	}
	
	public void setStartButtonValues() {
		int xStart = (this.snake.actualWidth / 2) - (this.START_BUTTON_WIDTH / 2);
		int yStart = (this.snake.actualHeight / 2) - (this.START_BUTTON_HEIGHT / 2);
		
		this.setText("PLAY");
		this.setFont(new Font("Ink Free", Font.BOLD, 20));
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setVerticalAlignment(SwingConstants.BOTTOM);
		this.setFocusable(false);
		this.setBounds(xStart, yStart, this.START_BUTTON_WIDTH, this.START_BUTTON_HEIGHT);
		this.setLayout(null);
		this.addListener();
	}
	
	public void addListener() {
		this.addActionListener(this);
	}
	
	public void removeListener() {
		this.removeActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this) {
			this.removeListener();
			this.snake.changeGameRunningStatus();
			this.snake.updateSnake();
		}
	}
}
