# Implementation Plan: Project Bootstrap

## Technical Context

- Java: JDK 11
- Build: Maven multi-module
- Test: JUnit 5, AssertJ
- Architecture test: ArchUnit
- Build governance: Maven Enforcer

## Modules

1. `buy2sell-domain`
   - Task aggregate
   - TaskId
   - TaskTitle
   - TaskStatus
   - Domain exceptions

2. `buy2sell-application`
   - TaskRepository port
   - Commands
   - TaskView
   - TaskApplicationService

3. `buy2sell-infrastructure`
   - InMemoryTaskRepository

4. `buy2sell-adapter`
   - TaskFacade

5. `buy2sell-bootstrap`
   - Manual wiring
   - Main class

6. `buy2sell-architecture-test`
   - ArchUnit layer rules

## Dependency Policy

Allowed dependencies:

- JUnit Jupiter
- AssertJ
- Mockito
- ArchUnit
- Maven Enforcer

## Verification

Run:

```bash
./mvnw clean verify
```

Expected result:

- all module tests pass
- architecture tests pass
- enforcer rules pass
