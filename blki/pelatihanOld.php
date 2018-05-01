<?php
include("PDOConnection.php");
header('Content-Type: application/json');

//Define some value
define("ACTION_ADD_USER", "add");
// define("ACTION_LOGIN", "login");
define("RESULT_SUCCESS", 0);
define("RESULT_ERROR", 1);


$action = $_POST["action"];
$result = RESULT_ERROR;


if(isset($action)){

	// $id = $_POST["id"];
	$nama = $_POST["nama"];
	$jk = $_POST["jk"];
	$ttl = $_POST["ttl"];
	$alamat = $_POST['alamat'];
	$provinsi = $_POST['provinsi'];
	$kab_kota = $_POST['kab_kota'];
	$notelp = $_POST['notelp'];
	$email = $_POST['email'];
	$agama = $_POST['agama'];
	$pendidikan = $_POST['pendidikan'];
	$jurusan = $_POST['jurusan'];
	$asal_sekolah = $_POST['asal_sekolah'];
	$kejuruan = $_POST['kejuruan'];
	$sub_kejuruan = $_POST['sub_kejuruan'];
	$program = $_POST['program'];
	$urlphoto = $_POST['urlphoto'];

	if(ACTION_ADD_USER == $action){

		//Check exists user
		// if(isExistUser($cnn, $id)){

		// 	$result = RESULT_USER_EXISTS;
		// } else{

			insertUser($cnn, $nama, $jk, $ttl, $alamat, $provinsi, $kab_kota, $notelp, $email, $agama, $pendidikan,
					$jurusan, $asal_sekolah, $kejuruan, $sub_kejuruan, $program, $urlphoto);
			$result = RESULT_SUCCESS;
		// }

	}
	// else //ACTION LOGIN
	// {
	// 	if(login($cnn, $username, $pwd)){

	// 		$result = RESULT_SUCCESS;
	// 		//login success
	// 	}
	// 	else{
	// 		//login fail
	// 		$result = RESULT_ERROR;
	// 	}
	// }
}
//Print result as json
echo(json_encode(array("result" => $result)));

function insertUser($cnn, $nama, $jk, $ttl, $alamat, $provinsi, $kab_kota, $notelp, $email, $agama, $pendidikan,
					$jurusan, $asal_sekolah, $kejuruan, $sub_kejuruan, $program, $urlphoto){

	$query = "INSERT INTO PELATIHAN(NAMA, JK, TTL, ALAMAT, PROVINSI, KAB_KOTA, NOTELP, EMAIL, AGAMA, PENDIDIKAN,
				JURUSAN, ASAL_SEKOLAH, KEJURUAN, SUB_KEJURUAN, PROGRAM, URLPHOTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	$stmt = $cnn->prepare($query);
	// $stmt->bindParam(1, $id);
	$stmt->bindParam(1, $nama);
	$stmt->bindParam(2, $jk);
	$stmt->bindParam(3, $ttl);
	$stmt->bindParam(4, $alamat);
	$stmt->bindParam(5, $provinsi);
	$stmt->bindParam(6, $kab_kota);
	$stmt->bindParam(7, $notelp);
	$stmt->bindParam(8, $email);
	$stmt->bindParam(9, $agama);
	$stmt->bindParam(10, $pendidikan);
	$stmt->bindParam(11, $jurusan);
	$stmt->bindParam(12, $asal_sekolah);
	$stmt->bindParam(13, $kejuruan);
	$stmt->bindParam(14, $sub_kejuruan);
	$stmt->bindParam(15, $program);
	$stmt->bindParam(16, $urlphoto);
	$stmt->execute();
}

// function isExistUser($cnn, $id){

// 	$query = "SELECT * FROM PELATIHAN WHERE ID = ?";
// 	$stmt = $cnn->prepare($query);
// 	$stmt->bindParam(1, $id);
// 	$stmt->execute();
// 	$rowcount = $stmt->rowCount();
// 	//for debug
// 	//var_dump($rowcount);
// 	return $rowcount;
// }

// function login($cnn, $username, $pwd){

// 	$query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
// 	$stmt = $cnn->prepare($query);
// 	$stmt->bindParam(1, $username);
// 	$stmt->bindParam(2, $pwd);
// 	$stmt->execute();
// 	$rowcount = $stmt->rowCount();
// 	//for debug
// 	//var_dump($rowcount);
// 	return $rowcount;

// }