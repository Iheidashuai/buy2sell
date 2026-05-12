# Tasks: <Feature Name>

## Phase 1: Spec Alignment

- [ ] T001 Confirm `spec.md`, `plan.md`, and `tasks.md` are consistent.
- [ ] T002 Resolve or explicitly defer open questions.
- [ ] T003 Update `specs/README.md` with this feature.

## Phase 2: Domain

- [ ] T004 Implement or update domain models and value objects.
- [ ] T005 Implement or update domain business rules.
- [ ] T006 Add or update domain unit tests.

## Phase 3: Application

- [ ] T007 Implement or update application commands, queries, views, and results.
- [ ] T008 Implement or update application ports.
- [ ] T009 Implement or update application services.
- [ ] T010 Add or update application tests.

## Phase 4: Infrastructure

- [ ] T011 Implement or update infrastructure adapters for application ports.
- [ ] T012 Add or update infrastructure tests.

## Phase 5: Adapter and Bootstrap

- [ ] T013 Implement or update inbound adapter DTOs, facades, or controllers.
- [ ] T014 Update bootstrap wiring if needed.
- [ ] T015 Add or update adapter and bootstrap tests.

## Phase 6: Architecture Governance

- [ ] T016 Add or update architecture tests if module boundaries or rules changed.
- [ ] T017 Add or update ADR if architecture or technology decisions changed.

## Phase 7: Long-Term Memory

- [ ] T018 Update `docs/product/feature-map.md` if product capabilities changed.
- [ ] T019 Update `docs/domain/glossary.md` if business terms changed.
- [ ] T020 Update `docs/domain/invariants.md` if durable business rules changed.

## Phase 8: Verification

- [ ] T021 Run relevant module tests.
- [ ] T022 Run `./mvnw clean verify` for cross-module changes.
- [ ] T023 Update this `tasks.md` to reflect completed work.

## Notes

- Remove tasks that do not apply only after the plan explains why they are unnecessary.
- Do not mark verification tasks complete until the command has passed locally or the blocker is recorded.

## Completion Checklist

- [ ] Code implemented.
- [ ] Tests added or updated.
- [ ] Relevant Maven command passed.
- [ ] `tasks.md` updated.
- [ ] Long-term memory indexes updated or explicitly skipped.
- [ ] ADR updated or explicitly skipped.

