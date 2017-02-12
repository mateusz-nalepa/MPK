function initMap() {
		var uluru = {lat: 50.879557, lng:20.639697 };
	
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 15,
          center: uluru
        });
        
        $.getJSON('/map/busstop', function(data) {
        	var i;
        
        	for(i=0;i<data.length;i++){        	
        		var myPosition = {lat: data[i].location.east, lng:data[i].location.north};
        		new google.maps.Marker({
        			position: myPosition,
        			map:map,
        			title: data[i].name
        		});
        	}
        });
        
        
        
      }
function delayMe(){
initMap();
}
//setTimeout(delayMe,1000);