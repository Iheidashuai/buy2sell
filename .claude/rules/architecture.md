# Architecture Rules

- `domain` must not depend on other layers.
- `application` must not depend on infrastructure, adapter, or bootstrap.
- `infrastructure` must not depend on adapter or bootstrap.
- `adapter` must not depend on infrastructure or bootstrap.
- `bootstrap` wires implementations.
- Architecture changes require ADR updates.
