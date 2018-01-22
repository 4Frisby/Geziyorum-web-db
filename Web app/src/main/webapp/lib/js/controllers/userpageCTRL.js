var myApp = angular.module('geziyorumAppModel', ['ngMap','ngSanitize','ngAnimate','ngToast','angular-loading-bar']);
myApp.controller("userpageCTRL", mainIndex);

myApp.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = false;
  cfpLoadingBarProvider.includeBar = true;
  cfpLoadingBarProvider.loadingBarTemplate = '<div id="loading-bar"><div class="bar"><div class="peg"></div></div></div>';
  }])

function mainIndex($scope,$http,$q,$timeout,NgMap,ngToast,cfpLoadingBar){
$scope.local=true;
$scope.friendsCount=null;
$scope.dbURL=null;
$scope.appName = "";
if(!$scope.local){
	$scope.appName = "/Geziyorum/";
}

$scope.showArkadasButton = false;
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
// cookie işlemleri
if($scope.sessionCookie === null){
  $scope.sessionCookie = "dummydummy";
  $scope.showArkadasButton = false;
}


$scope.ownerGeneralInfo = null;
$scope.visitorGeneralInfo = null;

$scope.ownerUserUsername= null;
$scope.ownerUserName= null;
$scope.ownerUserSurname= null;
$scope.ownerUserPersonalInfo = null;
$scope.ownerUserEmail= null;
$scope.ownerUserWebsite= null;
$scope.ownerUserBornDate= null;
$scope.ownerUserNumber = null;
$scope.ownerProfilePictureUrl = null;


$scope.visitorUserUsername= null;
$scope.visitorUserName= null;
$scope.visitorUserSurname= null;
$scope.visitorUserPersonalInfo = null;
$scope.visitorUserEmail= null;
$scope.visitorUserWebsite= null;
$scope.visitorUserBornDate= null;
$scope.visitorUserNumber = null;
$scope.visitorProfilePictureUrl = null;

$scope.activeTab = 1;
$scope.showGonderiTab = true;
$scope.showPaylasimTab = false;
$scope.gonderiList = null;
$scope.arkadasButton = "Arkadaş olarak ekle";

$scope.disableArkadasButton = false;
$scope.areWeFriends = false;
$scope.showFriendRequests = false;


$scope.selectedMedyaDetay = null;
$scope.selectedMedyaDetayTip =  null;

$scope.geziHizi = 6000;

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


$scope.yeniNotificationFlag = false;

$scope.isPaylasimFlag = false;
$scope.tripPaylasimciUsername = null;
$scope.olusturulmaZamani = null;
$scope.paylasilmaZamani = null;

$scope.showProfileTabOne = true;
$scope.showProfileTabTwo = false;

$scope.aramaMetini = null;

// bildiri elemanları
$scope.friendsCount = 0;
$scope.arkadaslikIstekListe = null;
$scope.commentNotifications = [];
$scope.yeniNotificationFlag = false;
$scope.commentNotificationsCount = 0;
$scope.friendRequestsCount = 0;


$scope.ifLiked = false;

$scope.comNotArama = "";
$scope.friendReqArama = "";

$scope.headerUsername = null;
$scope.headerProfilePhoto = null;

$scope.showGonderiDuzenleCheck = false;
$scope.notDownloadMediaIdList = [];


$scope.initalizeVariables = function(){
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
  $scope.sessionCookie = "dummydummy";
  $scope.showArkadasButton = false;
}

// cookie işlemleri


$scope.ownerGeneralInfo = null;
$scope.visitorGeneralInfo = null;

$scope.ownerUserUsername= null;
$scope.ownerUserName= null;
$scope.ownerUserSurname= null;
$scope.ownerUserPersonalInfo = null;
$scope.ownerUserEmail= null;
$scope.ownerUserWebsite= null;
$scope.ownerUserBornDate= null;
$scope.ownerUserNumber = null;
$scope.ownerProfilePictureUrl = null;


$scope.visitorUserUsername= null;
$scope.visitorUserName= null;
$scope.visitorUserSurname= null;
$scope.visitorUserPersonalInfo = null;
$scope.visitorUserEmail= null;
$scope.visitorUserWebsite= null;
$scope.visitorUserBornDate= null;
$scope.visitorUserNumber = null;
$scope.visitorProfilePictureUrl = null;

$scope.activeTab = 1;
$scope.showGonderiTab = true;
$scope.showPaylasimTab = false;
$scope.friendsCount=null;
$scope.dbURL=null;
$scope.gonderiList = null;
$scope.arkadasButton = "Arkadaş olarak ekle";
$scope.showArkadasButton = false;
$scope.areWeFriends = false;
$scope.disableArkadasButton = false;
$scope.showFriendRequests = false;

$scope.selectedMedyaDetay = null;
$scope.selectedMedyaDetayTip =  null;

$scope.geziHizi = 6000;

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

$scope.yeniNotificationFlag = false;
$scope.isPaylasimFlag = false;
$scope.tripPaylasimciUsername = null;
$scope.olusturulmaZamani = null;
$scope.paylasilmaZamani = null;


$scope.showProfileTabOne = true;
$scope.showProfileTabTwo = false;

$scope.aramaMetini = null;


// bildiri elemanları
$scope.friendsCount = 0;
$scope.arkadaslikIstekListe = null;
$scope.commentNotifications = [];
$scope.yeniNotificationFlag = false;
$scope.commentNotificationsCount = 0;
$scope.friendRequestsCount = 0;


$scope.ifLiked = false;

$scope.comNotArama = "";
$scope.friendReqArama = "";

$scope.headerUsername = null;
$scope.headerProfilePhoto = null;

$scope.showGonderiDuzenleCheck = false;
$scope.notDownloadMediaIdList = [];


}

