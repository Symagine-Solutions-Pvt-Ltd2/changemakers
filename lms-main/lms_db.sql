-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2021 at 10:44 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lms_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `game_id` int(11) NOT NULL,
  `game_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `game_type`) VALUES
(1, 'game 55'),
(2, 'game II'),
(3, 'game III'),
(4, 'game IV'),
(5, 'game V'),
(6, 'GAME VI');

-- --------------------------------------------------------

--
-- Table structure for table `game_task`
--

CREATE TABLE `game_task` (
  `game_task_id` int(12) NOT NULL,
  `game_id` int(12) NOT NULL,
  `task_id` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `game_task`
--

INSERT INTO `game_task` (`game_task_id`, `game_id`, `task_id`) VALUES
(1, 2, 2),
(2, 1, 6),
(3, 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `module_id` int(11) NOT NULL,
  `description` varchar(44) DEFAULT NULL,
  `image` varchar(550) DEFAULT NULL,
  `deep_dive` varchar(445) DEFAULT NULL,
  `prog_id` int(12) NOT NULL DEFAULT 0,
  `module_name` varchar(44) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`module_id`, `description`, `image`, `deep_dive`, `prog_id`, `module_name`) VALUES
(1, 'dsdsds', '/upload/module_files/32409671.jpg', '/upload/module_files/certificate (3).pdf', 1, 'Test'),
(2, 'sdsdsd', '/upload/module_files/module_video.png', '/upload/module_files/lms.txt', 3, 'Test2222'),
(3, 'dsdsd', '/upload/module_files/school_home_unfinished.png', '/upload/module_files/lms.txt', 12, 'sxdss');

-- --------------------------------------------------------

--
-- Table structure for table `module_training_details`
--

CREATE TABLE `module_training_details` (
  `id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `deep_dive` varchar(444) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `program`
--

