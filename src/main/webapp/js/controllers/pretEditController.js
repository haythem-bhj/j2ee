// START - USED SERVICES
/*
 *	pretService.create
 *		PARAMS: 
 *					pret obj - Object to insert
 *		
 *
 *	pretService.update
 *		PARAMS: 
 *					ObjectId id - Id pret
 *		RETURN: pret
 *				
 *
 *	pretService.get
 *		PARAMS: 
 *					ObjectId id - Id pret
 *		RETURN: pret
 *				
 *
 *	livreService.list
 *		PARAMS: 
 *		RETURN: ARRAY OF livre
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
 * livreService  
 * pretService  
 */
// END - REQUIRED RESOURCES

app.controller('pretEditController', ['$scope', '$location', '$routeParams', '$q', 'adherentService', 'livreService', 'pretService', 'adherentService', 'livreService',
    function ($scope, $location, $routeParams, $q, adherentService , livreService , pretService , adherentService, livreService) {

    	//manage create and save
		$scope.external = {};
		
    	if ($routeParams.id != 'new')
    	{
        	$scope.id = $routeParams.id;
        	$scope.obj = pretService.get({_id: $scope.id});
        	
    	}
    	else{
    		$scope.obj = new pretService();
        	
    	}
    	
    	$scope.save = function(){
    		$scope.obj.$save().then(function(data){
    			$scope.obj=data;
        		$location.path('/prets/');
    		});
    	}
    	
    	$scope.remove = function(){
    		pretService.remove({_id: $scope.obj._id}).$promise.then(function(){
				$('#removeModal').modal('hide');
				$('.modal-backdrop').remove();
				$('.modal-open').removeClass("modal-open");
        		$location.path('/prets/');
    		});
    	}
    	
    		
        //manage relation adherent_pret
        		
    	$scope.list_adherent = adherentService.query();

    		
        //manage relation livre_pret
        		
    	$scope.list_livre = livreService.query();

}]);