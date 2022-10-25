package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.element.ExecutableElement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.Champion;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Controller implements startPanelListner,getPlayersNamePanelListner ,PickChampionsPhaseListener,
                                   PickLeaderPanelListner,GamePhasePanelListner{
	
	Game game;
	Player Player1;
	Player Player2;
	StartPanel startPanel;
	PickChampionsPhasePanel pickChampionsPanel;
	GamePhasePanel gamePhasePanel;
	Window window;
	GetPlayersNamePanel getPlayerNamePanel;
	PickLeaderPanel pickLeaderPanel;
	
	
	
	public Controller()
	{
		window=new Window();
		startPanel=new StartPanel();
		startPanel.setListner(this);
		window.setContentPane(startPanel);
		window.pack();
		window.setLocationRelativeTo(null);
		
	}
	
	public static void main(String[]args)
	{
		Controller x=new Controller();
	}

	@Override
	public void onExit(StartPanel s) {
		// TODO Auto-generated method stub
		window.dispose();
		
	}

	@Override
	public void onPlayGame(StartPanel s) {
		window.getContentPane().remove(startPanel);
		window.validate();
		window.repaint();
		getPlayerNamePanel=new GetPlayersNamePanel();
		getPlayerNamePanel.setListner(this);
		window.setContentPane(getPlayerNamePanel);
		window.validate();
		window.repaint();
		
	}

	@Override
	public void onCredits(StartPanel s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOptions(StartPanel s) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void onStartGame(GetPlayersNamePanel s) {
		// TODO Auto-generated method stub
		window.validate();
		window.repaint();
		String player1=s.getPlayer1().getText();
		String player2=s.getPlayer2().getText();
		Player1=new Player(player1);
	    Player2=new Player(player2);
		
	    game=new Game(Player1,Player2);
		try {
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//window.dispose();
		
		pickChampionsPanel=new PickChampionsPhasePanel(player1,player2);
		
		
		
		pickChampionsPanel.setListner(this);
		window.setContentPane(pickChampionsPanel);
		pickChampionsPanel.player1Name=player1;
		pickChampionsPanel.player2Name=player2;
		
		window.validate();
		window.repaint();
		

		

	}

	

	@Override
	public void onChampPick(PickChampionsPhasePanel s) {
		// TODO Auto-generated method stub
		String name=s.getSelectedChampionName();
		Champion c=null ;
		
		for(Champion c1:game.getAvailableChampions())
		{
			String name1=c1.getName();
			if(name1.equals(name))
				c=c1;
				
		}
		if(game.getFirstPlayer().getTeam().size()==3&&game.getSecondPlayer().getTeam().size()==3)
		{
			JOptionPane.showMessageDialog(s, "YOU CAN NOT PICK MORE CHAMPIONS","ERROR!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(game.getFirstPlayer().getTeam().contains(c)||game.getSecondPlayer().getTeam().contains(c))
			{
			JOptionPane.showMessageDialog(s, "Champion is already picked!!","ERROR!",JOptionPane.ERROR_MESSAGE);
			return;
			}
		
		if(game.getFirstPlayer().getTeam().size()!=3)
			{
			
			s.pickedChampionsPanelUpdateFirstPlayer(c.getName());
			game.getFirstPlayer().getTeam().add(c);
//			if(game.getFirstPlayer().getTeam().size()==3&&game.getSecondPlayer().getTeam().size()==3)
//			{
//				window.dispose();
//				gamePhase=new GamePhasePanel();
//				window.setContentPane(gamePhase);
//				window.validate();
//				window.repaint();
//			}
			
			}
		else if (game.getSecondPlayer().getTeam().size()!=3)
		{
			s.pickedChampionsPanelUpdateSecondPlayer(c.getName());
			game.getSecondPlayer().getTeam().add(c);
//			if(game.getFirstPlayer().getTeam().size()==3&&game.getSecondPlayer().getTeam().size()==3)
//			{
//				window.dispose();
//				gamePhase=new GamePhasePanel();
//				window.setContentPane(gamePhase);
//				window.validate();
//				window.repaint();
//			}
		}
		
		
		
		
		
	}

	@Override
	public void onDisplayInfo(PickChampionsPhasePanel s) {
		// TODO Auto-generated method stub
		s.infoOfChampionPanel.removeAll();
		String name=s.getSelectedChampionName();
		Champion c=null ;
		for(Champion c1:game.getAvailableChampions())
		{
			String name1=c1.getName();
			if(name1.equals(name))
				c=c1;
				
		}
		if(c==null)
			throw new Error("The Champ is Null!!");
		if(c.getAbilities()==null)
			throw new Error("The Abilities are Null!!");

		 String x="";
			
			for(Ability a:c.getAbilities())
				{
				x+=" "+a.getName()+"\n";
				}
			TextArea info=new TextArea(
					                 "Name: "+c.getName()+
					                 "Health: "+c.getMaxHP()+"\n"+
					                "Attack Damage: "+c.getAttackDamage()+"\n"+
					                "Attack Range: "+c.getAttackRange()+"\n"+
					                "Action Points: "+c.getCurrentActionPoints()+"\n"+
					                "Mana: "+c.getMana()+"\n"+
					                "Speed: "+c.getSpeed()+"\n"+
					                "Abilties : \n"+x+"\n");
		      
			    info.setEditable(false);
			    s.pick=new JButton("Pick");
		        s.infoOfChampionPanel.add(info);
		        s.infoOfChampionPanel.add(s.pick);
		        s.infoOfChampionPanel.add(s.startGame);
		        s.add(s.infoOfChampionPanel, BorderLayout.SOUTH);
		        window.validate();
		        window.repaint();
		
		
	}

//	@Override
//	public void onPickedALeader1(PickChampionsPhasePanel s,JRadioButton x) {
//		// TODO Auto-generated method stub
//		String name=x.getText();
//		s.setNameOfLeader1(name);
//		Champion z=null;
//		for(Champion c:game.getAvailableChampions())
//		{
//			if(c.getName().equals(name))
//				z=c;
//		}
//		game.getFirstPlayer().setLeader(z);
//		System.out.print(game.getFirstPlayer().getLeader()+"this is the leader ");
//		
//		
//		
//	}
//
//	@Override
//	public void onPickedALeader2(PickChampionsPhasePanel s, JRadioButton x) {
//		// TODO Auto-generated method stub
//		String name=x.getText();
//		s.setNameOfLeader1(name);
//		Champion z=null;
//		for(Champion c:game.getAvailableChampions())
//		{
//			if(c.getName().equals(name))
//				z=c;
//		}
//		game.getSecondPlayer().setLeader(z);
//		System.out.print(game.getFirstPlayer().getLeader()+"this is the leader ");
//		gamePhasePanel=new GamePhasePanel(game);
//		window.dispose();
//		window.getContentPane().add(gamePhasePanel);
//	}

	@Override
	public void onStartGame(PickChampionsPhasePanel s) {
		// TODO Auto-generated method stub
		if(game.getFirstPlayer().getTeam().size()!=3||game.getSecondPlayer().getTeam().size()!=3)
			JOptionPane.showMessageDialog(s, "Pick more Champions!!","ERROR!",JOptionPane.ERROR_MESSAGE);
		else
			{
			
			window.getContentPane().remove(s);
			pickLeaderPanel=new PickLeaderPanel(game.getFirstPlayer().getTeam(),game.getSecondPlayer().getTeam(),game.getFirstPlayer().getName(),game.getSecondPlayer().getName());
			pickLeaderPanel.setLisnter(this);
			window.setContentPane(pickLeaderPanel);
			window.validate();
			window.repaint();
			}
		
	}

	@Override
	public void onPickLeader(PickLeaderPanel s,Champion  leaderChosen) {
		// TODO Auto-generated method stub
		if(game.getFirstPlayer().getLeader()==null&&game.getFirstPlayer().getTeam().contains(leaderChosen))
		{
			game.getFirstPlayer().setLeader(leaderChosen);
			
			
			s.textOfLeader1=new JLabel(game.getFirstPlayer().getName()+" "+leaderChosen.getName()+" is your leader");
			s.getLeadersChosenPanel1().add(s.textOfLeader1);
			
			System.out.print("the leader of player1 is "+leaderChosen.getName());
			window.validate();
			window.repaint();
			
		}
		else if (game.getSecondPlayer().getLeader()==null&&game.getSecondPlayer().getTeam().contains(leaderChosen))
		{
			game.getSecondPlayer().setLeader(leaderChosen);
			
			s.textOfLeader2=new JLabel(game.getSecondPlayer().getName()+" "+leaderChosen.getName()+" is your leader");
			s.getLeadersChosenPanel2().add(s.textOfLeader2);
			
			s.next=new JButton("Next");
			
			s.allLeaders.add(s.next);
			s.next.addActionListener(s);
			System.out.print("the leader of player2 is "+leaderChosen.getName());
			window.validate();
			window.repaint();
			
		}
		else 
		{
			//do nothing becuase he chose the leaders
		}
		
		
		
	}

	@Override
	public void onNext(PickLeaderPanel s) {
		// TODO Auto-generated method stub
		if(game.getFirstPlayer().getLeader()!=null&&game.getSecondPlayer().getLeader()!=null)
		{
			//game=new Game(game.getFirstPlayer(), game.getSecondPlayer());//initialized it again because the turorder is not initialized
			window.remove(s);
			//this.game=new Game(this.game.getFirstPlayer(), this.game.getSecondPlayer());
			gamePhasePanel=new GamePhasePanel(game);
			this.game=gamePhasePanel.game;
			gamePhasePanel.setListner(this);
			window.setContentPane(gamePhasePanel);
			window.validate();
			window.repaint();
		}
		
		
	}

	@Override
	public void onLabelHover(GamePhasePanel s,Champion c) {
		// TODO Auto-generated method stub
		
	
		String info="";
		
		info+="NAME: "+c.getName()+"\n"+"ATTACK DAMAGE: "+c.getAttackDamage()+"\n"+"ATTACK RANGE: "+c.getAttackRange()+
				"\nACTION POINTS: "+c.getCurrentActionPoints()+"\nCURRENT HP: "+c.getCurrentHP()+
				"\nMANA: "+c.getMana()+"\nSPEED: "+c.getSpeed()+"\n";//c.getAbilities()+c.getAppliedEffects();
		if (c instanceof Hero)
		{
			info+="TYPE: HERO\n";
		}
		else if (c instanceof Villain)
		{
			info+="TYPE: VILLAIN\n";
		}
		else
		{
			info+="TYPE: ANTI-HERO\n";
		}
		info+="ABILITIES: "+"\n";
		for(Ability a:c.getAbilities())
		{
			info+=a.getName()+"\n";
		}
		info+="APPLIED EFFECTS: "+"\n";
		for(Effect e:c.getAppliedEffects())
		{
			info+=e.getName()+"\n";
		}
		if(game.getFirstPlayer().getLeader().equals(c))
			info+="This champion is the leader of "+game.getFirstPlayer().getName();
		if(game.getSecondPlayer().getLeader().equals(c))
			info+="This champion is the leader of "+game.getSecondPlayer().getName();
		
		s.text.setText(info);
		window.validate();
		window.repaint();
		
		
		
	}

	@Override
	public void onmoveUp(GamePhasePanel s) {
		// TODO Auto-generated method stub
       Champion c=game.getCurrentChampion();
       try {
		game.move(Direction.UP);
		s.refresh();   
		
	      
	} catch (NotEnoughResourcesException | UnallowedMovementException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(s, "UNALLOWED MOVEMENT!!","ERROR!",JOptionPane.ERROR_MESSAGE);
		
	}

       
      
      
		
	}

	@Override
	public void onmoveDown(GamePhasePanel s) {
		// TODO Auto-generated method stub
		   Champion c=game.getCurrentChampion();
	       try {
			game.move(Direction.DOWN);
			s.refresh();
		} catch (NotEnoughResourcesException | UnallowedMovementException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(s, "UNALLOWED MOVEMENT!!","ERROR!",JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}

	@Override
	public void onmoveRight(GamePhasePanel s) {
		// TODO Auto-generated method stub
		Champion c=game.getCurrentChampion();
	       try {
			game.move(Direction.RIGHT);
			s.refresh();
		} catch (NotEnoughResourcesException | UnallowedMovementException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(s, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

	@Override
	public void onmoveLeft(GamePhasePanel s) {
		// TODO Auto-generated method stub
		Champion c=game.getCurrentChampion();
	       try {
			game.move(Direction.LEFT);
			s.refresh();
		} catch (NotEnoughResourcesException | UnallowedMovementException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(s, "UNALLOWED MOVEMENT!!","ERROR!",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

	@Override
	public void onattack(GamePhasePanel s) {
		// TODO Auto-generated method stub
		String [] options= {"UP","DOWN","LEFT","RIGHT"};
		int response=JOptionPane.showOptionDialog(s, "Choose the attack direction", "Attack", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);
		
		if(response==0)
		{
			try {
				game.attack(Direction.UP);
				if(game.checkGameOver()!=null)
				{ 
				window.removeAll();
				window.dispose();
				window.repaint();
				window.validate();
				playerWon x = new playerWon(game.checkGameOver());
				
				}
				s.refresh();
			} catch (NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(s, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
			
			}
		}
		else if(response==1)
		{
			try {
				game.attack(Direction.DOWN);
				if(game.checkGameOver()!=null)
				{ 
				
				window.removeAll();
				window.dispose();
				window.repaint();
				window.validate();
				playerWon x = new playerWon(game.checkGameOver());
				
				}
				s.refresh();
			} catch (NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(s, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
			
			}
		}
		else if (response==2)
		{
			try {
				game.attack(Direction.LEFT);
				if(game.checkGameOver()!=null)
				{ 
				window.removeAll();
				window.dispose();
				window.repaint();
				window.validate();
				playerWon x = new playerWon(game.checkGameOver());
				
				}
				s.refresh();
			} catch (NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(s, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
			
			}
		}
		else if (response==3)
		{
			try {
				game.attack(Direction.RIGHT);
				if(game.checkGameOver()!=null)
				{ 
				window.removeAll();
				window.dispose();
				window.repaint();
				window.validate();
				playerWon x = new playerWon(game.checkGameOver());
				
				}
				s.refresh();
			} catch (NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(s, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
			
			}
		}
		
		
	}

	@Override
	public void oncastAbility1(GamePhasePanel s) {
		// TODO Auto-generated method stub
//		Champion c=game.getCurrentChampion();
//		JFrame popup=new JFrame();
//		popup.setVisible(true);
//		popup.setLocationRelativeTo(s);
//		popup.setSize(500, 200);
//		castAbilityPanel panel=new castAbilityPanel(c);
//		panel.indicator=1;
//		panel.x=this;
//		panel.y=s;
//		popup.add(panel);
		
		JFrame popup=new JFrame();
		popup.setVisible(true);
		Ability a=game.getCurrentChampion().getAbilities().get(0);
		popup.setLocationRelativeTo(s);
		popup.setLocationRelativeTo(s);
		popup.setSize(500, 200);
		popup.setLayout(new FlowLayout());
		String ss="ABILITY NAME: "+a.getName()+"\n"+"ABILITY TYPE: ";
		
		if(a instanceof HealingAbility)
			ss+="HEALING ABILITY"+"\n";
		else if (a instanceof DamagingAbility)
			ss+="DAMAGING ABILITY"+"\n";
		else if (a instanceof CrowdControlAbility)
			ss+="CROWD CONTROL ABILITY"+"\n";
		ss+="AREA OF EFFECT: "+a.getCastArea()+"\n"+"CAST RANGE: "+a.getCastRange()+"\n"+
			"MANA COST: "+a.getManaCost()+"\n"+"ACTION POINTS COST: "+a.getRequiredActionPoints()+"\n"+
				"BASE COOLDOWN: "+a.getBaseCooldown()+"\n"+"CURRENT COOLDOWN: "+a.getCurrentCooldown()+"\n";
		if(a instanceof HealingAbility)
			{
			HealingAbility a1=(HealingAbility)a;
			ss+="HEAL AMOUNT: "+a1.getHealAmount();
			}
		else if (a instanceof DamagingAbility)
			{
			DamagingAbility a1=(DamagingAbility)a;
			ss+="DAMAGE AMOUNT: "+a1.getDamageAmount();
			}
		else if (a instanceof CrowdControlAbility)
			{
			CrowdControlAbility a1=(CrowdControlAbility)a;
			ss+="EFFECT NAME: "+a1.getEffect().getName()+"\n"+"EFFECT DURATION: "+a1.getEffect().getDuration();
			}
		JTextArea texxt=new JTextArea();
		texxt.setText(ss);
		texxt.setEditable(false);
		popup.add(texxt);
		texxt.setBounds(0,0,500,150);
		JButton button=new JButton("CAST");
		button.setBounds(50,150,50,50);
		popup.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				execute(a);
				popup.dispose();
				s.refresh();
				
			}
		})
		;
	}
//	public void oncastAbility11(Ability a,castAbilityPanel panel) {
//		
//		try {
//			if(a.getCastArea()==AreaOfEffect.DIRECTIONAL||a.getCastArea()==AreaOfEffect.SINGLETARGET)
//				throw new AbilityUseException("CAN NOT USE THIS ABILITY");
//			game.castAbility(a);
//			panel.y.refresh();
//		} catch (NotEnoughResourcesException|AbilityUseException|CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(panel, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
//		}
//	
//		
//		
//		
//	}
public void execute(Ability a) {
	
	if(a.getCastArea()!=AreaOfEffect.DIRECTIONAL&&a.getCastArea()!=AreaOfEffect.SINGLETARGET)
		try {
			game.castAbility(a);
			if(game.checkGameOver()!=null)
				{ 
				window.removeAll();
				window.dispose();
				window.repaint();
				window.validate();
				playerWon x = new playerWon(game.checkGameOver());
				
				}
			gamePhasePanel.refresh();
			
		} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
			JOptionPane.showMessageDialog(this.gamePhasePanel, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);

			//JOptionPane.showInternalMessageDialog(this.gamePhasePanel, e.getMessage());
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
	else if (a.getCastArea()==AreaOfEffect.SINGLETARGET)
	{
		JFrame popup=new JFrame();
		popup.setLayout(new FlowLayout());
		popup.setVisible(true);
		JTextField x=new JTextField("X");
		JTextField y=new JTextField("Y");
		x.setSize(30,20);y.setSize(30,20);
		popup.add(x);popup.add(y);
		JButton buton=new JButton("CAST");
		popup.add(buton);
		popup.setBounds(700, 300, 200, 200);
		buton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
				int xx=Integer.parseInt(x.getText());
				int yy=Integer.parseInt(y.getText());
				game.castAbility(a,xx,yy);
				popup.dispose();
				if(game.checkGameOver()!=null)
				{ 
				window.removeAll();
				window.dispose();
				window.repaint();
				window.validate();
				playerWon x = new playerWon(game.checkGameOver());
				
				}
				gamePhasePanel.refresh();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(gamePhasePanel, e1.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
				}
				
				// TODO Auto-generated method stub
				
			}
		});
	}
	else 
	{
		JFrame popup=new JFrame();
		popup.setLayout(new FlowLayout());
		popup.setVisible(true);
		popup.setBounds(700,300,200,100);
		JButton up=new JButton("UP");
		up.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					game.castAbility(a, Direction.UP);
					popup.dispose();
					if(game.checkGameOver()!=null)
					{ 
					window.removeAll();
					window.dispose();
					window.repaint();
					window.validate();
					playerWon x = new playerWon(game.checkGameOver());
					
					}
					gamePhasePanel.refresh();
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gamePhasePanel, e1.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);				}
				
			}
		});
		JButton down=new JButton("DOWN");
		down.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					game.castAbility(a, Direction.DOWN);
					popup.dispose();
					if(game.checkGameOver()!=null)
					{ 
					window.removeAll();
					window.dispose();
					window.repaint();
					window.validate();
					playerWon x = new playerWon(game.checkGameOver());
					
					}
					gamePhasePanel.refresh();
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gamePhasePanel, e1.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);				}
			}
		});
		JButton left=new JButton("LEFT");
		left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					game.castAbility(a, Direction.LEFT);
					popup.dispose();
					if(game.checkGameOver()!=null)
					{ 
					window.removeAll();
					window.dispose();
					window.repaint();
					window.validate();
					playerWon x = new playerWon(game.checkGameOver());
					
					}
					gamePhasePanel.refresh();
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gamePhasePanel, e1.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);				}
			}
		});
		JButton right=new JButton("RIGHT");
		right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					game.castAbility(a, Direction.RIGHT);
					popup.dispose();
					if(game.checkGameOver()!=null)
					{ 
					window.removeAll();
					window.dispose();
					window.repaint();
					window.validate();
					playerWon x = new playerWon(game.checkGameOver());
					
					}
					gamePhasePanel.refresh();
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gamePhasePanel, e1.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);				}
			}
		});
		popup.add(up);popup.add(right);popup.add(left);popup.add(down);
		
	}
	
	
		
	
}
	@Override
	public void oncastAbility2(GamePhasePanel s) {
		// TODO Auto-generated method stub
		
//		Champion c=game.getCurrentChampion();
//		JFrame popup=new JFrame();
//		popup.setVisible(true);
//		popup.setLocationRelativeTo(s);
//		popup.setSize(500, 200);
//		castAbilityPanel panel=new castAbilityPanel(c);
//		panel.indicator=2;
//		panel.initializeDirectionButtons();
//		panel.x=this;
//		panel.y=s;
//		popup.add(panel);
//	
		JFrame popup=new JFrame();
		popup.setVisible(true);
		Ability a=game.getCurrentChampion().getAbilities().get(1);
		popup.setLocationRelativeTo(s);
		popup.setLocationRelativeTo(s);
		popup.setSize(500, 200);
		popup.setLayout(new FlowLayout());
		String ss="ABILITY NAME: "+a.getName()+"\n"+"ABILITY TYPE: ";
		
		if(a instanceof HealingAbility)
			ss+="HEALING ABILITY"+"\n";
		else if (a instanceof DamagingAbility)
			ss+="DAMAGING ABILITY"+"\n";
		else if (a instanceof CrowdControlAbility)
			ss+="CROWD CONTROL ABILITY"+"\n";
		ss+="AREA OF EFFECT: "+a.getCastArea()+"\n"+"CAST RANGE: "+a.getCastRange()+"\n"+
			"MANA COST: "+a.getManaCost()+"\n"+"ACTION POINTS COST: "+a.getRequiredActionPoints()+"\n"+
				"BASE COOLDOWN: "+a.getBaseCooldown()+"\n"+"CURRENT COOLDOWN: "+a.getCurrentCooldown()+"\n";
		if(a instanceof HealingAbility)
			{
			HealingAbility a1=(HealingAbility)a;
			ss+="HEAL AMOUNT: "+a1.getHealAmount();
			}
		else if (a instanceof DamagingAbility)
			{
			DamagingAbility a1=(DamagingAbility)a;
			ss+="DAMAGE AMOUNT: "+a1.getDamageAmount();
			}
		else if (a instanceof CrowdControlAbility)
			{
			CrowdControlAbility a1=(CrowdControlAbility)a;
			ss+="EFFECT NAME: "+a1.getEffect().getName()+"\n"+"EFFECT DURATION: "+a1.getEffect().getDuration();
			}
		JTextArea texxt=new JTextArea();
		texxt.setText(ss);
		texxt.setEditable(false);
		popup.add(texxt);
		texxt.setBounds(0,0,500,150);
		JButton button=new JButton("CAST");
		button.setBounds(50,150,50,50);
		popup.add(button);
		button.setBounds(50,150,50,50);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				execute(a);
				popup.dispose();
				gamePhasePanel.refresh();
				
			}
		})
		;
	}
