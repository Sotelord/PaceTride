# PaceTride

Aplicación Android nativa (Jetpack Compose) para descubrir, explorar e inscribirse a carreras de running (5K, 10K, 21K, 42K) en Colombia.

> 🚧 **Estado: en desarrollo activo.** Navegación completa, inyección de dependencias, inicio de sesión con Firebase Auth, carga de imágenes (Coil + Firebase Storage) y consumo de un backend REST con Retrofit (carreras, reseñas y usuarios, con CRUD de reseñas) ya funcionan. La inscripción a carreras y varias pantallas secundarias siguen pendientes. Ver [Estado del proyecto](#estado-del-proyecto).

## Descripción

PaceTride muestra carreras destacadas, un listado de próximas carreras, filtros por distancia, un explorador con buscador y grid de resultados, el detalle de una carrera (ruta, fecha, inscripción, kit incluido, reseñas), comunidad con reseñas, notificaciones y un perfil de usuario con estadísticas y sus propias reseñas, que puede crear, editar y eliminar.

## Estado del proyecto

### ✅ Hecho
- **Navegación completa** con Navigation Compose (`AppNavigation.kt`, `NavHost` con rutas anidadas y argumentos). Los ids viajan como `String`, y la pantalla de escribir reseña recibe `raceId` y un `reviewId` opcional (sin `reviewId` crea una reseña, con `reviewId` edita esa reseña).
- **Splash screen** nativo (Android 12+ Splash Screen API) + `SplashScreen` composable que verifica si hay una sesión activa antes de decidir a dónde navegar (Login u Home).
- **Autenticación con Firebase Auth**: registro (email/contraseña + `displayName`), inicio de sesión, cierre de sesión, y manejo de errores traducidos al español (credenciales inválidas, usuario no encontrado, sin conexión) usando `Result` en `AuthRepository` y los `ViewModel`s.
- **Carga de imágenes con Coil y Firebase Storage**: `RaceAsyncImage` y `ProfileAsyncImage` cargan imágenes de forma asíncrona (con placeholder y estado de error) a partir de URLs dinámicas; `StorageRepository`/`StorageRemoteDataSource` suben y actualizan la foto de perfil del usuario en Firebase Storage.
- **Consumo de API REST con Retrofit** (carreras, reseñas y usuarios):
    - Configuración de Retrofit en un módulo de Hilt, con conversores Gson y Scalars.
    - DTOs que reflejan el JSON del backend y funciones de mapeo (`toCarrera()`, `toResena()`, `toUsuario()`) hacia los modelos de UI.
    - Un data source por entidad, con su interfaz y su implementación sobre Retrofit.
    - Repositorios que devuelven `Result` y traducen cada tipo de excepción (HTTP, timeout, sin conexión, servidor caído, respuesta inesperada) a mensajes amigables.
- **CRUD de reseñas**: crear, editar (la pantalla se precarga consultando la reseña por id) y eliminar reseñas desde el perfil. El perfil muestra únicamente las reseñas del usuario.
- **Estados de carga y error** (`isLoading` y `errorMessage` en el `State` de cada pantalla, con indicador de progreso y mensaje) en Home, Explorar, Detalle de carrera, Perfil, Perfil público y Escribir reseña.
- **Navegación al perfil público** al tocar la foto o el nombre de un usuario en una reseña (callback `onClickUsuario`).
- **Detalle de carrera** con la primera distancia seleccionada por defecto y precio calculado según la distancia.
- **Inyección de dependencias con Hilt**: `ViewModel`s anotados con `@HiltViewModel`, repositorios y data sources inyectados.
- **Arquitectura MVVM** en todas las pantallas: `State` inmutable (`data class`) + `StateFlow` + `ViewModel`, sin lógica de negocio en los composables.
- Barra de navegación inferior (`PacetrideBottomNavigationBar`) que se muestra solo en las pantallas principales.

### ❌ Pendiente
- **Datos que aún vienen de los providers locales** (`LocalCarreraProvider`, `LocalUsuarioProvider`): las próximas carreras y el historial del perfil, y otras pantallas que todavía no consumen la API (por ejemplo Comunidad, Notificaciones y Mis carreras).
- **Usuario autenticado fijo**: los usuarios del backend no están vinculados con los de Firebase Auth, así que la app usa un id de usuario fijo (`"3"`) para las consultas. Falta asociar el uid de Firebase con el usuario del backend.
- **Botón "INSCRIBEME"** y pantallas de flujo de inscripción (`Screen.Inscribeme`) aún son placeholders (`Text("Falta esta pantalla")`).
- **Escribir reseña desde Comunidad**: no hay una carrera elegida, y la ruta exige `raceId`.
- Pantallas sin implementar: `RecuperarContrasena`, `Configuracion`, `EditProfile`, `Comentarios`, `ConfigUsuarioPublico`.
- Sin tests reales: `ExampleUnitTest` y `ExampleInstrumentedTest` son los tests de plantilla que genera Android Studio.
- Los repositorios dependen de la implementación Retrofit de cada data source y no de la interfaz, lo que habrá que cambiar para poder sustituirla (por ejemplo por Firestore).

## Pantallas

| Pantalla | Descripción |
|---|---|
| Splash | Verifica sesión activa en Firebase y redirige a Login o Home |
| Login / Register | Autenticación con email y contraseña vía Firebase Auth |
| Inicio | Saludo al usuario, carrera destacada, próximas carreras, chips de distancia |
| Explorar | Buscador, filtros y grid de carreras, cargadas desde la API |
| Detalle de carrera | Portada, fecha/hora/lugar, ruta, kit de inscripción, selector de distancia con precio dinámico y reseñas de la carrera |
| Mis Carreras | Carreras próximas y completadas del usuario, con pestañas |
| Perfil | Foto, email de sesión activa, estadísticas y sus reseñas, que puede editar o eliminar; incluye logout |
| Escribir reseña | Calificación, texto y aspectos destacados. Crea una reseña nueva o edita una existente |
| Comunidad | Reseñas de otros usuarios |
| Notificaciones | Notificaciones del usuario, con acceso a perfil público y detalle de carrera |
| Perfil público | Perfil de otro usuario, al que se llega desde sus reseñas |

## Stack tecnológico

- **Lenguaje:** Kotlin 2.4.20
- **UI:** Jetpack Compose (Material 3), sin XML views
- **Arquitectura:** MVVM (`ViewModel` + `StateFlow` + `State` inmutable por pantalla), con capas DTO → data source → repositorio
- **Inyección de dependencias:** Dagger Hilt
- **Red:** Retrofit (conversores Gson y Scalars) + corrutinas
- **Backend:** API REST propia (Node.js, Express, Sequelize y PostgreSQL) para carreras, reseñas y usuarios; Firebase Auth (autenticación) y Firebase Storage (imágenes). Firestore planeado para más adelante
- **Carga de imágenes:** Coil (`coil-compose`)
- **Navegación:** Navigation Compose
- **Build:** Gradle (Kotlin DSL), Android Gradle Plugin 9.3.2 (soporte de Kotlin integrado), KSP, `libs.versions.toml` (Version Catalog)
- **SDK:** `minSdk 26` · `targetSdk 36` · `compileSdk 37`
- **Testing:** JUnit4 + Espresso (aún no utilizados más allá de la plantilla)

## Estructura del proyecto

```
PaceTride/
├── app/
│   ├── src/main/java/com/example/pacetride/
│   │   ├── MainActivity.kt
│   │   ├── BaseAplication.kt              # @HiltAndroidApp
│   │   ├── data/
│   │   │   ├── dtos/                      # DTOs del backend y funciones de mapeo a los modelos de UI
│   │   │   ├── datasource/                # Interfaces de data source (Auth, Storage, Carrera, Resena, Usuario)
│   │   │   │   ├── services/              # Interfaces de Retrofit (@GET, @POST, @PUT, @DELETE)
│   │   │   │   └── impl/                  # Implementaciones de los data sources sobre Retrofit
│   │   │   ├── repository/                # AuthRepository, CarreraRepository, ResenaRepository, UsuarioRepository, etc.
│   │   │   ├── local/                     # Providers de datos de ejemplo (en retirada)
│   │   │   └── injection/                 # Módulos de Hilt (Firebase, Retrofit y servicios)
│   │   ├── navigation/
│   │   │   ├── AppNavigation.kt           # NavHost y definición de rutas
│   │   │   └── NavigationLogic.kt
│   │   └── ui/
│   │       ├── screens/                   # Una carpeta por pantalla: Screen.kt, ViewModel.kt, State.kt, components/
│   │       └── theme/
│   ├── src/main/res/                      # drawables, strings, colores, iconos, xml/network_security_config.xml
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
- Un proyecto de Firebase configurado con Authentication (email/contraseña) y Storage habilitados, y `google-services.json` en `app/`
- El backend REST de PaceTride (Node.js + Express + PostgreSQL) en ejecución en el puerto `3000`

## Instalación y ejecución

```bash
git clone https://github.com/Sotelord/PaceTride.git
cd PaceTride
./gradlew assembleDebug
```

O ábrelo directamente en Android Studio y ejecuta `app` sobre un emulador/dispositivo con Android 8.0 (API 26) o superior. Asegúrate de tener tu propio `google-services.json` de Firebase antes de compilar.

### Conexión con el backend

1. Levanta el backend (puerto `3000`) antes de abrir la app.
2. La URL base se define en el módulo de Hilt donde se crea la instancia de Retrofit:
    - **Emulador de Android Studio:** `http://10.0.2.2:3000/`
    - **Dispositivo físico:** `http://<IP de tu computador en la red Wi-Fi>:3000/`, con el teléfono y el computador en la misma red.
3. El tráfico HTTP sin cifrar se permite mediante `res/xml/network_security_config.xml`, referenciado en el `AndroidManifest.xml`. Si usas la IP de tu computador, agrégala allí o habilita `cleartextTrafficPermitted` en la configuración base.

> **Nota sobre Android 17:** con `targetSdk 37`, Android bloquea el acceso a direcciones de red local (como `10.0.2.2`) si la app no tiene el permiso `ACCESS_LOCAL_NETWORK`, y la petición termina en un `SocketTimeoutException`. Por eso el proyecto usa `targetSdk 36`. Si subes a 37, declara y concede ese permiso.

## Roadmap sugerido

- [ ] Asociar el usuario de Firebase Auth con el usuario del backend, y retirar el id de usuario fijo.
- [ ] Migrar a la API las pantallas y datos que aún usan los providers locales (Comunidad, Notificaciones, Mis carreras, próximas carreras e historial del perfil).
- [ ] Implementar el flujo de inscripción a una carrera (`Screen.Inscribeme`).
- [ ] Completar las pantallas pendientes: Recuperar contraseña, Configuración, Editar perfil, Comentarios, Config. de perfil público.
- [ ] Inyectar las interfaces de los data sources en los repositorios, y añadir implementaciones de Firestore.
- [ ] Agregar tests reales de `ViewModel`s (lógica de estado) y de UI.