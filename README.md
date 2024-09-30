# Snake

Dette prosjektet er en implementering av det klassiske Snake-spillet skrevet i Java. Snake er et morsomt og utfordrende spill der spilleren kontrollerer en slange som må spise mat for å vokse, samtidig som man unngår å kollidere med veggene eller seg selv.

## Om prosjektet

Dette Snake-prosjektet er utviklet som en fri semesteroppgave i INF101 VÅR 2024, og bygger på prinsipper fra Tetris. Prosjektet er laget for å demonstrere min forståelse av Java-programmering og objektorienterte prinsipper. Spillet er bygget ved hjelp av Java Swing for GUI-delen, og følger designprinsippet om Model-View-Controller (MVC) for å organisere koden på en strukturert måte.

### Arkitektur

Prosjektet er delt inn i tre hovedpakker:

- **Model**: Inneholder klassene som representerer spillets logikk og tilstand.
- **View**: Ansvarlig for å tegne spillet grafisk på skjermen.
- **Controller**: Håndterer brukerens input og oppdaterer modellen.

### Hovedklasser

- **SnakeModel**: Representerer tilstanden til Snake-spillet, inkludert slangen, maten og spillbrettet.
- **SnakeView**: Tegner Snake-modellen på skjermen.
- **SnakeController**: Styrer interaksjonen mellom modellen og visningen basert på brukerens input.

## Komme i gang

### Systemkrav

- Java 11 eller nyere
- Maven

For å kjøre prosjektet, trenger du å ha Java installert på datamaskinen din. Klon prosjektet og naviger til prosjektmappen, deretter kan du bygge og kjøre det ved å bruke følgende kommandoer:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="your.package.name.SnakeMain"
