var cookieSkin = findCookie("skin");
var valueSwitch = 0;
var tok;
if (cookieSkin === "contrast.css") {
    valueSwitch = 1;
}
var header = header("Администрирование");
webix.ready(function () {

    webix.ui({
        rows: [
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
                        data: ["Тестирование", "Преподаватель", "Администратор"]
                    },
                    {
                        cols:
                            [
                                {gravity: 1},
                                {
                                    gravity: 16,
                                    rows:
                                        [
                                            {gravity: 1},
                                            {
                                                gravity: 16,
                                                rows:
                                                    [
                                                        {
                                                            cols:
                                                                [
                                                                    {
                                                                        rows:
                                                                            [
                                                                                {
                                                                                    type: "clean",
                                                                                    rows: [
                                                                                        {
                                                                                            borderless: true,
                                                                                            view: "tabbar",
                                                                                            id: 'tabbar',
                                                                                            selected: 'listView',
                                                                                            multiview: true,
                                                                                            options: [
                                                                                                {
                                                                                                    value: 'Пользователи',
                                                                                                    id: 'treetable1'
                                                                                                },
                                                                                                {
                                                                                                    value: 'Данные',
                                                                                                    id: 'emptyView'
                                                                                                }
                                                                                            ]
                                                                                        },
                                                                                        {
                                                                                            animate: {
                                                                                                type: "slide",
                                                                                                // direction:"top"
                                                                                            },
                                                                                            fitBiggest: true,
                                                                                            cells:
                                                                                                [
                                                                                                    {
                                                                                                        view: "form",
                                                                                                        id: "treetable1",
                                                                                                        elements:
                                                                                                            [
                                                                                                                {
                                                                                                                    view: "datatable",
                                                                                                                    id: "datatable1",
                                                                                                                    columns:
                                                                                                                        [
                                                                                                                            {
                                                                                                                                id: "id",
                                                                                                                                header: "",
                                                                                                                                css: {"text-align": "right"}
                                                                                                                            },
                                                                                                                            {
                                                                                                                                id: "username",
                                                                                                                                header: "Логин",
                                                                                                                                editor: "text",
                                                                                                                                fillspace: true
                                                                                                                            },
                                                                                                                            {
                                                                                                                                id: "fio",
                                                                                                                                header: "ФИО",
                                                                                                                                editor: "text",
                                                                                                                                fillspace: true
                                                                                                                            },
                                                                                                                            // {
                                                                                                                            //     id: "status",
                                                                                                                            //     header: "Статус",
                                                                                                                            //     editor: "combo",
                                                                                                                            //     options: ["NEW", "DONE", "DEL"]
                                                                                                                            // },
                                                                                                                            {
                                                                                                                                id: "role",
                                                                                                                                header: "Роль",
                                                                                                                                editor: "combo",
                                                                                                                                adjust: true,
                                                                                                                                options: ["ROLE_USER", "ROLE_TEACHER", "ROLE_ADMIN"]
                                                                                                                            },
                                                                                                                        ],
                                                                                                                    select: true,
                                                                                                                    resizeRow: true,
                                                                                                                    resizeColumn: true,
                                                                                                                    editable: true,
                                                                                                                    url: urlServer + "/user/all"
                                                                                                                },
                                                                                                                {
                                                                                                                    view: "toolbar",
                                                                                                                    id: "toolbarOption",
                                                                                                                    paddingY: 2,
                                                                                                                    cols: [
                                                                                                                        {
                                                                                                                            view: "button",
                                                                                                                            id: "saveOptionButton",
                                                                                                                            value: "Сохранить",
                                                                                                                            width: 100,
                                                                                                                            on:
                                                                                                                                {
                                                                                                                                    "onItemClick": function (id) {
                                                                                                                                        save();
                                                                                                                                    }
                                                                                                                                }
                                                                                                                        }
                                                                                                                    ]
                                                                                                                }
                                                                                                            ]

                                                                                                    },
                                                                                                    {
                                                                                                        id: "formView",
                                                                                                        view: "form",
                                                                                                        elementsConfig: {
                                                                                                            width: 200,
                                                                                                        },
                                                                                                        elements: [
                                                                                                            {
                                                                                                                view: "text",
                                                                                                                type: "number"
                                                                                                            },
                                                                                                            {
                                                                                                                view: "text",
                                                                                                                placeholder: "Book Title"
                                                                                                            },
                                                                                                            {view: "datepicker"},
                                                                                                            {
                                                                                                                view: "button",
                                                                                                                value: "Submit",
                                                                                                                width: 130
                                                                                                            }
                                                                                                        ]
                                                                                                    },
                                                                                                    {
                                                                                                        id: "emptyView",
                                                                                                        template: ""
                                                                                                    }
                                                                                                ]
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
                                                gravity: 1
                                            }

                                        ]
                                },
                                {gravity: 1}
                            ]
                    }
                ]
            }
        ]
    });
    $$("menuHome").attachEvent("onItemClick", function (id, e, node) {
        var item = this.getItem(id);
        if (id === "Преподаватель") {
            window.location.href = urlServer + '/teacher';
        } else if (id === "Тестирование") {
            window.location.href = urlServer + '/user';
        }
    });

    tok = checkAuthUser();

    function save() {
        var request = $$("datatable1").serialize(true);
        webix.ajax().sync().headers({
            "Content-type": "application/json",
        }).post(urlServer + "/user/all", JSON.stringify(request), {
            success: function (text, data, XmlHttpRequest) {
                webix.alert({
                    text: "Данные сохранены!",
                    title: "Запись",
                    type: "alert-warning",
                    ok: "OK"
                });
            },
            error: function (text, data, XmlHttpRequest) {
                webix.message({
                    text: "Ошибка при сохранении!",
                    type: "error",
                    expire: 1500
                });
            }
        })
    }
});