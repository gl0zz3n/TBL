package dreambot.behaviour.Leafs.CombatTraining;

import dreambot.Main;
import dreambot.constants.EquipmentList;
import dreambot.framework.Leaf;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;

public class combatTrainingLeaf extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return Inventory.contains(x -> x.getName().contains("scimitar")
                || Inventory.contains(i -> i.getName().contains("dagger")));
    }

    @Override
    public int onLoop() {
        MethodProvider.log("In Grand Exchange!");
        return (int) Calculations.nextGaussianRandom(350, 250);

    }

}
