package com.github.codingpokemon.kyaru.bot

import net.mamoe.mirai.Bot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.Assert


private fun createBot(qqid: Long, password: String): Bot = Bot(qqid, password)

@Configuration
open class BotConfiguration(private val properties: BotProperties) {
    @Bean
    open fun bot(): Bot {
        Assert.state(properties.bots.size > 0, "Bot definition not found. Check your properties.")
        val (qqid, password) = properties.bots[0]
        return createBot(qqid, password)
    }
}