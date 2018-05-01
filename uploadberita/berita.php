<?php

include_once("connection.php");
// $query = "SELECT pid, name, qty, price, image_url
// 		 FROM tbl_product ORDER BY pid DESC";

$query = "SELECT id, tanggal, judul, deskripsi, nama_file, keterangan FROM berita ORDER BY id DESC";

$result = mysqli_query($conn, $query);

while ($row = mysqli_fetch_assoc($result)) {
	
	$data[] = $row;
}

echo json_encode($data);
?>