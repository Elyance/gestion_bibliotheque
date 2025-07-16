-- Livres
INSERT INTO Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite) VALUES
(1, 'Spring pour les nuls', 'ISBN001', 'Eyrolles', 'Dupont', 0),
(2, 'Java Avancé', 'ISBN002', 'Dunod', 'Martin', 18),
(3, 'Contes pour enfants', 'ISBN003', 'Nathan', 'Petit', 0);

-- Exemplaires
INSERT INTO Exemplaire (idExemplaire, numero, idLivre) VALUES
(1, 'EX001', 1),
(2, 'EX002', 2),
(3, 'EX003', 3);

-- Règles de durée (Etudiant : 7j, Enseignant : 30j, Externe : 3j)
INSERT INTO RegleDuree (idRegleDuree, duree, date, idTypeAdherant) VALUES
(1, 7, '2025-01-01', 1),
(2, 30, '2025-01-01', 2),
(3, 3, '2025-01-01', 3);

-- Règles de nombre de livres (Etudiant : 2, Enseignant : 5, Externe : 1)
INSERT INTO RegleNbLivre (idRegleNbLivre, nbLivre, date, idTypeAdherant) VALUES
(1, 2, '2025-01-01', 1),
(2, 5, '2025-01-01', 2),
(3, 1, '2025-01-01', 3);

INSERT INTO RegleNbLivre (idRegleNbLivre, nbLivre, date, idTypeAdherant) VALUES
(1, 5, '2025-06-01', 1);

-- Format : (idTypeAdherant, idLivre, date)
INSERT INTO TypeAdherant_Livre (idTypeAdherant, idLivre, date) VALUES
(1, 1, '2025-01-01'), -- Etudiant peut emprunter le livre 1 à partir du 2025-01-01
(2, 1, '2025-01-01'), -- Enseignant peut emprunter le livre 1 à partir du 2025-01-01
(1, 2, '2025-07-01'), -- Etudiant peut emprunter le livre 2 à partir du 2025-07-01
(2, 2, '2025-01-01'), -- Enseignant peut emprunter le livre 2 à partir du 2025-01-01
(1, 3, '2025-01-01'), -- Etudiant peut emprunter le livre 3 à partir du 2025-01-01
(2, 3, '2025-01-01'); -- Enseignant peut emprunter le livre 3 à partir du 2025-01-01

INSERT INTO Statut (idStatut, nomStatut) VALUES
(1, 'En attente'),
(2, 'Validé'),
(3, 'Refusé'),
(4, 'Prêt'),
(5, 'Expiré');