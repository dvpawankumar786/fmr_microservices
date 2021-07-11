//*
$(document).ready(function(){

    // code to read selected table row cell data (values).
    $("#myTable").on('click','.btnSelect',function(){
         // get the current row
    	
    	var request = {}
        request["portfolioId"] = $("#pid").val();
    	
    	alert($("#pid").val());
         var currentRow=$(this).closest("tr"); 
         
         var pid=currentRow.find("td:eq(0)").text(); // get current row 1st TD value
         var col1=currentRow.find("td:eq(1)").text(); // get current row 2nd TD
         var col2=currentRow.find("td:eq(2)").text(); // get current row 3rd TD
         var col3=currentRow.find("td:eq(3)").text(); 
         var col4=currentRow.find("td:eq(4)").text(); 
         var col5=currentRow.find("td:eq(5)").text(); 
         var col6=currentRow.find("td:eq(6)").text(); 
         var sid=$("#sid").val();
        fire_ajax_submit_save(pid,col1,col2,col3,col4,col5,col6,sid);
         
    });
});
//*
function fire_ajax_submit_save(pid,col1,col2,col3,col4,col5,col6,sid) {

    var request = {}
    request["symbol"] = col1;
    request["stocksId"] = sid;
    request["portfolioId"] = pid;
    request["type"] = col2;
    request["lastDividend"] = col3; 
    request["fixedDividend"] = col4;
    request["parValue"] = col5;
    request["tickerPrice"] = col6;

    var token=$("#token").val();
    
    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8086/verify/saveStockDetailsIn",
        headers: {
            'Authorization': 'Bearer '+token,
            },
        data: JSON.stringify(request),
        dataType: 'json',
        cache: false,
        success: function (data) {
        	callbackfn(data);
        },
        error: function (e) {callbackfn(e)}
    });

}
function callbackfn(data)
{
	
	window.location.replace("http://localhost:8086/token/storestocks");
}