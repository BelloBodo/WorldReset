package de.bellobodo.manager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldManager {

    private static String activeWorld;


    private static World manhunt1_Overworld;

    private static World manhunt1_Nether;

    private static World manhunt1_The_End;

    private static World manhunt2_Overworld;

    private static World manhunt2_Nether;

    private static World manhunt2_The_End;

    public static void createNewWorld(String worldName) {
        if (!worldName.contains("manhunt-1")) {
            if (manhunt1_Overworld == null) {
                createManhunt1();
            } else {
                Bukkit.getLogger().info("Es gibt schon eine Welt. (manhunt-1)");
            }
        } else {
            if (manhunt2_Overworld == null) {
                createManhunt2();
            } else {
                Bukkit.getLogger().info("Es gibt schon eine Welt. (manhunt-2)");
            }
        }
    }

    private static void createManhunt1() {
        WorldCreator worldCreator = new WorldCreator("manhunt-1");
        worldCreator.environment(World.Environment.NORMAL);

        manhunt1_Overworld = Bukkit.getServer().createWorld(worldCreator);

        worldCreator = new WorldCreator("manhunt-1_nether");
        worldCreator.environment(World.Environment.NETHER);

        manhunt1_Nether = Bukkit.getServer().createWorld(worldCreator);

        worldCreator = new WorldCreator("manhunt-1_the_end");
        worldCreator.environment(World.Environment.THE_END);

        manhunt1_The_End = Bukkit.getServer().createWorld(worldCreator);
    }

    private static void createManhunt2() {
        WorldCreator worldCreator = new WorldCreator("manhunt-2");
        worldCreator.environment(World.Environment.NORMAL);

        manhunt2_Overworld = Bukkit.getServer().createWorld(worldCreator);

        worldCreator = new WorldCreator("manhunt-2_nether");
        worldCreator.environment(World.Environment.NETHER);

        manhunt2_Nether = Bukkit.getServer().createWorld(worldCreator);

        worldCreator = new WorldCreator("manhunt-2_the_end");
        worldCreator.environment(World.Environment.THE_END);

        manhunt2_The_End = Bukkit.getServer().createWorld(worldCreator);
    }

    private static void deleteManhunt1() {
        World[] worlds = {manhunt1_Overworld, manhunt1_Nether, manhunt1_The_End};

        for (World world: worlds) {
            world.getPlayers().forEach(players -> {
                players.teleport(manhunt2_Overworld.getSpawnLocation());
            });

            Bukkit.unloadWorld(world, false);

            world.getWorldFolder().delete();
        }
    }

    private static void deleteManhunt2() {
        World[] worlds = {manhunt2_Overworld, manhunt2_Nether, manhunt2_The_End};

        for (World world: worlds) {
            world.getPlayers().forEach(players -> {
                players.teleport(manhunt1_Overworld.getSpawnLocation());
            });

            Bukkit.unloadWorld(world, false);

            world.getWorldFolder().delete();
        }
    }

}
