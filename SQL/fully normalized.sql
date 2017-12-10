DROP DATABASE sky;

CREATE DATABASE sky;

USE sky;

CREATE TABLE `login_details` (
  `ld_ID` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `user_Password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_details`
--

INSERT INTO `login_details` (`ld_ID`, `username`, `user_Password`) VALUES
(1, 'prasanga', '1111');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  `grade` int(11) NOT NULL,
  `parent_Contact_Num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_ID`, `user_ID`, `grade`, `parent_Contact_Num`) VALUES
(2, 1, 12, 786543219),
(3, 4, 9, 776543219),
(4, 13, 90, 2147483647),
(5, 14, 6, 666666666),
(6, 15, 5, 876548961),
(7, 17, 12, 765432198),
(8, 18, 12, 776453289),
(9, 19, 12, 765432768);

-- --------------------------------------------------------

--
-- Table structure for table `student_registration`
--

CREATE TABLE `student_registration` (
  `registration_ID` int(11) NOT NULL,
  `student_ID` int(11) NOT NULL,
  `is_registered` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_registration`
--

INSERT INTO `student_registration` (`registration_ID`, `student_ID`, `is_registered`) VALUES
(3, 2, 1),
(4, 3, 1),
(5, 6, 1),
(6, 7, 1),
(7, 8, 1),
(8, 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `term_fees`
--

CREATE TABLE `term_fees` (
  `fee_ID` int(11) NOT NULL,
  `student_ID` int(11) NOT NULL,
  `is_paid` tinyint(1) NOT NULL,
  `paid_date` date DEFAULT NULL,
  `next_due_date` date DEFAULT NULL,
  `fee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `term_fees`
--

INSERT INTO `term_fees` (`fee_ID`, `student_ID`, `is_paid`, `paid_date`, `next_due_date`, `fee`) VALUES
(3, 2, 1, '2017-12-05', '2017-12-05', 4000),
(4, 3, 1, '2017-12-05', '2018-01-10', 4000),
(5, 7, 1, '2017-12-05', '2017-12-05', 4000),
(6, 8, 0, NULL, NULL, 0),
(7, 9, 1, '2017-12-05', '2016-12-05', 4000);

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `user_ID` int(11) NOT NULL,
  `first_Name` varchar(30) NOT NULL,
  `last_Name` varchar(30) NOT NULL,
  `user_Address` varchar(60) NOT NULL,
  `date_Of_Birth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`user_ID`, `first_Name`, `last_Name`, `user_Address`, `date_Of_Birth`) VALUES
(1, 'Prabodha', 'Fernando', 'Katunayake', '2017-12-21'),
(4, 'Sandun', 'Rathnayaka', 'Kovinna', '1995-12-04'),
(5, 'Thilanka', 'Rathnayaka', 'Kandy', '1990-12-30'),
(6, 'qeqwe', 'afssaf', '12313', '1990-04-30'),
(7, 'qqqqq', 'qqqqqqqq', 'qwqwqwq', '1990-02-05'),
(8, '123213', '12321', '123', '0000-00-00'),
(9, 'Slim', 'Shady', 'DC', '0000-00-00'),
(10, 'Shane', 'Ward', 'DC', '1990-11-11'),
(11, 'ss', 'kk', 'asdsda', '0000-00-00'),
(12, 'aa', 'bb', 'cc', '0000-00-00'),
(13, 'my', 'my', 'my', '0000-00-00'),
(14, 'mmmmmm', 'mmmmmmmm', 'mmmmmmmm', '1990-12-30'),
(15, 'kkkkk', 'kkkkkkkk', 'kkkkkkkkk', '1998-12-30'),
(16, 'kkkkk', 'kkkkkkkk', 'kkkkkkkkk', '1998-12-30'),
(17, 'Hashan', 'Rajapaksha', 'Kandy', '1990-03-05'),
(18, 'Uthayakumar', 'Amilraj', 'Grandpass', '1994-12-30'),
(19, 'Supun', 'Sandaruwan', 'Kovinna', '1990-12-05');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `role_ID` int(11) NOT NULL,
  `user_ID` int(11) NOT NULL,
  `user_Role` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`role_ID`, `user_ID`, `user_Role`) VALUES
(1, 1, 'admin'),
(2, 18, 'student'),
(3, 19, 'student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login_details`
--
ALTER TABLE `login_details`
  ADD PRIMARY KEY (`ld_ID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_ID`),
  ADD KEY `user_ID` (`user_ID`);

--
-- Indexes for table `student_registration`
--
ALTER TABLE `student_registration`
  ADD PRIMARY KEY (`registration_ID`),
  ADD KEY `student_ID` (`student_ID`);

--
-- Indexes for table `term_fees`
--
ALTER TABLE `term_fees`
  ADD PRIMARY KEY (`fee_ID`),
  ADD KEY `student_ID` (`student_ID`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`user_ID`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`role_ID`),
  ADD KEY `user_ID` (`user_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login_details`
--
ALTER TABLE `login_details`
  MODIFY `ld_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `student_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `student_registration`
--
ALTER TABLE `student_registration`
  MODIFY `registration_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `term_fees`
--
ALTER TABLE `term_fees`
  MODIFY `fee_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `user_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
  MODIFY `role_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `user_details` (`user_ID`);

--
-- Constraints for table `student_registration`
--
ALTER TABLE `student_registration`
  ADD CONSTRAINT `student_registration_ibfk_1` FOREIGN KEY (`student_ID`) REFERENCES `student` (`student_ID`);

--
-- Constraints for table `term_fees`
--
ALTER TABLE `term_fees`
  ADD CONSTRAINT `term_fees_ibfk_1` FOREIGN KEY (`student_ID`) REFERENCES `student` (`student_ID`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `user_details` (`user_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
