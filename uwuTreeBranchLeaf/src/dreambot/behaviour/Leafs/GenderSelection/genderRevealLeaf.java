package dreambot.behaviour.Leafs.GenderSelection;

import dreambot.Main;
import dreambot.areas.Gl0zz3nAreas;
import dreambot.constants.EquipmentList;
import dreambot.framework.Leaf;
import org.dreambot.api.input.Mouse;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;

import java.awt.*;

import static dreambot.constants.EquipmentList.*;
import static org.dreambot.api.Client.getLocalPlayer;

public class genderRevealLeaf extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return Gl0zz3nAreas.GAY_GILBERT.contains(getLocalPlayer())
                && Inventory.contains(EquipmentList.RAINBOW_SCARF)
                && (Inventory.contains(EquipmentList.BISEXUAL_CROWN)
                || Inventory.contains(EquipmentList.ASEXUAL_CROWN)
                || Inventory.contains(GAY_CROWN)
                || Inventory.contains(GENDERQUEER_CROWN)
                || Inventory.contains(LESBIAN_CROWN)
                || Inventory.contains(NONBINARY_CROWN)
                || Inventory.contains(PANSEXUAL_CROWN)
                || Inventory.contains(TRANSGENDER_CROWN));

    }

    @Override
    public int onLoop() {
        Gender();
        MethodProvider.log("uwu! I've Decided That I Am "+Gender()+" Now!");
        Inventory.interact("Flower crown","Wear");
        return 1800;
    }
    private String Gender() {
        if(Inventory.contains(TRANSGENDER_CROWN)) return "TRANS-GENDER!";
        if(Inventory.contains(PANSEXUAL_CROWN)) return "PAN-SEXUAL!";
        if(Inventory.contains(NONBINARY_CROWN)) return "NON-BINARY!";
        if(Inventory.contains(EquipmentList.ASEXUAL_CROWN)) return "ASEXUAL!";
        if(Inventory.contains(LESBIAN_CROWN)) return "LESBIAN!";
        if(Inventory.contains(GAY_CROWN)) return "GAY!";
        if(Inventory.contains(GENDERQUEER_CROWN)) return "GENDER-QUEER!";
        if(Inventory.contains(EquipmentList.BISEXUAL_CROWN)) return "BI-SEXUAL!";
        return "IDK WTF I AM";
    }






}