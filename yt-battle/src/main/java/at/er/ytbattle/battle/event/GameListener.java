package at.er.ytbattle.battle.event;

import java.io.Serializable;
import java.util.Arrays;

import net.minecraft.server.v1_6_R3.Packet205ClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.timer.FireworkTimer;
import at.er.ytbattle.battle.timer.InvincibilityTimer;

public class GameListener implements Listener, Serializable {
	private static final long serialVersionUID = 1L;

	private Battle plugin;

	public GameListener(Battle plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {

		plugin.setTags();
		plugin.updateScoreboard();
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {

		int blockID = event.getBlock().getTypeId();
		int blockMeta = event.getBlock().getData();

		Player player = (Player) event.getPlayer();

		if (blockID == 35 && plugin.getGame().isStarted() && plugin.getGame().getPlayers().contains(player.getName())) {

			switch (blockMeta) {
			case 0:
				if (plugin.getGame().getWhite().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a white wool!");
					plugin.getGame().getWhite().getBlockPlaceTimer().setWools(plugin.getGame().getWhite().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			case 4:
				if (plugin.getGame().getYellow().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a yellow wool!");
					plugin.getGame().getYellow().getBlockPlaceTimer().setWools(plugin.getGame().getYellow().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			case 5:
				if (plugin.getGame().getGreen().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a green wool!");
					plugin.getGame().getGreen().getBlockPlaceTimer().setWools(plugin.getGame().getGreen().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			case 9:
				if (plugin.getGame().getCyan().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a cyan wool!");
					plugin.getGame().getCyan().getBlockPlaceTimer().setWools(plugin.getGame().getCyan().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			case 10:
				if (plugin.getGame().getPurple().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a purple wool!");
					plugin.getGame().getPurple().getBlockPlaceTimer().setWools(plugin.getGame().getPurple().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			case 11:
				if (plugin.getGame().getBlue().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a blue wool!");
					plugin.getGame().getBlue().getBlockPlaceTimer().setWools(plugin.getGame().getBlue().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			case 14:
				if (plugin.getGame().getRed().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a red wool!");
					plugin.getGame().getRed().getBlockPlaceTimer().setWools(plugin.getGame().getRed().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			case 15:
				if (plugin.getGame().getBlack().getPlayers().contains(player.getName()) == false) {
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a black wool!");
					plugin.getGame().getBlack().getBlockPlaceTimer().setWools(plugin.getGame().getBlack().getBlockPlaceTimer().getWools() + 1);
				} else {
					player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
					event.setCancelled(true);
				}
				break;
			}
		}

		if (blockID == 20 && player.getWorld() == plugin.getGame().getSpawn().getLocation().getWorld() && plugin.getGame().isStarted() && player.getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
			player.sendMessage(Battle.prefix() + "You have reached the Battleborder!");
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityExplode(EntityExplodeEvent event) {
		if (event.getEntityType() == EntityType.CREEPER)
			event.blockList().clear();

		for (Block block : event.blockList()) {
			if (block.getType() == Material.WOOL || block.getType() == Material.GLASS)
				event.blockList().remove(block);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerShears(PlayerShearEntityEvent event) {
		event.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		int blockMeta = event.getBlock().getData();

		ItemStack base = new ItemStack(Material.QUARTZ_ORE);
		ItemMeta baseMeta = base.getItemMeta();
		baseMeta.setDisplayName(ChatColor.GRAY + "Base Block");
		baseMeta.setLore(Arrays.asList("Place me to create a base"));
		base.setItemMeta(baseMeta);

		if (event.getBlock().getType() == Material.GLASS && player.getWorld() == plugin.getGame().getSpawn().getLocation().getWorld() && plugin.getGame().isStarted()) {
			event.setCancelled(true);
			player.sendMessage(Battle.prefix() + "You are unable to place a Block of the Bordermaterial.");
		}

		if (event.getBlock().getTypeId() == 35 && plugin.getGame().isStarted() && plugin.getGame().getPlayers().contains(player.getName())) {

			switch (blockMeta) {
			case 0:
				if (plugin.getGame().getWhite().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			case 4:
				if (plugin.getGame().getYellow().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			case 5:
				if (plugin.getGame().getGreen().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			case 9:
				if (plugin.getGame().getCyan().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			case 10:
				if (plugin.getGame().getPurple().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			case 11:
				if (plugin.getGame().getBlue().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			case 14:
				if (plugin.getGame().getRed().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			case 15:
				if (plugin.getGame().getBlack().getPlayers().contains(player.getName()) == false) {
					player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
					event.setCancelled(true);
				}
				break;
			}
		}

		// create Base
		if (event.getBlock().getType() == Material.QUARTZ_ORE) {
			Location l = event.getBlock().getLocation();

			buildBase(l);
		}
	}

	// Interact

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && player.getItemInHand().getType() == Material.GHAST_TEAR && plugin.getGame().isStarted()) {

			if (!(plugin.getGame().getPlayers().contains(player.getName())) || plugin.getGame().getSpectators().contains(player.getName())) {
				player.sendMessage(Battle.prefix() + "You left the battle, this item is worthless now!");
			}

			if (plugin.getGame().getRed().getPlayers().contains(player.getName())) {
				plugin.getGame().getRed().setLifes(plugin.getGame().getRed().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "Red team has now " + plugin.getGame().getRed().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (plugin.getGame().getBlue().getPlayers().contains(player.getName())) {
				plugin.getGame().getBlue().setLifes(plugin.getGame().getBlue().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "Blue team has now " + plugin.getGame().getBlue().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (plugin.getGame().getGreen().getPlayers().contains(player.getName())) {
				plugin.getGame().getGreen().setLifes(plugin.getGame().getGreen().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "Green team has now " + plugin.getGame().getGreen().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (plugin.getGame().getYellow().getPlayers().contains(player.getName())) {
				plugin.getGame().getYellow().setLifes(plugin.getGame().getYellow().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "Yellow team has now " + plugin.getGame().getYellow().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (plugin.getGame().getPurple().getPlayers().contains(player.getName())) {
				plugin.getGame().getPurple().setLifes(plugin.getGame().getPurple().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "Purple team has now " + plugin.getGame().getPurple().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (plugin.getGame().getCyan().getPlayers().contains(player.getName())) {
				plugin.getGame().getCyan().setLifes(plugin.getGame().getCyan().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "Cyan team has now " + plugin.getGame().getCyan().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (plugin.getGame().getBlack().getPlayers().contains(player.getName())) {
				plugin.getGame().getBlack().setLifes(plugin.getGame().getBlack().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "Black team has now " + plugin.getGame().getBlack().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (plugin.getGame().getWhite().getPlayers().contains(player.getName())) {
				plugin.getGame().getWhite().setLifes(plugin.getGame().getWhite().getLifes() + 1);
				player.sendMessage(Battle.prefix() + "White team has now " + plugin.getGame().getWhite().getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}

			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			} else {
				player.getInventory().setItemInHand(new ItemStack(Material.AIR));
			}

			plugin.updateScoreboard();
		}
	}

	// Death

	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.SHEEP)
			event.getDrops().clear();
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event) {
		final Player player = event.getEntity();
		Location spawn = plugin.getGame().getSpawn().getLocation();
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			public void run() {
				if (player.isDead()) {
					Packet205ClientCommand packet = new Packet205ClientCommand();
					packet.a = 1;
					((CraftPlayer) player).getHandle().playerConnection.a(packet);
				}
			}
		}, 20L);

		if (plugin.getGame().getPlayers().contains(player.getName())) {
			if (plugin.getGame().isStarted()) {

				if (plugin.getGame().getRed().getPlayers().contains(player.getName()) && plugin.getGame().getRed().getLifes() > 0)
					plugin.getGame().getRed().setLifes(plugin.getGame().getRed().getLifes() - 1);
				if (plugin.getGame().getBlue().getPlayers().contains(player.getName()) && plugin.getGame().getBlue().getLifes() > 0)
					plugin.getGame().getBlue().setLifes(plugin.getGame().getRed().getLifes() - 1);
				if (plugin.getGame().getGreen().getPlayers().contains(player.getName()) && plugin.getGame().getGreen().getLifes() > 0)
					plugin.getGame().getGreen().setLifes(plugin.getGame().getGreen().getLifes() - 1);
				if (plugin.getGame().getYellow().getPlayers().contains(player.getName()) && plugin.getGame().getYellow().getLifes() > 0)
					plugin.getGame().getYellow().setLifes(plugin.getGame().getYellow().getLifes() - 1);
				if (plugin.getGame().getPurple().getPlayers().contains(player.getName()) && plugin.getGame().getPurple().getLifes() > 0)
					plugin.getGame().getPurple().setLifes(plugin.getGame().getPurple().getLifes() - 1);
				if (plugin.getGame().getCyan().getPlayers().contains(player.getName()) && plugin.getGame().getCyan().getLifes() > 0)
					plugin.getGame().getCyan().setLifes(plugin.getGame().getCyan().getLifes() - 1);
				if (plugin.getGame().getBlack().getPlayers().contains(player.getName()) && plugin.getGame().getBlack().getLifes() > 0)
					plugin.getGame().getBlack().setLifes(plugin.getGame().getBlack().getLifes() - 1);
				if (plugin.getGame().getWhite().getPlayers().contains(player.getName()) && plugin.getGame().getWhite().getLifes() > 0)
					plugin.getGame().getWhite().setLifes(plugin.getGame().getWhite().getLifes() - 1);

				plugin.updateScoreboard();

				if (plugin.getGame().getRed().getLifes() <= 0 && plugin.getGame().getTeams().contains("red") && plugin.getGame().getRed().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getRed().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the red team has lost!");

					if (plugin.getGame().getRed().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("red");
						Bukkit.broadcastMessage(Battle.prefix() + "Team red has lost!");
					}
				}

				if (plugin.getGame().getBlue().getLifes() <= 0 && plugin.getGame().getTeams().contains("blue") && plugin.getGame().getBlue().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getBlue().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the blue team has lost!");

					if (plugin.getGame().getBlue().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("blue");
						Bukkit.broadcastMessage(Battle.prefix() + "Team blue has lost!");
					}
				}

				if (plugin.getGame().getGreen().getLifes() <= 0 && plugin.getGame().getTeams().contains("green") && plugin.getGame().getGreen().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getGreen().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the green team has lost!");

					if (plugin.getGame().getGreen().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("green");
						Bukkit.broadcastMessage(Battle.prefix() + "Team green has lost!");
					}
				}

				if (plugin.getGame().getYellow().getLifes() <= 0 && plugin.getGame().getTeams().contains("yellow") && plugin.getGame().getYellow().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getYellow().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the yellow team has lost!");

					if (plugin.getGame().getYellow().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("yellow");
						Bukkit.broadcastMessage(Battle.prefix() + "Team yellow has lost!");
					}
				}

				if (plugin.getGame().getPurple().getLifes() <= 0 && plugin.getGame().getTeams().contains("purple") && plugin.getGame().getPurple().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getPurple().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the purple team has lost!");

					if (plugin.getGame().getPurple().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("purple");
						Bukkit.broadcastMessage(Battle.prefix() + "Team purple has lost!");
					}
				}

				if (plugin.getGame().getCyan().getLifes() <= 0 && plugin.getGame().getTeams().contains("cyan") && plugin.getGame().getCyan().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getCyan().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the cyan team has lost!");

					if (plugin.getGame().getCyan().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("cyan");
						Bukkit.broadcastMessage(Battle.prefix() + "Team cyan has lost!");
					}
				}

				if (plugin.getGame().getBlack().getLifes() <= 0 && plugin.getGame().getTeams().contains("black") && plugin.getGame().getBlack().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getBlack().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the black team has lost!");

					if (plugin.getGame().getBlack().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("black");
						Bukkit.broadcastMessage(Battle.prefix() + "Team black has lost!");
					}
				}

				if (plugin.getGame().getWhite().getLifes() <= 0 && plugin.getGame().getTeams().contains("white") && plugin.getGame().getWhite().getPlayers().contains(player.getName())) {
					player.teleport(spawn);
					player.setDisplayName(player.getName());
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getWhite().getPlayers().remove(player.getName());
					Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the white team has lost!");

					if (plugin.getGame().getWhite().getPlayers().size() == 0) {
						plugin.getGame().getTeams().remove("white");
						Bukkit.broadcastMessage(Battle.prefix() + "Team white has lost!");
					}
				}

				if (plugin.getGame().getTeams().size() == 1) {
					if (plugin.getGame().getTeams().contains("red")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team red has won the Battle!");
						for (String s : plugin.getGame().getRed().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					if (plugin.getGame().getTeams().contains("blue")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team blue has won the Battle!");
						for (String s : plugin.getGame().getBlue().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					if (plugin.getGame().getTeams().contains("green")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team green has won the Battle!");
						for (String s : plugin.getGame().getGreen().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					if (plugin.getGame().getTeams().contains("yellow")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team yellow has won the Battle!");
						for (String s : plugin.getGame().getYellow().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					if (plugin.getGame().getTeams().contains("purple")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team purple has won the Battle!");
						for (String s : plugin.getGame().getPurple().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					if (plugin.getGame().getTeams().contains("cyan")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team cyan has won the Battle!");
						for (String s : plugin.getGame().getCyan().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					if (plugin.getGame().getTeams().contains("black")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team black has won the Battle!");
						for (String s : plugin.getGame().getBlack().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					if (plugin.getGame().getTeams().contains("white")) {
						Bukkit.broadcastMessage(Battle.prefix() + "Team white has won the Battle!");
						for (String s : plugin.getGame().getWhite().getPlayers()) {
							Player p = Bukkit.getPlayer(s);

							p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
							p.teleport(spawn);
							p.setAllowFlight(true);
							p.setFlying(true);
						}
					}

					Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new FireworkTimer(), 0, 80);
					Bukkit.broadcastMessage(Battle.prefix() + "Thanks for playing! Battle Plugin v" + plugin.getDescription().getVersion() + " made by EXSolo and Reen8888.");
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();

		player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

		player.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
		player.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
		player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
		player.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));

		new InvincibilityTimer(plugin, player.getName(), 10);
		
		if (plugin.getGame().isStarted() && plugin.getGame().getPlayers().contains(player.getName())) {
			event.setRespawnLocation(plugin.getGame().getSpawn().getLocation());

			if (plugin.getGame().getRed().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 14));
			}
			if (plugin.getGame().getBlue().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 11));
			}
			if (plugin.getGame().getGreen().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 5));
			}
			if (plugin.getGame().getYellow().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 4));
			}
			if (plugin.getGame().getPurple().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 10));
			}
			if (plugin.getGame().getCyan().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 9));
			}
			if (plugin.getGame().getBlack().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 15));
			}
			if (plugin.getGame().getWhite().getPlayers().contains(player.getName())) {
				player.getInventory().addItem(new ItemStack(Material.WOOL));
			}

			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if (plugin.getGame().isStarted()) {
			if (plugin.getGame().getRed().getPlayers().contains(player.getName()) || plugin.getGame().getBlue().getPlayers().contains(player.getName()) || plugin.getGame().getGreen().getPlayers().contains(player.getName())
					|| plugin.getGame().getYellow().getPlayers().contains(player.getName()) || plugin.getGame().getPurple().getPlayers().contains(player.getName())) {
				if (!player.getInventory().contains(Material.WOOL)) {
					player.sendMessage(Battle.prefix() + ChatColor.RED + "Your Inventory has to contain a wool of your colour!");
				}
			}
		}

	}

	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent event) {
		ItemStack ce = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta ceMeta = (BookMeta) ce.getItemMeta();

		ceMeta.setDisplayName(ChatColor.GOLD + "Cheat Protection");
		ceMeta.addPage("#DisabledCheating :)\nNo wool by crafting :(");
		ceMeta.setAuthor("Ihr(e) Bundeskanzler(in)");

		ce.setItemMeta(ceMeta);

		ItemStack tear = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta tearMeta = tear.getItemMeta();
		tearMeta.setDisplayName(ChatColor.GOLD + "Live Exchanger");
		tearMeta.setLore(Arrays.asList("Right Click Me"));
		tear.setItemMeta(tearMeta);

		if (event.getInventory().getResult().getType() == Material.WOOL)
			event.getInventory().setResult(ce);

		if (event.getInventory().getResult().getType() == Material.CARPET)
			event.getInventory().setResult(tear);
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (plugin.getGame().isStarted()) {

			if (plugin.getGame().getTeams().size() > 1) {

				if (plugin.getGame().getRed().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.DARK_RED + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}

				if (plugin.getGame().getBlue().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.DARK_BLUE + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}

				if (plugin.getGame().getGreen().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.DARK_GREEN + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}

				if (plugin.getGame().getYellow().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.YELLOW + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}

				if (plugin.getGame().getPurple().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.DARK_PURPLE + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}

				if (plugin.getGame().getCyan().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.DARK_AQUA + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}

				if (plugin.getGame().getBlack().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.BLACK + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}

				if (plugin.getGame().getWhite().getPlayers().contains(player.getName())) {
					player.setDisplayName(ChatColor.BOLD + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				}
			} else {
				String w = plugin.getGame().getTeams().get(0);

				if (w.equals("red")) {
					if (plugin.getGame().getRed().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}

				if (w.equals("blue")) {
					if (plugin.getGame().getBlue().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}

				if (w.equals("green")) {
					if (plugin.getGame().getGreen().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}

				if (w.equals("yellow")) {
					if (plugin.getGame().getYellow().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}

				if (w.equals("purple")) {
					if (plugin.getGame().getPurple().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}

				if (w.equals("cyan")) {
					if (plugin.getGame().getCyan().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}

				if (w.equals("black")) {
					if (plugin.getGame().getBlack().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}

				if (w.equals("white")) {
					if (plugin.getGame().getWhite().getPlayers().contains(player.getName())) {
						player.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + player.getName());
					}
				}
			}
		}
	}

	public void buildBase(Location l) {
		World w = l.getWorld();

		for (int x = -2; x <= 2; x++) {
			for (int y = 0; y <= 2; y++) {
				for (int z = -2; z <= 2; z++) {
					Material type = w.getBlockAt((int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z).getType();
					if (type != Material.AIR && type != Material.IRON_ORE && type != Material.GOLD_ORE && type != Material.DIAMOND_ORE && type != Material.GLASS && type != Material.WOOL) {
						w.getBlockAt((int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z).setType(Material.AIR);
						w.playEffect(new Location(l.getWorld(), (int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z), Effect.MOBSPAWNER_FLAMES, 0);
					}
				}
			}
		}

		Location tmp = l;

		tmp.setZ(tmp.getZ() - 2);
		tmp.setX(tmp.getX() - 2);

		w.getBlockAt(tmp).setType(Material.CHEST);

		tmp.setZ(tmp.getZ() + 1);

		w.getBlockAt(tmp).setType(Material.CHEST);

		Chest c = (Chest) w.getBlockAt(tmp).getState();

		c.getInventory().addItem(new ItemStack(Material.POTATO_ITEM, 16));
		c.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		c.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
		c.getInventory().addItem(new ItemStack(Material.STONE_AXE));
		c.getInventory().addItem(new ItemStack(Material.STONE_SPADE));
	}
}
