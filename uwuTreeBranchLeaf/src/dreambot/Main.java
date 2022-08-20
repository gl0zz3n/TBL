package dreambot;

import dreambot.behaviour.CombatBranch;
import dreambot.behaviour.GenderBenderBranch;
import dreambot.behaviour.Leafs.GenderSelection.genderPreOpEquipmentObtainLeaf;
import dreambot.behaviour.Leafs.GenderSelection.genderPreOpTraverseLeaf;
import dreambot.behaviour.Leafs.GenderSelection.genderRevealLeaf;
import dreambot.behaviour.Leafs.GenderSelection.genderSwapLeaf;
import dreambot.behaviour.Leafs.RangeTraining.rangingBankingLeaf;
import dreambot.behaviour.Leafs.RangeTraining.rangingCombatLeaf;
import dreambot.behaviour.Leafs.RangeTraining.rangingEquipmentSwapLeaf;
import dreambot.behaviour.Leafs.Questing.*;
import dreambot.behaviour.ScriptStarterBranch;
import dreambot.behaviour.RangingBranch;
import dreambot.framework.Branch;
import dreambot.framework.Tree;
import dreambot.paint.CustomPaint;
import dreambot.paint.PaintInfo;
import dreambot.utilities.API;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.awt.*;

@ScriptManifest(
        author = "gl0b0t",
        description = "uwu BUILDER",
        category = Category.COMBAT,
        version = 1.0,
        name = "uwu BUILDER"
)
public class Main extends AbstractScript implements PaintInfo {

    /**
     * @param args script quick launch arguments
     */

    @Override
    public void onStart(String... args) {
        instantiateTree();
    }

    /**
     * On start from script launcher
     */
    @Override
    public void onStart() {
        instantiateTree();
    }


    private final Tree<Main> tree = new Tree<>();
    //private Branch<Main> exampleBranch;
    private Branch<Main> combatBranch;
    private Branch<Main> rangingBranch;
    private Branch<Main> questingBranch;
    private Branch<Main> genderBranch;


    private void instantiateTree() {
        //exampleBranch = new ExampleBranch();
        combatBranch = new CombatBranch();
        rangingBranch = new RangingBranch();
        questingBranch = new ScriptStarterBranch();
        genderBranch = new GenderBenderBranch();

        tree.addBranches(

                genderBranch.addLeafs(new genderPreOpTraverseLeaf(), new genderPreOpEquipmentObtainLeaf(), new genderSwapLeaf(), new genderRevealLeaf() ),
                questingBranch.addLeafs(new questingLeaf() ),
                rangingBranch.addLeafs(new rangingBankingLeaf(),new rangingEquipmentSwapLeaf(), new rangingCombatLeaf())
                //combatBranch.addLeafs(new ExampleLeaf(),new ExampleLeafTwo() ),




        );
    }


    /**
     * onLoop is a infinite loop
     * @return gets the leaf and executes it
     */

    @Override
    public int onLoop() {

        return this.tree.onLoop();

    }

    /**
     * @return the information for the paint
     */
    @Override
    public String[] getPaintInfo() {
        return new String[] {
                getManifest().name() + " V" + getManifest().version(),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf
        };
    }

    /**
     * Instantiate the paint object, can be customized to liking.
     */
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.TOP_LEFT_PLAY_SCREEN, new Color[]{new Color(255, 251, 255)},
            "Trebuchet MS",
            new Color[]{new Color(50, 50, 50, 175)},
            new Color[]{new Color(28, 28, 29)},
            1, false, 5, 3, 0);

    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


    /**
     * paint for the script
     */
    @Override
    public void onPaint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHints(aa);

        CUSTOM_PAINT.paint(gg);
    }

}
