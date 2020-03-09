package com.zhihui.user.config.dynamic.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 分库策略
 *
 * @author LDZ
 * @date 2020-03-06 18:25
 */
@Component
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2 + "")) {
                System.out.println("database" + preciseShardingValue.getValue() + " ==" + each);
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}
