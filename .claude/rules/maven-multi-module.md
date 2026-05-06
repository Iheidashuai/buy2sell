# Maven Multi-Module Rules

- Add new modules to the root `pom.xml`.
- Manage dependency versions in the root parent only.
- Do not add unmanaged versions in child modules.
- Use `./mvnw -pl <module> -am test` for module-level verification.
- Use `./mvnw clean verify` for cross-module changes.
