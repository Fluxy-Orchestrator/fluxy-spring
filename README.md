# Fluxy Spring

[![Java](https://img.shields.io/badge/Java-25-orange)](https://openjdk.org/)
[![Gradle](https://img.shields.io/badge/Gradle-9.4-blue)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

**Fluxy Spring** es una librería de persistencia JPA para [Fluxy Core](https://github.com/Fluxy-Orchestrator/fluxy-core). Proporciona entidades Jakarta Persistence y repositorios Spring Data listos para usar, permitiendo persistir flujos, steps, tasks, conexiones, condiciones y contextos de ejecución en cualquier base de datos relacional.

---

## 📋 Contenidos

- [Características](#-características)
- [Modelo de Datos](#-modelo-de-datos)
- [Instalación](#-instalación)
- [Entidades](#-entidades)
- [Repositorios](#-repositorios)
- [Uso](#-uso)

---

## 🚀 Características

- ✅ **13 entidades JPA** que mapean el modelo completo de Fluxy Core
- ✅ **13 repositorios Spring Data** con queries derivadas útiles
- ✅ **Persistencia completa de flujos** incluyendo Connections y Conditions
- ✅ **Persistencia de ejecuciones** con trazabilidad completa (ExecutionMetaInf)
- ✅ **Sin autoconfiguration** — librería pura de entidades y repositorios
- ✅ **Compatible con cualquier DB relacional** soportada por Hibernate

---

## 🗄️ Modelo de Datos

```
┌──────────────────────────────────────────────────────────────────┐
│                    DEFINICIÓN DE FLUJOS                          │
├──────────────────────────────────────────────────────────────────┤
│                                                                  │
│  fluxy_flow ──┬── fluxy_flow_step ──── fluxy_step                │
│               │        │                   │                     │
│               │        │              fluxy_step_task ── fluxy_task│
│               │        │                                         │
│               └── fluxy_connection                               │
│                       │                                          │
│                  fluxy_condition                                  │
│                                                                  │
├──────────────────────────────────────────────────────────────────┤
│                  CONTEXTO DE EJECUCIÓN                           │
├──────────────────────────────────────────────────────────────────┤
│                                                                  │
│  fluxy_execution_context ──┬── fluxy_variable                    │
│                            ├── fluxy_reference                   │
│                            └── fluxy_execution_meta_inf          │
│                                       │                          │
│                              fluxy_execution_step_record         │
│                                       │                          │
│                              fluxy_execution_task_record         │
│                                                                  │
└──────────────────────────────────────────────────────────────────┘
```

---

## 📦 Instalación

### Gradle

```gradle
dependencies {
    implementation 'org.fluxy:fluxy-spring:1.0.0'
}
```

### Maven

```xml
<dependency>
    <groupId>org.fluxy</groupId>
    <artifactId>fluxy-spring</artifactId>
    <version>1.0.0</version>
</dependency>
```

> **Nota:** Requiere `fluxy-core:1.0.0` como dependencia transitiva (incluida automáticamente vía `api`).

---

## 🏛️ Entidades

### Definición de Flujos

| Entidad | Tabla | Descripción |
|---------|-------|-------------|
| `FluxyFlowEntity` | `fluxy_flow` | Flujo de negocio: nombre, tipo y descripción |
| `FluxyStepEntity` | `fluxy_step` | Step reutilizable con nombre único |
| `FluxyTaskEntity` | `fluxy_task` | Task con nombre único (identidad) |
| `FlowStepEntity` | `fluxy_flow_step` | Vincula Flow↔Step con orden y estado |
| `StepTaskEntity` | `fluxy_step_task` | Vincula Step↔Task con orden, estado y resultado |
| `ConnectionEntity` | `fluxy_connection` | Bifurcación entre FlowSteps |
| `ConditionEntity` | `fluxy_condition` | Condición de una conexión (operador + valor esperado + variable) |

### Contexto de Ejecución

| Entidad | Tabla | Descripción |
|---------|-------|-------------|
| `ExecutionContextEntity` | `fluxy_execution_context` | Contexto: tipo, versión, variables y referencias |
| `VariableEntity` | `fluxy_variable` | Variable del contexto (nombre + valor TEXT) |
| `ReferenceEntity` | `fluxy_reference` | Referencia externa (tipo + valor TEXT) |
| `ExecutionMetaInfEntity` | `fluxy_execution_meta_inf` | Metadata de ejecución (1:1 con contexto) |
| `ExecutionStepRecordEntity` | `fluxy_execution_step_record` | Registro de ejecución por FlowStep |
| `ExecutionTaskRecordEntity` | `fluxy_execution_task_record` | Registro de ejecución por StepTask con estado y resultado |

---

## 📂 Repositorios

Todos extienden `JpaRepository<T, UUID>`:

| Repositorio | Queries derivadas |
|-------------|-------------------|
| `FluxyFlowRepository` | `findByName`, `findByType` |
| `FluxyStepRepository` | `findByName` |
| `FluxyTaskRepository` | `findByName` |
| `FlowStepRepository` | `findByFlow`, `findByFlowOrderByStepOrderAsc`, `findByFlowAndStepOrder` |
| `StepTaskRepository` | `findByStep`, `findByStepOrderByTaskOrderAsc` |
| `ConnectionRepository` | `findByFlow`, `findByFromStep`, `findByToStep` |
| `ConditionRepository` | `findByConnection` |
| `ExecutionContextRepository` | `findByType`, `findByTypeAndVersion` |
| `VariableRepository` | `findByExecutionContext`, `findByExecutionContextAndName` |
| `ReferenceRepository` | `findByExecutionContext`, `findByExecutionContextAndRefType` |
| `ExecutionMetaInfRepository` | `findByExecutionContext` |
| `ExecutionStepRecordRepository` | `findByExecutionMetaInf`, `findByFlowStep` |
| `ExecutionTaskRecordRepository` | `findByExecutionStepRecord` |

---

## 💡 Uso

Esta librería es **solo entidades + repositorios**. El proyecto consumidor debe:

1. Incluir `fluxy-spring` como dependencia
2. Configurar `@EnableJpaRepositories(basePackages = "org.fluxy.spring.persistence.repository")`
3. Configurar `@EntityScan(basePackages = "org.fluxy.spring.persistence.entity")`
4. Proveer un `DataSource` y configuración JPA/Hibernate

```java
@Configuration
@EnableJpaRepositories(basePackages = "org.fluxy.spring.persistence.repository")
@EntityScan(basePackages = "org.fluxy.spring.persistence.entity")
public class FluxyPersistenceConfig {
}
```

---

## 📄 Licencia

MIT — ver [LICENSE](LICENSE).
