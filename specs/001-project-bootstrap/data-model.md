# Data Model: Project Bootstrap

## Task

Fields:

- `TaskId id`
- `TaskTitle title`
- `TaskStatus status`
- `Instant createdAt`
- `Instant completedAt`

Rules:

- A new task starts as `OPEN`.
- An `OPEN` task may be renamed.
- An `OPEN` task may be completed.
- A `COMPLETED` task may not be renamed.
- A `COMPLETED` task may not be completed again.

## TaskStatus

- `OPEN`
- `COMPLETED`
