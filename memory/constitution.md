# buy2sell Constitution

## 1. Spec-Driven Development

All functionality must start with a specification. Implementation is only allowed after the feature has:

- `spec.md`
- `plan.md`
- `tasks.md`

Claude Code must not implement directly from a vague request. If the user request lacks a spec, create one with `/speckit.specify` first and use `specs/_template/` as the starting structure.

Before implementing a feature, Claude Code must also read the long-term memory indexes:

- `specs/README.md`
- `docs/product/feature-map.md`
- `docs/domain/glossary.md`
- `docs/domain/invariants.md`

## 2. Java and Build Constraints

- The project uses JDK 11.
- The project uses Maven multi-module build.
- Maven Wrapper must be used for all build commands.
- Dependency versions must be controlled by the root `pom.xml` through `dependencyManagement`.
- Child modules must not introduce unmanaged dependency versions.
- Java 17+ language features and APIs are forbidden.

## 3. Module Boundaries

- `buy2sell-common` contains common utilities, error codes, and base types. It must not depend on any other project module.
- `buy2sell-domain` is the core domain module. It may depend on `buy2sell-common`.
- `buy2sell-application` may depend on `buy2sell-common` and `buy2sell-domain`.
- `buy2sell-infrastructure` may depend on `buy2sell-common`, `buy2sell-domain`, and `buy2sell-application`.
- `buy2sell-adapter` may depend on `buy2sell-common` and `buy2sell-application`.
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
- long-term memory updates, or why no update was needed
- remaining risks

## 9. Long-Term Memory Maintenance

Long-term memory files preserve stable cross-feature knowledge. They are indexes and summaries, not replacements for feature specs.

- `specs/README.md` must be updated when a feature is added, changed, implemented, deprecated, or removed from active planning.
- `docs/product/feature-map.md` must describe stable product capabilities and feature relationships without implementation detail.
- `docs/domain/glossary.md` must define stable business terms and merge synonyms under one primary term.
- `docs/domain/invariants.md` must record durable business rules that future changes must preserve.
- Feature-specific details, edge cases, acceptance criteria, and implementation tasks must remain in `specs/{feature}/`.
