# PresupuestosEdge: Gestor de Finanzas Personales Inteligente

PresupuestosEdge es una aplicación Android nativa diseñada para ayudarte a gestionar tus finanzas personales de manera eficiente e inteligente. Combina la persistencia de datos local para un acceso rápido y offline ("Edge") con la sincronización opcional en la nube y funcionalidades de Inteligencia Artificial para ofrecerte información valiosa sobre tus hábitos de gasto.

## Características Principales (Planificadas/Implementadas)

*   **Registro de Transacciones:** Añade, visualiza, edita y elimina fácilmente tus ingresos y gastos.
*   **Categorización:** Organiza tus transacciones en categorías personalizables.
*   **Persistencia Local (Edge):** Todas tus transacciones se almacenan de forma segura en el dispositivo usando Room, permitiendo el acceso incluso sin conexión a internet.
*   **Sincronización con la Nube (Cloud):**
    *   (Opcional/Futuro) Sincronización de datos con Firebase Firestore para respaldo y acceso multidispositivo.
*   **Alertas Inteligentes (IA):**
    *   Análisis en el dispositivo mediante TensorFlow Lite para proporcionar información y alertas sobre tus patrones de gasto, posibles anomalías o recordatorios de presupuesto.
*   **Visualización de Datos:**
    *   (Futuro) Gráficos y resúmenes para entender mejor tus finanzas.
*   **Gestión de Presupuestos:**
    *   (Futuro) Establece presupuestos para diferentes categorías y haz un seguimiento de tu progreso.

## Arquitectura y Tecnologías Utilizadas

Esta aplicación sigue los principios de la arquitectura moderna de Android y utiliza las siguientes tecnologías y bibliotecas:

*   **Lenguaje:** Kotlin
*   **UI:** Jetpack Compose para una interfaz de usuario declarativa y moderna.
*   **Arquitectura:** MVVM (Model-View-ViewModel)
*   **Gestión de Dependencias:** Hilt (Dagger) para la inyección de dependencias.
*   **Persistencia de Datos (Local):** Room Persistence Library.
*   **Tareas en Segundo Plano:** WorkManager para tareas como la sincronización de datos.
*   **Inteligencia Artificial (On-Device):** TensorFlow Lite para ejecutar modelos de Machine Learning directamente en el dispositivo.
*   **Componentes de Jetpack:**
    *   ViewModel
    *   LiveData (o StateFlow)
    *   Navigation Compose
    *   Lifecycle
*   **Conectividad (Cloud - Futuro/Opcional):**
    *   Firebase Firestore (para base de datos en la nube)
    *   Firebase Authentication (para autenticación de usuarios)

## Estructura del Proyecto (Módulos Principales)

*   **`app`:** Módulo principal de la aplicación Android.
    *   **`ui`:** Contiene los Composables de Jetpack Compose, ViewModels y la lógica de la interfaz de usuario.
    *   **`data`:** Incluye Repositorios, DataSources (Room DAOs, APIs de red si las hubiera), y modelos de datos (Entities).
    *   **`domain`:** (Opcional, si se sigue Clean Architecture) Casos de uso y lógica de negocio pura.
    *   **`di`:** Módulos de Hilt para la inyección de dependencias.
    *   **`worker`:** Implementaciones de WorkManager.
    *   **`ml`:** (Sugerido) Código relacionado con la carga y ejecución de modelos de TensorFlow Lite.
    *   **`utils`:** Clases de utilidad.

## Estado Actual del Proyecto

*  El proyecto Presupuestos Edge ha completado la implementación de su arquitectura Híbrida (Edge Computing + Cloud).
* Todos los componentes principales están codificados e integrados.
*  Persistencia Local (Edge): El SGB Room (SQLite) está totalmente operativo,
* permitiendo el registro y recuperación de transacciones con latencia cero.
*  Análisis Inteligente (Edge IA): La infraestructura de TensorFlow Lite (TFLite) está configurada,
* permitiendo la ejecución de la lógica de analyzePatterns para alertas de gasto directamente en el dispositivo.
*  Sincronización Cloud: El sistema de respaldo asíncrono con WorkManager (SyncWorker.kt) está implementado y listo
* para enviar datos a Firebase Firestore.
*  Arquitectura: La capa de presentación (MVVM) utiliza Hilt para la inyección de dependencias,
* lo que garantiza un código desacoplado y testeable.

    