(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[34],{"8UXP":function(e,t,a){"use strict";var l=a("xK3H"),n=a("3rQ3");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("fhlT");var r=l(a("kXZy"));a("v0Pg");var d=l(a("Byrh"));a("fThj");var o=l(a("cYEd"));a("VfWj");var u=l(a("Otdh"));a("Ktq0");var i=l(a("fL5V"));a("XFd2");var c=l(a("33fr")),f=l(a("SeLb")),s=l(a("8g53"));a("/MhY");var p=l(a("w0+5"));a("/26P");var m=l(a("aYvq"));a("mKhK");var h=l(a("iW5v"));a("Jm7A");var y=l(a("F1Zk"));a("Lzml");var g=l(a("vpm+"));a("1pPU");var v=l(a("KjZ1")),w=l(a("Sx57")),E=l(a("4zp0")),k=l(a("iVWQ")),b=l(a("lF+r")),C=l(a("PApH"));a("Q3sr");var R,x,L,S=l(a("9NP+")),P=n(a("zLBQ")),T=a("LneV"),F=l(a("D/dJ")),M=a("HOty"),z=l(a("CkN6")),B=l(a("zHco")),I=l(a("efNL")),N=S.default.Item,V=function(e){function t(){return(0,w.default)(this,t),(0,k.default)(this,(0,b.default)(t).apply(this,arguments))}return(0,C.default)(t,e),(0,E.default)(t,[{key:"render",value:function(){var e=this.props,t=e.handleFileChange,a=e.resetReceiptByUuid,l=e.current;return P.default.createElement(m.default,{gutter:{md:12,lg:24,xl:24}},P.default.createElement(h.default,{lg:24,md:24,sm:24},""!==l.receipt&&P.default.createElement("div",{style:{minHeight:240}},P.default.createElement("img",{style:{width:"100%",maxHeight:240,marginBottom:10},src:l.receipt})),P.default.createElement(y.default,{name:"avatar",accept:"image/*",showUploadList:!1,action:(0,M.joinHost)("upload/change/devicereceipt?uuid="+l.key),onChange:t},P.default.createElement(g.default,{style:{marginBottom:40,marginTop:10}},P.default.createElement(v.default,{type:"upload"})," \u4e0a\u4f20/\u66f4\u6539\u56de\u6267\u5355")),P.default.createElement(g.default,{onClick:function(){a(l.key)}},"\u79fb\u9664")))}}]),t}(P.default.Component),O=S.default.create()(V),U=function(e){function t(){var e,a;(0,w.default)(this,t);for(var l=arguments.length,n=new Array(l),r=0;r<l;r++)n[r]=arguments[r];return a=(0,k.default)(this,(e=(0,b.default)(t)).call.apply(e,[this].concat(n))),a.handleFormReset=function(){var e=a.props.form;e.resetFields()},a.getItemsValue=function(){return a.props.form.getFieldsValue()},a}return(0,C.default)(t,e),(0,E.default)(t,[{key:"render",value:function(){var e=this.props,t=e.form.getFieldDecorator,a=e.handleSearch,l=e.loading;return P.default.createElement(S.default,{onSubmit:a,layout:"inline"},P.default.createElement(m.default,{gutter:{md:12,lg:24,xl:48}},P.default.createElement(h.default,{lg:8,md:12,sm:24},P.default.createElement(N,{label:"\u8bbe\u5907\u540d\u79f0"},t("name")(P.default.createElement(p.default,{placeholder:"\u8bf7\u8f93\u5165\u8bbe\u5907\u540d\u79f0"})))),P.default.createElement(h.default,{lg:8,md:12,sm:24},P.default.createElement(N,{label:"\u8bbe\u5907\u7f16\u7801"},t("equipmentNo")(P.default.createElement(p.default,{placeholder:"\u8bf7\u8f93\u5165\u8bbe\u5907\u7f16\u7801"})))),P.default.createElement(h.default,{lg:2,md:4,sm:24},P.default.createElement("span",{className:I.default.submitButtons},P.default.createElement(g.default,{type:"primary",className:I.default.searchSubmitBtn,htmlType:"submit",loading:l},"\u67e5\u8be2"),P.default.createElement(g.default,{style:{marginLeft:8},onClick:this.handleFormReset},"\u91cd\u7f6e")))))}}]),t}(P.default.Component),H=S.default.create()(U),D=(R=(0,T.connect)(function(e){var t=e.towercrane,a=e.loading;return{towercrane:t,loading:a.effects["towercrane/fetch"]}}),x=S.default.create(),R(L=x(L=function(e){function t(){var e,a;(0,w.default)(this,t);for(var l=arguments.length,n=new Array(l),r=0;r<l;r++)n[r]=arguments[r];return a=(0,k.default)(this,(e=(0,b.default)(t)).call.apply(e,[this].concat(n))),a.state={selectedRows:[],formValues:{},scurrent:{},type:1,page:1,size:20},a.handleSearch=function(e){e.preventDefault();var t=a.props.dispatch,l=a.state,n=l.size,r=l.type,d=a.formRef.getItemsValue();a.setState({formValues:d,page:1});var o=(0,f.default)({page:1,type:r,size:n},d);t({type:"towercrane/fetch",payload:o})},a.handleDelete=function(e){var t=a.props.dispatch,l=a.state,n=l.page,r=l.size,d=l.type,o=l.formValues;c.default.confirm({title:"\u786e\u8ba4\u8981\u5220\u9664\u9009\u62e9\u7684\u8bbe\u5907\u4fe1\u606f\u5417?",okText:"\u786e\u8ba4",okType:"danger",cancelText:"\u53d6\u6d88",onOk:function(){new Promise(function(a){t({type:"towercrane/remove",payload:{resolve:a,id:e}})}).then(function(e){if(200===e.code){var a=(0,f.default)({page:n,type:d,size:r},o);t({type:"towercrane/fetch",payload:a})}})}})},a.handleTableMenuClick=function(e){var t=a.state.selectedRows;t?i.default.warn("\u529f\u80fd\u5f00\u53d1\u4e2d..."):i.default.error("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u6761\u8bb0\u5f55")},a.handleTableSelectRows=function(e){a.setState({selectedRows:e})},a.showReceiptPicModel=function(e){var t=a.props.dispatch;new Promise(function(a){t({type:"towercrane/getReceiptByUuid",payload:{resolve:a,uuid:e.key}})}).then(function(t){200===t.code?(e.receipt=t.data,a.setState({scurrent:e})):i.default.error(t.message)})},a.hideReceiptPicModel=function(){a.setState({scurrent:{}})},a.resetReceiptByUuid=function(e){var t=a.props.dispatch,l=(0,s.default)((0,s.default)(a));c.default.confirm({title:"\u786e\u8ba4\u8981\u79fb\u9664\u8be5\u56de\u6267\u5355\u5417?",okText:"\u786e\u8ba4",okType:"danger",cancelText:"\u53d6\u6d88",onOk:function(){new Promise(function(a){t({type:"dustNoise/resetReceiptByUuid",payload:{resolve:a,uuid:e}})}).then(function(e){200===e.code&&(i.default.success("\u79fb\u9664\u6210\u529f"),l.hideReceiptPicModel())})}})},a.handleFileChange=function(e){var t=a.state.scurrent;if(!t.uploading){var l=i.default.loading("\u6b63\u5728\u4e0a\u4f20...",0);t.uploading=!0,t.uploadLoadingModels=l,a.setState({scurrent:t})}if("done"===e.file.status){var n=e.file.response.data;"devicereceipt"===n.type&&(t.uploadLoadingModels&&t.uploadLoadingModels(),a.hideReceiptPicModel(),i.default.destroy(),setTimeout(function(){i.default.success("\u4e0a\u4f20\u6210\u529f")},100))}},a.handleTableChange=function(e){var t=a.props.dispatch,l=a.state.formValues,n=(0,f.default)({page:e.current,size:e.pageSize},l);a.setState({page:e.current,size:e.pageSize}),t({type:"towercrane/fetch",payload:n})},a}return(0,C.default)(t,e),(0,E.default)(t,[{key:"componentDidMount",value:function(){var e=this.props.dispatch;e({type:"towercrane/fetch",payload:{type:1,page:1,size:20}})}},{key:"render",value:function(){var e=this,t=this.props,a=t.towercrane.data,l=t.loading,n=this.state,i=n.selectedRows,f=n.scurrent,s=P.default.createElement(u.default,{onClick:this.handleTableMenuClick,selectedKeys:["remove"]},P.default.createElement(u.default.Item,{key:"export"},"\u5bfc\u51fa")),p=[{title:"\u8bbe\u5907\u540d\u79f0",dataIndex:"name"},{title:"\u8bbe\u5907\u7f16\u7801",dataIndex:"equipmentno"},{title:"\u6240\u5728\u5de5\u5730",dataIndex:"proname"},{title:"\u4ea7\u54c1\u4f9b\u5e94\u5546",dataIndex:"suppliercompany"},{title:"\u4ea7\u54c1\u4ee3\u7406\u5546",dataIndex:"agentcompany"},{title:"\u5728\u7ebf\u72b6\u6001",dataIndex:"lastonlinetime"},{title:"\u6dfb\u52a0\u65f6\u95f4",dataIndex:"addtime"},{title:"\u64cd\u4f5c",render:function(t){return P.default.createElement(P.Fragment,null,P.default.createElement(F.default,{to:"/deviceMonitor/towercraneEdit/".concat(t.key)},"\u7f16\u8f91"),P.default.createElement(o.default,{type:"vertical"}),P.default.createElement("a",{onClick:function(){return e.showReceiptPicModel(t)}},"\u56de\u6267\u5355"),P.default.createElement(o.default,{type:"vertical"}),P.default.createElement("a",{style:{color:"#f5222d"},onClick:function(){return e.handleDelete(t.key)}},"\u5220\u9664"))}}];return P.default.createElement(B.default,null,P.default.createElement(r.default,{bordered:!1},P.default.createElement("div",{className:I.default.tableList},P.default.createElement("div",{className:I.default.tableListForm},P.default.createElement(H,{handleSearch:this.handleSearch,loading:l,wrappedComponentRef:function(t){return e.formRef=t}})),P.default.createElement("div",{className:I.default.tableListOperator},P.default.createElement(F.default,{to:"/deviceMonitor/towercraneCreate"},P.default.createElement(g.default,{type:"dashed",icon:"plus"},"\u65b0\u589e")),i.length>0&&P.default.createElement("span",null,P.default.createElement(d.default,{overlay:s},P.default.createElement(g.default,null," \u6279\u91cf\u64cd\u4f5c ",P.default.createElement(v.default,{type:"down"})," ")))),P.default.createElement(z.default,{selectedRows:i,loading:l,data:a,columns:p,onSelectRow:this.handleTableSelectRows,onChange:this.handleTableChange}))),P.default.createElement(c.default,{title:"\u56de\u6267\u5355",width:640,bodyStyle:{padding:"25px 52px",textAlign:"center"},destroyOnClose:!0,onCancel:this.hideReceiptPicModel,visible:!!f.key,footer:null},P.default.createElement(O,{current:f,resetReceiptByUuid:this.resetReceiptByUuid,handleFileChange:this.handleFileChange})))}}]),t}(P.PureComponent))||L)||L),K=D;t.default=K},efNL:function(e,t,a){e.exports={tableList:"antd-pro\\pages\\-monitor-device\\-towercrane-tableList",tableListOperator:"antd-pro\\pages\\-monitor-device\\-towercrane-tableListOperator",antCascaderPicker:"antd-pro\\pages\\-monitor-device\\-towercrane-antCascaderPicker",tableListForm:"antd-pro\\pages\\-monitor-device\\-towercrane-tableListForm",submitButtons:"antd-pro\\pages\\-monitor-device\\-towercrane-submitButtons"}}}]);