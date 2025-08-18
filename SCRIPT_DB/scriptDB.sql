CREATE SCHEMA `seguimiento_comida_gastos_db` ;

CREATE TABLE `seguimiento_comida_gastos_db`.`comida` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `precio` INT NOT NULL,
  PRIMARY KEY (`id`));
