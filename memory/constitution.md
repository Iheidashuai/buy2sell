# buy2sell Constitution

## 1. Spec-Driven Development

All functionality must start with a specification. Implementation is only allowed after the feature has:

- `spec.md`
- `plan.md`
- `tasks.md`

Claude Code must not implement directly from a vague request. If the user request lacks a spec, create one with `/speckit.specify` first.

## 2. Java and Build Constraints

- The project uses JDK 11.
- The project uses Maven multi-module build.
- Maven Wrapper must be used for all build commands.
- Dependency versions must be controlled by the root `pom.xml` through `dependencyManagement` and `pluginManagement`.
- Child modules must not introduce unmanaged dependency versions.
- Java 17+ language features and APIs are forbidden.

## 3. Module Boundaries

- `buy2sell-shared-kernel` contains shared model classes and framework-free utilities. It must not depend on any other project module.
- `buy2sell-domain` is the core domain module. It may depend on `buy2sell-shared-kernel`.
- `buy2sell-application` may depend on `buy2sell-shared-kernel` and `buy2sell-domain`.
- `buy2sell-infrastructure` may depend on `buy2sell-shared-kernel`, `buy2sell-domain`, and `buy2sell-application`.
- `buy2sell-adapter` may depend on `buy2sell-shared-kernel` and `buy2sell-application`.
- `buy2sell-bootstrap` wires modules together and may depend on all runtime modules.
- Cyclic dependencies are forbidden.
- External middleware, persistence, cache, configuration-center, RPC client, and framework integration code must stay outside `buy2sell-domain`.
- Database DTO/PO/entity/DAO/mapper classes belong in `buy2sell-infrastructure`.
- HTTP/RPC request and response DTOs belong in `buy2sell-adapter`.
- See `docs/architecture/model-placement.md` for model placement rules.

## 4. Coding Principles

- Keep business rules in domain or application layers.
- Keep technical details outside the domain layer.
- Prefer simple, explicit, testable code.
- Public methods must express clear intent.
- Exceptions must have explicit semantics.
- Do not add dependencies without updating the active plan.

## 5. Testing Principles

- Core business logic must have unit tests.
- New features must cover normal paths and at least one failure path.
- Bug fixes must include regression tests.
- Cross-module changes require `./mvnw clean verify`.
- Tests must not be deleted, skipped, weakened, or ignored just to pass the build.

## 6. Architecture Governance

- ArchUnit must validate package and layer boundaries.
- Architecture changes must update `docs/adr`.
- Architecture tests must not be weakened without explicit ADR justification.

## 7. External Infrastructure

External infrastructure and framework dependencies are allowed when needed by a feature, including but not limited to:

- Spring Boot
- HTTP frameworks
- MySQL
- Redis
- RocketMQ or other MQ clients
- Apollo or other config centers
- ORM frameworks
- RPC frameworks

These dependencies must be introduced through the active feature plan, managed by the root `pom.xml`, and kept outside `buy2sell-domain`.

## 8. AI Completion Rule

A Claude Code task is complete only when it reports:

- active spec path
- completed task IDs
- changed files grouped by module
- tests run
- architecture impact
- remaining risks
