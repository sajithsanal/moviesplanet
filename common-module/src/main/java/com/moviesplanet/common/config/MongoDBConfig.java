package com.moviesplanet.common.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("DB_NAME");
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(env.getProperty("CONNECTION_STRING"));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

}
