# Feature Specification: Architecture Governance Tests

## Summary

Strengthen the architecture test suite so future AI-assisted changes are checked against module boundaries, technology isolation, package naming conventions, and Maven dependency governance.

## Goals

- Prevent domain code from depending on technical frameworks or middleware APIs.
- Prevent application code from depending on concrete infrastructure, inbound adapter, or middleware APIs.
- Keep technical model naming in the correct module.
- Detect unmanaged external dependency versions in child module POM files.
- Keep the architecture rules executable through Maven tests.

## Non-Goals

- Do not introduce new production dependencies.
- Do not change runtime wiring or business behavior.
- Do not enforce code coverage thresholds.

## User Scenarios

### Scenario 1: AI Adds Infrastructure API to Domain

- Given a future change imports Spring, Redis, JDBC, ORM, HTTP, MQ, Apollo, or JSON framework APIs in `buy2sell-domain`
- When `./mvnw clean verify` runs
- Then architecture tests fail with a clear boundary violation

### Scenario 2: AI Adds Unmanaged External Dependency Version

- Given a future change adds an external dependency version directly in a child module POM
- When architecture tests run
- Then Maven dependency governance tests fail

## Functional Requirements

- FR-001: Domain classes must not depend on framework, persistence, cache, configuration center, MQ, HTTP, RPC, JDBC, or JSON framework packages.
- FR-002: Application classes must not depend on infrastructure, adapter, framework, persistence, cache, configuration center, MQ, HTTP, RPC, JDBC, or JSON framework packages.
- FR-003: DAO, Mapper, and PO naming must be limited to `buy2sell-infrastructure`.
- FR-004: Request, Response, Controller, and Facade naming must be limited to `buy2sell-adapter`, except bootstrap wiring may call adapter facades.
- FR-005: Child module POM files must not declare versions for external dependencies.
- FR-006: Project module dependencies in child module POM files may use `${project.version}`.

## Business Rules

- BR-001: Domain logic must remain free of technical integration details.
- BR-002: Application code may define ports but must not depend on concrete middleware or adapter APIs.
- BR-003: External dependency versions are governed from the root POM.

## Data Concepts

- Architecture rule: an executable test that validates module, package, technology, or build governance.
- Child module POM: a `pom.xml` under a project module directory, excluding the root `pom.xml`.

## Acceptance Criteria

- AC-001: New architecture tests are added for technology isolation.
- AC-002: New architecture tests are added for package naming conventions.
- AC-003: New architecture tests are added for Maven dependency governance.
- AC-004: `./mvnw -pl buy2sell-architecture-test -am test` passes.
- AC-005: `./mvnw clean verify` passes.

## Edge Cases and Failure Paths

- Internal project module dependencies may keep `${project.version}` in child modules.
- Empty common module packages should not make architecture tests fail.
- Future adapters may use HTTP/RPC types, but those types must stay outside domain and application.

## Open Questions

- [x] Should this governance decision have an ADR? Yes, because architecture test scope is a project-level governance decision.

## AI Readiness Checklist

- [x] Requirement source is clear.
- [x] User scenarios are listed.
- [x] Functional requirements are testable.
- [x] Acceptance criteria are observable.
- [x] Business rules are explicit.
- [x] Open questions are resolved or intentionally deferred.

