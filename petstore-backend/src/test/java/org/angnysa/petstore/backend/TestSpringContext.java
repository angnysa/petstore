package org.angnysa.petstore.backend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import jakarta.servlet.Filter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

import static org.testcontainers.containers.PostgreSQLContainer.DEFAULT_TAG;
import static org.testcontainers.containers.PostgreSQLContainer.IMAGE;

@SpringBootTest
@Testcontainers
public class TestSpringContext {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private EntityManager entityManager;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>(DockerImageName.parse(IMAGE).withTag(DEFAULT_TAG));

    @Test
    public void dumpApplicationContext() {
        for (String name : context.getBeanDefinitionNames()) {
            Class<?> beanClass = context.getBean(name).getClass();

            if (beanClass.getPackageName().startsWith("org.angnysa.petstore.backend")) {
                System.out.printf("Bean %s class %s%n", name, beanClass.getName());
            }
        }

        System.out.println();

        for (RequestMappingHandlerMapping bean : context.getBeansOfType(RequestMappingHandlerMapping.class).values()) {
            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : bean.getHandlerMethods().entrySet()) {
                System.out.printf("Route %s -> %s%n", entry.getKey(), entry.getValue());
            }
        }

        System.out.println();

        for (Filter bean : context.getBeansOfType(Filter.class).values()) {
            System.out.printf("Filter %s%n", bean.getClass().getName());
        }

        System.out.println();

        for (EntityType<?> entity : entityManager.getMetamodel().getEntities()) {
            System.out.printf("Entity %s class %s%n", entity.getName(),entity.getJavaType().getName());
        }
    }
}
