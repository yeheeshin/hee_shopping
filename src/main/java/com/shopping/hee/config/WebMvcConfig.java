package com.shopping.hee.config;

import com.shopping.hee.utils.MyPath;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        //외부에 있는 폴더를 내 프로젝트에서 찾을 수 있도록 세팅
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + MyPath.imgPath)
                .setCachePeriod(60 * 10 * 6)// 서버 부하를 줄이기 위해 해당요청을 캐시에 저장해놓는 시간을 지정
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
