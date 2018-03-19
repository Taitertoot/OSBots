package controller;

import org.dreambot.api.script.TaskNode;

import view.BotGUI;

public class Smith extends TaskNode{

	@Override
	public boolean accept() {
		return getInventory().count(BotGUI.BARS) > BotGUI.BARTAX && !getLocalPlayer().isAnimating();
	}

	@Override
	public int execute() {
		
		return 0;
	}

}
