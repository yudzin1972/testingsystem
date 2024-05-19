var subjectEditGrid = getSubjectEditGrid();
var questionEditGrid = getQuestionEditGrid();
var optionEditGrid = getOptionEditGrid();
var addSubjDialog = getDialogAddSubj();
var addQuestionDialog = getDialogAddQuestion();
var addOptionDialog = getDialogAddOption();
var protokolDialog = getProtokolDialog();
var detailsQuestion = getDetailsQuestion();
var rezimSubj = "";
var rezimQuestion = "";
var rezimOption = "";
var tok;
var valueSwitch = 0;
var cookieSkin = findCookie("skin");
if (cookieSkin === "contrast.css") {
    valueSwitch = 1;
}
var header = header("Преподаватель");
webix.ready(function () {

    webix.editors.$popup.text = {
        view: "popup",
        body: {
            view: "textarea",
            width: 550,
            height: 350
        }
    };
    var pager1 = {
        view: "pager",
        id: "pager1",
        template: "{common.first()} {common.prev()}{common.pages()}{common.next()} {common.last()}",
        size: 3,
        group: 5
    };
    webix.ui({
        rows: [
            header,
            {
                cols:
                    [
                        {
                            view: "list",
                            id: "menuHome",
                            width: 150,
                            select: true,
                            scroll: false,
                            tooltip: "Кликните для перехода в режим #value#",
                            data: ["Назад"]
                        },
                        {
                            gravity: 1
                        },
                        {
                            gravity: 18,
                            rows:
                                [
                                    {
                                        borderless: true,
                                        view: "tabbar",
                                        id: 'tabbar',
                                        selected: 'spr',
                                        multiview: true,
                                        options: [
                                            {
                                                value: 'Справочники',
                                                id: 'spr'
                                            },
                                            {
                                                value: 'Результаты',
                                                id: 'result'
                                            }
                                        ]
                                    },
                                    {
                                        animate: {
                                            type: "slide",
                                            // direction:"top"
                                        },
                                        fitBiggest: true,
                                        cells: [
                                            {
                                                id: "spr",
                                                cols:
                                                    [
                                                        {
                                                            rows:
                                                                [
                                                                    {
                                                                        rows:
                                                                            [
                                                                                subjectEditGrid,
                                                                                {
                                                                                    view: "toolbar",
                                                                                    id: "toolbarSubj",
                                                                                    paddingY: 2,
                                                                                    cols: [
                                                                                        {
                                                                                            view: "button",
                                                                                            id: "addSubjButton",
                                                                                            value: "Добавить",
                                                                                            width: 100
                                                                                        },
                                                                                        {
                                                                                            view: "button",
                                                                                            id: "editSubjButton",
                                                                                            value: "Изменить",
                                                                                            disabled: true,
                                                                                            width: 100
                                                                                        },
                                                                                        {
                                                                                            view: "button",
                                                                                            id: "saveSubjButton",
                                                                                            value: "Сохранить",
                                                                                            hidden: true,
                                                                                            width: 100
                                                                                        },
                                                                                        {
                                                                                            view: "button",
                                                                                            id: "delSubjButton",
                                                                                            value: "Удалить",
                                                                                            width: 100
                                                                                        }
                                                                                    ]
                                                                                }
                                                                            ]
                                                                    },
                                                                    {
                                                                        view: "resizer"
                                                                    },
                                                                    {}
                                                                ]
                                                        },
                                                        {
                                                            view: "resizer"
                                                        },
                                                        {
                                                            rows: [
                                                                {
                                                                    rows: [
                                                                        questionEditGrid,
                                                                        {
                                                                            view: "toolbar",
                                                                            id: "toolbarQuestion",
                                                                            paddingY: 2,
                                                                            cols: [
                                                                                {
                                                                                    view: "button",
                                                                                    id: "addQuestionButton",
                                                                                    value: "Добавить",
                                                                                    width: 100
                                                                                },
                                                                                {
                                                                                    view: "button",
                                                                                    id: "editQuestionButton",
                                                                                    value: "Изменить",
                                                                                    disabled: true,
                                                                                    width: 100
                                                                                },
                                                                                {
                                                                                    view: "button",
                                                                                    id: "saveQuestionButton",
                                                                                    value: "Сохранить",
                                                                                    hidden: true,
                                                                                    width: 100
                                                                                },
                                                                                {
                                                                                    view: "button",
                                                                                    id: "delQuestionButton",
                                                                                    value: "Удалить",
                                                                                    width: 100
                                                                                }
                                                                            ]
                                                                        }
                                                                    ]
                                                                },
                                                                {
                                                                    view: "resizer"
                                                                },
                                                                {
                                                                    // options
                                                                    rows: [
                                                                        optionEditGrid,
                                                                        {
                                                                            view: "toolbar",
                                                                            id: "toolbarOption",
                                                                            paddingY: 2,
                                                                            cols: [
                                                                                {
                                                                                    view: "button",
                                                                                    id: "addOptionButton",
                                                                                    value: "Добавить",
                                                                                    width: 100
                                                                                },
                                                                                {
                                                                                    view: "button",
                                                                                    id: "editOptionButton",
                                                                                    value: "Изменить",
                                                                                    disabled: true,
                                                                                    width: 100
                                                                                },
                                                                                {
                                                                                    view: "button",
                                                                                    id: "saveOptionButton",
                                                                                    value: "Сохранить",
                                                                                    hidden: true,
                                                                                    width: 100
                                                                                },
                                                                                {
                                                                                    view: "button",
                                                                                    id: "delOptionButton",
                                                                                    value: "Удалить",
                                                                                    width: 100
                                                                                }
                                                                            ]
                                                                        }
                                                                    ]
                                                                }
                                                            ]
                                                        }
                                                    ]
                                            },
                                            {
                                                id: "result",
                                                rows:
                                                    [
                                                        {
                                                            view: "datatable",
                                                            id: "teacherTable1",
                                                            gravity: 8,
                                                            select: true,
                                                            resizeRow: true,
                                                            resizeColumn: true,
                                                            editable: false,
                                                            columns: [
                                                                {
                                                                    id: "id",
                                                                    header: "Id",
                                                                    //hidden: true,
                                                                    //template: "{common.treetable()} #id#",
                                                                    width: 60,
                                                                    webix_kids: true
                                                                },
                                                                {
                                                                    id: "username",
                                                                    header: ["UserName", {content: "textFilter"}],
                                                                    sort: "text",
                                                                    fillspace: true
                                                                },
                                                                {
                                                                    id: "fio",
                                                                    header: ["fio", {content: "textFilter"}],
                                                                    sort: "text",
                                                                    fillspace: true
                                                                }
                                                            ],
                                                            url: urlServer + "/user/teacherall",
                                                            pager: "pager1",
                                                            on: {
                                                                "onSelectChange": function () {
                                                                    $$("teacherTable2").clearAll();
                                                                    if (this.getSelectedItem().tests) {
                                                                        $$("teacherTable2").define("data", this.getSelectedItem().tests);
                                                                    } else {
                                                                        $$("teacherTable2").define("data", []);
                                                                    }
                                                                }
                                                            }
                                                        },
                                                        pager1,
                                                        {gravity: 1},
                                                        {
                                                            gravity: 16,
                                                            view: "datatable",
                                                            id: "teacherTable2",
                                                            select: true,
                                                            resizeRow: true,
                                                            resizeColumn: true,
                                                            columns:
                                                                [
                                                                    {
                                                                        id: "id"
                                                                    },
                                                                    {
                                                                        id: "name_test",
                                                                        header: "Наименование",
                                                                        fillspace: true
                                                                    },
                                                                    {id: "itog", header: "Результат"},
                                                                    {id: "countQuestion", header: "Вопросов"},
                                                                    {id: "countAnswer", header: "Отвечено"},
                                                                    {
                                                                        id: "timeStart",
                                                                        header: "Начало",
                                                                        fillspace: true
                                                                    },
                                                                    {
                                                                        id: "timeFinish",
                                                                        header: "Завершение",
                                                                        fillspace: true
                                                                    }
                                                                ],
                                                            on: {
                                                                "onItemDblClick": function (id) {
                                                                    $$("teacherTable3").clearAll();
                                                                    if (this.getSelectedItem().id) {
                                                                        $$("teacherTable3").load(urlServer + "/test/" + this.getSelectedItem().id + "/protokolnew", "json");
                                                                    } else {
                                                                        $$("teacherTable2").define("data", []);
                                                                    }
                                                                    protokolDialog.show();
                                                                },
                                                                "onHeaderClick": function (header, event, target) {
                                                                    if (header.column === "name_test") {
                                                                        $$("teacherTable2").group(
                                                                            {
                                                                                //by: header.column
                                                                            }
                                                                        );
                                                                    } else {
                                                                        //$$("teacherTable2").ungroup()
                                                                    }
                                                                }
                                                            },
                                                            data: []
                                                        },
                                                        {gravity: 1}
                                                    ]
                                            }
                                        ]
                                    }

                                ]
                        },
                        {
                            gravity: 1
                        }
                    ]
            }
        ]
    });

    webix.extend($$("subjectEditGrid1"), webix.ProgressBar);
    webix.extend($$("questionEditGrid1"), webix.ProgressBar);
    webix.extend($$("optionEditGrid1"), webix.ProgressBar);

    $$("menuHome").attachEvent("onItemClick", function (id, e, node) {
        var item = this.getItem(id);
        if (id === "Назад") {
            window.location.href = urlServer + '/';
        }
    });
    $$("subjectEditGrid1").attachEvent("onBeforeAdd", function (id, obj, index) {

        var b = true;
        $$("subjectEditGrid1").eachRow(function (row) {
            var record = $$("subjectEditGrid1").getItem(row);
            if (record.status == "NEW") {
                b = false;
            }
        }, true);
        if (!b) {
            webix.alert({
                text: "Найдены несохраненные записи!",
                title: "Внимание",
                type: "alert-warning",
                ok: "OK"
            });
            return false;
        }
    });
    /////////////////////////////////// SUBJ
    $$("addSubjButton").attachEvent("onItemClick", function (id, e) {
        setFieldsSubjDialog("add");
        addSubjDialog.show();
    });
    $$("editSubjButton").attachEvent("onItemClick", function (id, e) {
        setFieldsSubjDialog("edit");
        addSubjDialog.show();
    });

    $$("saveSubjButton").attachEvent("onItemClick", function (id, e) {
        saveSubj();
    });
    $$("delSubjButton").attachEvent("onItemClick", function (id, e) {
        id = $$("subjectEditGrid1").getSelectedId(true, true);
        deleteSubg(id[0]);
    });
    /////////////////////////////// QUESTION
    $$("addQuestionButton").attachEvent("onItemClick", function (id, e) {
        var record = $$("subjectEditGrid1").getItem($$("subjectEditGrid1").getSelectedId(true).join());
        if (record && record.status != "NEW") {
            b = true;
        } else {
            b = false;
        }
        if (b == false) {
            webix.alert({
                title: "Внимание",
                text: "Выберите предмет!",
                type: "alert-warning",
                ok: "OK"
            });
            return;
        }

        $$("questionEditGrid1").hideOverlay();
        setFieldsQuestionDialog("add");
        addQuestionDialog.show();
    });

    $$("editQuestionButton").attachEvent("onItemClick", function (id, e) {
        setFieldsQuestionDialog("edit");
        addQuestionDialog.show();
    });

    $$("delQuestionButton").attachEvent("onItemClick", function (id, e) {
        idRowDel = $$("questionEditGrid1").getSelectedId(true, true);
        deleteQuestion(idRowDel[0]);
    });

    //////////////////////////////////////// Option
    $$("addOptionButton").attachEvent("onItemClick", function (id, e) {
        var record = $$("questionEditGrid1").getItem($$("questionEditGrid1").getSelectedId(true).join());
        if (record && record.status != "NEW") {
            b = true;
        } else {
            b = false;
        }
        if (b == false) {
            webix.alert({
                title: "Внимание",
                text: "Выберите вопрос!",
                type: "alert-warning",
                ok: "OK"
            });
            return;
        }

        $$("optionEditGrid1").hideOverlay();
        setFieldsOptionDialog("add");
        addOptionDialog.show();
    });

    $$("editOptionButton").attachEvent("onItemClick", function (id, e) {
        setFieldsOptionDialog("edit");
        addOptionDialog.show();
    });

    $$("delOptionButton").attachEvent("onItemClick", function (id, e) {
        idRowDel = $$("optionEditGrid1").getSelectedId(true, true);
        deleteOption(idRowDel[0]);
    });
    tok = checkAuthUser();
});

