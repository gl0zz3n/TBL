package dreambot.behaviour.Leafs.CombatTraining;

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

import static org.dreambot.api.methods.MethodProvider.log;
import static org.dreambot.api.methods.MethodProvider.sleep;


public class combatBankingLeaf extends Leaf<Main> {

    // ITEM IDS
    public static final int MITHRIL_SCIMITAR = 843;
    public static final int RUNE_SCIMITAR = 849;
    public static final int BRONZE_DAGGER = 1129;

    public static final int MITHRIL_HELMET = 853;
    public static final int MITHRIL_PLATEBODY = 857;
    public static final int MITHRIL_PLATELEGS = 861;
    public static final int MITHRIL_KITESHIELD = 861;

    public static final int IRON_HELMET = 853;
    public static final int IRON_PLATEBODY = 857;
    public static final int IRON_PLATELEGS = 861;
    public static final int IRON_KITESHIELD = 861;


    public static final int RAW_CHICKEN = 1167;
    public static final int COOKED_CHICKEN = 1169;
    public static final int BURNT_CHICKEN = 1133;





    @Override
    public boolean isValid() {
        return (!Equipment.contains(x -> x.getName().contains("scimitar"))
                && !Equipment.contains(i -> i.getName().contains("dagger"))
                && !Inventory.contains(j -> j.getName().contains("scimitar"))
                && !Inventory.contains(k -> k.getName().contains("dagger")));
    }

