package com.oshen.controller;

import com.oshen.controller.resources.ProductResource;
import com.oshen.dao.model.Product;
import com.oshen.dao.repository.ProductDao;
import com.oshen.service.ProductService;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class RestApplication extends Application<RestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RestApplication().run(args);
    }

    private final HibernateBundle<RestConfiguration> hibernateBundle =
            new HibernateBundle<RestConfiguration>(Product.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(RestConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public void initialize(final Bootstrap<RestConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new MigrationsBundle<RestConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(RestConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final RestConfiguration configuration,
                    final Environment environment) {

        // init Spring context
        //before we init the app context, we have to create a parent context with all the config objects others rely
        // on to get initialized
        AnnotationConfigWebApplicationContext parentContext = new AnnotationConfigWebApplicationContext();
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        parentContext.refresh();
        parentContext.getBeanFactory().registerSingleton("configuration", configuration);
        parentContext.registerShutdownHook();
        parentContext.start();

        // the real main app context has a link to the parent context
        context.setParent(parentContext);
        context.register(RestSpringConfiguration.class);
        context.refresh();
        context.registerShutdownHook();
        context.start();

        ProductService productService = context.getBean("productService", ProductService.class);

        environment.jersey().register(productService);
    }
}
