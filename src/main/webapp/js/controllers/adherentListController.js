// START - USED SERVICES
/*
 *	adherentService.delete
 *		PARAMS: 
 *					ObjectId id - Id adherent
 *		
 *
 *	adherentService.list
 *		PARAMS: 
 *		RETURN: ARRAY OF adherent
 *				
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * adherentService  
 */
// END - REQUIRED RESOURCES


//CRUD LIST FOR [object Object]

app.controller('adherentListController', ['$scope', 'adherentService',
    function ($scope, adherentService ) {
		
    	$scope.list = adherentService.query();
    	
}]);