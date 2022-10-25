package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.hamcrest.core.IsInstanceOf;

import engine.Game;
import engine.PriorityQueue;
import model.world.Champion;
import model.world.Cover;

public class GamePhasePanel extends JPanel implements MouseListener,ActionListener{
	
	JPanel boardPanel=new JPanel(new GridLayout(5, 5));
	//JPanel information=new JPanel(new FlowLayout());
	JPanel instructions=new JPanel(new GridLayout(0, 1,20,20));
	JPanel orderOfChampions;
	
	JButton moveUp;
	JButton moveDown;
	JButton moveRight;
	JButton moveLeft;
	JButton attack;
	JButton castAbility1;
	JButton castAbility2;
	JButton castAbility3;
	JButton endTurn;
	JButton useLeaderAbility;
	Game game;
	TextArea text;
	HashMap<Champion,JLabel> hashmap;
	HashMap<JLabel, Champion> hashmap2;
	GamePhasePanelListner listner;
	
	public GamePhasePanel(Game game)
	{
		 text=new TextArea(30,30);
		 
		 this.add(text, BorderLayout.WEST);
		 text.setEditable(false);
		hashmap=new HashMap<>();
		hashmap2=new HashMap<>();
		this.game=new Game(game.getFirstPlayer(), game.getSecondPlayer());
		//this.game=game;
		this.setLayout(new BorderLayout(5,5));
		game.placeChampions();
		game.placeCovers();
		
		

		 
		initializeInstructions(); 		 
		initializeBoardPanel();
		initializeTurnOrderPanel();
	    initializeInformationPanel();
	    setBorderForChampionInTurn();
			
		
	}
	
	
	public void initializeInstructions()
	{
		 moveUp=new JButton("MOVE UP");
		 moveUp.addActionListener(this);
		 
		 moveDown=new JButton("MOVE DOWN");
		 moveDown.addActionListener(this);
		 
		 moveRight=new JButton("MOVE RIGHT");
		 moveRight.addActionListener(this);
		 
		 moveLeft=new JButton("MOVE LEFT");
		 moveLeft.addActionListener(this);
		 
		 attack=new JButton("ATTACK");
		 attack.addActionListener(this);
		 
		 castAbility1=new JButton("CAST ABILITY 1");
		 castAbility1.addActionListener(this);
		 
		 castAbility2=new JButton("CAST ABILITY 2");
		 castAbility2.addActionListener(this);
		 
		 castAbility3=new JButton("CAST ABILITY 3");
		 castAbility3.addActionListener(this);
		 
		 useLeaderAbility=new JButton("USE LEADER ABILITY");
		 useLeaderAbility.addActionListener(this);
		 
		 endTurn=new JButton("END TURN");
		 endTurn.addActionListener(this);
		 
		 instructions.add(moveUp);instructions.add(moveDown);instructions.add(moveRight);instructions.add(moveLeft);
		 instructions.add(attack);instructions.add(castAbility1);instructions.add(castAbility2);instructions.add(castAbility3);
		 instructions.add(useLeaderAbility);
		 instructions.add(endTurn);
		 
		 this.add(instructions,BorderLayout.EAST);
	}
	
	
	
	public void initializeBoardPanel()
	{
		int ii=0;
		this.remove(boardPanel);
		boardPanel=new JPanel(new GridLayout(5, 5,12,12));
		Object[][] myBoard=game.getBoard();
		 ImageIcon image=new ImageIcon("/Marvel-M2/Images/Captain America.png");
		 
		 for (int i=0;i<game.getBoardheight();i++)
		 {
			 for(int j=0;j<game.getBoardwidth();j++)
			 {
				 JLabel label=new JLabel(new ImageIcon("/Marvel-M2/Images/Captain America.png"));
				 label.setOpaque(true);
				 
				 if(myBoard[i][j]==null)
				 {
					label.setBackground(Color.WHITE);
				 }
				 else if(myBoard[i][j]  instanceof Cover)
				 {
					 Cover c=(Cover)myBoard[i][j];
					 label.setBackground(Color.YELLOW);
					 label.setText("COVER"+"  HP "+c.getCurrentHP());
	                 			
					 label.setIcon(image);
					 
				 }
				 else if(myBoard[i][j] instanceof Champion)
				 {
					 Champion c=(Champion) myBoard[i][j];
					 if(game.getFirstPlayer().getTeam().contains(c))
					 {
						 label.setBackground(Color.BLUE);
						 String s=game.getFirstPlayer().getName()+"\n"+c.getName();
						 label.setText(s);
						 label.addMouseListener(this);
						 hashmap.put(c, label);
						 hashmap2.put(label,c);
						 
					 }
					 else
					 {
						 label.setBackground(Color.red);
						 String s=game.getSecondPlayer().getName()+"\n"+c.getName();
						 label.setText(s);
						 label.addMouseListener(this);
						 hashmap.put(c, label);
						 hashmap2.put(label,c);
						 
					 }
//					 System.out.println();
//					 System.out.println("Champion "+c.getName());
//					 System.out.println("Jlabel "+label.getText());
				 }
				 boardPanel.add(label);
				// System.out.println(ii++);
				 
			 
			 }
		 }
			 prindgrid();
			 System.out.println(ii++);
			 this.add(boardPanel, BorderLayout.CENTER);
			 this.validate();
			 this.repaint();
		 
	}
	
