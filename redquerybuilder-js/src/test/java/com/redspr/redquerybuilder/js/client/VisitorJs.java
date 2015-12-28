package com.redspr.redquerybuilder.js.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayMixed;


public class VisitorJs {
    public final native JsConfiguration config(String json) /*-{
        var x=  eval("(" + json + ")");
        alert(x);
        return x;
    }-*/;


    @Deprecated // use JSNI is going away
    public final native JsConfiguration config() /*-{
        return {meta : {
            tables : [ {
                "name" : "ticket",
                "label" : "Ticket",
            "columns" : [ {
                "name" : "priority",
                "label" : "Priority",
                "type" : "REF"
            }  ],
            fks : []
        } ],

        types : [ {
            "name" : "REF",
            "editor" : "SELECT",
            "operators" : [ {
                "name" : "IN",
                "label" : "any of",
                "cardinality" : "MULTI"
            },
            {
                "name" : "=",
                "label" : "equals",
                "cardinality" : "ONE"
            }]
        }  ]
            }
        }
    }-*/;

    public final native String visitValues(JavaScriptObject cb) /*-{
        var visitor = new $wnd.rqb.Visitor();

        var result = '';
        visitor.visit = function(context) {
            var hasValue = context.asHasValue();
            if (hasValue) {
                result += '(' + hasValue.getValue() + ')';
            }
        }

        cb.accept(visitor);

        return result;
    }-*/;

    public final native String visitMessages(JavaScriptObject cb) /*-{
        var visitor = new $wnd.rqb.Visitor();

        var result = '';
        visitor.visit = function(context) {
            var hasMessages = context.asHasMessages();
            if (hasMessages) {
                var message = new $wnd.rqb.Message('Magical message');

                hasMessages.showMessage(message);
            }
        }

        cb.accept(visitor);

        return result;
    }-*/;

    public final native String visitSerialise(JavaScriptObject cb) /*-{
        var visitor = new $wnd.rqb.Visitor();

        var result = '';

        visitor.visit = function(context) {
            result += "(" + context.getNodeType();
            if (context.getNodeName() != null) {
                 result += ":" + context.getNodeName();
            }
        }
        visitor.endVisit = function(context) {
            result += ")";
        }

        cb.accept(visitor);

        return result;
    }-*/;

    public final native JavaScriptObject start(JsConfiguration config, String sql, JsArrayMixed args) /*-{
        return $wnd.redQueryBuilder(config, sql, args);
    }-*/;
}
