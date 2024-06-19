-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema samplesphere
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema samplesphere
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `samplesphere` DEFAULT CHARACTER SET utf8 ;
USE `samplesphere` ;

-- -----------------------------------------------------
-- Table `samplesphere`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `samplesphere`.`Usuario` (
  `email` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`));


-- -----------------------------------------------------
-- Table `samplesphere`.`Perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `samplesphere`.`Perfil` (
  `username` VARCHAR(45) NOT NULL,
  `qntAlbuns` INT NOT NULL,
  `Usuario_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_Perfil_Usuario`
    FOREIGN KEY (`Usuario_email`)
    REFERENCES `samplesphere`.`Usuario` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `samplesphere`.`Artista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `samplesphere`.`Artista` (
  `idArtista` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `qntAlbuns` INT DEFAULT 0,
  PRIMARY KEY (`idArtista`));


-- -----------------------------------------------------
-- Table `samplesphere`.`Album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `samplesphere`.`Album` (
  `idAlbum` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `nota` DOUBLE NOT NULL,
  `generoMusical` VARCHAR(45) NOT NULL,
  `faixaFavorita` VARCHAR(45) NOT NULL,
  `Artista_idArtista` INT NOT NULL,
  PRIMARY KEY (`idAlbum`),
  CONSTRAINT `fk_Album_Artista1`
    FOREIGN KEY (`Artista_idArtista`)
    REFERENCES `samplesphere`.`Artista` (`idArtista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `samplesphere`.`Perfil_avalia_Album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `samplesphere`.`Perfil_avalia_Album` (
  `Perfil_username` VARCHAR(45) NOT NULL,
  `Album_idAlbum` INT NOT NULL,
  PRIMARY KEY (`Perfil_username`, `Album_idAlbum`),
  CONSTRAINT `fk_Perfil_has_Album_Perfil1`
    FOREIGN KEY (`Perfil_username`)
    REFERENCES `samplesphere`.`Perfil` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Perfil_has_Album_Album1`
    FOREIGN KEY (`Album_idAlbum`)
    REFERENCES `samplesphere`.`Album` (`idAlbum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

DROP TRIGGER IF EXISTS samplesphere.increment_qntAlbuns;
DELIMITER $$
CREATE TRIGGER increment_qntAlbuns
AFTER INSERT ON Album
FOR EACH ROW
BEGIN
  UPDATE Artista
  SET qntAlbuns = qntAlbuns + 1
  WHERE idArtista = NEW.Artista_idArtista;
END $$

DELIMITER ;

SELECT * FROM Usuario;
SELECT * FROM Artista;
SELECT * FROM Album;
SELECT * FROM Perfil;
