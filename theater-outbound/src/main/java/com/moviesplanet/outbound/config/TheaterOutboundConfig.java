package com.moviesplanet.outbound.config;

import com.moviesplanet.common.config.MongoDBConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.moviesplanet.theater.repo")
public class TheaterOutboundConfig extends MongoDBConfig {
}
