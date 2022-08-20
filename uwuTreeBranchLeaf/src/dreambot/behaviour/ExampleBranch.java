package dreambot.behaviour;

import dreambot.Main;
import dreambot.framework.Root;
import org.dreambot.api.methods.container.impl.Inventory;

public class ExampleBranch extends Root<Main> {
    @Override
    public boolean isValid() {
        return Inventory.isFull() || Inventory.contains(995);


    }

}
