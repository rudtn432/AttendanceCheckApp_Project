<?php
    error_reporting(E_ALL);
    ini_set('display_errors', 1);


    $hostname = 'localhost';
    $username = 'root';
    $password = '000516';
    $database = 'timetable'; 

    $con = mysqli_connect($hostname, $username, $password, $database);

    if (!$con) {
        die("Connection failed: " . mysqli_connect_error());
    }

    if(isset($_POST['userID']) || isset($_GET['userID'])){
        $userID = isset($_POST['userID']) ? $_POST['userID'] : $_GET['userID'];

        $sql = "SELECT * FROM timetable WHERE userID = '$userID'";
        $result = mysqli_query($con, $sql);

        $data = array();

        if (mysqli_num_rows($result) > 0) {
            while ($row = mysqli_fetch_assoc($result)) {
                $data[] = $row;
            }
        }

        echo json_encode($data, JSON_UNESCAPED_UNICODE);
    } else {
        echo "userID 입력바람.";
    }

    mysqli_close($con);
?>