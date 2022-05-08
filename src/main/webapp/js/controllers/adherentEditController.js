// START - USED SERVICES
/*
 *	adherentService.create
 *		PARAMS: 
 *					adherent obj - Object to insert
 *		
 *
 *	adherentService.update
 *		PARAMS: 
 *					ObjectId id - Id adherent
 *		RETURN: adherent
 *				
 *
 *	adherentService.get
 *		PARAMS: 
 *					ObjectId id - Id adherent
 *		RETURN: adherent
 *				
 *
 *	pretService.findByadherent_pret
 *		PARAMS: 
 *					Objectid key - Id of model to search for
 *		
 *
 */
// END - USED SERVICES

// START - REQUIRED RESOURCES
/*
 * adherentService  
 * pretService  
 */
// END - REQUIRED RESOURCES

app.controller('adherentEditController', ['$scope', '$location', '$routeParams', '$q', 'adherentService', 'pretService', 'pretService',
    function ($scope, $location, $routeParams, $q, adherentService , pretService , pretService) {

    	//manage create and save
		$scope.external = {};
		
    	if ($routeParams.id != 'new')
    	{
        	$scope.id = $routeParams.id;
        	$scope.obj = adherentService.get({_id: $scope.id});
        	$scope.external._pretadherent_pret = pretService.findByadherent_pret({key: $scope.id});
        	
    	}
    	else{
    		$scope.obj = new adherentService();
        	$scope.external._pretadherent_pret = [];
        	
    	}
    	
    	$scope.save = function(){
    		$scope.obj.$save().then(function(data){
    			$scope.obj=data;
        		$location.path('/adherents/');
    		});
    	}
    	
    	$scope.remove = function(){
    		adherentService.remove({_id: $scope.obj._id}).$promise.then(function(){
				$('#removeModal').modal('hide');
				$('.modal-backdrop').remove();
				$('.modal-open').removeClass("modal-open");
        		$location.path('/adherents/');
    		});
    	}
    	
    		
        //manage External relation adherent_pret
        		
    	$scope.list_pretadherent_pret = pretService.query();
    	
}]);