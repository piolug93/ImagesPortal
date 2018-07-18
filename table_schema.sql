-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `roleName` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`roleName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `mydb`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_role` (
  `idUser` INT NOT NULL,
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`, `roleName`),
  INDEX `role_roleName_idx` (`roleName` ASC),
  CONSTRAINT `role_roleName`
    FOREIGN KEY (`roleName`)
    REFERENCES `mydb`.`role` (`roleName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `registredDate` TIMESTAMP NULL,
  `activated` TINYINT NOT NULL,
  `blocked` TINYINT NOT NULL,
  `lastIp` VARCHAR(45) NULL,
  PRIMARY KEY (`id`, `login`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  CONSTRAINT `user_role`
    FOREIGN KEY (`id`)
    REFERENCES `mydb`.`user_role` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `mydb`.`posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `author` INT NOT NULL,
  `title` VARCHAR(255) NULL,
  `content` TEXT NULL,
  `source` VARCHAR(200) NULL,
  `date` TIMESTAMP NULL,
  `password` VARCHAR(45) NULL,
  `secured` TINYINT NOT NULL,
  `voteUp` INT NULL DEFAULT 0,
  `voteDown` INT NULL DEFAULT 0,
  `views` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `author`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `author_idx` (`author` ASC),
  CONSTRAINT `author_post`
    FOREIGN KEY (`author`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


-- -----------------------------------------------------
-- Table `mydb`.`vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vote` (
  `id` INT NOT NULL,
  `postId` INT NOT NULL,
  `userId` INT NOT NULL,
  `type` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`, `postId`, `userId`),
  INDEX `post_vote_idx` (`postId` ASC),
  INDEX `user_vote_idx` (`userId` ASC),
  CONSTRAINT `post_vote`
    FOREIGN KEY (`postId`)
    REFERENCES `mydb`.`posts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_vote`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_polish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
