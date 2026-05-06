# Tasks: Project Bootstrap

## Phase 1: Build Foundation

- [x] T001 Create root Maven parent `pom.xml`.
- [x] T002 Add Maven Wrapper scripts and wrapper properties.
- [x] T003 Configure dependencyManagement.
- [x] T004 Configure pluginManagement.
- [x] T005 Configure Maven Enforcer for JDK 11 and dependency convergence.

## Phase 2: Create Modules

- [x] T006 Create `buy2sell-domain` module.
- [x] T007 Create `buy2sell-application` module.
- [x] T008 Create `buy2sell-infrastructure` module.
- [x] T009 Create `buy2sell-adapter` module.
- [x] T010 Create `buy2sell-bootstrap` module.
- [x] T011 Create `buy2sell-architecture-test` module.

## Phase 3: Domain Model

- [x] T012 Implement `TaskId`.
- [x] T013 Implement `TaskTitle`.
- [x] T014 Implement `TaskStatus`.
- [x] T015 Implement `Task` aggregate.
- [x] T016 Add domain unit tests.

## Phase 4: Application Use Cases

- [x] T017 Define `TaskRepository` interface.
- [x] T018 Implement command objects.
- [x] T019 Implement `TaskApplicationService`.
- [x] T020 Add application unit tests.

## Phase 5: Infrastructure

- [x] T021 Implement `InMemoryTaskRepository`.
- [x] T022 Add repository behavior tests.

## Phase 6: Adapter and Bootstrap

- [x] T023 Add simple `TaskFacade`.
- [x] T024 Add `Buy2SellBootstrap` wiring.
- [x] T025 Add smoke test.

## Phase 7: Architecture Governance

- [x] T026 Add ArchUnit dependency rules.
- [x] T027 Add ADR 0001.
- [x] T028 Add ADR 0002.
- [x] T029 Update README.
- [ ] T030 Run `./mvnw clean verify` under JDK 11.

## Notes

T030 is not marked complete here because this generated package was created in an environment without Maven installed and with JDK 21 active. Run it locally with JDK 11.
