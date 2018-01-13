var myApp = angular.module('geziyorumAppModel', ['ngMap','ngSanitize','ngAnimate','ngToast','angular-loading-bar']);
myApp.controller("profilepageCTRL", mainIndex);

myApp.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = false;
  cfpLoadingBarProvider.includeBar = true;
  cfpLoadingBarProvider.loadingBarTemplate = '<div id="loading-bar"><div class="bar"><div class="peg"></div></div></div>';
  }])


function mainIndex($scope,$http,$q,$timeout,NgMap,ngToast,cfpLoadingBar){

$scope.userGeneralInfo = null;
$scope.local=true;
$scope.profileEdit = false;
$scope.profileEditButtonYazisi = "Profili Düzenle";
$scope.appName="";
$scope.sessionCookie = null;
$scope.dataLoading = false;


// cookie işlemleri
$scope.cookieName = "access_token";
$scope.mobileCookieName = "application";

$scope.mobileCookie = getMobileToken();

if(typeof $scope.mobileCookie === "undefined"){
  $scope.mobileCookie = false;
  $scope.isMobileFlag = false;
}else
{
  $scope.isMobileFlag = true;
}

$scope.sessionCookie = getSessionCookieValue();
if($scope.sessionCookie === null){
  bootbox.alert("Session cookie hatası");
  location.href ="/Geziyorum";
}
// cookie işlemleri

// personal info nullization
$scope.userUsername = null
$scope.userName = null;
$scope.userSurname = null;
$scope.userPersonalInfo = null;
$scope.userWebsite = null;
$scope.userEmail = null;
$scope.userNumber = null;
$scope.userOldPassword = null;
$scope.userNewPassword = null;
$scope.userNewPasswordValidation = null;
$scope.checkUserGeneralInfoChange = false;
// personal info nullization

//dosya seçilmiş mi ? hayır.
$scope.isFileSelected = false;

//şifreler aynı mı ? evet
$scope.arePasswordsSame = true;

$scope.selectedFile = null;
$scope.activeTab = 1;
$scope.profileChangeActiveTab = 1;
$scope.showProfileTabOne = true;
$scope.showProfileTabTwo = false;
$scope.ifHaveFriendRequest = true;  // arkadaş isteği var mı varsa arkadaş isteklerin varı gösterecek flag
$scope.showFriendRequests = false;  // arkadaş isteği listesini gösterem mi flag


$scope.profilePictureUrl = null;

$scope.friendsCount = 0;
$scope.sharingCount = 0;
$scope.gonderiCount = 0;
$scope.friendsList = null;

$scope.tripVideos = [];
$scope.showTripVideos = false;
$scope.tripPictures = [];
$scope.showTripPictures = false;
$scope.tripAudios = [];
$scope.showTripAudios = false;
$scope.rootPaylasimDetay = null;

$scope.seciliGonderi = null;
$scope.seciliGonderiIndex = null;

$scope.yorumIcerik = null;
$scope.seciliGonderiCommentleri = [];
$scope.arkadaslikIstekListe = null;



$scope.geziHizi = 2000; // 6 default sn

$scope.geziDevamEt = null;
$scope.streetGoster = false; // ilk açıldığında ekran street view kapalı gelsin diye

$scope.begenPaylasCount = null;

$scope.path = []; // normal hali

$scope.tripPath = []; // stringify hali

$scope.tripDetay = null;

$scope.tripOlusturucInfo = null;
$scope.grupGezisiFlag = null; 
$scope.geziKatilimcilarInfo = null;

$scope.commentNotifications = [];

$scope.begenButtonText = "Beğen";

$scope.showGonderiSikayetPaneFlag = false;


$scope.selectedMedyaDetay = null;
$scope.selectedMedyaDetayTip =  null;
$scope.showMedyaDetayPaneFlag = false;


$scope.commentNotificationsCount = 0;
$scope.friendRequestsCount = 0;

$scope.yeniNotificationFlag = false;

$scope.aramaMetini = null;

$scope.ifLiked = false;


$scope.headerUsername = null;
$scope.headerProfilePhoto = null;

$scope.comNotArama = "";
$scope.friendReqArama = "";

$scope.sikayetListOfUser = [];
$scope.sikayetListSearchText = "";

$scope.showGonderiDuzenleCheck = false;
$scope.notDownloadMediaIdList = [];


//dosya yukleme degiskenleri
$scope.videoYukleFlag = true;
$scope.resimYukleFlag = false;
$scope.sesYukleFlag = false;
$scope.secilecekMedyaTipleri = [
  {
    kod :0,
    isim : "Video"},
  {
    kod :1,
    isim : "Resim"
  },
  {
    kod:2,
    isim:"Ses"
  }  
];
$scope.seciliMedyaTipi = $scope.secilecekMedyaTipleri[0];
$scope.kimGorebilir = [
  {
    kod :0,
    isim : "Herkes"},
  {
    kod :1,
    isim : "Sadece arkadaşlarım"
  },
  {
    kod :2,
    isim : "Sadece Ben"
  }
];
$scope.kimGorebilirSecili = $scope.kimGorebilir[0];
$scope.medyaEkleNot = null;
$scope.uploadVideoFile = null;
$scope.uploadResimFile = null;
$scope.uploadSesFile = null;


if(!$scope.local){
  $scope.appName = "/Geziyorum/";
}

$scope.initalizeVariables = function(){
$scope.restCount = 3;
$scope.currentCount = 0;
$scope.userGeneralInfo = null;
$scope.local=true;
$scope.profileEdit = false;
$scope.profileEditButtonYazisi = "Profili Düzenle";
$scope.appName="";
$scope.sessionCookie = null;
$scope.cookieName = "access_token";


// personal info nullization
$scope.userUsername = null
$scope.userName = null;
$scope.userSurname = null;
$scope.userPersonalInfo = null;
$scope.userWebsite = null;
$scope.userEmail = null;
$scope.userNumber = null;
$scope.userOldPassword = null;
$scope.userNewPassword = null;
$scope.userNewPasswordValidation = null;
$scope.checkUserGeneralInfoChange = false; // alınan bilgiler değiştirilmiş mi ? hayır.
// personal info nullization

//dosya seçilmiş mi ? hayır.
$scope.isFileSelected = false;

//şifreler aynı mı ? evet
$scope.arePasswordsSame = true;

$scope.selectedFile = null;
$scope.activeTab = 1;
$scope.profileChangeActiveTab = 1;
$scope.showProfileTabOne = true;
$scope.showProfileTabTwo = false;
$scope.ifHaveFriendRequest = true;  // arkadaş isteği var mı varsa arkadaş isteklerin varı gösterecek flag
$scope.showFriendRequests = false;  // arkadaş isteği listesini gösterem mi flag

// cookie işlemleri
$scope.cookieName = "access_token";
$scope.mobileCookieName = "application";

$scope.mobileCookie = getMobileToken();

if(typeof $scope.mobileCookie === "undefined"){
  $scope.mobileCookie = false;
  $scope.isMobileFlag = false;
}else
{
  $scope.isMobileFlag = true;
}

$scope.sessionCookie = getSessionCookieValue();
if($scope.sessionCookie === null){
  bootbox.alert("Session cookie hatası");
  location.href ="/Geziyorum";
}
// cookie işlemleri

$scope.photo1src = null;
$scope.dbURL = null;

$scope.tripVideos = [];
$scope.showTripVideos = false;
$scope.tripPictures = [];
$scope.showTripPictures = false;
$scope.tripAudios = [];
$scope.showTripAudios = false;

$scope.rootPaylasimDetay = null;


$scope.seciliGonderi = null;
$scope.seciliGonderiIndex = null;

$scope.yorumIcerik = null;
$scope.seciliGonderiCommentleri = [];
$scope.arkadaslikIstekListe = null;

$scope.geziHizi = 2000; // 6 default sn
$scope.streetGoster = false; // ilk açıldığında ekran street view kapalı gelsin diye

$scope.begenPaylasCount = null;


$scope.path = []; 

$scope.tripPath = [];

$scope.tripDetay = null;

$scope.tripOlusturucInfo = null;
$scope.grupGezisiFlag = null; 
$scope.geziKatilimcilarInfo = null;

$scope.begenButtonText = "Beğen";

$scope.commentNotifications = [];


$scope.showGonderiSikayetPaneFlag = false;

$scope.selectedMedyaDetay = null;
$scope.selectedMedyaDetayTip =  null;
$scope.showMedyaDetayPaneFlag = false;

$scope.commentNotificationsCount = 0;
$scope.friendRequestsCount = 0;

$scope.yeniNotificationFlag = false;

$scope.aramaMetini = null;

$scope.ifLiked = false;


$scope.headerUsername = null;
$scope.headerProfilePhoto = null;


$scope.comNotArama = "";
$scope.friendReqArama = "";

$scope.sikayetListOfUser = [];

$scope.sikayetListSearchText = "";

$scope.showGonderiDuzenleCheck = false;

$scope.notDownloadMediaIdList = [];

$scope.videoYukleFlag = true;
$scope.resimYukleFlag = false;
$scope.sesYukleFlag = false;
$scope.medyaEkleNot = null;

//page yeniden yüklendiğinde seçili dosya varsa uçsun
document.getElementById("uploadBox").value = "";
}


$scope.aramaYap = function(){
  var e = document.getElementById("aramaTip");
  var aramaTip = e.options[e.selectedIndex].value;

  var data = {
    token : $scope.sessionCookie,
    arananTip : aramaTip,
    aramaMetni : $scope.aramaMetini
  }

    var url = $scope.appName+'aramaKaydiOlustur';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, JSON.stringify(data), config).then(function (response) {
        if(response.data)
          location.href="search"; 
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

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
    deleteAllCookies();
    location.href="/Geziyorum";
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}

$scope.loading = function(){
    $scope.dataLoading = true;
}

$scope.loaded = function(){
    $scope.dataLoading = false;
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

$scope.populateNotifications=function(){

}

$scope.profileEditPanel = function(){
  if($scope.profileEdit){
    $scope.profileEdit = false;
    $scope.profileEditButtonYazisi = "Profili Düzenle";
  }else{
    $scope.profileEdit = true;
    $scope.profileEditButtonYazisi = "Profili Görüntüle";
  } 
}


$scope.showFriendRequestList = function(){
      $scope.showFriendRequests = true;
}

$scope.showNotificationList = function(){
      $scope.showFriendRequests = false;
}

$scope.setActiveTab = function(value){
  $scope.activeTab = value;
  if(value === 1){
    $scope.showProfileTabOne = true;
    $scope.showProfileTabTwo = false;
  }else{
    $scope.showProfileTabOne = false;
    $scope.showProfileTabTwo = true;
  }
}

$scope.setProfileChangeActiveTab = function(value){
  $scope.profileChangeActiveTab = value;
}








$scope.checkUserGeneralInfoChanged = function(){
      if(
      ($scope.userName !=            $scope.userGeneralInfo.name) || 
      ($scope.userSurname !=         $scope.userGeneralInfo.surname) ||
      ($scope.userPersonalInfo !=    $scope.userGeneralInfo.personalInfo) ||
      ($scope.userEmail !=           $scope.userGeneralInfo.email) ||
      ($scope.userWebsite !=         $scope.userGeneralInfo.website) ||
      ($scope.userNumber !=          $scope.userGeneralInfo.phone)
      )
        $scope.checkUserGeneralInfoChange = true;
      else
        $scope.checkUserGeneralInfoChange = false;
}

$scope.validateMail = function (mailAddr) {
    var re = /\S+@\S+\.\S+/;
    return re.test(mailAddr);
}


$scope.isNumeric = function(str) {
    var tester = /^[-+]?(\d+|\d+\.\d*|\d*\.\d+)$/;
    var response = tester.test(str);
    return response;
}

$scope.isNonEnglish = function(str){
    var tester = /^[A-Za-z0-9]*$/;
    var response = tester.test(str);
    return response;
}

$scope.validURL = function(str){
  var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
  '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.?)+[a-z]{2,}|'+ // domain name
  '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
  '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
  '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
  '(\\#[-a-z\\d_]*)?$','i');
  if(!pattern .test(str)) {
    return false;
  } else {
    return true;
  }
}


$scope.validateRegister = function(){
    if(!$scope.validateIfUserInfoChanged)
    return false;

    if($scope.validateMail($scope.userEmail) == false){
        bootbox.alert("Lütfen düzgün bir e-mail adresi giriniz.");
        return false;
    }

    if($scope.isNumeric($scope.userName) || $scope.isNumeric($scope.userSurname)){
        bootbox.alert("İsim soyisim numerik değer içeremez.");
        return false;
    }

    if($scope.isNumeric($scope.userNumber) == false && $scope.userNumber !=""){
      bootbox.alert("Telefon numarası alanına sadece numerik karakter giriniz.");
        return false;
    }

    if($scope.validURL($scope.userWebsite) == false){
      bootbox.alert("Lütfen düzgün bir websitesi giriniz.");
      return false;
    }

    return true;

}

$scope.saveUserGeneralInfo = function(){
  

    if($scope.validateRegister() == false)
      return;

    var userInfo = {
      name : $scope.userName,
      surname : $scope.userSurname,
      personalInfo : $scope.userPersonalInfo,
      email : $scope.userEmail,
      website : $scope.userWebsite,
      phone : $scope.userNumber 
    }

    var obj = {
      token : $scope.sessionCookie,
      user : userInfo      
    }

    var data = obj;

    var url = $scope.appName+'saveUserGeneralInfo';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, JSON.stringify(data), config).then(function (response) {
            location.reload();
            $scope.getPageContent();
            $scope.profileEditPanel();
            bootbox.alert("Başarıyla kaydedildi");
        }, function (response) {
            $scope.error=JSON.parse(response.data);
            bootbox.alert($scope.error.message);
            bootbox.alert("Bilgileri değiştirmede hata ile karşılaşıldı.");
        });    
}

$scope.validateIfUserInfoChanged = function(){
    if($scope.checkUserGeneralInfoChange)
      {
       return true;
      }else{
        bootbox.alert("Kullanıcı bilgileri değiştirilmelidir.");
        return false;
      }

}




// değişim olmuş mu kontrolü yapmak için başka değişkenlerde tutmak mantıklıdır.
$scope.populateUserInfoOnPage = function(){
      $scope.userUsername =        $scope.userGeneralInfo.username;
      $scope.userName =            $scope.userGeneralInfo.name;
      $scope.userSurname =         $scope.userGeneralInfo.surname;
      $scope.userPersonalInfo =    $scope.userGeneralInfo.personalInfo;
      $scope.userEmail =           $scope.userGeneralInfo.email;
      $scope.userWebsite =         $scope.userGeneralInfo.website;
      $scope.userNumber =          $scope.userGeneralInfo.phone;
}

$scope.getOldInfosOfUser = function(){
  $scope.populateUserInfoOnPage();
}

//https://stackoverflow.com/questions/21015891/spring-mvc-angularjs-file-upload-org-apache-commons-fileupload-fileuploade/21016324#21016324
// angular ajax'ında sıkıntı vardı pure javascript ile yazdım.
$scope.saveProfilePhoto = function (){
  //create form data to send via POST
  var formData=new FormData();

  console.log('loading json info');
  /*var client = "hello";
  var toJSON = angular.toJson(client,true);
  Basic string dışında object yollayacaksın json formatına çevirmen lazım.
  */

  formData.append('token',$scope.sessionCookie); 
  // !!! when calling formData.append the boundary is auto generated!!!
  // but... there is no way to know which boundary is being used !!!

  console.log('loading file');
  var file= document.getElementById('uploadBox').files[0];// you should load the 
  formData.append('file',file);

  var uploadUrl="uploadProfilePhoto"
  //create the ajax request (traditional way)
  var request = new XMLHttpRequest();
  request.open('POST', uploadUrl);
  request.send(formData);  
}


//you need this function to convert the dataURI
function dataURItoBlob(dataURI) {
  var binary = atob(dataURI.split(',')[1]);
  var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
  var array = [];
  for (var i = 0; i < binary.length; i++) {
    array.push(binary.charCodeAt(i));
  }
  return new Blob([new Uint8Array(array)], {
    type: mimeString
  });
}



$scope.zipFile = null;
$scope.uploadZip = function() {
  $scope.uploadme= $scope.zipFile;
  var fd = new FormData();
  var imgBlob = dataURItoBlob($scope.uploadme);
  fd.append('file', imgBlob);
  fd.append('token',$scope.sessionCookie);
  fd.append('fileName',"trip");
  var url = "uploadTripZip";
  var data = fd;
  var config = {
        transformRequest: angular.identity,
        headers: {
          'Content-Type': undefined
        }
      };

   $http.post(url, data, config).then(function (response) {
        var file = response;
          $timeout( function(){
          location.reload();
          }, 1000 );
    }, function (response) {
        //$scope.error=JSON.parse(response.data);
        bootbox.alert(response.message);
    });  

}


// bu aralık silinecek deneme amaçlı grup gezisinin testinde kullanıldı.




$scope.eklenecekUserlar = [];
$scope.grupGeziTanimi = null;
$scope.grupArkiAciklama = null;
$scope.eklenecekUser = null;

$scope.geziKatilimciEkle = function(){
  $scope.eklenecekUserlar.push($scope.eklenecekUser);
}



$scope.cevapTripId = null;

$scope.talepOlustur = function(){
    var url = $scope.appName+'createTripDemand';
    var obj = {
      token : $scope.sessionCookie,
      tripAciklama : $scope.grupGeziTanimi,
      arkadaslaraAciklama : $scope.grupArkiAciklama,
      usernames : $scope.eklenecekUserlar
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
    $scope.cevapTripId = response.data;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });  

}


