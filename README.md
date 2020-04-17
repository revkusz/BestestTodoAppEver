# BestestTodoAppEver
Alkalmazások fejlesztése beadandó

## Funkcionális követelmények
-   az alkalmazásba legyen lehetöség felületen keresztül belépni már meglévö felhasználóval
-   Az alkalmazásban lehessen tárolni több felhasználó todo elemeit 
-   Az alkalmazás tudjon todo elemeket több categoriába rendezni melyek lehetnek elöre definiált mindenki által hasnált kategóriák
vagy a user által létrehozott saját kategório
-   Az alkalmazásban lehessen elvégezni feladatokat valamint listázni a még nem elvégzett feladatokat
-   Legyen admin role mely láthatja a user eket felvehet újabb user-t valamint láthaja a sima userek minden elemét és interaktálhat velük
-   Az admin tudjon cserélni role-t a felhasználokon

## Nem funkcionális követelmények

Menjen faszán

## Szakterületi fogalomjegyzék

-   [Angular](https://lmgtfy.com/?q=Angular)
-   [Componensek](https://lmgtfy.com/?q=angular+components)
-   [AJAX](https://lmgtfy.com/?q=ajax)
-   [REST](https://lmgtfy.com/?q=rest)
-   [Spring](https://lmgtfy.com/?q=spring)
-   [JPA](https://lmgtfy.com/?q=jpa)
-   [Hibernate](https://lmgtfy.com/?q=java+hibernate)
-   [Authentikáció](https://lmgtfy.com/?q=auth+spring)

## Szerepkörök

Komáromi Richárd - Backend God, Frontend Trainee
Sára Márk Róbert - Frontend God, Backend Trainee

# Backend fejlesztői környezet
Az alkalmazás fejlesztéséhez az [IntelliJ Ultimate](https://lmgtfy.com/?q=Angular) -et használjuk.
A beállításhoz nincsen más szükség csak a gitrepo ból clone ozni a backend projectet és mivel egy intellij
projectröl van szó ezért már egyből meglehet nyitni a projectet és mehet a fejlesztés.

A projectet buildelni 2 féleképpen lehet.
- mavennel 
- IntelliJ Spring application

Én a fejlesztés során a másodikat használtam mert ez az alapértelmezett az intelliJ-ben és autómatikusan be koncigurálja nekünk
De ha valaki esetleg más fejlesztö környezetben importálja a projectet akkor a már megszokott maven parancsokkal ugyan úgy indítható az alkalmazás
Mavennel az alábbi parancsal lehet futtatni az alkalmazást

`mvn spring-boot:run`

# Adatbázis terv
UML Diagram az adadtbázis strukturáról
![uml diagram](https://github.com/revkusz/BestestTodoAppEver/blob/master/docRes/Screenshot_7.png)

Táblák: 
- USERS ebben a táblában tároljuk a felhasználó neveket és a jelszavakat hashelve
- AUTHORITIES Ebben a táblában tároljuk a ROLE okat a felhasználókhoz
- TODO_ITEM Ebben a táblába vannak tárolva a todo elemek
- CATEGORY Ebben a táblában vannak tárolva a todo item-ek kategóriái 

# Végpontok
## User végpontok

`GET user/all` Admin lekérdezés vissza adja az összes felhasználót

`GET user/changePass?username={username}&newpass={newPassword}` Admin lekérdezés valamely user jelszavát változtatja meg

`POST user/create` Admin lekérdezés melyel létre lehet hozni egy új felhasználót Body: JSON: Username: "username", password: "password"

`user/enable/{username}?enabled={Boolean}` admin lekérdezés kikapcsolni illetve bekapcsolni lehet egy felhasználot
## Role végpontok
`GET role/all` Admin lekérdezés vissza adja az összes lehetséges role-t

`PUT role/add?username={username}` Admin lekérdezés amivel usernek role-t lehet adni Body: [{rolename},{rolename}]

`GET role/{username}` lekérdezés mely ha admin vagyunk vissza adja az adott user role-ját. ha nem vagyunk admin akkor csak a sajátunkra tér vissza adatokkal.

## Kategória végpontok

`GET category/all` Admin lekérdezés vissza adja az összes kategóriát

`GET category/all/{username}` vissza adja az adott user kategóriáit ha admin vagy akárkiét ha nem akkor csak a sajátodat

`GET category/{id}` id alapján tér vissza egy kategóriával amihez van jogosultságunk 

`POST category` egy kategórát tudunk hozzá adni jogosultság szerint userekhez body: lásd CategoryDto

`DELETE category/{id}` egy kategóriát tudunk törölni jogosultság szerint

`PUT category/{id}` egy kategóriát tudunk módosítani jogosultság szerint

## Todo List végpontok

`GET todo/all` Admin lekérdezés vissza adja az összes todo elemet

`GET todo/all/{username}` jogosultság alapján vissza adja egy user todo listáit

`GET todo/all/{username}/{category}` jogosultság alapján vissza adja kategória szerint az adott user todo elemeit

`POST todo` hozzáad egy todo elemet body: lásd todoItemDto

`DELETE todo/{id}` töröl egy adott id ju todo elemet jogosultság szerint.

`GET todo/{id}` Vissza ad egy id ju todo elemet jogosultság szerint

`PUT todo/{id}` frissit egy adott id ju elemet body lásd  todoItemDto

# Known issues
- Az olyan lekérdezésenkél melyeknél owner is van tehát user is van társítve a username hez a jelszó hashel ve is küldésre kerül
- Vannak olyan entitások melyeket csak logikailag lehet törölni. ezek jelenleg fizikailag kerülnek törélse.
- Általában a lekérdezések még nem dto kat adnak vissza hanem teljes adatbázis entitásokat ez még változni fog.






