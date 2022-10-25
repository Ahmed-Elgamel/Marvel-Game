package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GetPlayersNamePanel extends JPanel implements ActionListener {
	
	String firstPlayer;
	String secondPlayer;
	getPlayersNamePanelListner listner;
	
	JButton startGame;
	TextField player1;	
	TextField player2;
	
	public GetPlayersNamePanel()
	{

		//getPlayersNamePanel.setPreferredSize(getPlayersNamePanel.getMaximumSize());
		
		
		startGame=new JButton("START GAME");
		startGame.addActionListener(this);
		
		 player1=new TextField("Enter your name player 1");
		player1.setPreferredSize(new Dimension(10, 0));
		
		
		 player2=new TextField("Enter your name player 2");
		player2.setPreferredSize(new Dimension(10, 0));
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
		
		this.add(player1); this.add(player2);this.add(startGame);
		
		
	}

	public getPlayersNamePanelListner getListner() {
		return listner;
	}


	public void setListner(getPlayersNamePanelListner listner) {
		this.listner = listner;
	}


	public void displayErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(this, message,"ERROR!",JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 //TODO Auto-generated method stub
		
		 if(e.getSource()==startGame)
		{
			if(player1.getText().equals("")||player2.getText().equals(""))
				displayErrorMessage("Please enter your name!!!");
			else
				{
				listner.onStartGame(this);
				}
		}
		
			
		
	}

	public TextField getPlayer1() {
		return player1;
	}

	public TextField getPlayer2() {
		return player2;
	}
	
	
	

}
