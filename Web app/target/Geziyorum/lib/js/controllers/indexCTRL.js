var myApp = angular.module("geziyorumAppModel", []);
myApp.controller("indexCTRL", mainIndex);


function mainIndex($scope,$http,$q,$timeout){

$scope.showLoginTemplate = true;
$scope.showRegisterTemplate = false;
$scope.local=true;
$scope.appName="";
$scope.dataLoading = false;
$scope.sifremiUnuttumUsername = "";

if(!$scope.local){
	$scope.appName = "/Geziyorum/";
}

$scope.loading = function(){
    $scope.dataLoading = true;
}

$scope.loaded = function(){
    $scope.dataLoading = false;
}


$scope.login = function(){

    $scope.loading();
    var data = {
		username : $scope.loginUsername,
		password: $scope.loginPassword
    };

    var url = $scope.appName+'login';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, JSON.stringify(data), config).then(function (response) {
            $scope.loaded();
            $scope.session=response.data;
            deleteAllCookies();
            document.cookie = "access_token="+$scope.session +";"
            //document.cookie = "application="+$scope.session+";path/";  // mobile yapar.
            $scope.redirectHomePage();
        }, function (response) {
            $scope.loaded();
            $scope.error=JSON.parse(response.data);
            bootbox.alert($scope.error.message);
        });

}


$scope.register = function(){

    if($scope.validateRegister() == false)
        return;

    $scope.loading();
    var user = {
        username : $scope.regUserName,
        password : $scope.regPassword,
        name : $scope.regName,
        surname : $scope.regSurname,
        email : $scope.regMail
    };

    var url = $scope.appName+'userRegister';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, JSON.stringify(user), config).then(function (response) {
            $scope.uploadResult=JSON.parse(response.data);
            if($scope.uploadResult == true){
                bootbox.alert("Başarıyla üyelik oluşturuldu");
            }
                $scope.loaded();
        }, function (response) {
            $scope.error=JSON.parse(response.data);
            bootbox.alert($scope.error.message);
                $scope.loaded();
        });

           //location.reload();
}




$scope.validateRegister = function(){

    if($scope.regUserName == null || $scope.regUserName == ""
        || $scope.regPassword  == null || $scope.regPassword  == "" 
        || $scope.regName  == null || $scope.regName  == ""
        || $scope.regSurname == null || $scope.regSurname == ""
        || $scope.regMail  == null || $scope.regMail  == ""){

        bootbox.alert("Kayıt için her alan doldurulmalıdır.");
        return false;
    }    

    if($scope.regPassword != $scope.regPasswordValidation){
        bootbox.alert("Şifreler uyuşmamaktadır.");
        return false;
    }

    if($scope.isNumeric($scope.regName) || $scope.isNumeric($scope.regSurname)){
        bootbox.alert("İsim soyisim numerik değer içeremez.");
        return false;
    }

    if($scope.isNonEnglish($scope.regUserName) == false) {
        bootbox.alert('Kullanıcı adı türkçe karakter içermektedir.');
        return false;
    }

    if($scope.validateMail($scope.regMail) == false){
        bootbox.alert("Lütfen düzgün bir e-mail adresi giriniz.");
        return false;
    }

    return true;
}



$scope.forgotPassword = function(){
    
    $scope.loading();
    var data = $scope.sifremiUnuttumUsername;
    var url = $scope.appName+'forgotPassword';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url,data, config).then(function (response) {
            bootbox.alert("Kayıtlı mail'ine şifreni yenilemek için yapman gerekenleri yolladık.");
            $scope.loaded();
        }, function (response) {
            $scope.error=JSON.parse(response.data);
            console.log($scope.error.message);
            bootbox.alert("Yanlış bir talep oluşturdunuz.");
                $scope.loaded();
        });

}




$scope.isNonEnglish = function(str){
    var tester = /^[A-Za-z0-9]*$/;
    var response = tester.test(str);
    return response;
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

$scope.redirectHomePage = function(){
     location.href="home";
}

$scope.redirectSupportPage = function(){
     location.href="support";
}


$scope.changeToRegister = function(){
    $scope.showLoginTemplate = false;
    $scope.showRegisterTemplate = true;    
}

$scope.changeToLogin = function(){
    $scope.showLoginTemplate = true;
    $scope.showRegisterTemplate = false;    
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