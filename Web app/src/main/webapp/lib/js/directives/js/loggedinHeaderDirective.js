myApp.directive("myHeader",myHeaderFunc);

function myHeaderFunc(){ // başka sayfadakini kullandıldığı yere inject eder.

	return {
		templateUrl :"lib/js/directives/htm/loggedInHeader.htm"
	};
};	