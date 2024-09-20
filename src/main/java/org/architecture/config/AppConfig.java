package org.architecture.config;

import org.architecture.Clients.collection.ClientRepositoryCollection;
import org.architecture.Clients.Interface.ClientRepository;
import org.architecture.entities.ClientEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Set;
import java.util.HashSet;

@Configuration
@ComponentScan(basePackages ="org.architecture")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("application.properties")); // Spécifiez le fichier
        return configurer;
    }

     @Bean
     public ClientRepository clientRepoCollection() {
      Set<ClientEntity> clients = new HashSet<>();
      return new ClientRepositoryCollection(clients);
     }
}
