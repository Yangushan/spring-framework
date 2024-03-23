package org.yangushan.transaction;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * created by yangushan
 * 2024/3/22 18:16
 */
@ComponentScan("org.yangushan.transaction")
@EnableTransactionManagement()
public class TransAppConfig {
}
