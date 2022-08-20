package dreambot.behaviour;

import dreambot.Main;
import dreambot.framework.Root;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;

public class GenderBenderBranch extends Root<Main> {
    @Override
    public boolean isValid() {

        return Bank.getLastBankHistoryCacheTime() != 0
                && !Bank.contains("Flower crown")
                && !Equipment.contains("Flower crown")
                && !Inventory.contains("Flower crown");

    }
}
