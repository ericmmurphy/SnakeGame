import java.awt.event.*;
import javax.swing.*;

public class Snake implements KeyListener {
	protected final int PROGRAM_WIDTH = 350;
	protected final int PROGRAM_HEIGHT = 350;
	protected int actualHeight;
	protected int actualWidth;
	//protected float refreshRate;
	//protected float updateMS;
	protected boolean gameRunning;
	protected boolean initialLaunch;
	protected char currKey;
	protected char prevKey;
	protected JFrame frame;
	protected JPanel panel;
	protected StartPanel startPanel;
	protected SnakePanel snakePanel;
	protected FinishPanel finishPanel;
	protected PlayButton playButton;
	
	Snake() {
		//this.setRefreshVariables();
		
		this.gameRunning = false;
		this.initialLaunch = true;

		this.setPanelValues();
		this.setFrameValues();
		
		this.setActualSizeValues();
		
		this.frame.add(this.panel);
		this.updateSnake();
	}
	
	/*
	// For my information
	public void setRefreshVariables() {
		this.refreshRate = (float)(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate());
		this.updateMS = 1000 / this.refreshRate;
	}
	*/
	
	public void setActualSizeValues() {
		this.actualWidth = frame.getContentPane().getSize().width;
		this.actualHeight = frame.getContentPane().getSize().height;
	}
	
	public void setFrameValues() {
		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.frame.setResizable(false);
	    this.frame.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	    this.frame.setLayout(null);
	    this.frame.setVisible(true);
	}
	
	public void setPanelValues() {
		this.panel = new JPanel();
		this.panel.setBounds(0, 0, PROGRAM_WIDTH, PROGRAM_HEIGHT);
		this.panel.setLayout(null);
	}
	
	public void addPlayButton() {
		this.playButton = new PlayButton(this);
		this.panel.add(this.playButton);
	}
	
	public void removePlayButton() {
		if (this.playButton != null) {
			this.panel.remove(this.playButton);
			this.playButton = null;			
		}
	}
	
	public void addStartPanel() {
		this.startPanel = new StartPanel(this);
		this.panel.add(this.startPanel);		
	}
	
	public void removeStartPanel() {
		if (this.startPanel != null) {
			this.panel.remove(this.startPanel);
			this.startPanel = null;			
		}
	}
	
	public void addFinishPanel() {
		this.finishPanel = new FinishPanel(this);
		this.panel.add(this.finishPanel);		
	}
	
	public void removeFinishPanel() {
		if (this.finishPanel != null) {
			this.panel.remove(this.finishPanel);
			this.finishPanel = null;			
		}
	}
	
	public void addSnakePanel() {
		this.snakePanel = new SnakePanel(this);
		this.panel.add(this.snakePanel);
	}
	
	public void removeSnakePanel() {
		if (this.snakePanel != null) {
			this.panel.remove(this.snakePanel);
			this.snakePanel = null;
		}
	}
	
	public void updatePanel() {
		this.panel.revalidate();
		this.panel.repaint();
	}
	
	public void changeGameRunningStatus() {
		this.gameRunning = !this.gameRunning;
	}
	
	public void changeInitialLaunchStatus() {
		this.initialLaunch = !this.initialLaunch;
	}
	
	public void updateSnake() {
		UpdateSnake update = new UpdateSnake(this);
		Thread thread = new Thread(update);
		thread.start();
	}
	
	public void setInitialKeyChars() {
		this.currKey = 'd';
		this.prevKey = 'd';
	}
	
	public void nextKeyCharWindow() {
		this.prevKey = this.currKey;
	}
	
	public void addKeyListener() {
		this.frame.addKeyListener(this);
	}
	
	public void removeKeyListener() {
		this.frame.removeKeyListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent event) {
		char keyChar = event.getKeyChar();
		
		if (keyChar == 'w' && this.prevKey != 's') {
			this.currKey = 'w'; 
		} else if (keyChar == 's' && this.prevKey != 'w') {
			this.currKey = 's';
		} else if (keyChar == 'a' && this.prevKey != 'd') {
			this.currKey = 'a';
		} else if (keyChar == 'd' && this.prevKey != 'a') {
			this.currKey = 'd';
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
