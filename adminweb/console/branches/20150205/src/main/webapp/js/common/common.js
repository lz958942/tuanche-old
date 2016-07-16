String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g,"");
	};
function openImgWindow(url){
	window.open(url, "img", "scrollbars=yes");
}
function checkAll(obj){
	var ids = document.getElementsByName("ids");
	for(var i = 0;i<ids.length;i++){
			ids[i].checked = obj.checked;
	}
}
function postform(url,params){
	var temp = document.createElement("form");
	temp.action = url;
	temp.method= "post";
	temp.style.display = "none";
	for(var x in params) {
		var opt = document.createElement("textarea");
		opt.name = x;
		opt.value = params[x];
		temp.appendChild(opt);
	}
	document.body.appendChild(temp);
	temp.submit();
}
function display(boxid){
	var box  = document.getElementById(boxid);
	if(box.style.display!="block"){
		box.style.display="block"; 
	}
  }
function disappear(boxid){
	var box  = document.getElementById(boxid);
	if(box.style.display!="none"){
		box.style.display="none"; 
	}
 }