	public void prindgrid() {
		for(Object[] o:game.getBoard()) {
			for(Object oo:o) {
				System.out.println(oo);
			}
	System.out.println();	
	}
	}
	
	public void initializeTurnOrderPanel()
	{
		 ArrayList<Champion> myTurorder=new ArrayList<>();
		 PriorityQueue temp=new PriorityQueue(6);
		 orderOfChampions=new JPanel();
		 while(!game.getTurnOrder().isEmpty())
		 {
			 Champion c=(Champion) game.getTurnOrder().remove();
			 myTurorder.add(c);
			 temp.insert(c);
			
		 }
		 
		 while(!temp.isEmpty())
			 game.getTurnOrder().insert(temp.remove());
		 
		 
		 orderOfChampions.setLayout(new FlowLayout(0, 20, 20));
		 orderOfChampions.add(new JLabel("CHAMPIONS TURNS: "));
		 
		 for (Champion c:myTurorder)
		 {
			JLabel championName=new JLabel(c.getName());
			championName.setOpaque(true);
			if(game.getFirstPlayer().getTeam().contains(c))
				championName.setBackground(Color.BLUE);
			else
				championName.setBackground(Color.red);
				
			orderOfChampions.add(championName);
		 }
		 
		 String s1="";
		 String s2="";
		 
		 if(game.isFirstLeaderAbilityUsed())
			  s1=game.getFirstPlayer().getName()+" your leader ability is used";
		 else
			 s1=game.getFirstPlayer().getName()+" your leader ability is not used";

		 if(game.isSecondLeaderAbilityUsed())
			 s2=game.getSecondPlayer().getName()+" your leader ability is used"; 
		 else
			 s2=game.getSecondPlayer().getName()+" your leader ability is not used";
		 
		 JLabel abilityUsed1=new JLabel(s1);
		 JLabel abilityUsed2=new JLabel(s2);
		 
		 orderOfChampions.add(abilityUsed1);
		 orderOfChampions.add(abilityUsed2);
		 
		 this.add(orderOfChampions, BorderLayout.NORTH);
		 this.validate();
		 this.repaint();
		 
		 
	}
	
	
	public void setBorderForChampionInTurn()
	{
		JLabel fastestChampion=hashmap.get(game.getTurnOrder().peekMin());//i got the label that will play
		Border label_border=BorderFactory.createLineBorder(Color.magenta,7);
		fastestChampion.setBorder(label_border);
	}
	
	public void initializeInformationPanel()
	{
		
		
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
   	
		Champion c =hashmap2.get(e.getSource());
		listner.onLabelHover(this,c);
		this.add(text,BorderLayout.WEST);
		this.validate();
		this.repaint();
		
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	public GamePhasePanelListner getListner() {
		return listner;
	}




	public void setListner(GamePhasePanelListner listner) {
		this.listner = listner;
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		
//		this.remove(boardPanel);
//		this.remove(orderOfChampions);
//		this.validate();
//		this.repaint();
		if(e.getSource()==moveUp)
			listner.onmoveUp(this);
		else if(e.getSource()==moveDown)
			listner.onmoveDown(this);
		else if (e.getSource()==moveRight)
			listner.onmoveRight(this);
		else if(e.getSource()==moveLeft)
			listner.onmoveLeft(this);
		else if(e.getSource()==attack)
			listner.onattack(this);
		else if(e.getSource()==castAbility1)
			{
			listner.oncastAbility1(this);
			}
		else if(e.getSource()==castAbility2)
			listner.oncastAbility2(this);
		else if(e.getSource()==castAbility3)
			listner.oncastAbility3(this);
		else if(e.getSource()==useLeaderAbility)
			listner.onuseLeaderAbility(this);
		else
			listner.onendTurn(this);
	
		
		
	}
	
	public void refresh()
	{
		
			    
	    this.remove(boardPanel);
        this.validate();
	    this.repaint();
	    initializeBoardPanel();
	    setBorderForChampionInTurn();
	    this.revalidate();
	    this.repaint();
	    
	    this.remove(orderOfChampions);
	    this.revalidate();
	    this.repaint();
	    initializeTurnOrderPanel();
	    
	    
	    
	    
//	    
//        this.remove(orderOfChampions);
//        this.revalidate();
//	    this.repaint();
//		initializeTurnOrderPanel();
//		 this.revalidate();
//		    this.repaint();
//	    
//	    
//	    setBorderForChampionInTurn();
//	    this.revalidate();
//	    this.repaint();
	    
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
