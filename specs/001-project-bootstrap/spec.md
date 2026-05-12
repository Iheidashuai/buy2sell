# Feature Specification: Project Bootstrap

## Summary

Create the initial `buy2sell` Java backend project skeleton for long-term Spec-Driven AI Coding.

## Goals

- Establish a JDK 11 Maven multi-module project.
- Establish explicit module boundaries.
- Add unit test and architecture test foundations.
- Add AI workflow documents for Claude Code and GitHub Spec Kit.
- Include a minimal Task example to validate layering.

## Example Business Capability

Task management core model:

- Create a task.
- Rename a task.
- Complete a task.
- Query a task.

The implementation uses an in-memory repository only.

## Acceptance Criteria

- `./mvnw clean verify` passes under JDK 11.
- Maven Enforcer rejects non-JDK-11 builds.
- Domain, application, infrastructure, adapter, bootstrap, and architecture-test modules exist.
- Domain does not depend on any other project module.
- Application does not depend on infrastructure, adapter, or bootstrap.
- ArchUnit tests validate layer dependency rules.
- Task example includes domain and application tests.
- README explains project structure, build commands, and AI workflow.
- ADRs explain architecture and phase-one dependency restrictions.
