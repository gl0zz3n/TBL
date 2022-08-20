package dreambot.behaviour.Leafs;

import dreambot.Main;
import dreambot.framework.Leaf;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.walking.impl.Walking;

public class ExampleLeafTwo extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        if (Walking.shouldWalk(6)) {
            Walking.walk(BankLocation.GRAND_EXCHANGE.getCenter());
        }
        return (int) Calculations.nextGaussianRandom(350, 250);
    }
}
