# ADR 0001: Layered Maven Multi-Module Architecture

## Status

Accepted

## Context

`buy2sell` is designed for long-term AI-assisted backend development. The project needs explicit structure so Claude Code can safely add and modify logic without mixing domain rules, application orchestration, and technical implementation details.

## Decision

Use a Maven multi-module architecture:

- `buy2sell-shared-kernel`
- `buy2sell-domain`
- `buy2sell-application`
- `buy2sell-infrastructure`
- `buy2sell-adapter`
- `buy2sell-bootstrap`
- `buy2sell-architecture-test`

`buy2sell-shared-kernel` contains shared model classes and framework-free utilities that are stable across modules. It must not depend on other project modules or infrastructure/framework APIs.

## Consequences

Benefits:

- Clear dependency direction.
- Better AI context boundaries.
- Architecture drift can be detected through ArchUnit tests.
- Infrastructure dependencies can be introduced later without polluting the domain model.

Trade-offs:

- More modules and more upfront structure.
- Small features require slightly more files.
