var myApp = angular.module('geziyorumAppModel',['ngMap','angular-loading-bar']);
myApp.controller("adminpanelCTRL", mainIndex);

myApp.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = false;
  cfpLoadingBarProvider.includeBar = true;
  cfpLoadingBarProvider.loadingBarTemplate = '<div id="loading-bar"><div class="bar"><div class="peg"></div></div></div>';
  }])

function mainIndex($scope,$http,$q,$timeout,NgMap,cfpLoadingBar){




    $scope.aranacakKullaniciAdi = "";
    $scope.userProfilePhoto = null;
    $scope.user = null;
    $scope.appName="";
    $scope.cookieName = "access_token";
    $scope.mobileCookieName = "application";
    $scope.sessionCookie = getSessionCookieValue();  
    $scope.local = true;  
    if(!$scope.local){
        $scope.appName = "/Geziyorum/";
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




    $scope.admin = null;

    $scope.getAdminIfo = function(){
      var url = $scope.appName+'getAdminInfo';
      var data = $scope.sessionCookie;
      var config = {
        transformRequest: angular.identity,
        transformResponse: angular.identity,
        headers : {
            'Content-Type': 'application/json'
        }
      }

     $http.post(url, data, config).then(function (response) {
          $scope.admin = JSON.parse(response.data);

      }, function (response) {
          $scope.error=JSON.parse(response.data);
      }); 

    }



  $scope.seciliYorumSikayet = null;
  $scope.seciliGonderiSikayet = null;
  $scope.seciliProfilSikayet = null;

  $scope.profilSikayetDegerlendirmeMetini = null;
  $scope.yorumSikayetDegerlendirmeMetini = null;
  $scope.gonderiSikayetDegerlendirmeMetini = null;


	$scope.tabloVerisi = [];
	$scope.tabloVerisiDetayi = [];
	$scope.aranacakKelimeYorumSikayet = null;
	$scope.aranacakKelimeGonderiSikayet = null;
	$scope.aranacakKelimeProfilSikayet = null;

	$scope.activeTab = 1;
    $scope.currentPage = 1;
    $scope.aranacakKelime = "";
    $scope.aranacakKelimeAyrinti = "";

    // sikayet yorum arama check box item'ları

    $scope.yorumSikayetTabloVerisi = [];
    $scope.yorumSikayetAranacakKelime = null;


	$scope.yIdCheck;
	$scope.ySikayetEdilenYorumIdCheck;
	$scope.ySikayetciUserCheck;
	$scope.ySikayetEdilenUserCheck;
	$scope.yYorumIcerikCheck;
	$scope.ySikayetNedeniCheckk;
	$scope.ySikayetMetiniCheck;
    $scope.ySikayetZamaniCheck;
	$scope.ydegerlendirildi;

    $scope.globalYorumSikayetFilter = [
        "sikayetId",
        "sikayetEdilenYorumId",
        "sikayetciUsername",
        "sikayetEdilenUsername",
        "yorumIcerik",
        "sikayetNedeni",
        "sikayetMetini",
        "sikayetZamani",
        "degerlendirildi"
    ];

    $scope.yorumSikayetSutunFiltresi = function(){

        var searchFilter = [];
        
        $scope.yIdCheck == true ? searchFilter.push("sikayetId") : delete searchFilter.indexOf("sikayetId");
        $scope.ySikayetEdilenYorumIdCheck == true ? searchFilter.push("sikayetEdilenYorumId") : delete searchFilter.indexOf("sikayetEdilenYorumId");
        $scope.ySikayetciUserCheck == true ? searchFilter.push("sikayetciUsername") : delete searchFilter.indexOf("sikayetciUsername");
        $scope.ySikayetEdilenUserCheck == true ? searchFilter.push("sikayetEdilenUsername") : delete searchFilter.indexOf("sikayetEdilenUsername");
        $scope.yYorumIcerikCheck == true ? searchFilter.push("yorumIcerik") : delete searchFilter.indexOf("yorumIcerik");
        $scope.ySikayetNedeniCheckk == true ? searchFilter.push("sikayetNedeni") : delete searchFilter.indexOf("sikayetNedeni");
        $scope.ySikayetMetiniCheck == true ? searchFilter.push("sikayetMetini") : delete searchFilter.indexOf("sikayetMetini");
        $scope.ySikayetZamaniCheck == true ? searchFilter.push("sikayetZamani") : delete searchFilter.indexOf("sikayetZamani");
        $scope.ydegerlendirildi == true ? searchFilter.push("degerlendirildi") : delete searchFilter.indexOf("degerlendirildi");

        $scope.globalYorumSikayetFilter = searchFilter;
    }


    $scope.yorumFiltreHepsiTrue =  function(){
		$scope.yIdCheck = true;
		$scope.ySikayetEdilenYorumIdCheck = true;
		$scope.ySikayetciUserCheck = true;
		$scope.ySikayetEdilenUserCheck = true;
		$scope.yYorumIcerikCheck = true;
		$scope.ySikayetNedeniCheckk = true;
		$scope.ySikayetMetiniCheck = true;
    $scope.ySikayetZamaniCheck = true;
    $scope.ydegerlendirildi = true;        
    };

    $scope.yorumFiltreHepsiFalse = function(){
		$scope.yIdCheck = false;
		$scope.ySikayetEdilenYorumIdCheck = false;
		$scope.ySikayetciUserCheck = false;
		$scope.ySikayetEdilenUserCheck = false;
		$scope.yYorumIcerikCheck = false;
		$scope.ySikayetNedeniCheckk = false;
		$scope.ySikayetMetiniCheck = false;
    $scope.ySikayetZamaniCheck = false;
    $scope.ydegerlendirildi = false;        
    };        
 
   	$scope.yorumFiltreHepsiTrue();


	// şikayet gönderi arama check box item'ları

    $scope.gonderiSikayetTabloVerisi = [];
    $scope.gonderiSikayetAranacakKelime = null;

	$scope.gIdCheck;
	$scope.gSikayetEdilenGonderiIdCheck;
	$scope.gSikayetciUserCheck;
	$scope.gSikayetEdilenUserCheck;
	$scope.gSikayetNedeniCheck;
	$scope.gSikayetMetiniCheck;
    $scope.gSikayetZamaniCheck;
    $scope.gSikayetdegerlendirildiCheck;


    $scope.globalGonderiSikayetFilter = [
        "id",
        "sikayetEdilenGonderiId",
        "sikayetciUsername",
        "sikayetEdilenUsername",
        "sikayetNedeni",
        "sikayetMetini",
        "sikayetZamani",
        "degerlendirildi"
    ];    

    $scope.gonderiSikayetSutunFiltresi = function(){

        var searchFilter = [];
        
        $scope.gIdCheck == true ? searchFilter.push("id") : delete searchFilter.indexOf("id");
        $scope.gSikayetEdilenGonderiIdCheck == true ? searchFilter.push("sikayetEdilenGonderiId") : delete searchFilter.indexOf("sikayetEdilenGonderiId");
        $scope.gSikayetciUserCheck == true ? searchFilter.push("sikayetciUsername") : delete searchFilter.indexOf("sikayetciUsername");
        $scope.gSikayetEdilenUserCheck == true ? searchFilter.push("sikayetEdilenUsername") : delete searchFilter.indexOf("sikayetEdilenUsername");
        $scope.gSikayetNedeniCheck == true ? searchFilter.push("sikayetNedeni") : delete searchFilter.indexOf("sikayetNedeni");
        $scope.gSikayetMetiniCheck == true ? searchFilter.push("sikayetMetini") : delete searchFilter.indexOf("sikayetMetini");
        $scope.gSikayetZamaniCheck == true ? searchFilter.push("sikayetZamani") : delete searchFilter.indexOf("sikayetZamani");
        $scope.gSikayetdegerlendirildiCheck == true ? searchFilter.push("degerlendirildi") : delete searchFilter.indexOf("degerlendirildi");

        $scope.globalGonderiSikayetFilter = searchFilter;
    }


    $scope.gonderiFiltreHepsiTrue = function(){
		$scope.gIdCheck = true;
		$scope.gSikayetEdilenGonderiIdCheck = true;
		$scope.gSikayetciUserCheck = true;
		$scope.gSikayetEdilenUserCheck = true;
		$scope.gSikayetNedeniCheck = true;
		$scope.gSikayetMetiniCheck = true;
    $scope.gSikayetZamaniCheck = true;  
    $scope.gSikayetdegerlendirildiCheck = true;

    };

    $scope.gonderiFiltreHepsiFalse = function(){
		$scope.gIdCheck = false;
		$scope.gSikayetEdilenGonderiIdCheck = false;
		$scope.gSikayetciUserCheck = false;
		$scope.gSikayetEdilenUserCheck = false;
		$scope.gSikayetNedeniCheck = false;
		$scope.gSikayetMetiniCheck = false;
        $scope.gSikayetZamaniCheck = false;  
        $scope.gSikayetdegerlendirildiCheck = false;        
    };  

    $scope.gonderiFiltreHepsiTrue();





	// şikayet profil arama check box item'ları
    $scope.globalProfilSikayetFilter = [
        "sikayetId",
        "sikayetciUsername",
        "sikayetEdilenUsername",
        "sikayetNedeni",
        "sikayetMetini",
        "sikayetZamani",
        "degerlendirildi"
    ];   

    $scope.profilSikayetTabloVerisi = [];
    $scope.profilSikayetAranacakKelime = null;

	$scope.pIdCheck;
	$scope.pSikayetEdilenKullaniciId;
	$scope.pSikayetciUserCheck;
	$scope.pSikayetEdilenUserCheck;
	$scope.pSikayetNedeniCheckk;
	$scope.pSikayetMetiniCheck;
  $scope.pSikayetZamani;
	$scope.pdegerlendirildi;


    $scope.profilSikayetSutunFiltresi = function(){

        var searchFilter = [];
        
        $scope.pIdCheck == true ? searchFilter.push("sikayetId") : delete searchFilter.indexOf("sikayetId");
        $scope.pSikayetEdilenKullaniciId == true ? searchFilter.push("sikayetEdilenKullaniciId") : delete searchFilter.indexOf("sikayetEdilenKullaniciId");
        $scope.pSikayetciUserCheck == true ? searchFilter.push("sikayetciUsername") : delete searchFilter.indexOf("sikayetciUsername");
        $scope.pSikayetEdilenUserCheck == true ? searchFilter.push("sikayetEdilenUsername") : delete searchFilter.indexOf("sikayetEdilenUsername");
        $scope.pSikayetNedeniCheckk == true ? searchFilter.push("sikayetNedeni") : delete searchFilter.indexOf("sikayetNedeni");
        $scope.pSikayetMetiniCheck == true ? searchFilter.push("sikayetMetini") : delete searchFilter.indexOf("sikayetMetini");
        $scope.pSikayetZamani == true ? searchFilter.push("sikayetZamani") : delete searchFilter.indexOf("sikayetZamani");
        $scope.pdegerlendirildi == true ? searchFilter.push("degerlendirildi") : delete searchFilter.indexOf("degerlendirildi");

        $scope.globalProfilSikayetFilter = searchFilter;
    }	


    $scope.profilFiltreHepsiTrue = function(){
		$scope.pIdCheck = true;
		$scope.pSikayetEdilenKullaniciId = true;
		$scope.pSikayetciUserCheck = true;
		$scope.pSikayetEdilenUserCheck = true;
		$scope.pSikayetNedeniCheckk = true;
		$scope.pSikayetMetiniCheck = true;
        $scope.pSikayetZamani = true;
        $scope.pdegerlendirildi = true;        
    };

    $scope.profilFiltreHepsiFalse = function(){
		$scope.pIdCheck = false;
		$scope.pSikayetEdilenKullaniciId = false;
		$scope.pSikayetciUserCheck = false;
		$scope.pSikayetEdilenUserCheck = false;
		$scope.pSikayetNedeniCheckk = false;
		$scope.pSikayetMetiniCheck = false;
        $scope.pSikayetZamani = false;
        $scope.pdegerlendirildi = false;  
    }; 

    $scope.profilFiltreHepsiTrue();


    $scope.setActiveTab = function(num){
    	$scope.activeTab = num;
    }


$scope.getProfilSikayetList = function(){
    $scope.profilSikayetAranacakKelime = " ";
    var url = $scope.appName+'getProfilSikayetleri';
    var data = $scope.sessionCookie;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        var obj = JSON.parse(response.data);
        var sikayetList = [];
        for(i=0; i<obj.length; i++){
            sikayetList[i] = obj[i].profilSikayet;
            sikayetList[i].sikayetNedeni = obj[i].tanim;
            sikayetList[i].sikayetZamani = new Date(obj[i].profilSikayet.sikayetZamani).toLocaleString('en-GB');
            sikayetList[i].degerlendirildi = sikayetList[i].degerlendirildi == 0 ? "AÇIK" : "KAPALI"; 
        }
        $scope.profilSikayetTabloVerisi = sikayetList;
        $scope.profilSikayetAranacakKelime = "";
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Şikayet oluşturmada hata oluştu");
    }); 

}

