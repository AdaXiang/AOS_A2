# Laboratorio sobre Spring Boot:

- En este ejercicio, crearás una aplicación Spring Boot simple. 

**Parte 1 - Aplicación Web Simple**

1. Utilizando Spring Tool Suite o [https://start.spring.io](https://start.spring.io), crea un nuevo proyecto de Spring Boot.

   - Utiliza Maven.  Todas las instrucciones del laboratorio se basan en Maven.
   - Utiliza las últimas versiones estables de Boot y Java. Estas instrucciones han sido probadas con Java 11 y Boot 2.7.x.
   - Utiliza empaquetado JAR.
   - Utiliza los valores que prefieras para grupo, artefacto, paquete, descripción, etc. pe. `es.unex.aos`
   - Selecciona las siguientes dependencias: **Web, Thymeleaf, JPA, HSQLDB, data-rest y Actuator**.

2. Descomprime el proyecto creado e impórtalo en tu editor favorito. Crea una clase java que vamos a denominar controlador en el paquete base:

   
   - Crea una carpeta denominada `controllers`
   - Dale el nombre que desees al controlador, p.e. `HelloController.java` 
   - Anota el controlador con `@Controller`.


```java 
package es.unex.aos.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    String sayHello(){
        return "hello";
    }
    
}
```


3. Crea un nuevo método en el controlador:

   - Dale el nombre que desees al método. Haz que retorne una cadena. No necesita parámetros.
   - Anota el método con `@GetMapping("/")`.
   - Haz que el método retorne la cadena "hello". Nota ."hello" será el nombre de la página html que devolverá el controlador. Ésta página se definirá en el siguiente paso.

4. Si aún no existe, crea una nueva carpeta bajo `src/main/resources` llamada "templates".

5. Crea un nuevo archivo en la carpeta "templates" llamado "hello.html". Coloca las palabras "Hola desde Thymeleaf" (o cualquier otro marcado que desees) dentro del archivo.

6. Guarda todo tu trabajo. Ejecuta tu aplicación.

   - Si estás trabajando en Spring Tool Suite, simplemente haz clic con el botón derecho en la aplicación / Run As / Spring Boot App.
   - Si estás trabajando en otro IDE, ejecuta el método principal que se encuentra dentro de la clase Application principal.
   - Si deseas ejecutarlo desde una línea de comandos, desde la carpeta raíz de la aplicación, ejecuta `mvn spring-boot:run`.

7. Abre un navegador en [http://localhost:8080/](http://localhost:8080/). Deberías ver tu página web.

**Parte 2 - Devolver una respuesta RESTful**

9. Crea una nueva clase Java llamada "Team" en el paquete base "/model" p.e. `es.unex.aos.model`. Dales un campo Long para el id y campos String para el nombre, la ubicación y la mascota (o cualquier otra propiedad que desees). Genera "getters" y "setters" para todos los campos. Guarda tu trabajo.

```java
package es.unex.aos.lab1.model;

public class Team {
    private long id;
    
    private String name;
    private String location;

    public Team(String string, String string2) {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    
}
```


10. Crea un nuevo controlador llamado "TeamController". Anótalo con `@RestController`, es decir, crea la clase `TeamController.java` en la carpeta `controllers`.

11. Crea un nuevo método en el TeamController.

   - Dale el nombre "getTeams". Haz que retorne una lista de objetos Team.
   - Anótalo con `@GetMapping("/teams")`.
   - Haz que el método cree una lista de objetos Team. Crea uno o más objetos Team y agrégalos a la lista. Establece las propiedades de los equipos con los valores que prefieras y devuelve la lista. Ejemplo:

   ```java
    @GetMapping("/teams")
    public List<Team> getTeams() {
        List<Team> list = new ArrayList<>();

        Team team = new Team();
        team.setId(0l);
        team.setLocation("Harlem");
        team.setName("Globetrotters");
        list.add(team);
        
        team = new Team();
        team.setId(1l);
        team.setLocation("Washington");
        team.setName("Generals");
        list.add(team);
        
        return list;
    }
   ```

12. Guarda todo tu trabajo. Detén la aplicación si ya se está ejecutando y vuélvela a iniciar. Abre [http://localhost:8080/teams](http://localhost:8080/teams). Deberías ver una respuesta JSON con los datos de tus equipos.

**Parte 3 - Crear Repositorios de Spring Data JPA**

13. Volvemos a la clase `Team`. Agregua las anotaciones JPA necesarias: La clase debe estar anotada con `@Entity`, el id debe estar anotado con `@Id` y `@GeneratedValue`.

14. Crea una nueva interfaz llamada "TeamRepository" en la carpeta `repository`. Haz que extienda `CrudRepository<Team, Long>`.
   - Asegúrate de crear una interfaz, no  una clase.

15. Abre la clase de configuración principal/aplicación (aquella anotada con `@SpringBootApplication`). Usa `@Autowired` para inyectar una variable miembro de tipo TeamRepository. Puede nombrar la variable como desee (por ejemplo "teamRepository"?).

`@Autowired	TeamRepository teamRepository;`

16. Agrega la lógica para poblar inicialmente la base de datos: Agrega un método público void llamado "init". Anótalo con `@PostConstruct`. Copia y pega el código de creación de equipos desde su controlador a este método, pero en lugar de devolver una lista de equipos, llame al método `saveAll()` en el repositorio. Además, elimine cualquier valor establecido para los IDs de equipo. Código de ejemplo:

   ```java
    @PostConstruct
    public void init() {
        List<Team> list = new ArrayList<>();

        Team team = new Team();
        team.setLocation("Harlem");
        team.setName("Globetrotters");
        list.add(team);
        
        team = new Team();
        team.setLocation("Washington");
        team.setName("Generals");
        list.add(team);

        teamRepository.saveAll(list);
    }
   ```

Básicamente estamos indicando que al iniciar la aplicación, después de su construcción, se ejecute el método `init()` que permite iniciar la base de datos con cierta información, en este caso con una lista de `Teams`-


17. Regresa a la clase TeamController. Usa `@Autowired` para inyectar una variable TeamRepository. Puede nombrar la variable como desee (por ejemplo,  "teamRepository").

`@Autowired	TeamRepository teamRepository;`

18. Modifica la lógica en su método del controlador para simplemente devolver el resultado del método findAll() del repositorio:

   ```java
    @GetMapping("/teams")
    public Iterable<Team> getTeams() {
        return teamRepository.findAll();
    }
   ```
En este método estamos recuperando la información de un repositorio que puede estar asociado a una determinada base de datos.

19. Agrega esta propiedad a `application.properties` para controlar cómo la implementación JPA (Hibernate) debe manejar el esquema de la base de datos:

   ```
   spring.jpa.hibernate.ddl-auto = update
   ```

20. Guarde todo el trabajo. Detenga la aplicación si ya se está ejecutando y vuelva a iniciarla. Abra [http://localhost:8080/teams](http://localhost:8080/teams). Debería ver una respuesta JSON con los datos de sus equipos.

**Parte 4 (Opcional) - Crear un endpoint de Equipo único**

21. Regrese al TeamController y añade un método que devuelva un único equipo según su ID.
   - Puede nombrar el método como desee. Sugerencia: `getTeam`.
   - El tipo de retorno debe ser un objeto Team.
   - Utilice una anotación `@GetMapping` para asignar este método al patrón `/teams/{id}`.
   - Defina un parámetro llamado `id` de tipo Long anotado con `@PathVariable`.
   - Lógica: devuelva el resultado del método findById(id).get() del repositorio (el método findById() devuelve un "Optional" de Java 8 y get() simplemente devuelve el objeto Team real.

```java
   @GetMapping("/teams/{id}")
    Team getTeam(@PathVariable Long id){

        return teamRepository.findById(id).get();
    }
```

22. Guarda todo el trabajo. Deten la aplicación si ya se está ejecutando y vuelva a iniciarla. Use URLs como [http://localhost:8080/teams/1](http://localhost:8080/teams/1) o [http://localhost:8080/teams/2](http://localhost:8080/teams/2) para obtener resultados de los equipos individuales.

**Parte 5 - Agregar Jugadores**

23. Agregue una nueva clase llamada Player. Añade campos para id, nombre y posición. El id debe ser de tipo Long, y los otros campos pueden ser cadenas (Strings). Genere getters/setters para cada campo. Añade la anotación `@Entity` a la clase y las anotaciones `@Id` y `@GeneratedValue` al campo id. Puede desear agregar un constructor personalizado para facilitar la creación de un objeto Player proporcionando cadenas de nombre y posición (si lo hace, asegúrese de conservar un constructor predeterminado). Guarde su trabajo.

24. Abra la clase Team. Añade un conjunto (Set) de objetos Player llamado "players". Genere getters y setters. Anote el conjunto con `@OneToMany(cascade = CascadeType.ALL)` y `@JoinColumn(name = "teamId")`. Puede desear agregar un constructor personalizado para facilitar la creación de un objeto Team proporcionando cadenas de nombre, ubicación y un conjunto de jugadores (si lo hace, asegúrese de conservar un constructor predeterminado). Guarde su trabajo.

25. Vuelva al archivo de configuración principal/aplicación y modifique la lógica de población de equipos para agregar algunos jugadores a cada equipo. Aquí hay una implementación de ejemplo:

   ```java
    @PostConstruct
    public void init() {
        List<Team> list = new ArrayList<>();

        Set<Player> set = new HashSet<>();
        set.add(new Player("Big Easy", "Showman"));
        set.add(new Player("Buckets", "Guard"));
        set.add(new Player("Dizzy", "Guard"));
        
        list.add(new Team("Harlem", "Globetrotters", set));
        list.add(new Team("Washington", "Generals", null));

        teamRepository.saveAll(list);
    }
   ```

26. Guarde todo el trabajo. Reinicie la aplicación. Abra [http://localhost:8080/teams](http://localhost:8080/teams) para ver los jugadores.

**Parte 6 (Opcional) - Agregar Soporte para XML**

27. Cuando sea posible, las aplicaciones REST deben devolver el tipo de contenido solicitado por el cliente. Spring Boot puede admitir fácilmente formatos como XML basados en dependencias (JAXB 2, Jackson) en el classpath. Desafortunadamente, las versiones de Java posteriores a 8.x ya no incluyen automáticamente JAXB 2, por lo que el soporte para XML requiere un poco de esfuerzo por nuestra parte al agregar dependencias de Jackson (recomendado) o JAXB 2. Abra su POM y añade dependencias para EITHER Jackson (recomendado):

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

*O* JAXB 2:

```xml
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
</dependency>
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.0</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>javax.activation</groupId>
    <artifactId>javax.activation-api</artifactId>
    <version>1.2.0</version>
</dependency>
```

28. Si está utilizando JAXB2, también debe agregar una anotación `@XmlRootElement` a su clase Teams.

29. Agrega estas propiedades a su archivo application.properties para permitir el control del formato deseado a través de una extensión o sufijo o parámetro de consulta:

```properties
# Una URL que termina con ?format=xml puede especificar el tipo de medio solicitado:
spring.mvc.contentnegotiation.favor-parameter=true

```

30. Guarda todo el trabajo. Puedes ver el resultado

**json**

- `curl -X GET -H Accept:application/json "http://localhost:8080/teams"` 
- `curl -X GET -H Accept:application/json "http://localhost:8080/teams" | jq`
- `curl -i http://localhost:8080/teams?format=json`

**xml**

- `curl -X GET -H 'Accept: application/xml' -i http://localhost:8080/teams`
- `curl -i http://localhost:8080/teams?format=xml`

   - Ten en cuenta que no todos los navegadores muestran JSON y XML correctamente; considera buscar complementos.
   - Ten en cuenta que JAXB 2 tendrá problemas con el endpoint `/teams`, ya que el tipo devuelto no está anotado con `@XmlRootElement`, una de las razones por las que muchos prefieren Jackson.
   - Ten en cuenta que el encabezado Accept (`Accept=text/xml` o `Accept=application/json`) también se puede utilizar para especificar el tipo de contenido deseado, pero es un poco más complicado de configurar desde el navegador. Utiliza `curl` o `postman` para realizar las pruebas

**Parte 7 - Agregar Spring Data REST**

31. Abr3 el archivo POM del proyecto. Agrega una dependencia del grupo org.springframework.boot y el artefacto spring-boot-starter-data-rest. Guarda su trabajo.

```xml  
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```

32. Abre TeamRepository. Añade una anotación `@RestResource(path="teams", rel="team")` a la interfaz.

33. Crea una nueva interfaz llamada PlayerRepository. Haz que extienda `CrudRepository<Player, Long>`. (¡Asegúrese de crear esto como una interfaz, no como una clase)! Agrega una anotación `@RestResource(path="players", rel="player")` a la interfaz.

34. Abre `TeamController.java`. Comenta la anotación `@RestController` en la clase. (Usaremos Spring Data REST para implementar el controlador, por lo que no queremos que este controlador existente interfiera).


35. Guarda todo el trabajo. Reinicia la aplicación. Abra [http://localhost:8080/teams](http://localhost:8080/teams) para ver a los jugadores. Tenga en cuenta que, según el navegador que esté utilizando, **puedes navegar por los enlaces de jugadores y equipos**. 

   - Ten en cuenta que Spring Data REST actualmente no admite XML, por lo que el formato=xml.

Si ha llegado hasta este punto, ¡enhorabuena, ha completado el ejercicio!

**Parte 8 (Opcional) - Explorar los Puntos de Actuador**

36. Una de las dependencias que especificamos fue **Actuator**. Añade automáticamente algunos endpoints útiles a nuestra aplicación web. Abra los siguientes con un navegador:
  
    - [/actuator/health](http://localhost:8080/actuator/health)

37. Observa que algunos otros puntos de **Actuator** no están habilitados de forma predeterminada. Intente lo siguiente; no funcionarán, pero observe detenidamente la razón: exponer estos puntos podría ser un riesgo de seguridad:
  - [/actuator/beans](http://localhost:8080/actuator/beans)
  - [/actuator/configprops](http://localhost:8080/actuator/configprops)
  - [/actuator/autoconfig](http://localhost:8080/actuator/env)

38. Habilite estos puntos de Actuator agregando la siguiente configuración a su archivo `application.properties` (guarde su trabajo y reinicie):
  - management.endpoints.web.exposure.include=info,beans,configprops,mappings,env

39. Explore [/actuator/mappings](http://localhost:8080/actuator/mappings). Este es útil para depurar aplicaciones web. Investiga y observa dónde se configuran los @GetMappings que estableció anteriormente.

**Parte 9 (Opcional) - DevTools**

40. A menudo, al desarrollar, necesitamos ejecutar una aplicación, hacer algunos cambios y luego reiniciar. La dependencia Spring Boot "DevTools" puede facilitar las cosas al reiniciar automáticamente cuando se detectan cambios (aunque no es tan completa como otras herramientas como JRebel). Añade la siguiente dependencia:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

41. Mientras la aplicación se esté ejecutando, haz un pequeño cambio inocente en el código (como un comentario o espaciado). Observa que, según el cambio, DevTools reiniciará su aplicación.

