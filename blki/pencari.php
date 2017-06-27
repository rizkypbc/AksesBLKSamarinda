<?php require_once("PDOConnection.php");?>
<?php
// include("PDOConnection.php");

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
	$provinsi = $_POST['provinsi'];
	$kab = $_POST['kab'];
	$asal = $_POST['asal'];
	$pendidikan = $_POST['pendidikan'];
	$ketrampilan = $_POST['ketrampilan'];
	$pengalaman = $_POST['pengalaman'];
	$alumni = $_POST['alumni'];
	$urlphoto = $_POST['urlphoto'];
	$urlcv = $_POST['urlcv'];

	if(ACTION_ADD_USER == $action){


		if(isExistUser($cnn, $username)){

			$result = RESULT_USER_EXISTS;
		} else{

			insertUser($cnn, $username, $password, $noktp, $nama_pencari, $jk_pencari, $ttl_pencari, $provinsi, $kab, $asal, $pendidikan, $ketrampilan, $pengalaman, $alumni, $urlphoto, $urlcv);
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


function insertUser($cnn, $username, $password, $noktp, $nama_pencari, $jk_pencari, $ttl_pencari, $provinsi, $kab, $asal, $pendidikan, $ketrampilan, $pengalaman, $alumni, $urlphoto, $urlcv){

	$query = "INSERT INTO PENCARI(USERNAME, PASSWORD, NOKTP, NAMA_PENCARI, JK_PENCARI, TTL_PENCARI, PROVINSI, KAB, ASAL, PENDIDIKAN, KETRAMPILAN, PENGALAMAN, ALUMNI, URLPHOTO, URLCV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	$stmt = $cnn->prepare($query);
	// $stmt->bindParam(1, $id);
	$stmt->bindParam(1, $username);
	$stmt->bindParam(2, $password);
	$stmt->bindParam(3, $noktp);
	$stmt->bindParam(4, $nama_pencari);
	$stmt->bindParam(5, $jk_pencari);
	$stmt->bindParam(6, $ttl_pencari);
	$stmt->bindParam(7, $provinsi);
	$stmt->bindParam(8, $kab);
	$stmt->bindParam(9, $asal);
	$stmt->bindParam(10, $pendidikan);
	$stmt->bindParam(11, $ketrampilan);
	$stmt->bindParam(12, $pengalaman);
	$stmt->bindParam(13, $alumni);
	$stmt->bindParam(14, $urlphoto);
	$stmt->bindParam(15, $urlcv);
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