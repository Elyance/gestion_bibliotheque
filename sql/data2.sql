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



-- Livre interdit à Externe
-- INSERT INTO TypeAdherant_Livre (idTypeAdherant, idLivre) VALUES
-- (1, 1), (2, 1), (1, 2), (2, 2), (1, 3), (2, 3); -- Externe (3) n’a accès à aucun livre

-- Pénalité active pour Harisoa (idAdherant=3) du 2025-07-10 au 2025-07-20
INSERT INTO Penalite (idPenalite, nbJourPenalite, date, idPret) VALUES
(1, 10, '2025-07-10', 1);

-- Prêts déjà actifs pour quota
INSERT INTO Pret (idPret, dateDebut, dateFin, idTypePret, idAdmin, idAdherant, idExemplaire) VALUES
(1, '2025-07-01 10:00:00', '2025-07-08 10:00:00', 1, 1, 1, 1), -- Andrianina (Etudiant) a déjà 1 prêt
(2, '2025-07-05 10:00:00', '2025-07-12 10:00:00', 1, 1, 1, 3); -- Andrianina (Etudiant) a 2 prêts (quota atteint)

-- Cas de test à réaliser :
-- 1. Prêt accepté : Rasoa (Enseignant, idAdherant=2) emprunte 'Spring pour les nuls' (EX001)
-- 2. Prêt refusé (quota) : Andrianina (Etudiant, idAdherant=1) tente un 3e prêt
-- 3. Prêt refusé (pénalité) : Harisoa (Externe, idAdherant=3) tente un prêt pendant la pénalité
-- 4. Prêt refusé (livre interdit) : Harisoa (Externe) tente d’emprunter n’importe quel livre
-- 5. Prêt refusé (âge) : Tiana (Etudiant, idAdherant=4, né en 2002) tente d’emprunter 'Java Avancé' (âgeLimite=18)
-- 6. Prêt refusé (abonnement) : Créez un adhérant sans abonnement ou avec abonnement expiré

-- Pour chaque cas, le contrôleur doit :
-- - Accepter le prêt si toutes les règles sont OK (cas 1)
-- - Refuser avec le bon message sinon (cas 2 à 6)


-- Table TypeAdherant_Livre avec la colonne date
-- Format : (idTypeAdherant, idLivre, date)
INSERT INTO TypeAdherant_Livre (idTypeAdherant, idLivre, date) VALUES
(1, 1, '2025-01-01'), -- Etudiant peut emprunter le livre 1 à partir du 2025-01-01
(2, 1, '2025-01-01'), -- Enseignant peut emprunter le livre 1 à partir du 2025-01-01
(1, 2, '2025-07-01'), -- Etudiant peut emprunter le livre 2 à partir du 2025-07-01
(2, 2, '2025-01-01'), -- Enseignant peut emprunter le livre 2 à partir du 2025-01-01
(1, 3, '2025-01-01'), -- Etudiant peut emprunter le livre 3 à partir du 2025-01-01
(2, 3, '2025-01-01'); -- Enseignant peut emprunter le livre 3 à partir du 2025-01-01

-- Cas de test à réaliser :
-- 1. Andrianina (Etudiant, idAdherant=1) tente d'emprunter le livre 2 le 2025-06-30 => refusé (date trop tôt)
-- 2. Andrianina tente d'emprunter le livre 2 le 2025-07-02 => accepté
-- 3. Rasoa (Enseignant, idAdherant=2) peut emprunter tous les livres à n'importe quelle date >= 2025-01-01
-- 4. Harisoa (Externe, idAdherant=3) n'a aucune ligne => refusé pour tous les livres

-- Pour chaque cas, le contrôleur doit :
-- - Accepter le prêt si la date demandée >= date de la table pour le type/livre
-- - Refuser sinon

INSERT INTO Statut (idStatut, nomStatut) VALUES
(1, 'En attente'),
(2, 'Validé'),
(3, 'Refusé'),
(4, 'Prêt'),
(5, 'Expiré');