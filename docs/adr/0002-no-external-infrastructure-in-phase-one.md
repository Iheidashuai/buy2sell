# ADR 0002: No External Infrastructure Dependencies in Phase One

## Status

Accepted

## Context

The project will eventually involve real backend infrastructure, but the first step is to validate the AI coding harness and module boundaries.

## Decision

Do not introduce the following dependencies in phase one:

- Spring Boot
- MySQL
- Redis
- RocketMQ
- Apollo
- HTTP frameworks
- ORM frameworks
- RPC frameworks

Use an in-memory repository for the Task example.

## Consequences

Benefits:

- Faster bootstrap.
- Fewer moving parts.
- AI-generated changes are easier to review.
- Architecture and test rules become stable before adding infrastructure.

Trade-offs:

- Not production-ready yet.
- Future specs must introduce infrastructure explicitly.
