package com.zhihui.user.config.dynamic.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 分库策略
 *
 * @author LDZ
 * @date 2020-03-06 18:25
 */
@Component
@Slf4j
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> databases, PreciseShardingValue<Long> preciseShardingValue) {

        for (String database : databases) {
            if (database.endsWith((Long.parseLong(preciseShardingValue.getValue().toString()) / 8) % 8 + "")) {
                log.info("The Value {} is in the database {}", preciseShardingValue.getValue(), database);
                return database;
            }
        }
        throw new IllegalArgumentException();
    }
}
