var myApp = angular.module('geziyorumAppModel', ['ngMap','ngSanitize','ngAnimate','ngToast','angular-loading-bar']);
myApp.controller("homepageCTRL", mainIndex);

myApp.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = false;
  cfpLoadingBarProvider.includeBar = true;
  cfpLoadingBarProvider.loadingBarTemplate = '<div id="loading-bar"><div class="bar"><div class="peg"></div></div></div>';
  }])


function mainIndex($scope,$http,$q,$timeout,NgMap,ngToast,cfpLoadingBar){


$scope.local=true;
$scope.appName="";
$scope.isim = null;


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
if($scope.sessionCookie === null)
  location.href ="/Geziyorum";


$scope.personInfo = null;
$scope.showCommentInputSection = true;
$scope.showCommentsCommentSection = false;
$scope.ifHaveFriendRequest = true;  // arkadaş isteği var mı varsa arkadaş isteklerin varı gösterecek flag
$scope.showFriendRequests = false;  // arkadaş isteği listesini gösterem mi flag

$scope.friendGonderiList = [];
$scope.aramaMetini = null;

// bildiri elemanları
$scope.friendsCount = 0;
$scope.arkadaslikIstekListe = null;
$scope.commentNotifications = [];
$scope.yeniNotificationFlag = false;
$scope.commentNotificationsCount = 0;
$scope.friendRequestsCount = 0;


$scope.geziHizi = 6000;

$scope.showHicArkadasGonderiYok = false;  

$scope.headerUsername = null;
$scope.headerProfilePhoto = null;

$scope.comNotArama = "";
$scope.friendReqArama = "";

$scope.showGonderiDuzenleCheck = false;
$scope.notDownloadMediaIdList = [];

$scope.yorumIcerikDetay = "";


$scope.dahaFazlaGoruntuleFlag = false;
$scope.mainFriendGonderiList = [];
$scope.gonderiIndex = 0;

if(!$scope.local){
    $scope.appName = "/Geziyorum/";
}



$scope.initializeVariables = function(){
$scope.local=true;
$scope.appName="";
$scope.isim = null;

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
if($scope.sessionCookie === null)
  location.href ="/Geziyorum";

$scope.personInfo = null;
$scope.showCommentInputSection = true;
$scope.showCommentsCommentSection = false;
$scope.ifHaveFriendRequest = true;  // arkadaş isteği var mı varsa arkadaş isteklerin varı gösterecek flag
$scope.showFriendRequests = false;  // arkadaş isteği listesini gösterem mi flag

$scope.friendGonderiList = [];

$scope.commentNotificationsCount = 0;

$scope.yeniNotificationFlag = false;


$scope.aramaMetini = null;


// bildiri elemanları
$scope.friendsCount = 0;
$scope.arkadaslikIstekListe = null;
$scope.commentNotifications = [];
$scope.yeniNotificationFlag = false;
$scope.commentNotificationsCount = 0;
$scope.friendRequestsCount = 0;



$scope.geziHizi = 6000;
$scope.showHicArkadasGonderiYok = false;  


$scope.headerUsername = null;
$scope.headerProfilePhoto = null;

$scope.showGonderiDuzenleCheck = false;
$scope.notDownloadMediaIdList = [];

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

$scope.showCommentInput = function(){
    if($scope.showCommentInputSection)
        $scope.showCommentInputSection = false;
    else
        $scope.showCommentInputSection = true;
}

$scope.showCommentsComment = function(){
   $scope.showCommentsCommentSection ? $scope.showCommentsCommentSection = false : $scope.showCommentsCommentSection = true; 
}

$scope.showFriendRequestList = function(){
      $scope.showFriendRequests = true;
}

$scope.showNotificationList = function(){
      $scope.showFriendRequests = false;
}





function getSessionCookieValue() {
    name = $scope.cookieName;
   var nameEQ = name + "=";
   var pcToken = document.cookie.split(':')[0];
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
      callback(index,response);
    }, function (index,response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });  
}


