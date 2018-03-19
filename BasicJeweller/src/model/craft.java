package model;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.TaskNode;

import view.botgui;

public class craft extends TaskNode {

	@Override
	public int priority() {
		return 3;
	}
	
	@Override
	public boolean accept() {
		return getInventory().contains("Bracelet mould") && 
				getInventory().count(botgui.jewel) >= 13 && 
				getInventory().count("Gold bar") >= 13 && !getLocalPlayer().isAnimating();
	}

	@Override
	public int execute() {

		Tile dest = new Tile(Calculations.random(3105, 3108), Calculations.random(3497, 3500));
		if(getGameObjects().closest(16469) != null) {
			if(!getGameObjects().closest(16469).isOnScreen()) {
				getWalking().clickTileOnMinimap(dest);
				botgui.sleepUntil(()->getGameObjects().closest(16469).isOnScreen(), 3000);
			}
			botgui.sleep(Calculations.random(650,1200));
			if(getBank().isOpen()) {
				getBank().close();
			}
			if(Calculations.random(2) == 1) {
				botgui.sleep(Calculations.random(300));
				getMouse().move();
			}
			botgui.sleep(Calculations.random(400));
			if(!getGameObjects().closest(16469).interact())
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
		return Calculations.random(400, 700);
	}

}
