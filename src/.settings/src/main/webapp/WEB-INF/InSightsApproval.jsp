<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body>

<div id="apis">	</div>















</body>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>  

<script>
$(document).ready(function(){
	
	var i=0;
   
        $.getJSON("http://10.11.188.158:8080/authorisation/getInSightsUsers", function(result){
        console.log(result,'this is result');
        
        //var tableBody = '<div style=" margin-right:20%; margin-left:20%; width:100%; margin-top:5%;"><table border="1" id="showtable" class="table table-hover"><thead style="background-color: #38a108; color: white;"><tr><th>UserId</th><th>UUID</th><th>Name</th><th>Grant Access</th></tr></thead><tbody></div>';
        var parentDiv=$('<div style="width:80%; ">');
       // parentDiv.css('margin-right','20%');
        parentDiv.css('margin-left','10%');
        parentDiv.css('margin-top','5%');
        //parentDiv.css('width','100%');
        $('#apis').append(parentDiv);
        var table=$('<table>');
        table.attr('id','showtable');
        table.attr('border','1');
        table.attr('class','table table-hover');
        parentDiv.append(table);
        var head=$('<thead>');
        head.css('color','white');
        head.css('background-color','#38a108');
        table.append(head);
        var headRow=$('<tr>');
        var th1=$('<th>');
        th1.html('UserId');
        headRow.append(th1);
        var th2=$('<th>');
        th2.html('UUID');
        headRow.append(th2);
        var th3=$('<th>');
        th3.html('Name');
        headRow.append(th3);
        var th4=$('<th>');
        th4.html('Grant Access');
        headRow.append(th4);
        head.append(headRow);
        for(i=0;i<result.users.length;i++)
        {

        	var table=$('#showtable');
        	var row=$('<tr>');
        	var td1=$('<td>');
        	td1.attr('id','appname'+i);
        	td1.attr('contenteditable','false');
        	td1.html(result.users[i][0]);
        	row.append(td1);
        	var td2=$('<td>');
        	td2.html(result.users[i][1]);
        	row.append(td2);
        	var td3=$('<td>');
        	td3.html(result.users[i][2]);
        	row.append(td3);
        	
        	var td4=$('<td>');
        	var uuid=result.users[i][1];
        	var btn1=$('<button>');
        	btn1.attr('id',uuid);
        	btn1.html('Approve');
        	btn1.click(approve);
        	var btn2=$('<button>');
        	btn2.attr('id',uuid);
        	btn2.html('Decline');
        	btn2.click(decline);
        	td4.append(btn1);
        	td4.append(btn2);
        	row.append(td4);
        	table.append(row);
        	
        	
        	
        	
        	 /* tableBody+= '<tr>';
	        	tableBody+= '<td id="appname'+i+'" contenteditable="false">';
	        	tableBody+= result.users[i][0];
	        	tableBody+= '</td>';
	        	
	        	tableBody+= '<td>';
	        	tableBody+= result.users[i][1];
	        	tableBody+= '</td>';
	        	
	        	tableBody+= '<td>';
	        	tableBody+= result.users[i][2];
	        	tableBody+= '</td>';
	        	
	        	tableBody+= '<td>';
	        	var uuid=result.users[i][1];
	        	tableBody+='<button type="button" onclick="accept()">Accept</button>'+' '+'<button type="button" onclick="reject("'+uuid+'")">Decline</button>';
	        	tableBody+= '</td>';
	        	
	        tableBody+= '</tr>'; */
	       
	        
	        
	       
        }
  	
        //tableBody+= '</tbody></table';
        //$('#apis').html(tableBody);
        });
        			
    
 

});

function approve(eventObj) {
	
	var uuid=eventObj.target.id;
	console.log(uuid,'UUID');
	$.post("http://10.11.188.158:8080/authorisation/InSightsApproval",  {
	      UDID: uuid,
	      grantAccess: true
	    })
    
  
}
function decline(eventObj) {
	 
	  var uuid=eventObj.target.id;
	  console.log(uuid,'UUID');
	  $.post("http://10.11.188.158:8080/authorisation/InSightsApproval",  {
	      UDID: uuid,
	      grantAccess: false
	    })
	}

</script>

</html>