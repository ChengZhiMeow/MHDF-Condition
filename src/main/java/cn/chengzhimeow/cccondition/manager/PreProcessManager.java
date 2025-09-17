package cn.chengzhimeow.cccondition.manager;

import cn.chengzhimeow.cccondition.condition.AbstractCondition;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

public interface PreProcessManager {
    Object handle(AbstractCondition action, Object value);

    class PlaceholderAPIPreProcessManager implements PreProcessManager {
        @Override
        public Object handle(AbstractCondition condition, Object value) {
            return PlaceholderAPI.setPlaceholders(
                    condition.getPram("placeholder_owner", OfflinePlayer.class),
                            (String) value
                    );
        }
    }
}
