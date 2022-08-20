package dreambot.behaviour.Leafs.GenderSelection;

import dreambot.Main;
import dreambot.areas.Gl0zz3nAreas;
import dreambot.constants.EquipmentList;
import dreambot.framework.Leaf;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.walking.impl.Walking;

import static org.dreambot.api.Client.getLocalPlayer;

public class genderPreOpTraverseLeaf extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return !Gl0zz3nAreas.GAY_GILBERT.contains(getLocalPlayer()) && !Inventory.contains(EquipmentList.RAINBOW_SCARF);
    }

    @Override
    public int onLoop() {
        MethodProvider.log("uwu! Its A Very Special Day, Senpai! - We Get To Pick Our Gender!");
        if (Walking.shouldWalk()) {
            Walking.walk(Gl0zz3nAreas.GAY_GILBERT);
        }

        return 1200;
    }

}