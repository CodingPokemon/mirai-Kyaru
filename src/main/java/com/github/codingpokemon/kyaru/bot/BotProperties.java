package com.github.codingpokemon.kyaru.bot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SuppressWarnings("ConfigurationProperties")
@Configuration
@ConfigurationProperties
public class BotProperties {
    private List<BotDefinition> bots;

    public List<BotDefinition> getBots() {
        return bots;
    }

    public void setBots(List<BotDefinition> bots) {
        this.bots = bots;
    }

    @Data
    public static class BotDefinition {
        Long qqid;
        String password;

        public Long component1() {
            return qqid;
        }

        public String component2() {
            return password;
        }
    }
}
