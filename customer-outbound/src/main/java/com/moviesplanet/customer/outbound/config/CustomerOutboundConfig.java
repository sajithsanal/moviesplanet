package com.moviesplanet.customer.outbound.config;

import com.moviesplanet.common.config.MongoDBConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.moviesplanet.customer.common.repo")
public class CustomerOutboundConfig extends MongoDBConfig {
}