$scope.geziTalepleriGoruntule = function(){

}

$scope.zipFileGrupOlusturucu = null;

$scope.uploadZipGrupOlusturucu = function() {
  $scope.uploadme= $scope.zipFileGrupOlusturucu;
  var fd = new FormData();
  var imgBlob = dataURItoBlob($scope.uploadme);
  fd.append('file', imgBlob);
  fd.append('token',$scope.sessionCookie);
  fd.append('tripId',50);
  fd.append('fileName',"trip");
  fd.append('isFirstFileUpload',true);
  var url = "uploadGroupTripZip";
  var data = fd;
  var config = {
        transformRequest: angular.identity,
        headers: {
          'Content-Type': undefined
        }
      };

   $http.post(url, data, config).then(function (response) {
        var file = response;
          $timeout( function(){
          location.reload();
          }, 1000 );
    }, function (response) {
        //$scope.error=JSON.parse(response.data);
        bootbox.alert(response.message);
    });  

}

$scope.zipFileGrupKatilimci = null;
$scope.uploadZipGrupKatilimci = function() {
  $scope.uploadme= $scope.zipFileGrupKatilimci;
  var fd = new FormData();
  var imgBlob = dataURItoBlob($scope.uploadme);
  fd.append('file', imgBlob);
  fd.append('tripId', 50); 
  fd.append('token',$scope.sessionCookie);
  fd.append('fileName',"trip");
  fd.append('isFirstFileUpload',false);
  var url = "uploadGroupTripZip";
  var data = fd;
  var config = {
        transformRequest: angular.identity,
        headers: {
          'Content-Type': undefined
        }
      };

   $http.post(url, data, config).then(function (response) {
        var file = response;
          $timeout( function(){
          location.reload();
          }, 1000 );
    }, function (response) {
        //$scope.error=JSON.parse(response.data);
        bootbox.alert(response.message);
    });  

}