$scope.pageLoading = function(){
	$scope.progressBarVisible = true;
}

$scope.pageLoaded = function(){
	$scope.progressBarVisible = false;
}
	
$scope.getVisitorUserGeneralInfo = function(callback){
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
            $scope.error=JSON.parse(response.data);
            //console.log($scope.error.message);
            $scope.restCounterOnError();
        });
}	

$scope.populateVisitorUserInfoOnPage = function(){
  $scope.visitorUserUsername =        $scope.visitorGeneralInfo.username;
  $scope.visitorUserName =            $scope.visitorGeneralInfo.name;
  $scope.visitorUserSurname =         $scope.visitorGeneralInfo.surname;
  $scope.visitorUserPersonalInfo =    $scope.visitorGeneralInfo.personalInfo;
  $scope.visitorUserEmail =           $scope.visitorGeneralInfo.email;
  $scope.visitorUserWebsite =         $scope.visitorGeneralInfo.website;
  $scope.visitorUserBornDate =        $scope.visitorGeneralInfo.bornDate;
  $scope.visitorUserNumber =          $scope.visitorGeneralInfo.phone;
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
        //console.log($scope.error.message);
    }); 

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



// değişim olmuş mu kontrolü yapmak için başka değişkenlerde tutmak mantıklıdır.
$scope.populateOwnerUserInfoOnPage = function(){
      $scope.ownerUserUsername =        $scope.ownerGeneralInfo.username;
      $scope.ownerUserName =            $scope.ownerGeneralInfo.name;
      $scope.ownerUserSurname =         $scope.ownerGeneralInfo.surname;
      $scope.ownerUserPersonalInfo =    $scope.ownerGeneralInfo.personalInfo;
      $scope.ownerUserEmail =           $scope.ownerGeneralInfo.email;
      $scope.ownerUserWebsite =         $scope.ownerGeneralInfo.website;
      $scope.ownerUserBornDate =        $scope.ownerGeneralInfo.bornDate;
      $scope.ownerUserNumber =          $scope.ownerGeneralInfo.phone;
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
        //console.log($scope.error.message);
    }); 

}


