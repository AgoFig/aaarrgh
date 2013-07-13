-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2013 a las 16:33:42
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `twitter`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE IF NOT EXISTS mensaje (
  id_tweet INTEGER PRIMARY KEY AUTOINCREMENT,
  tweet varchar(140) NOT NULL,
  id_user int(11) NOT NULL,
  FOREIGN KEY(id_user) REFERENCES usuario(id_user)
)  ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sigue`
--

CREATE TABLE IF NOT EXISTS sigue (
  id_seguidor int(11) NOT NULL,
  id_seguido int(11) NOT NULL,
  PRIMARY KEY (id_seguidor,id_seguido),
  FOREIGN KEY(id_seguido) REFERENCES usuario(id_user),
  FOREIGN KEY(id_seguidor)REFERENCES usuario(id_user)
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS usuario (
  id_user INTEGER PRIMARY KEY AUTOINCREMENT,
  user varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  nombre varchar(45) NOT NULL,
  apellido varchar(45) NOT NULL,
  mail varchar(45) NOT NULL
) ;


