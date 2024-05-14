var tok;
var cookieSkin = findCookie("skin");
var valueSwitch = 0;
if (cookieSkin === "contrast.css") {
    valueSwitch = 1;
}
var header = header("Режим - тестирование");
webix.ready(function () {
    webix.ui({
        rows:
            [
                header,
                {
                    cols: [
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
                            width: 200
                        },

                        {
                            rows: [
                                {
                                    height: 40
                                },
                                {
                                    view: "toolbar",
                                    paddingY: 2,
                                    height: 40,
                                    rows: [
                                        {
                                            view: "label",
                                            label: "Выберите предмет"
                                        }
                                    ]
                                },
                                {
                                    view: "list",
                                    id: "subjList",
                                    //height:600,
                                    template: "#nameSubject# <div style='padding-left:18px'> Вопросов: <b>#countQuestion#</b> </div>",
                                    select: true,
                                    type: {
                                        height: 62
                                    },
                                    on: {
                                        onSelectChange: function () {
                                            if ($$("subjList").getSelectedId(true).join()) {
                                                $$("selectSubjButtonUser").enable();
                                            } else {
                                                $$("selectSubjButtonUser").disable();
                                            }
                                        }
                                    },
                                    url: urlServer + "/subjects/fortest"
                                },
                                {
                                    view: "toolbar",
                                    id: "toolbarSubjDialog",
                                    paddingY: 2,
                                    cols: [
                                        {
                                            view: "button",
                                            id: "selectSubjButtonUser",
                                            value: "Выбрать",
                                            tooltip: "Сгенерировать тест",
                                            width: 100,
                                            disabled: true,
                                            on: {
                                                "onItemClick": function (id) {
                                                    generateUserTest();
                                                }
                                            }
                                        }
                                    ]
                                },
                                {}
                            ]
                        },
                        {}
                    ]
                }
            ]
    });
    $$("menuHome").attachEvent("onItemClick", function (id, e, node) {
        var item = this.getItem(id);
        if (id === "Назад") {
            window.location.href = urlServer + '/';
        }
    });

    function generateUserTest() {
        var subj = $$("subjList");
        if (!subj) {
            // not find List subjes
            return;
        }
        var item = subj.getSelectedItem();

        var request = {
            subjectId: item.id,
            accountId: "7",
            userId: tok.id,
            name_test: item.nameSubject,
            useTimer: item.useTimer,
            timerMinutes: item.timerMinutes,
            countQuestion: item.countQuestion,
            status: "NEW"
        }
        webix.ajax().headers({
            "Content-type": "application/json",
        }).post(urlServer + "/test/addtest", request, {
            success: function (text, data, XmlHttpRequest) {

                webix.modalbox({
                    title: "Тестирование",
                    buttons: ["Далее"],
                    text: "Тест сгенерирован, нажмите далее...",
                    type: "alert-info",
                    width: 500
                }).then(function (result) {
                        document.location.href = urlServer + "/runtest/" + data.json().id;
                        // window.location.href = urlServer + '/testing';
                    }
                );
            },
            error: function (text, data, XmlHttpRequest) {

                webix.message({
                    text: "Ошибка генерации теста!",
                    type: "error",
                    expire: 15000
                });
                window.location.href = urlServer + '/error';
            }
        });
    }

    tok = checkAuthUser();
})