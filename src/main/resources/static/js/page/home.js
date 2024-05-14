var cookieSkin = findCookie("skin");
var valueSwitch = 0;
var tok;
if (cookieSkin === "contrast.css") {
    valueSwitch = 1;
}
var header = header("Тестирование");
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
                    {}
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
        } else {
            window.location.href = urlServer + '/admin';
        }
    });

    tok = checkAuthUser();
});