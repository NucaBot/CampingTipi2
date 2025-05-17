-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:8889
-- Tiempo de generación: 25-03-2025 a las 16:25:24
-- Versión del servidor: 5.7.39
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sys`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `fechaNacimiento` varchar(45) NOT NULL,
  `correoElectronico` varchar(45) NOT NULL,
  `preferenciasCliente` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idCliente`, `nombre`, `apellidos`, `password`, `dni`, `direccion`, `telefono`, `fechaNacimiento`, `correoElectronico`, `preferenciasCliente`) VALUES
(1, 'James', 'Williams', 'aea', '12314567R', 'Calle Petunias, 25 Madrid', '657349824', '02/02/1985', 'awilliams@gmail.com', 'En familia'),
(2, 'Marta', 'Wangeneberg', 'aeaplus', '35221135J', 'Calle Timón, 32 Madrid', '657349843', '28/12/1995', 'mwangeneberg@gmail.com', 'Solo'),
(3, 'Marco', 'Dosreis', 'aea123', '1345624R', 'Calle General, 4 Madrid', '673462891', '01/01/1998', 'marcodosreis@gmail.com', 'En pareja'),
(4, 'Raquel', 'Marcos', 'aea321', '45321764M', 'Calle Preciados, 5 Valencia', '689450923', '13/05/1992', 'rmarcos@gmail.com', 'En grupo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `idEstado` int(11) NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`idEstado`, `estado`) VALUES
(1, 'Reservada'),
(2, 'Confirmada'),
(3, 'Cancelada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos`
--

CREATE TABLE `metodos` (
  `idMetodo` int(11) NOT NULL,
  `metodo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `metodos`
--

INSERT INTO `metodos` (`idMetodo`, `metodo`) VALUES
(1, 'Tarjeta'),
(2, 'Bizum');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parcelas`
--

CREATE TABLE `parcelas` (
  `idParcela` int(11) NOT NULL,
  `tipoParcela` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  `precioNoche` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `parcelas`
--

INSERT INTO `parcelas` (`idParcela`, `tipoParcela`, `descripcion`, `foto`, `precioNoche`) VALUES
(1, 'Pequeña', 'Para tienda pequeña', NULL, '40 €'),
(2, 'Mediana', 'Para tienda mediana y grande', NULL, '50 €'),
(3, 'Grande', 'Para grupos', NULL, '60 €'),
(4, 'Premium', 'Junto al lago', NULL, '70 €');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `idReserva` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idParcela` int(11) NOT NULL,
  `adultos` int(11) NOT NULL,
  `menores` int(11) NOT NULL,
  `vehiculos` int(11) NOT NULL,
  `tomaLuz` int(11) NOT NULL,
  `tomaAgua` int(11) NOT NULL,
  `fechaEntrada` date NOT NULL,
  `fechaSalida` date NOT NULL,
  `metodoPago` int(11) NOT NULL,
  `estadoReserva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`idReserva`, `idCliente`, `idParcela`, `adultos`, `menores`, `vehiculos`, `tomaLuz`, `tomaAgua`, `fechaEntrada`, `fechaSalida`, `metodoPago`, `estadoReserva`) VALUES
(1, 2, 4, 1, 0, 0, 0, 0, '2025-05-20', '2025-05-25', 1, 1),
(2, 3, 2, 2, 1, 1, 1, 1, '2025-04-10', '2025-04-20', 2, 2),
(3, 4, 3, 5, 3, 2, 1, 1, '2025-08-20', '2025-08-31', 1, 1),
(4, 1, 1, 2, 3, 1, 1, 1, '2025-06-01', '2025-06-07', 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifas`
--

CREATE TABLE `tarifas` (
  `idTarifa` int(11) NOT NULL,
  `vehiculo` double NOT NULL,
  `adulto` double NOT NULL,
  `menor` double NOT NULL,
  `tomaLuz` double NOT NULL,
  `tomaAgua` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `metodos`
--
ALTER TABLE `metodos`
  ADD PRIMARY KEY (`idMetodo`);

--
-- Indices de la tabla `parcelas`
--
ALTER TABLE `parcelas`
  ADD PRIMARY KEY (`idParcela`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`idReserva`);

--
-- Indices de la tabla `tarifas`
--
ALTER TABLE `tarifas`
  ADD PRIMARY KEY (`idTarifa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `estados`
--
ALTER TABLE `estados`
  MODIFY `idEstado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `metodos`
--
ALTER TABLE `metodos`
  MODIFY `idMetodo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `parcelas`
--
ALTER TABLE `parcelas`
  MODIFY `idParcela` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `idReserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tarifas`
--
ALTER TABLE `tarifas`
  MODIFY `idTarifa` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