// bu aralık silinecek deneme amaçlı grup gezisinin testinde kullanıldı.


$scope.ppUploading = false;

$scope.uploadImage = function() {
  $scope.uploadme= $scope.xx;
  var fd = new FormData();
  var imgBlob = dataURItoBlob($scope.uploadme);
  fd.append('file', imgBlob);
  fd.append('token',$scope.sessionCookie);
  var url = "uploadProfilePhoto";
  var data = fd;
  var config = {
        transformRequest: angular.identity,
        headers: {
          'Content-Type': undefined
        }
      };

   $scope.ppUploading = true;      
   $http.post(url, data, config).then(function (response) {
        var file = response;
        $scope.ppUploading = false;  
        bootbox.alert("Başarıyla kaydedildi.");
          $timeout( function(){
          location.reload();
          }, 1000 );
    }, function (response) {
        //$scope.error=JSON.parse(response.data);
        $scope.ppUploading = false;          
        bootbox.alert(response.message);
    });  

}




$scope.getPaylasimList = function(){
    var url = $scope.appName+'getPaylasimList';
    var obj = {
      token : $scope.sessionCookie,
      username : userName
    }
    var data = obj;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    var url = response.data;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}




$scope.getTripYorumInfo = function(){
    var url = $scope.appName+'downloadProfilePhotoPath';
    var data = $scope.sessionCookie;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    var url = response.data;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.getPersonalSharingLikeCount = function(){
    var url = $scope.appName+'getPersonalSharingLikeCount';
    var obj = {
      token : $scope.sessionCookie,
      username : userName
    }
    var data = obj;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    var url = response.data;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.getTripSharedCount = function(){
    var url = $scope.appName+'downloadProfilePhotoPath';
    var obj = {
      token : $scope.sessionCookie,
      username : userName
    }
    var data = obj;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    var url = response.data;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}



$scope.checkFileSelected = function(){
  var files = event.target.files;
  if(document.getElementById("uploadBox").value != "") {
     $scope.isFileSelected = true;
  }else{
    $scope.isFileSelected = false;
  }
} 


$scope.validateIfPhotoSelected = function(){
        if($scope.isFileSelected){
          return true;
        }
        else
        {
          bootbox.alert("Yüklenecek fotoğraf seçilmelidir");
          return false;
        }
}

$scope.checkPasswordsAreDifferent = function(){
  if($scope.userNewPassword == null || $scope.userOldPassword == null || $scope.userNewPasswordValidation == null)
    {
      $scope.arePasswordsSame = true;
      return;
    }

  if($scope.userNewPassword == $scope.userOldPassword)
    $scope.arePasswordsSame = true;
  else if($scope.userNewPassword == $scope.userNewPasswordValidation)
    $scope.arePasswordsSame = false;
}

$scope.changeUserPassword = function(){
  var data = {
    token : $scope.sessionCookie,
    newPassword : $scope.userNewPassword,
    oldPassword : $scope.userOldPassword
  }

    var url = $scope.appName+'changeUserPassword';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, JSON.stringify(data), config).then(function (response) {
        bootbox.alert("Başarıyla kaydedildi.");
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });  

}

$scope.clearUserPasswordChangeFields = function(){
      $scope.userOldPassword = "";
      $scope.userNewPassword = "";
      $scope.userNewPasswordValidation = "";
}



$scope.getUserProfilePicture = function(){
 
} 

$scope.getUserFollowList = function(){
    
              return true;

} 

$scope.redirectLogin = function(){
     location.href="/";
}

$scope.redirectHomePage = function(){
     location.href="home";
}

$scope.redirectMyProfile = function(){
    location.href ="profile"
}

$scope.redirectMyNotifications = function(){
    location.href ="notifications"
}

$scope.getPageContent = function(){



}


/*
*şikayet alanı başlangıç
*/

$scope.sikayetGoruntule = false;
$scope.seciliSikayetSonuc = null;
$scope.sikayetSonucGoruntule = function(sikayet){
  if(sikayet.sonucDurum == 0)
    sikayet.sonucDurum = "Gizlenmedi";
  else
    sikayet.sonucDurum = "Gizlendi";
  $scope.seciliSikayetSonuc = sikayet;
  $scope.sikayetGoruntule = true;
}

$scope.sikayetlereGeriDon = function(){
  $scope.sikayetGoruntule = false;
}

$scope.getSikayetOfOwner = function(){

    $scope.sikayetGoruntule = false;
    $scope.seciliSikayetSonuc = null;    
    var url = $scope.appName+'getSikayetListOfUser';
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.headerUsername
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
        var obj = JSON.parse(response.data);
        for(i=0; i<obj.length; i++){
          obj[i].sikayetZamani = new Date(obj[i].sikayetZamani).toLocaleString('en-GB');
          obj[i].degerlendirilmeZamani = new Date(obj[i].degerlendirilmeZamani).toLocaleString('en-GB');
        }
        $scope.sikayetListOfUser = obj;
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });



}

