package cisc275.game.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import cisc275.game.model.Crab;
import cisc275.game.model.Fisherman;
import cisc275.game.model.Game;
import cisc275.game.model.Water;

/**
 * @author Team 6
 * Creates main panel where the game is played
 * Handles all rendering of game objects
 */
public class SplashScreen extends ViewTemplate implements ActionListener, MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int TIMER_DELAY = 50;
	int timer = 0;
	private static JLayeredPane panel2;
	int deletenum = -1; //with use of crabs
	int deletenumWater = -1; // with water
	int deletenumFM = -1; // with fisherman
	static ArrayList<Crab> crabs = new ArrayList<Crab>();//array of crabviews
	static ArrayList<CrabView> crabss = new ArrayList<CrabView>();
	static ArrayList<PlantView> plants = new ArrayList<PlantView>();
	static ArrayList<Water> waterTiles = new ArrayList<Water>();
	static ArrayList<Fisherman> fms = new ArrayList<Fisherman>();
	static ArrayList<GarbageCollectorView> garbColl = new ArrayList<GarbageCollectorView>();
	public static boolean crabby = false;
	boolean intersection = false;
    JLabel cloud = new JLabel("cloud");
	int numpics = 10;
	private JButton pbutton, gcbutton;
	public int crabcount = 0;
	public int watercount = 1;
	public int fmcount = 1;
	private BufferedImage pics[];
	private enum click {
		plant1, plant2, plant3, gC1, gC2, gC3, norm
	}
	private click isClicked = click.norm;
	
	/**
	 * Splash screen constructor, creates panel where the game is played
	 */
	public SplashScreen() {
		PlantView.InitializePictures();
		CrabView.InitializePicturesC();
		GarbageCollectorView.InitializeGarbage();
		Water.InitializePicturesW();
		Fisherman.InitializePicturesF();
		pics = new BufferedImage[numpics];
    	BufferedImage bi = InstanceView.createImage("images/BackTrial1.png");
//    	BufferedImage plant1 = createImage("images/Fern.png");
//    	BufferedImage GarbCol = createImage("images/Squirrel/Squirrel1.png");
    	BufferedImage Cloud = InstanceView.createImage("images/cloud.png");
    	BufferedImage bi2 = InstanceView.createImage("images/back1_pipe_2.png");
    	BufferedImage garb = InstanceView.createImage("images/trash.png");
//    	BufferedImage water1 = createImage("images/textures/water_map.png");
//    	BufferedImage fm1 = createImage("images/boatman.png");
//    	System.out.print("PrintPics");
    	pics[0] = bi;
//    	pics[1] = plant1;
//    	pics[2] = GarbCol;
    	pics[1] = Cloud;
    	pics[2] = bi2;
    	pics[3] = garb;
//    	pics[5] = water1;
//    	pics[6] = fm1;
    	  	
     	this.panel2 = GameFrame();
	}
	
//------Getters and Setters--------------------------------------------//
	/**
	 * @return panel for splash screen
	 */
	public static JLayeredPane getPanel2() {
		return panel2;
	}
	/**
	 * @return button for placing plants
	 */
	public JButton getPButton() {
		return this.pbutton;
	}
	/**
	 * @return button for placing garbage collectors
	 */
	public JButton getGCButton() {
		return this.gcbutton;
	}
	
