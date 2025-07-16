-- Désactiver temporairement les contraintes de clés étrangères
SET FOREIGN_KEY_CHECKS = 0;

-- Suppression des données
DELETE FROM StatutReservation;
DELETE FROM Penalite;
DELETE FROM Retour;
DELETE FROM reservation;
DELETE FROM Demande_prolongement;
DELETE FROM Prolongement;
DELETE FROM Pret;
DELETE FROM Abonnement;
DELETE FROM Inscription;
DELETE FROM Adherant;
DELETE FROM RegleNbLivre;
DELETE FROM RegleDuree;
DELETE FROM TypeAdherant_Livre;
DELETE FROM LivreCategorie;
DELETE FROM Exemplaire;
DELETE FROM Categorie;
DELETE FROM Livre;
DELETE FROM Admin;
DELETE FROM TypePret;
DELETE FROM Statut;
DELETE FROM Personne;
DELETE FROM TypeAdherant;

-- Réinitialisation des auto-incréments
ALTER TABLE StatutReservation AUTO_INCREMENT = 1;
ALTER TABLE Penalite AUTO_INCREMENT = 1;
ALTER TABLE Retour AUTO_INCREMENT = 1;
ALTER TABLE Reservation AUTO_INCREMENT = 1;
ALTER TABLE DemandeProlongement AUTO_INCREMENT = 1;
ALTER TABLE Prolongement AUTO_INCREMENT = 1;
ALTER TABLE Pret AUTO_INCREMENT = 1;
ALTER TABLE Abonnement AUTO_INCREMENT = 1;
ALTER TABLE Inscription AUTO_INCREMENT = 1;
ALTER TABLE Adherant AUTO_INCREMENT = 1;
ALTER TABLE RegleNbLivre AUTO_INCREMENT = 1;
ALTER TABLE RegleDuree AUTO_INCREMENT = 1;
ALTER TABLE Exemplaire AUTO_INCREMENT = 1;
ALTER TABLE Categorie AUTO_INCREMENT = 1;
ALTER TABLE Livre AUTO_INCREMENT = 1;
ALTER TABLE Admin AUTO_INCREMENT = 1;
ALTER TABLE TypePret AUTO_INCREMENT = 1;
ALTER TABLE Statut AUTO_INCREMENT = 1;
ALTER TABLE Personne AUTO_INCREMENT = 1;
ALTER TABLE TypeAdherant AUTO_INCREMENT = 1;

-- Réactiver les contraintes de clés étrangères
SET FOREIGN_KEY_CHECKS = 1;