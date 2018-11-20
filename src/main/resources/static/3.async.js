(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[3],{"9tYM":function(e,t,n){"use strict";var r=n("xK3H");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a=r(n("SeLb")),u=r(n("fh3l"));n("Ktq0");var s=r(n("fL5V")),c=n("JBQd"),o=n("zvuG"),p=n("anxO"),i=n("pwE5"),d=n("Q2Py"),f=n("vo3B"),l={namespace:"fogGun",state:{data:{list:[],pagination:{}},projects:[],supplierCompanys:[],agentCompanys:[],fogGunTypes:[],current:{}},effects:{fetch:u.default.mark(function e(t,n){var r,a,o,i,d;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,a=n.call,o=n.put,e.next=4,a(p.refreshToken);case 4:if(i=e.sent,i){e.next=7;break}return e.abrupt("return");case 7:return e.next=9,a(c.deviceList,r);case 9:return d=e.sent,200!==d.code&&(s.default.error(d.message),d.data={list:[],pagination:{total:0}}),e.next=13,o({type:"saveState",payload:d.data});case 13:case"end":return e.stop()}},e,this)}),saveOrUpdate:u.default.mark(function e(t,n){var r,a,s,c,i,d,f;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,a=n.call,e.next=4,a(p.refreshToken);case 4:if(s=e.sent,s){e.next=7;break}return e.abrupt("return");case 7:if(c=r.resolve,i=r.uuid,!i){e.next=15;break}return e.next=11,a(o.updateFogGun,r);case 11:d=e.sent,c&&c(d),e.next=19;break;case 15:return e.next=17,a(o.saveFogGun,r);case 17:f=e.sent,c&&c(f);case 19:case"end":return e.stop()}},e,this)}),initDevice:u.default.mark(function e(t,n){var r,a,s,c,i,d;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,a=n.call,s=n.put,e.next=4,a(p.refreshToken);case 4:if(c=e.sent,c){e.next=7;break}return e.abrupt("return");case 7:return i=r.resolve,e.next=10,a(o.getDeviceBaseInfoById,r);case 10:if(d=e.sent,200!==d.code){e.next=14;break}return e.next=14,s({type:"saveDeviceBaseInfo",payload:d.data});case 14:i&&i(d);case 15:case"end":return e.stop()}},e,this)}),initDeviceFogGunTypes:u.default.mark(function e(t,n){var r,a,s,c;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,a=n.call,s=n.put,e.next=4,a(i.getByType,{type:"FogGunType"});case 4:return c=e.sent,c.type=r.type,e.next=8,s({type:"saveDeviceFogGunTypes",payload:c.data});case 8:case"end":return e.stop()}},e,this)}),getReceiptByUuid:u.default.mark(function e(t,n){var r,a,s,o,i;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,a=n.call,e.next=4,a(p.refreshToken);case 4:if(s=e.sent,s){e.next=7;break}return e.abrupt("return");case 7:return o=r.resolve,e.next=10,a(c.getReceiptByUuid,r);case 10:i=e.sent,o&&o(i);case 12:case"end":return e.stop()}},e,this)}),resetReceiptByUuid:u.default.mark(function e(t,n){var r,a,s,o,i;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,a=n.call,e.next=4,a(p.refreshToken);case 4:if(s=e.sent,s){e.next=7;break}return e.abrupt("return");case 7:return o=r.resolve,e.next=10,a(c.resetReceiptByUuid,r);case 10:i=e.sent,o&&o(i);case 12:case"end":return e.stop()}},e,this)}),initCompanyList:u.default.mark(function e(t,n){var r,a,s,c,o,i;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=n.call,a=n.put,e.next=3,r(p.refreshToken);case 3:if(s=e.sent,s){e.next=6;break}return e.abrupt("return");case 6:return e.next=8,r(d.getCompanyOptions,{type:8});case 8:return c=e.sent,e.next=11,r(d.getCompanyOptions,{type:9});case 11:return o=e.sent,i={supplierCompanys:[],agentCompanys:[]},200===c.code&&(i.supplierCompanys=c.data),200===o.code&&(i.agentCompanys=o.data),e.next=17,a({type:"saveCompanyOptions",payload:i});case 17:case"end":return e.stop()}},e,this)}),initProjectList:u.default.mark(function e(t,n){var r,a,s,c,o;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=n.call,a=n.put,e.next=3,r(p.refreshToken);case 3:if(s=e.sent,s){e.next=6;break}return e.abrupt("return");case 6:return e.next=8,r(f.getProjectOptions,{type:8});case 8:return c=e.sent,o={projects:[]},200===c.code&&(o.projects=c.data),e.next=13,a({type:"saveProjectOptions",payload:o});case 13:case"end":return e.stop()}},e,this)}),remove:u.default.mark(function e(t,n){var r,a,c,i,d;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return r=t.payload,a=n.call,n.put,e.next=4,a(p.refreshToken);case 4:if(c=e.sent,c){e.next=7;break}return e.abrupt("return");case 7:if(i=r.resolve,!r.id){e.next=16;break}return r.uuid=r.id,e.next=12,a(o.deleteById,r);case 12:d=e.sent,200===d.code?(s.default.success("\u5220\u9664\u6210\u529f"),i&&i(d)):s.default.error(d.message),e.next=17;break;case 16:s.default.error("\u672a\u9009\u62e9\u6570\u636e");case 17:case"end":return e.stop()}},e,this)})},reducers:{saveState:function(e,t){return(0,a.default)({},e,{data:t.payload})},saveCompanyOptions:function(e,t){return(0,a.default)({},e,{supplierCompanys:t.payload.supplierCompanys,agentCompanys:t.payload.agentCompanys})},saveProjectOptions:function(e,t){return(0,a.default)({},e,{projects:t.payload.projects})},saveDeviceBaseInfo:function(e,t){return(0,a.default)({},e,{current:t.payload})},cleanCurrent:function(e){return(0,a.default)({},e,{current:{}})},saveDeviceFogGunTypes:function(e,t){return(0,a.default)({},e,{fogGunTypes:t.payload})}}};t.default=l},zvuG:function(e,t,n){"use strict";var r=n("xK3H");Object.defineProperty(t,"__esModule",{value:!0}),t.updateFogGun=c,t.saveFogGun=p,t.getDeviceBaseInfoById=d,t.deleteById=l;var a=r(n("fh3l")),u=r(n("Lnrk")),s=r(n("t3Un"));function c(e){return o.apply(this,arguments)}function o(){return o=(0,u.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,s.default)("/device/fogGun/update",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),o.apply(this,arguments)}function p(e){return i.apply(this,arguments)}function i(){return i=(0,u.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,s.default)("/device/fogGun/save",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),i.apply(this,arguments)}function d(e){return f.apply(this,arguments)}function f(){return f=(0,u.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,s.default)("/device/fogGun/getById",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),f.apply(this,arguments)}function l(e){return y.apply(this,arguments)}function y(){return y=(0,u.default)(a.default.mark(function e(t){return a.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,s.default)("device/fogGun/deleteById",{method:"POST",body:t}));case 1:case"end":return e.stop()}},e,this)})),y.apply(this,arguments)}}}]);