$scope.getSikayetList = function(token,callback){

    var url = $scope.appName+'getSikayetTypes';
    var data = token;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.sikayetList = [];
$scope.sikayetMetini = null;
$scope.selectedSikayet = null;
$scope.sikayetTuru = null; // 1 - yorum, 2 - gonderi, 3 - profil

$scope.yorumSikayetEt = function(){

    var yorumSikayet = {
      sikayetEdilenYorumId : $scope.seciliYorum.id, 
      sikayetEdilenUsername : $scope.seciliYorum.username,
      yorumIcerik : $scope.seciliYorum.content,
      sikayetType : $scope.selectedSikayet.sikayetTypeCode,
      sikayetMetini : $scope.sikayetMetini,
    }

    var obj = {
      token : $scope.sessionCookie,
      yorumSikayet : yorumSikayet
    }

    var url = $scope.appName+'createYorumSikayet';
    var data = JSON.stringify(obj);
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        if(response.data)
          bootbox.alert("Talebiniz oluşturuldu.");
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}


$scope.gonderiSikayetEt = function(){


}

$scope.profilSikayetEt = function(){

}

$scope.showProfilSikayet = function(){

  $scope.sikayetMetini = null;
  $scope.sikayetTuru = 3;
  $scope.showSikayetPane(); 

}

$scope.seciliYorum = null;

$scope.showYorumSikayet = function(comment){

  $scope.sikayetMetini = null;
  $scope.seciliYorum = comment;
  $scope.sikayetTuru = 1;
  $scope.showSikayetPane(); 


}

$scope.showGonderiSikayet = function(){
  
  $scope.sikayetMetini = null;
  $scope.sikayetTuru = 2;
  $scope.showSikayetPane(); 

}


$scope.sikayetEt = function(){
  if($scope.sikayetTuru == 1)
    $scope.yorumSikayetEt();
  if($scope.sikayetTuru == 2)
    $scope.gonderiSikayetEt();
  if($scope.sikayetTuru == 3)
    $scope.profilSikayetEt();


}


$scope.showSikayetPane = function(){
  $scope.showGonderiSikayetPaneFlag = true;
     $scope.getSikayetList($scope.sessionCookie,function(response){
        $scope.sikayetList = JSON.parse(response.data);
        $scope.selectedSikayet = $scope.sikayetList[0];
     });  
}


$scope.hideGonderiSikayetPane = function(){
  $scope.showGonderiSikayetPaneFlag = false;
}


/*
*şikayet alanı başlangıç
*/





$scope.gonderiGizle = function(psId){

bootbox.confirm({
    message: "Gönderiyi silmekte emin misin?",
    buttons: {
        confirm: {
            label: 'Evet'
        },
        cancel: {
            label: 'Hayır'
        }
    },
    callback: function (result) {
        if(result == false)
          return;
        var url = $scope.appName+'gonderiGizle';
        var obj = {
          token : $scope.sessionCookie,
          id : $scope.seciliGonderi.personalSharingId
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
        bootbox.alert("Gönderi başarıyla silindi.",function(){
              location.reload();
        });
      }, function (response) {
          $scope.error=JSON.parse(response.data);
          bootbox.alert($scope.error.message);
      });   
    }
});


}



$scope.getPersonalSharingComments = function(token,psID,callback){

    var url = $scope.appName+'getComments';
      var obj = {
        token : token,
        personalSharingId : psID
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}


$scope.commentOnPersonalSharing = function(){

    var url = $scope.appName+'commentTrip';
      var obj = {
        token : $scope.sessionCookie,
        personalSharingId : $scope.seciliGonderi.personalSharingId,
        content : $scope.yorumIcerik,
        sendTime : new Date().getTime(),
        bahsedilenlerUserName : []

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

     $scope.getPersonalSharingComments($scope.sessionCookie,$scope.seciliGonderi.personalSharingId,function(response){
         var comments = JSON.parse(response.data);
         for(i=0; i<comments.length; i++ ){
          comments[i].sendTime = new Date(comments[i].sendTime).toLocaleString('en-GB');
        }
        $scope.seciliGonderiCommentleri = comments;
     });
      ngToast.create({
        content: 'Yorumunuz kaydedildi.',
        className: 'info'
      });       

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });  

    });   

}

$scope.getTripMedia = function(token,tripId,callback){
    var url = $scope.appName+'getTripMedias';
      var obj = {
        token : token,
        tripId : tripId
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}


$scope.getPersonalSharingDetay = function(token,psID,callback){
    var url = $scope.appName+'getPersonalSharingDetay';
      var obj = {
        token : token,
        id : psID
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.getTripDetay = function(token,tripId,callback){
    var url = $scope.appName+'getTripDetay';
      var obj = {
        token : token,
        id : tripId
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.getTripPath = function(token,tripId,callback){
      var url = $scope.appName+'getTripPath';
      var obj = {
        token : token,
        id : tripId
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });  
}



$scope.getOlusturucuInfo = function(token,tripId,callback){
      var url = $scope.appName+'getTripOlusturucuInfo';
      var obj = {
        token : token,
        id : tripId
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.checkGrupGezisi = function(token,tripId,callback){
      var url = $scope.appName+'checkIfGroupTrip';
      var obj = {
        token : token,
        id : tripId
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 
}

$scope.getKatilimcilarInfo = function(token,tripId,callback){
      var url = $scope.appName+'getKatilimciInfo';
      var obj = {
        token : token,
        id : tripId
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}

$scope.likeTripProcessFlag = false;

$scope.likeTrip = function(token,personalSharingId,callback){
    if($scope.likeTripProcessFlag==true)
      return;

      var url = $scope.appName+'likeTrip';
      var obj = {
        token : token,
        id : personalSharingId
      }
    var data = JSON.stringify(obj);
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }
      $scope.likeTripProcessFlag = true;
   $http.post(url, data, config).then(function (response) {
    ngToast.create({
      content: 'Gönderi beğenildi.',
      className: 'success'
    });    
    $scope.likeTripProcessFlag = false;    
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });  
    $scope.likeTripProcessFlag = false;        
    }); 
}

$scope.unlikeTripProcessFlag = false;
$scope.unlikeTrip = function(token,personalSharingId,callback){
      if($scope.unlikeTripProcessFlag == true)
        return;  
      var url = $scope.appName+'unlikeTrip';
      var obj = {
        token : token,
        id : personalSharingId
      }
    var data = JSON.stringify(obj);
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }
    $scope.unlikeTripProcessFlag = true;
   $http.post(url, data, config).then(function (response) {
    ngToast.create(
      {
      content: 'Gönderi beğenilmekten vazgeçildi.',
      className: 'danger'
      }
      );  
    $scope.unlikeTripProcessFlag = false;    
    callback(response);
    }, function (response) {
    $scope.unlikeTripProcessFlag = false;      
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });
    });   
}

$scope.likeOrUnlikeTrip = function(likeOrNotLike){
  if(likeOrNotLike == 1){
      $scope.likeTrip($scope.sessionCookie,$scope.seciliGonderi.personalSharingId,function(response){
      $scope.ifLiked = true;
      $scope.begenPaylasCount.likeCount = $scope.begenPaylasCount.likeCount + 1;
    });
  }
  if(likeOrNotLike == 2){
      $scope.unlikeTrip($scope.sessionCookie,$scope.seciliGonderi.personalSharingId,function(response){
      $scope.ifLiked = false;
      $scope.begenPaylasCount.likeCount = $scope.begenPaylasCount.likeCount - 1;      
    });
  }
}


$scope.checkIfILikeTrip = function(token,personalSharingId){
      var url = $scope.appName+'checkIfILikeTrip';
      var obj = {
        token : token,
        id : personalSharingId
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
    var obj = JSON.parse(response.data);
    if(obj)
      $scope.begenButtonText = "Beğendin";
    else
      $scope.begenButtonText = "Beğen";
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 
}



$scope.shareTrip = function(token,personalSharingId){
      var url = $scope.appName+'shareTrip';
      var obj = {
        token : token,
        id : personalSharingId
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
      ngToast.create({
        content: 'Gönderi paylaşıldı.',
        className: 'success'
      });    
      $scope.begenPaylasCount.shareCount = $scope.begenPaylasCount.shareCount + 1; 
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });
    }); 
}