function deleteSubg(id) {
    if (id) {
        webix.confirm("Запись будет удалена, продолжить?").then(function () {
            setDelStatusSubj(id);
        });
    } else {
        webix.alert({
            title: "Внимание",
            text: "Выберите запись!",
            type: "alert-warning",
            ok: "OK"
        });
    }
}

function setDelStatusSubj(id) {
    $$("subjectEditGrid1").showProgress({
        type: "icon",
        hide: false
    });
    $$("subjectEditGrid1").remove(id);
    webix.ajax().headers({
        "Content-type": "application/json",
    }).post(urlServer + "/subject/setdelete/" + id, {}, {
        success: function (text, data, XmlHttpRequest) {
            reloadSubjectGrid();
            $$("subjectEditGrid1").enable();
            $$("subjectEditGrid1").hideProgress();

        },
        error: function (text, data, XmlHttpRequest) {
            reloadSubjectGrid();
            $$("subjectEditGrid1").hideProgress();
            $$("subjectEditGrid1").enable();

            webix.message({
                text: "Данные не найдены на сервере!",
                type: "error",
                expire: 15000
            });
        }
    });
}

function reloadSubjectGrid() {
    $$("subjectEditGrid1").clearAll(true);
    $$("subjectEditGrid1").load(urlServer + "/subjects", "json");
}

