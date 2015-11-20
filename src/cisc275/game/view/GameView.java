package cisc275.game.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import cisc275.game.controller.Action;
import cisc275.game.controller.GameListener;
import cisc275.game.controller.Player;
import cisc275.game.model.Crab;
import cisc275.game.model.Game;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JFrame implements GameListener<Game>, Runnable, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3555168036929980855L;
	//game constants
	private static final int WORLD_WIDTH = 1366;
	private static final int WORLD_HEIGHT = 768;
	private static final int SCALE = 1;
	public static String title = "Estuary Defense";
	private JFrame frame;
	int deletenum = -1; //with use of crabs
	static ArrayList<CrabView> crabs = new ArrayList<CrabView>();//array of crabviews
	private static GameView gameView = null;
	public int imgHeight;
	public int imgWidth;
	
	private Player player;
	//private Key, Mouse?
	private boolean running = false;
	private BufferedImage image = new BufferedImage(getWorldWidth(), getWorldHeight(), BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	// # of milliseconds between state updates, probably will be 
	//important when we figure out how to loop game
	private long speed; 	
	//number of rows and columns in the "world"
	private int rows;
	private int cols;
	//this level and the list of all possible levels
	private int level;
	
	Image crab;
	Image mcrab;
	Image garbage;
	Image plant;
	Image garbageCollector;
	
	private JPanel gamePanel, buttonPanel;
	private CardLayout gv1;
	private GridBagLayout gb1;
	private SimpleModel simpleModel = new SimpleModel();
	
	private InstructionsView instructionsView;
	private SplashScreen splashScreen;
   	
	public GameView() {
		splashScreen = SplashScreen.getInstance();
		instructionsView = InstructionsView.getInstance();
		
		splashScreen.setModel(simpleModel);
		instructionsView.setModel(simpleModel);
		
		gamePanel = locationPanel();
		gv1 = new CardLayout();
		this.setLayout(gv1);
		//this.add(buttonPanel, "South");
		this.add(gamePanel, "1");
		this.add(splashScreen, "2");
		this.add(instructionsView, "3");
		//gv1.show(getContentPane(), "1");
		gv1.show(gamePanel.getParent(), "1");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel locationPanel() {
		final Image image = createImage();
		JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWorldWidth(), getWorldHeight(), null);
                
            }
        };
		gb1 = new GridBagLayout();
		panel.setLayout(gb1);
		panel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		System.out.println("clicked: "+arg0.getX()+","+arg0.getY());
        	}
        });
		Dimension size = new Dimension(getWorldWidth()*getScale(), getWorldHeight()*getScale()); // create window dimension
		panel.setMinimumSize(size); // set window dimension
		
		JLabel name = new JLabel("WELCOME TO ESTUARY DEFENSE!");
		addGridItem(panel,name,0,0,1,1,GridBagConstraints.CENTER,new Insets(80,100,40,100));
		JButton button1 = new JButton("Start");
		button1.addActionListener(this);
		button1.setActionCommand("Open");
		addGridItem(panel,button1,0,1,1,1,GridBagConstraints.CENTER,new Insets(80,100,5,100));
		JButton button2 = new JButton("Tutorial");
		button2.addActionListener(this);
		button2.setActionCommand("OpenTut");
		addGridItem(panel,button2,0,2,1,1,GridBagConstraints.CENTER,new Insets(5,100,80,100));	
		
		return panel;
	}
	
	private void addGridItem(JPanel panel, JComponent comp, int x, int y, int width, int height, int align, Insets padding) {
		GridBagConstraints gcon = new GridBagConstraints();
		gcon.gridx = x;
		gcon.gridy = y;
		gcon.gridwidth = width;
		gcon.gridheight = height;
		gcon.weightx = 0.5;
		gcon.weighty = 0.5;
		gcon.insets = padding;
		gcon.anchor = align;
		gcon.fill = GridBagConstraints.NONE;
		panel.add(comp, gcon);
	}
	
	private class NextButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			gv1.next(gamePanel);
		}
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	public int getlevel(){
		return level;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	//sets the list of available levels
	void setLevel() {
	}		
	public static void getLevel(){			
	}
	/**
	 * @return prints the current level, money, and pH
	 */
	public String getStatus() {
		return null; 
    }
	/**
	 * acts as onTick
	 */
	void update() {
	}
	//no idea what these two do
	void remove() {
	}
	void render() {
	}
	//if sewage has stopped and game is not ended advance to next level
	void nextlevel() {
	}
    protected BufferedImage createImage() {
        BufferedImage bufferedImage;
        try {
        	//image=ImageIO.read(file);
            bufferedImage = ImageIO.read(new File("images/BackImg3.jpg"));
            imgHeight=bufferedImage.getHeight();
            imgWidth=bufferedImage.getWidth();
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	@Override
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
	      if(cmd.equals("Open")){
	            //getContentPane().removeAll();//dispose();
	            //getContentPane().add(splashScreen.getInstance());
	    	  	gv1.show(splashScreen.getParent(),  "2");
	            //gameView.remove(buttonPanel);
	            //pack();
	        }
	      if(cmd.equals("OpenTut")){
				//getContentPane().removeAll();
				//getContentPane().add(instructionsView.getInstance());
				gv1.show(instructionsView.getParent(), "3");
				//gameView.remove(buttonPanel);
				//pack();
			}
	    }
    public static int rando(){
    	Random rnd = new Random();
    	return(rnd.nextInt(100));
    }
	void drawGarbage() {
	}
	void drawPlants() {
	}
	void drawGarbageCollectors() {
	}
	void drawHills() {
	}
	void drawWater() {
	}
	void drawHealthBar() {
	}
	void drawMoneyBar() {
	}
	void playSound() {
	}
	@Override
	public void onPerformActionEvent(Action<Game> action, Game game) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onTickEvent(Game game) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onStartEvent(Game game) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onEndEvent(Game game) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onEvent(String event, Game game) {
		// TODO Auto-generated method stub
	}
//	public synchronized void start() {
//		running = true;
//		thread = new Thread(this, "Display");
//		thread.start();
//	}
	
//	public synchronized void stop() {
//		running = false;
//		try {
//			thread.join();
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.out.println("Try/catch failure in GameView.stop()");
//		}
//	}
	
//	@Override
//	public void run() {
//		long lastTime = System.nanoTime();
//		long timer = System.currentTimeMillis();
//		final double ns = 1000000000.0 / 60.0;
//		double delta = 0;
//		int frames = 0;
//		int updates = 0;
//		frame.requestFocus();
//		while (running) {
//			long now = System.nanoTime();
//			delta += (now - lastTime) / ns;
//			lastTime = now;
//			while (delta >= 1) {
//				update();
//				updates++;
//				delta--;
//			}
//			render();
//			frames++;
//			
//			if (System.currentTimeMillis() - timer > 1000) {
//				timer += 1000;
//				// System.out.println(updates+" UPS, "+frames+" FPS");
//				frame.setTitle(title+"  |  "+updates+" UPS, "+frames+" FPS");
//				updates = 0;
//				frames = 0;
//			}
//		}
//		stop();
//	}
	public static int getWorldWidth() {
		return WORLD_WIDTH;
	}
	public static int getWorldHeight() {
		return WORLD_HEIGHT;
	}
	public static int getScale() {
		return SCALE;
	}
	public static void main(String[] args) { //move to view, windows are central thread of game
		SwingUtilities.invokeLater(new Runnable(){	
			@Override
			public void run() {
				if (gameView == null) {
					gameView = new GameView();
					gameView.setVisible(true);
				}
			}
		});
	}
	@Override
	public void run() {
		if (gameView == null) {
			gameView = new GameView();
			gameView.setVisible(true);
		}
	}
}


