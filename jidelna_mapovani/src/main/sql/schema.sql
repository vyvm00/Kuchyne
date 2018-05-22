-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jidelna
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jidelna
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jidelna` DEFAULT CHARACTER SET utf8 ;
USE `jidelna` ;

-- -----------------------------------------------------
-- Table `jidelna`.`sklad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jidelna`.`sklad` ;

CREATE TABLE IF NOT EXISTS `jidelna`.`sklad` (
  `id_sklad` INT NOT NULL AUTO_INCREMENT,
  `nazev` VARCHAR(45) NULL,
  `adresa` VARCHAR(45) NULL,
  `plocha` INT NULL,
  PRIMARY KEY (`id_sklad`),
  UNIQUE INDEX `id_sklad_UNIQUE` (`id_sklad` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jidelna`.`surovina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jidelna`.`surovina` ;

CREATE TABLE IF NOT EXISTS `jidelna`.`surovina` (
  `id_surovina` INT NOT NULL AUTO_INCREMENT,
  `nazev` VARCHAR(45) NULL,
  `merna_jednotka` VARCHAR(45) NULL,
  PRIMARY KEY (`id_surovina`),
  UNIQUE INDEX `id_surovina_UNIQUE` (`id_surovina` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jidelna`.`surovina_na_sklade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jidelna`.`surovina_na_sklade` ;

CREATE TABLE IF NOT EXISTS `jidelna`.`surovina_na_sklade` (
  `mnozstvi` INT NOT NULL,
  `minimalni_mnozstvi` INT NULL,
  `id_sklad` INT NOT NULL,
  `id_surovina` INT NOT NULL,
  INDEX `fk_surovina_na_sklade_sklad_idx` (`id_sklad` ASC),
  INDEX `fk_surovina_na_sklade_surovina1_idx` (`id_surovina` ASC),
  PRIMARY KEY (`id_sklad`, `id_surovina`),
  CONSTRAINT `fk_surovina_na_sklade_sklad`
  FOREIGN KEY (`id_sklad`)
  REFERENCES `jidelna`.`sklad` (`id_sklad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_surovina_na_sklade_surovina1`
  FOREIGN KEY (`id_surovina`)
  REFERENCES `jidelna`.`surovina` (`id_surovina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jidelna`.`recept`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jidelna`.`recept` ;

CREATE TABLE IF NOT EXISTS `jidelna`.`recept` (
  `id_recept` INT NOT NULL AUTO_INCREMENT,
  `nazev` VARCHAR(45) NULL,
  `popis` TEXT NULL,
  `cas_pripravy` INT NULL,
  `cena_porce` INT NULL,
  PRIMARY KEY (`id_recept`),
  UNIQUE INDEX `id_recept_UNIQUE` (`id_recept` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jidelna`.`jidlo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jidelna`.`jidlo` ;

CREATE TABLE IF NOT EXISTS `jidelna`.`jidlo` (
  `id_jidlo` INT NOT NULL AUTO_INCREMENT,
  `datum` DATE NULL,
  `pocet_porci` INT NULL,
  `id_recept` INT NOT NULL,
  PRIMARY KEY (`id_jidlo`),
  UNIQUE INDEX `id_jidlo_UNIQUE` (`id_jidlo` ASC),
  INDEX `fk_jidlo_recept1_idx` (`id_recept` ASC),
  CONSTRAINT `fk_jidlo_recept1`
  FOREIGN KEY (`id_recept`)
  REFERENCES `jidelna`.`recept` (`id_recept`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jidelna`.`surovina_v_receptu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jidelna`.`surovina_v_receptu` ;

CREATE TABLE IF NOT EXISTS `jidelna`.`surovina_v_receptu` (
  `mnozstvi` INT NOT NULL,
  `id_surovina` INT NOT NULL,
  `id_recept` INT NOT NULL,
  INDEX `fk_surovina_v_receptu_surovina1_idx` (`id_surovina` ASC),
  INDEX `fk_surovina_v_receptu_recept1_idx` (`id_recept` ASC),
  CONSTRAINT `fk_surovina_v_receptu_surovina1`
  FOREIGN KEY (`id_surovina`)
  REFERENCES `jidelna`.`surovina` (`id_surovina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_surovina_v_receptu_recept1`
  FOREIGN KEY (`id_recept`)
  REFERENCES `jidelna`.`recept` (`id_recept`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
