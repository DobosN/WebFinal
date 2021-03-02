-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Már 02. 22:05
-- Kiszolgáló verziója: 10.4.14-MariaDB
-- PHP verzió: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `inandout`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `attendance`
--

CREATE TABLE `attendance` (
  `attendanceID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `year` int(10) NOT NULL,
  `month` int(2) NOT NULL,
  `day` int(2) NOT NULL,
  `started` varchar(5) DEFAULT NULL,
  `startOfBreak` varchar(5) DEFAULT NULL,
  `finishOfBreak` varchar(5) DEFAULT NULL,
  `finished` varchar(5) DEFAULT NULL,
  `hoursOfDay` varchar(5) DEFAULT NULL,
  `typeOfDay` varchar(15) DEFAULT 'Munkanap'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `attendance`
--

INSERT INTO `attendance` (`attendanceID`, `userID`, `year`, `month`, `day`, `started`, `startOfBreak`, `finishOfBreak`, `finished`, `hoursOfDay`, `typeOfDay`) VALUES
(1, 11, 2021, 2, 20, '08:00', '12:00', '12:30', '16:30', '08:00', 'Munkanap'),
(2, 11, 2021, 2, 21, '08:45', '12:50', '13:20', '17:15', '08:00', 'Munkanap'),
(3, 11, 2021, 2, 22, '08:00', '12:00', '12:20', '16:30', '08:10', 'Munkanap'),
(4, 11, 2021, 2, 23, '07:00', '11:30', '12:30', '17:30', '09:30', 'Munkanap'),
(5, 11, 2021, 2, 27, '07:30', '12:15', '12:30', '16:00', '08:15', 'Munkanap'),
(6, 11, 2021, 1, 29, '07:30', '12:15', '12:30', '16:00', '08:15', 'Munkanap'),
(7, 11, 2021, 1, 30, '08:30', '12:00', '12:30', '16:00', '07:30', 'Munkanap'),
(8, 11, 2021, 1, 31, '09:30', '12:50', '12:20', '17:00', '07:00', 'Munkanap'),
(9, 11, 2021, 3, 1, '08:00', '12:00', '12:15', '16:15', '08:00', 'Munkanap'),
(10, 11, 2021, 3, 2, '09:30', '12:00', '12:30', '16:30', '07:00', 'Munkanap');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `daytypes`
--

CREATE TABLE `daytypes` (
  `dtID` int(11) NOT NULL,
  `type` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `daytypes`
--

INSERT INTO `daytypes` (`dtID`, `type`) VALUES
(1, 'Munkanap'),
(2, 'Szabadnap'),
(3, 'Szabadság'),
(4, 'Beteg szabadság');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hours`
--

CREATE TABLE `hours` (
  `hoursID` int(11) NOT NULL,
  `hours` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `hours`
--

INSERT INTO `hours` (`hoursID`, `hours`) VALUES
(1, '00'),
(2, '01'),
(3, '03'),
(4, '04'),
(5, '05'),
(6, '06'),
(7, '07'),
(8, '08'),
(9, '09'),
(10, '10'),
(11, '11'),
(12, '12'),
(13, '13'),
(14, '14'),
(15, '15'),
(16, '16'),
(17, '17'),
(18, '18'),
(19, '19'),
(20, '20'),
(21, '21'),
(22, '22'),
(23, '23');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `leader_employe`
--

CREATE TABLE `leader_employe` (
  `leID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `leaderID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `leader_employe`
--

INSERT INTO `leader_employe` (`leID`, `userID`, `leaderID`) VALUES
(1, 1, 9),
(2, 1, 8),
(3, 2, 8),
(4, 3, 8),
(5, 4, 8),
(6, 5, 8),
(7, 6, 8),
(8, 5, 9),
(9, 6, 9),
(10, 11, 9),
(11, 12, 9);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `minutes`
--

CREATE TABLE `minutes` (
  `minutesID` int(11) NOT NULL,
  `minutes` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `minutes`
--

INSERT INTO `minutes` (`minutesID`, `minutes`) VALUES
(1, '00'),
(2, '01'),
(3, '02'),
(4, '03'),
(5, '04'),
(6, '05'),
(7, '06'),
(8, '07'),
(9, '08'),
(10, '09'),
(11, '10'),
(12, '11'),
(13, '12'),
(14, '13'),
(15, '14'),
(16, '15'),
(17, '16'),
(18, '17'),
(19, '18'),
(20, '19'),
(21, '20'),
(22, '21'),
(23, '22'),
(24, '23'),
(25, '24'),
(26, '25'),
(27, '26'),
(28, '27'),
(29, '28'),
(30, '29'),
(31, '30'),
(32, '31'),
(33, '32'),
(34, '33'),
(35, '34'),
(36, '35'),
(37, '36'),
(38, '37'),
(39, '38'),
(40, '39'),
(41, '40'),
(42, '41'),
(43, '42'),
(44, '43'),
(45, '44'),
(46, '45'),
(47, '46'),
(48, '47'),
(49, '48'),
(50, '49'),
(51, '50'),
(52, '51'),
(53, '52'),
(54, '53'),
(55, '54'),
(56, '55'),
(57, '56'),
(58, '57'),
(59, '58'),
(60, '59');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `month`
--

CREATE TABLE `month` (
  `monthID` int(2) NOT NULL,
  `nameOfMonth` varchar(10) NOT NULL,
  `lengthOfMonth` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `month`
--

INSERT INTO `month` (`monthID`, `nameOfMonth`, `lengthOfMonth`) VALUES
(1, 'Január', 31),
(2, 'Február', 28),
(3, 'Március', 31),
(4, 'Árilis', 30),
(5, 'Május', 31),
(6, 'Június', 30),
(7, 'Július', 31),
(8, 'Augusztus ', 31),
(9, 'Szeptembet', 30),
(10, 'Október', 31),
(11, 'November', 30),
(12, 'December', 31);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `role`
--

CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `role`
--

INSERT INTO `role` (`roleID`, `name`) VALUES
(1, 'employe'),
(2, 'leader'),
(3, 'admin');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `active` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `users`
--

INSERT INTO `users` (`userID`, `firstName`, `lastName`, `userName`, `email`, `password`, `active`) VALUES
(1, 'Norbert', 'Dobos', 'Dobos', 'dobos@gmail.com', '123456', 1),
(2, 'Karesz', 'Mihalecz', 'karesz', 'karesz@gmail.com', '123456', 1),
(3, 'Gábi', 'Tölcséres', 'gabi', 'gabi@gmail.com', '123456', 1),
(4, 'Beni', 'Rácsaki', 'beni', 'beni@gmail.com', '123456', 1),
(5, 'Nóra', 'Nász', 'nóri', 'nóri@gmail.com', '123456', 1),
(6, 'Norbert', 'Balogh', 'norbi', 'norbi@gmail.com', '123456', 1),
(7, 'Bill', 'Gates', 'gates', 'gates@gmail.com', '123456', 1),
(8, 'Elon', 'Musk', 'elon', 'elon@gmail.com', '123456', 1),
(9, 'Tony', 'Stark', 'stark', 'stark@gmail.com', '123456', 1),
(10, 'Steve', 'Jobs', 'steve', 'Steve@gmail.com', '123456', 1),
(11, 'Norbert', 'Dobos', 'ndobos', 'ndobos@gmail.com', '123456', 1),
(12, 'Elek', 'Test', 'telek', 'telek@gmail.com', '123456', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user_role`
--

CREATE TABLE `user_role` (
  `urID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `roleID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `user_role`
--

INSERT INTO `user_role` (`urID`, `userID`, `roleID`) VALUES
(1, 1, 3),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 2),
(9, 9, 2),
(10, 10, 1),
(11, 11, 1),
(12, 12, 1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendanceID`),
  ADD KEY `userID` (`userID`);

--
-- A tábla indexei `daytypes`
--
ALTER TABLE `daytypes`
  ADD PRIMARY KEY (`dtID`);

--
-- A tábla indexei `hours`
--
ALTER TABLE `hours`
  ADD PRIMARY KEY (`hoursID`);

--
-- A tábla indexei `leader_employe`
--
ALTER TABLE `leader_employe`
  ADD PRIMARY KEY (`leID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `leaderID` (`leaderID`);

--
-- A tábla indexei `minutes`
--
ALTER TABLE `minutes`
  ADD PRIMARY KEY (`minutesID`);

--
-- A tábla indexei `month`
--
ALTER TABLE `month`
  ADD PRIMARY KEY (`monthID`),
  ADD UNIQUE KEY `nameOfMonth` (`nameOfMonth`);

--
-- A tábla indexei `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleID`);

--
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `userName` (`userName`),
  ADD UNIQUE KEY `email` (`email`);

--
-- A tábla indexei `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`urID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `roleID` (`roleID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `attendance`
--
ALTER TABLE `attendance`
  MODIFY `attendanceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT a táblához `daytypes`
--
ALTER TABLE `daytypes`
  MODIFY `dtID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `hours`
--
ALTER TABLE `hours`
  MODIFY `hoursID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT a táblához `leader_employe`
--
ALTER TABLE `leader_employe`
  MODIFY `leID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT a táblához `minutes`
--
ALTER TABLE `minutes`
  MODIFY `minutesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT a táblához `role`
--
ALTER TABLE `role`
  MODIFY `roleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT a táblához `user_role`
--
ALTER TABLE `user_role`
  MODIFY `urID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Megkötések a táblához `leader_employe`
--
ALTER TABLE `leader_employe`
  ADD CONSTRAINT `leader_employe_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  ADD CONSTRAINT `leader_employe_ibfk_2` FOREIGN KEY (`leaderID`) REFERENCES `users` (`userID`);

--
-- Megkötések a táblához `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