$scope.getPaylasimList = function(oken,username,callback){
    var url = $scope.appName+'getPaylasimList';
    var obj = {
      token : $scope.sessionCookie,
      username : username
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
        //console.log($scope.error.message);
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
    $scope.profilePictureUrl = url;
    //document.getElementsByClassName("profilePicture").src = url;
    //window.location.href = 'data:application/octet-stream;base64,' + c;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        //console.log($scope.error.message);
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
    $scope.profilePictureUrl = url;
    //document.getElementsByClassName("profilePicture").src = url;
    //window.location.href = 'data:application/octet-stream;base64,' + c;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        //console.log($scope.error.message);
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
    $scope.profilePictureUrl = url;
    //document.getElementsByClassName("profilePicture").src = url;
    //window.location.href = 'data:application/octet-stream;base64,' + c;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        //console.log($scope.error.message);
    });   

}

$scope.commentTrip = function(){
    var url = $scope.appName+'commentTrip';
    var mentionedUsers = [];
    mentionedUsers = $scope.commentMentionedUsersList; // havada kaldı doldurulacak. 
    
    var data = {
     mentionedUsers : $scope.commentMentionedUsersList,
     token : $scope.sessionCookie,
     content : $scope.commentContent,
     time : new Date().getTime(),
     tripId : $scope.tripId // havada kaldı doldurulacak. 
    }
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    var url = response.data;
    $scope.profilePictureUrl = url;
    //document.getElementsByClassName("profilePicture").src = url;
    //window.location.href = 'data:application/octet-stream;base64,' + c;

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        //console.log($scope.error.message);
    });   

}


/*
*şikayet alanı başlangıç
*/
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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
    });   

}


$scope.gonderiSikayetEt = function(){

    var gonderiSikayetim = {
      sikayetEdilenGonderiId : $scope.seciliGonderi.personalSharingId,
      sikayetType : $scope.selectedSikayet.sikayetTypeCode,
      sikayetMetini : $scope.sikayetMetini,
    }

    var obj = {
      token : $scope.sessionCookie,
      gonderiSikayet : gonderiSikayetim,
      sikayetEdilenUserId : $scope.seciliGonderi.ownerId
    }

    var url = $scope.appName+'createGonderiSikayet';
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
        bootbox.alert("Şikayet oluşturmada hata oluştu");
    });   

}

$scope.profilSikayetEt = function(){

    var profilSikayetim = {
      sikayetEdilenUsername : $scope.ownerUserUsername,
      sikayetType : $scope.selectedSikayet.sikayetTypeCode,
      sikayetMetini : $scope.sikayetMetini
    }

    var obj = {
      token : $scope.sessionCookie,
      profilSikayet : profilSikayetim
    }

    var url = $scope.appName+'createProfilSikayet';
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
        bootbox.alert("Şikayet oluşturmada hata oluştu");
    }); 

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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
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
      $scope.likeTripProcessFlag = false;        
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        }); 

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
      ngToast.create({
        content: 'Gönderi beğenilmekten vazgeçildi',
        className: 'danger'
      });  
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
    $scope.ifLiked = obj;
    if(obj)
      $scope.begenButtonText = "Beğendin";
    else
      $scope.begenButtonText = "Beğen";
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        //console.log($scope.error.message);
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
        //console.log($scope.error.message);
    });   

}


$scope.checkBirilerindenBahsettiMi = function(yorum){

}

$scope.commentOnPersonalSharing = function(){
    $scope.checkBirilerindenBahsettiMi($scope.yorumIcerik);
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
     $scope.yorumIcerik = "";    
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
        $scope.yorumIcerik = "";      
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        }); 
    });   

}



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



/* Medya seçip indirme alanı.*/


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
        //console.log($scope.error.message);
    });   
}

