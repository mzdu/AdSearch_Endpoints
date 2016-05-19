/**
 * 
 */
angular.module('AdsSearchApp').service(
         "AdsSearchService",
         function ($http, $q) {
             return ({
                 getTokens:getTokens,
                
             });
             
             function getTokens(searchString)
             {
            	 var url = gapi.client.adsearchendpoints.getTokens();
            	 var data = {'name': searchString};
            	 return getData(url, data);
             }
             
             function getData(url, data) {
                 var deferred = $q.defer();

                 $http({
                     url: url,
                     method: 'get',
                     params: data
                 }
                     ).success(function (data) {
                         //Passing data to deferred's resolve function on successful completion
                         deferred.resolve(data);
                     }).error(function () {
                         //Sending a friendly error message in case of failure
                         deferred.reject("An error occured while fetching the data");
                     });

                 return deferred.promise;
             }
             
         });