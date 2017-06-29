<?php
require_once 'include/db_functions.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['username']) && isset($_POST['password']) ) {
 
    // receiving the post params
    $username = $_POST['username'];
    $password = $_POST['password'];
 
    // get the user by email and password
    $user = getUserByUsernameAndPassword($username, $password);
 
    if ($user != false) {
        // user is found
        $response["error"] = FALSE;
		$response["user"]["id"] = $user["id"];
        $response["user"]["username"] = $user["username"];
        $response["user"]["password"] = $user["password"];
        $response["user"]["status"] = 'Aktif';
        
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Wrong usernames or password entered! Please try again!";
        echo json_encode($response);
    }
} else {
    //required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing :(!";
    echo json_encode($response);
}
?>