$scope.getPersonalSharingComments = function(token,psID,gonderiIndex,callback){

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
    callback(gonderiIndex,response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.begenenKullanicilar = [];
$scope.paylasanKullanicilar = [];

$scope.showBegenenler = function(psId){
    var url = $scope.appName+'getLikedUsersOfPs';
    var data = psId;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    $scope.begenenKullanicilar = JSON.parse(response.data);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.showPaylasanlar = function(psId){

    var url = $scope.appName+'getSharedUsersOfPs';
    var data = psId;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
    var resp = JSON.parse(response.data);
    for(i=0; i<resp.length; i++){
      resp[i].sharedTime = new Date(resp[i].sharedTime).toLocaleString('en-GB');
    }
    $scope.paylasanKullanicilar = resp;
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });

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


$scope.commentOnPersonalSharing = function(gonderiIndex,yorumIcerik){
    var url = $scope.appName+'commentTrip';
      var obj = {
        token : $scope.sessionCookie,
        personalSharingId : $scope.friendGonderiList[gonderiIndex].gonderiId,
        content : yorumIcerik,
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
     yorumIcerik = "";
     $scope.getPersonalSharingComments($scope.sessionCookie,$scope.friendGonderiList[gonderiIndex].gonderiId,gonderiIndex,function(gonderiIndex,response){
         var comments = JSON.parse(response.data);
         for(i=0; i<comments.length; i++ ){
          comments[i].sendTime = new Date(comments[i].sendTime).toLocaleString('en-GB');
        }
        $scope.friendGonderiList[gonderiIndex].yorumlar = comments;
        $scope.gonderiPaylasimEsitle($scope.friendGonderiList[gonderiIndex]);              
     });
      ngToast.create({
        content: 'Yorumunuz kaydedildi.',
        className: 'info'
      });
    }, function (response) {
        yorumIcerik = "";
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });         
    });   

}

$scope.commentOnPersonalSharingDetay = function(yorumIcerik){
    var url = $scope.appName+'commentTrip';
      var obj = {
        token : $scope.sessionCookie,
        personalSharingId : $scope.seciliGonderi.gonderiId,
        content : yorumIcerik,
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
     $scope.yorumIcerikDetay = "";
     $scope.getPersonalSharingComments($scope.sessionCookie,$scope.seciliGonderi.gonderiId,0,function(gonderiIndex,response){
         var comments = JSON.parse(response.data);
         for(i=0; i<comments.length; i++ ){
          comments[i].sendTime = new Date(comments[i].sendTime).toLocaleString('en-GB');
        }
        $scope.seciliGonderi.yorumlar = comments;
        $scope.gonderiPaylasimEsitle($scope.seciliGonderi);      
     });        
      ngToast.create({
        content: 'Yorumunuz kaydedildi.',
        className: 'info'
      });   
    }, function (response) {
        $scope.yorumIcerikDetay = "";
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });   
    });   

}




$scope.shareTrip = function(gonderiIndex){
      var url = $scope.appName+'shareTrip';
      var obj = {
        token : $scope.sessionCookie,
        id : $scope.friendGonderiList[gonderiIndex].gonderiId
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
      $scope.friendGonderiList[gonderiIndex].begenPaylasCount.shareCount = $scope.friendGonderiList[gonderiIndex].begenPaylasCount.shareCount + 1; 
      ngToast.create({
        content: 'Gönderi paylaşıldı.',
        className: 'success'
      });   
      $scope.gonderiPaylasimEsitle($scope.friendGonderiList[gonderiIndex]);            
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });
    }); 
}

