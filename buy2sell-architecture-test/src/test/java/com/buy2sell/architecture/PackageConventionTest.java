package com.buy2sell.architecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class PackageConventionTest {

    @Test
    void persistence_named_classes_should_stay_in_infrastructure() {
        ArchRule rule = noClasses()
                .that()
                .resideOutsideOfPackage("com.buy2sell.infrastructure..")
                .should()
                .haveSimpleNameEndingWith("Dao")
                .orShould()
                .haveSimpleNameEndingWith("DAO")
                .orShould()
                .haveSimpleNameEndingWith("Mapper")
                .orShould()
                .haveSimpleNameEndingWith("Po")
                .orShould()
                .haveSimpleNameEndingWith("PO");

        rule.allowEmptyShould(true).check(new ClassFileImporter().importPackages("com.buy2sell"));
    }

    @Test
    void inbound_adapter_named_classes_should_stay_in_adapter_or_bootstrap() {
        ArchRule rule = noClasses()
                .that()
                .resideOutsideOfPackages(
                        "com.buy2sell.adapter..",
                        "com.buy2sell.bootstrap.."
                )
                .should()
                .haveSimpleNameEndingWith("Request")
                .orShould()
                .haveSimpleNameEndingWith("Response")
                .orShould()
                .haveSimpleNameEndingWith("Controller")
                .orShould()
                .haveSimpleNameEndingWith("Facade");

        rule.allowEmptyShould(true).check(new ClassFileImporter().importPackages("com.buy2sell"));
    }
}

