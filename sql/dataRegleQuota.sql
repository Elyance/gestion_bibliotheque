-- ✅ TYPE D'ADHERANT
INSERT INTO TypeAdherant (idTypeAdherant, nomTypeAdherant) VALUES
(1, 'Enfant'),
(2, 'Etudiant'),
(3, 'Adulte');

-- ✅ LIVRES
INSERT INTO Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite) VALUES
(1, 'Le Petit Prince', '123-ABC', 'Gallimard', 'Antoine de Saint-Exupéry', 6),
(2, '1984', '456-DEF', 'Secker & Warburg', 'George Orwell', 18),
(3, 'Les Fables', '789-GHI', 'Larousse', 'La Fontaine', 10);

-- ✅ TYPEADHERANT_LIVRE (autorisations)
INSERT INTO TypeAdherant_Livre VALUES (1, 1); -- Enfant peut lire Le Petit Prince
INSERT INTO TypeAdherant_Livre VALUES (2, 3); -- Etudiant peut lire Les Fables
INSERT INTO TypeAdherant_Livre VALUES (3, 2); -- Adulte peut lire 1984

-- ✅ PERSONNES
INSERT INTO Personne (idPersonne, nom, dateNaissance, adresse, mail, password) VALUES
(1, 'Kely', '2015-05-10', 'Tana', 'kely@mail.com', 'pass'),         -- 10 ans
(2, 'Sitraka', '2005-01-15', 'Tana', 'sitraka@mail.com', 'pass'),   -- 20 ans
(3, 'Malala', '1995-03-20', 'Tana', 'malala@mail.com', 'pass'),     -- 30 ans
(4, 'Admin', '1995-03-20', 'Tana', 'admin@mail.com', 'pass');     -- 30 ans


-- ✅ ADHERANTS
INSERT INTO Adherant (idAdherant, idPersonne, idTypeAdherant) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

-- ✅ ABONNEMENTS
INSERT INTO Abonnement (idAbonnement, dateDebut, dateFin, idAdherant) VALUES
(1, '2025-01-01', '2025-12-31', 1), -- OK
(2, '2022-01-01', '2022-12-31', 2), -- expiré
(3, '2024-01-01', '2025-12-31', 3); -- OK

-- ✅ EXEMPLAIRES
INSERT INTO Exemplaire (idExemplaire, numero, idLivre) VALUES
(1, 'EX001', 1),
(2, 'EX002', 2),
(3, 'EX003', 3);

-- ✅ TYPEPRET
INSERT INTO TypePret (idTypePret, nomType) VALUES
(1, 'Sur place'),
(2, 'A domicile');

-- ✅ REGLES DE DUREE
INSERT INTO RegleDuree (idRegleDuree, duree, date, idTypeAdherant) VALUES
(1, 5.0, '2025-01-01', 1),
(2, 10.0, '2025-01-01', 2),
(3, 15.0, '2025-01-01', 3);

-- ✅ REGLES DE NOMBRE DE LIVRES
INSERT INTO RegleNbLivre (idRegleNbLivre, nbLivre, date, idTypeAdherant) VALUES
(1, 1, '2025-01-01', 1),
(2, 2, '2025-01-01', 2),
(3, 3, '2025-01-01', 3);

-- ✅ PRÊTS ACTIFS POUR TESTER LE QUOTA (adherant 1 a déjà 1 prêt)
INSERT INTO Admin (idAdmin, idPersonne) VALUES (1, 4); -- un admin fictif
INSERT INTO Pret (idPret, dateDebut, dateFin, idTypePret, idAdmin, idAdherant, idExemplaire) VALUES
(1, '2025-07-01 10:00:00', '2025-07-06 10:00:00', 1, 1, 1, 1); -- prêt actif pour Kely
