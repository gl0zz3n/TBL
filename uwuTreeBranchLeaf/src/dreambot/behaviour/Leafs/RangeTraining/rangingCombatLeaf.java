package dreambot.behaviour.Leafs.RangeTraining;

import dreambot.Main;
import dreambot.areas.Gl0zz3nAreas;
import dreambot.constants.EquipmentList;
import dreambot.framework.Leaf;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.combat.CombatStyle;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;

import static org.dreambot.api.Client.getLocalPlayer;
import static org.dreambot.api.methods.MethodProvider.*;



public class rangingCombatLeaf extends Leaf<Main> {



    @Override
    public boolean isValid() {
        return ( Equipment.contains(x -> x.getName().contains("arrow"))
                && Equipment.contains(getAppropriateBow()) )

                || ( (Skills.getRealLevel(Skill.RANGED)>=40)
                && !Equipment.contains(getAppropriateLegArmor())
                && !Inventory.contains(getAppropriateLegArmor()) );
    }


    public void Check_Run() {
        if ((Walking.getRunEnergy() > Calculations.random(8, 16) ) && !Walking.isRunEnabled()){
            Walking.toggleRun();
        }
    }
    int Heal;




    @Override
    public int onLoop() {

        Area AreaToTrain = getAreaToTrain();
        String monsterToAttack = getMonsterToAttack();
        NPC Monster = NPCs.closest(npc -> npc.getName().equals(monsterToAttack) && !npc.isInCombat());
        int appropriateBowID = getAppropriateBow();
        int appropriateLegArmorID = getAppropriateLegArmor();


        if(Skills.getRealLevel(Skill.RANGED)>= 30
                && Skills.getRealLevel(Skill.DEFENCE)<30
                &&  Combat.getCombatStyle()!=CombatStyle.RANGED) {
            Combat.setCombatStyle(CombatStyle.RANGED_DEFENCE);
        } else if (Combat.getCombatStyle() != CombatStyle.RANGED_RAPID) {
            Combat.setCombatStyle(CombatStyle.RANGED_RAPID);
        }

        if (!getLocalPlayer().isInCombat()) {
            GroundItem arrows = GroundItems.closest(x -> x.getName().contains("arrow"));

            if(Gl0zz3nAreas.MONASTERY.contains(getLocalPlayer())
                    || Gl0zz3nAreas.FALADOR_FARM_CHICKENS.contains(getLocalPlayer())) {
                if ((arrows != null) && (arrows.getAmount()>5))
                {
                    arrows.interact("Take");
                    return 1800;
                }
            }


            if(getLocalPlayer().getHealthPercent()>99) {
                Heal = 0;
            }

            if((getLocalPlayer().getHealthPercent() < 99)
                    && Gl0zz3nAreas.MONASTERY.contains(getLocalPlayer())
                    && Heal == 1 ){
                log("uwu! - We're Running Away To De-Agro The Monk");
                if(!Dialogues.inDialogue()) {
                    Monster.interact("Talk-to");
                    sleepUntil(Dialogues::inDialogue,10000);
                    return 900;
                }
                if(Dialogues.canContinue()) {
                    Dialogues.continueDialogue();
                    return 900;
                }
                if(Dialogues.areOptionsAvailable()) {
                    Dialogues.chooseFirstOptionContaining("Can you heal me");
                    return 900;
                }
            }

            if (AreaToTrain.contains(getLocalPlayer()) && (AreaToTrain.contains(Monster)
                    || Gl0zz3nAreas.BLACK_KNIGHT_FORTRESS_ENTRANCE.contains(Monster))) {
                sleep(800,2000);
                Monster.interact("Attack");
                //rangeXpHr = (int) (rangeXpGained / ((System.currentTimeMillis() - timeBegan) / 3600000.0D));
                log("uwu! Attacking "+Monster+"!");
                return (Calculations.random(750, 900));

            } else {
                Check_Run();
                log("uwu! Travelling To "+AreaToTrain+"!");
                Walking.walkExact(AreaToTrain.getRandomTile());
                sleep(Calculations.random(1500, 2800));
                return (Calculations.random(10, 50));
            }
        }

        if (getLocalPlayer().isInCombat()) {

            if(getLocalPlayer().getHealthPercent()<40 && Gl0zz3nAreas.MONASTERY.contains(getLocalPlayer())) {
                log("uwu! - We're Running Away To De-Agro The Monk");
                Heal = 1;
                Check_Run();
                Bank.open();
            }

            if (Dialogues.canContinue()) {
                log("UWU! - We Gained A Level");
                Dialogues.continueDialogue();
                return (Calculations.random(10, 50));
            }

            if (!AreaToTrain.contains(getLocalPlayer())) {
                Check_Run();
                Walking.walkExact(AreaToTrain.getRandomTile());
                sleep(Calculations.random(1500, 2800));
                return (Calculations.random(10, 50));
            }

        }




        //MethodProvider.log("In Grand Exchange!");
        return (int) Calculations.nextGaussianRandom(350, 250);
    }

    private Area getAreaToTrain() {
        int lvl = Skills.getRealLevel(Skill.RANGED);
        if (lvl >= 40) return Gl0zz3nAreas.ICE_MOUNTAIN_SAFE_SPOT;
        if (lvl >= 20) return Gl0zz3nAreas.MONASTERY;
        return Gl0zz3nAreas.FALADOR_FARM_CHICKENS;
    }
    private String getMonsterToAttack() {
        int lvl = Skills.getRealLevel(Skill.RANGED);
        if (lvl >= 40) return "Fortress Guard";
        if (lvl >= 20) return "Monk";
        return "Chicken";
    }
    private int getAppropriateBow() {
        /*
        magic shortbow <= 50
        yew shortbow <= 40
        maple <= 30
        willow <= 20
        oak <= 5
        shortbow
         */
        int lvl = Skills.getRealLevel(Skill.RANGED);
        //if (lvl >= 50) return MAGIC_SHORTBOW;
        //if (lvl >= 40) return YEW_SHORTBOW;
        if (lvl >= 30) return EquipmentList.MAPLE_SHORTBOW;
        if (lvl >= 20) return EquipmentList.WILLOW_SHORTBOW;
        if (lvl >= 5) return EquipmentList.OAK_SHORTBOW;
        return EquipmentList.SHORTBOW;
    }
    private int getAppropriateLegArmor() {
        int lvl = Skills.getRealLevel(Skill.RANGED);
        if (lvl >= 40) return EquipmentList.GREEN_DHIDE_CHAPS;
        if (lvl >= 20) return EquipmentList.STUDDED_CHAPS;
        return EquipmentList.LEATHER_CHAPS;
    }

}
