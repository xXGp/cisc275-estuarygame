package cisc275.game.view;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author Team 6
 * Handles view for garbage collectors, initializes images and sets label icons
 */
public class GarbageCollectorView extends InstanceView{
	static ImageIcon[] pics = new ImageIcon[4];
	 int upxbound;
     int upybound;
     int downxbound;
     int downybound;
    static BufferedImage front;
    final int xIncr = 8;
    final int yIncr = 2;
    int xloc; //model
	int yloc; //model
    boolean up = false; //model
    boolean down = true; //model
    boolean left = false; //model
    boolean right = true; //model
    int randcount = 0;
    int picNum = 0;
    ImageIcon ing;
    final static int scaledimgWidth = ViewTemplate.scalex(58);
    final static int scaledimgHeight = ViewTemplate.scaley(75);
    JLabel gcbutton = new JLabel("gc");
    Point original;
	/**
	 * @param location - location for garbage collector label
	 * Set the label icon and position for garbage collector
	 */
	public GarbageCollectorView(Point location){
		upxbound = (int) location.getX()+ViewTemplate.scalex(250);
		downxbound = (int) location.getX()-ViewTemplate.scalex(250);
		upybound = (int) location.getY()+ViewTemplate.scaley(200);
		downybound = (int) location.getY()-ViewTemplate.scaley(200);
		xloc = (int) location.getX(); //model
		yloc = (int) location.getY(); //model
		gcbutton.setIcon(pics[0]);
        gcbutton.setLocation(location);
        gcbutton.setSize(scaledimgWidth, scaledimgHeight);
	}
	
	/**
     * If rando determines that the crab should change directions it determines the new
     * random direction
     */
    public void move() {
    		randcount= grndxdir(1);
        	int rand = 0;       	
        	if(randcount == 1){
        		randcount = 0;
        		rand = grndxdir(2);
        	}
        	 if (xloc >= upxbound || rand == 1)
             {
        		 if(xloc >= upxbound){
        			 //System.out.println((gcbutton.getX()+" "+ upxbound));
        			 right = false;
                     left = true;
        		 }
             }
             
             if (xloc <= downxbound || rand == 2)
             {
            	 if(xloc <= downxbound){
            		// System.out.println((gcbutton.getX()+" "+ downxbound));
            		 right = true;
            		 left = false;
            	 }
             }
//             if (yloc >=  upybound || rand == 3)
//             {
//            	 System.out.println((gcbutton.getY()+" "+ upybound));
//                 up = true;
//                 down = false;
//             }
//             
//             if (yloc <= downybound  || rand == 4)
//             {
//            	 System.out.println((gcbutton.getY()+" "+ downybound));
//                 up = false;
//                 down = true;
//             }
             rand = 0;
             rand = 0;
             if(picNum == 1 || picNum == 2){
         		picNum++;
         		if(right==true){
         			ing = pics[3];
         		}
         		else{
         			ing = pics[0];
         		}
         	}
         	else if(picNum == 3){
         		picNum++;
         		if(right==true){
         			ing = pics[2];
         		}
         		else{
         			ing = pics[1];
         		}
         	}
         	else{
         		picNum = 1;
         	}
            gcbutton.setIcon(ing);
//            if (up) yloc-=yIncr;
//            if (down) yloc+=yIncr;
            if (left) xloc-=xIncr;
            if (right) xloc+=xIncr;
            gcbutton.setLocation(xloc, yloc);
             
    }
    
    /**
     * 
     * Method controlling all random actions for crabs (mitten or native, y-axis spawn
     * location, direction change) 
     * @param l
     * @return int
     */
    public int grndxdir(int l){
    	Random rnd = new Random();
    	if(l == 1){ //1 in 8 chance of randomly changing direction
    		return(rnd.nextInt(8)+1);
    	}
    	else { //determines which direction it moves(l=2)
    		return(rnd.nextInt(4)+1);
    	}
    }
  
	/**
	 * Initializes garbage collector images
	 */
	public static void InitializeGarbage() {
		BufferedImage picss[] = new BufferedImage[4];
   		picss[0] = createImage("images/trashman1.png");
   		picss[1] = createImage("images/trashman2.png");
   		picss[2] = createImage("images/trashman3.png");
   		picss[3] = createImage("images/trashman4.png");
   		front = picss[0];
   		pics[0] = new ImageIcon(picss[0].getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT));
		pics[1] = new ImageIcon(picss[1].getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT));
		pics[2] = new ImageIcon(picss[2].getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT));
		pics[3] = new ImageIcon(picss[3].getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT));
	}
}
