/*
 * jQuery UIx Tree
 *
 * Authors:
 *  Yanick Rochon (yanick.rochon[at]gmail[dot]com)
 *
 * Licensed under the MIT (MIT-LICENSE.txt) license.
 *
 * http://mind2soft.com/labs/jquery/tabs/
 *
 *
 * Depends:
 * jQuery UI 1.9+
 *
 */

(function( $, undefined ) {


$.widget("uix.tree", {
    options: {
        icons: {                          // object : define the node icons
            expanded: "ui-icon-triangle-1-se",    // when the node is expanded (default: "ui-icon-triangle-1-se")
            collapsed: "ui-icon-triangle-1-e"     // when the node is collapsed (default: "ui-icon-triangle-1-e")
        },
        hide: "slideUp",                  // boolean, number, string or object : define the default hide and show behaviour
        show: "slideDown",                //    see : http://api.jqueryui.com/jQuery.widget/#option-hide
        toggleMode: "dblclick",           // "click", "dblclick", etc. : set the node toggle mode (default: "click")
        checkTree: false                  // boolean : define if each tree item should have a check box to determine selection
    },

    _create: function() {
        this._bindNavigation();
        this._buildTree();
    },

    _createUI: function(nodes) {
        return {
            nodes: nodes
        };
    },

    _bindNavigation: function() {
        this._on( this.element, {
            'keydown a:focus': function (evt) {
                var _dir;
                switch (evt.which) {
                    case 37: _dir = "Left"; break;
                    case 38: _dir = "Up"; break;
                    case 39: _dir = "Right"; break;
                    case 40: _dir = "Down"; break;
                }
                if (_dir) {
                    evt.stopPropagation();
                    evt.preventDefault();
                    this["_move" + _dir]($(evt.target));
                }
            }
        });
    
    },
    
    _buildTree: function() {
        this._createNodesRecursive( this.element.attr("role", "tree").addClass("uix-tree ui-widget ui-widget-content ui-corner-all").children("li"), 1 );

        var anchors = this.element.find("li > a:only-child")
            .parent().addClass("uix-tree-leaf").end()
            .attr("role", "presentation").addClass("ui-corner-all").uniqueId()
            .children("span.ui-icon").parent().css('padding-left', "18px").end().end()
        
        if (this.options.checkTree) {
            anchors.addClass("uix-checked").before( $("<input type=\"checkbox\" />") );
        }

        this._focusable( anchors );
        this._hoverable( anchors );
        this._on( anchors, {
            "mousedown": function (evt) { this._selectNode($(evt.currentTarget)); return false; },
            'click': function (evt) { evt.stopPropagation(); evt.preventDefault(); }, // leaf
            'keypress': function (evt) { if (evt.keyCode === 32) this._activateNode($(evt.currentTarget), !$(evt.currentTarget).prev().prop("checked")); }
        } );
        this._on( this.element.find("span.uix-tree-node-handle"), {
            "click": function(evt) { this._toggleNode( $(evt.currentTarget) ); }
        } );
        
        this._on(this.element.find(":input[type='checkbox']"), {
            "click": function (evt) { this._activateNode($(evt.currentTarget).next(), $(evt.currentTarget).is(":checked")); }
        });
    },

    _createNodesRecursive: function( base, level ) {
        var handle = $("<span></span>").addClass("uix-tree-node-handle ui-icon " + this.options.icons.collapsed);

        var nodes = base.attr("aria-level", level).children("ul").hide().attr("role", "group")
            .parent().attr("aria-expanded", false).append( handle ).end().children("li");

        if (nodes.length) {
            var anchors = base.attr("role", "treeitem").addClass("uix-tree-node")
                .children("a:not(:only-child)").attr("role", "presentation").addClass("ui-corner-all").uniqueId()
                .children("span.ui-icon").parent().css('padding-left', "18px").end().end();

                
            if (this.options.checkTree) {
                anchors.addClass("uix-checked").before( $("<input type=\"checkbox\" />") );
            }
            
            this._focusable( anchors );
            this._hoverable( anchors );
            
            var events = {
                'mousedown': function (evt) { this._selectNode($(evt.currentTarget)); return false; },
                'click': function (evt) { evt.stopPropagation(); evt.preventDefault(); }, // node
                'keypress': function (evt) { if (evt.keyCode === 32) this._activateNode($(evt.currentTarget), !$(evt.currentTarget).prev().prop("checked")); }
            };
            events[this.options.toggleMode] = function(evt) { this._toggleNode( $(evt.currentTarget) ); };

            this._on( anchors, events );
            
            this._createNodesRecursive( nodes, level + 1 );
        }
    },

    _selectNode: function(node) {
        var ui = this._createUI(node);
        var evt = $.Event("select");

        this._trigger("beforeSelect", evt, ui);

        if (!evt.isDefaultPrevented()) {
            node.focus();
            this._trigger("select", evt, ui);
            return true;
        }
        
        return false;
    },

    _toggleNode: function (node, state) {
        var ui = this._createUI(node);
        var evt = $.Event("toggle");

        var container = node.closest("li");
        var groupContainer = container.children("ul");
        var expanded = groupContainer.children("li").is(":visible");

        if (!groupContainer.children("li").length) return state === expanded;
        if (state !== undefined && state === expanded) return true;
        state = !expanded;
        
        this._trigger("beforeToggle", evt, ui);

        if (!evt.isDefaultPrevented()) {
            container.children("span.uix-tree-node-handle").toggleClass(this.options.icons.collapsed + " " + this.options.icons.expanded);
            
            var nodeSelected = groupContainer
                .find(".ui-state-focus").length > 0;

            container.attr("aria-expanded", expanded);

            if (groupContainer.children("li").length) this[expanded ? "_hide" : "_show"]( groupContainer, this.options[expanded ? "hide" : "show"] );

            this._trigger("toggle", evt, ui);

            if (nodeSelected) this._selectNode(node);
            
            return groupContainer.is(":visible") == state;
        }
        
        return false;
    },

    _activateNode: function (node, state/*, direction */) {
        state = !!state;  // to bool
        if (!(node.hasClass(".ui-state-disabled") && node.prev().prop("disabled"))) {
            node[(state ? "add" : "remove") + "Class"]("ui-state-active")
                        .prev().prop("checked", state);

            this._trigger("node" + (state ? "" : "de") + "activate", null, this._createUI(node));
        }

        if (arguments[2] !== "up") {
            var children = node.closest("li").children("ul").children("li").children("a");
            if (children.length)
                this._activateNode(children, state, "down");
        }
        if (arguments[2] !== "down") {
            var parent = node.closest("li").closest("ul").closest("li").children("a");
            if (parent.length) {
                // test if all node's siblings are also in the same state
                var siblings = node.closest("li").siblings("li").children("a:first").add(node);
                var allSelected = siblings.length === siblings.filter(".ui-state-active").length;

                this._activateNode(parent, allSelected, "up");
            }
        }
    },

    _moveLeft: function(node) {
        if ( !this._toggleNode( node, false ) ) return;
        var nextNode = node.closest("li").closest("ul").closest("li").children("a:first");
        if (nextNode.length) 
            this._selectNode( nextNode );
    },
    
    _moveUp: function(node) {
        var nextNode = node.closest("li").prev("li");
        if (!nextNode.length)
            this._moveLeft( node );
        else { 
            if ( nextNode.find("a:visible:last").length )
                this._selectNode( nextNode.find("a:visible:last") );
            else
                this._selectNode( nextNode.children("a:first") );
        }
    },
        
    _moveRight: function(node) {
        if (this._toggleNode(node, true)) {
            this._selectNode( node.closest("li").children("ul").children("li").children("a:first") );
        } else {
            this._moveDown( node );
        }
    },
    
    _moveDown: function(node) {
        if (node.closest("lI").children("ul:first").is(":visible")) {
            this._selectNode( node.closest("li").children("ul:first").children("li:first").children("a:first") );
        } else {
            var nextNode = node.closest("li").next("li").children("a");
            if (!nextNode.length) {
                var parent = node;
                
                while (!nextNode.length && parent.length && $.contains(this.element[0], parent[0])) {
                    parent = parent.closest("li").closest("ul").closest("li");
                    nextNode = parent.next("li").children("a:first");
                }
            }
            if (nextNode.length)
                this._selectNode( nextNode );
        }
    },
    
    _setOption: function( key, value ) {
        // TODO : implement this
    },

    _refresh: function() {
        // TODO : destroy and rebuild the whole tree ???
    },

    _destroy: function() {
        // TODO : remove ARIA, remove classes, remove all added nodes
    }

});


})(jQuery);
