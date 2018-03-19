package controller;

import org.dreambot.api.script.TaskNode;

import view.BotGUI;

public class Bank extends TaskNode {

	@Override
	public boolean accept() {
		return (getInventory().count(BotGUI.BARS) % BotGUI.BARTAX != 0);
	}

	@Override
	public int execute() {
		
		return 0;
	}

}
