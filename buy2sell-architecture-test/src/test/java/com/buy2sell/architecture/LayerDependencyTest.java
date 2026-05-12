package com.buy2sell.architecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class LayerDependencyTest {

    @Test
    void common_should_not_depend_on_other_project_modules() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("com.buy2sell.common..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(
                        "com.buy2sell.domain..",
                        "com.buy2sell.application..",
                        "com.buy2sell.infrastructure..",
                        "com.buy2sell.adapter..",
                        "com.buy2sell.bootstrap.."
                );

        rule.allowEmptyShould(true).check(new ClassFileImporter().importPackages("com.buy2sell"));
    }

    @Test
    void domain_should_not_depend_on_other_layers() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("com.buy2sell.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(
                        "com.buy2sell.application..",
                        "com.buy2sell.infrastructure..",
                        "com.buy2sell.adapter..",
                        "com.buy2sell.bootstrap.."
                );

        rule.check(new ClassFileImporter().importPackages("com.buy2sell"));
    }

    @Test
    void application_should_not_depend_on_infrastructure_adapter_or_bootstrap() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("com.buy2sell.application..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(
                        "com.buy2sell.infrastructure..",
                        "com.buy2sell.adapter..",
                        "com.buy2sell.bootstrap.."
                );

        rule.check(new ClassFileImporter().importPackages("com.buy2sell"));
    }

    @Test
    void infrastructure_should_not_depend_on_adapter_or_bootstrap() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("com.buy2sell.infrastructure..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(
                        "com.buy2sell.adapter..",
                        "com.buy2sell.bootstrap.."
                );

        rule.check(new ClassFileImporter().importPackages("com.buy2sell"));
    }

    @Test
    void adapter_should_not_depend_on_infrastructure_or_bootstrap() {
        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("com.buy2sell.adapter..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(
                        "com.buy2sell.infrastructure..",
                        "com.buy2sell.bootstrap.."
                );

        rule.check(new ClassFileImporter().importPackages("com.buy2sell"));
    }
}
