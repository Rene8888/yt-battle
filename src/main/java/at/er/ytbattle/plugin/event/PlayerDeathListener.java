package at.er.ytbattle.plugin.event;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.Team;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.timeables.FireworkTimer;
import at.er.ytbattle.util.BattleUtils;
import at.er.ytbattle.util.PlayerArmor;

public class PlayerDeathListener implements Listener {

    public PlayerDeathListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent event) {
        BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getEntity());

        if (BattlePlugin.game().isStarted() && BattlePlugin.game().getTeamManager().isInTeam(player)) {

            Location spawn = BattlePlugin.game().getSpawn().getLocation();

            Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);

            ItemStack helmet = player.getInventory().getHelmet();
            ItemStack chestplate = player.getInventory().getChestplate();
            ItemStack leggings = player.getInventory().getLeggings();
            ItemStack boots = player.getInventory().getBoots();

            if (helmet != null) {
                helmet = helmet.clone();
                if (helmet.getType() == Material.DIAMOND_HELMET)
                    helmet = new ItemStack(Material.IRON_HELMET);
                helmet.setDurability((short) 0);
                Iterator<?> hit = helmet.getEnchantments().entrySet().iterator();
                while (hit.hasNext())
                    helmet.removeEnchantment((Enchantment) ((Map.Entry<?, ?>) hit.next()).getKey());
            }

            if (chestplate != null) {
                chestplate = chestplate.clone();
                if (chestplate.getType() == Material.DIAMOND_CHESTPLATE)
                    chestplate = new ItemStack(Material.IRON_CHESTPLATE);
                chestplate.setDurability((short) 0);
                Iterator<?> cit = chestplate.getEnchantments().entrySet().iterator();
                while (cit.hasNext())
                    chestplate.removeEnchantment((Enchantment) ((Map.Entry<?, ?>) cit.next()).getKey());
            }

            if (leggings != null) {
                leggings = leggings.clone();
                if (leggings.getType() == Material.DIAMOND_LEGGINGS)
                    leggings = new ItemStack(Material.IRON_LEGGINGS);
                leggings.setDurability((short) 0);
                Iterator<?> lit = leggings.getEnchantments().entrySet().iterator();
                while (lit.hasNext())
                    leggings.removeEnchantment((Enchantment) ((Map.Entry<?, ?>) lit.next()).getKey());
            }

            if (boots != null) {
                boots = boots.clone();
                if (boots.getType() == Material.DIAMOND_BOOTS)
                    boots = new ItemStack(Material.IRON_BOOTS);
                boots.setDurability((short) 0);
                Iterator<?> bit = boots.getEnchantments().entrySet().iterator();
                while (bit.hasNext())
                    boots.removeEnchantment((Enchantment) ((Map.Entry<?, ?>) bit.next()).getKey());
            }

            PlayerArmor armor = new PlayerArmor(helmet, chestplate, leggings, boots);
            BattlePlugin.instance().playerArmor.put(player, armor);

            if (t.getLifes() > 0) {
                t.setLifes(t.getLifes() - 1);
            } else {
                player.teleport(spawn);
                player.setDisplayName(player.getName());
                t.removePlayer(player);
                Bukkit.broadcastMessage(BattlePlugin.prefix() + player.getName() + " from the " + t.getTeamColor().getLongName() + " team has lost!");
                if (t.getTeamSize() == 0) {
                    t.setLost(true);
                    Bukkit.broadcastMessage(BattlePlugin.prefix() + "Team " + t.getTeamColor().getLongName() + " has lost!");
                }
            }

            Team lastTeam = BattlePlugin.game().getTeamManager().getLastTeam();
            if (lastTeam != null) {
                Bukkit.broadcastMessage(BattlePlugin.prefix() + "Team " + t.getTeamColor().getLongName() + " has won the Battle!");
                for (BattlePlayer p : BattlePlugin.game().getBattlePlayerManager().getAllBattlePlayers()) {
                    p.teleport(spawn);
                    p.setAllowFlight(true);
                    p.setFlying(true);
                }
                BattlePlugin.game().setStarted(false);
                FireworkTimer ft = new FireworkTimer();
                int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlePlugin.instance(), ft, 0, 20L);
                ft.setID(id);
                Bukkit.broadcastMessage(BattlePlugin.prefix() + "Thanks for playing! YT-Battle v" + BattlePlugin.instance().getDescription().getVersion() + " made by EXSolo and Rene8888.");
            }
        }

        BattleUtils.updateScoreboard();

    }
}