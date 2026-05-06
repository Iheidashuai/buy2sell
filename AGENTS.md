# AGENTS.md

## Project Overview

This is `buy2sell`, a JDK 11 Maven multi-module Java backend project designed for Spec-Driven AI Coding.

The project intentionally starts without external infrastructure dependencies such as MySQL, Redis, RocketMQ, Apollo, Spring Boot, HTTP frameworks, ORM frameworks, or RPC frameworks. The first phase focuses on architecture, module boundaries, testability, and maintainable AI coding workflow.

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
buy2sell-domain
  ↑
buy2sell-application
  ↑
buy2sell-infrastructure / buy2sell-adapter
  ↑
buy2sell-bootstrap
```

Rules:

- `buy2sell-domain` must not depend on any other project module.
- `buy2sell-application` may depend on `buy2sell-domain`.
- `buy2sell-infrastructure` may depend on `buy2sell-domain` and `buy2sell-application`.
- `buy2sell-adapter` may depend on `buy2sell-application`.
- `buy2sell-bootstrap` may depend on adapter, infrastructure, application, and domain modules.
- No circular dependency is allowed.

## Coding Rules

- Keep domain logic free from technical details.
- Prefer constructor injection over field injection if a DI framework is introduced later.
- Do not introduce external dependencies unless the current plan explicitly allows it.
- Do not add dependencies directly in child modules without dependencyManagement.
- Prefer immutable value objects where practical.
- Keep methods small and intention-revealing.
- Do not weaken or delete tests to make the build pass.

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
