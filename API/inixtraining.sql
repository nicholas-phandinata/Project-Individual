-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 13 Feb 2022 pada 17.46
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inixtraining`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_detail_kelas`
--

CREATE TABLE `tb_detail_kelas` (
  `id_detail_kls` int(10) NOT NULL,
  `id_kls` int(10) NOT NULL,
  `id_pst` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_detail_kelas`
--

INSERT INTO `tb_detail_kelas` (`id_detail_kls`, `id_kls`, `id_pst`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 2, 6),
(7, 2, 7),
(8, 2, 8),
(9, 2, 9),
(10, 2, 10),
(11, 3, 11),
(12, 3, 12),
(13, 3, 13),
(14, 3, 14),
(15, 3, 15),
(16, 4, 1),
(17, 4, 2),
(18, 4, 3),
(19, 4, 4),
(20, 4, 5),
(21, 5, 6),
(22, 5, 7),
(23, 5, 8),
(24, 5, 9),
(25, 5, 10),
(26, 6, 11),
(27, 6, 12),
(28, 6, 13),
(29, 6, 14),
(30, 6, 15),
(31, 7, 1),
(32, 7, 2),
(33, 7, 3),
(34, 7, 4),
(35, 7, 5),
(36, 8, 6),
(37, 8, 7),
(38, 8, 8),
(39, 8, 9),
(40, 8, 10),
(41, 9, 11),
(42, 9, 12),
(43, 9, 13),
(44, 9, 14),
(45, 9, 15),
(46, 10, 1),
(47, 10, 2),
(48, 10, 3),
(49, 10, 4),
(50, 10, 5),
(51, 11, 6),
(52, 11, 7),
(53, 11, 8),
(54, 11, 9),
(55, 11, 10),
(56, 12, 11),
(57, 12, 12),
(58, 12, 13),
(59, 12, 14),
(60, 12, 15),
(61, 13, 1),
(62, 13, 2),
(63, 13, 3),
(64, 13, 4),
(65, 13, 5),
(66, 14, 6),
(67, 14, 7),
(68, 14, 8),
(69, 14, 9),
(70, 14, 10),
(71, 15, 11),
(72, 15, 12),
(73, 15, 13),
(74, 15, 14),
(75, 15, 15),
(76, 16, 1),
(77, 16, 2),
(78, 16, 3),
(79, 16, 4),
(80, 16, 5),
(81, 17, 6),
(82, 17, 7),
(83, 17, 8),
(84, 17, 9),
(85, 17, 10),
(86, 18, 11),
(87, 18, 12),
(88, 18, 13),
(89, 18, 14),
(90, 18, 15),
(91, 19, 1),
(92, 19, 2),
(93, 19, 3),
(94, 19, 4),
(95, 19, 5),
(96, 20, 6),
(97, 20, 7),
(98, 20, 8),
(99, 20, 9),
(100, 20, 10),
(101, 1, 37),
(103, 3, 37),
(116, 2, 38),
(120, 3, 38),
(132, 33, 37),
(145, 33, 39),
(146, 39, 37),
(148, 11, 37),
(149, 1, 38);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_instruktur`
--

CREATE TABLE `tb_instruktur` (
  `id_ins` int(10) NOT NULL,
  `nama_ins` varchar(50) NOT NULL,
  `email_ins` varchar(50) NOT NULL,
  `hp_ins` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_instruktur`
--

INSERT INTO `tb_instruktur` (`id_ins`, `nama_ins`, `email_ins`, `hp_ins`) VALUES
(1, 'Rondi Hidayat', 'rondih@inixindo.co.id', '081122334400'),
(2, 'Yusuf Rizal', 'yusufr@inixindo.co.id', '081122334401'),
(3, 'Ina Susanti', 'inas@inixindo.co.id', '081122334402'),
(4, 'Kania Pertiwi', 'kaniap@inixindo.co.id', '081122334403'),
(5, 'Alika Yuliarti', 'alikayu@inixindo.co.id', '081122334404'),
(6, 'Restu Pertiwi', 'restup@inixindo.co.id', '081122334405'),
(7, 'Elma Hasanah', 'elmah@inixindo.co.id', '081122334406'),
(8, 'Candra Irawan', 'candrai@inixindo.co.id', '081122334407'),
(9, 'Umaya Sitorus', 'umayas@inixindo.co.id', '081122334408'),
(10, 'Bagiya Wahyudin', 'bagiyaw@inixindo.co.id', '081122334409'),
(23, 'Tomi Saputra', 'toms@inixindo.co.id', ''),
(24, 'Budi Santoso', 'budisantoso@inixindo.com', '08'),
(27, 'Richard', '', '0815');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id_kls` int(10) NOT NULL,
  `tgl_mulai_kls` date NOT NULL,
  `tgl_akhir_kls` date NOT NULL,
  `id_ins` int(10) NOT NULL,
  `id_mat` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_kelas`
--

INSERT INTO `tb_kelas` (`id_kls`, `tgl_mulai_kls`, `tgl_akhir_kls`, `id_ins`, `id_mat`) VALUES
(1, '2021-01-04', '2021-01-15', 1, 1),
(2, '2021-01-18', '2021-01-29', 2, 2),
(3, '2021-02-01', '2021-02-12', 3, 3),
(4, '2021-02-15', '2021-02-26', 4, 4),
(5, '2021-03-01', '2021-03-12', 5, 5),
(6, '2021-03-15', '2021-03-26', 6, 6),
(7, '2021-04-05', '2021-04-16', 7, 7),
(8, '2021-04-19', '2021-04-30', 8, 8),
(9, '2021-05-03', '2021-05-14', 9, 9),
(10, '2021-05-17', '2021-04-28', 10, 10),
(11, '2021-06-01', '2021-06-11', 1, 1),
(12, '2021-06-14', '2021-06-25', 2, 2),
(13, '2021-07-05', '2021-07-16', 3, 3),
(14, '2021-07-19', '2021-07-30', 4, 4),
(15, '2021-08-02', '2021-08-13', 5, 5),
(16, '2021-08-16', '2021-08-27', 6, 6),
(17, '2021-09-06', '2021-09-17', 7, 7),
(18, '2021-09-20', '2021-09-30', 8, 8),
(19, '2021-10-04', '2021-11-12', 9, 9),
(20, '2021-11-15', '2021-12-31', 10, 10),
(33, '2022-01-03', '2022-01-28', 2, 16),
(39, '2022-02-10', '2022-02-20', 27, 16),
(41, '2022-02-07', '2022-02-25', 23, 18);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_materi`
--

CREATE TABLE `tb_materi` (
  `id_mat` int(10) NOT NULL,
  `nama_mat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_materi`
--

INSERT INTO `tb_materi` (`id_mat`, `nama_mat`) VALUES
(1, 'Introduction to SQL: Oracle'),
(2, 'Java Android Progamming'),
(3, 'IT Project Management'),
(4, 'Data Management and Data Governance'),
(5, 'Data Wrangling with Python'),
(6, 'Enterprise IT Architecture with TOGAF'),
(7, 'Securing Windows Server'),
(8, 'System Analyst and Design'),
(9, 'Red Hat Enterprise Linux System Administration'),
(10, 'Microsoft Azure Fundamentals'),
(16, 'Intro to AI'),
(18, 'IT Business');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_peserta`
--

CREATE TABLE `tb_peserta` (
  `id_pst` int(10) NOT NULL,
  `nama_pst` varchar(50) NOT NULL,
  `email_pst` varchar(50) NOT NULL,
  `hp_pst` varchar(13) NOT NULL,
  `instansi_pst` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_peserta`
--

INSERT INTO `tb_peserta` (`id_pst`, `nama_pst`, `email_pst`, `hp_pst`, `instansi_pst`) VALUES
(1, 'Lili Purnawati', 'lilip@permatabank.co.id', '081020304010', 'Permata Bank'),
(2, 'Rahmi Wastuti', 'rahmiw@permatabank.co.id', '081020304011', 'Permata Bank'),
(3, 'Rosi Nurdiyanti', 'rosin@permatabank.co.id', '081020304012', 'Permata Bank'),
(4, 'Uchita Riyanti', 'uchitar@permatabank.co.id', '081020304013', 'Permata Bank'),
(5, 'Danang Putra', 'danangp@permatabank.co.id', '081020304014', 'Permata Bank'),
(6, 'Jagaraga Salahudin', 'jagas@telkom.co.id', '081020304020', 'Telkom'),
(7, 'Okto Sitorus', 'oktos@telkom.co.id', '081020304021', 'Telkom'),
(8, 'Paulin Rahayu', 'paulinr@telkom.co.id', '081020304022', 'Telkom'),
(9, 'Tasnim Pratama', 'tasnimp@telkom.co.id', '081020304023', 'Telkom'),
(10, 'Queen Winarsih', 'queenw@telkom.co.id', '081020304024', 'Telkom'),
(11, 'Jayadi Wahyudin', 'jayadiw@binus.ac.id', '081020304030', 'Binus'),
(12, 'Gamani Santoso', 'gamanis@binus.ac.id', '081020304031', 'Binus'),
(13, 'Cagak Ardianto', 'cagaka@binus.ac.id', '081020304032', 'Binus'),
(14, 'Langgeng Maryadi', 'langgengm@binus.ac.id', '081020304033', 'Binus'),
(15, 'Galih Hardiansyah', 'galihh@binus.ac.id', '081020304034', 'Binus'),
(37, 'Benn', '', '', ''),
(38, 'Sana', '', '', ''),
(39, 'Steady', '', '', ''),
(42, 'Rose', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD PRIMARY KEY (`id_detail_kls`),
  ADD KEY `tb_detail_kelas_ibfk_1` (`id_kls`),
  ADD KEY `tb_detail_kelas_ibfk_2` (`id_pst`);

--
-- Indeks untuk tabel `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  ADD PRIMARY KEY (`id_ins`);

--
-- Indeks untuk tabel `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD PRIMARY KEY (`id_kls`),
  ADD KEY `tb_kelas_ibfk_1` (`id_ins`),
  ADD KEY `tb_kelas_ibfk_2` (`id_mat`);

--
-- Indeks untuk tabel `tb_materi`
--
ALTER TABLE `tb_materi`
  ADD PRIMARY KEY (`id_mat`);

--
-- Indeks untuk tabel `tb_peserta`
--
ALTER TABLE `tb_peserta`
  ADD PRIMARY KEY (`id_pst`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  MODIFY `id_detail_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=151;

--
-- AUTO_INCREMENT untuk tabel `tb_instruktur`
--
ALTER TABLE `tb_instruktur`
  MODIFY `id_ins` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT untuk tabel `tb_kelas`
--
ALTER TABLE `tb_kelas`
  MODIFY `id_kls` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT untuk tabel `tb_materi`
--
ALTER TABLE `tb_materi`
  MODIFY `id_mat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT untuk tabel `tb_peserta`
--
ALTER TABLE `tb_peserta`
  MODIFY `id_pst` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_detail_kelas`
--
ALTER TABLE `tb_detail_kelas`
  ADD CONSTRAINT `tb_detail_kelas_ibfk_1` FOREIGN KEY (`id_kls`) REFERENCES `tb_kelas` (`id_kls`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_detail_kelas_ibfk_2` FOREIGN KEY (`id_pst`) REFERENCES `tb_peserta` (`id_pst`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD CONSTRAINT `tb_kelas_ibfk_1` FOREIGN KEY (`id_ins`) REFERENCES `tb_instruktur` (`id_ins`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_kelas_ibfk_2` FOREIGN KEY (`id_mat`) REFERENCES `tb_materi` (`id_mat`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
