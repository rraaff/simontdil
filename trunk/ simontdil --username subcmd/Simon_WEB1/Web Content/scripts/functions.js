/**
 * Functions repository, currently suports: IE, NS and Mozilla.
 * Author: Rodrigo Mendoza <rodrigo.mendoza@jwt.com>
 */


isDOM = (document.getElementById ? true : false);
isIE = (document.all ? true : false);

function MM_swapImgRestore(){
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages(){
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d){
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_swapImage(){
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_showHideLayers() {
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v='hide')?'hidden':v; }
    obj.visibility=v; }
}

function getLayerName(strLayerName) {
	var objLayerName;
	if (isDOM)
		objLayerName = document.getElementById(strLayerName).style;
	else if (isIE)
		objLayerName = document.all[strLayerName].style;
	else 
		objLayerName = document.layers[strLayerName];
	return objLayerName;
}

function getObjectName(strObjectName) {
	var objObjectName;
	if (isIE)
		objObjectName = document.all[strObjectName];
	else if (isDOM)
		objObjectName = document.getElementById(strObjectName);
	else{
		if (document[strObjectName]) 
			objObjectName = document[strObjectName];
		else{ 
			for (i = 0; i < document.layers.length; i++){
				if (document.layers[i].document[strObjectName]){
					objObjectName = document.layers[i].document[strObjectName];
					break;
				}
			}
		}
	}
	return objObjectName;
}

function setLayerPosition(objLayerName, strNamePositioner, intTop, intLeft) {
	var objNamePositioner;
	intTop = parseInt(intTop);
	intLeft = parseInt(intLeft);
	if (strNamePositioner){
		if (isIE){
			objNamePositioner = document.all[strNamePositioner];
		}else if (isDOM){
			objNamePositioner = document.getElementById(strNamePositioner);
		}else{
			if (document[strNamePositioner]) 
				objNamePositioner = document[strNamePositioner];
			else if (objLayerName.document[strNamePositioner])
				objNamePositioner = objLayerName.document[strNamePositioner];
			else{ 
				for (i = 0; i < document.layers.length; i++){
					if (document.layers[i].document[strNamePositioner]){
						objNamePositioner = document.layers[i].document[strNamePositioner];
						break;
					}
				}
			}
		}
		objLayerName.top = parseInt(getAbsY(objNamePositioner)) + intTop;
		objLayerName.left = parseInt(getAbsX(objNamePositioner)) + intLeft;
	}else{
		objLayerName.top = intTop;
		objLayerName.left = intLeft;
	}
}

function getAbsX(objElement){
	if (!(isDOM) && !(isIE)){
		var strNombreElemento = objElement.name;
		if (!document[strNombreElemento]){
			for (i = 0; i < document.layers.length; i++){
				if (document.layers[i].document[strNombreElemento]){
					objElement = document.layers[i].document[strNombreElemento];
					objLayer = document.layers[i];
					break;
				}
			}
			return (objLayer.x + objElement.x);
		}else
			return ((objElement.x) || (parseInt(objElement.x) == 0)) ? objElement.x : getAbsPos(objElement, "Left");
	}else
		return ((objElement.x) || (parseInt(objElement.x) == 0)) ? objElement.x : getAbsPos(objElement, "Left");
}

function getAbsY(objElement){
	if (!(isDOM) && !(isIE)){
		var strNombreElemento = objElement.name;
		if (!document[strNombreElemento]){
			for (i = 0; i < document.layers.length; i++){
				if (document.layers[i].document[strNombreElemento]){
					objElement = document.layers[i].document[strNombreElemento];
					objLayer = document.layers[i];
					break;
				}
			}
			return (objLayer.y + objElement.y);
		}else
			return ((objElement.y) || (parseInt(objElement.y) == 0)) ? objElement.y : getAbsPos(objElement,"Top");
	}else
		return ((objElement.y) || (parseInt(objElement.y) == 0)) ? objElement.y : getAbsPos(objElement,"Top");
}

function getAbsPos(objElement,which) {
	iPos = 0;
	while (objElement != null) {
		iPos += objElement["offset" + which];
		objElement = objElement.offsetParent;
	}
	return iPos;
}

function setClippingArea(objLayerScan, intTop, intRight, intBottom, intLeft){

	if (document.layers){
		intTop = parseInt(intTop);
		intLeft = parseInt(intLeft);
		intBottom = parseInt(intBottom);
		intRight = parseInt(intRight);
		objLayerScan.clip.top = intTop;
		objLayerScan.clip.bottom = intBottom;
		objLayerScan.clip.left = intRight;
		objLayerScan.clip.right = intLeft;
	}else
		objLayerScan.clip = 'rect(' + intTop + ' ' + intLeft + ' ' + intBottom + ' ' + intRight + ')';
}

function centrarLayers(){
	var strArgs = centrarLayers.arguments;
	for (i = 0; i < (strArgs.length) - 2; i += 3){
		setLayerPosition(getLayerName(strArgs[i]), 'imgReferencia', strArgs[i+1], strArgs[i+2]);
		if(typeof strArgs[i+3] == "boolean"){
			if(strArgs[i+3]){
				MM_showHideLayers(strArgs[i], '', 'show');
				i++;
			}
		}else
			MM_showHideLayers(strArgs[i], '', 'show');
	}
}

function centrarLayersOcultos(){
	var strArgs = centrarLayersOcultos.arguments;
	for (i = 0; i < (strArgs.length) - 2; i += 3)
		setLayerPosition(getLayerName(strArgs[i]), 'imgReferencia', strArgs[i+1], strArgs[i+2]);
}

function getObjectFormInLayer(layerName,formName){
	if(isIE || isDOM)
		return eval('document.' + formName);
	else
		return eval('document.' + layerName + '.document.' + formName);
}

String.prototype.trim = function (){
	return this.ltrim().rtrim();
}

String.prototype.ltrim = function (){
	var i = 0;
	for(i = 0; i < this.length; i++){
		if(this.charAt(i) != ' ')
			break;
	}
	return this.substring(i, this.length);
}

String.prototype.rtrim = function (){
	var i = 0;
	for(i = this.length; i > 0; i--){
		if(this.charAt(i) != ' ')
			break;
	}
	return this.substring(0, i);
}