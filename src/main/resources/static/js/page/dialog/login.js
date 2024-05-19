webix.ready(function () {
    var wSize = window.innerWidth * 40 / 100;
    var hSize = window.innerHeight * 40 / 100;

    var form1 = [{
        view: "text",
        id: "textLogin",
        value: '',
        label: "Логин",
        placeholder: "login"
    },
        {
            view: "text",
            id: "textPassword",
            type: 'password',
            value: '',
            label: "Пароль"
        },
        {
            view: "label",
            label: "<a href='" + urlServer + "/register' class='webixtype_webix_view' style='color:#5DB4CD'>регистрация</a>",
            //borderless:false
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
        id: "login",
        // height: hSize,
        // width: wSize,
        //left:50, top:50,
        position: "center",
        modal: true,
        move: true,
        close: true,
        resize: false,
        escHide: true,
        head: "Авторизация",
        body: {
            rows: [{
                view: "form",
                scroll: false,
                width: 400,
                elements: form1
            }]
        }
    }).show();

    webix.extend($$("login"), webix.ProgressBar);

    function doOnOkClick() {
        $$("login").disable();
        $$("login").showProgress({
            type: "icon",
            hide: false
        });
        var request = {
            username: $$("textLogin").getValue(),
            password: $$("textPassword").getValue(),
        };
        webix.ajax().sync().headers({
            "Content-type": "application/json"
        }).post(urlServer + "/auth",
            request, {
                success: function (text, data, XmlHttpRequest) {
                    $$("login").enable();
                    $$("login").hideProgress();

                    //console.log(JSON.parse(text).token);
                    //console.log(text);
                    //console.log(data);
                    document.cookie = "hockey=" + "Bearer " + JSON.parse(text).token + ";max-age=6000;SameSite=Strict";
                    document.location.href = urlServer + "/"
                },
                error: function (text, data, XmlHttpRequest) {
                    $$("login").hideProgress();
                    $$("login").getTopParentView().hide();
                    webix.modalbox({
                        title: "Авторизация",
                        buttons: ["Ок"],
                        text: "Ошибка авторизации",
                        type: "alert-error",
                        width: 400
                    }).then(function (result) {
                            document.cookie = "hockey=" + "" + ";max-age=0;SameSite=Strict";
                            document.location.href = urlServer + "/error";
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