package Ass1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	private GUIController controler;
	private JFrame frame;
	private ArrayList<JButton> buttons;
	private Container contentPane;
	private JButton b,b1,b2,b3,b4,b5,b6,b7,b8;
	public GUI(GUIController controler
			){
		//Create the fame with specific features
		
		//this.controler=controler;
		setFrame(new JFrame("Network Topology"));
		//getFrame().setPreferredSize(new Dimension(1200, 700));
		buttons = new ArrayList<JButton>();
		
		
		
		frame.setResizable(true);
		}
		/*
		 * creates the topology based on a user entry, with all the nodes and connections
		 */
		public void createTopology(){
		
		//Panels of the Content Pane
			JPanel pane = new JPanel(new GridLayout(3,3)); 
			b =new JButton();
			b1 =new JButton();
			b2 =new JButton();
			b3 =new JButton();
			b4 =new JButton();
			b5 =new JButton();
			b6 =new JButton();
			b7=new JButton();
			b8 =new JButton();
			b.addActionListener(controler);
			pane.add(b);
			pane.add(b1);
			pane.add(b2);
			pane.add(b3);
			pane.add(b4);
			pane.add(b5);
			pane.add(b6);
			pane.add(b7);
			pane.add(b8);
		//Get the content pane from the frame.
			contentPane = frame.getContentPane();
			contentPane.add(pane);
			
			frame.setPreferredSize(new Dimension(500,500));
			frame.setResizable(false);
			frame.pack();
			frame.setVisible(true);
}
		public void setFrame(JFrame frame) {
			this.frame = frame;
	}
		public static void main(String args[]){
			GUIController gc = new GUIController();
			GUI gui = new GUI(gc);
			gui.createTopology();
		}
}
