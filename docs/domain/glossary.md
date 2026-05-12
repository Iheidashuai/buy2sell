# Domain Glossary

This glossary defines stable business terms used across specs, code, tests, and documentation. Add or update terms when a feature introduces new domain language. Merge synonyms under one primary term instead of creating competing definitions.

## Project Terms

### buy2sell

The Java backend project and product workspace.

### Feature

A user-visible or system capability documented under `specs/{feature}/`.

### Spec

The authoritative requirement source for a feature. Feature details belong in `specs/{feature}/spec.md`, `plan.md`, and `tasks.md`.

### Architecture Rule

An executable test or documented constraint that protects module boundaries, technology isolation, package conventions, or dependency governance.

### Technology Isolation

The rule that domain and application code must not depend on concrete frameworks, middleware clients, persistence APIs, HTTP APIs, or other technical integration details.

### Maven Dependency Governance

The rule that external dependency versions are managed from the root POM rather than directly in child module POM files.

## Example Terms

### Task

An example domain object used by the bootstrap feature to validate architecture and AI workflow. It is not currently a core `buy2sell` business concept.

### Task Title

The human-readable name of a Task. It must be non-blank.

### Task Status

The lifecycle state of a Task in the example capability.
