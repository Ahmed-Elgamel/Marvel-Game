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
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;

public class test1 extends JFrame implements ActionListener{
	
	JButton abilityName;
	JButton counter;
	JButton next;
	JButton abilityType;
	Game game;
	
	public test1()
	{
		JPanel panel=new JPanel(new GridLayout(2, 2));
		game=new Game(new Player(""), new Player(""));
		try {
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int randomAbility=(int) (Math.random()*(game.getAvailableAbilities().size()));
		
		counter=new JButton(randomAbility+"");
		
		Ability ability=game.getAvailableAbilities().get(randomAbility);
		
		abilityName=new JButton(ability.getName());
		//abilityName.setEnabled(false);
		
		if(ability instanceof DamagingAbility)
		{
			abilityType=new JButton("Damaging ability");
		}
		else if (ability instanceof HealingAbility)
		{
			abilityType=new JButton("Healing ability");
		}
		else if (ability instanceof CrowdControlAbility)
		{
			abilityType=new JButton("CrownControl ability");
		}
		next =new JButton("NEXT");
		next.addActionListener(this);
		panel.add(abilityName);panel.add(abilityType);panel.add(counter);panel.add(next);
        this.add(panel);
		this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("QUIZ");
        this.pack();
        this.setBounds(520, 200, 500, 500);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub4
		if(e.getSource()==next)
		{
			int randomAbility=(int) (Math.random()*(game.getAvailableAbilities().size()));
			counter.setText(randomAbility+"");
			Ability ability=game.getAvailableAbilities().get(randomAbility);
			abilityName.setText(ability.getName());
			//abilityName.setEnabled(false);
			if(ability instanceof DamagingAbility)
			{
				abilityType.setText("Damaging ability");
			}
			else if (ability instanceof HealingAbility)
			{
				abilityType.setText("Healing ability");
			}
			else if (ability instanceof CrowdControlAbility)
			{
				abilityType.setText("CrownControl ability");
			}
		}
		
	}
	
	
	public static void main(String []args)
	{
		new test1();
	}

}
