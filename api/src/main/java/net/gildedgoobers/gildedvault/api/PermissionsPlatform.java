package net.gildedgoobers.gildedvault.api;

import net.gildedgoobers.gildedvault.api.entities.GildedGroup;
import net.gildedgoobers.gildedvault.api.entities.GildedPlayer;
import org.bukkit.OfflinePlayer;

import java.util.Set;
import java.util.UUID;

/**
 * This class handles players, groups, and permissions data.
 */
public interface PermissionsPlatform {

    /**
     * @return All permissions that are true by default unless set otherwise
     */
    Set<String> getDefaultPermissions();

    /**
     * @param player The player
     * @return The permission representation of the player
     */
    default GildedPlayer getPlayer(OfflinePlayer player) {
        return getPlayer(player.getUniqueId());
    }

    /**
     * @param uniqueId The UUID of the player
     * @return The permission representation of the player
     */
    GildedPlayer getPlayer(UUID uniqueId);

    /**
     * @param player The player
     * @param permission The permission
     * @return If the permission is set, will return whether the player is granted the permission. Otherwise, will return the default state of the permission.
     */
    default boolean playerHasPermission(OfflinePlayer player, String permission) {
        return getPlayer(player).hasPermission(permission);
    }

    /**
     * @param player The player
     * @param permission The permission
     * @return If the player is op, will return true. If the permission is set, will return whether the player is granted the permission. Otherwise, will return the default state of the permission.
     */
    default boolean playerHasPermissionOrIsOp(OfflinePlayer player, String permission) {
        return player.isOp() || playerHasPermission(player, permission);
    }

    /**
     * @param groupName The name of the group
     * @return Gets a group by its name
     */
    GildedGroup getGroup(String groupName);

    /**
     * @return All known groups
     */
    Set<? extends GildedGroup> getGroups();

}
