# camunda-notification-with-camunda-bpm-reactor-websocket

cette application permet de piloter et se notifier lorsqu'il ya un changement d'état une instance d'un process aussi pour les tasks qui sont en cours d'execution
ce projet compore deux applicatios:  spring boot - utilise bpm reactor pour l'écoute des notifications au niveau camunda et une autre application angular qui utilise le websocket pour récuperer les notitications et les affichées
aussi l'application augular est publié en tant qu'un module qu'on peut l'importé via ce lien : 
https://www.npmjs.com/package/camundanotificationwebapp
