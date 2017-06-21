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
	$noktp = $_POST["noktp"];
	$nama_pencari = $_POST['nama_pencari'];
	$jk_pencari = $_POST['jk_pencari'];
	$ttl_pencari = $_POST['ttl_pencari'];
	// $notelp = $_POST['notelp'];
	// $email = $_POST['email'];
	// $agama = $_POST['agama'];
	// $pendidikan = $_POST['pendidikan'];
	// $jurusan = $_POST['jurusan'];
	// $asal_sekolah = $_POST['asal_sekolah'];
	// $kejuruan = $_POST['kejuruan'];
	// $sub_kejuruan = $_POST['sub_kejuruan'];
	// $program = $_POST['program'];
	// $urlphoto = $_POST['urlphoto'];

	if(ACTION_ADD_USER == $action){

		//Check exists user
		if(isExistUser($cnn, $username)){

			$result = RESULT_USER_EXISTS;
		} else{

			insertUser($cnn, $username, $password, $noktp, $nama_pencari, $jk_pencari, $ttl_pencari);
			$result = RESULT_SUCCESS;
		}

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

function insertUser($cnn, $username, $password, $noktp, $nama_pencari, $jk_pencari, $ttl_pencari){

	$query = "INSERT INTO PENCARI(USERNAME, PASSWORD, NOKTP, NAMA_PENCARI, JK_PENCARI, TTL_PENCARI) VALUES (?, ?, ?, ?, ?, ?)";
	$stmt = $cnn->prepare($query);
	// $stmt->bindParam(1, $id);
	$stmt->bindParam(1, $username);
	$stmt->bindParam(2, $password);
	$stmt->bindParam(3, $noktp);
	$stmt->bindParam(4, $nama_pencari);
	$stmt->bindParam(5, $jk_pencari);
	$stmt->bindParam(6, $ttl_pencari);

	$stmt->execute();
}

function isExistUser($cnn, $USERNAME){

	$query = "SELECT * FROM PELATIHAN WHERE USERNAME = ?";
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