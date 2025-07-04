-- Types d'adhérents
INSERT INTO TypeAdherant (idTypeAdherant, nomTypeAdherant) VALUES
  (1, 'Etudiant'),
  (2, 'Enseignant'),
  (3, 'Externe');

-- Personnes
INSERT INTO Personne (idPersonne, nom, dateNaissance, adresse, mail, password) VALUES
  (1, 'Admin Principal', '1990-01-01', 'Adresse Admin', 'admin@mail.com', 'admin123'),
  (2, 'Elyance', '2007-10-01', 'Adresse Elyance', 'elyance@mail.com', 'elyance123'),
  (3, 'Jean Dupont', '1985-05-15', '12 rue de Paris', 'jean.dupont@mail.com', 'jeanpass'),
  (4, 'Marie Curie', '1992-11-23', '34 avenue des Sciences', 'marie.curie@mail.com', 'mariepass'),
  (5, 'Paul Martin', '2000-03-10', '56 boulevard Victor', 'paul.martin@mail.com', 'paulpass'),
  (6, 'Alice Durand', '1998-07-30', '78 rue des Fleurs', 'alice.durand@mail.com', 'alicepass');

-- Admins
-- idAdmin: 1, mail: admin@mail.com, password: admin123
-- idAdmin: 2, mail: jean.dupont@mail.com, password: jeanpass
-- idAdmin: 3, mail: marie.curie@mail.com, password: mariepass
INSERT INTO Admin (idAdmin, idPersonne) VALUES
  (1, 1),
  (2, 3),
  (3, 4);

-- Adherants
-- idAdherant: 1, mail: elyance@mail.com, password: elyance123, type: Etudiant
-- idAdherant: 2, mail: paul.martin@mail.com, password: paulpass, type: Enseignant
-- idAdherant: 3, mail: alice.durand@mail.com, password: alicepass, type: Externe
INSERT INTO Adherant (idAdherant, idPersonne, idTypeAdherant) VALUES
  (1, 2, 1),
  (2, 5, 2),
  (3, 6, 3);

-- Données de test pour la table Livre
INSERT INTO Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite) VALUES
  (1, 'Le Petit Prince', '978-2070612758', 'Gallimard', 'Antoine de Saint-Exupéry', 7),
  (2, '1984', '978-0451524935', 'Secker & Warburg', 'George Orwell', 14),
  (3, 'L’Étranger', '978-2070360024', 'Gallimard', 'Albert Camus', 15),
  (4, 'Harry Potter à l''école des sorciers', '978-2070643027', 'Gallimard Jeunesse', 'J.K. Rowling', 9),
  (5, 'Le Seigneur des Anneaux', '978-2266154118', 'Christian Bourgois', 'J.R.R. Tolkien', 13);

  INSERT INTO Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite) VALUES
  (6, 'Le Petit Prince', '978-2070612758', 'Gallimard', 'Antoine de Saint-Exupéry', 7),
  (7, '1984', '978-0451524935', 'Secker & Warburg', 'George Orwell', 14),
  (8, 'L’Étranger', '978-2070360024', 'Gallimard', 'Albert Camus', 15),
  (9, 'Harry Potter à l''école des sorciers', '978-2070643027', 'Gallimard Jeunesse', 'J.K. Rowling', 9),
  (10, 'Le Seigneur des Anneaux', '978-2266154118', 'Christian Bourgois', 'J.R.R. Tolkien', 13);

   INSERT INTO Livre (idLivre, titre, ISBN, Edition, Auteur, ageLimite) VALUES
  (11, 'Le Petit Prince', '978-2070612758', 'Gallimard', 'Antoine de Saint-Exupéry', 7),
  (12, '1984', '978-0451524935', 'Secker & Warburg', 'George Orwell', 14),
  (13, 'L’Étranger', '978-2070360024', 'Gallimard', 'Albert Camus', 15),
  (14, 'Harry Potter à l''école des sorciers', '978-2070643027', 'Gallimard Jeunesse', 'J.K. Rowling', 9),
  (15, 'Le Seigneur des Anneaux', '978-2266154118', 'Christian Bourgois', 'J.R.R. Tolkien', 13);

-- Données de test pour la table Categorie
INSERT INTO Categorie (idCategorie, nomCategorie) VALUES
  (1, 'Romance'),
  (2, 'Comédie'),
  (3, 'Aventure'),
  (4, 'Fantastique'),
  (5, 'Science-Fiction'),
  (6, 'Classique');

-- Associations Livre-Categorie (un livre peut avoir plusieurs catégories)
INSERT INTO LivreCategorie (idLivre, idCategorie) VALUES
  (1, 1), -- Le Petit Prince : Romance
  (1, 3), -- Le Petit Prince : Aventure
  (1, 6), -- Le Petit Prince : Classique
  (2, 5), -- 1984 : Science-Fiction
  (2, 6), -- 1984 : Classique
  (3, 6), -- L’Étranger : Classique
  (4, 4), -- Harry Potter : Fantastique
  (4, 3), -- Harry Potter : Aventure
  (5, 4), -- Le Seigneur des Anneaux : Fantastique
  (5, 3), -- Le Seigneur des Anneaux : Aventure
  (6, 1), (6, 2), -- Le Petit Prince (copie) : Romance, Comédie
  (7, 2), (7, 5), -- 1984 (copie) : Comédie, Science-Fiction
  (8, 6), -- L’Étranger (copie) : Classique
  (9, 4), (9, 3), -- Harry Potter (copie) : Fantastique, Aventure
  (10, 4), (10, 3), -- Le Seigneur des Anneaux (copie) : Fantastique, Aventure
  (11, 1), (11, 3), -- Le Petit Prince (copie) : Romance, Aventure
  (12, 5), (12, 6), -- 1984 (copie) : Science-Fiction, Classique
  (13, 6), -- L’Étranger (copie) : Classique
  (14, 4), (14, 3), -- Harry Potter (copie) : Fantastique, Aventure
  (15, 4), (15, 3); -- Le Seigneur des Anneaux (copie) : Fantastique, Aventure

