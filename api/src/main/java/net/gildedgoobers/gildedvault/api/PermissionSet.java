package net.gildedgoobers.gildedvault.api;

import net.gildedgoobers.gildedvault.api.entities.GildedGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the permissions of an entity (player or group)
 */
public interface PermissionSet {

    /**
     * @return The platform that this object is built from
     */
    PermissionsPlatform getPlatform();

    /**
     * Sets the permission to the given state
     * @param permission The permission
     * @param state The state
     */
    void setPermission(String permission, boolean state);

    /**
     * Sets the permission to true
     * @param permission The permission
     */
    default void addPermission(String permission) {
        setPermission(permission, true);
    }

    /**
     * Sets the permission to false
     * @param permission The permission
     */
    default void removePermission(String permission) {
        setPermission(permission, false);
    }

    /**
     * Reverts the permission to its default state
     * @param permission The permission
     */
    void unsetPermission(String permission);

    /**
     * @param permission The permission
     * @return If the permission has been explicitly set, returns true.
     */
    boolean isPermissionSet(String permission);

    /**
     * @param permission The permission
     * @return If the permission is set, will return whether the player is granted the permission. Otherwise, will return the default state of the permission.
     */
    default boolean hasPermission(String permission) {
        return isPermissionSet(permission)
            ? getPermissions().contains(permission)
            : getPlatform().getDefaultPermissions().contains(permission);
    }

    /**
     * @return All permissions explicitly set to true, including inherited permissions
     */
    default Set<String> getPermissions() {
        HashSet<String> set = new HashSet<>(getSpecificPermissions());
        getParents().forEach(parent -> set.addAll(parent.getPermissions()));
        return Set.of(set.toArray(new String[0]));
    }

    /**
     * @return Returns only the permissions set to true in this node. Does not include inherited permissions
     */
    Set<String> getSpecificPermissions();

    /**
     * @return All parent groups, including those inherited
     */
    default Set<? extends GildedGroup> getParents() {
        Set<? extends GildedGroup> parents = getSpecificParents();
        HashSet<GildedGroup> set = new HashSet<>(parents);
        parents.forEach(parent -> set.addAll(parent.getParents()));
        return set;
    }

    /**
     * @return Only parent groups specific to this node. Does not include inherited parent groups
     */
    Set<? extends GildedGroup> getSpecificParents();

    /**
     * Adds the parent group to this entity
     * @param parent The parent group
     */
    default void addParent(GildedGroup parent) {
        addParent(parent.getGroupName());
    }

    /**
     * Adds the parent group to this entity
     * @param parentName The name of the parent group
     */
    void addParent(String parentName);

    /**
     * Removes the parent group from this entity
     * @param parent The parent group
     */
    default void removeParent(GildedGroup parent) {
        removeParent(parent.getGroupName());
    }

    /**
     * Removes the parent group from this entity
     * @param parentName THe name of the parent group
     */
    void removeParent(String parentName);

}
