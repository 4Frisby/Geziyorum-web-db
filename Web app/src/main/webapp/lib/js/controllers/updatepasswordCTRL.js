var myApp = angular.module("geziyorumAppModel", []);
myApp.controller("recreatepasswordCTRL", mainIndex);


function mainIndex($scope,$http,$q,$timeout){
$scope.local=true;
$scope.appName="";

if(!$scope.local){
	$scope.appName = "/Geziyorum/";
}

$scope.oldPassword = "";
$scope.newPassword = "";
$scope.newPasswordValidate = "";
$scope.user = null;



$scope.getWhoForgotPassword = function(){

    var data = "dummy";
    var url = $scope.appName+'../getWhoForgotPassword';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, data, config).then(function (response) {
       		$scope.user = JSON.parse(response.data);
        }, function (response) {
            $scope.error=JSON.parse(response.data);
            bootbox.alert($scope.error.message);
        });
}

$scope.getWhoForgotPassword();


$scope.updatePassword = function(username){
	if($scope.validatePassword() == false)
		return false;

    var obj = {
    	username : $scope.user.username,
    	newPassword : $scope.newPassword
    }
    var data = JSON.stringify(obj);
    var url = $scope.appName+'../updatePassword';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, data, config).then(function (response) {
            bootbox.alert("Şifren yenilendi.");
			     redirectLogin = function(){
			     location.href="/Geziyorum";
			     }
			$timeout(redirectLogin, 3000);            
        }, function (response) {
            $scope.error=JSON.parse(response.data);
            bootbox.alert($scope.error.message);
        });

}


$scope.validatePassword = function(){


	if($scope.user.password == $scope.newPassword){
		bootbox.alert("Eski ve yeni şifren farklı olmalıdır.");
		return false;
	}

	if($scope.newPassword != $scope.newPasswordValidate){
		bootbox.alert("Yeni şifreleriniz uyuşmamaktadır.");
		return false;
	}
}


}
