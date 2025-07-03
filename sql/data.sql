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