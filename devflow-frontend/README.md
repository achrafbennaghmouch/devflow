# DevFlow Frontend

Interface Angular standalone pour gérer les projets et tâches du backend DevFlow.

## Démarrage
1. Assurez-vous que le backend tourne sur `http://localhost:8080`.
2. Dans ce dossier, exécutez `npm install`.
3. Puis `npm start`.
4. Ouvrez `http://localhost:4200`.

Si Angular signale une erreur CORS, ajoutez `@CrossOrigin(origins = "http://localhost:4200")` aux contrôleurs backend ou configurez une politique CORS globale. Le frontend consomme `/api/projects` et `/api/tasks`.
