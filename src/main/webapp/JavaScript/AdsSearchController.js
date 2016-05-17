/**
 * 
 */
angular.module('AdsSearchApp').controller('AdsSearchController', function ($scope, AdsSearchService,$window) {
   $scope.searchText = null;
   $scope.showResult = false;
   $scope.tokens = [];
   
   $window.init= function() {
	   $scope.$apply($scope.load_guestbook_lib);
	 };
	 $scope.load_guestbook_lib = function() {
		  gapi.client.load('adsearchendpoints', 'v1', function() {
		    $scope.is_backend_ready = true;
		    
		  }, '/_ah/api');
		};

   $scope.getTokens = function()
   {
	   $scope.showResult = true;
	   var request = gapi.client.adsearchendpoints.getTokens({'name': $scope.searchText});
		request.execute(function(data)
				{		
				$scope.tokens  = data.items;
			$scope.$apply();
			console.log($scope.tokens);
				});
	   
   }


});