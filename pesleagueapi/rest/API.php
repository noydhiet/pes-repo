<?php

/*
    This is an example class script proceeding secured API
    To use this class you should keep same as query string and function name
    Ex: If the query string value rquest=delete_user Access modifiers doesn't matter but function should be
         function delete_user(){
             You code goes here
         }
    Class will execute the function dynamically;

    usage :

        $object->response(output_data, status_code);
        $object->_request	- to get santinized input

        output_data : JSON (I am using)
        status_code : Send status message for headers

    Add This extension for localhost checking :
        Chrome Extension : Advanced REST client Application
        URL : https://chrome.google.com/webstore/detail/hgmloofddffdnphfgcellkdfbfbjeloo

    I used the below table for demo purpose.

    CREATE TABLE IF NOT EXISTS `users` (
      `user_id` int(11) NOT NULL AUTO_INCREMENT,
      `user_fullname` varchar(25) NOT NULL,
      `user_email` varchar(50) NOT NULL,
      `user_password` varchar(50) NOT NULL,
      `user_status` tinyint(1) NOT NULL DEFAULT '0',
      PRIMARY KEY (`user_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
 */

require_once("Rest.inc.php");

class API extends REST {

	public $data = "";

	const DB_SERVER = "localhost";
	const DB_USER = "indraper_pes";
	const DB_PASSWORD = "123qweasd";
	const DB = "indraper_pes_league_db";

	private $db = NULL;

	public function __construct(){
		parent::__construct();				// Init parent contructor
		$this->dbConnect();					// Initiate Database connection
	}

	/*
     *  Database connection
    */
	private function dbConnect(){

		//$this->db = mysql_connect(self::DB_SERVER,self::DB_USER,self::DB_PASSWORD);
		$this->db = new mysqli(self::DB_SERVER,self::DB_USER,self::DB_PASSWORD, "indraper_pes_league_db");


		if ($this->db->connect_errno > 0)
			die("Connection Failed : " . mysqli_connect_error());
		//else
		//echo "Connect aing euy";


		//mysqli_select_db(self::DB, $this->conn);


	}

	/*
     * Public method for access api.
     * This method dynmically call the method based on the query string
     *
     */
	public function processApi(){
		$func = strtolower(trim(str_replace("/","",$_REQUEST['rquest'])));
		if((int)method_exists($this,$func) > 0)
			$this->$func();
		else
			$this->response('',404);				// If the method not exist with in this class, response would be "Page not found".
	}

	/*
     *	Simple login API
     *  Login must be POST method
     *  email : <USER EMAIL>
     *  pwd : <USER PASSWORD>
     */

	private function login(){
		// Cross validation if the request method is POST else it will return "Not Acceptable" status
		if($this->get_request_method() != "POST"){
			$this->response('',406);
		}

		$email = $this->_request['email'];
		$password = $this->_request['pwd'];

		// Input validations
		if(!empty($email) and !empty($password)){
			if(filter_var($email, FILTER_VALIDATE_EMAIL)){
				$sql = mysql_query("SELECT user_id, user_fullname, user_email FROM users WHERE user_email = '$email' AND user_password = '".md5($password)."' LIMIT 1", $this->db);
				if(mysql_num_rows($sql) > 0){
					$result = mysql_fetch_array($sql,MYSQL_ASSOC);

					// If success everythig is good send header as "OK" and user details
					$this->response($this->json($result), 200);
				}
				$this->response('', 204);	// If no records "No Content" status
			}
		}

		// If invalid inputs "Bad Request" status message and reason
		$error = array('status' => "Failed", "msg" => "Invalid Email address or Password");
		$this->response($this->json($error), 400);
	}

	private function users(){
		echo "test user";
		// Cross validation if the request method is GET else it will return "Not Acceptable" status
		/*if($this->get_request_method() != "GET"){
            $this->response('',406);
        }
        $sql = mysql_query("SELECT user_id, user_fullname, user_email FROM users WHERE user_status = 1", $this->db);
        if(mysql_num_rows($sql) > 0){
            $result = array();
            while($rlt = mysql_fetch_array($sql,MYSQL_ASSOC)){
                $result[] = $rlt;
            }
            // If success everythig is good send header as "OK" and return list of users in JSON format
            $this->response($this->json($result), 200);
        }
        $this->response('',204);	// If no records "No Content" status*/
	}

