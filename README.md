# TP-Java-JGraph | 2022
Trabajo práctico integrador para la cátedra de Diseño e Implementación de Estructuras de Datos (UTN FRSF)
Se desarrolló en Java con Java Swing y JGraph 

## Resumen
La aplicación contiene funcionalidad para 2 tipos de usuarios de una línea de transporte: clientes y personal.

> Clientes
Se ofrece la posibilidad de consultar la base de datos del sistema de transporte de una ciudad y comprar boletos para 
recorridos de colectivos que cumplan con sus requerimientos (ruta más corta, viaje más barato, viaje más rápido, etc).
La aplicación ofrece una interfaz donde se puede visualizar los caminos de la ciudad que componen los trayectos (en forma de grafo),
junto con el recorrido del colectivo elegido, la distancia y precio total del transporte.

> Personal
Se permite añadir colectivos y rutas y/o modificar los existentes. Esto incluye agregar contingencias que inhabiliten
temporalmente el uso de ciertas rutas (por ejemplo, por una protesta o manifestación).
Además, se pueden agregar nuevos nodos (paradas) y aristas (rutas, calles) al grafo que compone la ciudad y visualizar esos
cambios directamente.

## Cuestiones de configuración

Para poder correr el programa se debe tener en consideración lo siguiente.

> Configuración de la base de datos

-La base de datos que contiene rutas, recorridos y transporte utiliza *PostgresSQL*.

Para poder conectarse hay que configurar unos parámetros en el código que se encuentran en la clase *DBConnection*, dentro del paquete *died.izaguirre.haulet.tp.dao*.

Dichos parámetros son los atributos de clase:
```
private static final String DRIVER = "org.postgresql.Driver";
private static final String url="jdbc:postgresql://127.0.0.1:5432/postgres";
private static final String user="postgres";
private static final String pw="admin";
```
Ya tienen valores por defecto, pero fueron utilizados para una base de datos local, con el fin de hacer pruebas.

En el primer atributo cargamos el driver de postgresql, no debería modificarse a menos que se utilize otro motor de base de datos.

En el atributo de url va el enlace a la base de datos, con su respectivo protocolo:
```
"jdbc:postgresql://"
```
Seguido del enlace a la bdd con su puerto.

En el atributo *user* y *pw* van, respectivamente, el usuario y contraseña para conectarse con la base de datos.

El archivo para crear la base de dato es: *definicion_sql*
El archivo para insertar datos es: *inserts_datos*

## Integrantes
-Izaguirre, Ezequiel | [GitHub](https://github.com/Totremont).
-Haulet, Tomas       | [GitHub](https://github.com/tomihlt).


