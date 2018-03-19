package model;

import java.awt.Rectangle;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.TaskNode;

import view.botgui;

public class bank extends TaskNode {

	@Override
	public int priority() {
		return 4;
	}
	
	@Override
	public boolean accept() {
		return (getInventory().count("Ruby bracelet") == 13 || !getInventory().contains("Bracelet mould"));
	}

	public void checkCamera() {
		if(getCamera().getYaw() == 0) {
			if(!getMouse().isMouseInScreen()) {
				getMouse().move();
			}
			log("Adjusting camera position");
			getCamera().mouseRotateToYaw(Calculations.random(800,1200));
			getCamera().mouseRotateToPitch(Calculations.random(320,383));
		}
	}
	
	public void withdrawItems() {
		
		if(!getInventory().contains("Bracelet mould")) {
			getBank().withdraw("Bracelet mould");
			botgui.sleep(Calculations.random(10,50));
		}
		
		
		if(Calculations.random(20) == 1) {
			getBank().withdraw(botgui.jewel, 13);
			botgui.sleep(Calculations.random(100,250));
			getBank().withdraw("Gold bar", 13);
		} else {
			getBank().withdraw("Gold bar", 13);
			botgui.sleep(Calculations.random(100,250));
			getBank().withdraw(botgui.jewel, 13);
		}
		botgui.sleep(Calculations.random(200,400));
		
	}
	
	public void depositItems() {
		botgui.sleep(Calculations.random(100,200));
		getBank().depositAllItems();
		botgui.sleepUntil(() -> !getInventory().contains("Bracelet mould") && getBank().contains("Bracelet mould"), 3000);
		botgui.sleep(Calculations.random(100,250));
		//getMouse().move(new Rectangle(260, 75, 190, 40));
		botgui.sleep(Calculations.random(150,350));
		
	}
	
	public void checkBank() {
		if(getBank().getCurrentTab() != 3)
			getBank().openTab(3);
		botgui.sleep(Calculations.random(20,50));
		if(getBank().count(botgui.jewel) < 13 || getBank().count("Gold bar") <13) {
			System.exit(1);
		}
	}
	
	public void initWalking() {
		Tile dest = new Tile(Calculations.random(3105, 3108), Calculations.random(3497, 3500));
		getWalking().clickTileOnMinimap(dest);
	}
	
	public void openBank() {
		Rectangle depall = new Rectangle(340, 230, 140, 130);

		if(!getBank().isOpen()) {
			if(getGameObjects().closest(6943) != null) {
				if(!getGameObjects().closest(6943).isOnScreen()) {
					getWalking().clickTileOnMinimap(new Tile(3098 - Calculations.random(2), 3494 + Calculations.random(1)));		
					if(Calculations.random(2) == 1) {
						getMouse().move();
					}
				}
				botgui.sleepUntil(() -> getGameObjects().closest(6943).isOnScreen(), Calculations.random(1500, 3000));
				
				if(!getGameObjects().closest(6943).interact())
				{
					botgui.sleep(Calculations.random(300));
					getGameObjects().closest(6943).interact();
				}
				
				if(Calculations.random(2) == 1)
				{
					getCamera().mouseRotateToYaw(Calculations.random(800,1200));
					botgui.sleep(Calculations.random(100,300));
				}
				if(Calculations.random(2)==1)
					getMouse().move(depall);
				else
					getMouse().move();
				botgui.sleepUntil(() -> getBank().isOpen(), 500);
			}
		}
	}
	
	@Override
	public int execute() {
		if(Calculations.random(15) == 1) {
			checkCamera();
		}

		openBank();

		
		if(getBank().isOpen()) {
			
			checkBank();
			
			depositItems();

			withdrawItems();
			
			if(Calculations.random(5) == 1) {
				getMouse().move();
			}
			
			//initWalking();
		}

		botgui.sleepUntil(() -> getInventory().contains(botgui.jewel) && getInventory().contains("Gold bar") && getInventory().contains("Bracelet mould"), 2500);
		return Calculations.random(350, 600);
	}

}
