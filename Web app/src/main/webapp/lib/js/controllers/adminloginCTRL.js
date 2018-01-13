var myApp = angular.module("geziyorumAppModel", []);
myApp.controller("adminloginCTRL", mainIndex);

function mainIndex($scope,$http,$q,$timeout){
$scope.local=true;
$scope.appName="";

if(!$scope.local){
	$scope.appName = "/Geziyorum/";
}

$scope.login = function(){
    var data = {
		username : $scope.loginUsername,
		password: $scope.loginPassword
    };

    var url = $scope.appName+'adminLogin';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, JSON.stringify(data), config).then(function (response) {
            var resp = JSON.parse(response.data);
            $scope.session=resp.sessionToken;
            deleteAllCookies();
            document.cookie = "access_token="+$scope.session//+";application="+$scope.session+";path/";
            $scope.redirectAdminPanel();
        }, function (response) {
            $scope.error=JSON.parse(response.data);
            bootbox.alert($scope.error.message);
        });

}


$scope.redirectAdminPanel = function(){
     location.href="adminpanel";
}

$scope.redirectSupportPage = function(){
     location.href="support";
}


function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}


}