(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[38],{"C/Fn":function(e,t,a){"use strict";var n=a("xK3H"),d=a("3rQ3");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("g1BC");var l=n(a("RvKu")),r=n(a("dnxz"));a("fhlT");var i=n(a("kXZy"));a("79JM");var o=n(a("DmMy"));a("Lzml");var u=n(a("vpm+"));a("/MhY");var p=n(a("w0+5")),s=n(a("SeLb"));a("XFd2");var c=n(a("33fr")),f=n(a("Sx57")),m=n(a("4zp0")),h=n(a("iVWQ")),g=n(a("lF+r")),v=n(a("PApH")),y=n(a("8g53"));a("Q3sr");var C,w,E,S=n(a("9NP+")),k=d(a("zLBQ")),b=a("h/XY"),D=n(a("I9Uw")),x=a("LneV"),M=n(a("D/dJ")),P=n(a("zHco")),z=n(a("cxoR")),I=S.default.Item,U=(C=(0,x.connect)(function(e){var t=e.department,a=e.loading;return{department:t,loading:a.effects["department/fetch"]}}),w=S.default.create(),C(E=w(E=function(e){function t(){var e,a;(0,f.default)(this,t);for(var n=arguments.length,d=new Array(n),l=0;l<n;l++)d[l]=arguments[l];return a=(0,h.default)(this,(e=(0,g.default)(t)).call.apply(e,[this].concat(d))),a.state={visible:!1,currentPage:1,pageSize:20,keyword:"",deptUuid:""},a.loadData=function(e,t,n,d){var l=a.props.dispatch;l({type:"department/fetch",payload:{page:e,size:t,keyword:d,deptUuid:n}})},a.reloadData=function(){var e=a.state,t=e.currentPage,n=e.pageSize,d=e.deptUuid,l=e.keyword;a.loadData(t,n,d,l)},a.showModal=function(){a.setState({visible:!0})},a.showEditModal=function(e){var t=a.props.dispatch;t({type:"department/getById",payload:{uuid:e}})},a.handleCancel=function(){var e=a.props.dispatch;e({type:"department/hideModel"}),a.setState({visible:!1})},a.handleDelete=function(e){var t=a.props.dispatch,n=a.state,d=n.currentPage,l=n.pageSize,r=n.deptUuid,i=n.keyword,o=(0,y.default)((0,y.default)(a));c.default.confirm({content:"\u786e\u8ba4\u8981\u5220\u9664\u5f53\u524d\u90e8\u95e8\u5417\uff1f",okText:"\u786e\u8ba4",okType:"danger",cancelText:"\u53d6\u6d88",onOk:function(){new Promise(function(a){t({type:"department/deleteById",payload:{resolve:a,uuid:e}})}).then(function(e){200===e.code&&o.loadData(d,l,r,i)})},onCancel:function(){}})},a.handleSubmit=function(e){e.preventDefault();var t=a.props,n=t.dispatch,d=t.department.current,l=void 0===d?{}:d,r=t.form,i=a.state,o=i.currentPage,u=i.pageSize,p=i.deptUuid,c=i.keyword,f=l?l.id:"";r.validateFields(function(e,t){if(!e){var d=(0,y.default)((0,y.default)(a));new Promise(function(e){n({type:"department/saveOrUpdate",payload:(0,s.default)({resolve:e,id:f,deptUuid:p},t)})}).then(function(e){200===e.code&&(d.setState({visible:!1}),d.loadData(o,u,p,c))})}})},a.handleChangePage=function(e){var t=a.state,n=t.pageSize,d=t.deptUuid,l=t.keyword;a.setState({currentPage:e}),that.loadData(e,n,d,l)},a}return(0,v.default)(t,e),(0,m.default)(t,[{key:"componentDidMount",value:function(){var e=this.props.match,t=this.state,a=t.currentPage,n=t.pageSize,d=t.keyword,l=e.params.companyUuid;l&&(this.setState({deptUuid:l}),this.loadData(a,n,l,d))}},{key:"render",value:function(){var e=this,t=this.props,a=t.form.getFieldDecorator,n=t.department,d=n.data,s=n.current,f=void 0===s?{}:s,m=n.visible2,h=t.loading,g=this.state.visible,v=function(e){var t=e.data.addTime;return k.default.createElement("div",{className:z.default.listContent},k.default.createElement("div",{className:z.default.listContentItem},k.default.createElement("span",null,"\u6dfb\u52a0\u65f6\u95f4"),k.default.createElement("p",null,(0,D.default)(t).format("YYYY-MM-DD HH:mm"))))},y=function(){return k.default.createElement(S.default,{onSubmit:e.handleSubmit},k.default.createElement(I,{labelCol:{span:5},wrapperCol:{span:15},label:"\u90e8\u95e8\u540d\u79f0"},a("deptname",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u90e8\u95e8\u540d\u79f0,\u957f\u5ea6\u57282-36\u4e2a\u5b57\u7b26",min:2,max:36}],initialValue:f.name})(k.default.createElement(p.default,{placeholder:"\u8bf7\u8f93\u5165\u90e8\u95e8\u540d\u79f0"}))))},C={okText:"\u4fdd\u5b58",onOk:this.handleSubmit,onCancel:this.handleCancel};return k.default.createElement(P.default,null,k.default.createElement("div",{className:z.default.standardList},k.default.createElement(i.default,{className:z.default.listCard,bordered:!1,title:d.deptName,style:{marginTop:24},bodyStyle:{padding:"0 32px 40px 32px"}},k.default.createElement(u.default,{type:"dashed",style:{marginBottom:8},icon:"plus",onClick:this.showModal,ref:function(t){e.addBtn=(0,b.findDOMNode)(t)}},"\u6dfb\u52a0"),k.default.createElement(u.default,{type:"dashed",style:{marginBottom:8},icon:"reload",loading:h,onClick:this.reloadData},"\u5237\u65b0\u6570\u636e"),k.default.createElement(o.default,{size:"large",rowKey:"id",loading:h,dataSource:d.list||[],renderItem:function(t){return k.default.createElement(o.default.Item,{actions:[k.default.createElement("a",{onClick:function(a){a.preventDefault(),e.showEditModal(t.id)}},"\u7f16\u8f91"),k.default.createElement(M.default,{to:"/company/post/".concat(t.id)},"\u5c97\u4f4d"),k.default.createElement("a",{onClick:function(a){a.preventDefault(),e.handleDelete(t.id)}},"\u5220\u9664")]},k.default.createElement(o.default.Item.Meta,{title:t.name}),k.default.createElement(v,{data:t}))}}))),k.default.createElement("div",{className:z.default.pagination},k.default.createElement(l.default,(0,r.default)({},d.pagination,{onChange:this.handleChangePage}))),k.default.createElement(c.default,(0,r.default)({title:"".concat(f&&f.id?"\u7f16\u8f91":"\u6dfb\u52a0","\u90e8\u95e8"),width:640,bodyStyle:{padding:"28px 0 0"},destroyOnClose:!0,visible:g||m},C),y()))}}]),t}(k.PureComponent))||E)||E),L=U;t.default=L},cxoR:function(e,t,a){e.exports={standardList:"antd-pro\\pages\\-admin\\-department-standardList",headerInfo:"antd-pro\\pages\\-admin\\-department-headerInfo",listContent:"antd-pro\\pages\\-admin\\-department-listContent",listContentItem:"antd-pro\\pages\\-admin\\-department-listContentItem",extraContentSearch:"antd-pro\\pages\\-admin\\-department-extraContentSearch",pagination:"antd-pro\\pages\\-admin\\-department-pagination",listCard:"antd-pro\\pages\\-admin\\-department-listCard",standardListForm:"antd-pro\\pages\\-admin\\-department-standardListForm",formResult:"antd-pro\\pages\\-admin\\-department-formResult"}}}]);