$scope.tripDownloadList = [];
$scope.showPaylasimDetayPane = function(gonderiList,index){
    $scope.showGonderiSikayetPaneFlag = false;
  $scope.seciliGonderiIndex = index;
    $scope.notDownloadMediaIdList = [];
    $scope.showGonderiDuzenleCheck = false;
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




$scope.arkadasliktanCikar = function(){

bootbox.confirm({
    message: "Arkadaşlıktan çıkarmakta emin misin ?",
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
      var friend = $scope.ownerUserUsername
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
          //console.log($scope.error.message);
      });      
    }
});

}

$scope.arkadaslikTalebiYolla = function(){

bootbox.confirm({
    message: "Arkadaşlık talebi yollamak istediğinize emin misiniz ?",
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
      var url = $scope.appName+'friendRequest';
      var friend = $scope.ownerUserUsername
      var obj = {
        token : $scope.sessionCookie,
        username : $scope.ownerUserUsername
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
          //console.log($scope.error.message);
      });     
    }
});

}

$scope.arkadasEkleCikar = function(){
  if($scope.areWeFriends){
    $scope.arkadasliktanCikar();
  }else{
    $scope.arkadaslikTalebiYolla();
  }
}


$scope.getOwnerUserInfo = function(cookie,callback){  
    var obj ={
      token : cookie, 
      username : getUserNameFromURL()
    }   
   var data = JSON.stringify(obj);
   var url = $scope.appName+'wantToSee';
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
        bootbox.alert("Böyle bir kullanıcı mevcut değildir.");
        $timeout(ownerNotFound, 3000);
    });
}

  function ownerNotFound(){
    location.href = "/Geziyorum/home";
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



function getUserNameFromURL(){
  return location.href.split('@')[1];
}


$scope.getFriendsCount = function(callback){
    var url = $scope.appName+'getFriendsCount';
    // var userName = xx dolacak. 
    var obj ={
      token : $scope.sessionCookie, 
      username : $scope.ownerUserUsername
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
        console.log($scope.error.message);
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
        console.log($scope.error.message);
    }); 

}




$scope.getSharingCount = function(callback){
    var url = $scope.appName+'getSharingCountOfUser';
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.ownerUserUsername
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
        console.log($scope.error.message);
    });   

}

$scope.getGonderiCount = function(callback){
    var url = $scope.appName+'getGonderiCountOfUser';
    var obj = {
      token : $scope.sessionCookie,
      username : $scope.ownerUserUsername
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
        console.log($scope.error.message);
    });   

}

$scope.getGonderiList = function(token,username,callback){ // sadece listeyi getirir içi boştur bundan alınan bilgiyle üzerine tıklandığında modal dolar.
    var url = $scope.appName+'getPersonalSharingList';
    var obj = {
      token : token,
      username : username
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
        console.log($scope.error.message);
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
        console.log($scope.error.message);
    });   

}

$scope.getFriendsList = function(usernameInput,callback){
    var url = $scope.appName+'getFriendsList';
    // var userName = xx ihtiyaca binayen doldur. 
    var obj = {
      token : $scope.sessionCookie,
      username : usernameInput
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
        console.log($scope.error.message);
    });   

}

$scope.checkWaitingFriendRequest = function(cookie,otherUsername,callback){
    var url = $scope.appName+'checkFriendRequestExist';

    var obj = {
      token : $scope.sessionCookie,
      username : $scope.ownerUserUsername
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
        console.log($scope.error.message);
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



// bildirimler fonksiyonları başlangıç

$scope.showFriendRequestList = function(){
      $scope.showFriendRequests = true;
}

$scope.showNotificationList = function(){
      $scope.showFriendRequests = false;
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
        console.log($scope.error.message);
    }); 

}

