/* class */
var TabSet = new Class({
	options: {
		activeClass: 'active', //css class
		startIndex: 0 //start with this item if no cookie or active
	},
	Implements: [Options,Events],
	initialize: function(tabs,contents,options) {
		//handle arguments
		this.setOptions(options);
		this.tabs = $$(tabs);
		this.contents = $$(contents);
		//determine the "active" tab
		var active = this.options.startIndex;
		this.activeTab = this.tabs[active].addClass(this.options.activeClass);
		this.activeContent = this.contents[active].setStyle('height','auto');
		//process each tab and content
		this.tabs.each(function(tab,i) {
			this.processItem(tab,this.contents[i],i);
		},this);
		//tabs are ready -- load it!
		this.fireEvent('load');
	},
	processItem:function(tab,content,i) {
		var contentHeight = content.getScrollSize().y;
		//add a click event to the tab
		tab.addEvent('click',function(e) {
			//stop!
			if(e) e.stop();
			//if it's not the active tab
			if(tab != this.activeTab) {
				//remove the active class from the active tab
				this.activeTab.removeClass(this.options.activeClass);
				//make the clicked tab the active tab
				(this.activeTab = tab).addClass(this.options.activeClass);
				//tween the old tab content up
				//tween the new content down
				this.activeContent.set('tween',{
					onComplete:function() {
						this.activeContent = content.fade('in').set('tween',{ onComplete: $empty }).tween('height',contentHeight);
						//fire the tab change event
						this.fireEvent('change',[tab,content]);
					}.bind(this)
				}).setStyles({
					height: contentHeight,
					overflow: 'hidden'
				}).fade('out').tween('height',0);
			}
		}.bind(this));
	}
});