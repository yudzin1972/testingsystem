var idSelectedRowQuestion=null;
function getQuestionEditGrid() {
    var grid = {
        view: "datatable",
        id: "questionEditGrid1",
        scheme: {
            $change: function (item) {
                if (item.status === "NEW")
                    item.$css = "highlight";
            }
        },
        columns:
            [
                {
                    id: "text_question",
                    editor: "text",
                    header: "Вопросы",
                    sort: "text",
                    fillspace: true
                },
                {
                    header: "",
                    template: "{common.trashIcon()}",
                    width: 50
                }
            ],
        select: true,
        //editable: true,
        resizeRow: true,
        resizeColumn: true,
        editaction: "custom",
        on: {
            "onItemDblClick": function (id) {
                //this.editRow(id);
                setFieldsQuestionDialog("edit");
                addQuestionDialog.show();
            },
            "onBeforeLoad": function () {
                if ($$("questionEditGrid1").getSelectedId()) {
                    idSelectedRowQuestion = $$("subjectEditGrid1").getSelectedId().row;
                }
            },
            "onAfterLoad": function () {
                if (!this.count()) {
                    this.showOverlay("Нет данных...");
                    return;
                } else {
                    this.hideOverlay();
                }
                if (idSelectedRowQuestion && $$("questionEditGrid1").getItem(idSelectedRowQuestion)) {
                    $$("questionEditGrid1").select(idSelectedRowQuestion, false);
                }
            },
            "onSelectChange": function () {
                reloadOptionGrid($$("questionEditGrid1").getSelectedId(true).join());
                if ($$("questionEditGrid1").getSelectedId(true).join()) {
                    $$("editQuestionButton").enable();
                } else {
                    $$("editQuestionButton").disable();
                }
                if ($$("questionEditGrid1").getSelectedId()) {
                    idSelectedRowQuestion = $$("questionEditGrid1").getSelectedId().row;
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
                deleteQuestion(id.row);
            }
        },
        data: []
    };
    return grid;
}