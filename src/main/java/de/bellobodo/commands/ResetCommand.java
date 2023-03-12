package de.bellobodo.commands;

import de.bellobodo.WorldReset;
import de.bellobodo.manager.WorldManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("");
            return true;
        }

        final Player player = (Player) sender;

        final World world = player.getWorld();

        WorldManager.createNewWorld(world.getName());

        task = Bukkit.getScheduler().runTaskTimer(WorldReset.getInstance(), () -> {
            World newWorld = Bukkit.getWorld(newWorldName);

            if (newWorld != null) {
                world.getPlayers().forEach(players -> {
                    players.teleport(newWorld.getSpawnLocation());
                });

                Bukkit.unloadWorld(world, false);

                world.getWorldFolder().delete();

                task.cancel();
            }
        }, 20, 20);
        return true;
    }

    private static BukkitTask task;
}
