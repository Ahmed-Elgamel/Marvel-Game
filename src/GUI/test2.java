package GUI;

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

public class test2 extends JFrame implements ActionListener{
	
	
	JButton component1= new JButton();
	JButton component2= new JButton();
	JButton component3= new JButton();
	Champion champion1;
	Champion champion2;
	Champion champion3;
	JPanel panel=new JPanel(new GridLayout(3, 1));
	Game game;
	public test2()
	{
		game=new Game(new Player(""), new Player(""));
		try {
			Game.loadAbilities("Abilities.cs-v");
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int random1=(int) (Math.random()*(game.getAvailableChampions().size()));
		int random2=(int) (Math.random()*(game.getAvailableChampions().size()));
		int random3=(int) (Math.random()*(game.getAvailableChampions().size()));
		
		 champion1=game.getAvailableChampions().get(random1);
		 champion2=game.getAvailableChampions().get(random2);
		 champion3=game.getAvailableChampions().get(random3);
		
		component1.setText(champion1.getName()+"  "+champion1.getCurrentHP());
		component2.setText(champion2.getName()+"  "+champion2.getCurrentHP());
		component3.setText(champion3.getName()+"  "+champion3.getCurrentHP());
		
		
		
		component1.addActionListener(this);
		component2.addActionListener(this);
		component3.addActionListener(this);
		panel.add(component1);panel.add(component2);panel.add(component3);
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setBounds(550, 200, 500, 500);
        this.setTitle("QUIZ");
		  
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==component1)
		{
			champion1.setCurrentHP(champion1.getCurrentHP()-500);
			component1.setText(champion1.getName()+"  "+champion1.getCurrentHP());
		}
		else if(e.getSource()==component2)
		{
			champion2.setCurrentHP(champion2.getCurrentHP()-500);
			component2.setText(champion2.getName()+"  "+champion2.getCurrentHP());
		}
		
		else if (e.getSource()==component3)
		{
			champion3.setCurrentHP(champion3.getCurrentHP()-500);
			component3.setText(champion3.getName()+"  "+champion3.getCurrentHP());
		}
		
		
	
		
		
		
	}
	
	
	public static void main(String []args)
	{
		test2 x=new test2();
	}
	
	

}
