# buy2sell

`buy2sell` is a JDK 11 + Maven multi-module Java backend starter designed for Spec-Driven AI Coding with Claude Code and GitHub Spec Kit.

The first phase intentionally avoids external infrastructure dependencies such as MySQL, Redis, RocketMQ, Apollo, Spring Boot, HTTP frameworks, ORM frameworks, and RPC frameworks. Its purpose is to establish a maintainable AI coding harness: clear specifications, module boundaries, tests, architecture checks, ADRs, and repeatable verification commands.

## Goals

- Use GitHub Spec Kit as the requirement and task workflow.
- Use Claude Code as the coding executor.
- Keep Java backend architecture explicit and testable.
- Make every AI-generated change verifiable through Maven tests and architecture rules.
- Keep business logic separate from infrastructure details.

## Modules

```text
buy2sell-domain
  Core domain model, value objects, domain exceptions, and business rules.

buy2sell-application
  Application use cases, command objects, query DTOs, and repository ports.

buy2sell-infrastructure
  Technical implementations of application ports. Phase one only contains in-memory implementations.

buy2sell-adapter
  External-facing adapter/facade layer. Phase one does not introduce HTTP.

buy2sell-bootstrap
  Manual application wiring and simple runtime entry point. No DI framework is used in phase one.

buy2sell-architecture-test
  ArchUnit rules for validating module and package boundaries.
```

## Dependency Direction

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

- `domain` must not depend on `application`, `infrastructure`, `adapter`, or `bootstrap`.
- `application` may depend on `domain`, but not on infrastructure or adapter.
- `infrastructure` may depend on `domain` and `application`.
- `adapter` may depend on `application`.
- `bootstrap` wires the application together.
- Cyclic dependencies are forbidden.

## Build

```bash
./mvnw clean verify
```

## AI Coding Workflow

Every feature should follow:

```text
/speckit.specify
/speckit.clarify
/speckit.checklist
/speckit.plan
/speckit.tasks
/speckit.analyze
/speckit.implement
```

Before implementation, Claude Code must read:

- `memory/constitution.md`
- `CLAUDE.md`
- active `specs/{feature}/spec.md`
- active `specs/{feature}/plan.md`
- active `specs/{feature}/tasks.md`

## Example Business Capability

The starter includes a minimal Task capability:

- Create task
- Rename task
- Complete task
- Query task detail

The implementation uses an in-memory repository and exists only to validate the architecture and test workflow.
