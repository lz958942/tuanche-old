
//左侧树形菜单
function treeMenu(){
	
	if(!document.getElementById("treeview")) return false;
	var ul=document.getElementById('treeview').getElementsByTagName('ul');
	var b=document.getElementById('treeview').getElementsByTagName('b');
	
	for(var i=0;i<ul.length;i++){
		 if(ul[i].lastChild.nodeName.toLowerCase()=='li'){
				ul[i].lastChild.className='er';
		 }
		 if(ul[i].lastChild.nodeName.toLowerCase()!='li'){
				ul[i].lastChild.previousSibling.className='er';
		 }
	 }
	 for(var i=0;i<b.length;i++){
		 b[i].onclick=function(){
			if(!this.parentNode.getElementsByTagName("ul")[0]) return false ;
			var ul_xiao = this.parentNode.getElementsByTagName("ul")[0];
			if(ul_xiao.className=="er") {
				ul_xiao.className="";this.className=""
			}
			else{
				ul_xiao.className="er";this.className="er"
			};
			}
		 
	}
}
//按钮切换
function setFocus2(idName,num,count){
	for(var i=1;i<=count;i++){
		if(i==num){	document.getElementById(idName+i).className="er";}
		else document.getElementById(idName+i).className="";
	}
}
//按钮切换
function setFocus(menuName,boxName,num,count){
	for(var i=1;i<=count;i++){
		if(i==num){	
			document.getElementById(menuName+i).className="er";
			document.getElementById(boxName+i).style.display="block";
		}
		else {
			document.getElementById(menuName+i).className="";
			document.getElementById(boxName+i).style.display="none";
		}
	}
}




//滑动效果
function setFocus3(menuName,boxName,num,count){
	for(var i=1;i<=count;i++){
		if(i==num){	
			document.getElementById(menuName+i).className="er";
			document.getElementById(boxName+i).style.display="block";
		}
		else {
			document.getElementById(menuName+i).className="";
			document.getElementById(boxName+i).style.display="none";
		}
	}
}
//权限管理
if(typeof(HTMLElement) != 'undefined'){
	HTMLElement.prototype.contains = function(o){
		while(o != null){
			if(o == this){
				return true;
			}
			o = o.parentNode;
		}
		return false;
	}
}
function aa1(){
	document.getElementById("Y1").style.display = "";
}
function closediv(e){
	var e = window.event || e;
	var s = e.toElement || e.relatedTarget;
	if(!this.contains(s)){
		this.style.display = "none";
		document.all.divpassword.style.display="none";
	}
}
function aa2(){
	document.getElementById("Y2").style.display = "";
}

function aa3(){
	document.getElementById("Y3").style.display = "";
}

function aa4(){
	document.getElementById("Y4").style.display = "";
	document.all.divpassword.style.display="";
}

//权限管理
function showqx(){
	lis = new Array();
	var quxian = document.getElementById("quxian");
	var site = nav.getElementsByTagName("div");
	for(var i =0;i<site.length;i++){
		if(li[i].getElementsByTagName("span").length>0){ lis.push(li[i])}
	}
	for(var i =0;i<lis.length;i++){
		if(lis[i].className!="er"){
			lis[i].onmouseover=function(){
				this.className="er";this.getElementsByTagName("div")[0].style.display="block";
			}
			lis[i].onmouseout=function(){
				this.className="";this.getElementsByTagName("div")[0].style.display="none";
			}
		}
		if(lis[i].className=="er"){
			lis[i].onmouseover=function(){
				this.getElementsByTagName("div")[0].style.display="block";
			}
			lis[i].onmouseout=function(){
				this.getElementsByTagName("div")[0].style.display="none";
			}
		}
		
	}
}
function   setcol(td,keyword)   
  {   
  var   table   =   td.parentNode.parentNode;  
  var   col   =   td.cellIndex;  
  
  for(var   i=0;i<table.rows.length;i++)   
  {   
  var   tr   =   table.rows[i];  
  for(var   j=0;j<tr.cells.length;j++)   
  {   
  if(j==col)   
  {   
  tr.cells[j].bgColor   =   "C7CCDAF";   
  }   
  else   
  {   
  tr.cells[j].bgColor   =   "#ffffff";   
  }   
  }   
  }   
    document.getElementsByName("keyword")[0].value=keyword
  }   
 
 
 var aTDClick=new Array(3);
function onColor(td,n)
{
    if(!aTDClick[n-1]){
		td.style.backgroundColor='#FFFF99';
    	td.style.color='#0600FF';
	}else{
		td.style.backgroundColor='#FF6699';
    	td.style.color='#0600FF';
	}
}

