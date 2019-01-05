-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema new_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema new_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `new_schema` DEFAULT CHARACTER SET utf8 ;
USE `new_schema` ;

-- -----------------------------------------------------
-- Table `new_schema`.`area_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`area_type` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `parent` INT(11) NULL DEFAULT NULL,
  `child_order` INT(11) NOT NULL DEFAULT '0',
  `description` TEXT NULL DEFAULT NULL,
  `gid` CHAR(36) NOT NULL,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`area` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gid` CHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `type` INT(11) NULL DEFAULT NULL,
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `begin_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `ended` VARCHAR(1) NOT NULL DEFAULT '0',
  `comment` VARCHAR(255) NOT NULL DEFAULT '',
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_area_area_type1_idx` (`type` ASC),
  CONSTRAINT `fk_area_area_type1`
    FOREIGN KEY (`type`)
    REFERENCES `new_schema`.`area_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 118416
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`gender`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`gender` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `parent` INT(11) NULL DEFAULT NULL,
  `child_order` INT(11) NOT NULL DEFAULT '0',
  `description` TEXT NULL DEFAULT NULL,
  `gid` CHAR(36) NOT NULL,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`artist_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`artist_type` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `parent` INT(11) NULL DEFAULT NULL,
  `child_order` INT(11) NOT NULL DEFAULT '0',
  `description` TEXT NULL DEFAULT NULL,
  `gid` CHAR(36) NOT NULL,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`artist` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gid` CHAR(36) CHARACTER SET 'utf8' NOT NULL,
  `name` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `sort_name` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `begin_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `type` INT(11) NULL DEFAULT NULL,
  `area` INT(11) NULL DEFAULT NULL,
  `gender` INT(11) NULL DEFAULT NULL,
  `comment` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL DEFAULT '',
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ended` CHAR(1) CHARACTER SET 'utf8' NOT NULL DEFAULT '0',
  `begin_area` INT(11) NULL DEFAULT NULL,
  `end_area` INT(11) NULL DEFAULT NULL,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_artist_gender_idx` (`gender` ASC),
  INDEX `fk_artist_artist_type1_idx` (`type` ASC),
  INDEX `fk_artist_area1_idx` (`area` ASC),
  CONSTRAINT `fk_artist_gender`
    FOREIGN KEY (`gender`)
    REFERENCES `new_schema`.`gender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_artist_artist_type1`
    FOREIGN KEY (`type`)
    REFERENCES `new_schema`.`artist_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_artist_area1`
    FOREIGN KEY (`area`)
    REFERENCES `new_schema`.`area` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1728813
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `new_schema`.`artist_alias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`artist_alias` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `artist` INT(11) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `locale` TEXT NULL DEFAULT NULL,
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` INT(11) NULL DEFAULT NULL,
  `sort_name` VARCHAR(255) NOT NULL,
  `begin_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `primary_for_locale` CHAR(1) NOT NULL DEFAULT '0',
  `ended` CHAR(1) NOT NULL DEFAULT '0',
  UNIQUE INDEX `id` (`id` ASC),
  INDEX `fk_artist_alias_1_idx` (`artist` ASC),
  CONSTRAINT `fk_artist_alias_1`
    FOREIGN KEY (`artist`)
    REFERENCES `new_schema`.`artist` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 233778
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`artist_alias_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`artist_alias_type` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  `parent` INT(11) NULL DEFAULT NULL,
  `child_order` INT(11) NOT NULL DEFAULT '0',
  `description` TEXT NULL DEFAULT NULL,
  `gid` CHAR(36) NOT NULL,
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`artist_credit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`artist_credit` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `artist_count` SMALLINT(6) NOT NULL,
  `ref_count` INT(11) NULL DEFAULT '0',
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2300020
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`artist_credit_name`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`artist_credit_name` (
  `artist_credit` INT(11) NOT NULL,
  `position` SMALLINT(6) NOT NULL,
  `artist` INT(11) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `join_phrase` TEXT NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`artist_rating_raw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`artist_rating_raw` (
  `artist` INT(11) NOT NULL,
  `editor` INT(11) NOT NULL,
  `rating` SMALLINT(6) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`country_area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`country_area` (
  `area` INT(11) NOT NULL,
  PRIMARY KEY (`area`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`isrc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`isrc` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `recording` INT(11) NOT NULL,
  `isrc` CHAR(12) NOT NULL,
  `source` SMALLINT(6) NULL DEFAULT NULL,
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 956682
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`l_artist_artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`l_artist_artist` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `link` INT(11) NOT NULL,
  `entity0` INT(11) NOT NULL,
  `entity1` INT(11) NOT NULL,
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `link_order` INT(11) NOT NULL DEFAULT '0',
  `entity0_credit` TEXT NOT NULL,
  `entity1_credit` TEXT NOT NULL,
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 420770
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`label`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`label` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gid` CHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `begin_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `begin_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_year` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_month` SMALLINT(6) NULL DEFAULT NULL,
  `end_date_day` SMALLINT(6) NULL DEFAULT NULL,
  `label_code` INT(11) NULL DEFAULT NULL,
  `type` INT(11) NULL DEFAULT NULL,
  `area` INT(11) NULL DEFAULT NULL,
  `comment` VARCHAR(255) NOT NULL DEFAULT '',
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ended` CHAR(1) NOT NULL DEFAULT '0',
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 162172
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`language` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `iso_code_2t` CHAR(3) NULL DEFAULT NULL,
  `iso_code_2b` CHAR(3) NULL DEFAULT NULL,
  `iso_code_1` CHAR(2) NULL DEFAULT NULL,
  `name` VARCHAR(100) NOT NULL,
  `frequency` INT(11) NOT NULL DEFAULT '0',
  `iso_code_3` CHAR(3) NULL DEFAULT NULL,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7845
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`recording`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`recording` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gid` CHAR(36) CHARACTER SET 'utf8' NOT NULL,
  `name` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `artist_credit` INT(11) NOT NULL,
  `length` INT(11) NULL DEFAULT NULL,
  `comment` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL DEFAULT '',
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `video` CHAR(1) CHARACTER SET 'utf8' NOT NULL DEFAULT '0',
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 23489406
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `new_schema`.`release_group_primary_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`release_group_primary_type` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `parent` INT(11) NULL DEFAULT NULL,
  `child_order` INT(11) NOT NULL DEFAULT '0',
  `description` TEXT NULL DEFAULT NULL,
  `gid` CHAR(36) NOT NULL,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`release_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`release_group` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gid` CHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `artist_credit` INT(11) NOT NULL,
  `type` INT(11) NULL DEFAULT NULL,
  `comment` VARCHAR(255) NOT NULL DEFAULT '',
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_release_group_release_group_primary_type1_idx` (`type` ASC),
  CONSTRAINT `fk_release_group_release_group_primary_type1`
    FOREIGN KEY (`type`)
    REFERENCES `new_schema`.`release_group_primary_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2050413
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`release_country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`release_country` (
  `release` INT(11) NOT NULL,
  `date_year` SMALLINT(6) NULL DEFAULT NULL,
  `date_month` SMALLINT(6) NULL DEFAULT NULL,
  `date_day` SMALLINT(6) NULL DEFAULT NULL,
  `country` VARCHAR(45) NOT NULL,
  `release_id` BIGINT(20) UNSIGNED NOT NULL,
  `release_release_country_release` INT(11) NOT NULL,
  PRIMARY KEY (`release`, `country`, `release_id`, `release_release_country_release`),
  INDEX `fk_release_country_country_area1_idx` (`country` ASC),
  INDEX `fk_release_country_release1_idx` (`release_release_country_release` ASC, `release` ASC),
  CONSTRAINT `fk_release_country_country_area1`
    FOREIGN KEY (`country`)
    REFERENCES `new_schema`.`country_area` (`area`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_release_country_release1`
    FOREIGN KEY (`release_release_country_release` , `release`)
    REFERENCES `new_schema`.`release` (`release_country_release` , `release_country_release`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`release`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`release` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gid` CHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `artist_credit` INT(11) NOT NULL,
  `release_group` INT(11) NOT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `packaging` INT(11) NULL DEFAULT NULL,
  `language` INT(11) NULL DEFAULT NULL,
  `script` INT(11) NULL DEFAULT NULL,
  `barcode` VARCHAR(255) NULL DEFAULT NULL,
  `comment` VARCHAR(255) NOT NULL DEFAULT '',
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `quality` SMALLINT(6) NOT NULL DEFAULT '-1',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `release_country_release` INT(11) NOT NULL,
  UNIQUE INDEX `id` (`id` ASC),
  PRIMARY KEY (`id`, `release_country_release`),
  INDEX `fk_release_language1_idx` (`language` ASC),
  INDEX `fk_release_artist_credit1_idx` (`artist_credit` ASC),
  INDEX `fk_release_release_group1_idx` (`release_group` ASC),
  INDEX `fk_release_release_country1_idx` (`release_country_release` ASC),
  CONSTRAINT `fk_release_language1`
    FOREIGN KEY (`language`)
    REFERENCES `new_schema`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_release_artist_credit1`
    FOREIGN KEY (`artist_credit`)
    REFERENCES `new_schema`.`artist_credit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_release_release_group1`
    FOREIGN KEY (`release_group`)
    REFERENCES `new_schema`.`release_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_release_release_country1`
    FOREIGN KEY (`release_country_release`)
    REFERENCES `new_schema`.`release_country` (`release`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2275220
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`release_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`release_status` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `parent` INT(11) NULL DEFAULT NULL,
  `child_order` INT(11) NOT NULL DEFAULT '0',
  `description` TEXT NULL DEFAULT NULL,
  `gid` CHAR(36) NOT NULL,
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new_schema`.`track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schema`.`track` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `gid` CHAR(36) NOT NULL,
  `recording` INT(11) NOT NULL,
  `medium` INT(11) NOT NULL,
  `position` INT(11) NOT NULL,
  `number` TEXT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `artist_credit` INT(11) NOT NULL,
  `length` INT(11) NULL DEFAULT NULL,
  `edits_pending` INT(11) NOT NULL DEFAULT '0',
  `last_updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_data_track` CHAR(1) NOT NULL DEFAULT '0',
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 26541209
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

