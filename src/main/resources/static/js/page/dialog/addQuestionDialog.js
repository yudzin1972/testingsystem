function getDialogAddQuestion() {
    var form2 = [
        {
            view: "textarea",
            id: "text_question",
            clear: true,
            //label: "Текст",
            placeholder: "Вопрос"
        },
        {
          view: "resizer"
        },
        {
            id: 'contentQuestion',
            view: "nic-editor"
        },
        {
            view: "toolbar",
            id: "toolbarQuestionDialog",
            paddingY: 2,
            cols: [
                {
                    view: "button",
                    id: "saveQuestionButtonDialog",
                    value: "Сохранить",
                    width: 100,
                    on: {
                        "onItemClick": function (id) {
                            addAndSaveQuestionInGrid($$("text_question").getValue(), $$("contentQuestion").getValue());
                            dialogAddQuestion.hide();
                        }
                    }
                },
                {
                    view: "button",
                    id: "cancelQuestionButtonDialog",
                    value: "Отмена",
                    width: 100,
                    hotkey: "esc",
                    on: {
                        "onItemClick": function (id) {
                            dialogAddQuestion.hide();
                        }
                    }
                }
            ]
        }
    ];
    var dialogAddQuestion = webix.ui({
        view: "window",
        id: "dialogAddQuestion",
        height: 600,
        width: 700,
        position: "center",
        modal: true,
        move: true,
        close: true,
        resize: true,
        animate:{ type:"flip", subtype:"vertical" },
        head: {
            cols: [
                {template: "Вопрос", type: "header", borderless: true},
                {
                    view: "icon", icon: "mdi mdi-fullscreen", tooltip: "Развернуть", click: function () {
                        if (dialogAddQuestion.config.fullscreen) {
                            webix.fullscreen.exit();
                            this.define({icon: "mdi mdi-fullscreen", tooltip: "Развернуть"});
                        } else {
                            webix.fullscreen.set(dialogAddQuestion);
                            this.define({icon: "mdi mdi-fullscreen-exit", tooltip: "Свернуть"});
                        }
                        this.refresh();
                    }
                },
                {
                    view: "icon", icon: "wxi-close", tooltip: "Закрыть", click: function () {
                        dialogAddQuestion.hide();
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
                            elements: form2
                        }
                    ]
                }
            }]
        }
    });
    return dialogAddQuestion;
}