$scope.profilSikayetAciklama = "";

$scope.profilSikayetGizle = function(){
    var obj = {
      username : $scope.seciliProfilSikayet.sikayetEdilenUsername,
      profilSikayetId : $scope.seciliProfilSikayet.sikayetId, 
      aciklama : $scope.profilSikayetAciklama,
      kararVerenAdmin : $scope.admin.username,
      sikayetNedeni : $scope.seciliProfilSikayet.sikayetNedeni
    }

    var url = $scope.appName+'profilSikayetGizle';
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
        bootbox.alert("Değerlendirme kaydedildi.");
          $timeout( function(){
          location.reload();
          }, 3000 )        
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata!");
    });  
}


$scope.profilSikayetGizleme = function(){
    var obj = {
      username : $scope.seciliProfilSikayet.sikayetEdilenUsername,
      profilSikayetId : $scope.seciliProfilSikayet.sikayetId, 
      aciklama : $scope.profilSikayetAciklama,
      kararVerenAdmin : $scope.admin.username,
      sikayetNedeni : $scope.seciliProfilSikayet.sikayetNedeni
    }

    var url = $scope.appName+'profilSikayetGizleme';
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
        bootbox.alert("Değerlendirme kaydedildi.");
          $timeout( function(){
          location.reload();
          }, 3000 )        
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata!");
    }); 

}
$scope.seciliDegerlendirme = null;

