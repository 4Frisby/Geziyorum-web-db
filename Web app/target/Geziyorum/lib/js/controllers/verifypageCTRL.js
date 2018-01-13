var myApp = angular.module("geziyorumAppModel", []);
myApp.controller("verifypageCTRL", mainIndex);


function mainIndex($scope,$http,$q,$timeout){

$scope.showLoginTemplate = true;
$scope.showRegisterTemplate = false;
$scope.local=true;
$scope.appName="";

if(!$scope.local){
	$scope.appName = "/Geziyorum/";
}


$scope.verified = function(){

	redirectLogin = function(){
	     location.href="/Geziyorum";
	}
	$timeout(redirectLogin, 4000);
}

$scope.verified();




}
