'use strict';
 
app.controller('MyController', function($scope, $http){
    
    $scope.employees = [];
    
    
    $scope.hierarchytree = function hierarchytree(){
    	
        $http.get("http://localhost:8080/employeetree").then(
        		function(data){
        			$scope.employees = data;
        		},
        		function(errResponse){
        			console.error('Error while fetching hierarchy');
        		}
        );
    }    
});
