# KarriereHub – WebTech Projekt SS2025

## 👥 Team
- Dongwoo Kim

## 💡 Projektidee
**KarriereHub** ist eine moderne Plattform für die Jobsuche.  
Nutzer:innen können gezielt nach passenden Stellenangeboten filtern – zum Beispiel nach Fähigkeiten, Abschluss, Sprache oder Arbeitszeitmodell.  
Unternehmen wiederum können effizient Kandidat:innen mit passenden Qualifikationen finden.

## 🔧 Funktionen

### 👤 Für Unternehmen
- Registrierung und Verwaltung eines Firmenprofils
- Anlegen, Bearbeiten und Löschen von Stellenanzeigen
- Angabe von Anforderungen: Abschluss, Skills, Sprache, Berufserfahrung etc.

### 🔍 Für Bewerber:innen
- Durchsuchbare Joblisten mit:
    - Homeoffice-Option
    - Gehaltsangabe
    - Sprachkenntnissen
    - Studienrichtung und Tech-Skills
- Dynamisches Filtern über Sidebar-Komponente (`JobFilterSidebar.vue`)
- Detailansicht zu jedem Jobposting

## 🖥️ Tech Stack

| Bereich       | Technologien                                 |
|--------------|-----------------------------------------------|
| Backend       | Java 21, Spring Boot, Spring Data JPA        |
| Frontend      | Vue.js 3, TypeScript, Axios                  |
| Datenbank     | H2 (Entwicklung), PostgreSQL (Produktion)    |
| Build & CI/CD | Gradle, GitHub Actions, Docker               |
| Deployment    | Render.com (Backend & Frontend getrennt)     |

## 📁 Projektstruktur

Webtech_Projekt/
├── backend/ # Spring Boot REST-API
│ ├── controller/ # REST-Endpunkte für Jobs & Unternehmen
│ ├── domain/ # Entitäten & Enums
│ ├── dto/ # Daten-Transfer-Objekte
│ ├── repository/ # JPA-Repositories
│ └── service/ # Business-Logik
├── frontend/vue-project/ # Vue 3 SPA
│ ├── components/ # UI-Komponenten inkl. FilterSidebar & Startseite
│ ├── views/ # JobListView & JobDetailView
│ └── router/ # Vue Router Konfiguration


## 🚀 Lokale Entwicklung

### Voraussetzungen
- Java 21
- Node.js 20+
- Gradle 8.x
- GitHub Account
- Render Account


### API Endpoints
## 📡 API Endpoints (Beispiel)

| Methode | Endpoint                | Beschreibung              |
|---------|-------------------------|---------------------------|
| GET     | `/api/jobpostings`      | Alle Jobangebote abrufen |
| GET     | `/api/jobpostings/{id}` | Details zu einer Anzeige |
| POST    | `/api/jobpostings`      | Neues Jobposting anlegen |
| DELETE  | `/api/jobpostings/{id}` | Anzeige löschen          |




### Backend starten
```bash
cd backend
./gradlew bootRun

### Frontend starten

```bash
cd frontend/vue-project
npm install
npm run dev


# CI/CD

[![CI](https://github.com/Kimsschrift/Webtech_Projekt/actions/workflows/tests.yml/badge.svg)](https://github.com/Kimsschrift/Webtech_Projekt/actions/workflows/tests.yml)