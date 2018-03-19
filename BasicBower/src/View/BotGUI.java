package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/*
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
*/
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import org.dreambot.api.utilities.Timer;

import Model.AntiStringNode;
import Model.BankStringNode;
import Model.InterruptNode;
import Model.StringNode;
import Model.WidgetStringNode;
import org.dreambot.api.script.Category;

@ScriptManifest(author = "Taiterbot", name = "TaiterBow", version = 1.3, description = "AIO Fletching", category = Category.FLETCHING)
public class BotGUI extends TaskScript {
	
//	public String wood = ""; 
	public static String bow = "Magic longbow (u)";
	public boolean cutLog = false, stringBow = false;
	private Timer timer;
	
	public void onPaint(Graphics g){
    	g.setColor(Color.GREEN);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 14));
        g.drawString("Running: " + timer.elapsed()/1000/60/60 + ":" + timer.elapsed()/1000/60%60 + ":" + timer.elapsed()/1000%60, 10, 35);
    	g.drawString("cut / hour: " + timer.getHourlyRate((int) ((getSkillTracker().getGainedExperience(Skill.FLETCHING))/91)) + 
    			" [CUT: (" + getSkillTracker().getGainedExperience(Skill.FLETCHING)/91 +")]", 10, 50);
    	g.drawString("xp / hour: " + getSkillTracker().getGainedExperiencePerHour(Skill.FLETCHING) + " [GAINED: (" + getSkillTracker().getGainedExperience(Skill.FLETCHING) +")]", 10, 65);
    	g.drawString("TTL: " + (getSkillTracker().getTimeToLevel(Skill.FLETCHING))/1000/60/60 + ":" + getSkillTracker().getTimeToLevel(Skill.FLETCHING)/1000/60%60 + 
    			":" + getSkillTracker().getTimeToLevel(Skill.FLETCHING)/1000%60 +" [CURRENT: " + getSkillTracker().getStartLevel(Skill.FLETCHING) + 
    			"(" + getSkillTracker().getGainedLevels(Skill.FLETCHING) + ")]", 10, 80);    	
    }
	@Override
	public void onStart() {
		log("Welcome to Taite's AIO Fletching script.");
		getSkillTracker().start(Skill.FLETCHING);
		//getRandomManager().disableSolver(RandomEvent.ZOOM_SOLVER);
		timer = new Timer();
		if(!stringBow) {
			addNodes(new WidgetStringNode(), new StringNode(), new AntiStringNode(), 
				new BankStringNode(), new InterruptNode());
		}
	}
}