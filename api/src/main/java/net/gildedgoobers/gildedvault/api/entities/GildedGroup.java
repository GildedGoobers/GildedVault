package net.gildedgoobers.gildedvault.api.entities;

import net.gildedgoobers.gildedvault.api.DisplayMeta;
import net.gildedgoobers.gildedvault.api.PermissionSet;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

/**
 * The permissions and display meta representation of a group
 */
public interface GildedGroup extends PermissionSet, DisplayMeta {

    /**
     * @return The name of the group
     */
    String getGroupName();

    /**
     * @return The display name of the group
     */
    String getGroupDisplayName();

    /**
     * @param player The player
     * @return true if the player is in this group
     */
    default boolean hasPlayer(OfflinePlayer player) {
        return getPlatform().getPlayer(player).isInGroup(getGroupName());
    }

    /**
     * @param uniqueId the uuid of the player
     * @return true if the player is in this group
     */
    default boolean hasPlayer(UUID uniqueId) {
        return getPlatform().getPlayer(uniqueId).isInGroup(getGroupName());
    }

}
