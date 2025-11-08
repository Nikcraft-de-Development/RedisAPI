# 🚀 Wie benutze ich die API?
--- 

## 🪙 Währungen verwalten
Um Spieler entweder Coins oder Spielzeit hinzuzufügen oder zu entfernen, musst die mit der Währungen verbundenen Klassen aufrufen, z.B. "Coins, OnTime", usw...

Hier ein kleines Beispiel wie du Spielern Coins hinzufügen kannst.
```
public void updateCoins(Player player, int amount){
   Coins.add(player.getUniqueId(), amount);
}
```
In diesem Beispiel werden diesem Spieler eine bestimmte Anzahl an Coins gegeben, das ganze funktioniert aber auch anders herum.

---
## ⚙️ Spieler Profile verwalten
Damit Spieler einstellungen Ändern("Verwalten") können, musst du die "ProfileHandler" Klasse aufrufen und darin stehen dir nun allerlei Methoden zur Nutzung zur Verfügung.
Hier ein paar Beispiele:

**Spieler-Profile-Status Ändern:**

```
public void updateProfile(Player player){
    ProfileHandler.setProfileState(player, ProfileState.INGAME);
}
```
In diesem Beispiel wird der ProfilStatus(Nach dem Namen im Freundes-Menü sichtbar), auf Ingame gesetzt das sollte er bei jeden Spielstart und danach dementsprechend wieder nur auf ONLINE.

**Das Profil eines Spielers im ganzen bekommen:**

```
public void getProfile(Player player){
   Profile profile = ProfileHandler.getProfile(player);
}
```
Dabei bekommt man die Ursprungs-Klasse mit den Werten des Spielers zurück und kann diese so direkt verändern

**⚠️ Nicht Empfohlen!**

--- 
## 🚤 Datenbanken mit Redis(Jedis)
Wir speichern unsere Daten hauptsächlich in Redis auf dem Localhost, bei veralterten API's teilweise noch auf MySQL, diese werden jedoch noch in Redis umgeschrieben werden.

Hier ein Beispiel wie du damit arbeiten kannst:

**Core Bekommen**
```
private state RedisCore redisCore;

public RedisBridge(){
   redisCore = new redisCore(true); //localhost
}
```
Der boolean beim erstellen einer neuen instance bezieht sich darauf, ob die Datenbank automatisch auf die Localhost-Server zugreifen soll oder ob dieser extern ist.

**Werte Updaten/Speichern**
```
public static void insertUUID(UUID uuid, Object object){
   core.insert(uuid.toString(), object);
}
``` 
Der große Vorteil von Redis im Vergleich zu MySQL ist, das man nicht mehr Zeilen hinzufügen muss, sondern das dies automatisch passiert wenn der Wert noch nicht in der Datenbank gespeichert ist.

**Werte abrufen**
```
public static String get(Player player){
   Object object = core.get(player.getUniqueId().toString());
   return String.valueOf(object);
}
```

## 😊 Permissions Checken/Zuweisen/Ränge bekommen
Wenn du bei z.B. einer Join-Nachricht, das normale Rang-Format bekommen willst, kannst du das so machen:

**⚠️ Wichtig: Der Rückgabe-Wert ist ein Component kein String**
```
Component component = PlayerHandler.getPlayerName(player.getUniqueID());
```
