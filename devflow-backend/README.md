# DevFlow Backend

Backend REST de gestion de projets et de tâches avec Java 21, Spring Boot, Spring Data JPA, PostgreSQL et Lombok.

## Pré-requis
- JDK 21
- PostgreSQL démarré
- Base de données `devflow`
- IntelliJ IDEA ou Maven

## Configuration PostgreSQL

La configuration est dans `src/main/resources/application.properties`.
Le nom d'utilisateur par défaut est `achrafben`, et le mot de passe peut être fourni avec la variable d'environnement `DB_PASSWORD`.

Si PostgreSQL utilise un mot de passe, définissez `DB_PASSWORD` dans la configuration d'exécution IntelliJ. Ne commitez jamais de vrai mot de passe dans Git.

## Lancer l'application
Rechargez le projet Maven dans IntelliJ, puis lancez `DevflowBackendApplication`.

API par défaut : `http://localhost:8080`

## Endpoints projets
- `POST /api/projects`
- `GET /api/projects`
- `GET /api/projects/{id}`
- `PUT /api/projects/{id}`
- `DELETE /api/projects/{id}`
- Filtres : `GET /api/projects?name=dev` et `GET /api/projects?status=IN_PROGRESS`

Exemple de création :
```json
{
  "name": "Migration vers le cloud",
  "description": "Préparer le déploiement de DevFlow",
  "status": "PLANNED"
}
```

## Endpoints tâches
- `POST /api/tasks`
- `GET /api/tasks`
- `GET /api/tasks/{id}`
- `PUT /api/tasks/{id}`
- `DELETE /api/tasks/{id}`
- Filtres : `GET /api/tasks?projectId=1`, `GET /api/tasks?status=TODO`

Exemple de création :
```json
{
  "title": "Préparer le pipeline CI",
  "description": "Créer le premier pipeline",
  "status": "TODO",
  "priority": "HIGH",
  "dueDate": "2026-11-15",
  "projectId": 1
}
```

## Remarques
- `spring.jpa.hibernate.ddl-auto=update` est pratique en développement local, mais utilisez des migrations versionnées (par exemple Flyway) et une stratégie de schéma contrôlée en production.
- Cette version ne contient pas encore d'authentification ni d'autorisation.
- Les endpoints renvoient des DTO plutôt que les entités JPA.
