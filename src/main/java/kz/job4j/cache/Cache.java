package kz.job4j.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), check) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(int id) {
        return memory.get(id);
    }

    public List<Base> getAll() {
        return memory.values().stream().toList();
    }

    public BiFunction<Integer, Base, Base> check
            = (id, base) -> {
        Base stored = memory.get(id);
        if (stored.getVersion() != base.getVersion()) {
            throw new OptimisticException("Versions are not equal");
        }
        return stored;
    };
}
