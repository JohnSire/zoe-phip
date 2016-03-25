define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        init: function () {
            internal.buildList();
            internal.save();
        },
        buildList: function () {
            internal.req.getList(function (data) {
                $.each(data.result.rows, function (index, item) {
                    internal.buildItem(item)
                })
                //common.validformObj.config({ tiptype: 3 })
            });
        },
        buildItem: function (item) {
            var jqLabel = $("<label></label>").addClass("label").attr({"for": item["code"]}).text(item["name"] + "：");
            var jqInput = $("<input style='margin-left:5px; width:150px'/>").addClass("text").attr({
                "name": item["code"],
                "id": item["id"],
                "edit": "edit"
            });
            if (item["ValidateRegular"] && item["ValidateRegular"] != "") {
                jqInput.attr("datatype", item["ValidateRegular"]);
            }
            if (item["ValidateMessage"] && item["ValidateMessage"] != "") {
                jqInput.attr("errormsg", item["ValidateMessage"]);
            }
            var jqSpan = $("<span class='text-tips'><span class='icon-grid icon-grid-explain' title='说明' ></span>" + item["descr"] + "</span>");
            var jqColumn = $("<p></p>").addClass("column-one");
            if (item["ValueDefinition"]) {
                var valueDefinition = $.parseJSON(item["ValueDefinition"]);
                $.each(valueDefinition, function (index, option) {
                    var jqOption = $("<option></option>").attr({"value": option["value"]}).text(option["text"]);
                    jqSelect.append(jqOption);
                });
                jqColumn.append(jqLabel).append(jqSelect).append(jqSpan);
                $("#sysParamList").append(jqColumn);
                jqSelect.val(item["value"]);
            } else {
                jqColumn.append(jqLabel).append(jqInput).append(jqSpan);
                $("#sysParamList").append(jqColumn);
                jqInput.val(item["value"]);
            }

        },
        save: function () {
            $("#btnSave").on("click", function () {
                //if (common.validformObj.check()) {
                    var param = {list: ""};
                    var list = [];
                    $("#baseAttrForm").find("[edit='edit']").each(function () {
                        var id = $(this).attr("id");
                        var value = $(this).val();
                        list.push({Id: id, Value: value});
                    });
                    param.list = JSON.stringify(list);
                    internal.req.updateList(param);
                //}
            })

        }
    };
    exports.init = internal.init;
})