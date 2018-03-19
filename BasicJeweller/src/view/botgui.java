package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import org.dreambot.api.utilities.Timer;

import model.*;


@ScriptManifest(author = "Taiterbot", name = "TaiterJewels", version = 1.3, description = "AIO CRAFTING", category = Category.CRAFTING)
public class botgui extends TaskScript {
	
	public static String jewel = "Ruby";
	private Timer timer;
	
	public void onPaint(Graphics g){
    	g.setColor(Color.GREEN);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 14));
        g.drawString("Running: " + timer.elapsed()/1000/60/60 + ":" + timer.elapsed()/1000/60%60 + ":" + timer.elapsed()/1000%60, 10, 35);
    	g.drawString("cut / hour: " + timer.getHourlyRate((int) ((getSkillTracker().getGainedExperience(Skill.CRAFTING))/80)) + 
    			" [CUT: (" + getSkillTracker().getGainedExperience(Skill.CRAFTING)/80 +")]", 10, 50);
    	g.drawString("xp / hour: " + getSkillTracker().getGainedExperiencePerHour(Skill.CRAFTING) + " [GAINED: (" + getSkillTracker().getGainedExperience(Skill.CRAFTING) +")]", 10, 65);
    	g.drawString("TTL: " + (getSkillTracker().getTimeToLevel(Skill.CRAFTING))/1000/60/60 + ":" + getSkillTracker().getTimeToLevel(Skill.CRAFTING)/1000/60%60 + 
    			":" + getSkillTracker().getTimeToLevel(Skill.CRAFTING)/1000%60 +" [CURRENT: " + getSkillTracker().getStartLevel(Skill.CRAFTING) + 
    			"(" + getSkillTracker().getGainedLevels(Skill.CRAFTING) + ")]", 10, 80);    	
    }
	@Override
	public void onStart() {
		log("Welcome to Taite's AIO Hunting script.");
		getSkillTracker().start(Skill.CRAFTING);
		//getRandomManager().disableSolver(RandomEvent.ZOOM_SOLVER);
		timer = new Timer();
		addNodes(new bank(), new anti(), new craft(), new interrupt());
		
	}
}