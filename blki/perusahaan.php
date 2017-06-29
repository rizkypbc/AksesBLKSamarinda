<?php
include("PDOConnection.php");

//Define some value
define("ACTION_ADD_USER", "add");
// define("ACTION_LOGIN", "login");
define("RESULT_SUCCESS", 0);
define("RESULT_ERROR", 1);
define("RESULT_USER_EXISTS", 2);

$action = $_POST["action"];
$result = RESULT_ERROR;

if(isset($action)){

	// $id = $_POST["id"];
	$username = $_POST["username"];
	$password = $_POST["password"];
	$nama_perusahaan = $_POST["nama_perusahaan"];
	$jenis = $_POST['jenis'];
	$sektor = $_POST['sektor'];
	$bidang = $_POST['bidang'];
	$alamat_perusahaan = $_POST['alamat_perusahaan'];
	$provinsi = $_POST['provinsi'];
	$kab = $_POST['kab'];
	$telepon = $_POST['telepon'];
	$fax = $_POST['fax'];
	$email = $_POST['email'];
	$website = $_POST['website'];
	$profil_perusahaan = $_POST['profil_perusahaan'];
	$logo = $_POST['logo'];
	$pj = $_POST['pj'];
	$jabatan = $_POST['jabatan'];
	$hp = $_POST['hp'];
	$ket_lain = $_POST['ket_lain'];
	// $status = $_POST['status'];
	

	if(ACTION_ADD_USER == $action){


		if(isExistUser($cnn, $username)){

			$result = RESULT_USER_EXISTS;
		} else{

			insertUser($cnn, $username, $password, $nama_perusahaan, $jenis, $sektor, $bidang, $alamat_perusahaan, $provinsi, $kab, $telepon, $fax, $email, $website, $profil_perusahaan, $logo, $pj, $jabatan, $hp, $ket_lain);
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
}
//Print result as json
echo(json_encode(array("result" => $result)));


function insertUser($cnn, $username, $password, $nama_perusahaan, $jenis, $sektor, $bidang, $alamat_perusahaan, $provinsi, $kab, $telepon, $fax, $email, $website, $profil_perusahaan, $logo, $pj, $jabatan, $hp, $ket_lain){

	$query = "INSERT INTO PERUSAHAAN(USERNAME, PASSWORD, NAMA_PERUSAHAAN, JENIS, SEKTOR, BIDANG, ALAMAT_PERUSAHAAN, PROVINSI, KAB, TELEPON, FAX, EMAIL, WEBSITE, PROFIL_PERUSAHAAN, LOGO, PJ, JABATAN, HP, KET_LAIN, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Non Aktif')";
	$stmt = $cnn->prepare($query);
	// $stmt->bindParam(1, $id);
	$stmt->bindParam(1, $username);
	$stmt->bindParam(2, $password);
	$stmt->bindParam(3, $nama_perusahaan);
	$stmt->bindParam(4, $jenis);
	$stmt->bindParam(5, $sektor);
	$stmt->bindParam(6, $bidang);
	$stmt->bindParam(7, $alamat_perusahaan);
	$stmt->bindParam(8, $provinsi);
	$stmt->bindParam(9, $kab);
	$stmt->bindParam(10, $telepon);
	$stmt->bindParam(11, $fax);
	$stmt->bindParam(12, $email);
	$stmt->bindParam(13, $website);
	$stmt->bindParam(14, $profil_perusahaan);
	$stmt->bindParam(15, $logo);
	$stmt->bindParam(16, $pj);
	$stmt->bindParam(17, $jabatan);
	$stmt->bindParam(18, $hp);
	$stmt->bindParam(19, $ket_lain);
	// $stmt->bindParam(20, $status);
	$stmt->execute();
}

function isExistUser($cnn, $username){

	$query = "SELECT * FROM PENCARI WHERE USERNAME = ?";
	$stmt = $cnn->prepare($query);
	$stmt->bindParam(1, $id);
	$stmt->execute();
	$rowcount = $stmt->rowCount();
	//for debug
	//var_dump($rowcount);
	return $rowcount;
}

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