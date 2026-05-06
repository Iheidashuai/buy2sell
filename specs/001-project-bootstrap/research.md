# Research: Project Bootstrap

## Decision 1: Start without Spring Boot

Phase one intentionally avoids Spring Boot so that the AI coding harness validates project structure, tests, and module boundaries before introducing runtime framework complexity.

## Decision 2: Use in-memory implementation

The Task example uses an in-memory repository to validate application port and infrastructure adapter boundaries without introducing external infrastructure.

## Decision 3: Use ArchUnit

ArchUnit expresses architecture rules as executable tests. This gives Claude Code immediate feedback when module or package boundaries drift.
