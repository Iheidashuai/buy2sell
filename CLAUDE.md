# CLAUDE.md

## Project Overview

This is `buy2sell`, a JDK 11 Maven multi-module Java backend project designed for Spec-Driven AI Coding.

The project focuses on architecture, module boundaries, testability, and maintainable AI coding workflow. External infrastructure dependencies such as MySQL, Redis, RocketMQ, Apollo, Spring Boot, HTTP frameworks, ORM frameworks, or RPC frameworks are allowed when the active feature plan introduces them explicitly.

## Required Workflow

Before implementing any feature:

1. Read `memory/constitution.md`.
2. Read the active feature files under `specs/{feature}/`:
   - `spec.md`
   - `plan.md`
   - `tasks.md`
   - `quickstart.md` if present
   - `research.md` if present
3. Do not implement directly from a vague user request.
4. If the request has no spec, create one using `/speckit.specify`.
5. If the spec is ambiguous, use `/speckit.clarify`.
6. If there is no implementation plan, use `/speckit.plan`.
7. If there are no implementation tasks, use `/speckit.tasks`.
8. Before implementation, run `/speckit.analyze` when available.

## Java Version

- Use JDK 11 only.
- Do not use Java 17+ APIs or language features.
- Do not use records, sealed classes, pattern matching for switch, or other unsupported features.

## Build Commands

Use Maven Wrapper only.

```bash
./mvnw -v
./mvnw test
./mvnw clean verify
./mvnw -pl buy2sell-domain test
./mvnw -pl buy2sell-application -am test
```

## Module Rules

Allowed dependency direction:

```text
buy2sell-shared-kernel
  ↑
buy2sell-domain
  ↑
buy2sell-application
  ↑
buy2sell-infrastructure / buy2sell-adapter
  ↑
buy2sell-bootstrap
```

Rules:

- `buy2sell-shared-kernel` must not depend on any other project module.
- `buy2sell-domain` may depend on `buy2sell-shared-kernel`.
- `buy2sell-application` may depend on `buy2sell-shared-kernel` and `buy2sell-domain`.
- `buy2sell-infrastructure` may depend on `buy2sell-shared-kernel`, `buy2sell-domain`, and `buy2sell-application`.
- `buy2sell-adapter` may depend on `buy2sell-shared-kernel` and `buy2sell-application`.
- `buy2sell-bootstrap` may depend on shared-kernel, adapter, infrastructure, application, and domain modules.
- No circular dependency is allowed.

## Coding Rules

- Keep domain logic free from technical details.
- Prefer constructor injection over field injection if a DI framework is introduced later.
- Do not introduce external dependencies unless the current plan explicitly allows it.
- Do not add dependencies directly in child modules without dependencyManagement.
- Prefer immutable value objects where practical.
- Keep methods small and intention-revealing.
- Do not weaken or delete tests to make the build pass.

## Infrastructure Integration Rules

- Common model classes and framework-free utility classes belong in `buy2sell-shared-kernel`.
- Feature-specific domain models with business rules belong in `buy2sell-domain`.
- Application commands, queries, views, results, and ports belong in `buy2sell-application`.
- HTTP/RPC request and response DTOs belong in `buy2sell-adapter`.
- External middleware clients and technical integrations belong in `buy2sell-infrastructure`.
- `buy2sell-application` may define ports/interfaces for infrastructure capabilities, but should not depend on concrete middleware APIs.
- `buy2sell-domain` must not import or reference middleware, framework, persistence, cache, RPC, HTTP, or configuration-center APIs.
- `buy2sell-adapter` owns inbound protocols such as HTTP controllers, RPC providers, facades, request DTOs, and response DTOs.
- `buy2sell-bootstrap` owns runtime wiring, client initialization, framework bootstrapping, and application configuration assembly.
- DAO, database entity, ORM mapper, Redis implementation, Apollo implementation, MQ client, and outbound RPC client code should live in `buy2sell-infrastructure`.
- Application code should access Apollo-backed configuration, Redis, DAO, MQ, and outbound RPC through application-level ports instead of concrete client APIs.
- Follow `docs/architecture/model-placement.md` when deciding where a model, DTO, PO, or utility belongs.

## Instruction Maintenance Rules

- Long-term coding and module-placement instructions belong in `AGENTS.md` and `CLAUDE.md`.
- Project-wide principles belong in `memory/constitution.md`.
- Important architecture decisions and technology choices belong in `docs/adr`.
- Concrete feature requirements belong in `specs/{feature}/spec.md`, `plan.md`, and `tasks.md`.

## Testing Rules

- Use JUnit 5.
- Use AssertJ for assertions.
- Use Mockito only when mocking is genuinely needed.
- Business logic must be unit tested.
- Bug fixes require regression tests.
- Cross-module changes require `./mvnw clean verify`.
- Do not skip, delete, weaken, or ignore tests to pass the build.

## Completion Criteria

A task is complete only if:

1. The implementation matches the active spec.
2. Relevant tests are added or updated.
3. Maven tests pass.
4. `tasks.md` is updated.
5. Any changed architecture decision is documented in `docs/adr`.
6. The final response includes:
   - files changed
   - tests run
   - remaining risks
   - whether spec/plan/tasks/ADR were updated
