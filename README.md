# KarriereHub – WebTech Projekt SS2025

## 👥 Team
- Dongwoo Kim.

## 💡 Projektidee
**KarriereHub** ist eine moderne Plattform für die Jobsuche.  
Nutzer:innen können gezielt nach passenden Stellenangeboten filtern – zum Beispiel nach Fähigkeiten, Abschluss, Sprache oder Arbeitszeitmodell.  
Unternehmen wiederum können effizient Kandidat:innen mit passenden Qualifikationen finden.

## 🔧 Funktionen

### 👤 Für Unternehmen
- Registrierung und Verwaltung eines Firmenprofils
- Anlegen, Bearbeiten und Löschen von Stellenanzeigen
- Eigenen Account entfernen 
- Angabe von Anforderungen: Abschluss, Skills, Sprache, Berufserfahrung etc.


### 🔍 Für Bewerber:innen
- Durchsuchbare Joblisten mit:
    - Homeoffice-Option
    - Gehaltsangabe
    - Sprachkenntnissen
    - Studienrichtung und Tech-Skills
- Dynamisches Filtern über Sidebar-Komponente (`JobFilterSidebar.vue`)
- Detailansicht zu jedem Jobposting
- Accountlöschung für Bewerber

## 🖥️ Tech Stack

| Bereich       | Technologien                                 |
|--------------|-----------------------------------------------|
| Backend       | Java 21, Spring Boot, Spring Data JPA        |
| Frontend      | Vue.js 3, TypeScript, Axios                  |
| Datenbank     | H2 (Entwicklung), PostgreSQL (Produktion)    |
| Build & CI/CD | Gradle, GitHub Actions, Docker               |
| Deployment    | Render.com (Backend & Frontend getrennt)     |

### Deployment Links
- **Frontend:** https://karrierehub-frontend.onrender.com
- **Backend:** https://karrierehub-backend.onrender.com


## 🚀 Lokale Entwicklung

### Voraussetzungen
- Java 21
- Node.js 20+
- Gradle 8.x
- GitHub Account
- Render Account

Erstelle eine Kopie von `backend/.env.example` als `backend/.env` und fülle die Zugangsdaten für die Datenbank und API-URL aus.


### API Endpoints
## 📡 API Endpoints (Beispiel)

| Methode | Endpoint                | Beschreibung              |
|---------|-------------------------|---------------------------|
| GET     | `/api/jobpostings`      | Alle Jobangebote abrufen |
| GET     | `/api/jobpostings/{id}` | Details zu einer Anzeige |
| POST    | `/api/jobpostings`      | Neues Jobposting anlegen |
| DELETE  | `/api/jobpostings/{id}` | Anzeige löschen          |
| DELETE  | `/api/appUsers/{id}`    | Account löschen          |

# CI/CD

[![CI](https://github.com/Kimsschrift/Webtech_Projekt/actions/workflows/tests.yml/badge.svg)](https://github.com/Kimsschrift/Webtech_Projekt/actions/workflows/tests.yml)