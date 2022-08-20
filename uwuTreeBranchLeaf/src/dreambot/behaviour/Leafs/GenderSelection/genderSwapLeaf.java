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

import static org.dreambot.api.Client.getLocalPlayer;

public class genderSwapLeaf extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return Gl0zz3nAreas.GAY_GILBERT.contains(getLocalPlayer())
                && Inventory.contains(EquipmentList.RAINBOW_SCARF)
                && Inventory.contains(EquipmentList.PRIDE_CROWN);
    }

    @Override
    public int onLoop() {
        MethodProvider.log("uwu! This Is Gonna Hurt, Senpai!");

        if(!Widgets.isOpen()){
            Inventory.interact("Flower crown","Change");
        } else if (Widgets.isOpen()){
            pickGenderX();
            pickGenderY();
            Point p = new Point();
            p.setLocation(pickGenderX(), pickGenderY());
            Mouse.move(p);
            Mouse.click();
        }
        return 1800;
    }
    private int pickGenderX() {
        int x = Calculations.random(1,100);
        while (x>3) {
            x = Calculations.random(1,50);
        }
        if (x == 3) return 78+(Calculations.random(105));
        if (x == 2) return 205+(Calculations.random(105));
        return 334+(Calculations.random(105));

    }

    private int pickGenderY() {
        int y = Calculations.random(1,100);
        while (y>3) {
            y = Calculations.random(1,50);
        }
        if (y == 3) return 69+(Calculations.random(60));
        if (y == 2) return 154+(Calculations.random(60));
        return 238+(Calculations.random(64));

    }



}