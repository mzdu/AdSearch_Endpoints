/**
 * 
 */
angular.module('AdsSearchApp').controller('AdsSearchController', function ($scope, AdsSearchService,$window) {
   $scope.searchText = null;
   $scope.showResult = false;
   $scope.tokens = [];
   $scope.adsStatsInfoList = [];
   $scope.OptimizeadsStatsInfoList = [];
//   $window.init= function() {
//	   $scope.$apply($scope.load_adsearchendpoints);
//	 };
//	 $scope.load_adsearchendpoints = function() {
//		  gapi.client.load('adsearchendpoints', 'v1', function() {
//		    
//		  }, '/_ah/api');
//		};
   var postInitiation = function() {
	    // load all your assets
	  }
   $window.init= function() {
	   AdsSearchService.initendpoints(postInitiation);
	 };
   

   $scope.getTokens = function()
   {
	   AdsSearchService.getTokens($scope.searchText).then(function(data)
			   {
			$scope.tokens  = data.items;
			
			AdsSearchService.findMatch( $scope.tokens).then(function(data){
				$scope.adsStatsInfoList  = data.items;
				$scope.showResult = true;
			
				
			});
			AdsSearchService.optimize( $scope.tokens).then(function(data){
				$scope.OptimizeadsStatsInfoList  = data.items;
				$scope.showResult = true;
			
				
			});
		})
		 
	   
   }

});