package com.zhihui.user.config.dynamic.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 分表策略
 *
 * @author LDZ
 * @date 2020-03-06 18:26
 */
@Component
@Slf4j
public class TableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> tables, PreciseShardingValue<Long> preciseShardingValue) {
        for (String table : tables) {
            if (table.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 8 + "")) {
                log.info("The Value {} is in the database {}", preciseShardingValue.getValue(), table);
                return table;
            }
        }
        throw new IllegalArgumentException();
    }
}
