// START - USED SERVICES
/*
 *	livreService.create
 *		PARAMS: 
 *					livre obj - Object to insert
 *		
 *
 *	livreService.update
 *		PARAMS: 
 *					ObjectId id - Id
 *		
 *
 *	livreService.get
 *		PARAMS: 
 *					ObjectId id - Id livre
 *		RETURN: livre
 *				
 *
 *	pretService.findBylivre_pret
 *		PARAMS: 
 *					Objectid key - Id of model to search for
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * livreService  
 * pretService  
 */
// END - REQUIRED RESOURCES

app.controller('livreEditController', ['$scope', '$location', '$routeParams', '$q', 'livreService', 'pretService', 'pretService',
    function ($scope, $location, $routeParams, $q, livreService , pretService , pretService) {

    	//manage create and save
		$scope.external = {};
		
    	if ($routeParams.id != 'new')
    	{
        	$scope.id = $routeParams.id;
        	$scope.obj = livreService.get({_id: $scope.id});
        	$scope.external._pretlivre_pret = pretService.findBylivre_pret({key: $scope.id});
        	
    	}
    	else{
    		$scope.obj = new livreService();
        	$scope.external._pretlivre_pret = [];
        	
    	}
    	
    	$scope.save = function(){
    		$scope.obj.$save().then(function(data){
    			$scope.obj=data;
        		$location.path('/livres/');
    		});
    	}
    	
    	$scope.remove = function(){
    		livreService.remove({_id: $scope.obj._id}).$promise.then(function(){
				$('#removeModal').modal('hide');
				$('.modal-backdrop').remove();
				$('.modal-open').removeClass("modal-open");
        		$location.path('/livres/');
    		});
    	}
    	
    		
        //manage External relation livre_pret
        		
    	$scope.list_pretlivre_pret = pretService.query();
    	
}]);