-- Données de test pour la table Exemplaire
INSERT INTO Exemplaire (idExemplaire, numero, idLivre) VALUES
  (1, 'EX-001', 1),
  (2, 'EX-002', 1),
  (3, 'EX-003', 2),
  (4, 'EX-004', 2),
  (5, 'EX-005', 3),
  (6, 'EX-006', 3),
  (7, 'EX-007', 4),
  (8, 'EX-008', 4),
  (9, 'EX-009', 5),
  (10, 'EX-010', 5),
  (11, 'EX-011', 1),
  (12, 'EX-012', 2),
  (13, 'EX-013', 3),
  (14, 'EX-014', 4),
  (15, 'EX-015', 5);

-- Données de test pour la table Statut
INSERT INTO Statut (idStatut, nomStatut) VALUES
  (1, 'En attente'),
  (2, 'Validée'),
  (3, 'Refusée'),
  (4, 'Annulée'),
  (5, 'Expirée');

-- Données de test pour la table TypePret
INSERT INTO TypePret (idTypePret, nomType) VALUES
  (1, 'A domicile'),
  (2, 'Sur place');

-- Données de test pour la table Pret
INSERT INTO Pret (idPret, dateDebut, dateFin, idTypePret, idAdmin, idAdherant, idExemplaire) VALUES
  (1, '2024-01-15 10:00:00', '2024-02-15 10:00:00', 1, 1, 1, 1),
  (2, '2024-01-20 14:30:00', '2024-02-20 14:30:00', 2, 2, 2, 3),
  (3, '2024-02-01 09:15:00', '2024-03-01 09:15:00', 2, 1, 3, 5),
  (4, '2024-02-10 16:45:00', '2024-03-10 16:45:00', 1, 3, 1, 7),
  (5, '2024-02-15 11:20:00', '2024-03-15 11:20:00', 2, 2, 2, 9);

-- Prêts en cours (dates futures)
INSERT INTO Pret (idPret, dateDebut, dateFin, idTypePret, idAdmin, idAdherant, idExemplaire) VALUES
  (6, '2024-06-01 10:00:00', '2024-08-01 10:00:00', 1, 1, 1, 2),
  (7, '2024-06-15 14:30:00', '2024-08-15 14:30:00', 2, 2, 2, 4),
  (8, '2024-07-01 09:15:00', '2024-09-01 09:15:00', 3, 1, 3, 6),
  (9, '2024-07-10 16:45:00', '2024-09-10 16:45:00', 1, 3, 1, 8),
  (10, '2024-07-15 11:20:00', '2024-09-15 11:20:00', 2, 2, 2, 10);

-- Prêts expirés (dates passées)
INSERT INTO Pret (idPret, dateDebut, dateFin, idTypePret, idAdmin, idAdherant, idExemplaire) VALUES
  (11, '2023-12-01 10:00:00', '2023-12-31 10:00:00', 1, 1, 1, 11),
  (12, '2023-11-15 14:30:00', '2023-12-15 14:30:00', 2, 2, 2, 12),
  (13, '2023-10-01 09:15:00', '2023-11-01 09:15:00', 3, 1, 3, 13),
  (14, '2023-09-10 16:45:00', '2023-10-10 16:45:00', 1, 3, 1, 14),
  (15, '2023-08-15 11:20:00', '2023-09-15 11:20:00', 2, 2, 2, 15);

-- Quelques demandes de prolongement pour tester
INSERT INTO DemandeProlongement (idDemande, dateDemande, idPret) VALUES
  (1, '2024-07-25', 6),
  (2, '2024-07-20', 7),
  (3, '2024-08-01', 8),
  (4, '2024-08-05', 9),
  (5, '2024-08-10', 10);

-- Quelques prolongements accordés pour tester
INSERT INTO Prolongement (idProlongement, dateFin, idPret) VALUES
  (1, '2024-08-31 10:00:00', 6),
  (2, '2024-09-15 14:30:00', 7),
  (3, '2024-10-01 09:15:00', 8);

-- Quelques retours pour tester
INSERT INTO Retour (idRetour, dateRetour, idPret) VALUES
  (1, '2024-02-10 15:30:00', 1),
  (2, '2024-02-18 10:15:00', 2),
  (3, '2024-02-28 14:45:00', 3);

-- Quelques pénalités pour les retours en retard
INSERT INTO Penalite (idPenalite, nbJourPenalite, date, idPret) VALUES
  (1, 5, '2024-02-20', 1),
  (2, 3, '2024-02-23', 2),
  (3, 7, '2024-03-08', 3);