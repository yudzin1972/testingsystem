var userH=getUserFromCookie();
function header(title) {
    var header1 = {
        view: "toolbar",
        cols: [
            {
                template: "<img src='/images/icon1.png' style='cursor: pointer' onclick='document.location.href = urlServer + \"/\";'>",
                id: "buttonHome",
                tooltip: "Начальная страница",
                width: 40
            },
            {view: "label", label: title},
            {},
            {
                view: "switch",
                id: "changeSkin",
                //label: "Скин",
                width: 120,
                value: valueSwitch,
                align: "right",
                onLabel: "Темный",
                offLabel: "Светлый",
                on: {
                    "onChange": function (newValue, oldValue, config) {
                        sk = document.getElementById("mcss");
                        if (sk) {
                            if (newValue == 1) {
                                sk.setAttribute("href", "/js/libs/webix/skins/contrast.css");
                                document.cookie = "skin=contrast.css";
                            } else {
                                sk.setAttribute("href", "/js/libs/webix/skins/flat.css");
                                document.cookie = "skin=flat.css";
                            }
                        }
                    }
                }
            },
            {
                view: "label",
                id: "fioUser",
                width: 120,
                label: ""
            },
            {
                view: "icon",
                icon: "wxi-user",
                tooltip: "Авторизация",
                popup: "my_pop",
                // on: {
                //     "onItemClick": function (id) {
                //         document.location.href = urlServer + "/login";
                //     }
                // }
            }
        ]
    };
    webix.ui({
        view: "popup",
        id: "my_pop",
        width: 300,
        body: {
            view: "list",
            data: [
                {
                    id: "1",
                    name: "login",
                    location: "Войти",
                },
                {id: "2", name: "logout", location: "Выйти", enabled: !!(userH)},
                {id: "3", name: "changePassword", location: "Сменить пароль", enabled: !!(userH)}
            ],
            template: "#location#",
            autoheight: true,
            select: true,
            on: {
                "onItemClick": function (id, e, node) {
                    if (id === "1") {
                        document.location.href = urlServer + "/login";
                    } else if (id === "2") {
                        document.cookie = "hockey=" + "" + ";max-age=0;SameSite=Strict";
                        document.location.href = urlServer + "/";
                    } else if (id === "3") {
                        document.location.href = urlServer + "/changepassword";
                    }
                }
            }
        }
    });
    return header1;
}