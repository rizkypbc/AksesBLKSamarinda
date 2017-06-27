-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2017 at 06:06 PM
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
  `tanggal` varchar(250) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `deskripsi` text NOT NULL,
  `nama_file` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `berita`
--

INSERT INTO `berita` (`id`, `tanggal`, `judul`, `deskripsi`, `nama_file`) VALUES
(8, '25-10-2017', 'asdkjaskd', 'asdad', 'utaha.jpg');

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
  `urlphoto` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelatihan`
--

INSERT INTO `pelatihan` (`id`, `nama`, `jk`, `ttl`, `alamat`, `provinsi`, `kab_kota`, `notelp`, `email`, `agama`, `pendidikan`, `jurusan`, `asal_sekolah`, `kejuruan`, `sub_kejuruan`, `program`, `urlphoto`) VALUES
(13, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '11', '1'),
(14, 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'),
(15, 'kerja', 'kerja', 'ke', 'ke', 'ke', 'ek', 'ekeke', 'ke', 'kekee', 'ke', 'ke', 'ke', 'ke', 'ke', 'ke', 'ke'),
(16, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'),
(17, '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5'),
(18, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 'Personal', '7'),
(19, '8', '8', '8', '8', '8', '8', '8', '', '8', '8', '8', '8', '8', '8', 'Russia', '8'),
(20, '15', 'Female', '15', '151', '151', '151', '151', '151', '15', '15', '151', '15', '15', '151', 'Malaysia', '151'),
(21, 'Rizky Pratama Putra', 'Male', 'Bontang, 25 Juni 1995', 'Jalan PM Noor Perum Bumi Sempa', 'London', 'Kab. Bontang', '085388394854', 'rizky_scream@yahoo.com', 'Islam', 'S1', 'Teknik Informatika', 'SMAN 2 Bontang', 'Teknik Otomotif', 'Telekomunikasi', 'London', 'rizky.jpeg'),
(22, 'ridah', 'Perempuan', 'Bontang, 30 November 1999', 'Jalan Ahmda Yno', 'Malaysia', 'Kab. Samarinda', '085250992899', 'ridah@gmail.com', 'Islam', 'SLTA', 'Teknik Kimia', 'SMAN 1 Bontang', 'Teknik Otomotif', 'Telekomunikasi', 'Malaysia', 'ridah.png'),
(32, 'bbbbb', 'Laki-Laki', 'bbbb', 'bbb', 'India', 'Kab. Bontang', 'bbb', 'bbb', 'Islam', 'SD', 'Teknik Informatika', 'bbbb', 'Agrobisnis', 'Agrobisnis Produksi Peternakan', 'India', '/storage/emulated/0/Download/utaha.jpg'),
(33, 'rizky', 'Laki-Laki', 'Bontang, 25 Juni 1995', 'Jalan Buntu', 'India', 'Kab. Samarinda', '0815371', 'rizky@scream@yahoo.com', 'Budha', 'S2', 'Teknik Informatika', 'Sman 2 Bontang', 'Agrobisnis', 'Agrobisnis Produksi Peternakan', 'India', '/storage/sdcard1/DCIM/Camera/IMG_2017060'),
(34, 'fdgfdgd', 'Laki-Laki', 'dfgdfgdf', 'dfgdfgd', 'India', 'Kota Bontang', 'dsgdgdfgsd', 'fsdfsfsd', 'Katolik', 'SD', 'Teknik Informatika', 'xcvxvx', 'Agrobisnis', 'Agrobisnis Produksi Peternakan', 'India', '/storage/emulated/0/Download/utaha.jpg'),
(35, 'asdasdasasdas', 'Laki-Laki', 'asdassadda', 'asdasdas', 'India', 'Kab. Aceh Barat', 'asdasd', 'asdasd', 'Islam', 'SD', 'asdasdas', 'asdasdas', 'asdasdas', 'Agrobisnis Produksi Peternakan', 'India', '/storage/emulated/0/Download/utaha.jpg');

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
  `urlphoto` varchar(50) NOT NULL,
  `urlcv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pencari`
--

INSERT INTO `pencari` (`id`, `username`, `password`, `noktp`, `nama_pencari`, `jk_pencari`, `ttl_pencari`, `provinsi`, `kab`, `asal`, `pendidikan`, `ketrampilan`, `pengalaman`, `alumni`, `urlphoto`, `urlcv`) VALUES
(1, 'b', 'b', 'b', 'b', 'Laki-Laki', 'b', 'Aceh', 'Kab. Aceh Barat', 'b', 'b', 'b', 'b', 'Ya', '/storage/emulated/0/Download/utaha.jpg', '/storage/emulated/0/DCIM/Camera/IMG_20170610_14234'),
(2, 'chunchun', '12345', '1123345789', 'iwan', 'Laki-Laki', 'pinrang', 'Kalimantan Timur', 'Kab. Nunukan', 'hhhh', 'hzhzhsh', 'bsbbzb', 'hzbxb', 'Tidak', '', ''),
(3, 'kyrisk', '1234', 'jsvdhsu', 'safei', 'Laki-Laki', 'Bontang, 30 November 1999', 'Kalimantan Timur', 'Kota Bontang', 'SMAN 2 Bontang', 'hdjiaisjrjznsn', 'hdhbsjsjjsjs', 'bsbsnnsns', 'Ya', '/storage/sdcard1/DCIM/Camera/IMG_20170531_120502.j', '/storage/sdcard1/DCIM/Camera/IMG_20170529_104859_H'),
(4, 'rizky', '12345', '08290138102', 'rizky pratama putra', 'Laki-Laki', 'Bontang, 25 Juni 1995', 'Aceh', 'Kab. Bontang', 'SMAN 2 Bontang', 'asdalsdakldl,askdnajnd', 'asdasdasdaakc,sada', 'asdcasdasdmaskdmasd', 'Tidak', '/storage/emulated/0/Download/utaha.jpg', '/storage/emulated/0/Download/utaha.jpg'),
(5, 'rizkypbc', '555', '9819377891', 'ridha', 'Perempuan', 'Samarinda, 30 November 1999', 'Bali', 'Kab. Samarinda', 'SMAN 1 Bontang', 'xvcxvxcvxc', 'xcvxcvxcv', 'xcvxcv', 'Tidak', '/storage/emulated/0/Download/utaha.jpg', '/storage/emulated/0/DCIM/Camera/IMG_20170610_14234'),
(6, 'x', '1', 'xzc', 'zxc', 'Laki-Laki', 'zcz', 'Aceh', 'Kab. Aceh Barat', 'xcvxc', 'cxvx', 'xcvxcv', 'xvxc', 'Ya', '/storage/emulated/0/Download/utaha.jpg', '/storage/emulated/0/Download/utaha.jpg'),
(7, 'z', 'z', 'zz', 'z', 'Laki-Laki', 'z', 'Aceh', 'Kab. Aceh Barat', 'z', 'z', 'z', 'z', 'Ya', '/storage/emulated/0/Download/utaha.jpg', '/storage/emulated/0/Download/utaha.jpg');

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
  `email` int(30) NOT NULL,
  `website` int(30) NOT NULL,
  `profil_perusahaan` text NOT NULL,
  `logo` varchar(50) NOT NULL,
  `pj` varchar(30) NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `hp` varchar(30) NOT NULL,
  `ket_lain` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perusahaan`
