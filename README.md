# Prueba Tecnica Mercado Libre 
### Determinar si es humano mutante

- Fecha creacion: 25/02/2022
- Autor: Angel Marin Castrillon
- Email: castrillon89@gmail.com
- Direccion: https://github.com/castrillon899/exam-meli-mutante.git branch master
- La base de datos se encuentra alojada en nube
- Clonar el repositorio y correr el proyecto
---

### Tecnologias usadas: 
- springBoot: Extension de spring framework que nos permite disponibilizar aplicaciones en cuestion de minutos
- mongoDb: Base de datos que nos permite almacenar y consultar informacion rapidamente, relacionada al caso de uso 
- Git: Versionador de codigo
- Heroku: Usado para el despliegue de la aplicacion en nube
- Java 1.8


---

### Precondiciones: 


| escenario de prueba | prueba unitaria |
| ------ | ------ |
| validar que se envie al menos una secuencia de dna | invalidRequestWithoutDNA |
| validar consistencia de los dna enviados, todos deben tener la misma longitud | invalidRequestInconsistentLength |
| validar que la secuencia de DNA enviada sea valida  | invalidRequestInvalidNitrogenousBase |
| validar que el dna sea humano no mutante | test02TestHumanDNA |
| evitar secuencias dna repetidas en base de datos | test02aTestOnlyOneDNA |
| consultar estadisticas de humano mutante | test03StatsOneHuman |
| encontrar humano mutante en diagonal abajo hacia arriba | test04MutantDiagnonalUPLastPositionValid |
| validar secuencias dna validas minimas para determinar si es humano mutante | test05humanDiagnonalUPOneSequenceAtRowZero |
| encontrar humano mutante en diagonal arriba hacia abajo | test06mutantDiagnonalDOWNRespectiveLastLine |
| encontrar humano mutante en matriz horizontal | test07mutantHorizontal |
| encontrar humano mutante en matriz vertical | test08mutantVertical |
| consulta de estadisticas de humanos mutantes encontrados vs humanos | test09StatsMutantHuman |
| Validar la logitud de los dnas enviados sean >=4 para determinar si es humano mutante | test10HumanSequenceLessThan4 |


###Pruebas 

| API| DESCRIPCION| URL DE CONSUMO | METODO | CURL
| ------ | ------ | ------ | ------ |  ------ |
| /mutant | api que permite determinar si un humano mutante dada una secuencia de dna | https://exam-meli-mutant.herokuapp.com/mutant/ | POST | {   
    "dna" :
    ["AAGCAA",
     "CTGTAC",
     "TTAAGA",
     "AGAAAG",
     "CCCATT",
     "TCACTT"]
} |


## Enunciado
> Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
> contra los X-Men.

> Te ha contratado a ti para que desarrolles un proyecto que detecte si un
> humano es mutante basándose en su secuencia de ADN.
> Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En
> alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):

> En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
>de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
>cuales representa cada base nitrogenada del ADN.

## No-Mutante 
* A T G C G A 
* C A G T G C
* T T A T T T
* A G A C G G
* G C G T C A
* T C A C T G


### Mutante
* A T G C G A
* C A G T G C
* T T A T G T
* A G A A G G
* C C C C T A
* T C A C T G

> Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
> iguales​, de forma oblicua, horizontal o vertical.
> Ejemplo (Caso mutante):
> String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
>En este caso el llamado a la función isMutant(dna) devuelve “true”.
>Desarrolla el algoritmo de la manera más eficiente posible.
>Desafíos:


>  Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.

> Nivel 2:
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine,
Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es
mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el
siguiente formato:
POST → /mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden

> Nivel 3:
Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN.
Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las
verificaciones de ADN: {"count_mutant_dna" : 40, "count_human_dna" : 100, "ratio" : 0.4 }
Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1
millón de peticiones por segundo).
Test-Automáticos, Code coverage > 80%.

