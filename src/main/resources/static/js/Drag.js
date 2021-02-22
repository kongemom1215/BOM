//----------------------------------------------
//Drag v1.1 Source By Bermann
//dobermann75@gmail.com
//----------------------------------------------

//new Drag(ObjectAttributeName , DragElement , DragAreaNode , DragDirection[0 - Both , 1 - Horizontal , 2 - Vertical] , ScalePercentageAttributeName);

function Drag(Name,Dragelement,Dragarea,Direction,ScalePercent)
{
	this.Name = Name;
	this.Dragelement = Dragelement;
	this.Dragarea = Dragarea;
	this.Direction = Direction;
	this.ScalePercent = ScalePercent;
	this.DragMode = false;
	this.Element = null;
	this.Collection = [];
	this.Body = document.documentElement;
	this.Limit = null;
	this.LimitStartX = 0;
	this.LimitEndX = 0;
	this.LimitStartY = 0;
	this.LimitEndY = 0;
	this.ScaleX = 0;
	this.ScaleY = 0;
	this.setX = 0;
	this.setY = 0;
	if (window.attachEvent) {
		this.addEvent = function (Element,Handle,Action) { return Element.attachEvent(Handle,Action); }
	} else if (window.addEventListener) {
		this.addEvent = function (Element,Handle,Action) { return Element.addEventListener(Handle.replace(/^on/i,""),Action,false); }
	}
	this.Run();
}

Drag.prototype =
{
	MoveX : function (PositionX) {
		if (this.Limit != null) {
			if (this.LimitStartX >= PositionX) { this.Element.style.left = this.LimitStartX + "px"; }
			else if (this.LimitEndX <= (PositionX + this.Element.offsetWidth)) { this.Element.style.left = (this.LimitEndX - this.Element.offsetWidth) + "px"; }
			else { this.Element.style.left = PositionX + "px"; }
			if (this.Element.getAttribute(this.ScalePercent)) {
				var ScalePercent = Number(this.Element.getAttribute(this.ScalePercent));
				this.ScaleX = Math.ceil((this.Element.offsetLeft - this.LimitStartX) * (ScalePercent / (this.LimitEndX - this.LimitStartX - this.Element.offsetWidth)));
			}
		} else {
			this.Element.style.left = PositionX + "px";
		}
	},
	MoveY : function (PositionY) {
		if (this.Limit != null) {
			if (this.LimitStartY >= PositionY) { this.Element.style.top = this.LimitStartY + "px"; }
			else if (this.LimitEndY <= (PositionY + this.Element.offsetHeight)) { this.Element.style.top = (this.LimitEndY - this.Element.offsetHeight) + "px"; }
			else { this.Element.style.top = PositionY + "px"; }
			if (this.Element.getAttribute(this.ScalePercent)) {
				var ScalePercent = Number(this.Element.getAttribute(this.ScalePercent));
				this.ScaleY = Math.ceil((this.Element.offsetTop - this.LimitStartY) * (ScalePercent / (this.LimitEndY - this.LimitStartY - this.Element.offsetHeight)));
			}
		} else {
			this.Element.style.top = PositionY + "px";
		}
	},
	Run : function () {
		var This = this;
		this.addEvent(this.Body,"onmousedown",function (event) {
			if (event.button == 0 || event.button == 1) {
				This.Element = event.target || event.srcElement;
				This.DragMode = (This.Element.getAttribute(This.Name) == "true") ? true : false;
				if (This.DragMode == true) {
					if (This.Element.getAttribute(This.Dragelement)) { This.Element = eval(This.Element.getAttribute(This.Dragelement)); }
					//addCollection
					for (var i = 0; i < This.Collection.length; i++) { if (This.Element === This.Collection[i]) { break; } }
					if (i == This.Collection.length) {
						This.Element.style.zIndex = This.Collection.length + 1;
						This.Collection.push(This.Element);
						This.Element.style.position = "absolute";
					}
					//addCollection
					//reset z-Index
					var tmpElement = [];
					var tmpzIndex = This.Collection.length - 1;
					for (var i = tmpzIndex; i >= 0; i--) { tmpElement[This.Collection[i].style.zIndex - 1] = This.Collection[i]; }
					for (var i = tmpzIndex; i >= 0; i--) { tmpElement[i].style.zIndex = (tmpElement[i] === This.Element) ? tmpElement.length : tmpzIndex--; }
					//reset z-Index
					if (This.Element.getAttribute(This.Dragarea)) {
						This.Limit = eval(This.Element.getAttribute(This.Dragarea));
						This.LimitStartX += This.Limit.offsetLeft;
						This.LimitEndX += This.LimitStartX + This.Limit.clientWidth;
						This.LimitStartY += This.Limit.offsetTop;
						This.LimitEndY += This.LimitStartY + This.Limit.clientHeight;
					}
					This.setX = event.clientX - This.Element.offsetLeft;
					This.setY = event.clientY - This.Element.offsetTop;
				}
			}
		});
		this.addEvent(this.Body,"onmousemove",function (event) {
			if (This.DragMode == true) {
				var PositionX = event.clientX - This.setX;
				var PositionY = event.clientY - This.setY;
				switch (This.Element.getAttribute(This.Direction)) {
					case "0" :
						This.MoveX(PositionX);
						This.MoveY(PositionY);
						break;
					case "1" :
						This.MoveX(PositionX);
						break;
					case "2" :
						This.MoveY(PositionY);
						break;
					default :
						This.MoveX(PositionX);
						This.MoveY(PositionY);
				}
			}
		});
		this.addEvent(this.Body,"onmouseup",function () {
			This.DragMode = false;
			This.Element = null;
			This.Limit = null;
			This.LimitStartX = 0;
			This.LimitEndX = 0;
			This.LimitStartY = 0;
			This.LimitEndY = 0;
			This.setX = 0;
			This.setY = 0;
		});
	}
}