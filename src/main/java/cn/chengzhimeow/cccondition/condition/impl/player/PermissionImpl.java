package cn.chengzhimeow.cccondition.condition.impl.player;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import cn.chengzhimeow.cccondition.condition.ArgumentKey;
import org.bukkit.permissions.Permissible;

import java.util.Map;

public final class PermissionImpl extends AbstractCondition {
    @ArgumentKey(keys = {"user"}, disabledCheck = true)
    private Permissible user;

    @ArgumentKey(keys = {"permission", "perm"})
    private String permission;

    public PermissionImpl(CCCondition ccCondition, Map<String, Object> params) {
        super(ccCondition, params);
    }

    @Override
    public boolean onCheck() {
        return this.user.hasPermission(this.permission);
    }
}
