(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[7],{v2HZ:function(e,t,r){"use strict";var a=r("xK3H");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=a(r("SeLb"));r("Ktq0");var s=a(r("fL5V")),u=a(r("fh3l")),c=r("vm4F"),d=r("anxO"),i={namespace:"area",state:{data:{list:[],pagination:{}},current:{},visible:!1},effects:{fetch:u.default.mark(function e(t,r){var a,n,s,i,l;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return a=t.payload,n=r.call,s=r.put,e.next=4,n(d.refreshToken);case 4:if(i=e.sent,i){e.next=7;break}return e.abrupt("return");case 7:return e.next=9,n(c.requestList,a);case 9:return l=e.sent,e.next=12,s({type:"saveState",payload:l.data});case 12:case"end":return e.stop()}},e,this)}),saveOrUpdate:u.default.mark(function e(t,r){var a,n,i,l,f,o,p;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return a=t.payload,n=r.call,i=r.put,e.next=4,n(d.refreshToken);case 4:if(l=e.sent,l){e.next=7;break}return e.abrupt("return");case 7:if(f=a.resolve,!a.id){e.next=23;break}return a.uuid=a.id,e.next=12,n(c.update,a);case 12:if(o=e.sent,200!==o.code){e.next=20;break}return s.default.success("\u4fee\u6539\u6210\u529f"),f&&f(o),e.next=18,i({type:"hideModel"});case 18:e.next=21;break;case 20:s.default.error(o.message);case 21:e.next=34;break;case 23:return e.next=25,n(c.save,a);case 25:if(p=e.sent,200!==p.code){e.next=33;break}return s.default.success("\u4fdd\u5b58\u6210\u529f"),f&&f(p),e.next=31,i({type:"hideModel"});case 31:e.next=34;break;case 33:s.default.error(p.message);case 34:case"end":return e.stop()}},e,this)}),remove:u.default.mark(function e(t,r){var a,n,i,l,f,o;return u.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return a=t.payload,n=r.call,r.put,e.next=4,n(d.refreshToken);case 4:if(i=e.sent,i){e.next=7;break}return e.abrupt("return");case 7:if(l=a.resolve,!a.id){e.next=16;break}return a.uuid=a.id,e.next=12,n(c.deleteById,a);case 12:f=e.sent,200===f.code?(s.default.success("\u5220\u9664\u6210\u529f"),l&&l(f)):s.default.error(f.message),e.next=24;break;case 16:if(!a.ids){e.next=23;break}return e.next=19,n(c.deleteById,a);case 19:o=e.sent,200===o.code?(s.default.success("\u5220\u9664\u6210\u529f"),l&&l(o)):s.default.error(o.message),e.next=24;break;case 23:s.default.error("\u672a\u9009\u62e9\u6570\u636e");case 24:case"end":return e.stop()}},e,this)})},reducers:{saveState:function(e,t){return(0,n.default)({},e,{data:t.payload})},showModel:function(e,t){return(0,n.default)({},e,{visible:!0,current:t.payload})},hideModel:function(e){return(0,n.default)({},e,{visible:!1,current:{}})}}};t.default=i}}]);