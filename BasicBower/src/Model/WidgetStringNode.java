package Model;

import java.awt.Rectangle;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;

import View.BotGUI;

public class WidgetStringNode extends TaskNode{
	@Override
	public int priority() {
		return 3;
	}
	
	@Override
	public boolean accept() {
		return getWidgets().getWidget(270) != null && 
				getWidgets().getWidget(270).getChild(14) != null;
		}

	@Override
	public int execute() {
		log("Widget is active, attempting to execute");
		
		if(Calculations.random(22) == 1) {
			if(Calculations.random(2) == 1)
				getKeyboard().type("1", false);
			else
				getKeyboard().type(" ", false);
			if(Calculations.random(7) == 1)
			{
				getMouse().move();
				if(Calculations.random(7) == 1)
					getMouse().move();
			} else {
				getMouse().moveMouseOutsideScreen();
			}
			
		} else {
			if(Calculations.random(3)==1)
				getMouse().move(new Rectangle(140, 300, 200, 150));
			getMouse().move(getWidgets().getWidget(270).getChild(14).getRectangle());
			BotGUI.sleep(Calculations.random(250,500));
			getMouse().click();
			BotGUI.sleep(Calculations.random(100,250));
			if(Calculations.random(4) == 1)
			{
				getMouse().move();
				if(Calculations.random(2) == 1)
					getMouse().move();
				if(Calculations.random(2) == 1)
					getMouse().move();
				if(Calculations.random(2) == 1)
					getMouse().move();
			} else {
				getMouse().moveMouseOutsideScreen();
			}
		}
		BotGUI.sleepUntil(() -> getLocalPlayer().isAnimating(), 3000);
		return Calculations.random(1000,2222);
	}

}
