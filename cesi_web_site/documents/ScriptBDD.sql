CREATE TABLE Article (
  idArticle INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Page_idPage INTEGER UNSIGNED NOT NULL,
  Niveau_idNiveau INTEGER UNSIGNED NOT NULL,
  Categorie_idCategorie INTEGER UNSIGNED NOT NULL,
  contenuArticle VARCHAR(255) NULL,
  voteArticle INTEGER UNSIGNED NULL,
  PRIMARY KEY(idArticle, Page_idPage),
  INDEX Article_FKIndex1(Categorie_idCategorie),
  INDEX Article_FKIndex2(Page_idPage),
  INDEX Article_FKIndex3(Niveau_idNiveau)
);

CREATE TABLE Categorie (
  idCategorie INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  libelleCategorie VARCHAR(255) NULL,
  PRIMARY KEY(idCategorie)
);

CREATE TABLE Commentaire (
  idCommentaire INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Utilisateur_idUtilisateur INTEGER UNSIGNED NOT NULL,
  Page_idPage INTEGER UNSIGNED NOT NULL,
  contenuCommentaire VARCHAR(255) NULL,
  dateCreationCommentaire DATE NULL,
  dateDerniereModificationCommentaire DATE NULL,
  PRIMARY KEY(idCommentaire),
  INDEX Commentaire_FKIndex1(Page_idPage),
  INDEX Commentaire_FKIndex2(Utilisateur_idUtilisateur)
);

CREATE TABLE Competence (
  idCompetence INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Niveau_idNiveau INTEGER UNSIGNED NOT NULL,
  TypeCompetence_idTypeCompetence INTEGER UNSIGNED NOT NULL,
  nomCompetence VARCHAR(255) NULL,
  PRIMARY KEY(idCompetence),
  INDEX Competence_FKIndex1(TypeCompetence_idTypeCompetence),
  INDEX Competence_FKIndex2(Niveau_idNiveau)
);

CREATE TABLE Contributeur (
  idContributeur INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Utilisateur_idUtilisateur INTEGER UNSIGNED NOT NULL,
  Promotion_idPromotion INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idContributeur, Utilisateur_idUtilisateur),
  INDEX Contributeur_FKIndex2(Promotion_idPromotion),
  INDEX Contributeur_FKIndex2(Utilisateur_idUtilisateur)
);

CREATE TABLE CV (
  idCV INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Page_idPage INTEGER UNSIGNED NOT NULL,
  NiveauEtude_idNiveauEtude INTEGER UNSIGNED NOT NULL,
  experiencesProffessionnellesCV VARCHAR(255) NULL,
  scolariteCV VARCHAR(255) NULL,
  loisirsCV VARCHAR(255) NULL,
  contenuPersonnaliseCV VARCHAR(255) NULL,
  PRIMARY KEY(idCV, Page_idPage),
  INDEX CV_FKIndex1(Page_idPage),
  INDEX CV_FKIndex2(NiveauEtude_idNiveauEtude)
);

CREATE TABLE CV_has_Competence (
  CV_Page_idPage INTEGER UNSIGNED NOT NULL,
  CV_idCV INTEGER UNSIGNED NOT NULL,
  Competence_idCompetence INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(CV_Page_idPage, CV_idCV, Competence_idCompetence),
  INDEX CV_has_Competence_FKIndex1(CV_idCV, CV_Page_idPage),
  INDEX CV_has_Competence_FKIndex2(Competence_idCompetence)
);

CREATE TABLE Fichier (
  idFichier INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomOrigineFichier VARCHAR(255) NULL,
  nomReelFichier VARCHAR(255) NULL,
  dateUpload DATE NULL,
  PRIMARY KEY(idFichier)
);

CREATE TABLE Fichier_has_Page (
  Fichier_idFichier INTEGER UNSIGNED NOT NULL,
  Page_idPage INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(Fichier_idFichier, Page_idPage),
  INDEX Fichier_has_Page_FKIndex1(Fichier_idFichier),
  INDEX Fichier_has_Page_FKIndex2(Page_idPage)
);

CREATE TABLE Niveau (
  idNiveau INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  libelleNiveau VARCHAR(255) NULL,
  PRIMARY KEY(idNiveau)
);

CREATE TABLE NiveauEtude (
  idNiveauEtude INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  libelleNiveauEtude VARCHAR(255) NULL,
  PRIMARY KEY(idNiveauEtude)
);

CREATE TABLE Page (
  idPage INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Contributeur_Utilisateur_idUtilisateur INTEGER UNSIGNED NOT NULL,
  Contributeur_idContributeur INTEGER UNSIGNED NOT NULL,
  dateCreationPage DATE NULL,
  dateDerniereModificationPage DATE NULL,
  typePage ENUM NULL,
  PRIMARY KEY(idPage),
  INDEX Page_FKIndex1(Contributeur_idContributeur, Contributeur_Utilisateur_idUtilisateur)
);

CREATE TABLE Promotion (
  idPromotion INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  libellePromotion VARCHAR(255) NULL,
  anneeEntreePromotion DATE NULL,
  PRIMARY KEY(idPromotion)
);

CREATE TABLE TypeCompetence (
  idTypeCompetence INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  libelleCompetence VARCHAR(255) NULL,
  PRIMARY KEY(idTypeCompetence)
);

CREATE TABLE Utilisateur (
  idUtilisateur INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomUtilisateur VARCHAR(255) NULL,
  prenomUtilisateur VARCHAR(255) NULL,
  dateNaissanceUtilisateur DATE NULL,
  pseudoUtilisateur VARCHAR(255) NULL,
  droitsUtilisateur ENUM NULL,
  PRIMARY KEY(idUtilisateur)
);


