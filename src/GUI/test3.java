package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class test3 extends JFrame implements ActionListener {
	Game game;
	JPanel panel1;
	JPanel panel2;
	JButton component1;
	JButton component2;
	JButton component3;
	JButton component4;
	int i=1;
	Champion c;
	public test3()
	{
		game=new Game(new Player(""), new Player(""));
		
		try {
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel1=new 	JPanel(new GridLayout(1, 1));
		panel2=new JPanel(new GridLayout(1, 3));
		this.setLayout(new BorderLayout());
		 component2=new JButton("");
		 component3=new JButton("");
		 component4=new JButton("");
		int random=(int) (Math.random()*(game.getAvailableChampions().size()));
		 c=game.getAvailableChampions().get(random);
		component1=new JButton(c.getName());
		component1.addActionListener(this);
		panel1.add(component1);
		panel2.add(component2);panel2.add(component3);panel2.add(component4);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel1,BorderLayout.NORTH);
		this.add(panel2,BorderLayout.CENTER);
        this.pack();
        this.setBounds(550, 200, 500, 500);
        this.setTitle("QUIZ");
		this.validate();
		this.repaint();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==component1)
		{
			if(i==1)
			{
				component2.setText(c.getAbilities().get(0).getName());
				i++;
			}
			else if (i==2)
			{
				component3.setText(c.getAbilities().get(1).getName());
				i++;
			}
			else if(i==3)
			{
				component4.setText(c.getAbilities().get(2).getName());
				i++;
			}
			else if(i==4)
			{
				int random=(int) (Math.random()*(game.getAvailableChampions().size()));
				 c=game.getAvailableChampions().get(random);
				 component1.setText(c.getName());
				 component2.setText("");component3.setText("");component4.setText("");
				 i=1;
			}
		}
		
		
	}
	
	public static void main (String []args)
	{
		new test3();
	}
	
	
}
