-- 🔐 Désactiver les contraintes pour éviter les erreurs de FK
SET FOREIGN_KEY_CHECKS = 0;

-- Supprimer les données des tables dépendantes en dernier
DELETE FROM StatutReservation;
DELETE FROM TypeAdherant_Livre;
DELETE FROM LivreCategorie;
DELETE FROM Penalite;
DELETE FROM Retour;
DELETE FROM Reservation;
DELETE FROM DemandeProlongement;
DELETE FROM Prolongement;
DELETE FROM Pret;
DELETE FROM Abonnement;
DELETE FROM Inscription;
DELETE FROM Adherant;
DELETE FROM RegleNbLivre;
DELETE FROM RegleDuree;
DELETE FROM Statut;
DELETE FROM TypePret;
DELETE FROM Exemplaire;
DELETE FROM Categorie;
DELETE FROM Livre;
DELETE FROM Admin;
DELETE FROM Personne;
DELETE FROM TypeAdherant;

-- ✅ Réactiver les contraintes
SET FOREIGN_KEY_CHECKS = 1;


ALTER TABLE Livre AUTO_INCREMENT = 1;
ALTER TABLE Personne AUTO_INCREMENT = 1;
ALTER TABLE Adherant AUTO_INCREMENT = 1;
ALTER TABLE Retour AUTO_INCREMENT = 1;

-- etc. pour toutes les tables avec clé primaire auto-incrémentée
