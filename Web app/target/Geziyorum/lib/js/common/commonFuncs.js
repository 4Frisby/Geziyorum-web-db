redirectHomePage = function(){
     location.href="home";
}

redirectSupportPage = function(){
     location.href="support";
}

redirectPrivacyPage = function(){
	 location.href="privacy";
}

redirectAboutusPage = function(){
	 location.href="aboutus";
}

redirectSearchPage = function(){
	 location.href="search";
}

redirectMainPage = function(){
	 location.href="/Geziyorum";

javascript:(function(){document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); }); })();

}
