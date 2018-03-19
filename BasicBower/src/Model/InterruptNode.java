package Model;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;

import View.BotGUI;

public class InterruptNode extends TaskNode{

	@Override
	public int priority() {
		return 0;
	}
	
	@Override
	public boolean accept() {
		return (getInventory().count(BotGUI.bow) < 14 && getInventory().contains(BotGUI.bow)) &&
				(getInventory().count("Bow string") < 14 && getInventory().contains("Bow string")) &&
				!getLocalPlayer().isAnimating();
	}

	@Override
	public int execute() {
		log("Stringing interrupted, attempting to resume");
		if(getInventory().isItemSelected())
			getInventory().deselect();
		if(!getLocalPlayer().isAnimating()) {
			BotGUI.sleepUntil(() -> getLocalPlayer().isAnimating(), Calculations.random(2500,5000));
			if(getLocalPlayer().isAnimating()) {
				return Calculations.random(10,100);
			}
		}
		if(Calculations.random(2) == 0) {
			getMouse().click(getInventory().slotBounds(getInventory().slot((BotGUI.bow))));
			BotGUI.sleepUntil(() -> getInventory().isItemSelected(), 500);
			getMouse().click(getInventory().slotBounds(getInventory().slot(("Bow string"))));
		} else {
			getMouse().click(getInventory().slotBounds(getInventory().slot(("Bow string"))));
			BotGUI.sleepUntil(() -> getInventory().isItemSelected(), 500);
			getMouse().click(getInventory().slotBounds(getInventory().slot((BotGUI.bow))));
		}
		BotGUI.sleepUntil(() -> getWidgets().getWidget(270) != null, 1000);

		return Calculations.random(200);
	}

}
