	var playListURL = 'http://en.wikipedia.org/w/api.php?format=json&action=query&titles=India&prop=revisions&rvprop=content&callback=?';
	
	$.getJSON(playListURL ,function(data) {
		var hash = data
        var page_value = ""
        $.each(data["query"]["pages"],function(k,v){
            alert(k)
            $.each(v,function(key,val){
              alert(key)
            });
        });
	});