function offColor(td,n)
{
    if(!aTDClick[n-1]){
		td.style.backgroundColor='';
    	td.style.color='';
	}else{
		td.style.backgroundColor='#FF6699';
    	td.style.color='#0600FF';
	}
}



 function checkMonth(objname){//判断月份是否填写正确
	
		var tempname = objname;
		var y,m;
		var outdata;
		var datename = document.getElementById(tempname);
		
		if(datename.value!='')
		{
		if(datename.value.length <5||datename.value.length >7){
		alert("输入日期格式不正确，\n请按照YYYY-MM的格式输入!");
		//datename.value = "";
		datename.focus();
		return false;
		}else{
		var va=datename.value;
		var va_=va.indexOf('-');
		if(va_!=-1)
		{
		y = va.split('-')[0];
		if(y.length==1){
		    y='0'+y;
		}
		m = va.split('-')[1];
		if(m.length==1){
		    m='0'+m;
		}
		}
		else
		{
		y = datename.value.substring(0,4);
		m = datename.value.substring(4,6);
		}
		outdata = y+"-"+m;
		if(!(m.length==2)){
		     alert("输入日期不正确,请按照YYYY-MM的格式输入。");
		     return false;
		}
		if(datename.value != ""){
		if(!isDate1( outdata ,"yyyy-MM")){
		alert("输入日期不正确,请按照YYYY-MM的格式输入。");
		datename.focus();
		
		return false;
		}
		else
		{
		datename.value=outdata;
		}
		}
		}
		}
		}
		function isDate1( date, fmt ) { 
		if (fmt==null) fmt="yyyyMM"; 
		var yIndex = fmt.indexOf("yyyy"); 
		if(yIndex==-1) return false; 
		var year = date.substring(yIndex,yIndex+4); 
		var mIndex = fmt.indexOf("MM"); 
		if(mIndex==-1) return false; 
		var month = date.substring(mIndex,mIndex+2); 
		if(isNaN(year)||year>"3000" || year< "1900") return false; 
		
		if(isNaN(month)||month>"12" || month< "01") return false; 
		return true; 
	} 
	
      function validate(sDouble)
      {
          if(sDouble.value!=null&&sDouble.value!=""){
           if(isNaN(sDouble.value)) {
            sDouble.focus();
            return false;
            }else{
              var   bignum='9999999.9';
             if(sDouble.value>=0){
                if(parseFloat(sDouble.value)>parseFloat(bignum)){
                   return  false;
                }
                 return  true;
              }else{
                 return  false;
              }
             
            }
            }else{
               return  true;
            }
      }
      
      function checkDate(objname){
	
		var tempname = objname;
		var y,m,d;
		var outdata;
		var datename = document.getElementById(tempname);
		if(datename.value!='')
		{
		if(datename.value.length <8||datename.value.length >10){
		alert("输入日期格式不正确，\n请按照YYYY-MM-DD的格式输入!");
		//datename.value = "";
		datename.focus();
		return false;
		}else{
		var va=datename.value;
		var va_=va.indexOf('-');
		if(va_!=-1)
		{
		y = va.split('-')[0];
		m = va.split('-')[1];
		if(m.length==1){
		    m='0'+m;
		}
		d = va.split('-')[2];
		if(d.length==1){
		    d='0'+d;
		}
		}
		else
		{
		y = datename.value.substring(0,4);
		m = datename.value.substring(4,6);
		d = datename.value.substring(6,8);
		}
		
		outdata = y+"-"+m+"-"+d;
		if(datename.value != ""){
		if(!isDate( outdata ,"yyyy-MM-dd")){
		alert("输入日期不正确,请重新填写。");
		datename.focus();
		return false;
		}
		else
		{
		datename.value=outdata;
		}
		}
		}
		}
		}
		function isDate( date, fmt ) { 
		if (fmt==null) fmt="yyyyMMdd"; 
		var yIndex = fmt.indexOf("yyyy"); 
		if(yIndex==-1) return false; 
		var year = date.substring(yIndex,yIndex+4); 
		var mIndex = fmt.indexOf("MM"); 
		if(mIndex==-1) return false; 
		var month = date.substring(mIndex,mIndex+2); 
		var dIndex = fmt.indexOf("dd"); 
		if(dIndex==-1) return false; 
		var day = date.substring(dIndex,dIndex+2); 
		if(isNaN(year)||year>"2100" || year< "1900") return false; 
		if(isNaN(month)||month>"12" || month< "01") return false; 
		if(day>getMaxDay(year,month) || day< "01") return false; 
		return true; 
	} 
	function getMaxDay(year,month) { 
          if(month==4||month==6||month==9||month==11) 
             return "30"; 
            if(month==2) 
            if(year%4==0&&year%100!=0 || year%400==0) 
             return "29"; 
             else 
            return "28"; 
            return "31"; 
        } 
    
    function  checkInteger(intnumber){
        var re = /^[0-9]+[0-9]*]*$/;
         if (!re.test(intnumber)){
            return false;
         }else{
            return  true;
         }
    
    }
   

  function   checkPageNum(cournum){
      var cpage=  document.getElementById("cpage").value;
       var  flag=true;
       if(cpage==""){
           alert("请输入页数！");
           return  false;
       }
     if(isNaN(cpage)){
         alert("请输入正确的页数！");
        flag=false;
     }else{
        var exp   =   /^([1-9][0-9]*)$/; 
        if(!(exp.test(cpage))){
            alert("请输入正确的页数！");
           flag=false;
        }else{
              if(parseInt(cpage)>parseInt(cournum)){
                   alert("输入的页数不能大于总页数");
                   flag=false;
              }
        }
     }
      if(flag==true){
             document.getElementById("pageForm").submit();
        }
     
 }
