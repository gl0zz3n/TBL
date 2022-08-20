package dreambot.behaviour;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.script.ScriptManager;
import dreambot.Main;
import dreambot.areas.Gl0zz3nAreas;
import dreambot.framework.Root;
import static org.dreambot.api.Client.getLocalPlayer;
public class ScriptStarterBranch extends Root<Main> implements Runnable {
    @Override
    public boolean isValid() {

        return Gl0zz3nAreas.TUTORIAL_ISLAND.contains(getLocalPlayer()) || (Quests.getQuestPoints()<10);

    }

@Override
    public void run() {
        ScriptManager manager = ScriptManager.getScriptManager();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        //manager.start("tmp\'s Ten QP");
        if(Gl0zz3nAreas.TUTORIAL_ISLAND.contains(getLocalPlayer())) {
            MethodProvider.log("uwu! Tutorial Time!");
            MethodProvider.log("uwu! THANK YOU BRAVE! LOVE YOU LONG TIME!");
            manager.start("braveTutorial",null);
        } else {
            MethodProvider.log("uwu! Getting 10 Quest Points!");
            MethodProvider.log("uwu! THANK YOU TMP! LOVE YOU LONG TIME!");
            manager.start("tmp's Ten QP",null);
        }

    }


}

/*
public class QuestScriptStarterBranch implements Runnable  {
    @Override
    public void run() {
        ScriptManager manager = ScriptManager.getScriptManager();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        manager.start("tmp\'s Ten QP");
    }
}

*/
