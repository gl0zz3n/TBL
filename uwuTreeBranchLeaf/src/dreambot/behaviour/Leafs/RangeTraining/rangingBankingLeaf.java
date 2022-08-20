package dreambot.behaviour.Leafs.RangeTraining;

import dreambot.Main;
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

import dreambot.constants.EquipmentList;


public class rangingBankingLeaf extends Leaf<Main> {

    // BOW IDS


    @Override
    public boolean isValid() {
        return ( !Equipment.contains(x -> x.getName().contains("arrow"))
                && !Inventory.contains(x -> x.getName().contains("arrow")) )
                || ( !Equipment.contains(getAppropriateBow())
                && !Inventory.contains(getAppropriateBow()) )
                || ( (Skills.getRealLevel(Skill.RANGED)>=40)
                && !Equipment.contains(getAppropriateLegArmor())
                && !Inventory.contains(getAppropriateLegArmor()) );
    }



    @Override
    public int onLoop() {
        int appropriateBowID = getAppropriateBow();
        int appropriateHeadGearID = getAppropriateHeadGear();
        int appropriateBodyArmorID = getAppropriateBodyArmor();
        int appropriateLegArmorID = getAppropriateLegArmor();
        int appropriateVambracesID = getAppropriateVambraces();
        
        if (Bank.getLastBankHistoryCacheTime() == 0) {
            log("Test");
            if (Bank.isOpen()) {
                Bank.depositAllItems();
                Bank.depositAllEquipment();
            } else if (Walking.shouldWalk()) {
                Bank.open();
            }
            return 400;
        }

        /*
        if (!Bank.isOpen() && Walking.shouldWalk()
                && !Equipment.contains(appropriateBowID)
                || ( !Equipment.contains(x -> x.getName().contains("arrow"))
                && !Inventory.contains(x -> x.getName().contains("arrow"))) ) {
            Bank.open();
            return 1600;
        } // If bank is not open and equipment does not contain Bow OR equipment and Inventory does not contain arrow
*/

        if(Bank.isOpen() && Inventory.getEmptySlots() < 10) {
            log("uwu! He Have Stuff In Inventory!");
            Bank.depositAllExcept(
                    appropriateBowID
                    ,appropriateVambracesID
                    ,appropriateBodyArmorID
                    ,appropriateHeadGearID
                    ,appropriateLegArmorID
                    ,882,884,886,888,890 /*Arrow Id's because im retarded*/
            );
            return 900;
        }

        if(Bank.isOpen() && !Equipment.isEmpty()) {
            Bank.depositAllEquipment();
            return 1800;
        }

        if (Bank.isOpen() && Bank.contains(appropriateHeadGearID)
                && !Inventory.contains(appropriateHeadGearID)
                && !Equipment.contains(appropriateHeadGearID)) {
            Bank.withdraw(appropriateHeadGearID);
            return 1800;
        }
        
        if (Bank.isOpen() && Bank.contains(appropriateBodyArmorID)
                && !Inventory.contains(appropriateBodyArmorID)
                && !Equipment.contains(appropriateBodyArmorID)) {
            Bank.withdraw(appropriateBodyArmorID);
            return 1800;
        }
        
        if (Bank.isOpen() && Bank.contains(appropriateVambracesID)
                && !Inventory.contains(appropriateVambracesID)
                && !Equipment.contains(appropriateVambracesID)) {
            Bank.withdraw(appropriateVambracesID);
            return 1800;
        }
        
        if (Bank.isOpen() && Bank.contains(appropriateLegArmorID)
                && !Inventory.contains(appropriateLegArmorID)
                && !Equipment.contains(appropriateLegArmorID)) {
            Bank.withdraw(appropriateLegArmorID);
            return 1800;
        }

        if (Bank.isOpen() && Bank.contains(EquipmentList.AMULET_OF_POWER)
                && !Inventory.contains(EquipmentList.AMULET_OF_POWER)
                && !Equipment.contains(EquipmentList.AMULET_OF_POWER)) {
            Bank.withdraw(EquipmentList.AMULET_OF_POWER);
            return 1800;
        }

        if (Bank.isOpen() && Bank.contains(appropriateBowID)
                && !Inventory.contains(appropriateBowID)
                && !Equipment.contains(appropriateBowID)) {
            Bank.withdraw(appropriateBowID);
            return 1800;
        }

        if (Bank.isOpen() && Bank.contains(x -> x.getName().contains("arrow"))) {
            Bank.withdrawAll(x -> x.getName().contains("arrow"));
            return 1800;
        }

        if (Bank.isOpen() && Inventory.contains(appropriateBowID)
                && Inventory.contains(x -> x.getName().contains("arrow"))
                && !Bank.contains(x -> x.getName().contains("arrow"))) {
            Bank.close();
            return 1800;
        }

        if(Bank.isOpen() && !Inventory.contains(x -> x.getName().contains("arrow"))
                && !Bank.contains(x -> x.getName().contains("arrow"))
                && !Equipment.contains(x -> x.getName().contains("arrow"))) {
//stop the dang script
            ScriptManager.getScriptManager().stop();
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