//     public void oncastAbility22(Ability a,castAbilityPanel panel,Direction d) {
//		
//		try {
//			if(a.getCastArea()!=AreaOfEffect.DIRECTIONAL)
//				throw new AbilityUseException("CAN NOT USE THIS ABILITY");
//			game.castAbility(a,d);
//			panel.y.refresh();
//		} catch (NotEnoughResourcesException|AbilityUseException|CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(panel, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
//		}
//	
//		
//		
//		
//	}
	

	@Override
	public void oncastAbility3(GamePhasePanel s) {
		// TODO Auto-generated method stub
//		Champion c=game.getCurrentChampion();
//		JFrame popup=new JFrame();
//		popup.setVisible(true);
//		popup.setLocationRelativeTo(s);
//		popup.setSize(500, 200);
//		castAbilityPanel panel=new castAbilityPanel(c);
//		panel.indicator=3;
//		panel.initializeCoordinates();
//		panel.x=this;
//		panel.y=s;
//		popup.add(panel);
//		
		JFrame popup=new JFrame();
		popup.setVisible(true);
		Ability a=game.getCurrentChampion().getAbilities().get(2);
		popup.setLocationRelativeTo(s);
		popup.setLocationRelativeTo(s);
		popup.setSize(500, 200);
		popup.setLayout(new FlowLayout());
		String ss="ABILITY NAME: "+a.getName()+"\n"+"ABILITY TYPE: ";
		
		if(a instanceof HealingAbility)
			ss+="HEALING ABILITY"+"\n";
		else if (a instanceof DamagingAbility)
			ss+="DAMAGING ABILITY"+"\n";
		else if (a instanceof CrowdControlAbility)
			ss+="CROWD CONTROL ABILITY"+"\n";
		ss+="AREA OF EFFECT: "+a.getCastArea()+"\n"+"CAST RANGE: "+a.getCastRange()+"\n"+
			"MANA COST: "+a.getManaCost()+"\n"+"ACTION POINTS COST: "+a.getRequiredActionPoints()+"\n"+
				"BASE COOLDOWN: "+a.getBaseCooldown()+"\n"+"CURRENT COOLDOWN: "+a.getCurrentCooldown()+"\n";
		if(a instanceof HealingAbility)
			{
			HealingAbility a1=(HealingAbility)a;
			ss+="HEAL AMOUNT: "+a1.getHealAmount();
			}
		else if (a instanceof DamagingAbility)
			{
			DamagingAbility a1=(DamagingAbility)a;
			ss+="DAMAGE AMOUNT: "+a1.getDamageAmount();
			}
		else if (a instanceof CrowdControlAbility)
			{
			CrowdControlAbility a1=(CrowdControlAbility)a;
			ss+="EFFECT NAME: "+a1.getEffect().getName()+"\n"+"EFFECT DURATION: "+a1.getEffect().getDuration();
			}
		JTextArea texxt=new JTextArea();
		texxt.setText(ss);
		texxt.setEditable(false);
		popup.add(texxt);
		texxt.setBounds(0,0,500,150);
		JButton button=new JButton("CAST");
		button.setBounds(50,150,50,50);
		popup.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				execute(a);
				popup.dispose();
				gamePhasePanel.refresh();
			}
		})
		;
	}
