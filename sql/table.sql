DROP DATABASE bibliotheque;
CREATE DATABASE bibliotheque;
USE bibliotheque;

CREATE TABLE TypeAdherant(
   idTypeAdherant INTEGER NOT NULL AUTO_INCREMENT,
   nomTypeAdherant VARCHAR(50) NOT NULL,
   PRIMARY KEY(idTypeAdherant)
);

CREATE TABLE Personne(
   idPersonne INT AUTO_INCREMENT,
   nom VARCHAR(50),
   dateNaissance DATE NOT NULL,
   adresse VARCHAR(50),
   mail VARCHAR(50) NOT NULL,
   password VARCHAR(50),
   PRIMARY KEY(idPersonne)
);

CREATE TABLE Admin(
   idAdmin INT AUTO_INCREMENT,
   idPersonne INT NOT NULL,
   PRIMARY KEY(idAdmin),
   FOREIGN KEY(idPersonne) REFERENCES Personne(idPersonne)
);

CREATE TABLE Livre(
   idLivre INT AUTO_INCREMENT,
   titre VARCHAR(50) NOT NULL,
   ISBN VARCHAR(50) NOT NULL,
   Edition VARCHAR(50) NOT NULL,
   Auteur VARCHAR(50) NOT NULL,
   ageLimite INT NOT NULL,
   PRIMARY KEY(idLivre)
);

CREATE TABLE Categorie(
   idCategorie INT AUTO_INCREMENT,
   nomCategorie VARCHAR(50) NOT NULL,
   PRIMARY KEY(idCategorie)
);

CREATE TABLE Exemplaire(
   idExemplaire INT AUTO_INCREMENT,
   numero VARCHAR(50) NOT NULL,
   idLivre INT NOT NULL,
   PRIMARY KEY(idExemplaire),
   FOREIGN KEY(idLivre) REFERENCES Livre(idLivre)
);

CREATE TABLE TypePret(
   idTypePret INT AUTO_INCREMENT,
   nomType VARCHAR(50) NOT NULL,
   PRIMARY KEY(idTypePret)
);

CREATE TABLE Statut(
   idStatut INT AUTO_INCREMENT,
   nomStatut VARCHAR(50) NOT NULL,
   PRIMARY KEY(idStatut)
);

CREATE TABLE RegleDuree(
   idRegleDuree INT AUTO_INCREMENT,
   duree DECIMAL(5,2) NOT NULL,
   date DATE NOT NULL,
   idTypeAdherant INT NOT NULL,
   PRIMARY KEY(idRegleDuree),
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE RegleNbLivre(
   idRegleNbLivre INT AUTO_INCREMENT,
   nbLivre INT NOT NULL,
   date DATE NOT NULL,
   idTypeAdherant INT NOT NULL,
   PRIMARY KEY(idRegleNbLivre),
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE Adherant(
   idAdherant INT AUTO_INCREMENT,
   numero_adherant VARCHAR(50) NOT NULL,
   idPersonne INT NOT NULL,
   idTypeAdherant INT NOT NULL,
   PRIMARY KEY(idAdherant),
   UNIQUE(idPersonne),
   FOREIGN KEY(idPersonne) REFERENCES Personne(idPersonne),
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE Inscription(
   idInscription INT AUTO_INCREMENT,
   dateInscription DATETIME NOT NULL,
   idAdherant INT NOT NULL,
   PRIMARY KEY(idInscription),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant)
);

CREATE TABLE Abonnement(
   idAbonnement INT AUTO_INCREMENT,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   idAdherant INT NOT NULL,
   PRIMARY KEY(idAbonnement),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant)
);

CREATE TABLE Pret(
   idPret INT AUTO_INCREMENT,
   dateDebut DATETIME NOT NULL,
   dateFin DATETIME NOT NULL,
   idTypePret INT NOT NULL,
   idAdmin INT NOT NULL,
   idAdherant INT NOT NULL,
   idExemplaire INT NOT NULL,
   PRIMARY KEY(idPret),
   FOREIGN KEY(idTypePret) REFERENCES TypePret(idTypePret),
   FOREIGN KEY(idAdmin) REFERENCES Admin(idAdmin),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant),
   FOREIGN KEY(idExemplaire) REFERENCES Exemplaire(idExemplaire)
);