--

INSERT INTO `perusahaan` (`id`, `username`, `password`, `nama_perusahaan`, `jenis`, `sektor`, `bidang`, `alamat_perusahaan`, `provinsi`, `kab`, `telepon`, `fax`, `email`, `website`, `profil_perusahaan`, `logo`, `pj`, `jabatan`, `hp`, `ket_lain`) VALUES
(1, 'asdasd', '123', 'asdasda', 'Agrobisnis', 'Agrobisnis Produksi Peternakan', 'Teknik Informatika', 'asdasdasd', 'Aceh', 'Kab. Bontang', 'asdasdas', 'asdasd', 0, 0, 'asdasd', '/storage/emulated/0/Download/utaha.jpg', 'asdasdas', 'asdasdasd', 'asdasdasdasd', 'asdasda'),
(2, 'rizky', '123', 'asd', 'asd', 'asda', 'asd', 'asdsa', 'Aceh', 'Kab. Aceh Barat', 'asda', 'sada', 0, 0, 'asd', '/storage/emulated/0/Download/utaha.jpg', 'asda', 'asda', 'asdas', 'asda');

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
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `pelatihan`
--
ALTER TABLE `pelatihan`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `pencari`
--
ALTER TABLE `pencari`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `perusahaan`
--
ALTER TABLE `perusahaan`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