$scope.degerlendirmeDetay = function(veri){
  if(veri.sonucDurum == 0)
    veri.sonucDurum = "GİZLENMEDİ";
  else
    veri.sonucDurum = "GİZLENDİ";

  veri.degerlendirilmeZamani = new Date(veri.degerlendirilmeZamani).toLocaleString('en-GB');

  $scope.seciliDegerlendirme = veri;
}

$scope.getGonderiSikayetList = function(){
    var url = $scope.appName+'getGonderiSikayetleri';
    $scope.gonderiSikayetAranacakKelime = null;
    var data = $scope.sessionCookie;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        var obj = JSON.parse(response.data);
        var sikayetList = [];
        for(i=0; i<obj.length; i++){
            sikayetList[i] = obj[i].gonderiSikayet;
            sikayetList[i].sikayetNedeni = obj[i].tanim;
            sikayetList[i].sikayetZamani = new Date(obj[i].gonderiSikayet.sikayetZamani).toLocaleString('en-GB');
            sikayetList[i].degerlendirildi = sikayetList[i].degerlendirildi == 0 ? "AÇIK" : "KAPALI"; 
        }
        
    $scope.gonderiSikayetTabloVerisi = sikayetList;
    $scope.gonderiSikayetAranacakKelime = "";
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Şikayet oluşturmada hata oluştu");
    }); 

}

