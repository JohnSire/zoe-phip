define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        init: function () {
            internal.buildList();
            internal.save();
        },
        buildList: function () {
            internal.req.getList(function (data) {
                $.each(data.result, function (index, item) {
                    internal.buildItem(item)
                })
                common.validformObj.config({ tiptype: 3 })
            });
        },
        buildItem: function (item) {
            var jqLabel = $("<label></label>").addClass("label").attr({ "for": item["Code"] }).text(item["Name"] + "：");
            var jqInput = $("<input style='margin-left:5px; width:150px'/>").addClass("text").attr({"name": item["Code"], "id": item["Id"], "edit": "edit"});
            if (item["ValidateRegular"] && item["ValidateRegular"] != "") {
                jqInput.attr("datatype", item["ValidateRegular"]);
            }
            if (item["ValidateMessage"] && item["ValidateMessage"] != "") {
                jqInput.attr("errormsg", item["ValidateMessage"]);
            }
            var jqSelect = $("<select></select>").addClass("select-column").attr({ "name": item["Code"], "id": item["Id"], "edit": "edit" });
            var jqSpan = $("<span class='text-tips'><span class='icon-grid icon-grid-explain' title='说明' ></span>" + item["Descr"] + "</span>");
            var jqColumn = $("<p></p>").addClass("column-one");
            if (item["ValueDefinition"]) {
                var valueDefinition = $.parseJSON(item["ValueDefinition"]);
                $.each(valueDefinition, function (index, option) {
                    var jqOption = $("<option></option>").attr({ "value": option["value"] }).text(option["text"]);
                    jqSelect.append(jqOption);
                });
                jqColumn.append(jqLabel).append(jqSelect).append(jqSpan);
                $("#sysParamList").append(jqColumn);
                jqSelect.val(item["Value"]);
            } else {
                jqColumn.append(jqLabel).append(jqInput).append(jqSpan);
                $("#sysParamList").append(jqColumn);
                jqInput.val(item["Value"]);
            }

        },
        save: function () {
            $("#btnSave").on("click", function () {
                if (common.validformObj.check()) {
                    var param = { list: "" };
                    var list = [];
                    $("#baseAttrForm").find("[edit='edit']").each(function () {
                        var id = $(this).attr("id");
                        var value = $(this).val();
                        list.push({ Id: id, Value: value });
                    });
                    param.list = JSON.stringify(list);
                    internal.req.updateList(param);
                }
            })

        }
    };
    exports.init = internal.init;
})