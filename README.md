# 🚀 Core Server API 1.21.8
Unsere API enthält alle möglichen Technologien und Utils um die Arbeit am Server zu verbessern und möglich zu machen. Enthalten sind ScoreBoard, Tablist und Datenbank APIs.

Damit du manche Commands funktionieren Ingame sehen kannst wird das NcEssentials Plugin benötigt.

## Dependency hinzufügen

Wir benutzen als Compiler immer Gradle und benutzen gerade Jitpack um die dependencies in Projekt einzubinden.

### Jitpack hinzufügen
```
repositories {
    mavenCentral()
    maven {
        name = "jitpack"
        url = "https://jitpack.io"
    }
}
```
### API hinzufügen
```
dependencies {
    compileOnly("com.github.Nikcraft-de-Development:RedisAPI:1.0.3") <!-- ggf. Version ersetzen --!>
}
```

--- 
Letzte aktualisierung 21.11.2025 | 14:44 Uhr