    @Override
    public int onLoop() {
        int appropriateBowID = getAppropriateSword();
        int appropriateHeadGearID = getAppropriateHeadGear();
        int appropriateBodyArmorID = getAppropriateBodyArmor();
        int appropriateLegArmorID = getAppropriateLegArmor();
        int appropriateKiteShieldID = getAppropriateKiteShield();

        if (Bank.getLastBankHistoryCacheTime() == 0) {
            log("Test");
            if (Bank.isOpen()) {
                Bank.depositAllItems();
            } else if (Walking.shouldWalk()) {
                Bank.open();
            }
            //return 400;
        }

        if (!Bank.isOpen() && Walking.shouldWalk()
                && !Equipment.contains(appropriateBowID)
                || ( !Equipment.contains(x -> x.getName().contains("scimitar"))
                && !Inventory.contains(x -> x.getName().contains("scimitar"))) ) {
            Bank.open();
            //return 1600;
        } // If bank is not open and equipment does not contain Bow OR equipment and Inventory does not contain arrow

        if(Bank.isOpen() && !Inventory.isEmpty()) {
            log("uwu! He Have Stuff In Inventory!");
            Bank.depositAllItems();
            sleep(900,1800);
        }

        if (Bank.isOpen() && Bank.contains(x -> x.getName().contains("arrow"))) {
            Bank.withdrawAll(x -> x.getName().contains("arrow"));
            sleep(900,1800);
        }

        if (Bank.isOpen() && Bank.contains(appropriateBowID)
                && !Inventory.contains(appropriateBowID)
                && !Equipment.contains(appropriateBowID)) {
            Bank.withdraw(appropriateBowID);
            sleep(900,1800);
        }

        if (Bank.isOpen() && Bank.contains(appropriateHeadGearID)
                && !Inventory.contains(appropriateHeadGearID)
                && !Equipment.contains(appropriateHeadGearID)) {
            Bank.withdraw(appropriateHeadGearID);
            sleep(900,1800);
        }

        if (Bank.isOpen() && Bank.contains(appropriateBodyArmorID)
                && !Inventory.contains(appropriateBodyArmorID)
                && !Equipment.contains(appropriateBodyArmorID)) {
            Bank.withdraw(appropriateBodyArmorID);
            sleep(900,1800);
        }

        if (Bank.isOpen() && Bank.contains(appropriateKiteShieldID)
                && !Inventory.contains(appropriateKiteShieldID)
                && !Equipment.contains(appropriateKiteShieldID)) {
            Bank.withdraw(appropriateKiteShieldID);
            sleep(900,1800);
        }

        if (Bank.isOpen() && Bank.contains(appropriateLegArmorID)
                && !Inventory.contains(appropriateLegArmorID)
                && !Equipment.contains(appropriateLegArmorID)) {
            Bank.withdraw(appropriateLegArmorID);
            sleep(900,1800);
        }

        if (Bank.isOpen() && Bank.contains(EquipmentList.AMULET_OF_POWER)
                && !Inventory.contains(EquipmentList.AMULET_OF_POWER)
                && !Equipment.contains(EquipmentList.AMULET_OF_POWER)) {
            Bank.withdraw(EquipmentList.AMULET_OF_POWER);
            sleep(900,1800);
        }

        if (Bank.isOpen() && Inventory.contains(appropriateBowID)
                && Inventory.contains(x -> x.getName().contains("arrow"))
                && !Bank.contains(x -> x.getName().contains("arrow"))) {
            Bank.close();
            sleep(900,1800);
        }

        if(Bank.isOpen() && !Inventory.contains(x -> x.getName().contains("arrow"))
                && !Bank.contains(x -> x.getName().contains("arrow"))) {
//stop the dang script
            ScriptManager.getScriptManager().stop();
        }

        if (!Bank.isOpen() && !Equipment.contains(appropriateBowID) && Inventory.contains(appropriateBowID)) {
            Inventory.interact(appropriateBowID, "Wield");
            sleep(900,1800);
        }

        if (!Bank.isOpen()
                && !Equipment.contains(appropriateHeadGearID)
                && Inventory.contains(appropriateHeadGearID)) {
            Inventory.interact(appropriateHeadGearID, "Wear");
            sleep(900,1800);
        }

        if (!Bank.isOpen()
                && !Equipment.contains(appropriateBodyArmorID)
                && Inventory.contains(appropriateBodyArmorID)) {
            Inventory.interact(appropriateBodyArmorID, "Wear");
            sleep(900,1800);
        }

        if (!Bank.isOpen()
                && !Equipment.contains(appropriateLegArmorID)
                && Inventory.contains(appropriateLegArmorID)) {
            Inventory.interact(appropriateLegArmorID, "Wear");
            sleep(900,1800);
        }

        if (!Bank.isOpen()
                && !Equipment.contains(appropriateKiteShieldID)
                && Inventory.contains(appropriateKiteShieldID)) {
            Inventory.interact(appropriateKiteShieldID, "Wear");
            sleep(900,1800);
        }
        if (!Bank.isOpen()
                && !Equipment.contains(x -> x.getName().contains("arrow"))
                && Inventory.contains(x -> x.getName().contains("arrow"))) {
            Inventory.interact(x -> x.getName().contains("arrow"), "Wield");
            sleep(900,1800);
        }

        //MethodProvider.log("In Grand Exchange!");
        //return (int) Calculations.nextGaussianRandom(350, 250);

        return 800;
    }

    private int getAppropriateSword() {
        /*
        magic shortbow <= 50
        yew shortbow <= 40
        maple <= 30
        willow <= 20
        oak <= 5
        shortbow
         */
        int lvl = Skills.getRealLevel(Skill.RANGED);
        if (lvl >= 40) return RUNE_SCIMITAR;
        if (lvl >= 20) return MITHRIL_SCIMITAR;
        return BRONZE_DAGGER;
    }
    private int getAppropriateHeadGear() {
        int lvl = Skills.getRealLevel(Skill.DEFENCE);
        if (lvl >= 20) return MITHRIL_HELMET;
        return IRON_HELMET;
    }
    private int getAppropriateBodyArmor() {
        int lvl = Skills.getRealLevel(Skill.DEFENCE);
        if (lvl >= 20) return MITHRIL_PLATEBODY;
        return IRON_PLATEBODY;
    }

    private int getAppropriateLegArmor() {
        int lvl = Skills.getRealLevel(Skill.DEFENCE);
        if (lvl >= 20) return MITHRIL_PLATELEGS;
        return IRON_PLATELEGS;
    }

    private int getAppropriateKiteShield() {
        int lvl = Skills.getRealLevel(Skill.DEFENCE);
        if (lvl >= 20) return MITHRIL_KITESHIELD;
        return IRON_KITESHIELD;
    }

}
