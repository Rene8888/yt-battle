package at.er.ytbattle.battle.timer.timeables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.util.timer.Timeable;
import at.er.ytbattle.util.timer.TimerManager.TimeScale;

public class RemindTimer extends Timeable {

    private static final int MANAGER_ID = 461462;

    public RemindTimer() {
        super(MANAGER_ID, TimeScale.MINUTE, 15);
    }

    @Override
    public void tick(long elapsedTime) {
        if (elapsedTime != 0) {
            Bukkit.broadcastMessage(Battle.prefix() + ChatColor.DARK_RED + "The battle is going on for " + elapsedTime + " minutes!");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
            }
        }
    }

    public void startReminder() {
        Battle.instance().getGame().getTimerManager().registerTimer(this);
        this.startTimer();
    }

}