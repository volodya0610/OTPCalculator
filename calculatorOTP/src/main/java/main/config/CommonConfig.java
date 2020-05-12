package main.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonConfig implements WebMvcConfigurer {

    //Настройка Jackson Object Mapper
    @Autowired
    public void configureJackson(ObjectMapper jacksonMapper) {
        jacksonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jacksonMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.DEFAULT);
        jacksonMapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.DEFAULT);
        jacksonMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
        jacksonMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}


