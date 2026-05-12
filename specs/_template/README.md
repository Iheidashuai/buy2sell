# Feature Template Guide

Use this template directory when creating a new feature spec.

## How to Create a Feature Spec

1. Copy this directory to `specs/{number}-{feature-name}/`.
2. Replace all placeholder text wrapped in `<...>`.
3. Delete template guidance that does not apply.
4. Keep detailed requirements in the feature directory.
5. Update long-term memory indexes after the feature is specified or changed:
   - `specs/README.md`
   - `docs/product/feature-map.md`
   - `docs/domain/glossary.md`
   - `docs/domain/invariants.md`

## File Purpose

- `spec.md`: user need, business scope, functional requirements, acceptance criteria, and open questions.
- `plan.md`: technical approach, impacted modules, dependency changes, architecture impact, testing strategy, and risks.
- `tasks.md`: implementation checklist grouped by execution phase.
- `quickstart.md`: build, test, and manual verification instructions.
- `research.md`: decisions, alternatives, rejected options, and references.

