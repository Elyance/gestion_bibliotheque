SET FOREIGN_KEY_CHECKS = 0;

-- ========================================
-- SUPPRESSION DES DONNÉES TRANSACTIONNELLES
-- (Tables NON présentes dans data-monsieur.sql)
-- ========================================

-- Supprimer les données de fonctionnalité (prêts, retours, etc.)
DELETE FROM statut_demande;
DELETE FROM StatutReservation;
DELETE FROM Penalite;
DELETE FROM Retour;
DELETE FROM reservation;
DELETE FROM DemandeProlongement;
DELETE FROM Prolongement;
DELETE FROM Pret;


ALTER TABLE statut_demande AUTO_INCREMENT = 1;
ALTER TABLE StatutReservation AUTO_INCREMENT = 1;
ALTER TABLE Penalite AUTO_INCREMENT = 1;
ALTER TABLE Retour AUTO_INCREMENT = 1;
ALTER TABLE reservation AUTO_INCREMENT = 1;
ALTER TABLE DemandeProlongement AUTO_INCREMENT = 1;
ALTER TABLE Prolongement AUTO_INCREMENT = 1;
ALTER TABLE Pret AUTO_INCREMENT = 1;

-- Réactiver les contraintes de clés étrangères
SET FOREIGN_KEY_CHECKS = 1;