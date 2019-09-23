# Code Review / Refactoring exercise
Please review the following code snippet. Assume that all referenced assemblies have been properly included. 

The code is used to log different messages throughout an application. We want the ability to be able to log to a text file, the console and/or the database. Messages can be marked as message, warning or error. We also want the ability to selectively be able to choose what gets logged, such as to be able to log only errors or only errors and warnings.

## 1.	
If you were to review the following code, what feedback would you give? Please be specific and indicate any errors that would occur as well as other best practices and code refactoring that should be done. 

## 2.
Rewrite the code based on the feedback you provided in question 1. Please include unit tests on your code

# Solution of exercise

## Solution written in Spanish.

- En la clase JobLogger no se realiza una correcta conexión a la base de datos, pues no se incluye el nombre del esquema de la base de datos en el cual se encuentra la tabla a la que el handler insertara los registros de los logs.
- Dentro del método LogMessage se declara una variable con el nombre "l", sin embargo se encuentra inicializada como null y dentro de las estructuras de control que marcan el nivel del log se presenta una ```java java.lang.NullPointerException``` debido a que no se inicializa con un valor diferente de null, sino que directamente se concatena un texto a la variable.
- La clase JobLogger usa una gran cantidad de estructuras de control lo cual reduce el bajo acoplamiento del proyecto, lo que mas se recomienda hacer en estos casos es usar el patrón de diseño "Cadena de Responsabilidad que utiliza el API ```java java.logging``` para el manejo de logs de una aplicación.

* La refactorización del código del JobLogger fue modificada de tal manera que cumplir con el "principio de responsabilidad unica" de SOLID, y el uso del patron de diseño "Cadena de responsabilidad" gestionado a traves del API de ```java java.logging``` para el adecuado manejo independiente de los logs y los destinos de almacenamiento para los cuales va dirigido.

## Solution written in English.

- In the JobLogger class, a correct connection to the database is not made, since the name of the database schema in which the table to which the controller will insert the records of the records is not included is included.
- Within the LogMessage method, a variable with the name "l" is declared, however, it is initialized as null and within the control structures that mark the log level a ```java java.lang.NullPointerException``` is presented due because it is not initialized with a different value of null, but rather a text is concatenated directly to the variable.
- The JobLogger class uses a large number of control structures which reduces the low coupling of the project, what is recommended in these cases is to use the design pattern "Chain of responsibility used by the API ```java java.logging``` for handling records of an application.

* The refactoring of the JobLogger code was modified in such a way that it complies with the SOLID "single responsibility principle", and the use of the "Chain of responsibility" design pattern managed through the ```java java.logging``` API for the proper independent management of the logs and storage destinations for which it is intended.