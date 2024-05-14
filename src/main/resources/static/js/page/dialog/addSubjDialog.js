function getDialogAddSubj() {
    var form1 = [
        {
            view: "text",
            id: "nameSubject",
            clear: true,
            //label: "Текст",
            placeholder: "Название предмета"
        },
        {
            view: "switch",
            id: "useTimer",
            label: "Таймер",
            //      labelWidth: 70,
            value: 0,
            onLabel: "Включен",
            offLabel: "Отключен"
        },
        {
            view: "counter",
            id: "timerMinutes",
            label: "Минут",
            //        labelWidth: 70
        },
        {
            view: "counter",
            id: "countQuestion",
            label: "Вопросов",
            //       labelWidth: 70
        },
        {
            view: "toolbar",
            id: "toolbarSubjDialog",
            paddingY: 2,
            cols: [
                {
                    view: "button",
                    id: "saveSubjButtonDialog",
                    value: "Сохранить",
                    width: 100,
                    on: {
                        "onItemClick": function (id) {
                            addAndSaveSubjInGrid($$("nameSubject").getValue(),$$("useTimer").getValue(), $$("timerMinutes").getValue(), $$("countQuestion").getValue());
                            dialog.hide();
                        }
                    }
                },
                {
                    view: "button",
                    id: "cancelSubjButtonDialog",
                    value: "Отмена",
                    width: 100,
                    hotkey: "esc",
                    on: {
                        "onItemClick": function (id) {
                            dialog.hide();
                        }
                    }
                }
            ]
        }
    ];
    var dialog = webix.ui({
        view: "window",
        id: "dialogAddSubj",
        height: 300,
        width: 500,
        position: "center",
        modal: true,
        move: true,
        close: true,
        resize: true,
        head: {
            cols: [
                {template: "Запись по предмету", type: "header", borderless: true},
                {
                    view: "icon", icon: "mdi mdi-fullscreen", tooltip: "Развернуть", click: function () {
                        if (dialog.config.fullscreen) {
                            webix.fullscreen.exit();
                            this.define({icon: "mdi mdi-fullscreen", tooltip: "Развернуть"});
                        } else {
                            webix.fullscreen.set(dialog);
                            this.define({icon: "mdi mdi-fullscreen-exit", tooltip: "Свернуть"});
                        }
                        this.refresh();
                    }
                },
                {
                    view: "icon", icon: "wxi-close", tooltip: "Закрыть", click: function () {
                        dialog.hide();
                    }
                }
            ]
        },
        body: {
            rows: [{
                view: "scrollview",
                //scroll: "y",
                body: {
                    rows: [
                        {
                            view: "form",
                            scroll: false,
                            autowidth: true,
                            //width: 300,
                            elements: form1
                        }
                    ]
                }
            }]
        }
    });
    return dialog;
}