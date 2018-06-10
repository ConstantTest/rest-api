package com.oshen.controller;

import com.oshen.controller.resources.ProductResource;
import com.oshen.dao.model.Product;
import com.oshen.dao.repository.ProductDao;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        final ProductDao dao = new ProductDao(hibernateBundle.getSessionFactory());
//        final HelloWorldResource helloWorldResource =
//                new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());


//        environment.jersey().register(helloWorldResource);
        environment.jersey().register(ProductResource.class);
    }
}
