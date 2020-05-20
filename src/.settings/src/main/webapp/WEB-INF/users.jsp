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
                
             
                <div class="button_cont" align="center"><a class="example_e" href="applications"  style="width: 200px">Register Applications</a></div>
        
               <br/><br/>
                <div class="button_cont" align="center"><a class="example_e" href="apis" style="width: 200px">Register    APIs     </a></div>
               <br/>            <br/>
                <div class="button_cont" align="center"><a class="example_b" href="users" style="width: 200px">Register    Users   </a></div>
<br/>            <br/>
                <div class="button_cont" align="center"><a class="example_e" href="components" style="width: 200px">Register    Component constraints   </a></div>

                  

             </ul>

			  </div>  

    
<div class="col-8 col-lg-9" style="border:solid;border-width:1px;border-color:#E2E2E2;margin-top:10px;background-color:white;">   
  <div class="row" style="margin-top:10px;">
    <div class="col-5">
        
        <div class="row ">
            <div class="col-1 align-items-center" style="background-color:#4D4D4D;color: white;line-height: 33px;">3</div>
            <div class="w-100"></div>
            <div class="col-1"></div>

        </div>
    
    </div>
    
  </div>
  <div class="form-style-2">
    <div class="form-style-2-heading">Register the users of the application</div>
    <form method='post'>
            <label for="field2"><span>User ID <span class="required">*</span></span><input type="text" class="input-field" name="userId" value="" required/></label>
    <label for="field4"><span>App Name</span><select name="appId" class="select-field">
    <option value="CPLO Web">CPLO Web</option>
    <option value="InSights">InSights</option>
    <option value="DLP">DLP</option>
    <option value="Internal Portal">Internal portal</option>
    <option value="CPO">CPO</option>
    <option value="IDMS">IDMS</option>
    <option value="Global Match Maker">Global Match Maker</option>
    <option value="ClaimIt">ClaimIt</option>
    
    </select></label>
    <label for="field2"><span>Roles <span class="required">*</span></span><input type="text" class="input-field" name="role" value="" required/></label>
    
    <label><span> </span><input type="submit" value="Register Users"/></label>
    </form>
    
    <div style="margin-left:20%;">
		<input type="submit" value="Show Registered Users" id="showApi" />
	</div>
			<div>
				<div id="apis"  >
				
				</div>
			</div>
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
    $("#showApi").click(function(){
        $.getJSON("http://10.11.188.158:8080/authorisation/getUsersList", function(result){
        console.log(result,'this is result');
        var tableBody = '<div style="margin-left:20%; width:100%; margin-top:5%;"><table border="1" id="showtable"  class="table table-hover"><thead style="background-color: #38a108; color: white;"><tr><th>AppName</th><th>Role</th><th>User Id</th><th>Grant Access</th></tr></thead><tbody></div>';
        for(var i=0;i<result.AppName.length;i++)
        {
        	tableBody+= '<tr>';
	        	tableBody+= '<td>';
	        	tableBody+= result.AppName[i];
	        	tableBody+= '</td>';
	        	
	        	tableBody+= '<td>';
	        	tableBody+= result.role[i];
	        	tableBody+= '</td>';
	        	
	        	tableBody+= '<td>';
	        	tableBody+= result.userId[i];
	        	tableBody+= '</td>';
	        	
	        	tableBody+= '<td>';
	        	tableBody+= result.grantAccess[i];
	        	tableBody+= '</td>';
	        tableBody+= '</tr>';
        }
        	
        tableBody+= '</tbody></table';
        $('#apis').html(tableBody);
        });
    });
});
console.log(resultt,'resultttt');
</script>
</body>
  
</body>
</html>