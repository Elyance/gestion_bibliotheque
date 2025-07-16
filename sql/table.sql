DROP DATABASE bibliotheque;
CREATE DATABASE bibliotheque;
USE bibliotheque;
CREATE TABLE TypeAdherant(
   idTypeAdherant INTEGER NOT NULL,
   nomTypeAdherant VARCHAR(50) NOT NULL,
   PRIMARY KEY(idTypeAdherant)
);

CREATE TABLE Personne(
   idPersonne INT,
   nom VARCHAR(50),
   dateNaissance DATE NOT NULL,
   adresse VARCHAR(50),
   mail VARCHAR(50) NOT NULL,
   password VARCHAR(50),
   PRIMARY KEY(idPersonne)
);

CREATE TABLE Admin(
   idAdmin INT,
   idPersonne INT NOT NULL,
   PRIMARY KEY(idAdmin),
   FOREIGN KEY(idPersonne) REFERENCES Personne(idPersonne)
);

CREATE TABLE Livre(
   idLivre INT,
   titre VARCHAR(50) NOT NULL,
   ISBN VARCHAR(50) NOT NULL,
   Edition VARCHAR(50) NOT NULL,
   Auteur VARCHAR(50) NOT NULL,
   ageLimite INT NOT NULL,
   PRIMARY KEY(idLivre)
);

CREATE TABLE Categorie(
   idCategorie INT,
   nomCategorie VARCHAR(50) NOT NULL,
   PRIMARY KEY(idCategorie)
);

CREATE TABLE Exemplaire(
   idExemplaire INT,
   numero VARCHAR(50) NOT NULL,
   idLivre INT NOT NULL,
   PRIMARY KEY(idExemplaire),
   FOREIGN KEY(idLivre) REFERENCES Livre(idLivre)
);

CREATE TABLE TypePret(
   idTypePret INT,
   nomType VARCHAR(50) NOT NULL,
   PRIMARY KEY(idTypePret)
);

CREATE TABLE Statut(
   idStatut INT,
   nomStatut VARCHAR(50) NOT NULL,
   PRIMARY KEY(idStatut)
);

CREATE TABLE RegleDuree(
   idRegleDuree INT,
   duree DECIMAL(5,2) NOT NULL,
   date DATE NOT NULL,
   idTypeAdherant VARCHAR(55) NOT NULL,
   PRIMARY KEY(idRegleDuree),
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE RegleNbLivre(
   idRegleNbLivre INT,
   nbLivre INT NOT NULL,
   date DATE NOT NULL,
   idTypeAdherant VARCHAR(55) NOT NULL,
   PRIMARY KEY(idRegleNbLivre),
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE Adherant(
   idAdherant INT,
   idPersonne INT NOT NULL,
   idTypeAdherant VARCHAR(55) NOT NULL,
   PRIMARY KEY(idAdherant),
   UNIQUE(idPersonne),
   FOREIGN KEY(idPersonne) REFERENCES Personne(idPersonne),
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant)
);

CREATE TABLE Inscription(
   idInscription INT,
   dateInscription DATETIME NOT NULL,
   idAdherant INT NOT NULL,
   PRIMARY KEY(idInscription),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant)
);

CREATE TABLE Abonnement(
   idAbonnement INT,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   idAdherant INT NOT NULL,
   PRIMARY KEY(idAbonnement),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant)
);

CREATE TABLE Pret(
   idPret INT,
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
   idDemande INT,
   dateDemande DATE NOT NULL,
   idPret INT NOT NULL,
   PRIMARY KEY(idDemande),
   FOREIGN KEY(idPret) REFERENCES Pret(idPret)
);

CREATE TABLE Reservation(
   idReservation INT,
   date DATE NOT NULL,
   dateReservation DATE NOT NULL,
   idAdmin INT,
   idAdherant INT NOT NULL,
   idExemplaire INT NOT NULL,
   PRIMARY KEY(idReservation),
   FOREIGN KEY(idAdmin) REFERENCES Admin(idAdmin),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant),
   FOREIGN KEY(idExemplaire) REFERENCES Exemplaire(idExemplaire)
);

CREATE TABLE Retour(
   idRetour INT,
   dateRetour DATETIME NOT NULL,
   idPret INT NOT NULL,
   PRIMARY KEY(idRetour),
   FOREIGN KEY(idPret) REFERENCES Pret(idPret)
);

CREATE TABLE Penalite(
   idPenalite INT,
   nbJourPenalite INT NOT NULL,
   date DATE NOT NULL,
   idPret INT NOT NULL,
   PRIMARY KEY(idPenalite),
   UNIQUE(idPret),
   FOREIGN KEY(idPret) REFERENCES Pret(idPret)
);

CREATE TABLE LivreCategorie(
   idLivre INT,
   idCategorie INT,
   PRIMARY KEY(idLivre, idCategorie),
   FOREIGN KEY(idLivre) REFERENCES Livre(idLivre),
   FOREIGN KEY(idCategorie) REFERENCES Categorie(idCategorie)
);

CREATE TABLE TypeAdherant_Livre(
   idTypeAdherant VARCHAR(55),
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
   FOREIGN KEY(idReservation) REFERENCES Reservation(idReservation),
   FOREIGN KEY(idStatut) REFERENCES Statut(idStatut)
);
