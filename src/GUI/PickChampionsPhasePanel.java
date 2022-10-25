package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import engine.Game;
import model.abilities.Ability;
import model.world.Champion;

public class PickChampionsPhasePanel extends JPanel implements ActionListener{
	 
	ArrayList<String> championsNames;
	BorderLayout mainLayout=new BorderLayout(20, 20);
	JPanel allChampionsPanel=new JPanel();
	JPanel pickedChampionsPanelFirstPlayer=new JPanel(new GridLayout(4, 1) );
	JPanel pickedChampionsPanelSecondPlayer=new JPanel(new GridLayout(4, 1));
//	JPanel GRIDpickedChampionsPanelFirstPlayer=new JPanel(new FlowLayout(0) );
//	JPanel GRIDpickedChampionsPanelSecondPlayer=new JPanel(new FlowLayout(0));
	JPanel infoOfChampionPanel=new JPanel();
	GridLayout gridLayout=new GridLayout(0, 5,5,5) ;
	JButton pick;
	JButton startGame;
	PickChampionsPhaseListener listner;
	HashMap<JButton, String>hashMap =new HashMap<>();
	JButton selectedButton;
	String selectedChampionName;
	JPanel leaderGroup1=new JPanel( new GridLayout(0, 1));
	JPanel leaderGroup2=new JPanel( new GridLayout(0, 1));
	String nameOfLeader1="";
	String nameOfLeader2="";
	String player1Name;
	String player2Name;
	
	public PickChampionsPhasePanel(String name1,String name2)
	{
		
		
		pickedChampionsPanelFirstPlayer.add(new JLabel(name1));
		pickedChampionsPanelSecondPlayer.add(new JLabel(name2));
		
//		pickedChampionsPanelFirstPlayer.add(new JLabel("PICK A LEADER"));
//		pickedChampionsPanelSecondPlayer.add(new JLabel("PICK A LEADER"));
	  
		 startGame=new JButton("Start Game");
		startGame.addActionListener(this);
		this.setLayout(mainLayout);//the whole panel is borderlayout
		
		allChampionsPanel.setLayout(gridLayout);
		for(int i=0;i<Game.getAvailableChampions().size();i++)
		{
			String name=Game.getAvailableChampions().get(i).getName();
			//System.out.println(name);
			
			//ImageIcon icon=new ImageIcon(getClass().getResource("D:\\GUI\\Marvel-M2\\src\\Captain America.png"));
			
			//System.out.println(icon.getImageLoadStatus());
			
			JButton button=new JButton(name);
			//button.setToolTipText("fuckyou");
			//button.setIcon(icon);
			button.addActionListener(this);
			button.setSize(50, 50);
		
			hashMap.put(button,name );  //storing all the champions in hashmap
			allChampionsPanel.add(button);  //adding all the buttons in a flowlayout in the allchampions panel
		}
		this.validate();
		this.repaint();
		this.add(allChampionsPanel, BorderLayout.CENTER);  //then all the champions will be put in the center of the border layout
	
	
	
	
	
	
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
//		for(Component c :pickedChampionsPanelFirstPlayer.getComponents())
//		{
//			if(c==e.getSource())
//				{
//				//c.setBackground(Color.MAGENTA);
//				listner.onPickedALeader1(this,(JRadioButton) c);
//				System.out.print("actoin performed if i clicked a leader button");
//				return;
//				}
//
//		}
		
//		for (Enumeration<AbstractButton> buttons = leaderGroup1.getElements(); buttons.hasMoreElements();) 
//		 {
//			 AbstractButton button = buttons.nextElement();
//
//	            if (e.getSource()==button.getse)
//	            {
//	            	System.out.println("suck you mom");
//	            	listner.onPickedALeader1(this,(JRadioButton) button);
//					return;
//	            }
//		 }
		
		

//		for(Component c :pickedChampionsPanelSecondPlayer.getComponents())
//		{
//			if(c==e.getSource())
//				{
//				//c.setBackground(Color.MAGENTA);
//				listner.onPickedALeader2(this,(JRadioButton) c);
//				return;
//				}
//		}
//			 for (Enumeration<AbstractButton> buttons = leaderGroup1.getElements(); buttons.hasMoreElements();) 
//			 {
//				 AbstractButton button = buttons.nextElement();
//
//		            if (e.getSource()==button)
//		            {
//		            	System.out.println("suck you mom");
//		            	listner.onPickedALeader2(this,(JRadioButton) button);
//						return;
//		            }
//			 }
//		
		
			 if(e.getSource()==pick)
				{
					selectedButton.setEnabled(false);
					listner.onChampPick(this);
					this.add(pickedChampionsPanelFirstPlayer, BorderLayout.WEST);
					this.add(pickedChampionsPanelSecondPlayer, BorderLayout.EAST);
					this.validate();
					this.repaint();
					
				}
//				else if(e.getSource()==leaderGroup1.getSelection())//if he clicked to choose a leader
//				{ 
//					
//					listner.onPickedALeader1(this,(JButton) leaderGroup1.getSelection());
//					
//				}
//				else if(e.getSource()==leaderGroup2.getSelection())//if he clicked to choose a leader
//				{
//					listner.onPickedALeader2(this,(JButton) leaderGroup2.getSelection());
//					
//				}
			 
			 
			 else if (e.getSource()==startGame)
			 {
				 listner.onStartGame(this);
			 }
				 
				else //means he selected a champion and we want to display its attributes
				{
					
					 selectedChampionName=hashMap.get(e.getSource());
					
					selectedButton=(JButton) e.getSource();
					listner.onDisplayInfo(this);
					pick.addActionListener(this);
				}
		
				
		}
		
		
		
	
	
	
	public String getNameOfLeader1() {
		return nameOfLeader1;
	}

