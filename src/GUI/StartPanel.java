package GUI;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener{
	
	JButton startGame;
	JButton options;
	JButton credit;
	private JButton exit;
	private startPanelListner listner;
	
	
	public StartPanel()
	{
		this.setPreferredSize(new Dimension(700, 500));
		BoxLayout layout=new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setBackground(Color.black);
		
		
		
		 startGame=new JButton("Play");
//		startGame.setAlignmentX(LEFT_ALIGNMENT);
//		startGame.setAlignmentY(TOP_ALIGNMENT);
		startGame.setSize(new Dimension(1, 200));
		startGame.setBackground(Color.LIGHT_GRAY);
		startGame.setBorderPainted(true);
		startGame.addActionListener(this);
		
		options=new JButton("Options");
	//	options.setBounds(200, 300, 100, 200);
		startGame.setSize(new Dimension(300, 300));
		options.setBackground(Color.LIGHT_GRAY);
		
		credit=new JButton();
		credit.setText("Credits");
        //credit.setBounds(300, 400, 100, 200);
        startGame.setSize(new Dimension(300, 300));
        credit.setBackground(Color.LIGHT_GRAY);
        
		exit=new JButton();
		exit.setText("Exit");
		//exit.setBounds(400, 500, 100, 200);
		startGame.setSize(new Dimension(300, 300));
		exit.setBackground(Color.LIGHT_GRAY);
		exit.addActionListener(this);
		
		
		//this.setAlignmentX(this.CENTER_ALIGNMENT);
		this.add(startGame);
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		this.add(options);
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		this.add(credit);
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		this.add(exit);
		this.setAlignmentX(this.CENTER_ALIGNMENT);
		
		
		
		
		
		
	}
	
	public startPanelListner getListner() {
		return listner;
	}


	public void setListner(startPanelListner listner) {
		this.listner = listner;
	}


	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==exit)
			listner.onExit(this);
		else if(e.getSource()==startGame)
			listner.onPlayGame(this);
		
		
	}
	
	
	

}
