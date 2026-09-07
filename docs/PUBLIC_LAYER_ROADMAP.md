# Public Layer Roadmap

This repository is the public contract layer for the `brick-bootkit-admin` ecosystem.

## Current published modules

- `eqadmin-bom`
- `eqadmin-core-constants`
- `eqadmin-annotations`
- `brick-boot-admin-api`
- `eqadmin-spi`
- `eqadmin-result`

## Current public contract scope

- install payloads
- install status models
- install dependency check models
- install lifecycle events
- current account model
- company info model
- current account provider SPI
- company provider SPI
- public persistence base entity
- base shared constants
- machine code api models
- health monitor models
- log management models
- command execution result model
- request crypto payload models
- request crypto public key metadata model
- SSL automation payloads
- SSL reload lifecycle events
- license mode enum
- dashboard and monitor VO models
- user and permission VO models
- message channel DTO and SPI contracts
- message event type enum
- message dispatch event models

## Planned next modules

### Phase 1

- extend `eqadmin-spi`
  - more provider contracts
  - message channel contracts
  - event listener contracts

### Phase 2 (in progress)

- `eqadmin-starter-api`
  - public auto-configuration facade (`EqadminSpiAutoConfiguration` + `SpiRegistry`)
  - starter metadata (`EqadminStarterMetadata`)
  - SPI bootstrap helpers (`SpiBootstrap`, `EnableEqadminSpi`)

## What should stay private

Do not move these classes or packages into the public repository:

- controller implementations
- service implementations
- database entities and mappers
- interceptors, filters and aspects
- license verification internals
- plugin market internals
- commercial business logic
- install/runtime orchestration internals

## Migration order from private repository

1. Move public annotations and result model first.
2. Replace private repository imports with Central coordinates.
3. Extract stable DTO/VO models into `eqadmin-api`.
4. Extract explicit SPI interfaces into `eqadmin-spi`.
5. Keep private implementation modules depending on the published public modules.

## Naming convention

All public artifacts should use:

- GroupId: `cn.net.rjnetwork`
- Artifact prefix: `eqadmin-*`

Examples:

- `cn.net.rjnetwork:eqadmin-bom`
- `cn.net.rjnetwork:eqadmin-api`
- `cn.net.rjnetwork:eqadmin-spi`
- `cn.net.rjnetwork:eqadmin-result`

## Release rule

- Public repository publishes only open contract modules.
- Private repository publishes obfuscated implementation modules.
- Keep public and private artifact versions aligned whenever possible.
