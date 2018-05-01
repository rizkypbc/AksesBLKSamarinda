-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2017 at 10:59 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sampelblki`
--

-- --------------------------------------------------------

--
-- Table structure for table `berita`
--

CREATE TABLE `berita` (
  `id` int(10) NOT NULL,
  `tanggal` date NOT NULL,
  `judul` varchar(100) NOT NULL,
  `deskripsi` text NOT NULL,
  `nama_file` varchar(255) NOT NULL,
  `keterangan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `berita`
--

INSERT INTO `berita` (`id`, `tanggal`, `judul`, `deskripsi`, `nama_file`, `keterangan`) VALUES
(13, '2017-06-29', 'Bencana Alam', 'askdjaskldjas;ldkasdlaskda', 'utaha.jpg', 'No');

-- --------------------------------------------------------

--
-- Table structure for table `pelatihan`
--

CREATE TABLE `pelatihan` (
  `id` int(10) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `jk` varchar(10) DEFAULT NULL,
  `ttl` varchar(30) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `provinsi` varchar(50) DEFAULT NULL,
  `kab_kota` varchar(20) DEFAULT NULL,
  `notelp` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `agama` varchar(20) DEFAULT NULL,
  `pendidikan` varchar(20) DEFAULT NULL,
  `jurusan` varchar(30) DEFAULT NULL,
  `asal_sekolah` varchar(30) DEFAULT NULL,
  `kejuruan` varchar(40) DEFAULT NULL,
  `sub_kejuruan` varchar(40) DEFAULT NULL,
  `program` varchar(40) DEFAULT NULL,
  `urlphoto` varchar(40) DEFAULT NULL,
  `tanggal_daftar` date NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelatihan`
--

INSERT INTO `pelatihan` (`id`, `nama`, `jk`, `ttl`, `alamat`, `provinsi`, `kab_kota`, `notelp`, `email`, `agama`, `pendidikan`, `jurusan`, `asal_sekolah`, `kejuruan`, `sub_kejuruan`, `program`, `urlphoto`, `tanggal_daftar`, `status`) VALUES
(8, 'rizky pratama putra', 'Laki-Laki', 'Bontang, 25 Juni 1995', 'Jalan Buntu', 'Kalimantan Timur', 'Kota Bontang', '085388394854', 'rizky_scream@yahoo.com', 'Islam', 'S1', 'Teknik Informatika', 'SMAN 2 Bontang', 'Teknologi Informasi Komunikasi', 'Graphic Design', 'Pelatihan Berbasis Kompetensi', 'utaha.jpg', '2017-06-29', 'Daftar Baru');

-- --------------------------------------------------------

--
-- Table structure for table `pencari`
--

CREATE TABLE `pencari` (
  `id` int(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `noktp` varchar(50) NOT NULL,
  `nama_pencari` varchar(50) NOT NULL,
  `jk_pencari` varchar(30) NOT NULL,
  `ttl_pencari` varchar(50) NOT NULL,
  `provinsi` varchar(50) NOT NULL,
  `kab` varchar(50) NOT NULL,
  `asal` varchar(50) NOT NULL,
  `pendidikan` text NOT NULL,
  `ketrampilan` text NOT NULL,
  `pengalaman` text NOT NULL,
  `alumni` varchar(20) NOT NULL,
  `tanggal_daftar` date NOT NULL,
  `urlphotopencari` varchar(50) NOT NULL,
  `urlcv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pencari`
--

INSERT INTO `pencari` (`id`, `username`, `password`, `noktp`, `nama_pencari`, `jk_pencari`, `ttl_pencari`, `provinsi`, `kab`, `asal`, `pendidikan`, `ketrampilan`, `pengalaman`, `alumni`, `tanggal_daftar`, `urlphotopencari`, `urlcv`) VALUES
(25, 'rizky', '123', '078821398123', 'Rizky Pratama', 'Laki-Laki', 'Bontang, 25 Juni 1995', 'Kalimantan Timur', 'Kota Bontang', 'SMAN 2 Bontang', 'xcvsdfasdasda', 'asdasdasda', 'asdasdas', 'Tidak', '2017-06-29', 'IMG_20170610_142349.jpg', 'utaha.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `perusahaan`
--

CREATE TABLE `perusahaan` (
  `id` int(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nama_perusahaan` varchar(50) NOT NULL,
  `jenis` varchar(30) NOT NULL,
  `sektor` varchar(50) NOT NULL,
  `bidang` varchar(30) NOT NULL,
  `alamat_perusahaan` varchar(50) NOT NULL,
  `provinsi` varchar(50) NOT NULL,
  `kab` varchar(50) NOT NULL,
  `telepon` varchar(30) NOT NULL,
  `fax` varchar(30) NOT NULL,
  `email` varchar(100) NOT NULL,
  `website` varchar(100) NOT NULL,
  `profil_perusahaan` text NOT NULL,
  `logo` varchar(50) NOT NULL,
  `pj` varchar(30) NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `hp` varchar(30) NOT NULL,
  `ket_lain` text NOT NULL,
  `tanggal_daftar` date NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perusahaan`
--

INSERT INTO `perusahaan` (`id`, `username`, `password`, `nama_perusahaan`, `jenis`, `sektor`, `bidang`, `alamat_perusahaan`, `provinsi`, `kab`, `telepon`, `fax`, `email`, `website`, `profil_perusahaan`, `logo`, `pj`, `jabatan`, `hp`, `ket_lain`, `tanggal_daftar`, `status`) VALUES
(10, 'rizky', '123', 'asdsa', 'Instansi Pemerintah', 'Industri Pengolahan', 'Agribisnis', 'asdsa', 'Aceh', 'Kab. Aceh Barat', 'sada', 'sada', '0', '0', 'asdas', 'utaha.jpg', 'asdasd', 'asdsa', 'asda', 'asdsa', '2017-06-29', 'Aktif'),
(11, 'putra', '123', 'PT Maju Mundur', 'Perorangan', 'Jasa-Jasa', 'Hiburan', 'Jalan PM Noor Perum Bumi Sempaja', 'Kalimantan Timur', 'Kota Bontang', '0679893', '213182378', '0', '0', 'asdasdasda', 'utaha.jpg', 'rizky pratama', 'CEO', '088779089989', 'ASDKASDA', '2017-06-29', 'Non Aktif');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `berita`
--
ALTER TABLE `berita`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pelatihan`
--
ALTER TABLE `pelatihan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pencari`
--
ALTER TABLE `pencari`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perusahaan`
--
ALTER TABLE `perusahaan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `berita`
--
ALTER TABLE `berita`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `pelatihan`
--
ALTER TABLE `pelatihan`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `pencari`
--
ALTER TABLE `pencari`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `perusahaan`
--
ALTER TABLE `perusahaan`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
