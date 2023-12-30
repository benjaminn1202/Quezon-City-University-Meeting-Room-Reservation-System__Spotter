-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2023 at 08:52 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spotter_db`
--

-- Create the database
CREATE DATABASE spotter_db;

-- Switch to the newly created database
USE spotter_db;

-- --------------------------------------------------------

--
-- Table structure for table `admin_accounts`
--

CREATE TABLE `admin_accounts` (
  `account_id` int(11) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isVerified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_accounts`
--

INSERT INTO `admin_accounts` (`account_id`, `lastname`, `firstname`, `middlename`, `email`, `password`, `isVerified`) VALUES
(1, 'Rollan', 'Benjamin', 'Bool', 'benj.rollan.02@gmail.com', '12345', 1),
(2, 'Rollan', 'Benjamin', 'Bool', 'rollmin02@gmail.com', '12345', 1),
(3, 'Batumkal', 'Abigurl', 'Grayhedge', 'abigurl.batumbal.01@gmail.com', '12345', 0),
(4, 'Dela Cruz', 'Paula Joy', '', 'email@email.com', 'ganda', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL,
  `room_number` int(11) NOT NULL,
  `target_date` date NOT NULL,
  `time_range` enum('8am - 9am','9am - 10am','10am - 11am','11am - 12pm','12pm - 1pm','1pm - 2pm','2pm - 3pm','3pm - 4pm','4pm - 5pm') NOT NULL,
  `requester_id` int(11) DEFAULT NULL,
  `attendees` text DEFAULT NULL,
  `status` enum('Pending','Declined','Scheduled','Unattended','Attended','Expired') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `room_number`, `target_date`, `time_range`, `requester_id`, `attendees`, `status`) VALUES
(1, 1, '2023-11-23', '8am - 9am', 1, 'Benjamin Rollan,Carl Molina,Franklin Milloren,Luis Rualo,David Ramos', 'Unattended'),
(2, 1, '2023-11-23', '9am - 10am', 1, 'Benjamin Rollan,Carl Molina,Franklin Milloren,Luis Rualo,David Ramos', 'Unattended'),
(3, 1, '2023-11-24', '8am - 9am', 1, 'Benjamin Rollan,Franklin Milloren,David Ramuz,Carl Molina,Luis Rualo', 'Declined'),
(4, 1, '2023-11-25', '8am - 9am', 1, 'Benj,Benj,BEnj,Benj,Benj', 'Attended'),
(5, 1, '2023-12-01', '8am - 9am', 2, 'Benjamin Rollan,John David Ramos,Franklin Milloren,John Carlo Raborar,Jerwin Masagca,Luis Rualo,John Karl Molina,Jerom Corpuz,Jeian Jasper Obelidor,Fletcher Peter Hernandez', 'Scheduled');

-- --------------------------------------------------------

--
-- Table structure for table `student_accounts`
--

CREATE TABLE `student_accounts` (
  `account_id` int(11) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `student_number` varchar(20) NOT NULL,
  `program` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isVerified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_accounts`
--

INSERT INTO `student_accounts` (`account_id`, `lastname`, `firstname`, `middlename`, `student_number`, `program`, `email`, `password`, `isVerified`) VALUES
(1, 'Rualo', 'Luis Angelo', '', '22-0101', 'Bachelor of Science in Information Technology(BSIT)', 'rualo.luis.02@gmail.com', '12345', 1),
(2, 'Ramos', 'John David', 'Dinosaur', '22-01234', 'Bachelor of Science in Information Technology(BSIT)', 'ramos.john.dino.02@gmail.com', '12345', 1),
(3, 'Molina', 'John Karl ', 'Ponteras', '22-0117', 'Bachelor of Science in Information Technology(BSIT)', 'molinajohnkarl@gmail.com', 'april282004', 1),
(4, 'Molina', 'John Karl', 'Dinosaur', '22-0123', 'Bachelor of Science in Information Technology(BSIT)', 'molina.karl.02@gmail.com', '12345', 0),
(5, 'Batumbakal', 'Aristotle', 'Sun', '22-0134', 'Bachelor of Science in Information Technology(BSIT)', 'aristotle.batumbakal.01@gmail.com', '12345', 0),
(6, 'Rollan', 'Benjamin', 'Bool', '22-0132', 'Bachelor of Science in Information Technology(BSIT)', 'benj.rollan.02@gmail.com', 'mabait', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_accounts`
--
ALTER TABLE `admin_accounts`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `requester_id` (`requester_id`);

--
-- Indexes for table `student_accounts`
--
ALTER TABLE `student_accounts`
  ADD PRIMARY KEY (`account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_accounts`
--
ALTER TABLE `admin_accounts`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `student_accounts`
--
ALTER TABLE `student_accounts`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`requester_id`) REFERENCES `student_accounts` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
