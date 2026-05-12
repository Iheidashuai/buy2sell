package com.buy2sell.architecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class TechnologyIsolationTest {

    private static final String[] TECHNICAL_PACKAGES = {
            "org.springframework..",
            "javax.servlet..",
            "jakarta.servlet..",
            "javax.persistence..",
            "jakarta.persistence..",
            "org.hibernate..",
            "org.mybatis..",
            "redis.clients..",
            "org.springframework.data.redis..",
            "org.apache.rocketmq..",
            "com.ctrip.framework.apollo..",
            "java.sql..",
            "javax.sql..",
            "com.fasterxml.jackson..",
            "com.alibaba.fastjson.."
    };

    @Test
    void domain_should_not_depend_on_technical_packages() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("com.buy2sell.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(TECHNICAL_PACKAGES);

        rule.check(new ClassFileImporter().importPackages("com.buy2sell"));
    }

    @Test
    void application_should_not_depend_on_technical_packages() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("com.buy2sell.application..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(TECHNICAL_PACKAGES);

        rule.check(new ClassFileImporter().importPackages("com.buy2sell"));
    }
}

