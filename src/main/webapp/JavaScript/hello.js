/*
 * http://stackoverflow.com/questions/18260815/use-gapi-client-javascript-to-execute-my-custom-google-api
 * https://developers.google.com/appengine/docs/java/endpoints/consume_js
 * https://developers.google.com/api-client-library/javascript/reference/referencedocs#gapiclientload
 *
 */

/**
 * After the client library has loaded, this init() function is called.
 * The init() function loads the adsearchendpoints API.
 */

function init() {
	
	// You need to pass the root path when you load your API
	// otherwise calls to execute the API run into a problem
	
	// rootpath will evaulate to either of these, depending on where the app is running:
	// //localhost:8080/_ah/api
	// //your-app-id/_ah/api

	//var rootpath = "//" + window.location.host + "/_ah/api";
	
	// Load the adsearchendpoints API
	// If loading completes successfully, call loadCallback function
	//gapi.client.load('adsearchendpoints', 'v1', loadCallback, rootpath);
	 window.init();
}

/*
 * When adsearchendpoints API has loaded, this callback is called.
 * 
 * We need to wait until the adsearchendpoints API has loaded to
 * enable the actions for the buttons in index.html,
 * because the buttons call functions in the adsearchendpoints API
 */
function loadCallback () {	
	// Enable the button actions
	enableButtons ();
}

function enableButtons () {
	
	btn = document.getElementById("query_button");
	btn.onclick=function(){runQuery();};
	
	// Update the button label now that the button is active
	
}

/*
 * Execute a request to the getTokens() endpoints function
 */
function greetGenerically () {
	// Construct the request for the getTokens() function
	var request = gapi.client.adsearchendpoints.getTokens();
	
	// Execute the request.
	// On success, pass the response to sayHelloCallback()
	request.execute(getTokensCallback);
}

/*
 * Execute a request to the sayHelloByName() endpoints function.
 * Illustrates calling an endpoints function that takes an argument.
 */
function runQuery () {
	// Get the name from the name_field element
	var queryString = document.getElementById("query_field").value;
	
	// Call the sayHelloByName() function.
	// It takes one argument "name"
	// On success, pass the response to sayHelloCallback()
	
	var request = gapi.client.adsearchendpoints.getTokens({'name': queryString});
	request.execute(getTokensCallback);
	
//	var request = gapi.client.adsearchendpoints.getTokens({'name': queryString});
//	request.execute(sayHelloCallback);
	
}

// Process the JSON response
// In this case, just show an alert dialog box
// displaying the value of the message field in the response
function getTokensCallback (response) {
//	alert(response.testString);	
//	var res = response.testString;
	var res = response.items;
	alert("getTokens -> " + res);	
}



