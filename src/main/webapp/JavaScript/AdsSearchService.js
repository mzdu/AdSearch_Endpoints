/**
 * 
 */
angular.module('AdsSearchApp').service(
         "AdsSearchService",
         function ($http, $q) {
             return ({
                 getTokens:getTokens,
                 initendpoints:initendpoints,
                 findMatch:findMatch,
                 optimize:optimize
             });
            
             function initendpoints(postInitiation) {
            	 gapi.client.load('adsearchendpoints', 'v1', postInitiation, '/_ah/api');
              }
             function getTokens(searchString)
             {
            	 var url = gapi.client.adsearchendpoints.getTokens();
            	 var data = {'name': searchString};
            	 return getData(url.hg.hg.root + url.hg.hg.path,data);
            	 
             }
             function findMatch(tokens)
             {
            	 var url = gapi.client.adsearchendpoints.findMatch();
            	 var data = {'keyWords': tokens};
            	 return getData(url.hg.hg.root + url.hg.hg.path,data);
            	 
             }
             
             function optimize(keywords)
             {
            	 var url = gapi.client.adsearchendpoints.optimize();
            	 var data = {'keyWords': keywords};
            	 return getData(url.hg.hg.root + url.hg.hg.path,data);
            	 
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