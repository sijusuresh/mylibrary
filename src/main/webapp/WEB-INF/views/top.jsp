<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="../resources/js/jquery.min.js"></script>

<link rel="stylesheet" href="../resources/css/style.min.css" />
<link rel="stylesheet" href="../resources/css/styles.css" />
<script>
$(document).ready(function() {
	// On Enter Key Press trigger search button click event
	$("#query").keyup(function(event) {
	    if (event.keyCode === 13) {
	        $("#searchbutton").click();
	    }
	});
	// Call getAllMovies
	$.ajax({ 
		url: "/getAllMovies?start=1",
        type: 'get',
        success: function(result){
        	$.fn.setFrameContent(result);
        },
      	error: function(result) {
	        alert('error');
	    }
	});

 	// search button click event
    $("#searchbutton").click(function(e) {
	    e.preventDefault();
	    $.ajax({
	      type: "GET",
	      url: "/searchText",
	      data: {
	        query: $("#query").val(),
	      },
	      success: function(result) {
	    	  $.fn.setFrameContent(result);
	      },
	      error: function(result) {
	        alert('error');
	      }
	    });
	  });
 	
    $.fn.setFrameContent = function(result) {
    	var responseObj = $.parseJSON(JSON.stringify(result));
	   	 // Set Right Frame Content
	   	 var total_rows = responseObj.total;
	   	 var queryCriteria = responseObj.queryCriteria;
	   	 if(queryCriteria == null){// If query is null dont send null set it as empty string
	   		queryCriteria = '';
	   	 }
	   	 //alert(total_rows);
	   	 var movieTitles = String(responseObj.items);
	   	 var rightFrameContent='<table><tr>';
	   	 var colCount=1;
	      $.each(responseObj.items, function(index, item){
	      	if(colCount > 3){
	      		colCount=1;
	      		rightFrameContent += '</tr><tr>';
	      	}
	      	rightFrameContent += '<td><img src="'+item.poster+'" alt="Movie Poster" hspace="20" height="100" width="80"></td>';
	      	rightFrameContent += '<td><div style="width:200px; word-wrap: break-word" onclick="getMovieDetails(\''+item.uri+'\')">'+item.title+'</div></td>';
	      	colCount++;
	      });
	 	  rightFrameContent += '</tr><tr><td colspan="3"></td></tr></table>';
	      if(total_rows > 10){
	    	  rightFrameContent += '<br><br><table border="0"><tr><td colspan="2">Pages:</td>';
	          var count = 1;
	          for(var x = 0;  x < total_rows; x += 10)
	          {
	          	 rightFrameContent += '<td><a href="#" onClick="getNextPage(\''+count+'\',\''+queryCriteria+'\')">'+count+'</a></td>';
	          	 count++;
	          }
	          rightFrameContent += '</tr></table>';
	      }
	      $('#contentdiv',parent.frames["rightFrame"].document.body).html(rightFrameContent);
	      //$(parent.frames["rightFrame"].document.body).html(rightFrameContent);
	      // Set Left Frame Content
	      var content="<ul id='tree'>";
	      $.each(responseObj.facets, function(index,facets){
	          content += '<li><span class="handle collapsed"></span>'+facets.name+'<ul style="display: none;">';
	          $.each(facets.facetValues,function(index,result){
	          	content += '<li><a href="#" onClick="searchFacet(\''+facets.name+'\',\''+result.label+'\')">'+result.label+' ('+result.count+')</a></li>';
	          });
	      	content += '</ul></li>'
	      	//alert(content);
	      });
	      content += '</ul>';
	      //alert(content);
	   	$('#facetdiv',parent.frames["leftFrame"].document.body).html(content);
    }
});

</script>
<body>
<div align="center">
<table cellpadding="5" cellspacing="0" border="0" width="100%">
	<tr><td align="center"><font size="6">Movie Library</font></td>
	</tr>
	<tr>
			<td align="right">
					<input type="hidden" name="start" id="start" value="1"/>
			 		<input type="text" id="query" size="25" maxlength="256" />
		         	<input type="button" id="searchbutton" value="search"/>
			</td>
	    </tr>
</table>
</div>
</body>
</html>