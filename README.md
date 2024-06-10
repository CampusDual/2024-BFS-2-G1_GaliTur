## Default application users

By default the application provides two users. Adapt as needed:

 - Admin:
    - Role: `Administrator`
    - Username: `admin`
    - Password: `adminuser`

 - Demo:
    - Role: `User`
    - Username: `demo`
    - Password: `demouser`

## Ontimize Boot

- Go to the application folder and run an install:

		mvn clean install -Plocal

### Start only the server:

 - Go to the `cd2024bfs2g1-boot` folder and run the command

		mvn spring-boot:run -Dspring-boot.run.profiles=local

### Run the client alone, outside the spring-boot server

 - Go to the `frontend/src/main/ngx` folder, if you have node and npm installed on your system run the following commands:

		npm install
		npm run start-local

Use the following URL to access the application: [http://localhost:4299](http://localhost:4299)

### Deploy and run client and server together

 - Go to the `cd2024bfs2g1-boot/target` folder and run the command

		java -jar cd2024bfs2g1-boot/target/cd2024bfs2g1-boot.jar --spring.profiles.active=local

Use the following URL to access the application: [http://localhost:8080](http://localhost:8080)

## API-First and Swagger UI

The application adopts the API-first approach using the [OpenAPI](http://www.openapis.org/what-is-openapi) specification.

The REST API is defined using yaml files and, in compile time, the [Ontimize OpenAPI](http://www.ontimize.com/xwiki/bin/view/Ontimize+Boot/OpenAPI+plugin) plugin generates the models and the controller interfaces that must be implemented on the application.

The Ontimize OpenAPI plugin also provides the Swagger user interface and it will be available for local and compose environments at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## CSS GENERAL THEMES

- Font-Style: Poppins
- Colors: 
  -  Sidebar Font #d4cdc7
  -  Page Backgroud #e2dbd5
  -  Login and Sidebar gradiant #495635 #909f43
  -  Forms Background #ccc6c0

## Members

- Andrés Gayar Romero
- Borja García Valcarcel
- César Augusto Sención Pérez
- Daniel Salgado Canosa
- Daniel Verdes Martinez
- Darío Rigueira Merino
- David Garcia Vecin
- Diego Casal Carballal
- Francisco Cubelas Vacatello
- Gabriel Vázquez Rodríguez
- Harold Xavier Lugo García
- Joel Expósito Coutinho
- Marcos Puente Paz
- Martín Álvarez Ledo
- Mathias Veira Delbono
- Pablo Aragon Beloso
- Paula Caamaño Mayan
- Rodrigo Arcos Gonçalves
- Silvia Martinez Riobó
- Simón Souto Dopazo
- Vinicios Santos de Queiroz
- Xoán Araújo Gándara

