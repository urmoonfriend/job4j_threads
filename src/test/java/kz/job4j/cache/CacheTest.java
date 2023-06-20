package kz.job4j.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CacheTest {
    @Test
    public void whenUpdateThenOk() throws Exception {
        final Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        base.setName("Aidos");
        boolean res =  cache.update(base);
        Base updatedBase = cache.get(1);
        Assertions.assertThat(res).isEqualTo(true);
        Assertions.assertThat(base.getId()).isEqualTo(updatedBase.getId());
        Assertions.assertThat(base.getVersion()+1).isEqualTo(updatedBase.getVersion());
        Assertions.assertThat(base.getName()).isEqualTo(updatedBase.getName());
    }

    @Test
    public void whenUpdateThenError() throws Exception {
        final Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        Base base2 = new Base(2, 2);
        base2.setName("Aidos");
        boolean res = cache.update(base2);
        Assertions.assertThat(res).isEqualTo(false);
    }

    @Test
    public void whenDeleteThenOk() throws Exception {
        final Cache cache = new Cache();
        Base base = new Base(1, 1);
        Base base2 = new Base(2, 2);
        cache.add(base);
        cache.add(base2);
        cache.delete(base);
        Assertions.assertThat(cache.get(1)).isEqualTo(null);
        Assertions.assertThat(cache.get(2)).isEqualTo(base2);
        Assertions.assertThat(cache.getAll().size()).isEqualTo(1);
    }
}
