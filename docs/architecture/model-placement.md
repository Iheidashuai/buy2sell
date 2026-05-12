# Model Placement Guide

This guide defines where models, DTOs, persistence objects, and utilities belong in the `buy2sell` Maven module architecture.

## Shared Kernel

Use `buy2sell-shared-kernel` for code that is stable, broadly reused, and free from technical dependencies.

Allowed examples:

- common model classes shared across multiple modules or bounded contexts;
- common value objects with no middleware, framework, persistence, transport, or configuration dependency;
- common utility classes that are deterministic, framework-free, and do not hide infrastructure access;
- small shared exceptions or base types that are not tied to HTTP, RPC, database, Redis, Apollo, MQ, or Spring.

Do not put these in `buy2sell-shared-kernel`:

- database PO, entity, DAO, mapper, or ORM annotations;
- HTTP/RPC request or response DTOs;
- Redis, Apollo, MQ, database, RPC client, or Spring helper code;
- code that reads environment variables, files, remote services, Redis, Apollo, databases, or network resources;
- feature-specific domain models that belong to one bounded context.

## Domain Models

Use `buy2sell-domain` for domain models and business rules.

Examples:

- aggregate roots such as `Order`, `Product`, or `Task`;
- entities and value objects owned by one domain;
- domain events;
- domain exceptions;
- business state transitions and invariants.

Domain models must not depend on database DTOs, protocol DTOs, Redis, Apollo, HTTP, RPC, Spring, ORM, or infrastructure clients.

## Application Models

Use `buy2sell-application` for use-case input and output models.

Examples:

- commands;
- queries;
- application views;
- application result objects;
- ports/interfaces such as repositories, cache ports, configuration ports, MQ ports, and outbound RPC ports.

Application models must not use concrete middleware client APIs.

## Adapter DTOs

Use `buy2sell-adapter` for inbound protocol models.

Examples:

- HTTP request and response DTOs;
- RPC request and response DTOs;
- facade input and output DTOs;
- protocol error response models.

Adapter DTOs should be converted to application commands or queries before entering the application layer.

## Infrastructure Models

Use `buy2sell-infrastructure` for technical models and implementations.

Examples:

- database PO/entity classes;
- DAO and mapper classes;
- ORM mapping models;
- Redis key/value models;
- Apollo configuration implementation models;
- MQ message implementation models;
- outbound RPC client DTOs and adapters;
- serializers, codecs, and technical utility classes.

Infrastructure models may map to and from domain or application models, but domain code must never depend on infrastructure models.

## Bootstrap Models

Use `buy2sell-bootstrap` for startup and wiring concerns.

Examples:

- runtime configuration assembly;
- framework bootstrapping;
- bean/client initialization;
- main application entry points.

## Quick Decision Table

| Item | Module |
| --- | --- |
| Common model shared by multiple modules and free from technical dependencies | `buy2sell-shared-kernel` |
| Common framework-free utility | `buy2sell-shared-kernel` |
| Feature-specific domain model with business rules | `buy2sell-domain` |
| Application command/query/view/result | `buy2sell-application` |
| Repository/cache/config/MQ/RPC port interface | `buy2sell-application` |
| HTTP/RPC inbound request or response DTO | `buy2sell-adapter` |
| Database PO/entity/DAO/mapper | `buy2sell-infrastructure` |
| Redis/Apollo/MQ/outbound RPC implementation model | `buy2sell-infrastructure` |
| Runtime wiring or framework startup model | `buy2sell-bootstrap` |