$scope.getOwnerUserInfoForNotification = function(callback){
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
        console.log($scope.error.message);
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

// bildirimler fonksiyonları bitiş




$scope.firstFunctionExecute = function(){

  $scope.initalizeVariables();

  $scope.getVisitorUserGeneralInfo(function(response){
            $scope.visitorGeneralInfo=JSON.parse(response.data);
            $scope.populateVisitorUserInfoOnPage();      
  });

  $scope.getOwnerUserInfoForNotification(function(response){

      var usr = JSON.parse(response.data);
      $scope.userUsername = usr.username;
      $scope.headerUsername = $scope.userUsername;

         $scope.downloadProfilePhoto($scope.userUsername,function(response){
            $scope.profilePictureUrl = response.data.replace("\"",""); 
            $scope.headerProfilePhoto = $scope.profilePictureUrl;
          });


     // bildirim kontrol başlangıç
          $scope.getFriendsCount(function(response){
            $scope.friendsCount = JSON.parse(response.data);
          });


          $scope.getFriendRequests($scope.sessionCookie,function(response){
            $scope.arkadaslikIstekListe = JSON.parse(response.data);

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

     //bildirim kontrol bitiş
  });

$scope.ownerProfilePP = null;


  $scope.getOwnerUserInfo($scope.sessionCookie,function(response){
        $scope.ownerGeneralInfo=JSON.parse(response.data);
        $scope.populateOwnerUserInfoOnPage();
         $scope.downloadProfilePhoto($scope.ownerUserUsername,function(response){
            $scope.ownerProfilePP = response.data; 
          });

          $scope.getRootDB(function(response){
            $scope.dbURL = JSON.parse(response.data);
          });

          if($scope.ownerUserUsername == $scope.visitorUserUsername){
            $scope.showArkadasButton = false; 
          }else{
            if($scope.sessionCookie !="dummydummy"){
              $scope.checkAreWeFriends($scope.sessionCookie,$scope.ownerUserUsername,function(response){
                  $scope.areWeFriends = JSON.parse(response.data);
                  $scope.showArkadasButton = true;               
                  if( $scope.areWeFriends  == true){
                    $scope.arkadasButton = "Arkadaşsınız";
                  }
                  else{
                    $scope.checkWaitingFriendRequest($scope.sessionCookie,$scope.ownerUserUsername,function(response){
                      if(JSON.parse(response.data) == true){
                        $scope.arkadasButton = "Arkadaşlık talebi beklemede";
                        $scope.disableArkadasButton = true;
                      }
                      else{
                       $scope.arkadasButton = "Arkadaş olarak ekle";                  
                      }
                    })
                  }

              });
            }
          }


          $scope.getSharingCount(function(response){
            $scope.sharingCount = JSON.parse(response.data);
          });

          $scope.getGonderiCount(function(response){
            $scope.gonderiCount = JSON.parse(response.data);
          });

          $scope.getGonderiList($scope.sessionCookie,$scope.ownerUserUsername,function(response){
            var res= JSON.parse(response.data);            
            for(i=0; i<res.length; i++){
              res[i].kapakUrl = [];
              res[i].kapakUrl = $scope.dbURL.mediapath + res[i].folderName + "/" + "kapak.jpg";
            }            
            $scope.gonderiList = res;
          }); 

          $scope.getPaylasimList($scope.sessionCookie,$scope.ownerUserUsername,function(response){
            var res= JSON.parse(response.data);            
            for(i=0; i<res.length; i++){
              res[i].kapakUrl = [];
              res[i].kapakUrl = $scope.dbURL.mediapath + res[i].folderName + "/" + "kapak.jpg";
            }            
            $scope.paylasimList = res;
          });  

          $scope.getFriendsList($scope.ownerUserUsername,function(response){
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

  });

}

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



// Harita işlemleri başlangıç


$scope.getMapInfo = function(){

}


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


$scope.obj = JSON.stringify(new google.maps.LatLng(41.027879,28.907525));

    $scope.path = [
    [41.027879, 28.907525],
    [41.027879, 28.907525],
    [41.027540, 28.908417],
    [41.027166, 28.909120],
    [41.026456, 28.909442]
              ];

    $scope.initialPosition = $scope.path[0];



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

// Harita işlemleri bitiş

}