//	 public void oncastAbility33(Ability a,castAbilityPanel panel,int x,int y) {
//			
//			try {
////				if(a.getCastArea()!=AreaOfEffect.DIRECTIONAL)
////					throw new AbilityUseException("CAN NOT USE THIS ABILITY");
//					
//			    game.castAbility(a,x,y);
//				panel.y.refresh();
//				panel.y.refresh();
//			} catch (InvalidTargetException|NotEnoughResourcesException|AbilityUseException|CloneNotSupportedException e) {
//				// TODO Auto-generated catch block
//				JOptionPane.showMessageDialog(panel, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
//			}
//		
//			
//			
//			
//		}
		

	@Override
	public void onendTurn(GamePhasePanel s) {
		// TODO Auto-generated method stub
		game.endTurn();
		s.refresh();
		if(game.checkGameOver()==game.getFirstPlayer())
		{
			window.dispose();
			playerWon x=new playerWon(game.checkGameOver());
		}
		else if(game.checkGameOver()==game.getSecondPlayer())
		{
			window.dispose();
			playerWon x=new playerWon(game.checkGameOver());
		}
		
	}

	@Override
	public void onuseLeaderAbility(GamePhasePanel s) {
		// TODO Auto-generated method stub
		
		
		
		try {
			game.useLeaderAbility();
			if(game.checkGameOver()!=null)
			{ 
			window.removeAll();
			window.dispose();
			window.repaint();
			window.validate();
			playerWon x = new playerWon(game.checkGameOver());
			
			}
			s.refresh();
		} catch (LeaderAbilityAlreadyUsedException|LeaderNotCurrentException e) {
			JOptionPane.showMessageDialog(s, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		}
		
			
	}
	
	
	
	