function reloadQuestionGrid(subjectId) {
    $$("questionEditGrid1").clearAll(true);
    if (!subjectId) {
        subjectId = $$("subjectEditGrid1").getSelectedId(true).join();
    }
    if (subjectId) {
        $$("questionEditGrid1").load(urlServer + "/questions/" + subjectId, "json");
    } else {
        $$("questionEditGrid1").define("data", [])
    }
}

function reloadOptionGrid(questionId) {
    $$("optionEditGrid1").clearAll(true);
    if (!questionId) {
        questionId = $$("questionEditGrid1").getSelectedId(true).join();
    }

    if (questionId) {
        $$("optionEditGrid1").load(urlServer + "/options/" + questionId, "json");
    } else {
        $$("optionEditGrid1").define("data", [])
    }
}

function addAndSaveSubjInGrid(nameSubject, useTimer, timerMinutes, countQuestion) {
    $$("subjectEditGrid1").hideOverlay();
    if (rezimSubj === "add") {
        row = {
            status: "NEW",
            nameSubject: nameSubject,
            useTimer: ((useTimer === 0) ? false : true),
            timerMinutes: timerMinutes,
            countQuestion: countQuestion,
            questions: []
        };
        $$("subjectEditGrid1").add(row);
        saveSubj(row);
    } else {
        r = $$("subjectEditGrid1").getSelectedItem();
        r.nameSubject = nameSubject;
        r.useTimer = ((useTimer === 0) ? false : true);
        r.timerMinutes = timerMinutes;
        r.countQuestion = countQuestion;
        saveSubj(r);
    }
}

