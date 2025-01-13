package net.gildedgoobers.gildedvault.api.entities;

import net.gildedgoobers.gildedvault.api.DisplayMeta;
import net.gildedgoobers.gildedvault.api.PermissionSet;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

/**
 * A permissions and display meta representation of a player
 */
public interface GildedPlayer extends PermissionSet, DisplayMeta {

    /**
     * @return The offline player represented by this object
     */
    OfflinePlayer getOfflinePlayer();

    /**
     * @return The player represented by this object
     */
    default Player getPlayer() {
        return getOfflinePlayer().getPlayer();
    }

    /**
     * @return The uuid of the player represented by this object
     */
    default UUID getUniqueId() {
        return getOfflinePlayer().getUniqueId();
    }

    /**
     * @param permission The permission
     * @return If the player is op, will return true. If the permission is set, will return whether the player is granted the permission. Otherwise, will return the default state of the permission.
     */
    default boolean playerHasPermissionOrIsOp(String permission) {
        return getOfflinePlayer().isOp() || hasPermission(permission);
    }

    /**
     * @param groupName The name of the group
     * @return true if player is in the given group
     */
    default boolean isInGroup(String groupName) {
        return getGroups().stream().anyMatch(group -> group.getGroupName().equals(groupName));
    }

    /**
     * Synonym for {@link #getParents()}
     */
    default Set<? extends GildedGroup> getGroups() {
        return getParents();
    }

    /**
     * Synonym for {@link #addParent(GildedGroup)}
     */
    default void addGroup(GildedGroup parent) {
        addParent(parent);
    }

    /**
     * Synonym for {@link #addParent(String)}
     */
    default void addGroup(String parentName) {
        addParent(parentName);
    }

    /**
     * Synonym for {@link #removeParent(GildedGroup)}
     */
    default void removeGroup(GildedGroup parent) {
        removeParent(parent);
    }

    /**
     * Synonym for {@link #removeParent(String)}
     */
    default void removeGroup(String parentName) {
        removeParent(parentName);
    }

}
