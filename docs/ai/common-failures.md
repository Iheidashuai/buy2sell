# Common AI Coding Failures

## Introducing dependencies too early

Do not add Spring Boot, database clients, MQ clients, Redis clients, or Apollo before a feature plan explicitly allows it.

## Mixing layers

Domain classes must not import application, infrastructure, adapter, or bootstrap classes.

## Weak tests

Do not write tests that only check object construction. Tests should validate business behavior and failure paths.

## Forgetting tasks.md

A task is not complete until the corresponding item in `tasks.md` is updated.

## Bypassing architecture tests

Do not weaken ArchUnit rules without an ADR.
