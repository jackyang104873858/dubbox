package io.maxfeng.dubbox.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

/**
 * @author MaXueFeng
 * @since 1.0
 */
public class CacheTest {

    @Test
    public void testCacheSimpleAPI() {
        Cache<Object, Object> cache = CacheBuilder.newBuilder().weakValues().build();

        ConcurrentMap<Object, Object> map = cache.asMap();

        map.put("k", "v");
    }

    @Test
    public void testGetVFromCache(){
        Cache<Object, Object> cache = CacheBuilder.newBuilder().weakValues().build();
        Object k = cache.getIfPresent("k");
        System.out.println(k);
    }
}