//------Fisherman Methods----------------------------------------------//	
	/**
	 * Currently not implemented
	 */
	void initialize() {
	}
	/**
	 * Currently not implemented
	 */
	void onClick() {
	}
	/**
	 * Currently not implemented
	 */
	void inValidate() {
	}
	/**
	 * @return initialized panel for splash screen
	 */
	public JLayeredPane GameFrame(){
		new Timer(TIMER_DELAY, new TimerListener()).start();
        panel2 = new JLayeredPane() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(pics[0], 0, 0, WORLD_WIDTH, WORLD_HEIGHT, null);
    		Rectangle Hbar = new Rectangle((int)(WORLD_WIDTH * 0.7), (int)(WORLD_HEIGHT * 0.03), 20, 40);
    		Rectangle Mbar = new Rectangle((int)(WORLD_WIDTH * 0.45), (int)(WORLD_HEIGHT * 0.03), 20, 40);
    		Rectangle Cbar = new Rectangle((int)(WORLD_WIDTH * 0.2), (int)(WORLD_HEIGHT * 0.03), 20, 40);
    		int health = fms.get(0).getpH();
    		int money = fms.get(0).getMoney();
    		int crabs = CrabView.nativelimit;
    		// health bar gray background
    		g.setColor(Color.GRAY);
    		g.drawRoundRect(Mbar.x, Mbar.y, Mbar.width, Mbar.height, 15, 15);
    		g.fillRoundRect(Mbar.x, Mbar.y, Mbar.width, Mbar.height, 15, 15);
    		g.drawRoundRect(Hbar.x, Hbar.y, Hbar.width, Hbar.height, 15, 15);
    		g.fillRoundRect(Hbar.x, Hbar.y, Hbar.width, Hbar.height, 15, 15);
    		g.drawRoundRect(Cbar.x, Cbar.y, Cbar.width, Cbar.height, 15, 15);
    		g.fillRoundRect(Cbar.x, Cbar.y, Cbar.width, Cbar.height, 15, 15);
    		if(money > 150){
    			g.setColor(Color.GREEN);
    		}
    		if(money > 50 && money < 150){
    			g.setColor(Color.YELLOW);
    		}
    		if(money < 50){
    			g.setColor(Color.RED);
    		}
    		g.fillRoundRect(Mbar.x, Mbar.y, 200 , Mbar.height, 15, 15);
    		g.setColor(Color.BLACK);
    		g.setFont(new Font("Purisa", Font.BOLD, 22));
    		g.drawString("Money: " + fms.get(0).getMoney(), Mbar.x, Mbar.y );
    	
    		if (health < 50) {
    			g.setColor(Color.RED);
    		}
    		else if (health < 150) {
    			g.setColor(Color.YELLOW);
    		}
    		else {
    			g.setColor(Color.GREEN);
    		}
    		g.fillRoundRect(Hbar.x, Hbar.y, 200 , Hbar.height, 15, 15);
    		g.setColor(Color.BLACK);
    		g.setFont(new Font("Purisa", Font.BOLD, 22));
    		g.drawString("pH: " + fms.get(0).getpH()/25, Hbar.x , Hbar.y );
    		
    		if (crabs < 5) {
    			g.setColor(Color.GREEN);
    		}
    		else {
    			g.setColor(Color.RED);
    		}
    		g.fillRoundRect(Cbar.x, Cbar.y, 200 , Cbar.height, 15, 15);
    		g.setColor(Color.BLACK);
    		g.setFont(new Font("Purisa", Font.BOLD, 22));
    		g.drawString("Crabs Caught: " + crabs, Cbar.x , Cbar.y );
    		};
    };
    //crabs.add(new Crab()); // creates initial crab
    waterTiles.add(new Water(new Point (ViewTemplate.scalex(575),ViewTemplate.scaley(280)), ViewTemplate.scaley(100), 5, Color.BLUE, 1.0));
    fms.add(new Fisherman(new Point (ViewTemplate.scalex(1200),ViewTemplate.scaley(700)), new Point (ViewTemplate.scalex(100),ViewTemplate.scaley(700)), 0, 200));
    panel2.addMouseListener(new MouseAdapter() { //change to addMouseMotionListener if using drag 
        private Color background;
        @Override
        public void mouseClicked(MouseEvent e) {
        	Point loc = new Point(e.getX(), e.getY()); //e.getLocationOnScreen();
        	switch (isClicked) {
        	case norm:
        		System.out.println(loc);
        		break;
        	case plant1:
        		loc.setLocation(loc.getX()-ViewTemplate.scalex(30), loc.getY()-ViewTemplate.scaley(30));
        		PlantView tempplant = new PlantView(1, loc);
        		PlantView.checkbuffer(tempplant);
        		getPanel2().add(tempplant.pbutton);
        		plants.add(tempplant);
        		fms.get(0).setMoney(fms.get(0).getMoney() - 10);
    			crabby = true;
    			isClicked = click.norm;
    			break;   		
    		case plant2:
    			paintPlantComponent(e.getComponent().getGraphics(), loc);
    			break;    			
    		case plant3:
    			paintPlantComponent(e.getComponent().getGraphics(), loc);
    			break;    			
    		case gC1:
    			loc.setLocation(loc.getX()-30, loc.getY()-30);
        		GarbageCollectorView tempgc = new GarbageCollectorView(loc);
        		getPanel2().add(tempgc.gcbutton);
        		garbColl.add(tempgc);
        		fms.get(0).setMoney(fms.get(0).getMoney() - 10);
    			isClicked = click.norm;
    			crabby = true;
    			break;    			
    		case gC2:
    			paintGarbageCollectorComponent(e.getComponent().getGraphics(), loc);
    			break;    			
    		case gC3:
    			paintGarbageCollectorComponent(e.getComponent().getGraphics(), loc);
    			break;
        	}
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            setBackground(background);
        }
    });	
    pack();
    setVisible(true);
    
	Dimension size = new Dimension(WORLD_WIDTH, WORLD_HEIGHT); // create window dimension
	panel2.setPreferredSize(size); // set window dimension
	panel2.setBorder(BorderFactory.createLineBorder(Color.blue)); // creates a border, not really needed
	panel2.setLayout(null); // default layout is Flowlayout, we need to decide what we want

	BufferedImage plant1 = PlantView.pics[0]; //"images/Fern.png"
	pbutton = new JButton();
	Dimension size2 = pbutton.getPreferredSize();
	pbutton.setBounds(ViewTemplate.scalex(1250), ViewTemplate.scaley(100),  ViewTemplate.scalex(100), ViewTemplate.scaley(100));
	pbutton.setIcon(new ImageIcon(plant1.getScaledInstance(ViewTemplate.scalex(100), ViewTemplate.scaley(100), Image.SCALE_DEFAULT)));
	pbutton.addActionListener(this);
	pbutton.setActionCommand("Plant");
	pbutton.setBorderPainted(true);
	pbutton.setFocusPainted(false);
	pbutton.setContentAreaFilled(false);
	System.out.println("SCALE\n"+widthscale+","+heightscale);
	System.out.println("RESOLUTION\n"+WORLD_WIDTH+","+WORLD_HEIGHT);
	System.out.println("actual scale\n"+WORLD_WIDTH/1366+","+WORLD_HEIGHT/768);
	BufferedImage GarbCol = GarbageCollectorView.pics[0]; //"images/Squirrel/Squirrel1.png"
	gcbutton = new JButton();
	Dimension size3 = gcbutton.getPreferredSize();
	gcbutton.setBounds(ViewTemplate.scalex(1250), ViewTemplate.scaley(200),  ViewTemplate.scalex(100), ViewTemplate.scaley(100));
	gcbutton.setIcon(new ImageIcon(GarbCol.getScaledInstance(ViewTemplate.scalex(100), ViewTemplate.scaley(100), Image.SCALE_DEFAULT)));
	gcbutton.addActionListener(this);
	gcbutton.setActionCommand("Garbage Collector");
	gcbutton.setBorderPainted(true);
	gcbutton.setFocusPainted(false);
	gcbutton.setContentAreaFilled(false);
	
	BufferedImage Cloud = pics[1]; //"images/cloud.png"
	Dimension size4 = cloud.getPreferredSize();
	cloud.setIcon(new ImageIcon(Cloud.getScaledInstance(100, 100, 20)));
	//TODO: change this
	//if (run == true){
	cloud.setBounds(500, 50, 100, 150);
	//theX+=xincr;
	//}
	
	panel2.add(pbutton);
	panel2.add(gcbutton);
    return panel2;
    }
    /**
     * @param g - graphics for rendering
     * @param loc - location to render plant
     * Paints a plant to the panel at the specified location
     */
    protected void paintPlantComponent(Graphics g, Point loc ) {    
    	BufferedImage plant = PlantView.pics[0];
    	g.drawImage(plant, (int)loc.getX()-33, (int)loc.getY()-36, 75, 75, null); //TODO:Move hard coded 30 pixels offset elsewhere for loading plants
    }
    /**
     * @param g - graphics for rendering
     * @param loc - location to render plant
     * Paints a garbage collector to the panel at the specified location
     */
    protected void paintGarbageCollectorComponent(Graphics g, Point loc ) {    
    	BufferedImage GarbC = pics[2];
    	g.drawImage(GarbC, (int)loc.getX()-33, (int)loc.getY()-36, 75, 75, null); //TODO:Move hard coded 30 pixels offset elsewhere for loading plants
    }
    
    protected void paintGarbageComponent(Graphics g, Point loc) {
    	BufferedImage garb = pics[3];
    	g.drawImage(garb, (int)loc.getX()-33, (int)loc.getY()-36, 75, 75, null);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Plant")){
			//PlaceObject placeplant = new PlaceObject();
			//we might not want this here, but this could invoke a call to update
			//which could then place a plant at the next clicked location based on the 
			//next mouse click that still has this button enabled
			isClicked = click.plant1;
			crabby = false;
			getPButton().setBorderPainted(true);
			getGCButton().setBorderPainted(false);
        } else if(cmd.equals("Garbage Collector")){
			//PlaceObject placegc = new PlaceObject();
			//same thing here. maybe just set the button enable and then 
			//invoke a call to update based on a click action listener which
			//would give the location to place
        	isClicked = click.gC1;
        	crabby = false;
        	getGCButton().setBorderPainted(true);
        	getPButton().setBorderPainted(false);
		} 
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		isClicked = click.norm;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
    public static int rando(){
    	Random rnd = new Random();
    	return(rnd.nextInt(100));
    }
	@Override
	public void mouseDragged(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseMoved(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	/**
	 * @author Team 6
	 * Timer for handling game updates
	 */
	private class TimerListener implements ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent e) {
        	if(crabby == true && SplashScreen.crabby == true){
        		//System.out.println("test");
               paintcrab();
               paintwater();
               paintfm();
               plantcheck();
                //System.out.println("test6");
//                for(CrabView c: crabs){
//             	   frame.remove(c.cbutton);
//            	}
                if(rando() == 1 && crabcount < 50){ //randomly makes a crab (1/50 chance)
                	crabs.add(new Crab(crabs.size()));
                	int index = crabs.size()-1;
            		crabss.add(new CrabView(crabs.get(index)));
            		crabcount += 1;
            		panel2.add(crabss.get(index).cbutton);
            		//System.out.println("CRAB CREATED: " + crabcount);
            		}
                timer +=1;
                if(timer == 30 && watercount < 50){ //randomly makes a crab (1/50 chance)
            		waterTiles.add(new Water(new Point (ViewTemplate.scalex(575),ViewTemplate.scaley(280)), ViewTemplate.scaley(100), 5, Color.BLUE, 1.0));
            		if(fmcount < 5) {
            			fms.add(new Fisherman(new Point (ViewTemplate.scalex(1200),ViewTemplate.scaley(700)), new Point (ViewTemplate.scalex(100),ViewTemplate.scaley(700)), 0, 200));
            			fmcount += 1;
            		}
            		watercount += 1;
            		timer = 0;
            		}
        	}
        };
     }
	//a lot of this seems like game logic
	/**
	 * Handles array list of crabs
	 */
	public void paintcrab() {
		int index = 0;
    	for(Crab c: crabs){
    		
    		if(c.removel == true){ //checks if crab needs to be removed
    			deletenum = crabs.indexOf(c);
    			Game.nativelimit++;
    			//System.out.println("deletenum");
    		}
    		c.move();
    		//System.out.println("CRAB INDEX " + index);
    		crabss.get(index).paintcrab();
    		getPanel2().remove(crabss.get(index).cbutton);
    		getPanel2().add(crabss.get(index).cbutton);
    		getPanel2().repaint();
    		index++;
    	}
    	if(deletenum != -1){ //removes crab
    		if(crabs.get(deletenum).isMitten()!= true){
    			if(Game.nativelimit > 6){
    				fms.get(0).setMoney(fms.get(0).getMoney() - 10);
    			}
    			else{
    				fms.get(0).setMoney(fms.get(0).getMoney() + 10);
    			}
    		}
    		getPanel2().remove(crabss.get(deletenum).cbutton);
    		
    		crabss.remove(deletenum);
    		crabs.remove(deletenum);
    		crabcount -=1;
    		deletenum = -1;
    	}
    }
	/**
	 * Handles array list of water
	 */
	public void paintwater() {
    	for(Water w: waterTiles){
    		if(!w.isStopping()){
    			w.move();
    		}
    		if(w.getRemoved() == true){ 
    			deletenumWater = waterTiles.indexOf(w);
    		}
    		w.paintWater();
    		getPanel2().remove(w.getWbutton());
    		getPanel2().add(w.getWbutton());
    		
    	}
    	if(deletenumWater != -1){ 
    		getPanel2().remove(waterTiles.get(deletenumWater).getWbutton());
    		waterTiles.remove(deletenumWater);
    		watercount -=1;
    		deletenumWater = -1;
    	}
    }
	/**
	 * Handles array list of fisherman
	 */
	public void paintfm() {
    	for(Fisherman fm: fms){
    		if(!fm.isRemoved()){
    			fm.move();
    		}
    		else { 
    			deletenumFM = fms.indexOf(fm);
    		}
    		fm.paintFM();
    		getPanel2().remove(fm.getFLabel());
    		getPanel2().add(fm.getFLabel());
    	}
    	if(deletenumFM != -1){ 
    		getPanel2().remove(fms.get(deletenumFM).getFLabel());
    		fms.remove(deletenumFM);
    		fmcount -=1;
    		deletenumFM = -1;
    	}
    }
	/**
	 * Checks for plant/water collisions
	 */
	void plantcheck(){
		for(PlantView p:plants){
			for(Water w:waterTiles){
				if(!p.intersecting){
					if(p.checkintersectw(w)){
						if(p.buffer){
							w.setStopping(true);
						}
						else if(!w.affected.contains(p)){
							w.shrink(p);
						}
					}
				}
				else if(w.affected.contains(p)){
					w.normal(p);
				}
			}
			p.intersecting = false;
			int index= 0;
			for(Crab c:crabs){
				if(c.planta == null){
					if(p.checkintersects(crabss.get(index).cbutton)){
						if(c.isMitten() & !p.buffer){
						c.setStop(true);
						c.planta = p;
						p.intersecting = true;
						p.changepic(2);
						getPanel2().remove(p.pbutton);
						getPanel2().add(p.pbutton);
						}
						else{
							c.setrandom();
						}
					}
				}
				else if(c.planta == p){
					p.intersecting = true;
				}
				index++;
			}
			if(!p.intersecting & !p.buffer){
				p.changepic(1);
			}
		}
	}
}
