-- Catégories
INSERT INTO Categorie (idCategorie, nomCategorie) VALUES
(1, 'Littérature classique'),
(2, 'Philosophie'),
(3, 'Jeunesse / Fantastique');

-- Insertion des livres
INSERT INTO Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite) VALUES 
(1, 'Les Miserables', '9782070409189', 'Gallimard', 'Victor Hugo', 1),
(2, 'L etranger', '9782070360022', 'Gallimard', 'AlbertCamus', 1),
(3, 'Harry Potter à l école des sorciers', '9782070643026', 'Gallimard', 'J.K. Rowling', 1);

-- Liaison livre-categorie
INSERT INTO Livre_Categorie (idLivre, idCategorie) VALUES (1, 1); -- Les Miserables → Litterature classique
INSERT INTO Livre_Categorie (idLivre, idCategorie) VALUES (2, 2); -- L'Etranger → Philosophie
INSERT INTO Livre_Categorie (idLivre, idCategorie) VALUES (3, 3); -- Harry Potter → Jeunesse


-- Exemplaires du livre "Les Miserables"
INSERT INTO Exemplaire (idExemplaire, numero, idLivre) VALUES 
(1, 'MIS001', 1),
(2, 'MIS002', 1),
(3, 'MIS003', 1),
(4, 'ETR001', 2),
(5, 'ETR002', 2),
(6, 'HAR001', 3);

-- Types d'adhérents
INSERT INTO TypeAdherant (idTypeAdherant, nomTypeAdherant) VALUES
(1, 'Etudiant'),
(2, 'Enseignant'),
(3, 'Professionnel');

-- Personnes
INSERT INTO Personne (idPersonne, nom, dateNaissance, adresse, mail, password) VALUES
(1, 'Amine Bensaïd', '2000-01-01', 'Rabat', 'amine.bensaid@email.com', 'pass1'),
(2, 'Sarah El Khattabi', '2001-02-02', 'Casablanca', 'sarah.elkhattabi@email.com', 'pass2'),
(3, 'Youssef Moujahid', '2002-03-03', 'Fès', 'youssef.moujahid@email.com', 'pass3'),
(4, 'Nadia Benali', '1980-04-04', 'Marrakech', 'nadia.benali@email.com', 'pass4'),
(5, 'Karim Haddadi', '1978-05-05', 'Agadir', 'karim.haddadi@email.com', 'pass5'),
(6, 'Salima Touhami', '1985-06-06', 'Tanger', 'salima.touhami@email.com', 'pass6'),
(7, 'Rachid El Mansouri', '1990-07-07', 'Oujda', 'rachid.elmansouri@email.com', 'pass7'),
(8, 'Amina Zerouali', '1992-08-08', 'Kenitra', 'amina.zerouali@email.com', 'pass8'),
(9, 'Rakoto', '1988-08-20', 'Toamasina', 'rakoto@example.com', 'admin123');

-- Adhérents avec numero_adherant
INSERT INTO Adherant (idAdherant, idPersonne, idTypeAdherant, numero_adherant) VALUES
(1, 1, 1, 'ETU001'), -- Amine Bensaïd, Etudiant
(2, 2, 1, 'ETU002'), -- Sarah El Khattabi, Etudiant
(3, 3, 1, 'ETU003'), -- Youssef Moujahid, Etudiant
(4, 4, 2, 'ENS001'), -- Nadia Benali, Enseignant
(5, 5, 2, 'ENS002'), -- Karim Haddadi, Enseignant
(6, 6, 2, 'ENS003'), -- Salima Touhami, Enseignant
(7, 7, 3, 'PROF001'), -- Rachid El Mansouri, Professionnel
(8, 8, 3, 'PROF002'); -- Amina Zerouali, Professionnel

INSERT INTO Statut (idStatut, nomStatut) VALUES
(1, 'En attente'),
(2, 'Validé'),
(3, 'Refusé'),
(4, 'Prêt'),
(5, 'Expiré');

INSERT INTO Admin (idAdmin, idPersonne) VALUES
(1, 9);  -- Rakoto est admin email: rakoto@example.com  ,mdp: admin123

-- Abonnements
INSERT INTO Abonnement (idAbonnement, idAdherant, dateDebut, dateFin) VALUES
(1, 1, '2025-02-01', '2025-07-24'), -- ETU001
(2, 2, '2025-02-01', '2025-07-01'), -- ETU002
(3, 3, '2025-04-01', '2025-12-01'), -- ETU003
(4, 4, '2025-07-01', '2026-07-01'), -- ENS001
(5, 5, '2025-08-01', '2026-05-01'), -- ENS002
(6, 6, '2025-07-01', '2026-06-01'), -- ENS003
(7, 7, '2025-06-01', '2025-12-01'), -- PROF001
(8, 8, '2024-10-01', '2025-06-01'); -- PROF002

-- Types de prêt
INSERT INTO TypePret (idTypePret, nomType) VALUES
(1, 'A domicile'),
(2, 'Sur place');

-- Quotas de prêt (nombre de livres et durée par adhérent)
-- Table RegleNbLivre : idRegleNbLivre, nbLivre, date, idTypeAdherant
INSERT INTO RegleNbLivre (idRegleNbLivre, nbLivre, date, idTypeAdherant) VALUES
(1, 2, '2025-01-01', 1), -- Etudiant (ETU001, ETU002, ETU003)
(2, 3, '2025-01-01', 2), -- Enseignant (ENS001, ENS002, ENS003)
(3, 4, '2025-01-01', 3); -- Professionnel (PROF001, PROF002)

-- Table RegleDuree : idRegleDuree, duree, date, idTypeAdherant
INSERT INTO RegleDuree (idRegleDuree, duree, date, idTypeAdherant) VALUES
(1, 7, '2025-01-01', 1), -- Etudiant (ETU001, ETU002, ETU003)
(2, 9, '2025-01-01', 2), -- Enseignant (ENS001, ENS002, ENS003)
(3, 12, '2025-01-01', 3); -- Professionnel (PROF001, PROF002)

INSERT INTO JourFerier (id, date) VALUES
(1, '2025-07-13'), -- Dimanche
(2, '2025-07-19'), -- Jour férié
(3, '2025-07-20'), -- Dimanche
(4, '2025-07-26'), -- Jour férié
(5, '2025-07-27'), -- Dimanche
(6, '2025-08-03'), -- Dimanche
(7, '2025-08-10'), -- Dimanche
(8, '2025-08-17'); -- Dimanche

INSERT INTO PenaliteQuota (id, nbJourPenalite, idTypeAdherant) VALUES
(1, 10, 1), -- Etudiant : 10 jours de pénalité
(2, 9, 2), -- Enseignant : 9 jours de pénalité
(3, 8, 3); -- Professionnel : 8 jours de pénalité

-- À ajouter à la fin de ton fichier data-monsieur.sql :

-- Quotas de réservation par type d'adhérant
INSERT INTO QuotaReservation (id, nbReservation, idTypeAdherant) VALUES
(1, 1, 1), -- Etudiant : 1 réservation
(2, 2, 2), -- Enseignant : 2 réservations
(3, 3, 3); -- Professionnel : 3 réservations

-- Quotas de prolongement par type d'adhérant
INSERT INTO QuotaProlongement (id, nbProlongement, idTypeAdherant) VALUES
(1, 3, 1), -- Etudiant : 3 prolongements
(2, 5, 2), -- Enseignant : 5 prolongements
(3, 7, 3); -- Professionnel : 7 prolongements

