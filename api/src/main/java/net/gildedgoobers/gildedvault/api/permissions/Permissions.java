package net.gildedgoobers.gildedvault.api.permissions;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public interface Permissions {

    default PermissionSet getPlayer(OfflinePlayer player) {
        return getPlayer(player.getUniqueId());
    }

    PermissionSet getPlayer(UUID uuid);

    default boolean playerHasPermission(OfflinePlayer player, String permission) {
        return getPlayer(player).hasPermission(permission);
    }

    default boolean playerHasPermissionOrIsOp(OfflinePlayer player, String permission) {
        return player.isOp() || playerHasPermission(player, permission);
    }

    PermissionSet getGroup(String group);

}
