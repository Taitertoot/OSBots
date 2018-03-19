package view;

import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import org.dreambot.api.utilities.Timer;

import controller.Bank;
import controller.Smith;
import controller.Walker;

@ScriptManifest(author = "Taiterbot", name = "TaiterSmith", version = 1.0, description = "Smiths bars", category = Category.SMITHING)

public class BotGUI extends TaskScript{

	public static String BARS = "Bronze bar";
	public static int BARTAX = 3;
	private Timer timer;
	
	@Override
	public void onStart() {
		log("Bot started");
		getSkillTracker().start(Skill.SMITHING);
		timer = new Timer();
		
		addNodes(new Walker(), new Bank(), new Smith());
		
	}
}
