-- TypeAdherant (idTypeAdherant, nomTypeAdherant)
INSERT INTO TypeAdherant (idTypeAdherant, nomTypeAdherant) VALUES
(1, 'Etudiant'),
(2, 'Externe');

-- Personne (idPersonne, nom, dateNaissance, adresse, mail, password)
INSERT INTO Personne (idPersonne, nom, dateNaissance, adresse, mail, password) VALUES
(1, 'Rakoto', '2000-01-01', 'Tana', 'rakoto@mail.com', 'pass'),
(2, 'Rabe', '2012-01-01', 'Fianar', 'rabe@mail.com', 'pass'),
(3, 'Jean', '1990-01-01', 'Majunga', 'jean@mail.com', 'pass');

-- Adherant (idAdherant, idPersonne, idTypeAdherant)
INSERT INTO Adherant (idAdherant, idPersonne, idTypeAdherant) VALUES
(1, 1, '1'),  -- Rakoto est Etudiant
(2, 2, '1'),  -- Rabe est Etudiant
(3, 3, '2');  -- Jean est Externe

-- Admin (idAdmin, idPersonne)
INSERT INTO Admin (idAdmin, idPersonne) VALUES
(1, 1);

-- Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite)
INSERT INTO Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite) VALUES
(1, 'Java Débutant', 'ISBN-111', '1ere', 'Oracle', 12),
(2, 'Secrets Adultes', 'ISBN-222', '2e', 'Inconnu', 18);

-- Exemplaire (idExemplaire, numero, idLivre)
INSERT INTO Exemplaire (idExemplaire, numero, idLivre) VALUES
(1, 'EX-001', 1),
(2, 'EX-002', 2);

-- TypePret (idTypePret, nomType)
INSERT INTO TypePret (idTypePret, nomType) VALUES
(1, 'Normal');

-- RegleDuree (idRegleDuree, duree, date_, idTypeAdherant)
INSERT INTO RegleDuree (idRegleDuree, duree, date_, idTypeAdherant) VALUES
(1, 7, '2025-01-01', '1'),
(2, 5, '2025-01-01', '2');

-- RegleNbLivre (idRegleNbLivre, nbLivre, date_, idTypeAdherant)
INSERT INTO RegleNbLivre (idRegleNbLivre, nbLivre, date, idTypeAdherant) VALUES
(1, 2, '2025-01-01', '1'),
(2, 1, '2025-01-01', '2'),
(3, 2, '2025-02-02', '2');

INSERT INTO RegleNbLivre (idRegleNbLivre, nbLivre, date, idTypeAdherant) VALUES (3, 2, '2025-02-02', '2');


-- TypeAdherant_Livre (idTypeAdherant, idLivre)
INSERT INTO TypeAdherant_Livre (idTypeAdherant, idLivre) VALUES
('1', 1), -- Etudiant peut emprunter Java Débutant
('2', 2); -- Externe peut emprunter Secrets Adultes

-- Abonnement (idAbonnement, dateDebut, dateFin, idAdherant)
INSERT INTO Abonnement (idAbonnement, dateDebut, dateFin, idAdherant) VALUES
(1, '2025-01-01', '2025-12-31', 1), -- Rakoto est abonné toute l'année
(2, '2025-01-01', '2025-03-01', 3); -- Jean est abonné jusqu'au 1er mars

-- Pret (idPret, dateDebut, dateFin, idTypePret, idAdmin, idAdherant, idExemplaire)
INSERT INTO Pret (idPret, dateDebut, dateFin, idTypePret, idAdmin, idAdherant, idExemplaire) VALUES
(1, '2025-06-01 10:00:00', '2025-06-08 10:00:00', 1, 1, 3, 2); -- Jean emprunte "Secrets Adultes"

INSERT INTO Retour ()

-- Penalite (idPenalite, nbJourPenalite, date, idPret)
INSERT INTO Penalite (idPenalite, nbJourPenalite, date, idPret) VALUES
(1, 10, '2025-07-01', 1); -- Pénalité pour Jean du 1er juillet pendant 10 jours
