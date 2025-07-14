-- Types d’adhérents
INSERT INTO TypeAdherant (idTypeAdherant, nomTypeAdherant) VALUES
(1, 'Etudiant'),
(2, 'Enseignant'),
(3, 'Externe');

-- Personnes (utilisateurs du système)
INSERT INTO Personne (idPersonne, nom, dateNaissance, adresse, mail, password) VALUES
(1, 'Andrianina', '2000-01-15', 'Antananarivo', 'andrianina@example.com', 'pass123'),
(2, 'Rasoa', '1995-05-10', 'Fianarantsoa', 'rasoa@example.com', 'pass456'),
(3, 'Rakoto', '1988-08-20', 'Toamasina', 'rakoto@example.com', 'admin123'),
(4, 'Harisoa', '1997-03-30', 'Antsirabe', 'harisoa@example.com', 'abcd1234'),
(5, 'Tiana', '2002-07-12', 'Mahajanga', 'tiana@example.com', 'pass789');

-- Admins
INSERT INTO Admin (idAdmin, idPersonne) VALUES
(1, 3); -- Rakoto est admin

-- Adhérents
INSERT INTO Adherant (idAdherant, idPersonne, idTypeAdherant) VALUES
(1, 1, 1),  -- Andrianina - Etudiant
(2, 2, 2),  -- Rasoa - Enseignant
(3, 4, 3),  -- Harisoa - Externe
(4, 5, 1);  -- Tiana - Etudiant

-- Abonnements
INSERT INTO Abonnement (idAbonnement, dateDebut, dateFin, idAdherant) VALUES
(1, '2024-01-10', '2028-01-10', 1),
(2, '2024-02-15', '2028-02-15', 2),
(3, '2024-03-20', '2028-03-20', 3),
(4, '2024-04-05', '2028-04-05', 4);


-- Types de prêt
INSERT INTO TypePret (idTypePret, nomType) VALUES
  (1, 'A domicile'),
  (2, 'Sur place');