function saveSubj(data) {
    $$("subjectEditGrid1").showProgress({
        type: "icon",
        hide: false
    });
    $$("subjectEditGrid1").editStop();
    $$("subjectEditGrid1").disable();
    //var allData = $$("subjectEditGrid1").serialize(true);

    webix.ajax().headers({
        "Content-type": "application/json",
    }).put(urlServer + "/subject",
        JSON.stringify(data), {
            success: function (text, data, XmlHttpRequest) {
                reloadSubjectGrid();
                $$("subjectEditGrid1").enable();
                $$("subjectEditGrid1").hideProgress();

            },
            error: function (text, data, XmlHttpRequest) {
                $$("subjectEditGrid1").hideProgress();
                $$("subjectEditGrid1").enable();
                webix.alert({
                    text: "Ошибка при сохранении!",
                    title: "Ошибка",
                    type: "alert-error",
                    ok: "OK"
                });
            }
        });
}

function setFieldsSubjDialog(rezim) {
    if (rezim === "edit") {
        $$("nameSubject").setValue($$("subjectEditGrid1").getSelectedItem().nameSubject);
        $$("useTimer").setValue($$("subjectEditGrid1").getSelectedItem().useTimer);
        $$("timerMinutes").setValue($$("subjectEditGrid1").getSelectedItem().timerMinutes);
        $$("countQuestion").setValue($$("subjectEditGrid1").getSelectedItem().countQuestion);
    } else {
        $$("nameSubject").setValue("");
        $$("useTimer").setValue(false);
        $$("timerMinutes").setValue(0);
        $$("countQuestion").setValue(0);
    }
    rezimSubj = rezim;
}

