package cn.chengzhimeow.cccondition.manager;

import cn.chengzhimeow.cccondition.CCCondition;
import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

public interface PreProcessManager {
    Object handle(CCCondition ccCondition, AbstractCondition condition, Object value);

    class PlaceholderAPIPreProcessManager implements PreProcessManager {
        @Override
        public Object handle(CCCondition ccCondition, AbstractCondition condition, Object value) {
            return PlaceholderAPI.setPlaceholders(
                    condition.getParam("placeholder_owner", OfflinePlayer.class),
                    (String) value
            );
        }
    }
}
