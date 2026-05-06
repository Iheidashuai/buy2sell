# Prompt Examples

## Create a new feature spec

```text
/speckit.specify
Implement order core capability.
The first phase must use an in-memory repository only and must not introduce Spring Boot, MySQL, Redis, MQ, or Apollo.
```

## Generate plan

```text
/speckit.plan
Use the existing JDK 11 Maven multi-module architecture. Domain rules go into buy2sell-domain. Application orchestration goes into buy2sell-application. Technical implementations go into buy2sell-infrastructure.
```

## Review AI implementation

```text
Please review the current diff against memory/constitution.md, CLAUDE.md, and the active specs folder. Focus on module boundaries, dependency drift, missing tests, and whether tasks.md is updated.
```
