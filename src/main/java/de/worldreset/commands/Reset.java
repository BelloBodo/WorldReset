package de.worldreset.commands;

import de.worldreset.WorldReset;
import jdk.internal.org.jline.utils.ShutdownHooks;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.FileUtil;

import java.io.File;

public class Reset implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("");
            return true;
        }

        Player player = (Player) sender;

        World world = player.getWorld();

        String newWorldName;

        if (world.getName() != "manhunt-1") {
            Bukkit.createWorld(new WorldCreator("manhunt-1"));
            newWorldName = "manhunt-1";
        } else {
            Bukkit.createWorld(new WorldCreator("manhunt-2"));
            newWorldName = "manhunt-2";
        }

        task = Bukkit.getScheduler().runTaskTimer(WorldReset.getInstance(), () -> {
            World newWorld = Bukkit.getWorld(newWorldName);

            if (newWorld != null) {
                world.getPlayers().forEach(players -> {
                    players.teleport(newWorld.getSpawnLocation());
                });

                task.cancel();
            }
        }, 20, 20);

        Bukkit.unloadWorld(world, false);

        world.getWorldFolder().delete();

        return true;
    }

    private static BukkitTask task;
}
