package dreambot.behaviour;
import dreambot.Main;
import dreambot.areas.Gl0zz3nAreas;
import dreambot.framework.Root;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;

import static org.dreambot.api.Client.getLocalPlayer;
import static dreambot.booleans.Conditions.*;

public class CombatBranch extends Root<Main> {
    @Override
    public boolean isValid() {

        return (Skills.getRealLevel(Skill.RANGED)>40)
                && (Skills.getRealLevel(Skill.ATTACK) < 40
                || Skills.getRealLevel(Skill.STRENGTH) < 40)
                && (tutorialComplete())
                || (shouldRestockArrows());



    }
}