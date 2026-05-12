# Tasks: Architecture Governance Tests

## Phase 1: Spec Alignment

- [x] T001 Confirm `spec.md`, `plan.md`, and `tasks.md` are consistent.
- [x] T002 Resolve or explicitly defer open questions.
- [x] T003 Update `specs/README.md` with this feature.

## Phase 2: Architecture Tests

- [x] T004 Add technology isolation architecture tests.
- [x] T005 Add package naming convention architecture tests.
- [x] T006 Add Maven dependency governance tests.

## Phase 3: Architecture Governance

- [x] T007 Add ADR 0002 for executable architecture governance.

## Phase 4: Long-Term Memory

- [x] T008 Update `docs/product/feature-map.md`.
- [x] T009 Update `docs/domain/glossary.md`.
- [x] T010 Update `docs/domain/invariants.md`.

## Phase 5: Verification

- [x] T011 Run `./mvnw -pl buy2sell-architecture-test -am test`.
- [x] T012 Run `./mvnw clean verify`.
- [x] T013 Update this `tasks.md` to reflect completed work.

## Notes

- Internal project module dependencies may use `${project.version}` in child module POMs.
- External dependency versions must be governed by the root POM.

## Completion Checklist

- [x] Code implemented.
- [x] Tests added or updated and passed.
- [x] Relevant Maven command passed.
- [x] `tasks.md` updated.
- [x] Long-term memory indexes updated.
- [x] ADR updated.