CREATE TABLE DemandeProlongement(
   idDemande INT AUTO_INCREMENT,
   dateDemande DATE NOT NULL,
   nbJourDemande INT NOT NULL,
   idPret INT NOT NULL,
   idAdherant INT NOT NULL,
   PRIMARY KEY(idDemande),
   FOREIGN KEY(idPret) REFERENCES Pret(idPret),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant)
);

CREATE TABLE reservation(
   idReservation INT AUTO_INCREMENT,
   date DATE NOT NULL,
   dateReservation DATE NOT NULL,
   idAdmin INT,
   idAdherant INT NOT NULL,
   idExemplaire INT NOT NULL,
   idStatutActuel INT,
   PRIMARY KEY(idReservation),
   FOREIGN KEY(idAdmin) REFERENCES Admin(idAdmin),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant),
   FOREIGN KEY(idExemplaire) REFERENCES Exemplaire(idExemplaire),
   FOREIGN KEY(idStatutActuel) REFERENCES Statut(idStatut)
);

CREATE TABLE Retour(
   idRetour INT AUTO_INCREMENT,
   dateRetour DATETIME NOT NULL,
   idPret INT NOT NULL,
   PRIMARY KEY(idRetour),
   FOREIGN KEY(idPret) REFERENCES Pret(idPret)
);

CREATE TABLE Penalite(
   idPenalite INT AUTO_INCREMENT,
   nbJourPenalite INT NOT NULL,
   date DATE NOT NULL,
   idPret INT NOT NULL,
   PRIMARY KEY(idPenalite),
   UNIQUE(idPret),
   FOREIGN KEY(idPret) REFERENCES Pret(idPret)
);

CREATE TABLE Livre_Categorie(
   idLivre INT,
   idCategorie INT,
   PRIMARY KEY(idLivre, idCategorie),
   FOREIGN KEY(idLivre) REFERENCES Livre(idLivre),
   FOREIGN KEY(idCategorie) REFERENCES Categorie(idCategorie)
);

CREATE TABLE TypeAdherant_Livre(
   idTypeAdherant INT,
   idLivre INT,
   date DATE NOT NULL,
   PRIMARY KEY(idTypeAdherant, idLivre),
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant),
   FOREIGN KEY(idLivre) REFERENCES Livre(idLivre)
);

CREATE TABLE StatutReservation(
   idReservation INT,
   idStatut INT,
   dateStatut DATE,
   PRIMARY KEY(idReservation, idStatut),
   FOREIGN KEY(idReservation) REFERENCES reservation(idReservation),
   FOREIGN KEY(idStatut) REFERENCES Statut(idStatut)
);

CREATE TABLE statut_demande(
   idStatutDemande INT AUTO_INCREMENT,
   idDemande INT NOT NULL,
   idStatut INT NOT NULL,
   date_statut DATETIME NOT NULL,
   PRIMARY KEY(idStatutDemande),
   FOREIGN KEY(idDemande) REFERENCES DemandeProlongement(idDemande),
   FOREIGN KEY(idStatut) REFERENCES Statut(idStatut)
);

CREATE TABLE QuotaReservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nbReservation INT NOT NULL
);

CREATE TABLE PenaliteQuota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nbJourPenalite INT NOT NULL,
    idTypeAdherant INT NOT NULL,
    FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE JourFerier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL
);

CREATE TABLE QuotaReservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nbReservation INT NOT NULL,
    idTypeAdherant INT NOT NULL,
    FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE QuotaProlongement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nbProlongement INT NOT NULL,
    idTypeAdherant INT NOT NULL,
    FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);