myApp.controller("supportpageCTRL", mainIndex);


function mainIndex($scope,$http,$q,$timeout){


$scope.nameSurname = "";
$scope.email = "";
$scope.phone = "";
$scope.message = "";


$scope.redirectToIndex = function(){
     location.href="/";
}


$scope.cikisYap = function(){
    var url = $scope.appName+'cikisYap';
    var data = $scope.sessionCookie;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    location.href="/Geziyorum";
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}


$scope.yolla = function(){
  if($scope.validateContent() == false){
    return;
  }
      var url ='createSm';
    var obj = {
      name : $scope.nameSurname,
      mail : $scope.email,
      phone : $scope.phone,
      message: $scope.message
    }
    var data = JSON.stringify(obj);
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        bootbox.alert("Mesajınız kaydedildi.");
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Beklenmedik hata!");
        console.log($scope.error.message);
    }); 


}

$scope.validateContent = function(){
  if($scope.mail == "" || $scope.phone == "" || $scope.message == "" || $scope.nameSurname == ""){
     bootbox.alert("Lütfen her alanı doldurunuz.");
     return false;
  } 

    if($scope.isNumeric($scope.nameSurname)){
        bootbox.alert("İsim soyisim numerik değer içeremez.");
        return false;
    }

  if($scope.validateMail($scope.email) == false){
      bootbox.alert("Lütfen düzgün bir e-mail adresi giriniz.");
      return false;
  }
}


$scope.isNumeric = function(str) {
    var tester = /^[-+]?(\d+|\d+\.\d*|\d*\.\d+)$/;
    var response = tester.test(str);
    return response;
}


$scope.validateMail = function (mailAddr) {
    var re = /\S+@\S+\.\S+/;
    return re.test(mailAddr);
}


}






