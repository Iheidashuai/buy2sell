package com.buy2sell.architecture;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MavenDependencyGovernanceTest {

    private static final String PROJECT_GROUP_ID = "com.buy2sell";
    private static final String PROJECT_VERSION = "${project.version}";

    @Test
    void child_module_external_dependencies_should_not_declare_versions() throws Exception {
        List<String> violations = new ArrayList<>();

        for (Path pom : childModulePoms()) {
            Document document = parse(pom);
            NodeList dependencies = document.getElementsByTagName("dependency");
            for (int i = 0; i < dependencies.getLength(); i++) {
                Element dependency = (Element) dependencies.item(i);
                String groupId = childText(dependency, "groupId");
                String artifactId = childText(dependency, "artifactId");
                String version = childText(dependency, "version");

                if (version == null || version.isEmpty()) {
                    continue;
                }
                if (PROJECT_GROUP_ID.equals(groupId) && PROJECT_VERSION.equals(version)) {
                    continue;
                }

                violations.add(pom + " declares dependency version for " + groupId + ":" + artifactId);
            }
        }

        assertTrue(violations.isEmpty(), "Unexpected dependency versions: " + violations);
    }

    private static List<Path> childModulePoms() throws Exception {
        Path root = Paths.get("").toAbsolutePath().getParent();
        try (Stream<Path> paths = Files.list(root)) {
            return paths
                    .filter(Files::isDirectory)
                    .map(path -> path.resolve("pom.xml"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
    }

    private static Document parse(Path pom) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        Document document = factory.newDocumentBuilder().parse(pom.toFile());
        document.getDocumentElement().normalize();
        return document;
    }

    private static String childText(Element element, String name) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element && name.equals(node.getNodeName())) {
                return node.getTextContent().trim();
            }
        }
        return null;
    }
}
