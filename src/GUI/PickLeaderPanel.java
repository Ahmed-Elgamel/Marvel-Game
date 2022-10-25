package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.GapContent;

import engine.Game;
import model.world.Champion;

public class PickLeaderPanel extends JPanel implements ActionListener{
	
	
	ArrayList<Champion>team1;
	ArrayList<Champion>team2;
	JPanel leaders1=new JPanel(new GridLayout(1, 4, 20, 20));
	JPanel leaders2=new JPanel(new GridLayout(1, 4, 20, 20));
	HashMap<JButton,Champion> hashmap;
	JPanel leaderOfPlayer1;
	JPanel leaderOfPlayer2;
	JPanel leadersChosenPanel1=new JPanel(new GridLayout(1,1,20,20));
	JPanel leadersChosenPanel2=new JPanel(new GridLayout(1,1,20,20));
	JLabel textOfLeader1;
	JLabel textOfLeader2;
	JPanel allLeaders;
	JButton next;
	PickLeaderPanelListner lisnter;
	public PickLeaderPanel(ArrayList<Champion>team1,ArrayList<Champion>team2,String name1,String name2)
	{
		
		this.setLayout(new BorderLayout(100, 150));
//		this.team1=team1;
//		this.team2=team2;
		hashmap=new HashMap<>();
		leaderOfPlayer1=new JPanel();
		leaderOfPlayer2=new JPanel();
		JLabel name11=new JLabel("Pick your leader "+name1);
		leaders1.add(name11);
		JLabel name22=new JLabel("Pick your leader "+name2);
		leaders2.add(name22);
		
		for (Champion c:team1)
		{
			JButton button=new JButton(c.getName());
			button.addActionListener(this);
			hashmap.put(button,c);
			leaders1.add(button);
			
		}
		for (Champion c:team2)
		{
			JButton button=new JButton(c.getName());
			button.addActionListener(this);
			hashmap.put(button,c);
			leaders2.add(button);

		}
		
//	    textOfLeader1=new JLabel("Pick your leader "+name1);
//		textOfLeader1.setBounds(20, 20, 30, 30);
//		
//		textOfLeader2=new JLabel("Pick your leader "+name2);
//		textOfLeader2.setBounds(20, 20, 30, 30);
		
		
//		leadersChosenPanel1.add(textOfLeader1);
//		leadersChosenPanel1.add(textOfLeader2);
		
		this.add(leaders1, BorderLayout.NORTH);
		this.add(leaders2, BorderLayout.SOUTH);
		
		allLeaders=new JPanel(new GridLayout(3, 1));
		allLeaders.add(leadersChosenPanel1);
		allLeaders.add(leadersChosenPanel2);
		this.validate();
		this.repaint();
		this.add(allLeaders, BorderLayout.CENTER);
		
		this.validate();
		this.repaint();
//		this.add(leaderOfPlayer1, BorderLayout.WEST);
//		this.add(leaderOfPlayer2,BorderLayout.EAST);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==next)
		{
			lisnter.onNext(this);
		}
		else
		{
			Champion c=hashmap.get(e.getSource());
			lisnter.onPickLeader(this,c);
			
			this.validate();
			this.repaint();
		}
		
	}
	public PickLeaderPanelListner getLisnter() {
		return lisnter;
	}
	public void setLisnter(PickLeaderPanelListner lisnter) {
		this.lisnter = lisnter;
	}
	public JPanel getLeadersChosenPanel1() {
		return leadersChosenPanel1;
	}
	public void setLeadersChosenPanel1(JPanel leadersChosenPanel1) {
		this.leadersChosenPanel1 = leadersChosenPanel1;
	}
	public JPanel getLeadersChosenPanel2() {
		return leadersChosenPanel2;
	}
	public void setLeadersChosenPanel2(JPanel leadersChosenPanel2) {
		this.leadersChosenPanel2 = leadersChosenPanel2;
	}
	
	
	
	
	
	
	
	

}