function addAndSaveQuestionInGrid(text_question, contentQuestion) {
    $$("questionEditGrid1").showProgress({
        type: "icon",
        hide: false
    });
    $$("questionEditGrid1").editStop();
    $$("questionEditGrid1").disable();
    if (rezimQuestion === "add") {
        row = {
            status: "NEW",
            text_question: text_question,
            contentQuestion: contentQuestion,
            idSubject: $$("subjectEditGrid1").getSelectedId(true).join(),
            options: []
        };
        $$("questionEditGrid1").add(row);
    } else {
        r = $$("questionEditGrid1").getSelectedItem();
        r.text_question = text_question;
        r.contentQuestion = contentQuestion;
    }

    var allData = $$("questionEditGrid1").serialize(true);
    webix.ajax().headers({
        "Content-type": "application/json",
    }).post(urlServer + "/questions",
        JSON.stringify(allData), {
            success: function (text, data, XmlHttpRequest) {
                reloadSubjectGrid();
                reloadQuestionGrid($$("subjectEditGrid1").getSelectedId(true).join());

                $$("questionEditGrid1").enable();
                $$("questionEditGrid1").hideProgress();

            },
            error: function (text, data, XmlHttpRequest) {
                $$("questionEditGrid1").hideProgress();
                $$("questionEditGrid1").enable();

                webix.alert({
                    title: "Ошибка",
                    text: "Ошибка при сохранении!",
                    type: "alert-error",
                    ok: "OK"
                });
            }
        });
}

function setFieldsQuestionDialog(rezim) {
    if (rezim === "edit") {
        $$("text_question").setValue($$("questionEditGrid1").getSelectedItem().text_question);
        $$("contentQuestion").setValue($$("questionEditGrid1").getSelectedItem().contentQuestion);
        //$$("idSubject").setValue($$("questionEditGrid1").getSelectedItem().id);
    } else {
        $$("text_question").setValue("");
        $$("contentQuestion").setValue("");
    }
    rezimQuestion = rezim;
}

function deleteQuestion(id) {
    if (id) {
        webix.confirm("Запись будет удалена, продолжить?").then(function () {
            setDelStatusQuestion(id);
        });
    } else {
        webix.alert({
            title: "Внимание",
            text: "Выберите запись!",
            type: "alert-warning",
            ok: "OK"
        });
    }
}

