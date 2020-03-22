$(document).ready(function () {
 
    $("#home1").click(function (event) {
    	
    	event.preventDefault();
    	alert('insied home 1 js');
    	window.location.replace("http://localhost:8086/token/welcome");
    	 
    });
 
});
$(document).ready(function () {
	 
    $("#home2").click(function (event) {
    	
    	event.preventDefault();
    	alert('insied home 2js');
    	window.location.replace("http://localhost:8086/token/welcome");
    	 
    });
 
});
$(document).ready(function () {
	 
    $("#homelg").click(function (event) {
    	
    	event.preventDefault();
    	alert('insied home 2js');
    	window.location.replace("http://localhost:8086/token/login");
    	 
    });
 
});