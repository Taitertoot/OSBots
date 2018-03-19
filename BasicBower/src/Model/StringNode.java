package Model;


import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;

import View.BotGUI;

public class StringNode extends TaskNode{

	@Override
	public int priority() {
		return 2;
	}
	@Override
	public boolean accept() {
		return getInventory().count("Bow string") == 14 && 
				getInventory().count(BotGUI.bow) == 14 && 
				!getLocalPlayer().isAnimating();
	}

	@Override
	public int execute() {
		log("Stringing bows");
		if(!getTabs().isOpen(Tab.INVENTORY)) {
			getTabs().openWithFKey(Tab.INVENTORY);
		}
		if(getInventory().isItemSelected())
			getInventory().deselect();
		
		if(Calculations.random(7) == 0) {
			getMouse().click(getInventory().slotBounds(13));
			BotGUI.sleepUntil(() -> getInventory().isItemSelected(), 500);
			getMouse().click(getInventory().slotBounds(14));
		} else {
			getMouse().click(getInventory().slotBounds(14));
			BotGUI.sleepUntil(() -> getInventory().isItemSelected(), 500);
			getMouse().click(getInventory().slotBounds(13));
		}
		BotGUI.sleepUntil(() -> getWidgets().getWidget(270) != null, 1000);
		return 0;
	}

}
