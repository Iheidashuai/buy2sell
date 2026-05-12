# Quickstart: Architecture Governance Tests

## Prerequisites

- JDK 11
- Maven Wrapper from project root

## Build

```bash
./mvnw -v
./mvnw test
```

## Feature Verification

```bash
./mvnw -pl buy2sell-architecture-test -am test
```

## Full Verification

```bash
./mvnw clean verify
```

## Manual Verification

1. Run the feature verification command.
2. Confirm `TechnologyIsolationTest`, `PackageConventionTest`, and `MavenDependencyGovernanceTest` pass.
3. Run full verification for all modules.

## Expected Results

- Architecture governance tests pass.
- Full Maven verification passes.

## Troubleshooting

- Architecture violation: move the class or dependency to the correct module, or document and update the architecture decision before changing the rule.
- Maven dependency governance failure: move external dependency versions to root `dependencyManagement`.