$scope.gonderiSikayetAciklama = "";

$scope.gonderiSikayetGizle = function(){
    var obj = {
      gonderiSikayetId : $scope.seciliGonderiSikayet.id,
      psId : $scope.seciliGonderiSikayet.sikayetEdilenGonderiId,
      aciklama : $scope.gonderiSikayetAciklama,
      kararVerenAdmin : $scope.admin.username,
      sikayetEdilenUsername : $scope.seciliGonderiSikayet.sikayetEdilenUsername,
      sikayetNedeni : $scope.seciliGonderiSikayet.sikayetNedeni
    }
    var url = $scope.appName+'gonderiSikayetGizle';
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
        bootbox.alert("Değerlendirme kaydedildi.");
          $timeout( function(){
          location.reload();
          }, 2000 )        
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata!");
    });  
}


$scope.gonderiSikayetGizleme = function(){

    var url = $scope.appName+'gonderiSikayetGizleme';
    var obj = {
      gonderiSikayetId : $scope.seciliGonderiSikayet.id,
      psId : $scope.seciliGonderiSikayet.sikayetEdilenGonderiId,
      aciklama : $scope.gonderiSikayetAciklama,
      kararVerenAdmin : $scope.admin.username,
      sikayetEdilenUsername : $scope.seciliGonderiSikayet.sikayetEdilenUsername,
      sikayetNedeni : $scope.seciliGonderiSikayet.sikayetNedeni      
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
        bootbox.alert("Değerlendirme kaydedildi.");
          $timeout( function(){
          location.reload();
          }, 3000 )        
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata!");
    }); 

}

