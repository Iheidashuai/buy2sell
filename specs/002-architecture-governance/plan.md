# Implementation Plan: Architecture Governance Tests

## Technical Context

- Java: JDK 11
- Build: Maven multi-module
- Test: JUnit 5, AssertJ
- Architecture test: ArchUnit
- Existing related specs: `specs/001-project-bootstrap/`

## Impacted Modules

- `buy2sell-common`: No change.
- `buy2sell-domain`: No change.
- `buy2sell-application`: No change.
- `buy2sell-infrastructure`: No change.
- `buy2sell-adapter`: No change.
- `buy2sell-bootstrap`: No change.
- `buy2sell-architecture-test`: Add architecture governance tests.

## Dependency Changes

- New dependencies: None.
- Dependency management changes: None.

## Data Model Changes

- Domain models: None.
- Application models: None.
- Infrastructure models: None.
- Adapter DTOs: None.

## Application Flow

1. Maven test phase runs `buy2sell-architecture-test`.
2. ArchUnit imports `com.buy2sell` classes and checks technology/package boundaries.
3. JUnit parses module `pom.xml` files and checks child-module dependency version governance.

## Architecture Impact

- Module boundary impact: existing dependency direction remains unchanged.
- ADR needed: Yes, add ADR 0002 for executable architecture governance scope.
- Architecture tests needed: Yes, this feature adds them.

## Testing Strategy

- Domain tests: None.
- Application tests: None.
- Infrastructure tests: None.
- Adapter tests: None.
- Bootstrap or integration smoke tests: None.
- Architecture tests: add technology isolation, package convention, and Maven dependency governance tests.

## Long-Term Memory Updates

- `specs/README.md`: Add architecture governance feature.
- `docs/product/feature-map.md`: Add project governance capability.
- `docs/domain/glossary.md`: Add architecture governance terminology.
- `docs/domain/invariants.md`: Add project governance invariants.

## Risks

- Rule set could become too strict for future framework adoption. Mitigation: rules focus on domain/application isolation and clear module ownership.
- Naming rules could misclassify future classes. Mitigation: enforce only clear technical suffixes, not ambiguous domain terms like Entity.

## Plan Readiness Checklist

- [x] Impacted modules are listed.
- [x] Dependency changes are listed or explicitly marked as none.
- [x] Data model changes are listed or explicitly marked as none.
- [x] Architecture impact is clear.
- [x] Testing strategy is clear.
- [x] Long-term memory updates are identified.