$scope.shareTripDetay = function(){
      var url = $scope.appName+'shareTrip';
      var obj = {
        token : $scope.sessionCookie,
        id : $scope.seciliGonderi.gonderiId
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
      $scope.seciliGonderi.begenPaylasCount.shareCount = $scope.seciliGonderi.begenPaylasCount.shareCount + 1; 
      ngToast.create({
        content: 'Gönderi paylaşıldı.',
        className: 'success'
      });  
      $scope.gonderiPaylasimEsitle($scope.seciliGonderi);        
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });
    }); 
}



$scope.getPersonalSharingDetay = function(token,psID,index,callback){
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
    callback(index,response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    });   

}

$scope.likeOrUnlikeTrip = function(likeOrNotLike,gonderiIndex){
  if(likeOrNotLike==1){
      $scope.likeTrip($scope.sessionCookie,$scope.friendGonderiList[gonderiIndex].gonderiId,function(response){
      $scope.friendGonderiList[gonderiIndex].ifLiked  = true;
      $scope.friendGonderiList[gonderiIndex].begenPaylasCount.likeCount = $scope.friendGonderiList[gonderiIndex].begenPaylasCount.likeCount + 1;
      $scope.gonderiPaylasimEsitle($scope.friendGonderiList[gonderiIndex]);
    });
  }
  if(likeOrNotLike==2){
      $scope.unlikeTrip($scope.sessionCookie,$scope.friendGonderiList[gonderiIndex].gonderiId,function(response){
      $scope.friendGonderiList[gonderiIndex].ifLiked  = false;
      $scope.friendGonderiList[gonderiIndex].begenPaylasCount.likeCount = $scope.friendGonderiList[gonderiIndex].begenPaylasCount.likeCount - 1;      
      $scope.gonderiPaylasimEsitle($scope.friendGonderiList[gonderiIndex]);      
    });
  }
}


$scope.likeOrUnlikeTripDetay = function(likeOrNotLike){
  if(likeOrNotLike==1){
      $scope.likeTrip($scope.sessionCookie,$scope.seciliGonderi.gonderiId,function(response){
      $scope.seciliGonderi.ifLiked  = true;
      $scope.seciliGonderi.begenPaylasCount.likeCount = $scope.seciliGonderi.begenPaylasCount.likeCount + 1;
      $scope.gonderiPaylasimEsitle($scope.seciliGonderi);       
   });
  }
  if(likeOrNotLike==2){
      $scope.unlikeTrip($scope.sessionCookie,$scope.seciliGonderi.gonderiId,function(response){
      $scope.seciliGonderi.ifLiked  = false;
      $scope.seciliGonderi.begenPaylasCount.likeCount = $scope.seciliGonderi.begenPaylasCount.likeCount - 1;      
      $scope.gonderiPaylasimEsitle($scope.seciliGonderi);       
    });
  }
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
    //bootbox.alert("Gönderi beğenildi");
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
ngToast.create(
  {
  content: 'Gönderi beğenilmekten vazgeçildi.',
  className: 'danger'
  }
  );
    $scope.unlikeTripProcessFlag = false;
    callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        $scope.unlikeTripProcessFlag = false;        
        ngToast.create({
          content: $scope.error.message,
          className: 'danger'
        });
    });   
}


$scope.checkIfILikeTrip = function(token,index,personalSharingId,callback){
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
    callback(index,obj);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 
}


$scope.getFriendGonderiList = function(token,callback){
    var url = $scope.appName+'getFriendPaylasimList';
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

$scope.checkIfSameLongLat = function(tripMedias){
  for(i=0; i<tripMedias.length; i++){
    for(j=i+1; j<tripMedias.length; j++){
      if(tripMedias[i].latitude == tripMedias[j].latitude && tripMedias[i].longitude == tripMedias[j].longitude)
        tripMedias[i].latitude = parseFloat(tripMedias[i].latitude.toFixed(5)) + 0.00004;
        tripMedias[i].longitude = parseFloat(tripMedias[i].longitude.toFixed(5)) + 0.00008;
    }
  }
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
        bootbox.alert($scope.error.message);
    });   
}

