package dreambot.booleans;

import dreambot.areas.Gl0zz3nAreas;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;

import static org.dreambot.api.Client.getLocalPlayer;

public class Conditions {

    public static boolean shouldRestockArrows() {
        return !Inventory.contains(x -> x.getName().contains("arrow"))
                && !Bank.contains(x -> x.getName().contains("arrow"))
                && !Equipment.contains(x-> x.getName().contains("arrow"));
    }
    public static boolean botHasArrows() {
        return Inventory.contains(x -> x.getName().contains("arrow"))
                || Bank.contains(x -> x.getName().contains("arrow"))
                || Equipment.contains(x-> x.getName().contains("arrow"));
    }
    public static boolean botHasCoins() {
        return (Bank.count("Coins") >= 5000)
                || (Inventory.count("Coins") >= 5000)
                || ( Bank.count("Coins") + (Inventory.count("Coins")) >= 5000 );

    }

    public static boolean tutorialComplete(){
        return !Gl0zz3nAreas.TUTORIAL_ISLAND.contains(getLocalPlayer());
    }



    public static boolean combatTrainingComplete() {
        return  Skills.getRealLevel(Skill.ATTACK)>=40
                && Skills.getRealLevel(Skill.STRENGTH)>=50
                && Skills.getRealLevel(Skill.DEFENCE)>=40;
    }
    public static boolean rangeTrainingComplete() {
        return Skills.getRealLevel(Skill.RANGED)>=50;
    }




}
