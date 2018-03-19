package Model;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;

import View.BotGUI;

public class AntiStringNode extends TaskNode{

	@Override
	public int priority() {
		return 1;
	}
	
	@Override
	public boolean accept() {
		return getInventory().contains(BotGUI.bow) && 
				getInventory().contains("Bow string") &&
				getLocalPlayer().isAnimating();
	}


	public void sleepyTime(int rand) {
		if(rand == 1) {
			log("Short sleep");
			BotGUI.sleepUntil(() -> !getInventory().contains(BotGUI.bow), 16000);
			BotGUI.sleep(Calculations.random(5000, 20000));
		}
		else if(rand == 14) {
			log("Longest sleep");
			BotGUI.sleep(Calculations.random(45000,143000));
		}
	}
	
	public boolean hoverGEBank(int rand) {
		if(getGameObjects().closest(10060) != null && rand == 1) {
			if(Calculations.random(2) == 1)
			{
				if(Calculations.random(2)==1)
	    			getMouse().move();
				getMouse().move();
				log("Moving mouse in AntiNode");
			}
			
			if(Calculations.random(4)==1)
			{
				getMouse().move();
				log("Moving mouse in AntiNode");
			}
			BotGUI.sleep(Calculations.random(300));
			getMouse().move(getGameObjects().closest(10060).getClickablePoint());
			BotGUI.sleepUntil(() -> !getInventory().contains(BotGUI.bow), 15000);
			return true;
	    }
		return false;
	}
	
	public boolean hoverBankBooth1(int rand) {
		if(getGameObjects().closest(7409) != null && rand == 2)
	    {
	    	if(Calculations.random(2) == 1)
			{
	    		if(Calculations.random(2)==1)
	    			getMouse().move();
				getMouse().move();
				log("Moving mouse in AntiNode");
			}
			
			if(Calculations.random(4)==1)
			{
				getMouse().move();
				log("Moving mouse in AntiNode");
			}
			BotGUI.sleep(Calculations.random(300));
			getMouse().move(getGameObjects().closest(7409).getClickablePoint());
			BotGUI.sleepUntil(() -> !getInventory().contains(BotGUI.bow), 15000);
			return true;
	    }
		return false;
	}
	
	public boolean hoverBankBooth2(int rand)
	{
		if(getGameObjects().closest(7478) != null && rand == 1)
	    {
	    	if(Calculations.random(2) == 1)
			{
	    		if(Calculations.random(2)==1)
	    			getMouse().move();
				getMouse().move();
				log("Moving mouse in AntiNode");
			}
			
			if(Calculations.random(4)==1)
			{
				getMouse().move();
				log("Moving mouse in AntiNode");
			}
			BotGUI.sleep(Calculations.random(300));
			getMouse().move(getGameObjects().closest(7478).getClickablePoint());
			BotGUI.sleepUntil(() -> !getInventory().contains(BotGUI.bow), 15000);
			return true;
	    }
		return false;
	}
	
	@Override
	public int execute() {
		int rand = Calculations.random(5);

		if(getMouse().isMouseInScreen()) {
			rand = Calculations.random(2);
			if(Calculations.random(2)== 1) {
				getMouse().move();
			}
			if(Calculations.random(2)== 1) {
				getMouse().move();
			}
			if(Calculations.random(2)== 1) {
				getMouse().move();
			}
		    hoverGEBank(rand);
		} else
			rand = Calculations.random(3);
	    hoverGEBank(rand);
	    
		return Calculations.random(5555);
	}	
}
