// START - USED SERVICES
/*
 *	pretService.delete
 *		PARAMS: 
 *					ObjectId id - Id pret
 *		
 *
 *	pretService.list
 *		PARAMS: 
 *		RETURN: ARRAY OF pret
 *				
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * pretService  
 */
// END - REQUIRED RESOURCES


//CRUD LIST FOR [object Object]

app.controller('pretListController', ['$scope', 'pretService',
    function ($scope, pretService ) {
		
    	$scope.list = pretService.query();
    	
}]);