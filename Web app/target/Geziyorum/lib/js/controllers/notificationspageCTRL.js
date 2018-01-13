myApp.controller("notificationspageCTRL", mainIndex);


function mainIndex($scope,$http,$q,$timeout){
$scope.activeTab = 1;
$scope.showMentions = false;
$scope.showAllMentions = true;
$scope.geziler = [1,2,3];


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


$scope.setActiveTab = function(num){
	$scope.activeTab = num;
}

}