$scope.isPaylasimFlag = false;
$scope.tripPaylasimciUsername = null;
$scope.olusturulmaZamani = null;
$scope.paylasilmaZamani = null;

$scope.showPaylasimDetayPaneWithSharedUser = function(gonderiList,index){

  $scope.paylasilmaZamani = new Date(gonderiList[index].paylasilmaZamani).toLocaleString('en-GB')
  $scope.olusturulmaZamani = new Date(gonderiList[index].sharedTime).toLocaleString('en-GB')
  $scope.tripPaylasimciUsername = gonderiList[index].sharedUserUsername;
  $scope.isPaylasimFlag = true;
  $scope.showPaylasimDetayPane(gonderiList,index);
}


$scope.showGonderiDetayPane = function(gonderiList,index){
  $scope.olusturulmaZamani = new Date(gonderiList[index].sharedTime).toLocaleString('en-GB')  
  $scope.tripPaylasimciUsername = null;
  $scope.isPaylasimFlag = false;
  $scope.showGonderiSikayetPaneFlag = false;
  $scope.showPaylasimDetayPane(gonderiList,index);    
}

$scope.checkIfSameLongLat = function(tripMedias){
  for(i=0; i<tripMedias.length; i++){
    for(j=i+1; j<tripMedias.length; j++){
      if(tripMedias[i].latitude == tripMedias[j].latitude && tripMedias[i].longitude == tripMedias[j].longitude)
        tripMedias[i].latitude = parseFloat(tripMedias[i].latitude.toFixed(5)) + 0.00004;
        tripMedias[i].longitude = parseFloat(tripMedias[i].longitude.toFixed(5)) + 0.00008;
    }
  }
}



$scope.chooseDownloadMedia = function(id,flag){
  var list = $scope.tripDownloadList;
  if(flag == true)
   list.push(id);
  if(flag == false){
    var index = list.indexOf(id);
    if (index > -1) {
    list.splice(index, 1);
    }
  }
  $scope.tripDownloadList = list;  
}

$scope.showGonderiDuzenleChecks = function(){

  if($scope.showGonderiDuzenleCheck){
    $scope.showGonderiDuzenleCheck = false;
      ngToast.create({
        content: 'Düzenleme tamamlandı.',
        className: 'success'
      });     
  }
  else{
    $scope.showGonderiDuzenleCheck = true;
      ngToast.create({
        content: 'İndirmek istemediğiniz dosyalardan tiki kaldırınız.',
        className: 'info'
      });     
  }
}

$scope.tripDownload = function(tId){
      var url = $scope.appName+'tripDownload';
      var obj = {
        downloadList : $scope.tripDownloadList,
        tripId : tId
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
        var link = document.createElement("a");
        link.download = name;
        link.href = response.data;
        link.click();    
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   
}

$scope.tripDownloadList = [];
$scope.allTripMedias = [];

$scope.showPaylasimDetayPane = function(gonderiList,index){
$scope.loading();
  
  $scope.notDownloadMediaIdList = [];
  $scope.showGonderiDuzenleCheck = false;
  $scope.seciliGonderiIndex = index;
  $scope.seciliGonderi = gonderiList[index]; 
  var gonderi = gonderiList[index];
  $scope.getTripMedia($scope.sessionCookie,gonderi.tripId,function(response){
     var tripMedias = JSON.parse(response.data);
     for(i=0; i<tripMedias.length; i++){
        $scope.tripDownloadList[i] = tripMedias[i].fileName;
     }     
     $scope.rootPaylasimDetay = $scope.dbURL.mediapath + gonderi.folderName;
     $scope.tripVideos = [];
     $scope.tripPictures = [];
     $scope.tripAudios = [];
     $scope.allTripMedias = [];

     $scope.checkIfSameLongLat(tripMedias);
     for(i=0; i<tripMedias.length; i++){
          var obj = $scope.rootPaylasimDetay + "/" + $scope.dbURL.tripmediafolder + "/" + tripMedias[i].fileName;
          var pic = {
            "url" : obj,
            "lat" : tripMedias[i].latitude,
            "long" : tripMedias[i].longitude,
            "yorum" : tripMedias[i].note,
            "id" : tripMedias[i].fileName,
            "indirCheck" : true,
            "creatorUsername" : tripMedias[i].creatorUsername,
            "type" : tripMedias[i].type
          }  

        if(tripMedias[i].type == 1){
          $scope.tripVideos.push(pic);
          $scope.showTripVideos= true;
        }

        if(tripMedias[i].type == 2){
          $scope.tripPictures.push(pic);
          $scope.showTripPictures = true;
        }

        if(tripMedias[i].type == 3){
          $scope.tripAudios.push(pic);
          $scope.showTripAudios = true;
        }

        $scope.allTripMedias.push(pic);

     }

     $scope.getPersonalSharingComments($scope.sessionCookie,$scope.seciliGonderi.personalSharingId,function(response){
         var comments = JSON.parse(response.data);
         for(i=0; i<comments.length; i++ ){
          comments[i].sendTime = new Date(comments[i].sendTime).toLocaleString('en-GB');
        }
        $scope.seciliGonderiCommentleri = comments;
     });


     $scope.getTripDetay($scope.sessionCookie,gonderi.tripId,function(response){
        var obj = JSON.parse(response.data);
        obj.starTime = new Date(obj.starTime).toLocaleString('en-GB'); 
        obj.endTime = new Date(obj.endTime).toLocaleString('en-GB'); 
        $scope.tripDetay = obj;


           $scope.getOlusturucuInfo($scope.sessionCookie,$scope.tripDetay.tripId,function(response){
              var obj = JSON.parse(response.data);
              $scope.tripOlusturucInfo = obj; 

           });   // gezi olustrucu bilgileri gelir.

           $scope.checkGrupGezisi($scope.sessionCookie,$scope.tripDetay.tripId,function(response){
              var obj = JSON.parse(response.data);
              $scope.grupGezisiFlag = obj;
                if($scope.grupGezisiFlag){
                     $scope.getKatilimcilarInfo($scope.sessionCookie,$scope.tripDetay.tripId,function(response){
                        var obj = JSON.parse(response.data);
                        $scope.geziKatilimcilarInfo = obj;

                     });            
                }

           });  // gezi katılımcıları bilgileri gelir.     



        $scope.checkIfILikeTrip($scope.sessionCookie,$scope.seciliGonderi.personalSharingId); 
        // beğendiysem butonu beğendi olarak değiştirir.

     })  // zaman mekan gelir.
     

     $scope.getPersonalSharingDetay($scope.sessionCookie,$scope.seciliGonderi.personalSharingId,function(response){
      $scope.begenPaylasCount = JSON.parse(response.data);
      if($scope.begenPaylasCount.likeCount == 0)
        $scope.begenPaylasCount.likeCount = null;

      if($scope.begenPaylasCount.shareCount == 0)
        $scope.begenPaylasCount.shareCount = null;
     })  // beğen paylaş count gelir.

     

     $scope.getTripPath($scope.sessionCookie,gonderi.tripId,function(response){

        $scope.loaded();
        var obj = JSON.parse(response.data);
        var path = [];
        for(i=0; i<obj.length; i++){
          var singlePath = [];
          singlePath[0] = obj[i].latitude;
          singlePath[1] = obj[i].longitude;
          path.push([obj[i].latitude,obj[i].longitude]);
        }
        $scope.path = path;
        $scope.initialPosition = $scope.path[0];
        $scope.tripPath = JSON.stringify(path);

     });
     





  });



}



$scope.showMedyaDetay = function(medyaUrl,tip){

$scope.showMedyaDetayPaneFlag = true;
$scope.selectedMedyaDetayTip = tip;
$scope.selectedMedyaDetay = medyaUrl;

}

$scope.hideMedyaDetay = function(){
  $scope.showMedyaDetayPaneFlag = false;
}


$scope.playVideo = function(index){
  var id = 'video' + index; 
  var video = document.getElementById(id);
  video.play();
  video.webkitRequestFullScreen();
}

$scope.playAudio = function(index){
  var id = 'audio' + index; 
  var audio = document.getElementById(id);
  audio.play();
}



$scope.getOwnerUserInfo = function(callback){
    var url = $scope.appName+'getUserInfoByToken';
    var data = $scope.sessionCookie;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    callback(response);

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}


$scope.getUserInfoByUserId = function(token,userID,callback){
      var url = $scope.appName+'getUserInfoById';
      var obj = {
        token : token,
        userId : userID
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}




$scope.arkadasliktanCikar = function(arkadasIndex){

bootbox.confirm({
    message: "Arkadaşlıktan çıkarmakta emin misin?",
    buttons: {
        confirm: {
            label: 'Evet'
        },
        cancel: {
            label: 'Hayır'
        }
    },
    callback: function (result) {
      if(result == false)
        return;
      var url = $scope.appName+'unfriendFriend';
      var friend = $scope.friendsList[arkadasIndex].username;
      var obj = {
        token : $scope.sessionCookie,
        username : friend
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
                location.reload();
      }, function (response) {
          $scope.error=JSON.parse(response.data);
          bootbox.alert($scope.error.message);
      });    
    }
});
 

}




$scope.getGonderiList = function(token,username,callback){ // sadece listeyi getirir içi boştur bundan alınan bilgiyle üzerine tıklandığında modal dolar.
    var url = $scope.appName+'getPersonalSharingList';
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.userUsername
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   


}


$scope.getPaylasimList = function(oken,username,callback){
    var url = $scope.appName+'getPaylasimList';
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.userUsername
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });     
}

