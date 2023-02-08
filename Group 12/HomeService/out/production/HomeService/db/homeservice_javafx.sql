-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2022 at 07:27 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `homeservice_javafx`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(250) CHARACTER SET utf8 NOT NULL,
  `password` varchar(250) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'Abcd1234#');

-- --------------------------------------------------------

--
-- Table structure for table `home_owners`
--

CREATE TABLE `home_owners` (
  `house_ownersID` int(10) NOT NULL,
  `title` varchar(10) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `personal_TelephoneNO` int(10) NOT NULL,
  `home_telephone_No` int(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `home_owners`
--

INSERT INTO `home_owners` (`house_ownersID`, `title`, `firstname`, `lastname`, `personal_TelephoneNO`, `home_telephone_No`, `username`, `email`, `address`) VALUES
(1, 'Miss', 'Sobi', 'Raj', 778569874, 778563214, 'Sobi', 'asd@gmail.com', 'temple road,Colombo'),
(2, 'Miss', 'Nivi', 'Uthai', 773214785, 212220202, 'Nivi123', 'zxcv@gmail.com', 'kandy road,Colombo'),
(3, 'Mr', 'Raj', 'Kumar', 774856321, 778741547, 'raj29', 'qwer@yahoo.com', 'sea,street.Colombo'),
(4, 'Miss', 'kala', 'namasivayam', 778541236, 117896540, 'kala123', 'lkjh@yahoo.com', 'Post office lane,colombo06'),
(5, 'Miss', 'mathu', 'ravi', 774856521, 778456321, 'mathu29', 'qwea@yahoo.com', 'school lane mathara'),
(6, 'Miss', 'Ratha', 'Ravi', 771252410, 212221540, 'ratha', 'ratha@gmail.com', '45, Kovil road, Kokuvil.'),
(7, 'Mr', 'Amal', 'Silva', 774512520, 212244596, 'amal', 'amal@gmail.com', '15, Kovil road, Jaffna.'),
(8, 'Mr', 'Joseph', 'Vijay', 774512520, 212241515, 'joseph', 'joseph@gmail.com', '15, temple road, Kokuvil.'),
(9, 'Mr', 'Rahu', 'Nathan', 774512150, 212225454, 'rahu', 'rahu@gmail.com', 'Kokuvil');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL,
  `home_owner_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `reservation_date` date NOT NULL,
  `reservation_start_time` time NOT NULL,
  `reservation_end_time` time NOT NULL,
  `reservation_details` varchar(250) NOT NULL,
  `submit_dateTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `worker_remark` varchar(50) NOT NULL DEFAULT 'Waiting For Approval',
  `worker_reply` varchar(250) NOT NULL DEFAULT 'Wait For Reply',
  `homeowner_reply` varchar(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `home_owner_id`, `worker_id`, `reservation_date`, `reservation_start_time`, `reservation_end_time`, `reservation_details`, `submit_dateTime`, `worker_remark`, `worker_reply`, `homeowner_reply`) VALUES
(1, 4, 1, '2021-08-12', '13:00:00', '18:00:00', 'I want watersupply for my new home.', '2021-07-31 06:20:53', 'Accepted', 'I acceped your request.', 'Thank You'),
(8, 4, 2, '2021-08-13', '15:30:00', '17:30:00', 'Repair my motorbike', '2021-08-01 12:07:39', 'Waiting For Approval', 'Wait For Reply', NULL),
(3, 6, 12, '2021-08-20', '14:00:00', '18:00:00', 'Paint for my new house', '2021-07-31 06:30:15', 'Waiting For Approval', 'Wait For Reply', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 NOT NULL,
  `userroll` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT 'house_owners'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `userroll`) VALUES
('admin', 'admin1234', 'admin'),
('vijay', 'Abcd1234#', 'workers'),
('devid', 'Abcd1234#', 'workers'),
('anu', 'Abcd1234#', 'workers'),
('Sobi', 'Sobi1234', 'house_owners'),
('Nivi123', 'nivi123', 'house_owners'),
('raj29', 'Raj1997', 'house_owners'),
('kala123', 'kala1234', 'house_owners'),
('mathu29', 'Mathu2919', 'house_owners'),
('mala', 'Abcd1234#', 'workers'),
('wishmila', 'Abcd1234#', 'workers'),
('kavitha', 'Abcd1234#', 'workers'),
('varan', 'Abcd1234#', 'workers'),
('raju', 'Abcd1234#', 'workers'),
('ratha', 'ratha123', 'house_owners'),
('amal', 'Amal1234#', 'house_owners'),
('manith', 'Abcd1234#', 'workers'),
('joseph', 'joseph1234', 'house_owners'),
('siva', 'Abcd1234#', 'workers'),
('rahu', 'rahu1234', 'house_owners'),
('karthi', 'Abcd1234#', 'workers');

-- --------------------------------------------------------

--
-- Table structure for table `workers`
--

CREATE TABLE `workers` (
  `workerID` int(11) NOT NULL,
  `title` varchar(5) CHARACTER SET utf8 NOT NULL,
  `firstname` varchar(20) CHARACTER SET utf8 NOT NULL,
  `lastname` varchar(20) CHARACTER SET utf8 NOT NULL,
  `personalno` int(10) NOT NULL,
  `homeno` int(10) NOT NULL,
  `workerroll` varchar(20) CHARACTER SET utf8 NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `workers`
--

INSERT INTO `workers` (`workerID`, `title`, `firstname`, `lastname`, `personalno`, `homeno`, `workerroll`, `username`) VALUES
(1, 'Mr', 'Vijay', 'Dev', 771231456, 212221010, 'Plumber', 'vijay'),
(2, 'Mr', 'Devid', 'Silva', 771234567, 212221025, 'Mechanic', 'devid'),
(3, 'Miss', 'Anu', 'Ram', 774512362, 212254120, 'Cleaner', 'anu'),
(4, 'Mrs', 'Mala', 'Waran', 778541236, 212365412, 'Painter', 'mala'),
(5, 'Miss', 'Wishmila', 'Ravi', 774956321, 778563214, 'Plumber', 'wishmila'),
(6, 'Mrs', 'Kavitha', 'Gnayakam', 778456321, 778456321, 'Carpenter', 'kavitha'),
(9, 'Mr', 'Varan', 'Prabhu', 771215420, 212214105, 'Electrician', 'varan'),
(10, 'Mr', 'Raju', 'Ram', 774512520, 212212525, 'Mechanic', 'raju'),
(12, 'Mr', 'Mani', 'Radna', 774512522, 212221415, 'Painter', 'manith');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `home_owners`
--
ALTER TABLE `home_owners`
  ADD PRIMARY KEY (`house_ownersID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `workers`
--
ALTER TABLE `workers`
  ADD PRIMARY KEY (`workerID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `home_owners`
--
ALTER TABLE `home_owners`
  MODIFY `house_ownersID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `workers`
--
ALTER TABLE `workers`
  MODIFY `workerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
