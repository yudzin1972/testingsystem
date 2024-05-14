function getDetailsQuestion() {
    var wind=webix.ui({
        view: "window",
        id: "dialogDetailsQuestion",
        height: 400,
        width: 700,
        position: "center",
        modal: true,
        move: true,
        close: true,
        resize: true,
        head: {
            cols: [
                {template: "Дополнительно", type: "header", borderless: true},
                {
                    view: "icon", icon: "mdi mdi-fullscreen", tooltip: "Развернуть", click: function () {
                        if (wind.config.fullscreen) {
                            webix.fullscreen.exit();
                            this.define({icon: "mdi mdi-fullscreen", tooltip: "Развернуть"});
                        } else {
                            webix.fullscreen.set(wind);
                            this.define({icon: "mdi mdi-fullscreen-exit", tooltip: "Свернуть"});
                        }
                        this.refresh();
                    }
                },
                {
                    view: "icon", icon: "wxi-close", tooltip: "Закрыть", click: function () {
                        wind.hide();
                    }
                }
            ]
        },
        body: {
            gravity: 1, rows:
                [
                    {
                        id: "details",
                        template: "<div><div>"
                    }
                ]
        }
    });
    return wind;
}