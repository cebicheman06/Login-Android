# Implementation Plan - Sign-In View with SOLID Principles

This plan outlines the creation of a Sign-In screen for the `Ejemplo2` application. The implementation will follow SOLID principles and a clean architecture structure (MVVM).

## User Review Required

> [!IMPORTANT]
> I will be introducing a multi-layer package structure (`data`, `domain`, `ui`) to adhere to SOLID principles and Clean Architecture.
> I will also add `androidx.lifecycle:lifecycle-viewmodel-compose` dependency to support ViewModels in Compose.

## Proposed Changes

### Dependencies

#### [MODIFY] [libs.versions.toml](file:///D:/Sexto%20Semestre/Desarrollo_de_Aplicaciones_Moviles/Primer_Parcial/Ejemplo2/gradle/libs.versions.toml)
- Add `androidx-lifecycle-viewmodel-compose` library.

#### [MODIFY] [build.gradle.kts](file:///D:/Sexto%20Semestre/Desarrollo_de_Aplicaciones_Moviles/Primer_Parcial/Ejemplo2/app/build.gradle.kts)
- Add `androidx-lifecycle-viewmodel-compose` dependency.

---

### Domain Layer (SOLID: Dependency Inversion, Single Responsibility)

#### [NEW] `AuthRepository.kt`
- Interface defining the contract for authentication operations.

#### [NEW] `SignInUseCase.kt`
- Domain logic for the sign-in operation, abstracting the repository.

---

### Data Layer (SOLID: Liskov Substitution)

#### [NEW] `AuthRepositoryImpl.kt`
- Implementation of `AuthRepository`. For now, it will use a mock implementation.

---

### UI Layer (SOLID: Single Responsibility, Interface Segregation)

#### [NEW] `SignInViewModel.kt`
- Manages the state of the Sign-In screen and interacts with the domain layer.

#### [NEW] `SignInScreen.kt`
- Compose UI for the sign-in view.

#### [MODIFY] `MainActivity.kt`
- Update to show the `SignInScreen`.

## Verification Plan

### Automated Tests
- I will create a unit test for `SignInViewModel` to verify it correctly calls the use case.
- I will create a unit test for `SignInUseCase` to verify it correctly calls the repository.

### Manual Verification
- Deploy the app to a device/emulator and verify:
    - UI displays correctly.
    - Error messages are shown for invalid input (mocked).
    - Success state is handled (mocked).
