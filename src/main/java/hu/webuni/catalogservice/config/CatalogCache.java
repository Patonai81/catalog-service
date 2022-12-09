package hu.webuni.catalogservice.config;

import lombok.RequiredArgsConstructor;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionType;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@EnableCaching
@Configuration
public class CatalogCache {

/*
    private final EmbeddedCacheManager cacheManager;

    @Bean
    public InfinispanCacheConfigurer cacheConfigurer() {
        return manager -> {
            final org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering()
                    .cacheMode(CacheMode.LOCAL)
                    .build();

            manager.defineConfiguration("local-sync-config", ispnConfig);
        };
    }

    @Bean(name = "historyCache")
    public org.infinispan.configuration.cache.Configuration smallCache() {

        return new ConfigurationBuilder()
                .read(cacheManager.getCacheConfiguration("local-sync-config"))
                .memory().size(1000L)
                .memory().evictionType(EvictionType.COUNT)
                .build();
    }

*/
}

