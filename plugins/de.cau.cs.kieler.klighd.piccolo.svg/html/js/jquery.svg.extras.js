$.fn.addDraggable = function() {

    // add it to all elements
    $(this).each(function() {

        $(this).hover(function() {
            $(this).css("cursor", "pointer");
        }, function() {
            $(this).css("cursor", "default");
        });

        // listen to drag init event to register the starting translation
        $(this).drag("init", function(event, dd) {

            // get the svg
            var svg = $(this).svg('get');
            var g = svg.getElementById('group');

            // extract the old translation
            var oldTranslate = g.getAttribute('transform');
            var parts = /translate\(\s*([^\s,)]+)[\s*,\s*](\s*[^\s,)]+)/.exec(oldTranslate);

            var firstX = parts ? parseInt(parts[1]) : 0;
            var firstY = parts ? parseInt(parts[2]) : 0;

            // attach them to the current svg object
            svg._oldTransX = firstX;
            svg._oldTransY = firstY;

            // return the object to continue drag action
            return this;
        });

        // listen to the actual dragging
        $(this).drag(function(event, dd) {

            // get the svg
            var svg = $(this).svg('get');
            var g = svg.getElementById('group');

            // get the current scale
            var transform = g.getAttribute('transform');
            var scale = /scale\([0-9|\.|\s]*\)/.exec(transform);

            // get the targets offsets to adapt the drag offset
            var targetOffset = $(dd.target).offset();

            // adjust the drag offsets depending on the current scale
            var xOffset = dd.offsetX / (g._scale || 1);
            var yOffset = dd.offsetY / (g._scale || 1);
            var left = targetOffset.left / (g._scale || 1);
            var top = targetOffset.top / (g._scale || 1);
            ;

            var xTrans = xOffset + svg._oldTransX - left;
            var yTrans = yOffset + svg._oldTransY - top;

            var translate = 'translate(' + xTrans + ', ' + yTrans + ')';
            g.setAttribute('transform', (scale || "") + " " + translate);
            // fire a change event
            $('g#group').change();
        });
        
        
        $(this).drag("end", function() {
            // get the svg
            var svg = $(this).svg('get');
            var g = svg.getElementById('group');

            // extract the old translation
            var oldTranslate = g.getAttribute('transform');
            var parts = /translate\(\s*([^\s,)]+)[\s*,\s*](\s*[^\s,)]+)/.exec(oldTranslate);

            var firstX = parts ? parseInt(parts[1]) : 0;
            var firstY = parts ? parseInt(parts[2]) : 0;

            // attach them to the current svg object
            svg._oldTransX = firstX;
            svg._oldTransY = firstY;

            // return the object to continue drag action
            return this;
        });
    });
};

$.fn.addScalability = function() {

    $(this).each(function() {

    	// add mouse wheel scaling to all elements
        $(this).bind('mousewheel', function(event, delta) {
            var svg = $(this).svg('get');
            var g = svg.getElementById('group');
            
            if (!g._scale) {
                g._scale = 1;
            }
            
            // get the current translate
            var transform = g.getAttribute('transform');
            var translate = /translate\(.*\)/.exec(transform);

            var oldScale = g._scale;
            
            if (delta > 0) {
                // zoom in
                g._scale += 0.1;
            } else {
                // zoom out
                g._scale -= 0.1;
                if (g._scale <= 0.5) {
                    g._scale = 0.5;
                }
            }
            
            // get the mouse position
            var parentOffset = $(this).offset(); 
            var relX = event.pageX - parentOffset.left;
            var relY = event.pageY - parentOffset.top;
            
            // adapt the translate
            var offsetX = -( relX / oldScale + ((-svg._oldTransX) || 0) - relX / g._scale  ) ;
            var offsetY = -( relY / oldScale + ((-svg._oldTransY ) || 0) - relY / g._scale  );

            /*$('#xc').text(svg._oldTransX + " " + offsetX + "      ");
            $('#yc').text(svg._oldTransY + " " + offsetY);*/
            
            // set the new translate
            translate = "translate(" + offsetX +", "+offsetY+")";
            svg._oldTransX = offsetX;
            svg._oldTransY = offsetY;
            
            // set the new transform
            g.setAttribute('transform', 'scale(' + g._scale + ')' + " " + (translate || ""));
            // fire a change event
            $('g#group').change();

            svg._scale = g._scale;
            // dont scroll the window itself, just the scrollable area
            $("html:not(:animated), body:not(:animated)").animate({
                scrollTop : 0
            }, 500);

            return true;
        });
    });
};

