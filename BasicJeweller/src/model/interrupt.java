package model;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.TaskNode;

import view.botgui;

public class interrupt extends TaskNode {

	@Override
	public int priority() {
		return 2;
	}
	
	@Override
	public boolean accept() {
		return getInventory().contains("Bracelet mould") && 
				getInventory().count(botgui.jewel) < 13 && getInventory().contains(botgui.jewel) &&
				getInventory().count("Gold bar") < 13 && getInventory().contains("Gold bar") && 
				(getInventory().count("Gold bar")+getInventory().count("Ruby bracelet") == 13) &&
				!getLocalPlayer().isAnimating();
	}

	@Override
	public int execute() {
		if(getInventory().isItemSelected()) {
			getInventory().deselect();
		}
		if(!getLocalPlayer().isAnimating()) {
			botgui.sleepUntil(() -> getLocalPlayer().isAnimating(), 1500);
			if(getLocalPlayer().isAnimating()) {
				return Calculations.random(10,100);
			}
		}
		
		log("Not a fluke, bot has been interrupted");
		
		Tile dest = new Tile(Calculations.random(3105, 3108), Calculations.random(3497, 3500));
		if(getGameObjects().closest(16469) != null) {
			if(!getGameObjects().closest(16469).isOnScreen()) {
				getWalking().clickTileOnMinimap(dest);
			}
			if(Calculations.random(2) == 1) {
				botgui.sleep(Calculations.random(300));
				getMouse().move();
			}
			botgui.sleep(Calculations.random(700));
			
			getGameObjects().closest(16469).interact();
			
			botgui.sleepUntil(() -> getWidgets().getWidget(446) != null, Calculations.random(3500, 5000));
			
			if(getWidgets().getWidget(446) != null && getWidgets().getWidget(446).getChild(50) != null) {
				botgui.sleep(Calculations.random(150,500));
				if(Calculations.random(35) == 1) {
					getCamera().mouseRotateToYaw(Calculations.random(800,1200));
					if(Calculations.random(15)==1) {
						getCamera().mouseRotateToYaw(Calculations.random(800,1200));
						getCamera().mouseRotateToPitch(Calculations.random(250,383));
					}
				}
				if(Calculations.random(2) == 1) {
					botgui.sleep(Calculations.random(300));
					getMouse().move();
				}
				if(getInventory().count(botgui.jewel) <= 10)
					getWidgets().getWidget(446).getChild(50).interact("Make-10");
				else
					getWidgets().getWidget(446).getChild(50).interact("Make-All");
				
				botgui.sleep(100);
				if(Calculations.random(35) == 1) {
					getCamera().mouseRotateToYaw(Calculations.random(800,1200));
					if(Calculations.random(15)==1) {
						getCamera().mouseRotateToYaw(Calculations.random(800,1200));
						getCamera().mouseRotateToPitch(Calculations.random(250,383));
					}
				}
				if(Calculations.random(2) == 1) {
					botgui.sleep(Calculations.random(300));
					getMouse().move();
				}
			}
		}
		botgui.sleepUntil(()->getLocalPlayer().isAnimating(), 1500);
		return Calculations.random(200,500);
		
	}

}