$scope.tripDownloadList = [];


$scope.gonderiPaylasimEsitle = function(seciliGonderi){
  for(i=0; i<$scope.friendGonderiList.length; i++){
    if(seciliGonderi.gonderiId == $scope.friendGonderiList[i].gonderiId)
    {
     $scope.friendGonderiList[i].begenPaylasCount = seciliGonderi.begenPaylasCount; 
     $scope.friendGonderiList[i].yorumlar = seciliGonderi.yorumlar;
     $scope.friendGonderiList[i].ifLiked = seciliGonderi.ifLiked;     
    }
  }
}


$scope.showPaylasimDetayPane = function(gonderiIndex){
  $scope.seciliGonderiIndex = gonderiIndex;
  $scope.notDownloadMediaIdList = [];
  $scope.showGonderiDuzenleCheck = false;
  $scope.showGonderiSikayetPaneFlag = false;  
  $scope.yorumIcerikDetay = "";  
  $scope.seciliGonderi = $scope.friendGonderiList[gonderiIndex];
  var gonderi = $scope.friendGonderiList[gonderiIndex];


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


     })  // zaman mekan gelir.
          

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



// bildirimler fonksiyonları başlangıç

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
        bootbox.alert($scope.error.message);
    }); 

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

    var gonderiSikayetim = {
      sikayetEdilenGonderiId : $scope.seciliGonderi.gonderiId,
      sikayetType : $scope.selectedSikayet.sikayetTypeCode,
      sikayetMetini : $scope.sikayetMetini,
    }

    var obj = {
      token : $scope.sessionCookie,
      gonderiSikayet : gonderiSikayetim,
      sikayetEdilenUserId : $scope.seciliGonderi.olusturanUserId
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

$scope.showYorumSikayet = function(yorumIndex){

  $scope.sikayetMetini = null;
  $scope.seciliYorum = $scope.seciliGonderi.yorumlar[yorumIndex];
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
*şikayet alanı bitiş
*/


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


$scope.checkSessionAlive = function(callback){
    var data = $scope.sessionCookie;
    var url = $scope.appName+'checkSessionAlive';
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
            $timeout(function(){
              cikisYap();
            },4000);
        });    
}


$scope.dahaFazlaGoruntule = function(){

  cfpLoadingBar.start();
  var randomnumber = Math.floor(Math.random() * (2500 - 750 + 1)) + 750;  
  $timeout(function(){
  if($scope.mainFriendGonderiList.length > $scope.friendGonderiList.length){
    var kalanEleman = $scope.mainFriendGonderiList.length - $scope.friendGonderiList.length;
    if(kalanEleman < 5){
        for(i=0; i<kalanEleman; i++){
          $scope.friendGonderiList.push($scope.mainFriendGonderiList[$scope.gonderiIndex]);
          $scope.gonderiIndex = $scope.gonderiIndex + 1; 
        }
      $scope.dahaFazlaGoruntuleFlag = false;  
    }else{
        for(i=0; i<5; i++){
         $scope.friendGonderiList.push($scope.mainFriendGonderiList[$scope.gonderiIndex]); 
         $scope.gonderiIndex = $scope.gonderiIndex + 1;
        }
    }
  }

  cfpLoadingBar.complete(); 
  },randomnumber);

}