function setDelStatusQuestion(id) {

    $$("questionEditGrid1").showProgress({
        type: "icon",
        hide: false
    });
    $$("questionEditGrid1").remove(id);
    webix.ajax().headers({
        "Content-type": "application/json",
    }).post(urlServer + "/question/setdelete/" + id, {}, {
        success: function (text, data, XmlHttpRequest) {
            reloadQuestionGrid($$("subjectEditGrid1").getSelectedId(true).join());
            reloadOptionGrid($$("questionEditGrid1").getSelectedId(true).join());
            $$("questionEditGrid1").enable();
            $$("questionEditGrid1").hideProgress();

        },
        error: function (text, data, XmlHttpRequest) {
            reloadQuestionGrid($$("subjectEditGrid1").getSelectedId(true).join());
            reloadOptionGrid($$("questionEditGrid1").getSelectedId(true).join());
            $$("questionEditGrid1").hideProgress();
            $$("questionEditGrid1").enable();

            webix.message({
                text: "Данные не найдены на сервере!",
                type: "error",
                expire: 1000
            });
        }
    });
}

function setFieldsOptionDialog(rezim) {
    console.log(rezim);
    if (rezim === "edit") {
        $$("text_opt").setValue($$("optionEditGrid1").getSelectedItem().text_opt);
        $$("correct").setValue($$("optionEditGrid1").getSelectedItem().correct);
    } else {
        $$("text_opt").setValue("");
        $$("correct").setValue(false);
    }
    rezimOption = rezim;
}

function addAndSaveOptionInGrid(text_opt, correct) {
    $$("optionEditGrid1").showProgress({
        type: "icon",
        hide: false
    });
    $$("optionEditGrid1").editStop();
    $$("optionEditGrid1").disable();
    if (rezimOption === "add") {
        row = {
            status: "NEW",
            text_opt: text_opt,
            correct: correct,
            idQuestion: $$("questionEditGrid1").getSelectedId(true).join()
        };
        $$("optionEditGrid1").add(row);
    } else {
        r = $$("optionEditGrid1").getSelectedItem();
        r.text_opt = text_opt;
        r.correct = correct;
    }

    var allData = $$("optionEditGrid1").serialize(true);
    webix.ajax().headers({
        "Content-type": "application/json",
    }).post(urlServer + "/options",
        JSON.stringify(allData), {
            success: function (text, data, XmlHttpRequest) {
                reloadQuestionGrid($$("subjectEditGrid1").getSelectedId(true).join());
                reloadOptionGrid($$("questionEditGrid1").getSelectedId(true).join());
                $$("optionEditGrid1").enable();
                $$("optionEditGrid1").hideProgress();

            },
            error: function (text, data, XmlHttpRequest) {
                $$("optionEditGrid1").hideProgress();
                $$("optionEditGrid1").enable();

                webix.alert({
                    title: "Ошибка",
                    text: "Ошибка при сохранении!",
                    type: "alert-error",
                    ok: "OK"
                });
            }
        });
}

function deleteOption(id) {
    if (id) {
        webix.confirm("Запись будет удалена, продолжить?").then(function () {
            setDelStatusOption(id);
        });
    } else {
        webix.alert({
            title: "Внимание",
            text: "Выберите запись!",
            type: "alert-warning",
            ok: "OK"
        });
    }
}

function setDelStatusOption(id) {

    $$("optionEditGrid1").showProgress({
        type: "icon",
        hide: false
    });
    $$("optionEditGrid1").remove(id);
    webix.ajax().headers({
        "Content-type": "application/json",
    }).post(urlServer + "/option/setdelete/" + id, {}, {
        success: function (text, data, XmlHttpRequest) {
            reloadOptionGrid($$("questionEditGrid1").getSelectedId(true).join());
            $$("optionEditGrid1").enable();
            $$("optionEditGrid1").hideProgress();

        },
        error: function (text, data, XmlHttpRequest) {
            reloadOptionGrid($$("questionEditGrid1").getSelectedId(true).join());
            $$("optionEditGrid1").hideProgress();
            $$("optionEditGrid1").enable();

            webix.message({
                text: "Данные не найдены на сервере!",
                type: "error",
                expire: 1000
            });
        }
    });
}
