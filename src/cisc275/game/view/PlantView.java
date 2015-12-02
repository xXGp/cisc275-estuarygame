package cisc275.game.view;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlantView {
	static Image[] pics;
    double xloc;
    double yloc;
    final static int scaledimgWidth = 75;
    final static int scaledimgHeight = 75;
    JLabel pbutton = new JLabel("test");
	public PlantView() {
		pics = new Image[4];
   	 //loads all subimages into array, separated by their type
   		
   		//pics[i] = pics[i].getScaledInstance(82, 82, Image.SCALE_DEFAULT);
   		try {
   			pics[0] = ImageIO.read(getClass().getResource("Fern.png")).getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT);
   			pics[1] = ImageIO.read(getClass().getResource("Fern.png")).getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT);
   			pics[2] = ImageIO.read(getClass().getResource("Fern.png")).getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT);
   			pics[3] = ImageIO.read(getClass().getResource("Fern.png")).getScaledInstance(scaledimgWidth, scaledimgHeight, Image.SCALE_DEFAULT);
   			//System.out.println(pics[1]+ "crab1");
   			//System.out.println(pics[0]+ "crab2");
   		} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	public PlantView(int num, Point location){
		//loci = location;
		pbutton.setIcon(new ImageIcon(pics[num]));
        pbutton.setLocation(location);
        pbutton.setSize(75,75);
	}

}