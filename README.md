# GameReview – Grupprojekt

Ett Java-projekt som bygger en textbaserad spelrecensionsplattform. Användaren kan visa spel, skriva recensioner, ta bort och uppdatera sina omdömen – via ett konsolgränssnitt.

---

## Funktionalitet

- Visa alla spel
- Visa ett spels detaljer + recensioner
- Lägg till recension
- Uppdatera recension
- Ta bort recension

Alla data lagras i en relationsdatabas (Apache Derby) och hanteras via JPA och Hibernate.

---

## Teknikanvändning

| Teknik                    | Användning                          |
| ------------------------- | ----------------------------------- |
| Java 17                   | Programmering                       |
| Spring Framework          | Dependency Injection, konfiguration |
| JPA (`javax.persistence`) | Objekt–relational mapping           |
| Hibernate 5.6.x           | JPA-implementation                  |
| Apache Derby              | Databas                             |
| Maven                     | Byggverktyg                         |
| SLF4J                     | Loggning                            |

---

## Projektstruktur

src/
├── main/
│ ├── java/se/yrgo/
│ │ ├── client/ # Startklass (Client.java)
│ │ ├── data/ # DAO-implementering
│ │ ├── services/ # Affärslogik
│ │ ├── domain/ # Entiteter: Game, Review, User
│ │ └── exception/ # Egenkastade undantag
│ └── resources/
│ ├── applicationContext.xml
│ └── META-INF/persistence.xml
pom.xml
README.md
.gitignore

---

## Kör projektet

### Förutsättningar

- Java 17+
- Maven 3+
