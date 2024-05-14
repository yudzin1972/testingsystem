webix.ready(function () {

    var form1 = [
        {
            view: "text",
            id: "textLogin",
            value: '',
            label: "Логин",
            placeholder: "email"
        },
        {
            view: "text",
            id: "textFio",
            value: '',
            label: "ФИО"
        },
        {
            view: "text",
            id: "textPassword",
            type: 'password',
            value: '',
            label: "Пароль"
        },
        {
            view: "text",
            id: "textPassword2",
            type: 'password',
            value: '',
            label: "Confirm"
        },
        {
            margin: 5,
            cols: [{
                view: "button",
                id: "buttonOk",
                value: "Ok",
                css: "webix_primary",
                click: doOnOkClick,
                hotkey: "enter"
            },
                {
                    view: "button",
                    id: "buttonCancel",
                    value: "Отмена",
                    click: doOnCancelClick,
                    hotkey: "esc"
                }
            ]
        }
    ];

    webix.ui({
        view: "window",
        id: "register",

        position: "center",
        modal: true,
        move: true,
        close: true,
        resize: false,
        escHide: true,
        head: "Регистрация",
        body: {
            rows: [{
                view: "form",
                scroll: false,
                width: 400,
                elements: form1
            }]
        }
    }).show();

    webix.extend($$("register"), webix.ProgressBar);

    function doOnOkClick() {
        if ($$("textPassword").getValue() != $$("textPassword2").getValue()) {
            webix.confirm("Пароли не совпадают").then(function () {
                return;
            });
            return;
        }
        $$("register").disable();
        $$("register").showProgress({
            type: "icon",
            hide: false
        });
        var request = {
            username: $$("textLogin").getValue(),
            fio: $$("textFio").getValue(),
            password: $$("textPassword").getValue(),
            //roles: ["ROLE_ADMIN"]
        };
        webix.ajax().sync().headers({
            "Content-type": "application/json"
        }).post(urlServer + "/auth/user",
            request, {
                success: function (text, data, XmlHttpRequest) {
                    $$("register").enable();
                    $$("register").hideProgress();
                    document.cookie = "hockey=" + "Bearer " + JSON.parse(text).token + ";max-age=6000;SameSite=Strict";
                    webix.modalbox({
                        title: "Регистрация",
                        buttons: ["Ок"],
                        text: "Успешная регистрация",
                        type: "alert-info",
                        width: 400
                    }).then(function (result) {
                            document.location.href = urlServer + "/";
                        }
                    );
                },
                error: function (text, data, XmlHttpRequest) {
                    $$("register").hideProgress();

                    var textMessage = buildErrorMessage(text);
                    webix.modalbox({
                        title: "Регистрация",
                        buttons: ["Ок"],
                        text: textMessage,
                        type: "alert-error",
                        width: 600
                    }).then(function (result) {
                            document.cookie = "hockey=" + "" + ";max-age=0;SameSite=Strict";
                            //document.location.href = urlServer + "/error";
                            $$("register").enable();
                            $$("register").hideProgress();
                        }
                    );
                }
            }
        );
    }

    function doOnCancelClick() {
        this.getTopParentView().hide();
        document.location.href = urlServer + "/";
    }
});