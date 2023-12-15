# Model-View-Controller Minta-project MVC

Az objektumorientált programozásban az ismétlődő problémákra általános megoldási sémákat alkalmazunk. Ezek a minták hozzájárulnak az alkalmazások struktúrájának, rugalmasságának és karbantarthatóságának növeléséhez.

## Singleton minta:
A Singleton minta arra irányul, hogy biztosítsa, hogy egy adott osztályból csupán egy példány létezzen, és ezt egy globális ponton keresztül lehessen hozzáférni. Különösen hasznos, ha egy osztályból csak egy példányra van szükségünk, például konfigurációs beállítások kezelésekor.

## Factory Method minta:
A Factory Method minta egy interfészt meghatároz, de a példányosítást a származtatott osztályokra bízza. Ezzel lehetővé válik egy algoritmus létrehozása az alkalmazás futása közben, miközben nem szükséges módosítani az algoritmus használatát.

## Observer minta:
Az Observer minta egy eseményalapú rendszert valósít meg, ahol az egyik objektum (a megfigyelt) változása esetén a többi objektum (a megfigyelők) értesítést kapnak és reagálnak.
Például grafikus felhasználói felületek esetén használható, hogy az egyes felületi elemek automatikusan frissüljenek, amikor az adatok megváltoznak.

## Decorator minta:
A Decorator minta lehetővé teszi egy objektum funkcionalitásának bővítését anélkül, hogy megváltoztatnánk az eredeti objektumot.
Ezáltal rugalmasabbá és könnyen kiterjeszthetővé válik a rendszer.

## Strategy minta:
A Strategy minta egy algoritmus cseréjét teszi lehetővé egy másikkal anélkül, hogy az ügyfélnek észre kellene vennie a változást.
Hasznos, ha különböző algoritmusokat akarunk kicserélni egy adott feladat végrehajtása során.

## Command minta:
A Command minta egy parancsot ábrázol, amit lehet tárolni, továbbítani és visszavonni.
Ez lehetővé teszi az akciók objektummá történő átalakítását, támogatva ezzel a parancsok parametrizálását és későbbi végrehajtását.

## Composite minta:
A Composite minta lehetővé teszi az objektumok és az objektumok hierarchiájának kezelését egységesen.
Például egy fájlrendszer, ahol mind a fájlok, mind a könyvtárak kezelhetők ugyanazon interfészen keresztül.
Az MVC egy szoftvertervezési minta, amelyet gyakran alkalmaznak a grafikus felhasználói felületek (GUI) fejlesztéséhez, de alkalmazható más típusú alkalmazásokban is. A minta három fő komponenst definiál: Model (Modell), View (Nézet) és Controller (Vezérlő).

## Model (Modell):
A Model felelős az alkalmazás üzleti logikájának és adattárolásának kezeléséért.
Az adatok manipulációját, frissítését és azok állapotának nyomon követését végzi.
A Model nem tartalmaz semmilyen felhasználói felületi logikát vagy közvetlen kapcsolatot a felhasználóval.

## View (Nézet):
A View a felhasználói felületet abárázolja, és az adatokat jeleníti meg a felhasználónak.
Felelős a felhasználói interakciók érzékeléséért (például gombok lenyomása, egérkattintások stb.).
Nem tartalmaz üzleti logikát vagy adatmanipulációt; csupán a felhasználói felület megjelenítéséért felelős.

## Controller (Vezérlő):
A Controller közvetíti a kommunikációt a Model és a View között.
Fogadja a felhasználói kapcsolatokat a View-től, és ezeket továbbítja a Modelnek a szükséges műveletek végrehajtásához.
Felelős a felhasználói kapcsolatok feldolgozásáért és a megfelelő műveletek meghívásáért a Modelben.
A fő elképzelés az, hogy a Model felelős az adatokért és a logikaért, a View a megjelenítésért, míg a Controller az interakciókért és a vezérlésért. A különválasztásuk segít abban, hogy az alkalmazás strukturáltabb és könnyen karbantartható legyen. Továbbá, ha például meg kell változtatni a felhasználói felületet, de az üzleti logika változatlan marad, vagy fordítva, a változtatásokat könnyen el lehet végezni az egyik rétegen anélkül, hog