$scope.checkAreWeFriends = function(cookie,otherUsername,callback){
    var url = $scope.appName+'checkAreWeFriends';

    var obj = {
      token : cookie,
      otherUserUsername : otherUsername
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}


$scope.getCommentNotifications = function(myToken,callback){
    var obj = {
      token : myToken
    }
    var data = myToken;
    var url = $scope.appName+'getCommentNotifications';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
      callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        console.log($scope.error.message);
    });   

}


$scope.getCommentNotificationsCount = function(myToken,callback){
    var obj = {
      token : myToken
    }
    var data = myToken;
    var url = $scope.appName+'getCommentNotificationsCount';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      },
      ignoreLoadingBar: true
    }

   $http.post(url, data, config).then(function (response) {
      callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        console.log($scope.error.message);
    });   

}

$scope.getFriendRequests = function(token,callback){
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.userUsername
    }
    var data = JSON.stringify(obj);
    var url = $scope.appName+'getFriendRequests';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
      callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        console.log($scope.error.message);
    });  
}



$scope.getFriendRequestsCount = function(myToken,callback){
    var obj = {
      token : myToken
    }
    var data = myToken;
    var url = $scope.appName+'getFriendRequestsCount';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      },
      ignoreLoadingBar: true
    }

   $http.post(url, data, config).then(function (response) {
      callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        console.log($scope.error.message);
    });   

}




$scope.acceptFriendRequest = function(index){
    var obj = {
      token : $scope.sessionCookie,
      id : $scope.arkadaslikIstekListe[index].friendRequestId
    }
    var data = JSON.stringify(obj);
    var url = $scope.appName+'acceptFriend';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
      console.log(response);
      location.reload();
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        console.log($scope.error.message);
    });  

}


$scope.denyFriendRequest = function(index,callback){
    var obj = {
      token : $scope.sessionCookie,
      id : $scope.arkadaslikIstekListe[index].friendRequestId
    }
    var data = JSON.stringify(obj);
    var url = $scope.appName+'denyFriend';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
      console.log(response.data);
      location.reload();
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        console.log($scope.error.message);
    });  

}


$scope.downloadProfilePhoto = function(username,callback){
    var data = username;
    var url = $scope.appName+'downloadProfilePhotoPath';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
      callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        console.log($scope.error.message);
    });  
}


$scope.downloadFriendProfilePhoto = function(index,username,callback){
    var data = username;
    var url = $scope.appName+'downloadProfilePhotoPath';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
      response.index = [];
      response.index = index;
      callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });  
}



$scope.getUserGeneralInfo = function(cookie,callback){
    var data = $scope.sessionCookie;
    var url = $scope.appName+'getUserGeneralInfo';
       var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': 'application/json'
            }
       }
       
       $http.post(url, data, config).then(function (response) {
          callback(response);

        }, function (response) {
            alert("Kullanıcı bilgileri çekilirken hata alındı");          
            $scope.error=JSON.parse(response.data);
            bootbox.alert($scope.error.message);
        });
} 



$scope.getSharingCount = function(callback){
    var url = $scope.appName+'getSharingCountOfUser';
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.userUsername
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}



$scope.getGonderiCount = function(callback){
    var url = $scope.appName+'getGonderiCountOfUser';
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.userUsername
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
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}


$scope.commentBildirimHepsiOkundu = function(){
    var url = $scope.appName+'deleteAllCommentBildirim';
    var data = $scope.sessionCookie;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }
   $http.post(url, data, config).then(function (response) {
       if(response.data){
        $scope.commentNotificationsCount = 0;
        $scope.commentNotifications = [];
       }
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });  
}


$scope.comBildirimOkundu = function(index){
    var seciliBildirim = $scope.commentNotifications[index];
    var obj = {
      token : $scope.sessionCookie,
      id : seciliBildirim.id
    }
    var url = $scope.appName+'deleteCommentBildirim';
    var data = JSON.stringify(obj);
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
       if(response.data){
        $scope.commentNotificationsCount = $scope.commentNotificationsCount-1;
        $scope.getCommentNotifications($scope.sessionCookie,function(response){
            var notList = JSON.parse(response.data);
            for(i=0; i<notList.length; i++){
              notList[i].commentTime = new Date(notList[i].commentTime).toLocaleString('en-GB');
            }
            $scope.commentNotifications = notList;
        });
       }
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });  
}