	private function getLiveScores(){
		// Cross validation if the request method is POST else it will return "Not Acceptable" status

		//echo 'masuk bray' . $this->get_request_method();

		if($this->get_request_method() != "POST"){
			$this->response('',406);
		}

		//$eventType = '';
		//function !IsNullOrEmptyString($_POST['event_type']){
		//    $event_type = $_POST['event_type'];
		//}
		//$email = $_POST['email'];


		$sql = "Select ls.*, event.name_pes_event_type from pes_live_score as ls inner join pes_event_type event on event.id_pes_event_type = ls.pes_event_type_id_pes_event_type";

		if(!$result = $this->db->query($sql)){
			die('There was an error running the query [' . $this->db->error . ']');
		}

		$result_json = array();

		while($row = $result->fetch_assoc()){
			array_push($result_json, $row);
			//$result_json = $row;
		}
		$respon = array('message' => 'Live Score Data', 'data' => $result_json);
		$this->response(json_encode($respon), 200);

		// If invalid inputs "Bad Request" status message and reason
		$error = array('message' => "Failed", "data" => mysqli_query($this->conn, $sql));
		$this->response(json_encode($error), 400);
	}

	private function getLiveScoresBasedEventType(){
		if($this->get_request_method() != "GET"){
			$this->response('',406);
		}

		$eventType = $_GET['event_type'];
		$sql = "Select ls.*, event.name_pes_event_type from pes_live_score as ls inner join pes_event_type event on event.id_pes_event_type = ls.pes_event_type_id_pes_event_type where event.name_pes_event_type = '".$eventType."'";

		if(!$result = $this->db->query($sql)){
			die('There was an error running the query [' . $this->db->error . ']');
		}

		$result_json = array();

		while($row = $result->fetch_assoc()){
			array_push($result_json, $row);
			//$result_json = $row;
		}
		$respon = array('message' => 'Live Score Data', 'data' => $result_json);
		$this->response(json_encode($respon), 200);

		// If invalid inputs "Bad Request" status message and reason
		$error = array('message' => "Failed", "data" => mysqli_query($this->conn, $sql));
		$this->response(json_encode($error), 400);
	}

	private function getEventTypes(){
		if($this->get_request_method() != "POST"){
			$this->response('',406);
		}


		$sql = "Select * from pes_event_type";

		if(!$result = $this->db->query($sql)){
			die('There was an error running the query [' . $this->db->error . ']');
		}

		$result_json = array();

		while($row = $result->fetch_assoc()){
			array_push($result_json, $row);
			//$result_json = $row;
		}
		$respon = array('message' => "Event Types Data", 'data' => $result_json);
		$this->response(json_encode($respon), 200);

		// If invalid inputs "Bad Request" status message and reason
		$error = array('message' => "Failed", "data" => mysqli_query($this->conn, $sql));
		$this->response(json_encode($error), 400);
	}

	private function getListEvents(){
		// Cross validation if the request method is POST else it will return "Not Acceptable" status

		//echo 'masuk bray' . $this->get_request_method();

		if($this->get_request_method() != "POST"){
			$this->response('',406);
		}


		/*if (mysqli_query($this->conn, $sql)){
            if (mysqli_num_rows($sql) > 0){
                $result = array();
                while($rlt = mysqli_fetch_array($sql)){
                    $result[] = $rlt;
                }
                $this->response($this->json($result), 200);
            }
        }*/


		$sql = "select * from pes_event a, pes_event_type b where a.pes_event_type_id_pes_event_type = b.id_pes_event_type;";

		if(!$result = $this->db->query($sql)){
			die('There was an error running the query [' . $this->db->error . ']');
		}

		$result_json = array();

		while($row = $result->fetch_assoc()){
			$result_json = $row;
		}
		$this->response($this->json($result_json), 200);

		// If invalid inputs "Bad Request" status message and reason
		$error = array('status' => "Failed", "msg" => mysqli_query($this->conn, $sql));
		$this->response($this->json($error), 400);
	}

	private function deleteUser(){
		// Cross validation if the request method is DELETE else it will return "Not Acceptable" status
		if($this->get_request_method() != "DELETE"){
			$this->response('',406);
		}
		$id = (int)$this->_request['id'];
		if($id > 0){
			mysql_query("DELETE FROM users WHERE user_id = $id");
			$success = array('status' => "Success", "msg" => "Successfully one record deleted.");
			$this->response($this->json($success),200);
		}else
			$this->response('',204);	// If no records "No Content" status
	}

	/*
     *	Encode array into JSON
    */
	private function json($data){
		if(is_array($data)){
			return json_encode($data);
		}
	}
}

// Initiiate Library

$api = new API;
$api->processApi();
?>