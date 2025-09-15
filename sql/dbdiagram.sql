Project bibliotheque {
  database_type: "MySQL"
}

Table TypeAdherant {
  idTypeAdherant int [pk, increment]
  nomTypeAdherant varchar(50)
}

Table Personne {
  idPersonne int [pk, increment]
  nom varchar(50)
  dateNaissance date
  adresse varchar(50)
  mail varchar(50)
  password varchar(50)
}

Table Admin {
  idAdmin int [pk, increment]
  idPersonne int [ref: > Personne.idPersonne]
}

Table Livre {
  idLivre int [pk, increment]
  titre varchar(50)
  ISBN varchar(50)
  Edition varchar(50)
  Auteur varchar(50)
  ageLimite int
}

Table Categorie {
  idCategorie int [pk, increment]
  nomCategorie varchar(50)
}

Table Exemplaire {
  idExemplaire int [pk, increment]
  numero varchar(50)
  idLivre int [ref: > Livre.idLivre]
}

Table TypePret {
  idTypePret int [pk, increment]
  nomType varchar(50)
}

Table Statut {
  idStatut int [pk, increment]
  nomStatut varchar(50)
}

Table RegleDuree {
  idRegleDuree int [pk, increment]
  duree decimal(5,2)
  date date
  idTypeAdherant int [ref: > TypeAdherant.idTypeAdherant]
}

Table RegleNbLivre {
  idRegleNbLivre int [pk, increment]
  nbLivre int
  date date
  idTypeAdherant int [ref: > TypeAdherant.idTypeAdherant]
}

Table Adherant {
  idAdherant int [pk, increment]
  numero_adherant varchar(50)
  idPersonne int [ref: > Personne.idPersonne]
  idTypeAdherant int [ref: > TypeAdherant.idTypeAdherant]
}

Table Inscription {
  idInscription int [pk, increment]
  dateInscription datetime
  idAdherant int [ref: > Adherant.idAdherant]
}

Table Abonnement {
  idAbonnement int [pk, increment]
  dateDebut date
  dateFin date
  idAdherant int [ref: > Adherant.idAdherant]
}

Table Pret {
  idPret int [pk, increment]
  dateDebut datetime
  dateFin datetime
  idTypePret int [ref: > TypePret.idTypePret]
  idAdmin int [ref: > Admin.idAdmin]
  idAdherant int [ref: > Adherant.idAdherant]
  idExemplaire int [ref: > Exemplaire.idExemplaire]
}

Table DemandeProlongement {
  idDemande int [pk, increment]
  dateDemande date
  nbJourDemande int
  idPret int [ref: > Pret.idPret]
  idAdherant int [ref: > Adherant.idAdherant]
}

Table reservation {
  idReservation int [pk, increment]
  date date
  dateReservation date
  idAdmin int [ref: > Admin.idAdmin]
  idAdherant int [ref: > Adherant.idAdherant]
  idExemplaire int [ref: > Exemplaire.idExemplaire]
  idStatutActuel int [ref: > Statut.idStatut]
}

Table Retour {
  idRetour int [pk, increment]
  dateRetour datetime
  idPret int [ref: > Pret.idPret]
}

Table Penalite {
  idPenalite int [pk, increment]
  nbJourPenalite int
  date date
  idPret int [ref: > Pret.idPret, unique]
}

Table Livre_Categorie {
  idLivre int [ref: > Livre.idLivre]
  idCategorie int [ref: > Categorie.idCategorie]
  Note: "Primary Key (idLivre, idCategorie)"
}

Table TypeAdherant_Livre {
  idTypeAdherant int [ref: > TypeAdherant.idTypeAdherant]
  idLivre int [ref: > Livre.idLivre]
  date date
  Note: "Primary Key (idTypeAdherant, idLivre)"
}

Table StatutReservation {
  idReservation int [ref: > reservation.idReservation]
  idStatut int [ref: > Statut.idStatut]
  dateStatut date
  Note: "Primary Key (idReservation, idStatut)"
}

Table statut_demande {
  idStatutDemande int [pk, increment]
  idDemande int [ref: > DemandeProlongement.idDemande]
  idStatut int [ref: > Statut.idStatut]
  date_statut datetime
}

Table QuotaReservation {
  id int [pk, increment]
  nbReservation int
  idTypeAdherant int [ref: > TypeAdherant.idTypeAdherant]
}

Table PenaliteQuota {
  id int [pk, increment]
  nbJourPenalite int
  idTypeAdherant int [ref: > TypeAdherant.idTypeAdherant]
}

Table JourFerier {
  id int [pk, increment]
  date date
}

Table QuotaProlongement {
  id int [pk, increment]
  nbProlongement int
  idTypeAdherant int [ref: > TypeAdherant.idTypeAdherant]
}

Table Prolongement {
  id int [pk, increment]
  idAdherant int [ref: > Adherant.idAdherant]
  idPret int [ref: > Pret.idPret]
}
