# Prueba Tecnica Mercado Libre 
### Determinar si es humano mutante
- Fecha creacion: 25/02/2022
- Autor: Angel Marin Castrillon
- Email: castrillon89@gmail.com
- Direccion: https://github.com/castrillon899/exam-meli-mutante.git branch master

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
| Validar si la secuencias enviada de dnas, los item de dna en su longitud son >=4 para determinar si es humano mutante | test10HumanSequenceLessThan4 |
| ------ | ------ |

### Arquitectura de solucion: 


![REPOSITORIO_ARQUITECTURA](https://github.com/[username]/[reponame]/blob/[branch]/image.jpg?raw=true)

---

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




## _Determinar si humano mutante_


[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Dillinger is a cloud-enabled, mobile-ready, offline-storage compatible,
AngularJS-powered HTML5 Markdown editor.

- Type some Markdown on the left
- See HTML in the right
- ✨Magic ✨

## Features

- Import a HTML file and watch it magically convert to Markdown
- Drag and drop images (requires your Dropbox account be linked)
- Import and save files from GitHub, Dropbox, Google Drive and One Drive
- Drag and drop markdown and HTML files into Dillinger
- Export documents as Markdown, HTML and PDF

Markdown is a lightweight markup language based on the formatting conventions
that people naturally use in email.
As [John Gruber] writes on the [Markdown site][df1]

> The overriding design goal for Markdown's
> formatting syntax is to make it as readable
> as possible. The idea is that a
> Markdown-formatted document should be
> publishable as-is, as plain text, without
> looking like it's been marked up with tags
> or formatting instructions.

This text you see here is *actually- written in Markdown! To get a feel
for Markdown's syntax, type some text into the left window and
watch the results in the right.

## Tech

Dillinger uses a number of open source projects to work properly:

- [AngularJS] - curl --silent --location --request GET 'https://exam-meli-mutant.herokuapp.com/stats/'
- [Ace Editor] - awesome web-based text editor
- [markdown-it] - Markdown parser done right. Fast and easy to extend.
- [Twitter Bootstrap] - great UI boilerplate for modern web apps
- [node.js] - evented I/O for the backend
- [Express] - fast node.js network app framework [@tjholowaychuk]
- [Gulp] - the streaming build system
- [Breakdance](https://breakdance.github.io/breakdance/) - HTML
to Markdown converter
- [jQuery] - duh

And of course Dillinger itself is open source with a [public repository][dill]
 on GitHub.

## Installation

Dillinger requires [Node.js](https://nodejs.org/) v10+ to run.

Install the dependencies and devDependencies and start the server.

```sh
cd dillinger
npm i
node app
```

For production environments...

```sh
npm install --production
NODE_ENV=production node app
```

## Plugins

Dillinger is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |

## Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantaneously see your updates!

Open your favorite Terminal and run these commands.

First Tab:

```sh
node app
```

Second Tab:

```sh
gulp watch
```

(optional) Third:

```sh
karma test
```

#### Building for source

For production release:

```sh
gulp build --prod
```

Generating pre-built zip archives for distribution:

```sh
gulp build dist --prod
```

## Docker

Dillinger is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 8080, so change this within the
Dockerfile if necessary. When ready, simply use the Dockerfile to
build the image.

```sh
cd dillinger
docker build -t <youruser>/dillinger:${package.json.version} .
```

This will create the dillinger image and pull in the necessary dependencies.
Be sure to swap out `${package.json.version}` with the actual
version of Dillinger.

Once done, run the Docker image and map the port to whatever you wish on
your host. In this example, we simply map port 8000 of the host to
port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart=always --cap-add=SYS_ADMIN --name=dillinger <youruser>/dillinger:${package.json.version}
```

> Note: `--capt-add=SYS-ADMIN` is required for PDF rendering.

Verify the deployment by navigating to your server address in
your preferred browser.

```sh
127.0.0.1:8000
```

## License

MIT

**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>


##Prueba Tecnica MercadoLibre

##Angel Marin 25/05/2022
heroku logs --tail

##PRECONDICIONES
## -----------REQUEST PRUEBAS API MUTANTE--------------

##CURL 1, HUMANO NO MUTANTE
curl --silent --location --request POST 'https://exam-meli-mutant.herokuapp.com/mutant/' `
--header 'Content-Type: application/json' `
--data-raw '{  
    "dna" :
    ["AAGCAA",
     "CTGTAC",
     "TTAAGT",
     "AGAAAG",
     "CCCATT",
     "TCACTT"]
}'

##CURL2 HUMANO MUTANTE
curl --silent --location --request POST 'https://exam-meli-mutant.herokuapp.com/mutant/' `
--header 'Content-Type: application/json' `
--data-raw '{
   
    "dna" :
    ["AAGCAA",
     "CTGTAC",
     "TTAAGA",
     "AGAAAG",
     "CCCATT",
     "TCACTT"]
}'


##CONSULTAR ESTADISTICA DE CONSULTAS AL API MUTANTE
#CURL
curl --silent --location --request GET 'https://exam-meli-mutant.herokuapp.com/stats/'

##ENUNCIADO
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En
alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):
* boolean isMutant(String[] dna); // Ejemplo Java

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

### No-Mutante 
A T G C G A 

C A G T G C

T T A T T T

A G A C G G

G C G T C A

T C A C T G


### Mutante
A T G C G A

C A G T G C

T T A T G T

A G A A G G

C C C C T A

T C A C T G



Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales​, de forma oblicua, horizontal o vertical.
Ejemplo (Caso mutante):

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};


En este caso el llamado a la función isMutant(dna) devuelve “true”.
Desarrolla el algoritmo de la manera más eficiente posible.
Desafíos:


## Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.

## Nivel 2:
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

## Nivel 3:
Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN.

Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las
verificaciones de ADN: {"count_mutant_dna" : 40, "count_human_dna" : 100, "ratio" : 0.4 }
Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1
millón de peticiones por segundo).
Test-Automáticos, Code coverage > 80%.


docker run -d \
--name mongodb \
-e MONGO_INITDB_DATABASE=trackdb \
-p 27017:27017 \
mongo
Go to in container and create user

docker exec -it mongodb bash
mongo
use trackdb
db.createUser({user: "user", pwd: "secretPassword",roles: [{ role: 'readWrite', db:'trackdb'}]})
exit
exit


