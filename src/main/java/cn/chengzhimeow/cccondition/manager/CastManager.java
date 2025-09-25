package cn.chengzhimeow.cccondition.manager;

import cn.chengzhimeow.cccondition.exception.CastException;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

import java.util.UUID;

public interface CastManager {
    Object cast(Object value, Class<?> type) throws CastException;

    class StringCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) {
            if (value instanceof String) return value;
            else return value.toString();
        }
    }

    class LongCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Long) return value;
            else if (value instanceof String s) return Long.parseLong(s);
            else if (value instanceof Integer v) return v.longValue();
            else if (value instanceof Short v) return v.longValue();
            else if (value instanceof Double v) return v.longValue();
            else if (value instanceof Float v) return v.longValue();
            else throw new CastException(value, type);
        }
    }

    class IntCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Integer) return value;
            else if (value instanceof String s) return Integer.parseInt(s);
            else if (value instanceof Long v) return v.intValue();
            else if (value instanceof Short v) return v.intValue();
            else if (value instanceof Double v) return v.intValue();
            else if (value instanceof Float v) return v.intValue();
            else throw new CastException(value, type);
        }
    }

    class ShortCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Short) return value;
            else if (value instanceof String s) return Short.parseShort(s);
            else if (value instanceof Long v) return v.shortValue();
            else if (value instanceof Integer v) return v.shortValue();
            else if (value instanceof Double v) return v.shortValue();
            else if (value instanceof Float v) return v.shortValue();
            else throw new CastException(value, type);
        }
    }

    class DoubleCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Double) return value;
            else if (value instanceof String s) return Double.parseDouble(s);
            else if (value instanceof Long v) return v.doubleValue();
            else if (value instanceof Integer v) return v.doubleValue();
            else if (value instanceof Short v) return v.doubleValue();
            else if (value instanceof Float v) return v.doubleValue();
            else throw new CastException(value, type);
        }
    }

    class FloatCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Float) return value;
            else if (value instanceof String s) return Float.parseFloat(s);
            else if (value instanceof Long v) return v.floatValue();
            else if (value instanceof Integer v) return v.floatValue();
            else if (value instanceof Short v) return v.floatValue();
            else if (value instanceof Double v) return v.floatValue();
            else throw new CastException(value, type);
        }
    }

    class BooleanCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Boolean) return value;
            else if (value instanceof String s) return Boolean.parseBoolean(s);
            else throw new CastException(value, type);
        }
    }

    class ByteCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Byte) return value;
            else if (value instanceof String s) return Byte.parseByte(s);
            else if (value instanceof Long v) return v.byteValue();
            else if (value instanceof Integer v) return v.byteValue();
            else if (value instanceof Short v) return v.byteValue();
            else if (value instanceof Double v) return v.byteValue();
            else if (value instanceof Float v) return v.byteValue();
            else throw new CastException(value, type);
        }
    }

    class CharCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Character) return value;
            else if (value instanceof String s) return s.charAt(0);
            else throw new CastException(value, type);
        }
    }

    class ComponentCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Component) return value;
            else if (value instanceof String s) return Component.text(s);
            else throw new CastException(value, type);
        }
    }

    class BossBarColorCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof BossBar.Color) return value;
            else if (value instanceof String s) return BossBar.Color.valueOf(s);
            else throw new CastException(value, type);
        }
    }

    class BossBarOverlayCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof BossBar.Overlay) return value;
            else if (value instanceof String s) return BossBar.Overlay.valueOf(s);
            else throw new CastException(value, type);
        }
    }

    class PlayerCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Player) return value;
            else if (value instanceof String s) return Bukkit.getPlayer(s);
            else if (value instanceof UUID u) return Bukkit.getPlayer(u);
            else throw new CastException(value, type);
        }
    }

    class OfflinePlayerCastManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof OfflinePlayer) return value;
            else if (value instanceof String s) return Bukkit.getOfflinePlayer(s);
            else if (value instanceof UUID u) return Bukkit.getOfflinePlayer(u);
            else throw new CastException(value, type);
        }
    }

    class PermissibleManager implements CastManager {
        @Override
        public Object cast(Object value, Class<?> type) throws CastException {
            if (value instanceof Permissible) return value;
            else throw new CastException(value, type);
        }
    }
}