$scope.getFriendsList = function(callback){
    var url = $scope.appName+'getFriendsList';
    // var userName = xx ihtiyaca binayen doldur. 
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.userUsername
    }
    var data = JSON.stringify(obj);
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

    $scope.obj = {};
   $http.post(url, data, config).then(function (response) {
       callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}


$scope.getRootDB = function(callback){
    var url = $scope.appName+'getRootDB';
    // var userName = xx dolacak. 
    var obj =$scope.sessionCookie;
    var data = JSON.stringify(obj);
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    callback(response);

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}



$scope.getFriendsCount = function(callback){
    var url = $scope.appName+'getFriendsCount';
    // var userName = xx dolacak. 
    var obj ={
      token : $scope.sessionCookie, 
      username : $scope.userUsername
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
    callback(response);

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}

$scope.checkNewNotification =function(){

  var sonsuzCagri = function(){
    $scope.getCommentNotificationsCount($scope.sessionCookie, function(response){
      var yeniCommentNotCount = JSON.parse(response.data);
      var eskiCommentNotCount = $scope.commentNotificationsCount
      if( eskiCommentNotCount<yeniCommentNotCount ){
        $scope.commentNotificationsCount = yeniCommentNotCount;
        $scope.yeniNotificationFlag = true;
      }
    });
    $scope.getFriendRequestsCount($scope.sessionCookie,function(response){
      var yeniFriendReqCount = JSON.parse(response.data);
      var eskiFriendReqCount = $scope.friendRequestsCount;
      if( eskiFriendReqCount<yeniFriendReqCount ){
        $scope.friendRequestsCount = yeniFriendReqCount;
        $scope.yeniNotificationFlag = true;
      }
    });

       if( 
        ( $scope.commentNotificationsCount == 0 || $scope.commentNotificationsCount == undefined ) &&
        ( $scope.friendRequestsCount == 0  || $scope.friendRequestsCount == undefined ) 
        )
            $scope.yeniNotificationFlag = false;

    $timeout(sonsuzCagri, 2000);
  }

  sonsuzCagri();
}


$scope.getAllNotifications = function(){
    $scope.getCommentNotifications($scope.sessionCookie,function(response){
      var notList = JSON.parse(response.data);
      for(i=0; i<notList.length; i++){
        notList[i].commentTime = new Date(notList[i].commentTime).toLocaleString('en-GB');
      }
      $scope.commentNotifications = notList;
    });

    $scope.getFriendRequests($scope.sessionCookie,function(response){
      var resp = JSON.parse(response.data);
      for(i=0; i<resp.length; i++){
        resp[i].sendTime = new Date(resp[i].sendTime).toLocaleString('en-GB'); 
      }
      $scope.arkadaslikIstekListe = resp;
    });          
}


$scope.firstFunctionExecute = function(){

$scope.loading();

     $scope.getUserGeneralInfo($scope.sessionCookie,function(response){
       $scope.userGeneralInfo =JSON.parse(response.data);
       $scope.headerUsername = $scope.userGeneralInfo.username;

       $scope.populateUserInfoOnPage();     


         $scope.downloadProfilePhoto($scope.userUsername,function(response){
            $scope.profilePictureUrl = response.data.replace("\"",""); 
            $scope.headerProfilePhoto = $scope.profilePictureUrl;
          });

          $scope.getFriendsCount(function(response){
            $scope.friendsCount = JSON.parse(response.data);
          });


          $scope.getFriendRequests($scope.sessionCookie,function(response){
            var resp = JSON.parse(response.data);
            for(i=0; i<resp.length; i++){
              resp[i].sendTime = new Date(resp[i].sendTime).toLocaleString('en-GB'); 
            }
            $scope.arkadaslikIstekListe = resp;

          });

          $scope.getFriendRequestsCount($scope.sessionCookie,function(response){
                $scope.getCommentNotificationsCount($scope.sessionCookie,function(response){
                  $scope.checkNewNotification();
                })
          });

          $scope.getCommentNotifications($scope.sessionCookie,function(response){
            var notList = JSON.parse(response.data);
            for(i=0; i<notList.length; i++){
              notList[i].commentTime = new Date(notList[i].commentTime).toLocaleString('en-GB');
            }
            $scope.commentNotifications = notList;
          });



          $scope.getRootDB(function(response){
            $scope.dbURL = JSON.parse(response.data);

              $scope.getGonderiList($scope.sessionCookie,$scope.userUsername,function(response){
                var res= JSON.parse(response.data);            
                for(i=0; i<res.length; i++){
                  res[i].kapakUrl = [];
                  res[i].kapakUrl = $scope.dbURL.mediapath + res[i].folderName + "/" + "kapak.jpg";
                }            
                $scope.gonderiList = res;
              });

              $scope.getPaylasimList($scope.sessionCookie,$scope.userUsername,function(response){
                var res= JSON.parse(response.data);            
                for(i=0; i<res.length; i++){
                  res[i].kapakUrl = [];
                  res[i].kapakUrl = $scope.dbURL.mediapath + res[i].folderName + "/" + "kapak.jpg";
                }            
                $scope.paylasimList = res;
              });              
          });


          $scope.getFriendsList(function(response){
              var tmpList = JSON.parse(response.data);
              for(i=0; i<tmpList.length; i++){
                tmpList[i].profilePhotoUrl = [];
                tmpList[i].areWeFriends = [];                
                   $scope.downloadFriendProfilePhoto(i,tmpList[i].username,function(response){ // index callback gelene kadar işlenip dğeiştiği için onu da parametre olarak gönderiyoruz.
                      var realIndex = response.index; 
                      tmpList[realIndex].profilePhotoUrl = response.data; 
                      $scope.checkAreWeFriends($scope.sessionCookie,tmpList[realIndex].username,function(response){
                            tmpList[realIndex].areWeFriends = response.data;

                         })
                    });                
              }
              $scope.friendsList = tmpList;

          });

          $scope.getSharingCount(function(response){
            $scope.sharingCount = JSON.parse(response.data);
          });

          $scope.getGonderiCount(function(response){
            $scope.gonderiCount = JSON.parse(response.data);
          });
       

     });    

      $scope.loaded();
}
// first function to execute


$scope.firstFunctionExecute();



function getSessionCookieValue() {
    name = $scope.cookieName;
   var nameEQ = name + "=";
   var pcToken = document.cookie.split(';')[0];
    var ca = pcToken.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
};

function getMobileToken() {
    name = $scope.mobileCookieName;
   var nameEQ = name + "=";
   var mobileToken = document.cookie.split(';')[1]; // bunu 2 token olduğu için fonk.'a ben ekledim.
    if(typeof mobileToken === "undefined"){
      return;
    }else{
      var ca = mobileToken.split(';');
      for(var i=0;i < ca.length;i++) {
          var c = ca[i];
          while (c.charAt(0)==' ') c = c.substring(1,c.length);
          if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
      }
      return null;
    }
};




$scope.mapGorunum = "TERRAIN";
$scope.gorunumButtonMetni = "Uydu görünümü göster";

$scope.changeGorunum = function(){
  if($scope.mapGorunum == "TERRAIN"){
  $scope.mapGorunum = "SATELLITE";
  $scope.gorunumButtonMetni = "Normal görünüm göster";
  }else{
        if($scope.mapGorunum == "SATELLITE")
        {
         $scope.mapGorunum = "TERRAIN"
         $scope.gorunumButtonMetni = "Uydu görünümü göster";
        }  
  }
}

$scope.getMapInfo = function(){

}


    $scope.obj = JSON.stringify(new google.maps.LatLng(41.027879,28.907525));





    $scope.geziStreetGoster = function(){
      $scope.streetGoster = true;
      $scope.geziDevamEt = true;
    }

    $scope.geziStreetGizle = function(){
      $scope.streetGoster = false;
      $scope.geziDevamEt = false;
      $scope.initialPosition = $scope.path[0];
      $scope.i = 0;
    }    

    $scope.geziBaslat =function(){
      
      $scope.geziDevamEt = true;
      $scope.geziStreetGoster();
      $scope.i = 0;
      var canliTakip = function(){
      $scope.obj = JSON.stringify(new google.maps.LatLng($scope.path[$scope.i][0],$scope.path[$scope.i][1]));
      $scope.initialPosition = $scope.path[$scope.i];
       if( $scope.i < $scope.path.length ){
              if($scope.geziDevamEt){
                  $timeout(canliTakip, $scope.geziHizi);
              }
              else{
                return;
              }
        }
        $scope.i++;
      }


      canliTakip();

    }

  

// medya ekleme ng map

$scope.medyaEkleErrorFlag = false;
$scope.medyaFlagError = null;
$scope.fileUploading = false;


      $scope.uzantiKontrol= function(uzanti){
        switch($scope.seciliMedyaTipi.kod){
          case 0 : // video
            if(uzanti == "mp4" || uzanti == "m4v"
             || uzanti == "avi" || uzanti == "mpg")
                return true;
              else
                return false;          
          break;
          case 1: // resim
            if(uzanti == "jpg" || uzanti == "jpeg" || uzanti == "png")
                return true;
              else
                return false;          
          break;
          case 2: // ses
            if(uzanti == "mp3")
                return true;
              else
                return false;          
          break; 
        }

      }

      $scope.uploadMedia = function() {


      var uzanti = "";


      if($scope.videoYukleFlag) {
        $scope.uploadme = $scope.uploadVideoFile;
        uzanti = document.getElementById("uploadGeziVideo").value.split('.').pop();;
      }
       
      if($scope.resimYukleFlag){
        $scope.uploadme = $scope.uploadResimFile;
        uzanti = document.getElementById("uploadGeziResim").value.split('.').pop();;
      }
      

      if($scope.sesYukleFlag){
        $scope.uploadme = $scope.uploadSesFile;
        uzanti = document.getElementById("uploadGeziSes").value.split('.').pop();;        
      }


      if($scope.uzantiKontrol(uzanti) == false){
        $scope.medyaEkleErrorFlag = true;
        $scope.medyaFlagError = "Sadece RESİM: 'jpg,jpeg,png' VİDEO :'mp4,m4v,avi,mpg', SES: 'mp3' uzantılı dosyaları yükleyebilirsiniz.";
        $timeout(function(){
          $scope.medyaEkleErrorFlag = false;
        },5000)            
        $scope.fileUploading = false;
        return;            
      }
      


      if($scope.pos == null || $scope.pos == null)
        {
          $scope.medyaEkleErrorFlag = true;
          $scope.medyaFlagError = "Lütfen konum seçiniz.";
          $timeout(function(){
            $scope.medyaEkleErrorFlag = false;
          },5000)
          return ;
        } 

      var fd = new FormData();
      var fileBlob = dataURItoBlob($scope.uploadme);
      fd.append('uzanti', uzanti);
      fd.append('file', fileBlob);
      fd.append('token',$scope.sessionCookie);
      fd.append('dosyaTipi',$scope.seciliMedyaTipi.kod);
      fd.append('not',$scope.medyaEkleNot);
      fd.append('kimGorebilir',$scope.kimGorebilirSecili.kod);
      fd.append('longitude',$scope.pos.lng());
      fd.append('latitude',$scope.pos.lat());
      fd.append('altitude',0);
      fd.append('tripId',$scope.tripDetay.tripId);
      var url = "uploadTripMedia";
      var data = fd;
      var config = {
            transformRequest: angular.identity,
            headers: {
              'Content-Type': undefined
            }
          };

       $scope.fileUploading = true;        
       $http.post(url, data, config).then(function (response) {
            var file = response; 
            $scope.fileUploading = false;  
            bootbox.alert("Dosya başarıyla yüklendi", function(){
                location.reload();
            });  
        }, function (response) {
            $scope.medyaEkleErrorFlag = true;
            $scope.medyaFlagError = response.data.message;
            $timeout(function(){
              $scope.medyaEkleErrorFlag = false;
            },5000)            
            $scope.fileUploading = false; 
        });  

      }


    $scope.onMedyaTipiSelect = function(){
      var aramaTip = $scope.seciliMedyaTipi.kod;

      if(aramaTip == 0){
        $scope.videoYukleFlag = true;
        $scope.resimYukleFlag = false;
        $scope.sesYukleFlag = false;
      }

      if(aramaTip == 1){
        $scope.videoYukleFlag = false;
        $scope.resimYukleFlag = true;
        $scope.sesYukleFlag = false;
      }

      if(aramaTip == 2){
        $scope.videoYukleFlag = false;
        $scope.resimYukleFlag = false;
        $scope.sesYukleFlag = true;
      }

    }

     $scope.pos = null;
    $scope.getCurrentLocation = function(){

         $scope.pos = this.getPosition();
         console.log($scope.pos.lat() +' '+ $scope.pos.lng());
    }

      var vm = this;
      vm.toggleBounce = function() {
        if (this.getAnimation() != null) {
          this.setAnimation(null);
        } else {
          this.setAnimation(google.maps.Animation.BOUNCE);
        }
      }

      NgMap.getMap({ id: 'medyaEkleMapId' }).then(function(map) {
            vm.medyaEkleMap = map;
            google.maps.event.addListenerOnce(vm.polylineMap, 'idle', function(){
                 vm.mapSettings = { zoom: 7};
            });    

        });  

        $('#medyaEkleModal').on('show.bs.modal', function(e) {
            $timeout(function() {
                google.maps.event.trigger(vm.medyaEkleMap, 'resize');             
            });
        });


// medya ekleme ng map

    $scope.addMarkerAndPath = function(event) {
      $scope.path.push([event.latLng.lat(), event.latLng.lng()]);
    };

    NgMap.getMap().then(function(map) {     
      $scope.showCustomMarker= function(evt) {
        map.customMarkers.foo.setVisible(true);
        map.customMarkers.foo.setPosition(this.getPosition());
      };
      $scope.closeCustomMarker= function(evt) {
        this.style.display = 'none';
      };
    });




        var vm = this;


        NgMap.getMap({ id: 'polylineMap' }).then(function(map) {
            vm.polylineMap = map;
            google.maps.event.addListenerOnce(vm.polylineMap, 'idle', function(){
                 vm.mapSettings = { zoom: 7};
            });    

        });        


        NgMap.getMap({ id: 'svMap' }).then(function(map) {
            vm.svMap = map;
            google.maps.event.addListenerOnce(vm.svMap, 'idle', function(){
                 vm.mapSettings = {  zoom: 7};
            });    

        });        

        $('#paylasimDetay').on('show.bs.modal', function(e) {
            $timeout(function() {
                google.maps.event.trigger(vm.polylineMap, 'resize');
                google.maps.event.trigger(vm.svMap, 'resize');                
            });
        });


} // end of controller


$(document).ready(function() {
  $('.progress .progress-bar').progressbar({display_text: 'fill', use_percentage: false, amount_format: function(p) {return 'Yükleniyor..';}});
});


$(document).ready(function(){
    
    var canvas = $("#chart").get(0);
    //var ctx = canvas.getContext('2d');

    // Global Options:
     //Chart.defaults.global.defaultFontColor = '#a0aeb6';
     //Chart.defaults.global.defaultFontSize = 12;

    // Data with datasets options
    var data = {
        labels: ["Eyecare", "Medical", "Dentist", "Rx"],
        datasets: [
          {
            backgroundColor:
              ['#313f4d','#54585a','#39af87','#379baf'],
            borderWidth: 0,
            data: [84.60, 84.60, 160, 110]
          }
        ]
    };

    var options = {
      layout: {
        padding: {
          left: 0,
          right: 0,
          top: 30,
          bottom: 0
        }
      },
      isFixedWidth:true,
      barWidth:1,
      legend: {
        display: false
      },
      tooltips: {
        mode: 'index',
        callbacks: {
          label: function(tooltipItem) {
            return "$" + Number(tooltipItem.yLabel).toFixed(2);
          },
          labelTextColor:function(tooltipItem, chart){
            return '#000000';
          }/*,
          titleTextColor:function(tooltipItem, chart){
            return '#000000';
          }*/
        },
        backgroundColor: '#ffffff',
        borderColor: '#a0aeb6',
        borderWidth: 1,
        bodySpacing: 0,
        cornerRadius: 2,
        displayColors: false,
        titleMarginTop: 20,
        titleMarginBottom: 0,
        titleFontSize: 0,
        titleFontColor: '#000000',
        caretSize: 4,
        xAlign: 'center',
        yAlign: 'bottom',
        xPadding: 12,
        yPadding: 12,
      },
      title: {
        display: false,
        text: 'Graph title',
        position: 'bottom'
      },
      scales: {
        xAxes: [
          {
            barThickness : 35,
            gridLines: {
              display: false
            },
            ticks: {
              fontSize: "12",
              fontColor: "#000000",
            }
          }
        ],
        yAxes: [
          {
            gridLines: {
              drawBorder: false,
              color: "#dce1e5",
              borderDash: [4, 3],
              borderDashOffset: 3
            },
            ticks: {
              beginAtZero:true,
              fontColor: "#a0aeb6",
              fontSize: "12",
              callback: function (value) {
                return "$"+Number(value)
              },
              stepSize: 25
            }
          }]
      },
      responsive: true
    };

    // Chart declaration:
    /*var barChart = new Chart(ctx, {
        type: 'bar',
        data: data,
        options: options
    });*/ 

});




/* modal body sarmal yapıya alma */
$('#paylasimModal').on('show', function () {
      $('.modal-body',this).css({width:'auto',height:'auto', 'max-height':'100%'});
});