$scope.mdbCount=0;
$scope.firstFunctionToExecute = function(){

  $scope.initializeVariables();


    $scope.checkSessionAlive(function(response){

    $scope.getRootDB(function(response)
    {
      $scope.dbURL = JSON.parse(response.data);
        $scope.getFriendGonderiList($scope.sessionCookie,function(response)
        {
          $scope.dahaFazlaGoruntuleFlag = false;
          $scope.mainFriendGonderiList = [];
          $scope.gonderiIndex = 0; 
          $scope.mdbCount=0;  
                
          var res= JSON.parse(response.data);     
          if(res.length == 0)
            $scope.showHicArkadasGonderiYok = true;       
          for(i=0; i<res.length; i++)
          {
            res[i].kapakUrl = [];
            res[i].kapakUrl = $scope.dbURL.mediapath + res[i].folderName + "/" + "kapak.jpg";
            res[i].sharedTime = new Date(res[i].sharedTime).toLocaleString('en-GB');
            res[i].startTime = new Date(res[i].startTime).toLocaleString('en-GB');
            res[i].endTime = new Date(res[i].endTime).toLocaleString('en-GB');
            if(res[i].olusturanUsername == res[i].paylasanUsername)
            res[i].showOlusturan = false;
            else
            res[i].showOlusturan = true;  
          }            
          $scope.mainFriendGonderiList = res;


            for(i=0; i<$scope.mainFriendGonderiList.length; i++)
            {
              $scope.getPersonalSharingComments
              ( $scope.sessionCookie,$scope.mainFriendGonderiList[i].gonderiId,i,function(gonderiIndex,response)
                {
                   var comments = JSON.parse(response.data);
                   for(i=0; i<comments.length; i++ ){
                    comments[i].sendTime = new Date(comments[i].sendTime).toLocaleString('en-GB');
                  }                  
                $scope.mainFriendGonderiList[gonderiIndex].yorumlar = comments;
                checkIfAjaxEnd(gonderiIndex);
                }
              )

             $scope.getPersonalSharingDetay($scope.sessionCookie,$scope.mainFriendGonderiList[i].gonderiId,i,function(index,response){
              var begenPaylasCount = JSON.parse(response.data);
              if(begenPaylasCount.likeCount == 0)
                begenPaylasCount.likeCount = null;

              if(begenPaylasCount.shareCount == 0)
                begenPaylasCount.shareCount = null;

              $scope.mainFriendGonderiList[index].begenPaylasCount = begenPaylasCount;
              checkIfAjaxEnd(index);
             })  // beğen paylaş count gelir.


             $scope.checkIfILikeTrip($scope.sessionCookie,i,$scope.mainFriendGonderiList[i].gonderiId,function(index,response){
              $scope.mainFriendGonderiList[index].ifLiked = response;
              checkIfAjaxEnd(index);
             });

              $scope.downloadFriendProfilePhoto(i,$scope.mainFriendGonderiList[i].paylasanUsername,function(index,response){
                $scope.mainFriendGonderiList[index].paylasanUserPP = response.data;
                checkIfAjaxEnd(index);
              })

            }

        
        function checkIfAjaxEnd(index){
          if(index==$scope.mainFriendGonderiList.length-1)
            $scope.mdbCount = $scope.mdbCount + 1;

            if($scope.mdbCount == 4)
              populateFriendGonderiList();
        }

        function populateFriendGonderiList(){
          if($scope.mainFriendGonderiList.length>5){
            for(i=0; i<5; i++){
              $scope.friendGonderiList.push($scope.mainFriendGonderiList[i]);
              $scope.gonderiIndex = $scope.gonderiIndex + 1;
            }
            $scope.dahaFazlaGoruntuleFlag = true;
            
          }else{
            for(i=0; i<$scope.mainFriendGonderiList.length; i++){
              $scope.friendGonderiList.push($scope.mainFriendGonderiList[i]);
              $scope.gonderiIndex = $scope.gonderiIndex + 1;              
            }
            $scope.dahaFazlaGoruntuleFlag = false;            
          }          
        }



        });            
    });

  $scope.getOwnerUserInfo(function(response){

      var usr = JSON.parse(response.data);
      $scope.userUsername = usr.username;
      $scope.headerUsername = $scope.userUsername;
         $scope.downloadProfilePhoto($scope.userUsername,function(response){
            $scope.headerProfilePhoto = response.data
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

     //bildirim kontrol bitiş
      });  


    });


}

$scope.firstFunctionToExecute();







}






