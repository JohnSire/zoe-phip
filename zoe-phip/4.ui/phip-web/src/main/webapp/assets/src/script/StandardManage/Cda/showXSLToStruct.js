/**
 * Created by chenzhisen on 2016/5/9.
 */
define(function (require, exports, module) {

    var internal = {
        attrCodeList: [],
        arrNameList: [],
        itemDataset: [],
        itemElement: [],
        tempItemDataset: [],
        tempItemElement: [],
        setJson: [],
        elementJson: [],
        cookieSetName: "RHIP_STRUCT_SET",
        cookieElementName: "RHIP_STRUCT_ELEMENT",
        init: function (cdaID, sourceID, type) {
            Ext.BLANK_IMAGE_URL = webRoot + 'content/v2/images/xmltree.gif';
            Ext.app.BookLoader = Ext.extend(Ext.ux.tree.XmlTreeLoader, {
                processAttributes: function (attr) {
                    if (attr.isExistChild) {
                        attr.text = attr.content;
                        //alert(attr.text);
                        // attr.iconCls = 'author-m';
                        attr.loaded = true;
                        attr.expanded = false;
                        attr.leaf = false; //不是叶子
                    }
                    else {
                        attr.leaf = true;
                        attr.text = attr.fullContent;
                        //alert(attr.text);
                        //attr.iconCls = 'author-m';
                        attr.leaf = true;
                    }
                }

            });
            var tree = new Ext.tree.TreePanel({
                id: 'tree-panel',
                height: 350,
                renderTo: 'tree',
                margins: '2 2 0 2',
                autoScroll: true,
                rootVisible: false,
                enableDD: true, //是否支持拖拽效果
                rootVisible: false, // 是否隐藏根节点,很多情况下，我们选择隐藏根节点增加美观性
                animate: false, // 动画效果
                lines: true,
                collapseFirst: true,
                expand: true,
                hlColor: true,
                root: new Ext.tree.AsyncTreeNode({}),
                loader: new Ext.app.BookLoader({
                    //dataUrl: 'xml.aspx' //xml路径            
                    dataUrl: 'GetXSLStruct?id=' + cdaID + '&type='+type + '&r=' + Math.round() * 100000, //xml路径
                    requestMethod: 'Get'
                }),
                listeners: {
                    'movenode': function () { alert("move"); },
                    'click': function (node, e) {
                        var attr = node.attributes;
                        //internal.getAttr(attr);
                    },
                    'hover': function (node, e) {
                        // 
                    },
                    'contextmenu': function (node, e) { }
                }
            });
            tree.expandAll();
            //internal.event();
        },
        createElement: function (config) {
            this.datatype = config.datatype;
            this.xpath = config.xpath;
            this.code = config.code;
            this.name = config.name;
            this.length = config.length;
            this.accu = config.accu;
            this.formtype = config.formtype;
            this.formtypeText = config.formtypeText;
            this.sec = config.sec;
            this.secText = config.secText;
            this.versin = config.version;
            this.md5 = config.md5;
            this.config = JSON.stringify(config);
        }
    }


    exports.init = function () {
        internal.init();
    }
});