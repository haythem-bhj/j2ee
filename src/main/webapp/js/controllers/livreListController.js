// START - USED SERVICES
/*
 *	livreService.delete
 *		PARAMS: 
 *					ObjectId id - Id livre
 *		
 *
 *	livreService.list
 *		PARAMS: 
 *		RETURN: ARRAY OF livre
 *				
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * livreService  
 */
// END - REQUIRED RESOURCES


//CRUD LIST FOR [object Object]

app.controller('livreListController', ['$scope', 'livreService',
    function ($scope, livreService ) {
		
    	$scope.list = livreService.query();
    	
}]);