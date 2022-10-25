package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.JFrame;

import engine.Player;

public class playerWon extends JFrame{

	
	public playerWon(Player winner)
	{
		TextArea x=new TextArea(winner.getName()+" won the game wohooo");
		this.setLayout(new FlowLayout());
		x.setEditable(false);
	
		this.add(x);
		x.setSize(new Dimension(500, 500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocation(550,150);
	}
	
//	public static void main(String[]args)
//	{
//		new playerWon(new Player("Ahmed"));
//	}
}
