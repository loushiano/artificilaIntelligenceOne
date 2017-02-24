package Ass1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GUIController implements ActionListener {
	private GUI gui;
	
	public void setGui(GUI gui){
		this.gui=gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int Num=gui.getNum();
		JButton b=(JButton)e.getSource();
		b.setActionCommand(Num+"");
		b.setText(Num+" ");
		
		
	}

}
