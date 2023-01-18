package com.example.miniwikibackend.config;

import com.example.miniwikibackend.Entities.Post;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Blocking HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT.
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private String theAlleredOrigins = "http://localhost:3000/";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unSupportedAction = {HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT};
        config.exposeIdsFor(Post.class);
        disableHttptMethod(Post.class, config, unSupportedAction);


        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAlleredOrigins);
    }

    private void disableHttptMethod(Class postClass, RepositoryRestConfiguration config, HttpMethod[] unSupportedAction) {
        config.getExposureConfiguration().forDomainType(postClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(unSupportedAction))
                .withCollectionExposure(((metdata, httpMethods) ->
                        httpMethods.disable(unSupportedAction)));
    }
}
