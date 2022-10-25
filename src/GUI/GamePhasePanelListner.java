package GUI;

import javax.swing.JButton;

import model.world.Champion;

public interface GamePhasePanelListner {
	
	public void onLabelHover(GamePhasePanel s,Champion c);
	public void onmoveUp(GamePhasePanel s);
	public void onmoveDown(GamePhasePanel s);
	public void onmoveRight(GamePhasePanel s);
	public void onmoveLeft(GamePhasePanel s);
	public void onattack(GamePhasePanel s);
	public void oncastAbility1(GamePhasePanel s);
	public void oncastAbility2(GamePhasePanel s);
	public void oncastAbility3(GamePhasePanel s);
	public void onendTurn(GamePhasePanel s);
	public void onuseLeaderAbility(GamePhasePanel s);
	
	
	

}
