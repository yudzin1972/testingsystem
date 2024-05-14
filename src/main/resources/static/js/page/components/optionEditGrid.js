function getOptionEditGrid() {
    var grid = {
        view: "datatable",
        id: "optionEditGrid1",
        scheme: {
            $change: function (item) {
                if (item.status === "NEW")
                    item.$css = "highlight";
            }
        },
        columns:
            [
                {
                    id: "text_opt",
                    editor: "text",
                    header: "Ответы",
                    sort: "text",
                    fillspace: true
                },
                {
                    id: "correct",
                    editor: "checkbox",
                    header: "Correct",
                    template: "{common.checkbox()}",
                    width: 120
                },
                {
                    header: "",
                    template: "{common.trashIcon()}",
                    width: 50
                }
            ],
        select: true,
        resizeRow: true,
        resizeColumn: true,
        editable: false,
        editaction: "custom",
        on: {
            "onItemDblClick": function (id) {
                //this.editRow(id);
                setFieldsOptionDialog("edit");
                addOptionDialog.show();
            },
            "onAfterLoad": function () {
                if (!this.count()) {
                    this.showOverlay("Нет данных...");
                } else {
                    this.hideOverlay();
                }
            },
            "onSelectChange": function () {
                //reloadOptionGrid($$("questionEditGrid1").getSelectedId(true).join());
                if ($$("questionEditGrid1").getSelectedId(true).join()) {
                    $$("editOptionButton").enable();
                } else {
                    $$("editOptionButton").disable();
                }
            },
            "onresize": function () {
                this.adjustRowHeight(null, true);
            },
            "onColumnResize": function (id,newWidth,oldWidth,user_action) {
                this.adjustRowHeight(null, true);
            }
        },
        onClick: {
            "wxi-trash": function (e, id) {
                deleteOption(id.row);
            }
        },
        data: []
    };
    return grid;
}