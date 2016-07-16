// 导航栏配置文件
var outlookbar=new outlook();
var t;
t=outlookbar.addtitle('基本信息','ESS',1)
outlookbar.additem('个人资料',t,'/oa/servlet/UserMessageServlet?action=findBaseMessage&findscope=fromsession')
outlookbar.additem('部门及职位信息',t,'/oa/servlet/UserMessageServlet?action=findUserDept&findscope=fromsession')
outlookbar.additem('考核信息',t,'/oa/servlet/UserMessageServlet?action=findUserAssess&findscope=fromsession')
outlookbar.additem('销售试用期考核信息',t,'/oa/servlet/UserMessageServlet?action=findUserSyExam&findscope=fromsession')
outlookbar.additem('奖惩信息',t,'/oa/servlet/UserMessageServlet?action=findUserRb&findscope=fromsession')



t=outlookbar.addtitle('通讯录','people',1)
outlookbar.additem('员工通讯录',t,'22');


t=outlookbar.addtitle('业务信息','people',1)
outlookbar.additem('已申请业务',t,'/usermessage/ListMainIframe.jsp?url=15')
outlookbar.additem('已审批业务',t,'/usermessage/ListMainIframe.jsp?url=16')

t=outlookbar.addtitle('考勤管理','CHILD',1)
outlookbar.additem('请假信息',t,'/usermessage/ListMainIframe.jsp?url=17')
outlookbar.additem('考勤统计',t,'/usermessage/ListMainIframe.jsp?url=18')