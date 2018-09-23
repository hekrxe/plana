package com.plana.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * User: tanhuayou
 * Date: 2018/9/5
 */
@Configuration
@EnableRedisRepositories
public class AppConfiguration {


    @Bean("numberRedisTemplate")
    public RedisTemplate<String, Long> numberRedisTemplate(RedisConnectionFactory factory) {


        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(0);
        configuration.setHostName("127.0.0.1");
        configuration.setPort(6379);

        LettuceConnectionFactory factory1 = new LettuceConnectionFactory(configuration);
        factory1.afterPropertiesSet();


        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(factory1);

        template.setKeySerializer(keyStringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<>(Long.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(Long.class));

        template.afterPropertiesSet();

        return template;
    }


    @Bean("stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {


        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(0);
        configuration.setHostName("127.0.0.1");
        configuration.setPort(6379);

        LettuceConnectionFactory factory1 = new LettuceConnectionFactory(configuration);
        factory1.afterPropertiesSet();


        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory1);

        template.setKeySerializer(keyStringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();

        return template;
    }

    public RedisSerializer<String> keyStringRedisSerializer() {
        return new RedisSerializer<String>() {
            @Override
            public byte[] serialize(String key) throws SerializationException {
                Assert.hasText(key, "Null for key of serialize");
                return key.getBytes(Charset.forName("UTF-8"));
            }

            @Override
            public String deserialize(byte[] bytes) throws SerializationException {
                return bytes == null ? null : new String(bytes, Charset.forName("UTF-8"));
            }
        };
    }


    // for test start
    private static final AppConfiguration APP_CONFIGURATION = new AppConfiguration();
    private static final RedisTemplate<String, Long> REDIS_TEMPLATE = APP_CONFIGURATION.numberRedisTemplate(null);
    private static final StringRedisTemplate STRING_REDIS_TEMPLATE = APP_CONFIGURATION.stringRedisTemplate(null);

    public static RedisTemplate<String, Long> getNumberTemplate() {
        return REDIS_TEMPLATE;
    }

    public static StringRedisTemplate getStringRedisTemplate() {
        return STRING_REDIS_TEMPLATE;
    }
    // for test end

}
