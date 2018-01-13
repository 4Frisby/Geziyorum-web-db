var myApp = angular.module("geziyorumAppModel", []);
myApp.controller("indexCTRL", mainIndex);

function mainIndex($scope,$http,$q,$timeout){
$scope.local=true;

if(!$scope.local){
	$scope.appName = "/Geziyorum/";
}else{
    $scope.appName = "";
}

$scope.register = function(){
    var data = {
        username : $scope.regUserName,
        password : $scope.regPassword,
        name : $scope.regName,
        surname : $scope.regSurname,
        mail : $scope.regMail
    };

    var url = $scope.appName+'registerNewUser';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, JSON.stringify(data), config).then(function (response) {
            $scope.uploadResult=response.data;
        }, function (response) {
            $scope.uploadResult=response.data;
        });

           location.reload();
}


}