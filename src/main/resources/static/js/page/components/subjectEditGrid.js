var idSelectedRowSubj=null;
function getSubjectEditGrid() {
    var grid = {
        view: "datatable",
        id: "subjectEditGrid1",
        drag: "order",
        scheme: {
            $change: function (item) {
                if (item.status == "NEW")
                    item.$css = "highlight";
            }
        },
        columns: [
            //   { id:"id", header:"", width:30},
            {
                id: "nameSubject",
                editor: "text",
                header: "Название",
                sort: "text",
                fillspace: true
            },
            {
                id: "useTimer",
                editor: "checkbox",
                header: "Таймер",
                template: "{common.checkbox()}",
                width: 120
            },
            {
                id: "timerMinutes",
                editor: "text",
                header: "Время",
                width: 120
            },
            {
                id: "countQuestion",
                editor: "text",
                header: "Количество вопросов",
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
                setFieldsSubjDialog("edit");
                addSubjDialog.show();
            },
            "onBeforeLoad": function () {
                if ($$("subjectEditGrid1").getSelectedId()) {
                    idSelectedRowSubj = $$("subjectEditGrid1").getSelectedId().row;
                }
            },
            "onAfterLoad": function () {
                if (!this.count()) {
                    this.showOverlay("Нет данных...");
                    return;
                }
                if (idSelectedRowSubj && $$("subjectEditGrid1").getItem(idSelectedRowSubj)) {
                    $$("subjectEditGrid1").select(idSelectedRowSubj, false);
                }
            },
            "onSelectChange": function () {
                reloadQuestionGrid($$("subjectEditGrid1").getSelectedId(true).join());
                if ($$("subjectEditGrid1").getSelectedId(true).join()) {
                    $$("editSubjButton").enable();
                } else {
                    $$("editSubjButton").disable();
                }
                if ($$("subjectEditGrid1").getSelectedId()) {
                    idSelectedRowSubj = $$("subjectEditGrid1").getSelectedId().row;
                }
            },
            "onresize": function () {
                this.adjustRowHeight(null, true);
            },
            "onColumnResize": function (id, newWidth, oldWidth, user_action) {
                this.adjustRowHeight(null, true);
            }
        },
        onClick: {
            "wxi-trash": function (e, id) {
                webix.confirm("Запись будет удалена, продолжить?").then(function () {
                    $$("subjectEditGrid1").remove(id.row);
                    setDelStatusSubj(id.row);
                });
            }
        },
        url: urlServer + "/subjects"
    };
    return grid;
}