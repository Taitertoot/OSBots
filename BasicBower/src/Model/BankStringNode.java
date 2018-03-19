package Model;

import java.awt.Rectangle;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;

import View.BotGUI;

public class BankStringNode extends TaskNode {
	@Override
	public int priority()
	{
		return 4;
	}
	@Override
	public boolean accept() {
		return !getInventory().contains(BotGUI.bow) || !getInventory().contains("Bow string");
	}

	public void checkCamera() {
		if(getCamera().getYaw() == 0) {
			if(!getMouse().isMouseInScreen()) {
				getMouse().move();
			}
			if(getGameObjects().closest(10060) != null) {
				getCamera().mouseRotateToYaw(Calculations.random(300, 800));
				getCamera().mouseRotateToPitch(Calculations.random(170, 380));
			} else if(getGameObjects().closest(7409) != null){
				getCamera().mouseRotateToYaw(Calculations.random(1400, 1700));
				getCamera().mouseRotateToPitch(Calculations.random(170, 380));
			}
		}
	}
	
	public void checkBank() {
		if((getBank().count(BotGUI.bow) < 14 || getBank().count("Bow string") < 14) && getBank().isOpen()) {
			System.exit(0);
		}
	}
	
	public void deposit() {
		log("Depositing everything");
		if(!getInventory().isEmpty())
			getBank().depositAllItems();
		BotGUI.sleep(Calculations.random(300));
	}
	
	public void checkSelection() {
		if(getInventory().isItemSelected())
			getInventory().deselect();
	}
	
	public void withdrawBank() {
		log("Withdrawing from bank");
		if(Calculations.random(25) == 1) {
			getBank().withdraw("Bow string", 14);
			//BotGUI.sleepUntil(() -> getInventory().contains("Bow string"), 1000);
			BotGUI.sleep(Calculations.random(250));
			if(Calculations.random(4)==1)
				getBank().withdraw(BotGUI.bow, 14);
			else
				getBank().withdrawAll(BotGUI.bow);
			//BotGUI.sleepUntil(() -> getInventory().contains(BotGUI.bow), 1000);
		} else {
			getBank().withdraw(BotGUI.bow, 14);
			//BotGUI.sleepUntil(() -> getInventory().contains(BotGUI.bow), 1000);
			BotGUI.sleep(Calculations.random(250));
			if(Calculations.random(3) == 1)
				getBank().withdraw("Bow string", 14);
			else
				getBank().withdrawAll("Bow string");
			//BotGUI.sleepUntil(() -> getInventory().contains("Bow string"), 1000);
		}
	}
	
	@Override
	public int execute() {
		log("Banking");
		checkCamera();
		checkSelection();
		if(!getBank().isOpen()) {
			if(!getBank().openClosest()) {
				if(getGameObjects().closest(4483) != null) {
					getMouse().move(getGameObjects().closest(4483).getClickablePoint());
					BotGUI.sleep(Calculations.random(110,220));
					getMouse().click();
					if(Calculations.random(44) == 1)
						getMouse().move(getInventory().slotBounds(Calculations.random(2, 20)));
					else if(Calculations.random(3) == 1)
						getMouse().move();
					BotGUI.sleepUntil(() -> getBank().isOpen(), Calculations.random(402,811));
				} else {
					return Calculations.random(200,500);
				}
			}
		}
		deposit();
		BotGUI.sleepUntil(() -> getBank().isOpen(), 2500);
		checkBank();
		withdrawBank();
		if(Calculations.random(3) == 1) {
			getBank().close();
		} else {
			getMouse().click(new Rectangle(475, 12, 25, 22));
		}
		if(Calculations.random(8)==1)
			getMouse().move();
		log("Done banking");
		BotGUI.sleepUntil(() -> getInventory().contains("Bow string") && getInventory().contains(BotGUI.bow), 2000);
		return Calculations.random(120, 333);
	 }
	
}