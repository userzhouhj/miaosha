package com.jun.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();


        template.setKeySerializer(new StringRedisSerializer());
        ////设置value的序列化器  默认值是JdkSerializationRedisSerializer
        //使用Jackson序列化器的问题是，复杂对象可能序列化失败，比如JodaTime的DateTime类型
        //使用Jackson2，将对象序列化为JSON
        ObjectMapper om = new ObjectMapper();
        Jackson2JsonRedisSerializer<Object> jackson = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson.setObjectMapper(om);
        template.setValueSerializer(jackson);
        template.setHashValueSerializer(jackson);

        //将redis连接工厂设置到模板类中
        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }
}
