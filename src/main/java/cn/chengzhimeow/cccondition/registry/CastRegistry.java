package cn.chengzhimeow.cccondition.registry;

import cn.chengzhimeow.cccondition.condition.ConditionBuilder;
import cn.chengzhimeow.cccondition.manager.CastManager;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;

public final class CastRegistry extends Registry<Class<?>, CastManager> {
    @Override
    public Map<Class<?>, CastManager> defaultRegistry() {
        Map<Class<?>, CastManager> map = super.defaultRegistry();

        map.put(String.class, new CastManager.StringCastManager());

        map.put(long.class, new CastManager.LongCastManager());
        map.put(Long.class, new CastManager.LongCastManager());

        map.put(int.class, new CastManager.IntCastManager());
        map.put(Integer.class, new CastManager.IntCastManager());

        map.put(short.class, new CastManager.ShortCastManager());
        map.put(Short.class, new CastManager.ShortCastManager());

        map.put(double.class, new CastManager.DoubleCastManager());
        map.put(Double.class, new CastManager.DoubleCastManager());

        map.put(float.class, new CastManager.FloatCastManager());
        map.put(Float.class, new CastManager.FloatCastManager());

        map.put(boolean.class, new CastManager.BooleanCastManager());
        map.put(Boolean.class, new CastManager.BooleanCastManager());

        map.put(byte.class, new CastManager.ByteCastManager());
        map.put(Byte.class, new CastManager.ByteCastManager());

        map.put(char.class, new CastManager.CharCastManager());
        map.put(Character.class, new CastManager.CharCastManager());

        map.put(Component.class, new CastManager.ComponentCastManager());

        map.put(BossBar.Color.class, new CastManager.BossBarColorCastManager());
        map.put(BossBar.Overlay.class, new CastManager.BossBarOverlayCastManager());

        map.put(Player.class, new CastManager.PlayerCastManager());
        map.put(OfflinePlayer.class, new CastManager.OfflinePlayerCastManager());

        map.put(ConditionBuilder.Builder.class, new CastManager.ConditionBuilderManager());

        return map;
    }

    @Override
    public boolean replace() {
        return true;
    }
}
