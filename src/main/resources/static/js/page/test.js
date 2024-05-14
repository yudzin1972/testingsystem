var cookieSkin = findCookie("skin");
var valueSwitch = 0;
var interval;
var timer;
var seconds = 0;
var minutes = testData.timerMinutes;
var dataQ;
if (cookieSkin === "contrast.css") {
    valueSwitch = 1;
}
var header = header("Режим - тестирование");
webix.ready(function () {
    webix.ui({
        rows:
            [
                {
                    view: "toolbar", cols: [{template: "<img src='/images/icon1.png'>", width: 40}, {
                        view: "label", label: "Режим - тестирование"
                    }, {}, {
                        view: "switch", id: "changeSkin", //label: "Скин",
                        width: 120, value: valueSwitch, align: "right", onLabel: "Темный", offLabel: "Светлый"
                    }]
                }, {
                cols: [// {
                    //     view: "list",
                    //     id: "menuHome",
                    //     width: 150,
                    //     select: true,
                    //     scroll: false,
                    //     tooltip: "Кликните для перехода в режим #value#",
                    //     data: ["Назад"]
                    // },
                    {
                        gravity: 1
                    }, {
                        gravity: 8,
                        rows:
                            [
                                {
                                    height: 40,
                                    cols:
                                        [
                                            {
                                                view: "label",
                                                id: "watch",
                                                hidden: true,
                                                label: "<h2 id='timer' style='margin:0px'>00:00</h2>"
                                            }
                                        ]
                                }, {
                                view: "toolbar", paddingY: 2, height: 40, rows:
                                    [
                                        {
                                            view: "label",
                                            id: "labelHeader1",
                                            label: "Тестирование по предмету: " + testData.name_test
                                        }
                                    ]
                            }, {
                                //body
                                cols:
                                    [
                                        {
                                            gravity: 1, rows:
                                                [
                                                    {
                                                        id: "instr",
                                                        template: "<div style='margin-left:20px;margin-right:20px'>" + "<div>" + " Количество вопросов: " + testData.countQuestion + "</div><hr>" + "<div>" + " Время для прохождения: " + testData.timerMinutes + "</div><hr>" + "<div>" + " Таймер включен: " + testData.useTimer + "</div><hr>" + "</div>" + "<br>" + "<br>" + "<h2>Нажмите кнопку начать...</h2>"
                                                    }
                                                ]
                                        },
                                        {
                                            view: "resizer"
                                        },
                                        {
                                            gravity: 1, rows:
                                                [
                                                    {
                                                        view: "datatable",
                                                        id: "optionsTable",
                                                        header: false,
                                                        borderless: false,
                                                        select: true,
                                                        resizeRow: true,
                                                        resizeColumn: true,
                                                        cssFormat: multilineCss,
                                                        columns: [
                                                            {
                                                                id: "correct",
                                                                template: "{common.checkbox()}",
                                                                width: 40
                                                            },
                                                            // {id: "id", width: 40},
                                                            {id: "text_opt", fillspace: true}
                                                        ],
                                                        checkboxRefresh: true,
                                                        on: {
                                                            "onresize": function () {
                                                                this.adjustRowHeight(null, true);
                                                            },
                                                            "onColumnResize": function (id, newWidth, oldWidth, user_action) {
                                                                this.adjustRowHeight(null, true);
                                                            }

                                                        },
                                                        data: []
                                                    }
                                                ]
                                        }
                                    ]
                            }, {
                                view: "toolbar", id: "toolbarSubjDialog", paddingY: 2,
                                cols:
                                    [
                                        {
                                            view: "button",
                                            id: "startTestButton",
                                            value: "Начать",
                                            tooltip: "Начать тест",
                                            width: 100,
                                            disabled: false,
                                            on: {
                                                "onItemClick": function (id) {
                                                    startTest();
                                                }
                                            }
                                        },
                                        {
                                            view: "button",
                                            id: "cancelTestButton",
                                            value: "Отмена",
                                            tooltip: "Отменить тест",
                                            width: 100,
                                            disabled: false,
                                            on: {
                                                "onItemClick": function (id) {
                                                    setDeleteTest();
                                                }
                                            }
                                        },
                                        {
                                            view: "button",
                                            id: "acceptQuestionButton",
                                            value: "Ответить",
                                            tooltip: "Ответить на вопрос",
                                            width: 100,
                                            disabled: false,
                                            hidden: true,
                                            on: {
                                                "onItemClick": function (id) {
                                                    acceptQuestion();
                                                }
                                            }
                                        },
                                        {
                                            view: "button",
                                            id: "stopTestButton",
                                            value: "Завершить",
                                            tooltip: "Завершить тест",
                                            width: 100,
                                            disabled: false,
                                            hidden: true,
                                            on: {
                                                "onItemClick": function (id) {
                                                    if ($$("acceptQuestionButton").isVisible()) {
                                                        setStopTest();
                                                    } else {
                                                        document.location.href = urlServer + "/";
                                                    }
                                                }
                                            }
                                        }
                                    ]
                            }, {
                                height: 40
                            }]
                    }, {
                        gravity: 1
                    }]
            }]
    });
    // $$("menuHome").attachEvent("onItemClick", function (id, e, node) {
    //     var item = this.getItem(id);
    //     if (id === "Назад") {
    //         window.location.href = urlServer + '/';
    //     }
    // });
    webix.extend($$("optionsTable"), webix.ProgressBar);
    $$("optionsTable").attachEvent("onBeforeLoad", function () {
        $$("optionsTable").showProgress({
            type: "icon",
            hide: false
        });
    });
    $$("optionsTable").attachEvent("onAfterLoad", function () {
        $$("optionsTable").hideProgress();
        if (!$$("optionsTable").count()) {
            $$("optionsTable").showOverlay("Нет данных...");
        } else {
            $$("optionsTable").hideOverlay();
        }
    });

    function setDeleteTest() {
        webix.ajax().headers({
            "Content-type": "application/json",
        }).post(urlServer + "/test/setdelete/" + testData.id, []);
        document.location.href = urlServer + "/";
    }

    function startTest() {
        if (testData.useTimer) {
            $$("watch").show();
            timer = document.getElementById('timer');
            timer.innerText = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
            interval = setInterval(updateTime, 1000);
        } else {
            $$("watch").hide();
        }
        $$("startTestButton").hide();
        $$("cancelTestButton").hide();
        $$("acceptQuestionButton").show();
        $$("stopTestButton").show();
        nextQuestion();
    }

    function updateTime() {
        if (seconds === 0) {
            minutes--;
            seconds = 60;
        }
        seconds--;
        if (minutes === 0 && seconds === 0) {
            clearInterval(interval);
            setStopTest();
        }
        timer.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }

    function acceptQuestion() {
        if (dataQ) {
            dataQJson = dataQ.json();
            optionsUser = $$("optionsTable").serialize(true);
            request = {
                id: dataQJson.id_question,
                id_test: dataQJson.id_test,
                status: "DONE",
                testsUserOptionEntities: optionsUser
            }
            //console.log(request);
            webix.ajax().sync().headers({
                "Content-type": "application/json",
            }).post(urlServer + "/testhistoryquestion", request, {
                success: function (text, data, XmlHttpRequest) {

                },
                error: function (text, data, XmlHttpRequest) {

                    webix.message({
                        text: "Ошибка при сохранении ответа!",
                        type: "error",
                        expire: 1500
                    });
                    //window.location.href = urlServer + '/error';
                }
            });

        }
        nextQuestion();
    }

    function nextQuestion() {
        webix.ajax().sync().headers({
            "Content-type": "application/json",
        }).post(urlServer + "/test/nextquestion/" + testData.id, {
            success: function (text, data, XmlHttpRequest) {
                if (data.json()) {
                    dataQ = data;
                    $$("instr").define("template", data.json().text_question + "<br>" + data.json().content_question);
                    $$("instr").refresh();
                    nextOption(data.json().id_question);
                    webix.message({
                        text: "yahooo!",
                        type: "info",
                        expire: 2000
                    });
                } else {
                    // result test
                    setStopTest();
                }
                promResult(testData.id);
            },
            error: function (text, data, XmlHttpRequest) {

                webix.message({
                    text: "Ошибка при получении вопроса!",
                    type: "error",
                    expire: 1500
                });
                //window.location.href = urlServer + '/error';
            }
        });
    }

    function nextOption(id) {
        $$("optionsTable").clearAll();
        $$("optionsTable").load(function () {
            return webix.ajax().post(urlServer + "/options/" + id);
        }, "json", true);
    }

    function setStopTest() {
        clearInterval(interval);
        $$("watch").hide();
        $$("startTestButton").hide();
        $$("cancelTestButton").hide();
        $$("acceptQuestionButton").hide();
        $$("stopTestButton").show();
        $$("optionsTable").clearAll();
        var jsonObject = finish(testData.id);
        $$("instr").define("template", "<h1>Тест завершен</h1>" + "<h2>Результат: " + jsonObject.itog + "%</h2>");
        $$("instr").refresh();
    }

    function multilineCss(value) {
        return {
            whiteSpace: "pre-wrap"
        }
    }

    function promResult(id) {
        var jsonObject;
        webix.ajax().sync().headers({
            "Content-type": "application/json",
        }).get(urlServer + "/test/" + id + "/promrez", {
            success: function (text, data, XmlHttpRequest) {
                jsonObject = data.json();
                if (jsonObject) {
                    console.log(jsonObject);
                    $$("labelHeader1").define("label", "Тестирование по предмету:" + jsonObject.name_test + " (отвечено на " + jsonObject.countAnswer + " из " + jsonObject.countQuestion + ")");
                    $$("labelHeader1").refresh();
                }
            },
            error: function (text, data, XmlHttpRequest) {

                webix.message({
                    text: "Ошибка при получении состояния!",
                    type: "error",
                    expire: 1500
                });
            }
        })
        return jsonObject;
    }

    function finish(id) {
        var jsonObject;
        webix.ajax().sync().headers({
            "Content-type": "application/json",
        }).post(urlServer + "/test/" + id + "/finish", {
            success: function (text, data, XmlHttpRequest) {
                jsonObject = data.json();
                if (jsonObject) {
                    console.log(jsonObject);
                    $$("labelHeader1").define("label", "Тестирование по предмету:" + jsonObject.name_test + " (отвечено на " + jsonObject.countAnswer + " из " + jsonObject.countQuestion + ")");
                    $$("labelHeader1").refresh();
                }
            },
            error: function (text, data, XmlHttpRequest) {

                webix.message({
                    text: "Ошибка при получении состояния!",
                    type: "error",
                    expire: 1500
                });
            }
        })
        return jsonObject;
    }
})

