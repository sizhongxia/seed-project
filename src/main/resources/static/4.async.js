(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[4],{"7f+4":function(e,t,r){"use strict";var n=r("xK3H");Object.defineProperty(t,"__esModule",{value:!0}),t.updateDustNoise=c,t.saveDustNoise=o,t.getDeviceBaseInfoById=d,t.deleteById=f;var a=n(r("fh3l")),s=n(r("Lnrk")),u=n(r("t3Un"));function c(e){return i.apply(this,arguments)}function i(){return i=(0,s.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("/device/towercrane/update",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),i.apply(this,arguments)}function o(e){return p.apply(this,arguments)}function p(){return p=(0,s.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("/device/towercrane/save",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),p.apply(this,arguments)}function d(e){return l.apply(this,arguments)}function l(){return l=(0,s.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("/device/towercrane/getById",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),l.apply(this,arguments)}function f(e){return y.apply(this,arguments)}function y(){return y=(0,s.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("device/towercrane/deleteById",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),y.apply(this,arguments)}},sawH:function(e,t,r){"use strict";var n=r("xK3H");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a=n(r("SeLb")),s=n(r("fh3l"));r("Ktq0");var u=n(r("fL5V")),c=r("JBQd"),i=r("7f+4"),o=r("anxO"),p=r("Q2Py"),d=r("vo3B"),l={namespace:"towercrane",state:{data:{list:[],pagination:{}},projects:[],supplierCompanys:[],agentCompanys:[],current:{}},effects:{fetch:s.default.mark(function e(t,r){var n,a,i,p,d;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=t.payload,a=r.call,i=r.put,e.next=4,a(o.refreshToken);case 4:if(p=e.sent,p){e.next=7;break}return e.abrupt("return");case 7:return e.next=9,a(c.deviceList,n);case 9:return d=e.sent,200!==d.code&&(u.default.error(d.message),d.data={list:[],pagination:{total:0}}),e.next=13,i({type:"saveState",payload:d.data});case 13:case"end":return e.stop()}},e,this)}),saveOrUpdate:s.default.mark(function e(t,r){var n,a,u,c,p,d,l;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=t.payload,a=r.call,e.next=4,a(o.refreshToken);case 4:if(u=e.sent,u){e.next=7;break}return e.abrupt("return");case 7:if(c=n.resolve,p=n.uuid,!p){e.next=15;break}return e.next=11,a(i.updateDustNoise,n);case 11:d=e.sent,c&&c(d),e.next=19;break;case 15:return e.next=17,a(i.saveDustNoise,n);case 17:l=e.sent,c&&c(l);case 19:case"end":return e.stop()}},e,this)}),initDevice:s.default.mark(function e(t,r){var n,a,u,c,p,d;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=t.payload,a=r.call,u=r.put,e.next=4,a(o.refreshToken);case 4:if(c=e.sent,c){e.next=7;break}return e.abrupt("return");case 7:return p=n.resolve,e.next=10,a(i.getDeviceBaseInfoById,n);case 10:if(d=e.sent,200!==d.code){e.next=14;break}return e.next=14,u({type:"saveDeviceBaseInfo",payload:d.data});case 14:p&&p(d);case 15:case"end":return e.stop()}},e,this)}),getReceiptByUuid:s.default.mark(function e(t,r){var n,a,u,i,p;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=t.payload,a=r.call,e.next=4,a(o.refreshToken);case 4:if(u=e.sent,u){e.next=7;break}return e.abrupt("return");case 7:return i=n.resolve,e.next=10,a(c.getReceiptByUuid,n);case 10:p=e.sent,i&&i(p);case 12:case"end":return e.stop()}},e,this)}),resetReceiptByUuid:s.default.mark(function e(t,r){var n,a,u,i,p;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=t.payload,a=r.call,e.next=4,a(o.refreshToken);case 4:if(u=e.sent,u){e.next=7;break}return e.abrupt("return");case 7:return i=n.resolve,e.next=10,a(c.resetReceiptByUuid,n);case 10:p=e.sent,i&&i(p);case 12:case"end":return e.stop()}},e,this)}),initCompanyList:s.default.mark(function e(t,r){var n,a,u,c,i,d;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=r.call,a=r.put,e.next=3,n(o.refreshToken);case 3:if(u=e.sent,u){e.next=6;break}return e.abrupt("return");case 6:return e.next=8,n(p.getCompanyOptions,{type:8});case 8:return c=e.sent,e.next=11,n(p.getCompanyOptions,{type:9});case 11:return i=e.sent,d={supplierCompanys:[],agentCompanys:[]},200===c.code&&(d.supplierCompanys=c.data),200===i.code&&(d.agentCompanys=i.data),e.next=17,a({type:"saveCompanyOptions",payload:d});case 17:case"end":return e.stop()}},e,this)}),initProjectList:s.default.mark(function e(t,r){var n,a,u,c,i;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=r.call,a=r.put,e.next=3,n(o.refreshToken);case 3:if(u=e.sent,u){e.next=6;break}return e.abrupt("return");case 6:return e.next=8,n(d.getProjectOptions,{type:8});case 8:return c=e.sent,i={projects:[]},200===c.code&&(i.projects=c.data),e.next=13,a({type:"saveProjectOptions",payload:i});case 13:case"end":return e.stop()}},e,this)}),remove:s.default.mark(function e(t,r){var n,a,c,p,d;return s.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return n=t.payload,a=r.call,r.put,e.next=4,a(o.refreshToken);case 4:if(c=e.sent,c){e.next=7;break}return e.abrupt("return");case 7:if(p=n.resolve,!n.id){e.next=16;break}return n.uuid=n.id,e.next=12,a(i.deleteById,n);case 12:d=e.sent,200===d.code?(u.default.success("\u5220\u9664\u6210\u529f"),p&&p(d)):u.default.error(d.message),e.next=17;break;case 16:u.default.error("\u672a\u9009\u62e9\u6570\u636e");case 17:case"end":return e.stop()}},e,this)})},reducers:{saveState:function(e,t){return(0,a.default)({},e,{data:t.payload})},saveCompanyOptions:function(e,t){return(0,a.default)({},e,{supplierCompanys:t.payload.supplierCompanys,agentCompanys:t.payload.agentCompanys})},saveProjectOptions:function(e,t){return(0,a.default)({},e,{projects:t.payload.projects})},saveDeviceBaseInfo:function(e,t){return(0,a.default)({},e,{current:t.payload})},cleanCurrent:function(e){return(0,a.default)({},e,{current:{}})}}};t.default=l}}]);