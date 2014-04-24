package at.er.ytbattle.battle.timer;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;

public class GraceTimer implements Runnable, Serializable {

	private static final long serialVersionUID = 1L;

	private int time;

	public GraceTimer() {
		this.time = -1;
	}

	public GraceTimer(int timeSec) {
		this.time = timeSec;
	}

	public void run() {
		if (time >= 600 && time % 600 == 0) {
			broadcastTime();
		}
		if (time <= 300 && time % 60 == 0 && time > 60) {
			broadcastTime();
		}

		if (time <= 60 && time % 15 == 0 && time > 0) {
			broadcastTime();
		}

		if (time <= 10 && time > 0) {
			broadcastTime();
			if (time <= 3)
				note();
		}

		if (time == 0) {
			Battle.BATTLE.getGame().getSpawn().getLocation().getWorld().setPVP(true);
			for (Team t : Battle.BATTLE.getGame().getTeamManager().getTeams()) {
				for (String p : t.getPlayers()) {
					Player player = Bukkit.getPlayer(p);
					player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 10, 1);
				}
			}
			Bukkit.broadcastMessage(Battle.prefix() + "The grace period has ended!");
		}

		if (time >= 0) {
			time = time - 1;
		}
	}

	private void note() {
		for (Team t : Battle.BATTLE.getGame().getTeamManager().getTeams()) {
			for (String p : t.getPlayers()) {
				Player player = Bukkit.getPlayer(p);
				player.playSound(player.getLocation(), Sound.NOTE_SNARE_DRUM, 10, 1);
			}
		}
	}

	private void broadcastTime() {
		if (time > 60) {
			Bukkit.broadcastMessage(Battle.prefix() + "The grace period will end in " + time / 60 + " Minutes");
		} else {
			Bukkit.broadcastMessage(Battle.prefix() + "The grace period will end in " + time + " Seconds");
		}
	}

	public int getTime() {
		return this.time;
	}

}
