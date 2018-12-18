# Conversion Doc
Application de Conversion document
## Description
L'application permettra à un utilisateur de convertir un document(pdf,image,ppt,pptx,doc,docx) vers un autre format(pdf,image,ppt,pptx).
Pour réaliser cela l'application composera des services et processus permettant de realiser les taches:
### Services
- Upload un fichier cette service permettant d'uploader de(s) fichier(s) tout en precisant le(s) format(s) de sorti, toute fois on peut associer son email lorsque l'on souhaite être notifier a la fin de la conversion
- Service de téléchargement il permet de telecharger le(s) document(s) apres conversion
- Service Status de(s) document(s) pour un identifant , permet de visualiser les details des documents pour cette identifiant
- service Status de tout les documents
### Processus
les processus dont les evenement interne du systeme
- Processus la conversion des documents et notification de l'utilisateur(facultatif) 
- Processus de suppression des document après 5min de la conversion

## Architecture
![Alt text](https://github.com/ibkis/ConversionDoc/blob/master/images/cloud.png)

# Organisation des données

On utilise un Base de Donnée hybernite volatile c’est-à-dire une fois qu’on redémarre le système les données seront effacer, la raison d’utilisation de ce type de stockage est dû au fait, qu’on efface les données tous les 5 minute, cela nous permettra de n’est pas engendrer des trafique externe (ce qui est couteux en terne d’accée et ressource sur open shift).
Cette base de donnée est construite selon le diagramme de classe suivant :
![Alt text](https://github.com/ibkis/ConversionDoc/blob/master/images/class.PNG)

# Type de Conversion possible
- doc, docx, png, jpeg, jpg, ppt, pptx ===> pdf
- png, Jpg, jpeg,Pdf                   ==> (ppt, pptx)
- pdf, ppt, pptx                       ==> (png,jpg, jpeg)

# Gestion des clients
la gestion des clients est modeliser selon 3 senarios :
## Upload de fichier 
Un client souhaitant convertir un document, passe par le système ou en utilisation le service d'uploade tout en respectant les paramètre d'entrer, une fois le client uploade le fichier alors le système vérifie la conformité du document par rapport à ce qu'il peut traiter lorsque tout est conforme alors le fichier est pris en compte sinon refuser toute fois le client peut être notifier s'il associe son email. 

![Alt text](https://github.com/ibkis/ConversionDoc/blob/master/images/Upload.jpg)
## Status d'un fichier
Après avoir uploade un fichier un identifiant sera associer à ce fichier, ce qui permet au client de voir l'état du document et la télécharger lorsque la conversion est terminée. 

![Alt text](https://github.com/ibkis/ConversionDoc/blob/master/images/details_files.jpg)

## Action Interne du Systeme
Le système exécute en arrière-plan des actions permettant de répondre au client la demande, dès qu’ un fichier est uploader il est mis dans une file d'attente, ainsi le processus de conversion sera notifier de la présence d'un fichier à traiter, ce processus  prend le fichier en question est la convertie au format attendu et notifie le processus de suppression qui a son tour supprimer le fichier après 5 minute, le processus conversion ne s'endorme que si la file est vide de même que le processus de suppression par contre lui seulement si la base est vide.  

![Alt text](https://github.com/ibkis/ConversionDoc/blob/master/images/Processus%20INterne.jpg)



# Boite à Outils
Le projet est réalisé sous Java EE en employant le Framework Springs facilite le développement des application web avec micro-service, 
et Maven pour les dépendances c'est à dire les Library a utiliser
car certaine Library dépend d'un ensemble de Library externe alors cela sera fait de manière abstraite pour nous.

la boite a outils est composer de: 

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
<dependency>
    <groupId>args4j</groupId>
    <artifactId>args4j</artifactId>
    <version>2.32</version>
</dependency>
<dependency>
    <groupId>org.docx4j</groupId>
    <artifactId>docx4j</artifactId>
    <version>3.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.12</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.12</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-scratchpad</artifactId>
    <version>3.12</version>
</dependency>
<dependency>
    <groupId>fr.opensagres.xdocreport</groupId>
    <artifactId>org.apache.poi.xwpf.converter.pdf</artifactId>
    <version>1.0.5</version>
</dependency>
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version>5.5.6</version>
</dependency>
<dependency>
    <groupId>fr.opensagres.xdocreport</groupId>
    <artifactId>org.odftoolkit.odfdom.converter.pdf</artifactId>
    <version>1.0.5</version>
</dependency>
<dependency>
    <groupId>fr.opensagres.xdocreport</groupId>
    <artifactId>fr.opensagres.xdocreport.itext.extension</artifactId>
    <version>1.0.5</version>
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
# Configuration
## Docx4j
```properties
docx4j.Log4j.Configurator.disabled=true
```
## Log4j
```properties
#log4j.rootLogger=debug, stdout, R
log4j.rootLogger=OFF

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout

# Pattern to output the caller's file name and line number.
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] (%F:%L) - %m%n

log4j.appender.R=org.apache.log4j.RollingFileAppender
log4j.appender.R.File=example.log

log4j.appender.R.MaxFileSize=100KB
# Keep one backup file
log4j.appender.R.MaxBackupIndex=1

log4j.appender.R.layout=org.apache.log4j.PatternLayout
log4j.appender.R.layout.ConversionPattern=%p %t %c - %m%n
```
## Email
``` java
 String to = email;//change accordingly
        String from = "ibrahimkarimouseyni.com";
        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("ibrahimkarimouseyniEmail", "password");
                    }
                });
        //compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(ms, "text/html");
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
```
# Developpement
Le développement de ce système sera mis en œuvre, sous le langage java en tout utilisant :
- Springs
- Une base de donnée Hibernet pour le stockage des donnée

# Démo
 ![Alt text](https://github.com/karimouseyni/ConversionDoc/blob/master/images/1.PNG)
 ![Alt text](https://github.com/karimouseyni/ConversionDoc/blob/master/images/2.PNG)
 ![Alt text](https://github.com/karimouseyni/ConversionDoc/blob/master/images/3.PNG)
 ![Alt text](https://github.com/karimouseyni/ConversionDoc/blob/master/images/4.PNG)
