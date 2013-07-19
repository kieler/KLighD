// init svg
$('#viewport').svg();
$('#viewport').addScalability();
$('#viewport').addDraggable();

/**
 * ----------------------------------------------------------------------------------------------------------
 * The WebSocket Connection
 */
var connection = null;

$('#connect').click(function() {

	var host = window.location.host;
	connection = new WebSocket('ws://' + host + '/', "protocol.svgws");

	// -- Open
	connection.onopen = function() {
		// currently nothing to do
	};

	// -- Log errors
	connection.onerror = function(error) {
		$('#messages').html('WebSocket Error ' + error);
	};

	// -- Received message from server
	connection.onmessage = function(e) {
		// set the svg
		$('#viewport').html(e.data);

		// attach a mousedown listener to handle expanding of hierarchical nodes
		var expandFun = function(d) {
			// get the id
			var text = this.textContent;
			// if starts with id
			if (text.indexOf("de.cau.cs.kieler.id:") === 0) {
				var id = text.substring(20, text.length);
				// send expand toggle command
				$.ajax({
					type : 'PUT',
					url : 'expand/' + id
				});

			}
		};
		d3.select("svg").selectAll("text").on("mousedown", expandFun);
		// for the browser
		d3.select("svg").selectAll("text").on("tap", expandFun);
	};

	// -- Try to connect
	$('#messages').html("Connecting ...");
	setTimeout(function() {
		if (connection.readyState === WebSocket.OPEN) {
			$('#messages').html("Connected to " + window.location.host + ".");
		} else {
			$('#messages').html("Could not connect to web socket.");
			connection = null;
		}
	}, 500);

});

/**
 * ----------------------------------------------------------------------------------------------------------
 * Initial Setup
 */

// executed
$(function() {

	// get the initial content
	$.ajax({
		type : 'GET',
		url : 'content/',
		success : function(res) {
			$('#data').append(res);

			// register listener if a file is clicked
			$('.file').click(function() {
				var path = $(this).attr("data-path");
				$.ajax({
					type : 'PUT',
					url : 'resource' + path,
					success : function(res) {
						// hide old errors
						$('#errors').hide();
					},
					error : function(err) {
						console.log("error:" + JSON.stringify(err));
						$('#errors').html(err.responseText);
						$('#errors').show();
					}
				});
			});

			/*
			 * TODO implement load on demand for folders
			$('.folder').click(function() {
				var path = $(this).attr("data-path");
				$.ajax({
					type : 'GET',
					url : 'folder' + path,
					success : function(res) {
						console.log("Folder: " + res);
					}
				});
			});*/

			// init tree
			$("#tree").tree();
			$("#tree_icons").tree({
				toggle : function(evt, ui) {
					var expanded = (ui.nodes.attr("aria-expanded") == "true");
					ui.nodes.children("a").children("span.ui-icon")
						.removeClass("ui-icon-folder-" + (expanded ? "collapsed" : "open"))
						.addClass("ui-icon-folder-"	+ (expanded ? "open" : "collapsed"));
				}
			});
		},
		error : function(e) {
			$('#errors').html(e);
		}
	});

});
