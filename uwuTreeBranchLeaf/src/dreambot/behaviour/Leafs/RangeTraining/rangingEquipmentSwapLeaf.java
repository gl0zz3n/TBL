package dreambot.behaviour.Leafs.RangeTraining;

import dreambot.Main;
import dreambot.constants.EquipmentList;
import dreambot.framework.Leaf;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.event.ScriptEvent;

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;


public class rangingEquipmentSwapLeaf extends Leaf<Main> {



    @Override
    public boolean isValid() {
        return ( !Equipment.contains(getAppropriateBow())
                || !Equipment.contains(x -> x.getName().contains("arrow")) )

                && ( Inventory.contains(x -> x.getName().contains("arrow"))
                || Inventory.contains(getAppropriateBow()) )

                || ( (Skills.getRealLevel(Skill.RANGED)>=40)
                && !Equipment.contains(getAppropriateLegArmor())
                && Inventory.contains(getAppropriateLegArmor()) );
    }

    @Override
    public int onLoop() {
        int appropriateBowID = getAppropriateBow();
        int appropriateHeadGearID = getAppropriateHeadGear();
        int appropriateBodyArmorID = getAppropriateBodyArmor();
        int appropriateLegArmorID = getAppropriateLegArmor();
        int appropriateVambracesID = getAppropriateVambraces();

        if(Bank.isOpen()) {
            Bank.close();
            return 900;
        }


        if (!Equipment.contains(appropriateHeadGearID) && Inventory.contains(appropriateHeadGearID)) {
            Inventory.interact(appropriateHeadGearID, "Wear");
            return 900;
        }

        if (!Equipment.contains(appropriateBodyArmorID) && Inventory.contains(appropriateBodyArmorID)) {
            Inventory.interact(appropriateBodyArmorID, "Wear");
            return 900;
        }

        if (!Equipment.contains(appropriateVambracesID) && Inventory.contains(appropriateVambracesID)) {
            Inventory.interact(appropriateVambracesID, "Wear");
            return 900;
        }

        if (!Equipment.contains(appropriateLegArmorID) && Inventory.contains(appropriateLegArmorID)) {
            Inventory.interact(appropriateLegArmorID, "Wear");
            return 900;
        }

        if (!Equipment.contains(EquipmentList.AMULET_OF_POWER) && Inventory.contains(EquipmentList.AMULET_OF_POWER)) {
            Inventory.interact(EquipmentList.AMULET_OF_POWER, "Wear");
            return 900;
        }

        if (!Equipment.contains(appropriateBowID) && Inventory.contains(appropriateBowID)) {
            Inventory.interact(appropriateBowID, "Wield");
            return 900;
        }

        if (!Equipment.contains(x -> x.getName().contains("arrow")) && Inventory.contains(x -> x.getName().contains("arrow"))) {
            Inventory.interact(x -> x.getName().contains("arrow"), "Wield");
            return 900;
        }

        //MethodProvider.log("In Grand Exchange!");
        //return (int) Calculations.nextGaussianRandom(350, 250);

        return 800;
    }

    private int getAppropriateBow() {
        /*
        magic shortbow <= 50
        yew shortbow <= 40
        maple <= 30
        willow <= 20
        oak <= 5
        shortbow
         */
        int lvl = Skills.getRealLevel(Skill.RANGED);
        //if (lvl >= 50) return EquipmentList.MAGIC_SHORTBOW;
        //if (lvl >= 40) return EquipmentList.YEW_SHORTBOW;
        if (lvl >= 30) return EquipmentList.MAPLE_SHORTBOW;
        if (lvl >= 20) return EquipmentList.WILLOW_SHORTBOW;
        if (lvl >= 5) return EquipmentList.OAK_SHORTBOW;
        return EquipmentList.SHORTBOW;
    }
    private int getAppropriateHeadGear() {
        int lvl = Skills.getRealLevel(Skill.RANGED);
        if (lvl >= 20) return EquipmentList.COIF;
        return EquipmentList.LEATHER_COWL;
    }
    private int getAppropriateBodyArmor() {
        int lvl = Skills.getRealLevel(Skill.RANGED);
        int dLvl = Skills.getRealLevel(Skill.DEFENCE);
        if (lvl >= 20 && dLvl >= 20) return EquipmentList.STUDDED_BODY;
        return EquipmentList.LEATHER_BODY;
    }
    private int getAppropriateLegArmor() {
        int lvl = Skills.getRealLevel(Skill.RANGED);
        if (lvl >= 40) return EquipmentList.GREEN_DHIDE_CHAPS;
        if (lvl >= 20) return EquipmentList.STUDDED_CHAPS;
        return EquipmentList.LEATHER_CHAPS;
    }
    private int getAppropriateVambraces() {
        int lvl = Skills.getRealLevel(Skill.RANGED);
        if (lvl >= 40) return EquipmentList.GREEN_DHIDE_VAMBRACES;
        return EquipmentList.LEATHER_VAMBRACES;
    }

}
