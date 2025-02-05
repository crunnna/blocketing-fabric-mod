package com.blocketing.events;

import com.blocketing.discord.DiscordBot;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/**
 * This class would be responsible for handling player events like join and disconnect events.
 */
public class PlayerEventHandler {

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register(PlayerEventHandler::onPlayerJoin);
        ServerPlayConnectionEvents.DISCONNECT.register(PlayerEventHandler::onPlayerDisconnect);
    }

    /**
     * Handles player join events.
     * @param handler The network handler for the player.
     * @param sender The packet sender.
     * @param server The Minecraft server.
     */
    private static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        String playerName = handler.getPlayer().getGameProfile().getName();
        String playerUUID = handler.getPlayer().getUuid().toString();
        String avatarUrl = "https://api.mineatar.io/face/" + playerUUID + "?scale=8"; // Get the player's avatar    (scale= (4=mini, 8=normal, 12=big))

        DiscordBot.sendEmbed("Player Joined", "**" + playerName + "** joined the server.", 0x00FF00, avatarUrl); // Green colored embed
    }

    /**
     * Handles player disconnect events.
     * @param handler The network handler for the player.
     * @param server The Minecraft server.
     */
    private static void onPlayerDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        String playerName = handler.getPlayer().getGameProfile().getName();
        String playerUUID = handler.getPlayer().getUuid().toString();
        String avatarUrl = "https://api.mineatar.io/face/" + playerUUID + "?scale=8"; // Get the player's avatar    (scale= (4=mini, 8=normal, 12=big))

        DiscordBot.sendEmbed("Player Left", "**" + playerName + "** left the server.", 0xFF0000, avatarUrl); // Red colored embed
    }
}