$scope.gonderiSec = function(index){
  $scope.seciliGonderiSikayet = $scope.pageListGonderi[index];
}

$scope.yorumSec = function(index){
  $scope.seciliYorumSikayet = $scope.pageListYorum[index];
}

$scope.profilSec = function(index){
  $scope.seciliProfilSikayet = $scope.pageListProfil[index];
}



$scope.getYorumSikayetList = function(){
    var url = $scope.appName+'getYorumSikayetleri';
    $scope.yorumSikayetAranacakKelime = "dummy";
    var data = $scope.sessionCookie;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        var obj = JSON.parse(response.data);
        var sikayetList = [];
        for(i=0; i<obj.length; i++){
            sikayetList[i] = obj[i].yorumSikayet;
            sikayetList[i].sikayetNedeni = obj[i].tanim;
            sikayetList[i].sikayetZamani = new Date(obj[i].yorumSikayet.sikayetZamani).toLocaleString('en-GB');
            sikayetList[i].degerlendirildi = sikayetList[i].degerlendirildi == 0 ? "AÇIK" : "KAPALI"; 
        }
        $scope.yorumSikayetTabloVerisi = sikayetList;
        $scope.yorumSikayetAranacakKelime = "";      

    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Şikayet oluşturmada hata oluştu");
    }); 

}

$scope.profilSikayetAciklama = "";
$scope.yorumSikayetGizle = function(){
    var obj = {
      sikayetId : $scope.seciliYorumSikayet.sikayetId,
      yorumId : $scope.seciliYorumSikayet.sikayetEdilenYorumId,
      aciklama : $scope.yorumSikayetAciklama,
      kararVerenAdmin : $scope.admin.username,
      sikayetEdilenUsername : $scope.seciliYorumSikayet.sikayetEdilenUsername,
      sikayetNedeni : $scope.seciliYorumSikayet.sikayetNedeni,
      yorumIcerik : $scope.seciliYorumSikayet.yorumIcerik       
    }

    var url = $scope.appName+'yorumSikayetGizle';
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
        bootbox.alert("Değerlendirme kaydedildi.");
          $timeout( function(){
          location.reload();
          }, 3000 );
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata!");
    });  
}


$scope.yorumSikayetGizleme = function(){

    var obj = {
      sikayetId : $scope.seciliYorumSikayet.sikayetId,
      yorumId : $scope.seciliYorumSikayet.sikayetEdilenYorumId,
      aciklama : $scope.yorumSikayetAciklama,
      kararVerenAdmin : $scope.admin.username,
      sikayetEdilenUsername : $scope.seciliYorumSikayet.sikayetEdilenUsername,
      sikayetNedeni : $scope.seciliYorumSikayet.sikayetNedeni,
      yorumIcerik : $scope.seciliYorumSikayet.yorumIcerik           
    }
    var url = $scope.appName+'yorumSikayetGizleme';
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
        bootbox.alert("Değerlendirme kaydedildi.");
          $timeout( function(){
          location.reload();
          }, 3000 )        
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata!");
    }); 

}

$scope.sikayetDegerlendir = function(val){
  if(val == 1) // yorum şikayet
  {
    var e = document.getElementById("gizleSelectYorum");
    var talep = e.options[e.selectedIndex].value;
    if(talep == 0)
      $scope.yorumSikayetGizle();
    if(talep == 1)
      $scope.yorumSikayetGizleme();
  }

  if(val == 2) // gonderi şikayet
  {
    var e = document.getElementById("gizleSelectGonderi");
    var talep = e.options[e.selectedIndex].value;   
    if(talep == 0)
      $scope.gonderiSikayetGizle();
    if(talep == 1)
      $scope.gonderiSikayetGizleme();
  }

  if(val == 3) // profil sikayet
  {
    var e = document.getElementById("gizleSelectProfil");
    var talep = e.options[e.selectedIndex].value;   
    if(talep == 0)
      $scope.profilSikayetGizle();
    if(talep == 1)
      $scope.profilSikayetGizleme();
  }


}

