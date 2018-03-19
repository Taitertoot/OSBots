package model;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.TaskNode;

import view.botgui;

public class anti extends TaskNode {

	@Override
	public int priority() {
		return 1;
	}
	
	@Override
	public boolean accept() {
		return getLocalPlayer().isAnimating();
	}

	@Override
	public int execute() {
		int rand = Calculations.random(6);

		if(!getWalking().isRunEnabled() && getWalking().getRunEnergy() > Calculations.random(30, 45)) {
			getWalking().toggleRun();
		}
		
		if(Calculations.random(50) == 1) {
			log("Adjusting camera position");
			getCamera().mouseRotateToYaw(Calculations.random(800,1200));
			getCamera().mouseRotateToPitch(Calculations.random(320,383));
			botgui.sleep(Calculations.random(150,250));
		}
		
		if(getMouse().isMouseInScreen()) {
			rand = Calculations.random(2);
			if(Calculations.random(2)== 1) {
				getMouse().move();
			}
			if(Calculations.random(3)== 1) {
				getMouse().move();
			}
			if(Calculations.random(4)== 1) {
				getMouse().move();
			}
		} else {
			if(Calculations.random(7) == 1) {
				getMouse().move();
			}
		}
		if(rand == 1) {
			getMouse().move(getMap().getBounds(new Tile(3098 - Calculations.random(2), 3494 + Calculations.random(1))));
			botgui.sleepUntil(() -> !getInventory().contains(botgui.jewel), Calculations.random(12000, 15000));
		}
		return Calculations.random(1111,4444);
	}

}
