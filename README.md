# Conversion Doc
Application de Conversion document

L'application permettra à un utilisateur de convertir d'un document vers un autre format.
Pour réaliser cela l'application sera décomposée en sous service :
- Service permettant d'uploader des fichier (et notification) + notification du processus de conversion
- Processus la conversion des documents + notification utilisateur(facultatif)
- Processus de suppression des URL après 5min
- Service permettant de télécharger un document converti
- Service permettant de retourner l'état des documents
- service permettant de retourner l'état des documents d'un client

# Dependences
## Template
    
```
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
    <version>3.0.9.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring4</artifactId>
    <version>3.0.9.RELEASE</version>
</dependency>
<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
    <version>2.0.5</version>
</dependency>
```
## Conversion
```
<dependency>
    <groupId>com.aspose</groupId>
    <artifactId>aspose-pdf</artifactId>
    <version>18.9</version>
</dependency>
```
## Envoi email
  ```
    <dependency>
        <groupId>com.sun.mail</groupId>
        <artifactId>javax.mail</artifactId>
        <version>1.6.1</version>
    </dependency>
    ```
    ## Base de donnée
    ```
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```
## Js et Css 
```
<!--Webjars-->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>3.3.7</version>
</dependency>
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>font-awesome</artifactId>
    <version>4.6.1</version>
</dependency>
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>fastclick</artifactId>
    <version>1.0.6</version>
</dependency>
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>2.2.3</version>
</dependency>
<dependency>
    <groupId>org.webjars.bower</groupId>
    <artifactId>nprogress</artifactId>
    <version>0.2.0</version>
</dependency>
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>webjars-locator</artifactId>
    <version>0.30</version>
</dependency>        
```       

# développement
Le développement de ce système sera mis en œuvre, sous le langage java en tout utilisant :
- Springs
- Une base de donnée Hibernet pour le stockage des donnée