$scope.destekMesajiAra = null;
$scope.destekMesajlari = [];
$scope.destekFilters = [
"date",
"id",
"mail",
"message",
"name",
"phone"
];


$scope.getDestekMesajlari = function(){
    $scope.destekMesajiAra = "dummy";
    var url = $scope.appName+'getSmList';
    var data = "dummy";
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
          obj[i].date = new Date(obj[i].date).toLocaleString('en-GB');
        }
        $scope.destekMesajlari = obj;
        $scope.destekMesajiAra = "";
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata!");
    });  
}

$scope.dbURL = null;


$scope.getRootDB = function(callback){
    var url = $scope.appName+'getRootDB';
    var obj = "dummy";
    var data = obj;
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


$scope.firstFunctionToExecute = function(){
    $scope.getAdminIfo();  
    $scope.getYorumSikayetList();
    $scope.getGonderiSikayetList();
    $scope.getProfilSikayetList();
    $scope.getDestekMesajlari();
    $scope.getRootDB(function(response){
        $scope.dbURL = JSON.parse(response.data);
      });    
}

$scope.firstFunctionToExecute();








$scope.userSilinebilirFlag = false;
$scope.kullaniciAra = function(kullaniciAdi){
    $scope.checkUserSilinebilirMi();
    var url = $scope.appName+'adminUserGetir';
    var data = kullaniciAdi;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        var resp = JSON.parse(response.data);   
        $scope.user = resp.user;
        $scope.userProfilePhoto = "/Geziyorum/DB/profile_photo/" + resp.pp.photoName;
        if($scope.user.isVerified == 1)
          $scope.user.isVerified = "EVET";
        else 
          $scope.user.isVerified = "HAYIR";
      
        if($scope.user.hide == 1) 
          $scope.user.hide = "EVET"; 
        else 
          $scope.user.hide = "HAYIR";   
    }, function (response) {
        $scope.user = null;
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata! " + $scope.error.message);
    }); 
}



$scope.checkUserSilinebilirMi = function(){
    $scope.userSilinebilirFlag = false;
    var url = $scope.appName+'userSilinebilirMi';
    var data = $scope.aranacakKullaniciAdi;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        var resp = JSON.parse(response.data);   
        if(resp == true)
          $scope.userSilinebilirFlag = true;
        else
          $scope.userSilinebilirFlag = false;          
    }, function (response) {
        var resp = JSON.parse(response);
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata! " + $scope.error.message);
    }); 
}


$scope.cikisYap = function(){
    var url = $scope.appName+'adminCikisYap';
    var data = $scope.admin.adminId;
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


$scope.userSil = function(){
    var url = $scope.appName+'userSil';
    var data = $scope.aranacakKullaniciAdi;
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url, data, config).then(function (response) {
        var resp = JSON.parse(response.data);   
        bootbox.alert("Kullanıcı başarıyla silindi.");        
    }, function (response) {
        var resp = JSON.parse(response);
        $scope.error=JSON.parse(response.data);
        bootbox.alert("Hata! " + $scope.error.message);
    });   

}


$scope.getTripByPSID = function(psId,callback){
  var data = psId;
    var url = $scope.appName+'getTripInfoByPSID';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url,data, config).then(function (response) {
          callback(response);
    }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}

$scope.getPsByPSID = function(psId,callback){
  var data = psId;
    var url = $scope.appName+'getPsInfoByPSID';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url,data, config).then(function (response) {
          callback(response);
        }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}

$scope.getFakeSession = function(psId,callback){
  var data = psId;
    var url = $scope.appName+'getFakeSession';
    var config = {
      transformRequest: angular.identity,
      transformResponse: angular.identity,
      headers : {
          'Content-Type': 'application/json'
      }
    }

   $http.post(url,data, config).then(function (response) {
          callback(response);
        }, function (response) {
        $scope.error=JSON.parse(response.data);
        bootbox.alert($scope.error.message);
    }); 

}


