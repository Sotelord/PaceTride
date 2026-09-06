# PaceTride

Aplicación Android nativa (Jetpack Compose) para descubrir, explorar e inscribirse a carreras de running (5K, 10K, 21K, 42K) en Colombia.

> 🚧 **Estado: en desarrollo activo.** Navegación completa, inyección de dependencias e inicio de sesión con Firebase Auth ya funcionan. Persistencia de datos de negocio (Firestore) e inscripción a carreras aún están pendientes. Ver [Estado del proyecto](#estado-del-proyecto).

## Descripción

PaceTride muestra carreras destacadas, un listado de próximas carreras, filtros por distancia, un explorador con buscador y grid de resultados, el detalle de una carrera (ruta, fecha, inscripción, kit incluido), comunidad con reseñas, notificaciones y un perfil de usuario con historial y estadísticas.

## Estado del proyecto

### ✅ Hecho
- **Navegación completa** con Navigation Compose (`AppNavigation.kt`, `NavHost` con rutas anidadas y argumentos).
- **Splash screen** nativo (Android 12+ Splash Screen API) + `SplashScreen` composable que verifica si hay una sesión activa antes de decidir a dónde navegar (Login u Home).
- **Autenticación con Firebase Auth**: registro (email/contraseña + `displayName`), inicio de sesión, cierre de sesión, y manejo de errores traducidos al español (credenciales inválidas, usuario no encontrado, sin conexión).
- **Inyección de dependencias con Hilt**: `ViewModel`s anotados con `@HiltViewModel`, repositorios y data sources inyectados (`AuthRepository` → `AuthRemoteDataSource` → `FirebaseAuth`).
- **Arquitectura MVVM** en todas las pantallas: `State` inmutable (`data class`) + `StateFlow` + `ViewModel`, sin lógica de negocio en los composables.
- Pantallas maquetadas y conectadas a su `ViewModel` correspondiente: `Login`, `Register`, `Home`, `Explorer`, `MisCarreras`, `Profile`, `RaceDetail`, `Notifications`, `PublicProfile`, `Comunidad`, `EscribirResena`.
- Barra de navegación inferior (`PacetrideBottomNavigationBar`) que se muestra solo en las pantallas principales.

### ❌ Pendiente
- **Persistencia de datos de negocio**: carreras, perfil extendido (ubicación, bio, foto, estadísticas, historial), reseñas y notificaciones siguen viniendo de `LocalCarreraProvider`/`LocalUsuarioProvider` (datos de ejemplo hardcodeados), no de Firestore.
- **Botón "INSCRIBEME"** y pantallas de flujo de inscripción (`Screen.Inscribeme`) aún son placeholders (`Text("Falta esta pantalla")`).
- Pantallas sin implementar: `RecuperarContrasena`, `Configuracion`, `EditProfile`, `Comentarios`, `ConfigUsuarioPublico`.
- Sin tests reales: `ExampleUnitTest` y `ExampleInstrumentedTest` son los tests de plantilla que genera Android Studio.
- Corregir el nombre de archivo `RaceDatailViewModel.kt` / `RaceDatailState.kt` / `RaceDetailSreeen.kt` (typos: "Datail", "Sreeen").

## Pantallas

| Pantalla | Descripción |
|---|---|
| Splash | Verifica sesión activa en Firebase y redirige a Login o Home |
| Login / Register | Autenticación con email y contraseña vía Firebase Auth |
| Inicio | Saludo al usuario, carrera destacada, próximas carreras, chips de distancia |
| Explorar | Buscador, filtros y grid de carreras cercanas |
| Detalle de carrera | Portada, fecha/hora/lugar, ruta, kit de inscripción y selector de distancia con precio dinámico |
| Mis Carreras | Carreras próximas y completadas del usuario, con pestañas |
| Perfil | Foto, email de sesión activa, estadísticas, historial y próximas carreras; incluye logout |
| Comunidad | Reseñas de otros usuarios y acceso para escribir una nueva |
| Notificaciones | Notificaciones del usuario, con acceso a perfil público y detalle de carrera |
| Perfil público | Perfil de otro usuario, con reseñas y configuración |

## Stack tecnológico

- **Lenguaje:** Kotlin 2.4.10
- **UI:** Jetpack Compose (Material 3), sin XML views
- **Arquitectura:** MVVM (`ViewModel` + `StateFlow` + `State` inmutable por pantalla)
- **Inyección de dependencias:** Dagger Hilt
- **Backend:** Firebase Auth (autenticación) — Firestore planeado para datos de negocio
- **Navegación:** Navigation Compose
- **Build:** Gradle (Kotlin DSL), Android Gradle Plugin 9.3.2 (soporte de Kotlin integrado), KSP, `libs.versions.toml` (Version Catalog)
- **SDK:** `minSdk 26` · `targetSdk 37` · `compileSdk 37`
- **Testing:** JUnit4 + Espresso (aún no utilizados más allá de la plantilla)

## Estructura del proyecto

```
PaceTride/
├── app/
│   ├── src/main/java/com/example/pacetride/
│   │   ├── MainActivity.kt
│   │   ├── BaseAplication.kt              # @HiltAndroidApp
│   │   ├── data/
│   │   │   ├── datasource/                # AuthRemoteDataSource, etc.
│   │   │   ├── repository/                # AuthRepository, etc.
│   │   │   ├── local/                     # Providers de datos de ejemplo
│   │   │   └── injection/                 # Módulos de Hilt (FirebaseHiltModule, etc.)
│   │   ├── navigation/
│   │   │   ├── AppNavigation.kt           # NavHost y definición de rutas
│   │   │   └── NavigationLogic.kt
│   │   └── ui/
│   │       ├── screens/                   # Una carpeta por pantalla: Screen.kt, ViewModel.kt, State.kt, components/
│   │       └── theme/
│   ├── src/main/res/                      # drawables, strings, colores, iconos
│   ├── src/test/                          # tests unitarios (plantilla)
│   └── src/androidTest/                   # tests instrumentados (plantilla)
├── gradle/libs.versions.toml
├── build.gradle.kts
└── settings.gradle.kts
```

## Paleta de colores

| Nombre | Hex | Uso |
|---|---|---|
| `electric_lime` | `#C6FF00` | Acento principal (botones, chips activos) |
| `graphite` | `#1A211F` | Fondo de tarjetas y barra de navegación |
| `mist` | `#A7B0AC` | Texto secundario |
| `pulse_orange` | `#FF6B35` | (definido, sin uso actual) |

## Requisitos

- Android Studio (versión reciente compatible con AGP 9.3.2)
- JDK 11
- SDK de Android con API 37 instalado
- Un proyecto de Firebase configurado con Authentication (email/contraseña) habilitado, y `google-services.json` en `app/`

## Instalación y ejecución

```bash
git clone https://github.com/Sotelord/PaceTride.git
cd PaceTride
./gradlew assembleDebug
```

O ábrelo directamente en Android Studio y ejecuta `app` sobre un emulador/dispositivo con Android 8.0 (API 26) o superior. Asegúrate de tener tu propio `google-services.json` de Firebase antes de compilar.

## Roadmap sugerido

- [ ] Modelar y conectar Firestore para carreras, perfil de usuario, reseñas e historial (reemplazando los providers locales).
- [ ] Implementar el flujo de inscripción a una carrera (`Screen.Inscribeme`).
- [ ] Completar las pantallas pendientes: Recuperar contraseña, Configuración, Editar perfil, Comentarios, Config. de perfil público.
- [ ] Agregar tests reales de `ViewModel`s (lógica de estado) y de UI.
- [ ] Corregir los typos de nombres de archivo (`RaceDatail*` → `RaceDetail*`, `RaceDetailSreeen` → `RaceDetailScreen`).