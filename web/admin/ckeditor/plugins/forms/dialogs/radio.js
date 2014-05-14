﻿/*
 Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.md or http://ckeditor.com/license
 */
CKEDITOR.dialog.add("radio", function (d) {
    return{title: d.lang.forms.checkboxAndRadio.radioTitle, minWidth: 350, minHeight: 140, onShow: function () {
        delete this.radioButton;
        var a = this.getParentEditor().getSelection().getSelectedElement();
        a && ("input" == a.getName() && "radio" == a.getAttribute("type")) && (this.radioButton = a, this.setupContent(a))
    }, onOk: function () {
        var a, b = this.radioButton, c = !b;
        c && (a = this.getParentEditor(), b = a.document.createElement("input"), b.setAttribute("type", "radio"));
        c && a.insertElement(b);
        this.commitContent({element: b})
    },
        contents: [
            {id: "info", label: d.lang.forms.checkboxAndRadio.radioTitle, title: d.lang.forms.checkboxAndRadio.radioTitle, elements: [
                {id: "name", type: "text", label: d.lang.common.name, "default": "", accessKey: "N", setup: function (a) {
                    this.setValue(a.data("cke-saved-name") || a.getAttribute("name") || "")
                }, commit: function (a) {
                    a = a.element;
                    this.getValue() ? a.data("cke-saved-name", this.getValue()) : (a.data("cke-saved-name", !1), a.removeAttribute("name"))
                }},
                {id: "value", type: "text", label: d.lang.forms.checkboxAndRadio.value, "default": "",
                    accessKey: "V", setup: function (a) {
                    this.setValue(a.getAttribute("value") || "")
                }, commit: function (a) {
                    a = a.element;
                    this.getValue() ? a.setAttribute("value", this.getValue()) : a.removeAttribute("value")
                }},
                {id: "checked", type: "checkbox", label: d.lang.forms.checkboxAndRadio.selected, "default": "", accessKey: "S", value: "checked", setup: function (a) {
                    this.setValue(a.getAttribute("checked"))
                }, commit: function (a) {
                    var b = a.element;
                    if (CKEDITOR.env.ie) {
                        var c = b.getAttribute("checked"), e = !!this.getValue();
                        c != e && (c = CKEDITOR.dom.element.createFromHtml('<input type="radio"' +
                            (e ? ' checked="checked"' : "") + "></input>", d.document), b.copyAttributes(c, {type: 1, checked: 1}), c.replace(b), d.getSelection().selectElement(c), a.element = c)
                    } else this.getValue() ? b.setAttribute("checked", "checked") : b.removeAttribute("checked")
                }}
            ]}
        ]}
});