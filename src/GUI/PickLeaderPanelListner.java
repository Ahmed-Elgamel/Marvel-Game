package GUI;

import javax.swing.JButton;

import model.world.Champion;

public interface PickLeaderPanelListner {
	
	public void onPickLeader(PickLeaderPanel s,Champion leaderChosen);
	public void onNext(PickLeaderPanel s);

}
