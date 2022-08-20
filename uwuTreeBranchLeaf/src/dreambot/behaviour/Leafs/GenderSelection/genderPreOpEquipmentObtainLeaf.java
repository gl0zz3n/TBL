package dreambot.behaviour.Leafs.GenderSelection;

import dreambot.Main;
import dreambot.areas.Gl0zz3nAreas;
import dreambot.constants.EquipmentList;
import dreambot.framework.Leaf;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;

import static org.dreambot.api.Client.getLocalPlayer;
import static org.dreambot.api.methods.MethodProvider.sleep;

public class genderPreOpEquipmentObtainLeaf extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return Gl0zz3nAreas.GAY_GILBERT.contains(getLocalPlayer()) && !Inventory.contains(EquipmentList.RAINBOW_SCARF);
    }

    @Override
    public int onLoop() {
        MethodProvider.log("uwu! Checking In To Our Appointment!");
        NPC Gilbert = NPCs.closest("Gilbert");

        if(!Dialogues.inDialogue()){
            Gilbert.interact("Talk-to");
            return 1800;
        }

        if(Dialogues.inDialogue() && Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            return 1600;
        }

        if(Dialogues.inDialogue() && Dialogues.areOptionsAvailable()) {
            Dialogues.chooseFirstOptionContaining("flower crown");
            return 1600;
        }




        return 1800;
    }

}