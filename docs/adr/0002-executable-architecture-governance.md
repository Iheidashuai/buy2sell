# ADR 0002: Executable Architecture Governance

## Status

Accepted

## Context

`buy2sell` is intended for long-term AI-assisted development. Basic module dependency tests already protect high-level layer direction, but future feature work can still accidentally introduce framework APIs into domain code, concrete middleware dependencies into application code, technical models into the wrong module, or unmanaged dependency versions in child module POM files.

## Decision

Extend the architecture-test module with executable governance rules:

- technology isolation tests for domain and application layers;
- package and naming convention tests for technical and adapter-owned types;
- Maven dependency governance tests for child module POM files.

The rules focus on stable boundaries and avoid ambiguous terminology. For example, persistence-specific names such as `PO`, `Dao`, and `Mapper` are restricted to infrastructure, while domain terms such as `Entity` are not globally banned.

## Consequences

Benefits:

- AI-generated changes receive fast feedback when they cross architectural boundaries.
- Domain and application code stay insulated from concrete frameworks and middleware.
- External dependency versions remain governed from the root POM.
- Architecture rules are discoverable as tests and run during Maven verification.

Trade-offs:

- Future framework adoption may require deliberate rule updates.
- Naming convention checks can occasionally need refinement as new patterns emerge.
