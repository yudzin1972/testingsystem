function getProtokolDialog() {
    var dialogProtokol = webix.ui(
        {
            view: "window",
            id: "dialogProtokol",
            height: 600,
            width: 1300,
            position: "center",
            modal: true,
            move: true,
            close: true,
            resize: true,
            head: {
                cols: [
                    {template: "Протокол", type: "header", borderless: true},
                    {
                        view: "icon", icon: "mdi mdi-fullscreen", tooltip: "Развернуть", click: function () {
                            if (dialogProtokol.config.fullscreen) {
                                webix.fullscreen.exit();
                                this.define({icon: "mdi mdi-fullscreen", tooltip: "Развернуть"});
                            } else {
                                webix.fullscreen.set(dialogProtokol);
                                this.define({icon: "mdi mdi-fullscreen-exit", tooltip: "Свернуть"});
                            }
                            this.refresh();
                        }
                    },
                    {
                        view: "icon", icon: "wxi-close", tooltip: "Закрыть", click: function () {
                            dialogProtokol.hide();
                        }
                    }
                ]
            },
            body: {
                cols: [
                    {gravity: 1},
                    {
                        gravity: 98,
                        rows:
                            [
                                {
                                    gravity: 1
                                },
                                {
                                    gravity: 98,
                                    view: "treetable",
                                    id: "teacherTable3",
                                    select: true,
                                    resizeRow: true,
                                    resizeColumn: true,
                                    scheme: {
                                        $change: function (item) {
                                            if (item.correctAnswer && item.correctAnswer !== "Верно") {
                                                item.$css = "highlight";
                                            }
                                            if (item.correct_option_user !== item.correct_option) {
                                                item.$css = "highlightChild";
                                            }
                                        }
                                    },
                                    columns:
                                        [
                                            {
                                                //id: "id_question",
                                                header: "",
                                                hidden: true,
                                                template: "{common.treetable()} #id_question#",
                                                adjust: true
                                            },
                                            {
                                                id: "text",
                                                header: "Вопрос/ответ",
                                                template: "{common.treetable()} #text#",
                                                sort: "text",
                                                fillspace: true
                                            },
                                            {
                                                id: "edit",
                                                header: "Доп-но",
                                                template: function (obj, common, value, column, index) {
                                                    if (obj.content_question) {
                                                        retu = "<div onclick='setDetailsQuestion(" + obj.id + ")' style='cursor: pointer'>...</div>";
                                                        return retu
                                                    } else {
                                                        return "";
                                                    }
                                                },
                                                adjust: true
                                            },
                                            {
                                                id: "statusQuestionHistory",
                                                header: ["Статус", {content: "selectFilter"}],
                                                adjust: true
                                            },
                                            {
                                                id: "correctAnswer",
                                                header: ["Верно", {content: "selectFilter"}],
                                                adjust: true
                                            },
                                            {
                                                id: "correct_option",
                                                header: "Нужно",
                                                adjust: true
                                            },
                                            {
                                                id: "correct_option_user",
                                                header: "Ответ",
                                                adjust: true
                                            }
                                        ],
                                    filterMode: {
                                        level: false,
                                        showSubItems: true
                                    },
                                    on:
                                        {
                                            "onresize": function () {
                                                this.adjustRowHeight(null, true);
                                            },
                                            "onColumnResize": function (id, newWidth, oldWidth, user_action) {
                                                this.adjustRowHeight(null, true);
                                            }
                                        },
                                    data: []
                                },
                                {
                                    cols: [
                                        {
                                            gravity: 1, view: "button", value: "Export в pdf", click: function () {
                                                textHeader = $$("teacherTable1").getSelectedItem().fio + " по предмету: " + $$("teacherTable2").getSelectedItem().name_test;
                                                textHeader += "\n" + "Время начала: " + $$("teacherTable2").getSelectedItem().timeStart;
                                                textHeader += "\n" + "Время завершения: " + $$("teacherTable2").getSelectedItem().timeFinish;
                                                webix.toPDF($$("teacherTable3"), {

                                                    docHeader: "Результаты теста " + textHeader,
                                                    orientation: "landscape"
                                                });
                                            }
                                        },
                                        {gravity: 3}
                                    ]
                                },
                                {
                                    gravity: 1
                                }
                            ]
                    },
                    {gravity: 1}
                ]
            }
        });
    return dialogProtokol;
}