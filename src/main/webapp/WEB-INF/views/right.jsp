<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<script src="../resources/js/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="../resources/css/styles.css" />
<script>
$(document).ready(function() {
	
	$.fn.setFrameContent = function(result) {
    	var responseObj = $.parseJSON(JSON.stringify(result));
   	 // Set Right Frame Content
   	 var total_rows = responseObj.total;
   	 var includetvseries = responseObj.includetvseries;
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
        	 rightFrameContent += '<td><a href="#" onClick="getNextPage(\''+count+'\',\''+encodeURIComponent(queryCriteria)+'\','+includetvseries+')">'+count+'</a></td>';
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
function getNextPage(count, queryCriteria, includetvseries){
	
	queryCriteria = decodeURIComponent(queryCriteria);
	var url="/searchText";
	if(queryCriteria.length === 0 ){
		url="/getAllMovies"
	}
	$.ajax({
        type: "GET",
        url: url,
        data: {
        	query: queryCriteria,
        	start: count,
        	includetvseries:includetvseries
        },
        success: function(result){
        	$.fn.setFrameContent(result);
        }
   });
}

function getMovieDetails(uri){
	$.ajax({
        type: "GET",
        url: "/getMovieDetails",
        data: {
        	uri: uri
        },
        success: function(result){
        	// CLear the dialog div before loading
        	$('#moviedialog').empty();
        	var responseObj = $.parseJSON(result);
        	var dialogMessage = '<div align="center"><img src="'+responseObj.Poster+'" alt="Movie Poster" hspace="20" height="100" width="80"></div>';
        	dialogMessage += '<table cellspacing="10" style=\'font-family:"Courier New", Courier, monospace; font-size:80%\' width: 100%;>';
        	dialogMessage += '<tr><td class="blue">Title</td><td>'+responseObj.Title+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Released</td><td>'+responseObj.Released+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Runtime</td><td>'+responseObj.Runtime+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Genre</td><td>'+responseObj.Genre+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Director</td><td>'+responseObj.Director+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Writer</td><td>'+responseObj.Writer+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Actors</td><td>'+responseObj.Actors+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Plot</td><td>'+responseObj.Plot+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Language</td><td>'+responseObj.Language+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Production</td><td>'+responseObj.Production+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Awards</td><td>'+responseObj.Awards+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Website</td><td>'+responseObj.Website+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">Type</td><td>'+responseObj.Type+'</td></tr>';
        	dialogMessage += '<tr><td class="blue">imdbRating</td><td>'+responseObj.imdbRating+'</td></tr></table>';
			$(dialogMessage).appendTo('#moviedialog');
        	// Show Dialog
        	$("#moviedialog").dialog({
        	      modal: true,
        	      create: function (event, ui) {
        	          $(".ui-widget-header").hide();
        	          $("body").css({ overflow: 'hidden' });
        	      },
        	      open: function(event, ui) {
        	          $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
        	      },
        	      show: {
        	          effect: "blind",
        	          duration: 600
        	      },
        	      hide: {
        	          effect: "explode",
        	          duration: 600
        	      },
        	      height: 500,
        	      width: 800,
        	      buttons: {
        	        Ok: function() {
        	          $( this ).dialog( "close" );
        	        }
        	      }
        	});
        }
   });
}
</script>
<div class="contentdiv" id="contentdiv" align="center">
</div>
<div id="moviedialog">

</div>
</html>