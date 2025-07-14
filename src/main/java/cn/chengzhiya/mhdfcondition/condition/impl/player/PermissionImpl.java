package cn.chengzhiya.mhdfcondition.condition.impl.player;

import cn.chengzhiya.mhdfcondition.condition.ArgumentKey;
import cn.chengzhiya.mhdfcondition.condition.Condition;
import org.bukkit.command.CommandSender;

import java.util.Map;

public final class PermissionImpl extends Condition {
    @ArgumentKey(keys = {"user"})
    private CommandSender user;

    @ArgumentKey(keys = {"permission", "perm"})
    private String permission;

    public PermissionImpl(Map<String, Object> pramHashMap) {
        super(pramHashMap);
    }

    @Override
    public boolean onCheck() {
        return this.user.hasPermission(this.permission);
    }
}