$scope.getTripMedia = function(token,tripId,callback){
    var url = $scope.appName+'getTripMediasForAdmin';
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


$scope.checkIfSameLongLat = function(tripMedias){
  for(i=0; i<tripMedias.length; i++){
    for(j=i+1; j<tripMedias.length; j++){
      if(tripMedias[i].latitude == tripMedias[j].latitude && tripMedias[i].longitude == tripMedias[j].longitude)
        tripMedias[i].latitude = parseFloat(tripMedias[i].latitude.toFixed(5)) + 0.00004;
        tripMedias[i].longitude = parseFloat(tripMedias[i].longitude.toFixed(5)) + 0.00008;
    }
  }
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



$scope.showPaylasimDetayPane = function(psID){

$scope.ownerUserToken = "";

    $scope.getFakeSession(psID,function(response){
        $scope.ownerUserToken = response.data;


    $scope.getTripByPSID(psID,function(response){
      $scope.seciliTrip = JSON.parse(response.data);

      $scope.getPsByPSID(psID,function(response){
        $scope.seciliPs = JSON.parse(response.data);


        $scope.getTripMedia($scope.ownerUserToken,$scope.seciliTrip.tripId,function(response){
           var tripMedias = JSON.parse(response.data);     
           $scope.rootPaylasimDetay = $scope.dbURL.mediapath + $scope.seciliTrip.folderName;
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

     $scope.getPersonalSharingComments($scope.ownerUserToken,$scope.seciliPs.id,function(response){
         var comments = JSON.parse(response.data);
         for(i=0; i<comments.length; i++ ){
          comments[i].sendTime = new Date(comments[i].sendTime).toLocaleString('en-GB');
        }      
        $scope.seciliGonderiCommentleri = comments;
     });


     $scope.getTripDetay($scope.ownerUserToken,$scope.seciliTrip.tripId,function(response){
        var obj = JSON.parse(response.data);
        obj.starTime = new Date(obj.starTime).toLocaleString('en-GB'); 
        obj.endTime = new Date(obj.endTime).toLocaleString('en-GB'); 
        $scope.tripDetay = obj;


           $scope.getOlusturucuInfo($scope.ownerUserToken,$scope.tripDetay.tripId,function(response){
              var obj = JSON.parse(response.data);
              $scope.tripOlusturucInfo = obj; 

           });   // gezi olustrucu bilgileri gelir.

           $scope.checkGrupGezisi($scope.ownerUserToken,$scope.tripDetay.tripId,function(response){
              var obj = JSON.parse(response.data);
              $scope.grupGezisiFlag = obj;
                if($scope.grupGezisiFlag){
                     $scope.getKatilimcilarInfo($scope.ownerUserToken,$scope.tripDetay.tripId,function(response){
                        var obj = JSON.parse(response.data);
                        $scope.geziKatilimcilarInfo = obj;

                     });            
                }

           });  // gezi katılımcıları bilgileri gelir.     


       $scope.getTripPath($scope.ownerUserToken,$scope.tripDetay.tripId,function(response){
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


     })  // zaman mekan gelir.


     $scope.getPersonalSharingDetay($scope.ownerUserToken,$scope.seciliPs.id,function(response){
      $scope.begenPaylasCount = JSON.parse(response.data);
      if($scope.begenPaylasCount.likeCount == 0)
        $scope.begenPaylasCount.likeCount = null;

      if($scope.begenPaylasCount.shareCount == 0)
        $scope.begenPaylasCount.shareCount = null;
     })  // beğen paylaş count gelir.     



          });

        });  

    });




});

};



        var vm = this;


        NgMap.getMap({ id: 'polylineMap' }).then(function(map) {
            vm.polylineMap = map;
            google.maps.event.addListenerOnce(vm.polylineMap, 'idle', function(){
                 vm.mapSettings = { zoom: 7};
            });    

        });        


        $('#paylasimDetay').on('show.bs.modal', function(e) {
            $timeout(function() {
                google.maps.event.trigger(vm.polylineMap, 'resize');
                google.maps.event.trigger(vm.svMap, 'resize');                
            });
        });




};







myApp.directive("paging", function() {
 
  return {
    restrict: 'AE',
    template:  '<ul class="pagination" >'+
                         '                <li ng-class="prevPageDisabled()">'+
                         '                 <a href ng-click="prevPage()"> << </a>'+
                         '            </li>'+
                         '           <li ng-repeat="n in range()" ng-class="{active: n == currentPage}" ng-click="setPage(n)">'+
                         '              <a href="#">{{n+1}}</a>'+
                         '         </li>'+
                         '        <li ng-class="nextPageDisabled()">'+
                         '           <a href ng-click="nextPage()">'+
                         '             >>  '+
                         '         </a>'+
                         '   </li>'+
                         ' </ul></br>'+
                         ' <b>Toplam Kayıt Sayısı:</b> <b>{{ total() }}</b>',
    scope: {datasource:'=',  //pagination için gerekli listenin tamamı
            pageData:'=',    //paging yapılmış ve onyüze döndürülen liste
            pageSize: '=',   //her sayfanın kaç satırdan oluşacağını bilgisi
            filtre: '=',     //search için arama text i
            filterVariables:'=' // search için liste içindeki objelerdeki hangi propertyler de bakılacağının bilgisi. Array olmalı
        },
    transclude : false , 
    link: function (scope, element, attrs, ngModelCtrl) {
  
        
     scope.itemsPerPage = scope.pageSize;
     scope.currentPage = 0;
     scope.filteredData = scope.datasource;

     scope.range = function () {
        var rangeSize = 5;
        var ret = [];
        var start;

        start =  scope.currentPage;
        if (start >  scope.pageCount() - rangeSize) {
            start =  scope.pageCount() - rangeSize;
        }

        for (var i = start; i < start + rangeSize; i++) {
            if (i >= 0) {
                ret.push(i);
            }

        }
        return ret;
    };
    
    scope.pageCount = function () {
        return Math.ceil(scope.total() / scope.itemsPerPage);
     };
     
    scope.prevPage = function () {
        if ( scope.currentPage > 0) {
            scope.currentPage--;
        }
    };

    scope.prevPageDisabled = function () {
        return scope.currentPage === 0 ? "disabled" : "";
    };

    scope.nextPage = function () {
      
        if (scope.currentPage < scope.pageCount() - 1) {
            scope.currentPage++;
        }
    };

    scope.nextPageDisabled = function () {
       
        
        return scope.currentPage === scope.pageCount() - 1 ? "disabled" : "";
    };

   

    scope.setPage = function (n) { 
        if (n >= 0 && n < scope.pageCount()) {
            scope.currentPage = n;
        } 
    };
    
 
    
    scope.getItems=function   ( offset, limit) {             
            var result =[];
            if (scope.filteredData.length > 0) {
                result = scope.filteredData.slice(offset, offset + limit);
            }
            
            return result;
    } 
     
    
    scope.total= function () {
        return scope.filteredData.length;
    };

    scope.dataRefresh = function (newValue) {
        if (scope.filteredData) {
            if (scope.pageSize) {
                var pagedData = scope.getItems(newValue * scope.itemsPerPage, scope.itemsPerPage);
                scope.pageData = pagedData;
            }
        }
    } 
     

    scope.$watch("currentPage", function (newValue, oldValue) {
             
            console.log("currentPage"+newValue);
            scope.dataRefresh(newValue);  
            
           
    });


    /* data source değiştiğinde data source'un değişimini izleyerek tabloyu yeniden şekillendirir.*/
    scope.$watch("datasource", function () {
     scope.itemsPerPage = scope.pageSize;
     scope.currentPage = 0;
     scope.filteredData = scope.datasource;                     
    });    


    /*data listesi içersinde , verilen propertler içersinde searchtext ile eşleşme olup olmadığına bakar.Sonrasın pagination için data refreshlenir.*/
        scope.searchFilterData = function () {
        
            var pagedData = []; 
                for (var i = 0; i < scope.datasource.length; i++) {
                 var eslesti =compareObjectForFilter(scope.datasource[i], scope.filterVariables,scope.filtre);
                    if (eslesti) {
                        pagedData.push(scope.datasource[i]);
                    }
                }

            console.log("pagedData" + pagedData); 
            scope.filteredData = pagedData;
            scope.dataRefresh(0) ;
           
        }

        scope.$watch("filtre", function (newValue, oldValue) {
            scope.searchFilterData();
        });
       
    }
  };
});



function compareObjectForFilter(dataObject,filterVariables,searchText){
    var re = new RegExp(searchText, 'i');   
    var bEslesti=false 
    var indx=0;
    while (!bEslesti && (filterVariables.length>indx) ) { 
            if (!searchText ||re.test(dataObject[""+filterVariables[indx]].toString()) ){
                bEslesti=true;                            
            }
            indx++
    }
                
   return bEslesti;             
};
