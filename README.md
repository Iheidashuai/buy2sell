# buy2sell

`buy2sell` is a JDK 11 + Maven multi-module Java backend starter designed for Spec-Driven AI Coding with Claude Code and GitHub Spec Kit.

The project establishes a maintainable AI coding harness: clear specifications, module boundaries, tests, architecture checks, ADRs, and repeatable verification commands. External infrastructure dependencies such as databases, Redis, MQ, Apollo, HTTP frameworks, ORM frameworks, and RPC frameworks may be introduced when the active feature plan calls for them.

## Goals

- Use GitHub Spec Kit as the requirement and task workflow.
- Use Claude Code as the coding executor.
- Keep Java backend architecture explicit and testable.
- Make every AI-generated change verifiable through Maven tests and architecture rules.
- Keep business logic separate from infrastructure details.

## Modules

```text
buy2sell-common
  Common utilities, error codes, and base types used across modules.

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
buy2sell-common
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

- `common` must not depend on other project modules.
- `domain` may depend on `common`, but not on `application`, `infrastructure`, `adapter`, or `bootstrap`.
- `application` may depend on `common` and `domain`, but not on infrastructure or adapter.
- `infrastructure` may depend on `common`, `domain`, and `application`.
- `adapter` may depend on `common` and `application`.
- `bootstrap` wires the application together.
- Cyclic dependencies are forbidden.

## Model Placement

Use [docs/architecture/model-placement.md](docs/architecture/model-placement.md) when deciding where models, DTOs, PO/entity classes, and utility classes belong.

- Common utilities, error codes, and base types: `buy2sell-common`.
- Feature-specific domain models and business rules: `buy2sell-domain`.
- Commands, queries, views, results, and ports: `buy2sell-application`.
- HTTP/RPC request and response DTOs: `buy2sell-adapter`.
- Database DTO/PO/entity/DAO/mapper, Redis, Apollo, MQ, and outbound RPC implementation models: `buy2sell-infrastructure`.

## Build

```bash
./mvnw -v
./mvnw test
./mvnw clean verify
```

Use JDK 11 for local development and verification.

## Spec-Driven AI Workflow

For each feature, do not directly ask the AI to implement from a vague prompt. Use this sequence:

```text
/speckit.specify
/speckit.clarify
/speckit.checklist
/speckit.plan
/speckit.tasks
/speckit.analyze
/speckit.implement
```

Claude Code must read:

- `memory/constitution.md`
- `specs/README.md`
- `docs/product/feature-map.md`
- `docs/domain/glossary.md`
- `docs/domain/invariants.md`
- `CLAUDE.md`
- active feature files under `specs/{feature}/`

A task is complete only when:

1. Implementation matches the active spec.
2. Tests are added or updated.
3. `./mvnw clean verify` passes.
4. `tasks.md` is updated.
5. Architecture changes are recorded in `docs/adr`.
6. Long-term memory indexes are updated, or the final response explains why no update was needed.

## Current Example Capability

The starter includes a minimal in-memory Task capability:

- Create a task.
- Rename a task.
- Mark a task as completed.
- Query task details.

This is not intended to be a full product feature. It exists to validate layering, tests, and AI-maintainable structure.

## Push to GitHub

If the GitHub repository has not been created yet:

```bash
./scripts/push-to-github.sh Iheidashuai buy2sell
```

This script expects the GitHub CLI `gh` to be installed and authenticated. It creates the repo if missing, initializes Git, commits the project, and pushes to `main`.
