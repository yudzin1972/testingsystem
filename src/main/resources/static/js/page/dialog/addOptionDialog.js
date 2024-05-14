function getDialogAddOption() {
    var form3 = [
        {
            view: "textarea",
            id: "text_opt",
            //label: "Текст",
            placeholder: "Вариант ответа"
        },
        {
            view: "switch",
            id: "correct",
            //label: "Таймер",
            //      labelWidth: 70,
            value: 0,
            onLabel: "Верный",
            offLabel: "Не верный"
        },
        {
            view: "toolbar",
            id: "toolbarOptionDialog",
            paddingY: 2,
            cols: [
                {
                    view: "button",
                    id: "saveOptionButtonDialog",
                    value: "Сохранить",
                    width: 100,
                    on: {
                        "onItemClick": function (id) {
                            addAndSaveOptionInGrid($$("text_opt").getValue(), $$("correct").getValue());
                            dialogAddOption.hide();
                        }
                    }
                },
                {
                    view: "button",
                    id: "cancelOptionButtonDialog",
                    value: "Отмена",
                    width: 100,
                    hotkey: "esc",
                    on: {
                        "onItemClick": function (id) {
                            dialogAddOption.hide();
                        }
                    }
                }
            ]
        }
    ];
    var dialogAddOption = webix.ui({
        view: "window",
        id: "dialogAddOption",
        height: 400,
        width: 700,
        position: "center",
        modal: true,
        move: true,
        close: true,
        resize: true,
        animate: {type: "flip", subtype: "vertical"},
        head: {
            cols: [
                {template: "Ответ", type: "header", borderless: true},
                {
                    view: "icon", icon: "mdi mdi-fullscreen", tooltip: "Развернуть", click: function () {
                        if (dialogAddOption.config.fullscreen) {
                            webix.fullscreen.exit();
                            this.define({icon: "mdi mdi-fullscreen", tooltip: "Развернуть"});
                        } else {
                            webix.fullscreen.set(dialogAddOption);
                            this.define({icon: "mdi mdi-fullscreen-exit", tooltip: "Свернуть"});
                        }
                        this.refresh();
                    }
                },
                {
                    view: "icon", icon: "wxi-close", tooltip: "Закрыть", click: function () {
                        dialogAddOption.hide();
                    }
                }
            ]
        },
        body: {
            rows:
                [
                    {
                        view: "scrollview",
                        //scroll: "y",
                        body: {
                            rows: [
                                {
                                    view: "form",
                                    scroll: false,
                                    autowidth: true,
                                    //width: 300,
                                    elements: form3
                                }
                            ]
                        }
                    }
                ]
        }
    });
    return dialogAddOption;
}