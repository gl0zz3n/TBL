package dreambot.behaviour;

import dreambot.Main;
import dreambot.areas.Gl0zz3nAreas;
import dreambot.constants.EquipmentList;
import dreambot.framework.Root;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import static org.dreambot.api.Client.getLocalPlayer;
import static dreambot.booleans.Conditions.*;
import static org.dreambot.api.methods.MethodProvider.*;
public class RangingBranch extends Root<Main> {
    @Override
    public boolean isValid() {

        return (Skills.getRealLevel(Skill.RANGED)<50)
                && !Gl0zz3nAreas.TUTORIAL_ISLAND.contains(getLocalPlayer())
                && (botHasArrows());

    }
}