	public void setNameOfLeader1(String nameOfLeader1) {
		this.nameOfLeader1 = nameOfLeader1;
	}

	public String getNameOfLeader2() {
		return nameOfLeader2;
	}

	public void setNameOfLeader2(String nameOfLeader2) {
		this.nameOfLeader2 = nameOfLeader2;
	}

	public BorderLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(BorderLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public JButton getSelectedButton() {
		return selectedButton;
	}

	public String getSelectedChampionName() {
		return selectedChampionName;
	}

	public PickChampionsPhaseListener getListner() {
		return listner;
	}

	public void setListner(PickChampionsPhaseListener listner) {
		this.listner = listner;
	}
	
	public void pickedChampionsPanelUpdateFirstPlayer(String name)
	{
		//JLabel name1=new JLabel(name);
		//name1.addActionListener(this);
		JLabel leader=new JLabel(name);
		leaderGroup1.add(leader);
		//pickedChampionsPanelFirstPlayer.add(name1);
		pickedChampionsPanelFirstPlayer.add(leader);
		//GRIDpickedChampionsPanelFirstPlayer.add(pickedChampionsPanelFirstPlayer);
		//this.add(pickedChampionsPanelFirstPlayer, BorderLayout.WEST);
		this.validate();
		this.repaint();
		
	}
	
	public void pickedChampionsPanelUpdateSecondPlayer(String name)
	{
		
		//JLabel name1=new JLabel(name);
		JLabel leader=new JLabel(name);
		//name1.addActionListener(this);
		leaderGroup2.add(leader);
		
		//pickedChampionsPanelSecondPlayer.add(name1);
		pickedChampionsPanelSecondPlayer.add(leader);
		//GRIDpickedChampionsPanelSecondPlayer.add(pickedChampionsPanelSecondPlayer);
		//this.add(pickedChampionsPanelSecondPlayer, BorderLayout.EAST);
		this.validate();
		this.repaint();
		

	}
	
	public class GroupButtonUtils {

	    public String getSelectedButtonText(ButtonGroup buttonGroup) {
	        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();

	            if (button.isSelected()) {
	                return button.getText();
	            }
	        }

	        return null;
	    }
	}

	
}

	

	


