package cn.chengzhimeow.cccondition.registry;

import cn.chengzhimeow.cccondition.manager.PreProcessManager;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class PreProcessRegistry extends Registry<Class<?>, List<PreProcessManager>> {
    @Override
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public Map<Class<?>, List<PreProcessManager>> defaultRegistry() {
        Map<Class<?>, List<PreProcessManager>> map = super.defaultRegistry();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            map.put(String.class, Arrays.asList(new PreProcessManager.PlaceholderAPIPreProcessManager()));
        }

        return map;
    }

    @Override
    public boolean replace() {
        return true;
    }

    public void register(Class<?> clazz, PreProcessManager preProcessManager) {
        List<PreProcessManager> list = super.getOrDefault(clazz, new ArrayList<>());
        list.add(preProcessManager);
        super.register(clazz, list);
    }

    public void unregister(Class<?> clazz, PreProcessManager preProcessManager) {
        List<PreProcessManager> list = super.getOrDefault(clazz, new ArrayList<>());
        list.remove(preProcessManager);

        if (list.isEmpty()) {
            super.unregister(clazz);
            return;
        }
        super.register(clazz, list);
    }
}
