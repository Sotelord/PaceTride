# PaceTride

Aplicación Android nativa (Jetpack Compose) para descubrir, explorar e inscribirse a carreras de running (5K, 10K, 21K, 42K) en Colombia.

> ⚠️ **Estado: prototipo visual (UI-only).** Las pantallas están construidas con datos de ejemplo (*hardcoded*) y no hay navegación, lógica de negocio, persistencia ni backend conectados. Ver [Estado del proyecto](#estado-del-proyecto).

## Descripción

PaceTride muestra carreras destacadas, un listado de próximas carreras, filtros por distancia, un explorador con buscador y grid de resultados, el detalle de una carrera (ruta, fecha, inscripción, kit incluido) y un perfil de usuario con historial y estadísticas.

## Estado del proyecto

- ✅ 4 pantallas maquetadas en Compose: `HomeScreen`, `ExploreScreen`, `RaceDetailSreeen`, `ProfileScreen`.
- ❌ Sin navegación real: `MainActivity` solo renderiza `HomeScreen()`. Las demás pantallas existen en el código pero no son alcanzables desde la app (no hay `NavHost` ni `NavController`).
- ❌ Botones sin acción: "Ver carrera" e "INSCRIBEME" están conectados a `onClick = { /*TODO*/ }`.
- ❌ Datos 100% estáticos: carreras, precios, fechas y perfil están escritos directamente en el código, no vienen de una API, base de datos local (Room) ni `ViewModel`.
- ❌ Sin tests reales: `ExampleUnitTest` y `ExampleInstrumentedTest` son los tests de plantilla que genera Android Studio, no prueban lógica de la app.

## Capturas / Pantallas

| Pantalla | Archivo | Descripción |
|---|---|---|
| Inicio | `ui/HomeScreen.kt` | Saludo al usuario, carrera destacada, próximas carreras, chips de distancia y barra de navegación inferior |
| Explorar | `ui/ExploreScreen.kt` | Buscador, filtros y grid de carreras cercanas |
| Detalle de carrera | `ui/RaceDetailSreeen.kt` | Portada, fecha/hora/lugar, ruta, kit de inscripción y botón de inscripción |
| Perfil | `ui/ProfileScreen.kt` | Foto, estadísticas (mejor 10K/21K), historial y próximas carreras del usuario |

## Stack tecnológico

- **Lenguaje:** Kotlin 2.2.10
- **UI:** Jetpack Compose (Material 3), sin XML views
- **Build:** Gradle (Kotlin DSL) con Android Gradle Plugin 9.3.1, `libs.versions.toml` (Version Catalog)
- **SDK:** `minSdk 26` · `targetSdk 37` · `compileSdk 37`
- **Testing:** JUnit4 + Espresso (aún no utilizados más allá de la plantilla)

## Estructura del proyecto

```
PaceTride/
├── app/
│   ├── src/main/java/com/example/pacetride/
│   │   ├── MainActivity.kt
│   │   └── ui/
│   │       ├── HomeScreen.kt
│   │       ├── ExploreScreen.kt
│   │       ├── RaceDetailSreeen.kt      # (typo: "Sreeen")
│   │       ├── ProfileScreen.kt
│   │       └── theme/
│   │           ├── Color.kt
│   │           ├── Theme.kt
│   │           └── Type.kt
│   ├── src/main/res/                    # drawables, strings, colores, iconos
│   ├── src/test/                        # tests unitarios (plantilla)
│   └── src/androidTest/                 # tests instrumentados (plantilla)
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

- Android Studio (Ladybug o superior recomendado, por AGP 9.3.1)
- JDK 11
- SDK de Android con API 37 instalado

## Instalación y ejecución

```bash
git clone https://github.com/Sotelord/PaceTride.git
cd PaceTride
./gradlew assembleDebug
```

O ábrelo directamente en Android Studio y ejecuta `app` sobre un emulador/dispositivo con Android 8.0 (API 26) o superior.

## Pendientes conocidos (roadmap sugerido)

- [ ] Agregar Navigation Compose y conectar `HomeScreen`, `ExploreScreen`, `RaceDetailSreeen` y `ProfileScreen`.
- [ ] Implementar los `onClick` de "Ver carrera" e "INSCRIBEME".
- [ ] Extraer los datos de ejemplo a un modelo (`data class Race`) y un `ViewModel`/`repository`, en vez de tenerlos escritos en cada Composable.
- [ ] Corregir el nombre de archivo `RaceDetailSreeen.kt` → `RaceDetailScreen.kt`.
- [ ] Mover los strings hardcodeados restantes a `strings.xml` (ya existe la mayoría, pero algunos textos siguen en el código).
- [ ] Reemplazar los tests de plantilla por pruebas reales de UI y de lógica.


