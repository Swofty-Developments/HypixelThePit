package net.swofty.hypixelthepit.Managers.InterfacesAndEnums;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.*;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import net.swofty.hypixelthepit.Core.Utils;
import net.swofty.hypixelthepit.Managers.TagManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

public interface NameTag {

    static TagManager of(String text) {
        Preconditions.checkArgument(text.length() < 100, "Name length must not exceed 16 characters.");
        return new TagManager(text);
    }

    String getText();

    default void applyTo(Player player) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        int id = player.getEntityId();
        int ping = 0;

        Location location = player.getLocation();

        WrappedGameProfile wrappedGameProfile = WrappedGameProfile.fromPlayer(player);
        EnumWrappers.NativeGameMode nativeGameMode = EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode());

        WrappedChatComponent tabName = WrappedChatComponent.fromText(player.getPlayerListName());

        WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(player);
        WrappedSignedProperty wrappedSignedProperty = new PlayerInfoData(wrappedGameProfile, ping, nativeGameMode, tabName).getProfile().getProperties().get("textures").iterator().next();

        PacketContainer removePlayer = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
        PacketContainer addPlayer = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);

        PacketContainer destroyEntity = protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        PacketContainer namedEntitySpawn = protocolManager.createPacket(PacketType.Play.Server.NAMED_ENTITY_SPAWN);

        removePlayer.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
        removePlayer.getPlayerInfoDataLists().write(0, Utils.getPlayerInfoDataList(player));

        addPlayer.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);

        PlayerInfoData playerInfoData = new PlayerInfoData(wrappedGameProfile.withName(this.getText()), ping, nativeGameMode, tabName);
        playerInfoData.getProfile().getProperties().clear();
        playerInfoData.getProfile().getProperties().get("textures").add(wrappedSignedProperty);

        addPlayer.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));

        destroyEntity.getIntegerArrays().write(0, new int[]{id});

        namedEntitySpawn.getIntegers().write(0, id);

        namedEntitySpawn.getUUIDs().write(0, player.getUniqueId());

        namedEntitySpawn.getDoubles().write(0, location.getX());
        namedEntitySpawn.getDoubles().write(1, location.getY());
        namedEntitySpawn.getDoubles().write(2, location.getZ());

        namedEntitySpawn.getBytes().write(0, (byte)((int)(location.getYaw() * 256.0F / 360.0F)));
        namedEntitySpawn.getBytes().write(1, (byte)((int)(location.getPitch() * 256.0F / 360.0F)));

        namedEntitySpawn.getDataWatcherModifier().write(0, dataWatcher);

        protocolManager.broadcastServerPacket(removePlayer);
        protocolManager.broadcastServerPacket(addPlayer);

        Bukkit.getOnlinePlayers().stream().filter(Predicates.not(player::equals)).forEach(o -> {
            try {
                protocolManager.sendServerPacket(o, destroyEntity);
                protocolManager.sendServerPacket(o, namedEntitySpawn);
            } catch (InvocationTargetException exception) {
                exception.printStackTrace();
            }
        });
    }
}