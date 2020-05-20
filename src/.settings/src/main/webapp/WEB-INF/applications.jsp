<!DOCTYPE html>
<html>
<head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="style.css" rel="stylesheet" type="text/css">
    

</head>
<body>

<div class="jumbotron jumbotron-fluid" style="background-color:white;padding:1rem 1rem;height:40px;">
  <div class="container-fluid">
    <div class="row">
	<div class="col-1">
     <img src="component.PNG" height="60px" width="60px">
	</div>
	<div class="col-7">
	
	 <div class="row align-items-center">
	 <div class="col-12" style="font-weight:bold;font-size: 30px;"><p align="center">NDH Authorization service </p></div>
     <div class="col-12" style="margin-top:-14px">
	 </div>
	 </div>
	 
	</div>
	
	</div>    
  </div>
</div>
<br/>
<br/>
<div class="container-fluid" style="background-color:#FAFAFA">

<div class="row">
 
     <div class="col-3 col-lg-2">
        <ul class="nav flex-column "   >
                
             
                <div class="button_cont" align="center"><a class="example_b" href="applications"  style="width: 200px">Register Applications</a></div>
        
               <br/><br/>
                <div class="button_cont" align="center"><a class="example_e" href="apis" style="width: 200px">Register    APIs     </a></div>
               <br/>            <br/>
                <div class="button_cont" align="center"><a class="example_e" href="users" style="width: 200px">Register    Users   </a></div>
<br/>            <br/>
                <div class="button_cont" align="center"><a class="example_e" href="components" style="width: 200px">Register    Component constraints   </a></div>

                  

             </ul>

			  </div>  

    
<div class="col-8 col-lg-9" style="border:solid;border-width:1px;border-color:#E2E2E2;margin-top:10px;background-color:white;">   
  <div class="row" style="margin-top:10px;">
    <div class="col-5">
        
        <div class="row ">
            <div class="col-1 align-items-center" style="background-color:#4D4D4D;color: white;line-height: 33px;">1</div>
            <div class="w-100"></div>
            <div class="col-1"></div>

        </div>
    
    </div>
    
  </div>
  <div class="form-style-2">
    <div class="form-style-2-heading">Register the application below</div>
    <form  method="post">
    <label for="field4"><span>App Name  <span class="required">*</span></span><select name="appId" class="select-field">
    <option value="CPLO Web">CPLO Web</option>
    <option value="InSights">InSights</option>
    <option value="DLP">DLP</option>
    <option value="Internal Portal">Internal portal</option>
    <option value="CPO">CPO</option>
    <option value="IDMS">IDMS</option>
    <option value="Global Match Maker">Global Match Maker</option>
    <option value="ClaimIt">ClaimIt</option>
    </select></label>
    <label for="field2"><span>Callback URL <span class="required"></span></span><input type="text" class="input-field" name="callbackURL" value="" /></label>
    
    <label><span> </span><input type="submit" value="Register Applications" /></label>
    </form>
        <div style="margin-left:20%;">
		<input type="submit" value="Show Registered Applications" id="showApi" />
		</div>
			<div>
				<div id="apis"  >
				
				</div>
			</div>
		<!-- <div class="menu">
		   <ul>
		    <a href=""><li>LINK ONE</li></a>
		    <a href=""><li>LINK TWO</li></a>
		    <a href=""><li>LINK THREE</li></a>
		    <a href=""><li>LINK FOUR</li></a>
		    <a href=""><li>LINK FIVE</li></a>
		  </ul> 
		  <p>heloo</p>
		</div> 	
			 -->
			 
	<div id="editModal">
	</div>	
	
	<div id="deleteModal">
	</div>	 
			 
<!-- 	<div class="modal fade" id="edit" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        
        <div class="modal-body">
          <p>Editing part</p>
        </div>
        
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
        
      </div>
      
    </div>
  </div>	 
 -->
	
    
    
    </div>
 </div> 
</div>
</div>

 <!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>  

<script>
var resultt;
$(document).ready(function(){
	$(".menu").hide();
	var i=0;
	var ham = 'hamburger';
    $("#showApi").click(function(){
        $.getJSON("http://10.11.188.158:8080/authorisation/getApplicationList", function(result){
        console.log(result,'this is result');
        
        var tableBody = '<div style="margin-left:20%; width:100%; margin-top:5%;"><table border="1" id="showtable"  class="table table-hover"><thead style="background-color: #38a108; color: white;"><tr><th>AppName</th><th>callbackUrl</th></tr></thead><tbody></div>';
        for(i=0;i<result.AppName.length;i++)
        {
        	console.log(ham+i);
        	var key = result.AppName[i]
        	tableBody+= '<tr>';
	        	tableBody+= '<td id="appname'+i+'" contenteditable="false">';
	        	tableBody+= result.AppName[i];
	        	tableBody+= '</td>';
	        	
	        	tableBody+= '<td>';
	        	tableBody+= result.callbackUrl[i];
		        	//tableBody+= '<div>';
				        	/* tableBody+= '<div class="dropdown">';
				        		tableBody +='<button id="'+ham+i+'" class="hamburger" onClick="fun(this)" >&#8286;</button>';
				        		tableBody += '<div id="myhamburger'+i+'" class="dropdown-content">';
				        			tableBody +='<a id="editprompt'+i+'" data-toggle="modal" data-target="#edit1" onClick="editfun(this,'+i+') ">Edit</a>';
				        			tableBody +='<a id="deleteprompt'+i+'" data-toggle="modal" data-target="#delete1" onClick="deletefun(this)">Delete</a>';
				        			
				        		tableBody += '</div>';
				        		
				    			
				        	tableBody+= '</div>'; */
				        	/* tableBody +='<button id="editsub'+i+'"  class="subbtn1"  >submit</button>';
			    			tableBody +='<button id="deletesub'+i+'"  class="deletebtn"  >cancel</button>';  */
		        	//tableBody+= '</div>';
	        	tableBody+= '</td>';
	        	
	        tableBody+= '</tr>';
	        console.log("i: "+i);
	        /* $("#editsub"+i).hide();
	        $("#deletesub"+i).hide(); */
        }
  	
        tableBody+= '</tbody></table';
        $('#apis').html(tableBody);
        });
        			
    });
 

});

$(document).ready(function(){
	
});

/* function fun(e){
		
	    console.log('clicked');
	    console.log(e.id);
	    //$(".menu").slideToggle();
	    $("#my"+e.id).slideToggle();
	    //document.getElementById("my"+e.id).classList.toggle("show");
	 }

function editfun(k,key){
	console.log("this is edit modal");
	var x = document.getElementById("appname"+key);
	//var sub = document.getElementById("editsub"+key).style.visibility = "visible";
	//var del = document.getElementById("deletesub"+key).style.visibility = "visible";
	//console.log(sub)
		if(x.contentEditable == "false"){
			x.contentEditable = "true";
			 $("#myhamburger"+key).slideToggle();
			//console.log(x)
		}
		else{
			x.contentEditable = "false";
			$("#myhamburger"+key).slideToggle();
		}
	console.log(x);
}


function deletefun(j){
	console.log("this is delete modal");
	var deleteModal="";
	deleteModal += '<div class="modal fade" id="delete1" role="dialog">';
		deleteModal += '<div class="modal-dialog">';
			deleteModal += '<div class="modal-content">';
				deleteModal += '<div class="modal-body"> <p>put your delete code here</p> </div>';
	deleteModal += '</div></div></div>';
	document.getElementById("deleteModal").innerHTML= deleteModal;
	console.log(j.id);
} */


console.log(resultt,'resultttt');

</script>
 
</body>
</html>