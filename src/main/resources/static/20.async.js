(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[20],{swq8:function(e,t,a){"use strict";var l=a("xK3H"),n=a("3rQ3");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("fhlT");var r=l(a("kXZy"));a("Lzml");var u=l(a("vpm+"));a("/26P");var d=l(a("aYvq"));a("cFNL");var i=l(a("gOg5"));a("9TwZ");var f=l(a("wOVV"));a("mKhK");var c=l(a("iW5v"));a("/MhY");var o=l(a("w0+5"));a("fThj");var m=l(a("cYEd"));a("1pPU");var s=l(a("KjZ1"));a("Ktq0");var p=l(a("fL5V"));a("XFd2");var h=l(a("33fr")),E=l(a("SeLb")),y=l(a("Sx57")),g=l(a("4zp0")),v=l(a("iVWQ")),w=l(a("lF+r")),b=l(a("PApH"));a("Q3sr");var k=l(a("9NP+"));a("E2ON");var V,I,O,L=l(a("jjSt")),C=n(a("zLBQ")),q=a("LneV"),P=l(a("I9Uw")),S=l(a("r27q")),T=l(a("zHco")),x=L.default.Option,M=(V=(0,q.connect)(function(e){var t=e.towercrane,a=e.loading;return{towercrane:t,loading:a.effects["towercrane/initDeviceFogGunTypes"],creating:a.effects["towercrane/saveOrUpdate"]}}),I=k.default.create(),V(O=I(O=function(e){function t(){var e,a;(0,y.default)(this,t);for(var l=arguments.length,n=new Array(l),r=0;r<l;r++)n[r]=arguments[r];return a=(0,v.default)(this,(e=(0,w.default)(t)).call.apply(e,[this].concat(n))),a.state={uuid:""},a.handleSubmit=function(e){e.preventDefault();var t=a.state.uuid,l=a.props,n=l.dispatch,r=l.form;r.validateFieldsAndScroll(function(e,a){e||new Promise(function(e){n({type:"towercrane/saveOrUpdate",payload:(0,E.default)({resolve:e,uuid:t},a)})}).then(function(e){200===e.code?h.default.confirm({content:"\u4fdd\u5b58\u6210\u529f, \u662f\u5426\u8df3\u8f6c\u81f3\u8bbe\u5907\u5217\u8868\u9875\uff1f",okText:"\u786e\u8ba4",okType:"danger",cancelText:"\u53d6\u6d88",onOk:function(){S.default.push("/deviceMonitor/towercrane")},onCancel:function(){t?n({type:"towercrane/initDevice",payload:{uuid:t}}):r.resetFields()}}):p.default.error(e.message)})})},a}return(0,b.default)(t,e),(0,g.default)(t,[{key:"componentDidMount",value:function(){var e=this.props,t=e.dispatch,a=e.match,l=a.params.deviceUuid;l?(this.setState({uuid:l}),new Promise(function(e){t({type:"towercrane/initDevice",payload:{resolve:e,uuid:l}})}).then(function(e){200!==e.code&&h.default.confirm({content:"\u65e0\u6548\u7684\u53c2\u6570",okText:"\u786e\u8ba4",okType:"danger",cancelText:"\u53d6\u6d88",onOk:function(){S.default.push("/deviceMonitor/towercrane")},onCancel:function(){S.default.push("/deviceMonitor/towercrane")}})})):t({type:"towercrane/cleanCurrent",payload:{}}),t({type:"towercrane/initCompanyList",payload:{}}),t({type:"towercrane/initProjectList",payload:{}})}},{key:"render",value:function(){var e=this.props,t=e.form.getFieldDecorator,a=e.towercrane,l=a.projects,n=a.supplierCompanys,p=a.agentCompanys,h=a.current,E=e.loading,y=e.creating,g=this.state.uuid;return C.default.createElement(T.default,null,C.default.createElement(r.default,{title:"".concat(""===g?"\u65b0\u589e":"\u4fee\u6539","\u8bbe\u5907"),bordered:!1,loading:E},C.default.createElement(k.default,{onSubmit:this.handleSubmit,layout:"vertical",hideRequiredMark:!1},C.default.createElement(m.default,{orientation:"left"},C.default.createElement(s.default,{type:"info-circle"})," \u57fa\u672c\u4fe1\u606f"),C.default.createElement(d.default,{gutter:24},C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u8bbe\u5907\u540d\u79f0"},t("name",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u8bbe\u5907\u540d\u79f0"}],initialValue:h.name})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165\u8bbe\u5907\u540d\u79f0"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u8bbe\u5907\u7f16\u7801"},t("equipmentno",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u8bbe\u5907\u7f16\u7801"}],initialValue:h.equipmentno})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165\u8bbe\u5907\u7f16\u7801"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u6240\u5c5e\u5de5\u5730"},t("prouuid",{rules:[{required:!0,message:"\u8bf7\u9009\u62e9\u6240\u5c5e\u5de5\u5730"}],initialValue:h.prouuid})(C.default.createElement(L.default,{style:{width:"100%"},showSearch:!0,placeholder:"\u8bf7\u9009\u62e9\u6240\u5c5e\u5de5\u5730",filterOption:function(e,t){return t.props.children.toLowerCase().indexOf(e.toLowerCase())>=0}},l.map(function(e){return C.default.createElement(x,{key:e.key,value:e.key},e.name)}))))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u5b89\u88c5\u65f6\u95f4"},t("installtime",{rules:[{required:!0,message:"\u8bf7\u9009\u62e9\u5b89\u88c5\u65f6\u95f4"}],initialValue:h.installtime&&(0,P.default)(h.installtime,"YYYY-MM-DD")})(C.default.createElement(f.default,{style:{width:"100%"},placeholder:"\u8bf7\u9009\u62e9\u5b89\u88c5\u65f6\u95f4"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u4ea7\u54c1\u4f9b\u5e94\u5546"},t("suppliercompanyuuid",{initialValue:h.suppliercompanyuuid})(C.default.createElement(L.default,{style:{width:"100%"},showSearch:!0,placeholder:"\u8bf7\u9009\u62e9\u4ea7\u54c1\u4f9b\u5e94\u5546",filterOption:function(e,t){return t.props.children.toLowerCase().indexOf(e.toLowerCase())>=0}},n.map(function(e){return C.default.createElement(x,{key:e.key,value:e.key},e.name)}))))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u4ea7\u54c1\u4ee3\u7406\u5546"},t("agentcompanyuuid",{initialValue:h.agentcompanyuuid})(C.default.createElement(L.default,{style:{width:"100%"},showSearch:!0,placeholder:"\u8bf7\u9009\u62e9\u4ea7\u54c1\u4ee3\u7406\u5546",filterOption:function(e,t){return t.props.children.toLowerCase().indexOf(e.toLowerCase())>=0}},C.default.createElement(x,{value:""},"\u65e0\u4ee3\u7406"),p.map(function(e){return C.default.createElement(x,{key:e.key,value:e.key},e.name)}))))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u4f4d\u7f6e\u7ecf\u5ea6"},t("lng",{initialValue:h.lng})(C.default.createElement(i.default,{style:{width:"100%"},placeholder:"\u8bf7\u8f93\u5165\u4f4d\u7f6e\u7ecf\u5ea6"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u4f4d\u7f6e\u7eac\u5ea6"},t("lat",{initialValue:h.lat})(C.default.createElement(i.default,{style:{width:"100%"},placeholder:"\u8bf7\u8f93\u5165\u4f4d\u7f6e\u7eac\u5ea6"}))))),C.default.createElement(m.default,{orientation:"left"},C.default.createElement(s.default,{type:"deployment-unit"})," \u76d1\u63a7\u914d\u7f6e"),C.default.createElement(d.default,{gutter:24},C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u8bbe\u5907IP\u5730\u5740\u548c\u7aef\u53e3"},t("ipaddressport",{initialValue:h.ipaddressport})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165IP\u5730\u5740\u548c\u7aef\u53e3"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u4e3b\u673a\u5730\u5740"},t("host",{initialValue:h.host})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165\u4e3b\u673a\u5730\u5740"}))))),C.default.createElement(m.default,{orientation:"left"},C.default.createElement(s.default,{type:"share-alt"})," \u8054\u7cfb\u65b9\u5f0f"),C.default.createElement(d.default,{gutter:24},C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u8d1f\u8d23\u4eba"},t("personincharge",{initialValue:h.personincharge})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165\u8bbe\u5907\u8d1f\u8d23\u4eba"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u8d1f\u8d23\u4eba\u8054\u7cfb\u65b9\u5f0f"},t("personinchargephone",{initialValue:h.personinchargephone})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165\u8bbe\u5907\u8d1f\u8d23\u4eba\u8054\u7cfb\u65b9\u5f0f"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u5b89\u88c5\u4eba\u5458\u59d3\u540d"},t("installperson",{initialValue:h.installperson})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165\u5b89\u88c5\u4eba\u5458\u59d3\u540d"})))),C.default.createElement(c.default,{lg:6,md:12,sm:24},C.default.createElement(k.default.Item,{label:"\u5b89\u88c5\u4eba\u5458\u8054\u7cfb\u7535\u8bdd"},t("installpersonphone",{initialValue:h.installpersonphone})(C.default.createElement(o.default,{placeholder:"\u8bf7\u8f93\u5165\u5b89\u88c5\u4eba\u5458\u8054\u7cfb\u7535\u8bdd"}))))),C.default.createElement(d.default,{gutter:24},C.default.createElement(c.default,{lg:24,md:24,sm:24,style:{textAlign:"center"}},C.default.createElement(u.default,{type:"primary",htmlType:"submit",loading:y},"".concat(""===g?"\u65b0\u589e":"\u4fee\u6539","\u8bbe\u5907\u4fe1\u606f")))))))}}]),t}(C.PureComponent))||O)||O),D=M;t.default=D}}]);