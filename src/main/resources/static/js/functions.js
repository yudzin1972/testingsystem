function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function findCookie(szName) {
    var i = 0;
    var nStartPosition = 0;
    var nEndPosition = 0;
    var szCookieString = document.cookie;

    while (i <= szCookieString.length) {
        nStartPosition = i;
        nEndPosition = nStartPosition + szName.length;

        if (szCookieString.substring(nStartPosition, nEndPosition) === szName) {
            nStartPosition = nEndPosition + 1;
            nEndPosition = document.cookie.indexOf(";", nStartPosition);

            if (nEndPosition < nStartPosition)
                nEndPosition = document.cookie.length;

            return document.cookie.substring(nStartPosition, nEndPosition);
            break;
        }
        i++;
    }
    return "";
}

function checkAuthUser() {
    var menuHome = $$("menuHome");
    var userH = getUserFromCookie();
    if (userH) {
        if (userH.exp * 1000 < Number(new Date())) {
            document.cookie = "hockey=" + "" + ";max-age=0;SameSite=Strict";
            menuHome.disable();
        } else {
            if (userH.role === "ROLE_USER") {
                menuHome.enableItem("Тестирование");
                menuHome.disableItem("Преподаватель");
                menuHome.disableItem("Администратор");
            } else if (userH.role === "ROLE_TEACHER") {
                menuHome.enableItem("Тестирование");
                menuHome.enableItem("Преподаватель");
                menuHome.disableItem("Администратор");
            } else if (userH.role === "ROLE_ADMIN") {
                menuHome.enableItem("Тестирование");
                menuHome.enableItem("Преподаватель");
                menuHome.enableItem("Администратор");
            } else {
                menuHome.disable();
            }
            if ($$("fioUser")) {
                if (userH) {
                    $$("fioUser").define("label", userH.username);
                    $$("fioUser").refresh();
                } else {
                    $$("fioUser").define("label", "гость");
                    $$("fioUser").refresh();
                }
            }
            return userH;
        }
    } else {
        menuHome.disable();
    }
}

function buildErrorMessage(text) {
    try {
        errorR = JSON.parse(text);
    } catch (e) {
        return text;
    }

    var rez = "<span style='text-align: left;display: table-cell;'>";
    if (errorR.violations) {
        errorR.violations.forEach(function (e) {
            rez += "Поле: " + e.fieldName + " - " + e.message + "<br>";
        });
    }
    rez += "</span>";
    return rez;
}

function getUserFromCookie() {
    var cookieHockey = findCookie("hockey");
    try {
        if (cookieHockey) {
            tokenH = cookieHockey.split(" ")[1];
            if (tokenH) {
                bodyH64 = tokenH.split(".")[1];
                if (bodyH64) {
                    bodyH = bodyH64.replaceAll("+", "-").replaceAll("/", "_");
                    userH = JSON.parse(atob(bodyH));
                    if (userH) {
                        if (userH.exp * 1000 < Number(new Date())) {
                            document.cookie = "hockey=" + "" + ";max-age=0;SameSite=Strict";
                            return null;
                        } else {
                            return userH;
                        }
                    }
                }
            }
        }
    } catch (e) {
        document.cookie = "hockey=" + "" + ";max-age=0;SameSite=Strict";
        return null;
    }

}

function getUserFromServer(username) {
    var userS;
    webix.ajax().sync().headers({
        "Content-type": "application/json"
    }).get(urlServer + "/user/" + username,
        {
            success: function (text, data, XmlHttpRequest) {
                userS = data.json();
            },
            error: function (text, data, XmlHttpRequest) {
                userS = null;
            }
        }
    );
    return userS;
}

function setDetailsQuestion(id) {
    if (id) {
        detailsQuestion.show();
        $$("details").define("template", $$("teacherTable3").getItem(id).content_question);
        $$("details").refresh();
    }
}
