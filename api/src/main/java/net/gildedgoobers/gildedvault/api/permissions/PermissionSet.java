package net.gildedgoobers.gildedvault.api.permissions;

import java.util.Set;

public interface PermissionSet {

    void setPermission(String permission, boolean state);

    void unsetPermission(String permission);

    boolean hasPermission(String permission);

    Set<String> getPermissions();

}
