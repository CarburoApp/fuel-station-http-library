# Fuel Stations HTTP Client Library

*Fuel Stations HTTP Client Library* es una librería Java incluida dentro del proyecto *Carburo*, diseñada para facilitar el consumo de servicios REST de precios y estaciones de hidrocarburos. Actualmente, la librería se centra en consumir la API REST proporcionada por el **Ministerio de Transportes, Movilidad y Agenda Urbana (MITMA) de España**, pero está diseñada para poder integrarse con otras APIs, incluso de otros países, en el futuro.

La API oficial se puede consultar en: [PreciosCarburantes REST API](https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes)
El endpoint de ayuda está disponible en: [Help](https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help)

---

## Funcionalidad Principal

La librería permite consumir todos los servicios de la API MITMA relacionados con hidrocarburos, incluyendo:

- **Estaciones terrestres y marítimas** en España
- **Históricos de estaciones** por fecha
- **Filtros** sobre estaciones por:
- Comunidad Autónoma (CCAA)
- Provincia
- Municipio
- Tipo de combustible
- Combinaciones de los anteriores
- **Listados** de:
- Comunidades Autónomas
- Provincias
- Municipios
- Productos petrolíferos

En resumen, se puede obtener información detallada de las estaciones, sus precios y localización, tanto actuales como históricas, aplicando distintos filtros geográficos y de producto.

---

## Arquitectura

- La librería tiene como punto de entrada el **`HTTPClient`**, desde donde se puede seleccionar qué servicios utilizar, ya sea la API MITMA actualmente o cualquier otro cliente HTTP que se integre en el futuro.
- Cliente HTTP centralizado (`SpainMitmaAPIClientAbstractService`) que gestiona la configuración, las peticiones y la conversión de JSON a objetos DTO.
- Cada servicio específico encapsula las llamadas a endpoints concretos, por ejemplo `GetEEESSTerrestresHistoricoService` para estaciones históricas.
- Diseñada para ser **ampliable**, permitiendo integrar nuevas APIs nacionales o internacionales sin necesidad de reescribir la lógica base.

---

## Configuración

La librería utiliza dos ficheros de configuración principales:

1. **`application.properties`**
Permite configurar aspectos generales del cliente, como certificados SSL o parámetros de seguridad.
- Se pueden crear nuevos archivos `application.properties` dentro de la carpeta `/config` del proyecto para sobrescribir los valores por defecto.
- Si no se encuentra un archivo en `/config`, se usan los valores por defecto incluidos en los recursos de la librería.

2. **`endpoints.properties`**
Contiene las URLs base y endpoints específicos de los servicios del MITMA.
- De igual forma, se puede poner un nuevo archivo en `/config` con los mismos nombres para sobrescribir los valores por defecto.
- Esto permite apuntar a entornos alternativos o personalizar endpoints sin modificar la librería.

En ambos casos, modificar estos archivos es opcional; si no se hace, se utilizarán los valores por defecto de la librería.

---

## Extensibilidad

- Se pueden añadir nuevos clientes HTTP extendiendo la lógica de `HTTPClient` y `SpainMitmaAPIClientAbstractService`.
- Permite integrar APIs nacionales o internacionales de hidrocarburos o movilidad.
- Cada clase de servicio puede añadir nuevos endpoints y lógica específica de filtrado o consulta, manteniendo la reutilización de la infraestructura de HTTP y parsing JSON.

---

## Autoría

**Manuel García**

Proyecto: **Carburo**