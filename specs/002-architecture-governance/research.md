# Research: Architecture Governance Tests

## Decisions

### Decision 1: Use ArchUnit for class-level boundaries

- Decision: Use ArchUnit for technology isolation and package naming convention checks.
- Rationale: ArchUnit is already part of the architecture-test module and directly validates Java class dependencies.
- Consequences: Framework package allowlists and denylists remain explicit and executable.

### Decision 2: Use JDK XML parsing for Maven governance

- Decision: Parse child module POM files with the JDK DOM APIs in a JUnit test.
- Rationale: Avoids adding production or test dependencies for a small governance check.
- Consequences: The test remains simple and scoped to dependency version governance.

### Decision 3: Do not ban domain Entity naming

- Decision: Do not enforce a blanket ban on `Entity` suffix outside infrastructure.
- Rationale: `Entity` can be a valid domain modeling term and should not be confused with persistence objects.
- Consequences: Database persistence objects should use PO, DAO, Mapper, or similarly technical names in infrastructure.

## Alternatives Considered

### Alternative 1: Enforce all Maven dependency governance through Maven Enforcer

- Summary: Add more Maven Enforcer rules for dependency governance.
- Reason rejected: The current need is child-POM structure validation, which is easy to express in a focused JUnit test without changing build plugins.

## References

- `docs/architecture/model-placement.md`
- `docs/adr/0001-project-architecture.md`

## Follow-Up Questions

- [ ] Consider adding dependency convergence and Java release enforcement in a future build-governance feature.