CREATE TABLE `program` (
  `prog_id` int(12) NOT NULL,
  `program_name` varchar(44) DEFAULT NULL,
  `image_path` varchar(450) DEFAULT NULL,
  `description` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `program`
--

INSERT INTO `program` (`prog_id`, `program_name`, `image_path`, `description`) VALUES
(1, 'Waste management', '/upload/program_files/32409671.jpg', ''),
(2, 'Noise pollution', '/upload/program_files/WhatsApp Image 2021-11-26 at 1.53.39 PM.jpeg', ''),
(3, 'Electricity saving', '/upload/program_files/WhatsApp Image 2021-11-26 at 1.53.39 PM.jpeg', ''),
(4, 'dsdsdsd', '/upload/program_files/20211109210335XXXXXXX6451_Page_1.png', ''),
(5, 'sddsdsd', '/upload/program_files/module_listing.png', ''),
(6, 'dsdsds', '/upload/program_files/module_listing.png', ''),
(7, 'dfdfdfd', '/upload/program_files/module_listing.png', ''),
(8, 'sdsdssd', '/upload/program_files/module_listing.png', ''),
(9, 'dsdsdd', '/upload/program_files/32409671.jpg', ''),
(10, 'cdcxc', '/upload/program_files/WhatsApp Image 2021-11-26 at 1.53.39 PM.jpeg', ''),
(11, 'sdsdsdds', '/upload/program_files/32409671.jpg', ''),
(12, 'Waste management2323', '/upload/program_files/WhatsApp Image 2021-11-26 at 7.12.02 PM.jpeg', ''),
(13, 'dsds', '/upload/program_files/resign_mail_4.png', 'sdsdd');

-- --------------------------------------------------------

--
-- Table structure for table `program_assignment`
--

CREATE TABLE `program_assignment` (
  `assign_id` int(12) NOT NULL,
  `student_id` int(12) DEFAULT NULL,
  `prog_id` int(12) DEFAULT NULL,
  `school_id` int(12) NOT NULL,
  `user_id` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `program_assignment`
--

INSERT INTO `program_assignment` (`assign_id`, `student_id`, `prog_id`, `school_id`, `user_id`) VALUES
(1, 1, 1, 1, 44),
(2, 1, 2, 1, 44),
(3, 2, 3, 2, 45);

-- --------------------------------------------------------

--
-- Table structure for table `program_progress`
--

CREATE TABLE `program_progress` (
  `progress_id` int(12) NOT NULL,
  `progress` int(50) NOT NULL DEFAULT 0,
  `task_id` int(12) NOT NULL,
  `student_id` int(12) NOT NULL,
  `start_date` varchar(155) DEFAULT NULL,
  `end_date` varchar(155) DEFAULT NULL,
  `module_id` int(12) NOT NULL,
  `prog_id` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `quiz_id` int(11) NOT NULL,
  `question` varchar(44) NOT NULL,
  `answer` int(12) NOT NULL,
  `score` int(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`quiz_id`, `question`, `answer`, `score`) VALUES
(1, 'which option best describes your job role?', 1, 10),
(2, 'what is job title ?', 5, 20),
(17, 'Test', 55, 0),
(18, 'Test1', 60, 0);

-- --------------------------------------------------------

--
-- Table structure for table `quiz_option`
--

CREATE TABLE `quiz_option` (
  `quiz_option_id` int(11) NOT NULL,
  `quiz_choice` varchar(255) DEFAULT NULL,
  `quiz_id` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quiz_option`
--

INSERT INTO `quiz_option` (`quiz_option_id`, `quiz_choice`, `quiz_id`) VALUES
(1, 'Small Business Owner or Employee', 1),
(2, 'Nonprofit Owner or Employee', 1),
(3, 'Journalist or Activist ', 1),
(4, 'Other', 1),
(5, 'developer', 2),
(6, 'designer', 2),
(7, 'data engineer', 2),
(8, 'other', 2),
(53, 'op1', 17),
(54, 'op2', 17),
(55, 'op3', 17),
(56, 'op4', 17),
(57, 'op11', 18),
(58, 'op22', 18),
(59, 'op33', 18),
(60, 'op44', 18);

-- --------------------------------------------------------

--
-- Table structure for table `quiz_task`
--

CREATE TABLE `quiz_task` (
  `quiz_task_id` int(12) NOT NULL,
  `quiz_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quiz_task`
--

INSERT INTO `quiz_task` (`quiz_task_id`, `quiz_id`, `task_id`) VALUES
(1, 1, 4),
(2, 1, 8),
(3, 1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE `school` (
  `school_id` int(11) NOT NULL,
  `school_branch` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `school_address` varchar(44) DEFAULT NULL,
  `school_code` varchar(44) DEFAULT NULL,
  `contact_name` varchar(44) DEFAULT NULL,
  `contact_email` varchar(44) DEFAULT NULL,
  `contact_phone` int(11) NOT NULL DEFAULT 0,
  `stu_reg_count` int(15) NOT NULL DEFAULT 0,
  `certificate_issued` int(15) NOT NULL DEFAULT 0,
  `user_id` int(15) NOT NULL DEFAULT 0,
  `token` varchar(444) DEFAULT NULL,
  `password` varchar(244) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `school`
--

INSERT INTO `school` (`school_id`, `school_branch`, `school_name`, `school_address`, `school_code`, `contact_name`, `contact_email`, `contact_phone`, `stu_reg_count`, `certificate_issued`, `user_id`, `token`, `password`) VALUES
(1, 'Baguiati', 'North Point', NULL, '9442ha', 'Debasish Ghosh', 'shayan.deba0930@gmail.com', 0, 1, 0, 44, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaGF5YW4uZGViYTA5MzBAZ21haWwuY29tIiwiZXhwIjoxNjQwODE3MTc1LCJpYXQiOjE2NDA3OTkxNzV9.2jxh-k8SHgWmDRfmffhakbKI6rO8JXeFEKsxEwMXuaBxp1L7juVtmmDGfsaynhBJaXymgp1BxgbiHUntiWDHLQ', '$2a$04$DIM6C6uukFpub4qe0W.ffuiKBbS1bi0TaNT5rkf6mZ7PW8/5DH4ym'),
(2, 'Baguiati', 'North Point', NULL, '6013la', 'shayan', 'clash.shsyan@gmail.com', 6464646, 1, 0, 45, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGFzaC5zaHN5YW5AZ21haWwuY29tIiwiZXhwIjoxNjQwODIwMDM5LCJpYXQiOjE2NDA4MDIwMzl9.anRUo1mFLrzJJL4zSBYTNLlSutPZLpxAz66FARlg_l9iRBN-FLVl--jfdTpeHswM5vT2_FQQ73Gaf4k6mQaHJg', '$2a$04$1.3nrGWx3llKKxmofpoPRO5U1YgSlueP1Cnv9.nBsDLPBUz8PbrFK');

-- --------------------------------------------------------

--
-- Table structure for table `school_assign_prog`
--

CREATE TABLE `school_assign_prog` (
  `sc_assign_id` int(12) NOT NULL,
  `sub_assign_id` int(12) NOT NULL,
  `school_id` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

CREATE TABLE `score` (
  `score_id` int(11) NOT NULL,
  `module_id` int(11) DEFAULT NULL,
  `score_game` int(11) DEFAULT NULL,
  `score_quiz` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `section_id` int(12) NOT NULL,
  `section_name` int(34) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `standard`
--

CREATE TABLE `standard` (
  `standard_id` int(12) NOT NULL,
  `standard_name` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `standard`
--

INSERT INTO `standard` (`standard_id`, `standard_name`) VALUES
(1, 'CLASS I'),
(2, 'CLASS II'),
(3, 'CLASS III'),
(4, 'CLASS IV'),
(5, 'CLASS V'),
(6, 'CLASS VI'),
(7, 'CLASS VII'),
(8, 'CLASS VIII'),
(9, 'CLASS IX'),
(10, 'CLASS X'),
(11, 'CLASS XI'),
(12, 'CLASS XII');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `certificate_url` varchar(255) DEFAULT NULL,
  `standard_id` int(12) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(55) DEFAULT NULL,
  `parent_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `parent_number` varchar(255) DEFAULT NULL,
  `school_id` int(12) NOT NULL,
  `score` varchar(255) DEFAULT NULL,
  `score_percentage` varchar(255) DEFAULT NULL,
  `section_id` int(10) NOT NULL,
  `token` varchar(400) DEFAULT NULL,
  `last_name` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `address`, `certificate_url`, `standard_id`, `email`, `first_name`, `parent_name`, `password`, `parent_number`, `school_id`, `score`, `score_percentage`, `section_id`, `token`, `last_name`) VALUES
(1, NULL, NULL, 10, 'deba.shayan12259@gmail.com', 'Debasish', NULL, '$2a$04$7NyOrF9woS0qbhPbkcdRJuahdbBuFT62nx.ZjzQYYXzKQq5zdDgP6', NULL, 1, NULL, NULL, 0, NULL, 'Ghosh'),
(2, NULL, NULL, 4, 'deba.shayan122359@gmail.com', 'Debasish', NULL, '$2a$04$4zPse35ZyO.KUdGkOJZSteT/TT56tujnz/uwA5gLvXXX2sWeMqI12', NULL, 2, NULL, NULL, 0, NULL, 'Ghosh'),
(3, NULL, NULL, 1, 'debasish.ghosh666@gmail.com', 'Test', NULL, '$2a$04$yB1WvP7HtXyDNzOEifZF7enZSBSD.HI/kgo9bgeeS5Fe//JiBnrZm', NULL, 2, NULL, NULL, 0, NULL, 'test');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `module_id` int(12) NOT NULL,
  `type` varchar(88) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_id`, `module_id`, `type`) VALUES
(1, 1, 'video'),
(2, 1, 'game'),
(3, 1, 'video'),
(4, 1, 'quiz'),
(5, 2, 'video'),
(6, 2, 'game'),
(7, 2, 'video'),
(8, 2, 'quiz'),
(9, 3, 'video'),
(10, 3, 'game'),
(11, 3, 'video'),
(12, 3, 'quiz');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(44) NOT NULL,
  `school_id` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `teacher_code` varchar(255) DEFAULT NULL,
  `token` varchar(555) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `email`, `is_active`, `name`, `password`, `phone`, `school_id`, `subject`, `teacher_code`, `token`) VALUES
(1, 'Debasish.Ghosh4@cognizant.com', 0, 'Deba', '$2a$04$oMYO7.6ePPynYTe3injCP.gzUGbjWHmQrx3hL/grU9zihEL0HGsMG', '918902416', 3, 'Science', '345718', NULL),
(2, 'Debasish.Ghosh44@cognizant.com', 0, 'Julie', '$2a$04$EaHvlIogYGJEoWoRx9rRAuzIPZFOfcS7nYbduxeb22Q0yxw7G4f4y', '918902416', 3, 'maths', '146218', NULL),
(4, 'Debasish.Ghosh334@cognizant.com', 0, 'Deba', '$2a$04$JL18y/chCGvX2mxneespieSa/zn/tz5aKqOpgm27qCHXT2XEtXm1S', '918902416', 3, 'Science', '841918', NULL),
(6, 'deba.shayan12259@gmail.com', 1, 'Debasish Ghosh', '$2a$04$htvNOb1ueXqB4w7ejEdlkuqwssN8FJnYwrs0Roc3vr9KSza8ofxDK', '+18902416510', 1, 'maths', '50318', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWJhLnNoYXlhbjEyMjU5QGdtYWlsLmNvbSIsImV4cCI6MTYzOTI1MDk2NiwiaWF0IjoxNjM5MjMyOTY2fQ.jii4UOfemLuvk20MT0PMt9Css8ZSjDoUW_iySy7G48v7dygPjEzoyfqM5h_EAUv6kmtgoIMsWIu-RL4Z6mGCaw'),
(7, 'shayan.deba0930@gmail.com', 1, 'Debasish Ghosh', '$2a$04$y5ZBEfG0O96EUv7ACNV3N.kMeh4N9XAxAJRq7FTT05ldNFIsLag5S', '8902416510', 1, 'Science', '180790', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaGF5YW4uZGViYTA5MzBAZ21haWwuY29tIiwiZXhwIjoxNjM4NDc0ODI4LCJpYXQiOjE2Mzg0NTY4Mjh9.gHwtawi8yvEJPGU8dw3s3fzyi3ep0IByIj47jq35Kz1tuY1FpnSZeeV2leJRKIaO14THKbT3NGXQv3uUAs6FTg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `type` int(12) NOT NULL,
  `password` varchar(444) NOT NULL,
  `name` varchar(44) DEFAULT NULL,
  `organization_name` varchar(44) DEFAULT NULL,
  `phone` double DEFAULT NULL,
  `username` varchar(44) NOT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `type`, `password`, `name`, `organization_name`, `phone`, `username`, `token`) VALUES
(1, 'jonas@gmail.com', 1, '123', 'admin', 'admin', 9874155488, 'admin', NULL),
(44, 'deba.shayan12259@gmail.com', 2, '$2a$04$c5IIqlWhRXEY7FnkbFjHJeZqTgLK4TcMCVMNF7KiFP7CNGNvmIzR6', 'Debasish Ghosh', 'Project requirement', 0, 'Debasish615', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWJhLnNoYXlhbjEyMjU5QGdtYWlsLmNvbSIsImV4cCI6MTY0MDgxNzEwMCwiaWF0IjoxNjQwNzk5MTAwfQ.1bItCKaADFrc5TqJUe2F9dUprFA4PjHSdWt3r4ukK01IqmHxPeWGHlGUvgvtL4dRKkNPXQFnkQVSIlfN8SboAg'),
(45, 'deba.shayan1225922@gmail.com', 2, '$2a$04$JHIjxqhhnWqSjbvJIrH.P.egvBBbKiLyWCm1SsOr5XxgeTOBhlyqC', 'Debasish Ghosh', 'Test', 0, 'Debasish420', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWJhLnNoYXlhbjEyMjU5MjJAZ21haWwuY29tIiwiZXhwIjoxNjQwODE5ODA3LCJpYXQiOjE2NDA4MDE4MDd9.LKV8cwD1fWQduePH5Q3lBG9kSRZtDY3akT-C8PpSZrCISkZHfv9Q8Q1lhEjtwvg2H7NDPIg74v6gF-vLGsJYYg');

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE `video` (
  `video_id` int(11) NOT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `video_title` varchar(44) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `video`
--

INSERT INTO `video` (`video_id`, `video_url`, `video_title`) VALUES
(1, 'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4', 'Sample 1'),
(2, 'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4', 'Sample 2'),
(3, 'https://www.netflix.com/watch/70291051?trackId=14170286&tctx=1%2C0%2Cacfd2217-d612-44f4-ba4a-503e35a5758e-30038378%2C5035280d-0f16-4327-a489-c957b94829e5_64174425X3XX1640527327188%2C5035280d-0f16-4327-a489-c957b94829e5_ROOT%2C%2C%2C', 'Netflix');

-- --------------------------------------------------------

--
-- Table structure for table `video_task`
--

CREATE TABLE `video_task` (
  `video_task_id` int(12) NOT NULL,
  `video_id` int(12) NOT NULL,
  `task_id` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `video_task`
--

INSERT INTO `video_task` (`video_task_id`, `video_id`, `task_id`) VALUES
(1, 1, 1),
(2, 2, 3),
(3, 1, 5),
(4, 2, 7),
(5, 1, 9),
(6, 1, 11);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`);

--
-- Indexes for table `game_task`
--
ALTER TABLE `game_task`
  ADD PRIMARY KEY (`game_task_id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`module_id`);

--
-- Indexes for table `module_training_details`
--
ALTER TABLE `module_training_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`prog_id`);

--
-- Indexes for table `program_assignment`
--
ALTER TABLE `program_assignment`
  ADD PRIMARY KEY (`assign_id`);

--
-- Indexes for table `program_progress`
--
ALTER TABLE `program_progress`
  ADD PRIMARY KEY (`progress_id`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`quiz_id`);

--
-- Indexes for table `quiz_option`
--
ALTER TABLE `quiz_option`
  ADD PRIMARY KEY (`quiz_option_id`);

--
-- Indexes for table `quiz_task`
--
ALTER TABLE `quiz_task`
  ADD PRIMARY KEY (`quiz_task_id`);

--
-- Indexes for table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`school_id`);

--
-- Indexes for table `school_assign_prog`
--
ALTER TABLE `school_assign_prog`
  ADD PRIMARY KEY (`sc_assign_id`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`score_id`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`section_id`);

--
-- Indexes for table `standard`
--
ALTER TABLE `standard`
  ADD PRIMARY KEY (`standard_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`video_id`);

--
-- Indexes for table `video_task`
--
ALTER TABLE `video_task`
  ADD PRIMARY KEY (`video_task_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `game_task`
--
ALTER TABLE `game_task`
  MODIFY `game_task_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `module_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `module_training_details`
--
ALTER TABLE `module_training_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `program`
--
ALTER TABLE `program`
  MODIFY `prog_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `program_assignment`
--
ALTER TABLE `program_assignment`
  MODIFY `assign_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `program_progress`
--
ALTER TABLE `program_progress`
  MODIFY `progress_id` int(12) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `quiz_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `quiz_option`
--
ALTER TABLE `quiz_option`
  MODIFY `quiz_option_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `quiz_task`
--
ALTER TABLE `quiz_task`
  MODIFY `quiz_task_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `school`
--
ALTER TABLE `school`
  MODIFY `school_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `school_assign_prog`
--
ALTER TABLE `school_assign_prog`
  MODIFY `sc_assign_id` int(12) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `score`
--
ALTER TABLE `score`
  MODIFY `score_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `section_id` int(12) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `standard`
--
ALTER TABLE `standard`
  MODIFY `standard_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `video`
--
ALTER TABLE `video`
  MODIFY `video_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `video_task`
--
ALTER TABLE `video_task`
  MODIFY `video_task_id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
