# Punchclock-backend

Folgende Schritte sind notwendig um die Applikation zu erstellen und zu starten:

1. Stellen Sie sicher, dass OpenJDK 11 oder höher installiert und JAVA_HOME korrekt gesetzt ist.
2. Installieren Sie (falls noch nicht vorhanden) Apache Maven 3.8.1 oder höher
3. Wechseln Sie auf der Kommandozeile in den Ordner dieser Applikation.
   `./puncher-backend`
4. Starten Sie die Applikation mit

```shell script
./mvnw compile quarkus:dev
```

# Abweichungen zur Planung

Es gibt eine Abweichung vom Klassendiagramm in der LBB und zwar habe ich beim User ein Attribut token hinzugefügt,
dieses wird aber nicht persistiert und musste wegen des Frontends hinzugefügt werden.

# Small Bug in Application

Manchmal gibt es einen Fehler mit der CORS-Policy im backend und dann funktioniert nichts mehr. Der Fehler entsteht
durch Quarkus, jedoch konnte diesen nicht beheben. Falls also das Frontend gar nicht mehr funktioniert und in der
Konsole vom puncher-backend errors wegen der CORS-Policy ausgegeben werden sollte die Backend-Applikation neu gestartet
werden. Benutzen sie:

```shell script
1.  ctrl+c
2. ./mvnw compile quarkus:dev
``` 

# +Dienste:

Swagger API: http://localhost:8080/q/swagger-ui/
