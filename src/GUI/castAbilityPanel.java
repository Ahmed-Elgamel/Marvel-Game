//package GUI;
//
//import java.awt.BorderLayout;
//import java.awt.Button;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Rectangle;
//import java.awt.TextArea;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.HashMap;
//
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//
//import model.abilities.Ability;
//import model.abilities.AreaOfEffect;
//import model.abilities.CrowdControlAbility;
//import model.abilities.DamagingAbility;
//import model.abilities.HealingAbility;
//import model.effects.EffectType;
//import model.world.Champion;
//import model.world.Direction;
//
//public class castAbilityPanel extends JPanel implements ActionListener{
//	
//	int indicator;
//	TextArea text;
//	HashMap<JButton, Ability> hashmap;
//	String []abilities;
//	JButton cast;
//	Controller listner;
//	JPanel abilitybtns;
//	JButton next;
//	Ability selectedAbility;
//	Controller x;
//	GamePhasePanel y;
//	JRadioButton up;
//	JRadioButton down;
//	JRadioButton left;
//	JRadioButton right;
//	JPanel directions;
//	TextArea Xcoordinate;
//	TextArea Ycoordinate;
//	public castAbilityPanel(Champion c)
//	{
//	
//		ButtonGroup groupAbilities=new ButtonGroup();
//		//this.setSize(new Dimension(700, 700));
//		//this.setBounds(100, 100, 100, 100);
//		hashmap=new HashMap<>();
//		abilities=new String[c.getAbilities().size()];
//		text=new TextArea();
//		 abilitybtns=new JPanel(new GridLayout(0,4,5,5));
//		int i=0;
//		for(Ability a:c.getAbilities())
//		{
//			abilities[i++]=a.getName();
//			JButton x=new JButton(a.getName());
//			x.addActionListener(this);
//			groupAbilities.add(x);
//			abilitybtns.add(x);
//			hashmap.put(x, a);
//			
//		}
//		next=new JButton("NEXT");
//		next.addActionListener(this);
//		abilitybtns.add(next);
//		this.add(abilitybtns, BorderLayout.CENTER);
//	}
//
//
//
//
//
//
//
//
//
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource()==next)
//		{
//			if(selectedAbility==null)
//			{
//				JOptionPane.showMessageDialog(this, "CHOOSE AN ABILITY","ERROR!",JOptionPane.ERROR_MESSAGE);
//			}
//			else if((indicator==1&&selectedAbility.getCastArea()==AreaOfEffect.DIRECTIONAL||selectedAbility.getCastArea()==AreaOfEffect.SINGLETARGET)||
//					(indicator==2&&selectedAbility.getCastArea()!=AreaOfEffect.DIRECTIONAL)||
//					(indicator==3&&selectedAbility.getCastArea()!=AreaOfEffect.SINGLETARGET))
//				JOptionPane.showMessageDialog(this, "CAN NOT USE THIS ABILITY","ERROR!",JOptionPane.ERROR_MESSAGE);
//			else if(selectedAbility.getCastArea()==AreaOfEffect.SURROUND||
//					selectedAbility.getCastArea()==AreaOfEffect.TEAMTARGET||
//					selectedAbility.getCastArea()==AreaOfEffect.SELFTARGET)
//			{
//				x.oncastAbility11(selectedAbility,this);
//			}
//			
//				if(e.getSource()==up)
//					{
//					x.oncastAbility22(selectedAbility, this, Direction.UP);
//					return;
//					}
//				else if(e.getSource()==down)
//					x.oncastAbility22(selectedAbility, this, Direction.DOWN);
//				else if(e.getSource()==left)
//					x.oncastAbility22(selectedAbility, this, Direction.LEFT);
//				else if(e.getSource()==right)
//					x.oncastAbility22(selectedAbility, this, Direction.RIGHT);
//				
//			else if(selectedAbility.getCastArea()==AreaOfEffect.DIRECTIONAL)
//			{
//				x.oncastAbility33(selectedAbility, this, Integer.parseInt(Xcoordinate.getText()), Integer.parseInt(Ycoordinate.getText()));
//			}
//			else if(indicator==2)
//			{
//				
//			}
//			
//		}
//		else 
//		{
//			Ability a=hashmap.get(e.getSource());
//			if (a==null)
//			{
//				System.out.println(a);
//				return;
//			}
//			selectedAbility=a;
//			String s="ABILITY NAME: "+a.getName()+"\n"+"ABILITY TYPE: ";
//			
//			if(a instanceof HealingAbility)
//				s+="HEALING ABILITY"+"\n";
//			else if (a instanceof DamagingAbility)
//				s+="DAMAGING ABILITY"+"\n";
//			else if (a instanceof CrowdControlAbility)
//				s+="CROWD CONTROL ABILITY"+"\n";
//			s+="AREA OF EFFECT: "+a.getCastArea()+"\n"+"CAST RANGE: "+a.getCastRange()+"\n"+
//				"MANA COST: "+a.getManaCost()+"\n"+"ACTION POINTS COST: "+a.getRequiredActionPoints()+"\n"+
//					"BASE COOLDOWN: "+a.getBaseCooldown()+"\n"+"CURRENT COOLDOWN: "+a.getCurrentCooldown();
//			if(a instanceof HealingAbility)
//				{
//				HealingAbility a1=(HealingAbility)a;
//				s+="HEAL AMOUNT: "+a1.getHealAmount();
//				}
//			else if (a instanceof DamagingAbility)
//				{
//				DamagingAbility a1=(DamagingAbility)a;
//				s+="DAMAGE AMOUNT: "+a1.getDamageAmount();
//				}
//			else if (a instanceof CrowdControlAbility)
//				{
//				CrowdControlAbility a1=(CrowdControlAbility)a;
//				s+="EFFECT NAME: "+a1.getEffect().getName()+"/n"+"EFFECT DURATION: "+a1.getEffect().getDuration();
//				}
//			text.setText(s);
//			this.add(text);
//			this.add(text, BorderLayout.WEST);
//			this.validate();
//			this.repaint();
//		}
//	}
//
//	
//	public void initializeDirectionButtons()
//	{
//		    up=new JRadioButton("UP");
//		    up.addActionListener(this);
//			down=new JRadioButton("DOWN");
//			down.addActionListener(this);
//			left=new JRadioButton("LEFT");
//			left.addActionListener(this);
//			right=new JRadioButton("RIGHT");
//			right.addActionListener(this);
//			ButtonGroup group=new ButtonGroup(); 
//
//			group.add(right);group.add(left);group.add(down);group.add(up);
//			directions=new JPanel();
//			directions.add(up);directions.add(down);directions.add(left);directions.add(right);
//			this.add(directions);
//	}
//	
//	
//	public void initializeCoordinates()
//	{
//		Xcoordinate=new TextArea("X COORDINATE");
//		Xcoordinate.setBounds(new Rectangle(5, 5));
//		Ycoordinate=new TextArea("Y COORDINATE");
//		Ycoordinate.setBounds(new Rectangle(5, 5));
//		JPanel layout=new JPanel(new FlowLayout());
//		layout.add(Xcoordinate);
//		layout.add(Ycoordinate);
//		this.add(layout, BorderLayout.EAST);
//		
//	}
//	
//	
//}
