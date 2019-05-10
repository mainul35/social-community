/**
 * FusionJS
 * Created by Syed Mainul Hasan
 * github: mainul35
 * Email: mainuls18@gmail.com
 *
 * */
Fusion = {
    contextPath: '/',
    htmlToDOMElement: function (htmlString) {
        return new DOMParser().parseFromString(htmlString, "text/html")
    },
    domEmelentToHTML: function (element) {
        return element.cloneNode(true).querySelector("body").innerHTML;
    },
    hasClass: function (element, className) {
        if (element.classList)
            return element.classList.contains(className)
        else
            return !!element.className.match(new RegExp('(\\s|^)' + className + '(\\s|$)'))
    },
    addClass: function (element, className) {
        if (element.classList)
            element.classList.add(className)
        else if (!Fusion.hasClass(element, className)) element.className += " " + className
    },
    removeClass: function (element, className) {
        if (element.classList)
            element.classList.remove(className)
        else if (Fusion.hasClass(element, className)) {
            var reg = new RegExp('(\\s|^)' + className + '(\\s|$)')
            element.className = element.className.replace(reg, ' ')
        }
    },
    addCSS: function (cssURL) {
        var link = document.createElement("link")
        link.href = Fusion.contextPath + cssURL
        link.type = "text/css";
        link.rel = "stylesheet";
        var cssList = document.head.getElementsByTagName('link')
        var found = false;
        for (var i = 0; i < cssList.length; i++) {
            if (cssList[i].href === link.href) {
                found = true
                break
            } else {
                found = false
            }
        }
        if (!found) {
            document.head.appendChild(link)
        }
    },
    addJS: function (jsURL) {
        var script = document.createElement('script');
        var contextPathMatcher = jsURL.match(/(http[s]?:\/\/.+?\/)/)
        if (contextPathMatcher !== null) {
            jsURL = jsURL.replace(contextPathMatcher[0], "");
        }
        var fileNameMatcher = jsURL.split("/")[jsURL.split("/").length - 1].match(/.+?(?=\.)/)
        var fileName = ""
        if (fileNameMatcher !== null) {
            fileName = fileNameMatcher[0]
        }
        script.onload = function () {
            //TODO
            console.log('loading js: ' + jsURL)
        };
        script.src = Fusion.contextPath + jsURL;

        var jsList = document.head.getElementsByTagName('script')
        var found = false
        for (var i = 0; i < jsList.length; i++) {
            if (jsList[i].src === script.src) {
                found = true
                break
            } else {
                found = false
            }
        }
        if (!found) {
            document.head.appendChild(script);
        }
        return fileName
    },
    reloadJsInContent: function (jsList) {
        Array.from(jsList).forEach(function (item, i) {
            if (item.src) {
                var src = item.src
                item.parentNode.removeChild(item);
                setTimeout(function () {
                    var fileName = Fusion.addJS(src)
                    setTimeout(function () {
                        if (window[fileName] !== undefined)
                            window[fileName].initialize(window.document.querySelector(".content-pane"))
                    }, 500)
                }, 200)
            }
        })
    },

    reloadResources: function (document) {
        var elem = Fusion.htmlToDOMElement(document)
        var jsList = elem.getElementsByTagName("script")
        Fusion.reloadJsInContent(jsList)
    }
};

/**
 * Form Handler plugin
 * Created by Syed Mainul Hasan
 * github: mainul35
 * Email: mainuls18@gmail.com
 *
 * */
Fusion.FormHandler = (function () {
    return {
        serialize: function (form) {
            const values = {};
            const inputs = form.elements;

            for (let i = 0; i < inputs.length; i++) {
                values[inputs[i].name] = inputs[i].value;
            }
            return values;
        },
        submit: function (form, callback) {
            form.onsubmit = (this, callback)
        }
    }
}());

/**
 * Request Manager plugin
 * Created by Syed Mainul Hasan
 * github: mainul35
 * Email: mainuls18@gmail.com
 *
 * */
Fusion.RequestManager = (function () {
    return {
        loadContent: function (container, path, event = null) {
            container.onload = function () {
                $.get(path, function (data) {
                    Fusion.RequestManager.loader.removeLoading(container);
                    if (event === null) container.innerHTML = data
                    else event(container, data)
                }).fail(function (data) {
                    container.innerHTML = "<p>Failed to process your request.</p>";
                });
            }
        },

        loadContentAfterDomReady: function (container, path, event = null) {
            $.get(path, function (data) {
                Fusion.RequestManager.loader.removeLoading(container);
                if (event === null) container.innerHTML = data
                else event(container, data)
            }).fail(function (data) {
                container.innerHTML = "<p>Failed to process your request.</p>";
            });
        },

        handleRouting: function () {
            var routes = document.getElementsByTagName("a")
            for (var i = 0; i < routes.length; i++) {
                routes[i].onclick = function () {
                    var path = this.getAttribute('path')
                    var contentPane = document.querySelector('.content-pane')
                    if (path !== null) {
                        history.pushState(path, '', path);
                        Fusion.RequestManager.loader.addLoading(contentPane)
                        $.get(path, function (data) {
                            var elem = Fusion.htmlToDOMElement(data)
                            var jsList = elem.getElementsByTagName("script")
                            Fusion.reloadJsInContent(jsList)
                            Fusion.RequestManager.loader.removeLoading(contentPane)
                            contentPane.innerHTML = data;
                        }).fail(function (data) {
                            contentPane.innerHTML = "<p>Failed to process your request.</p>";
                        });
                    }
                }
            }
        },
        post: function (form, callback, headers = {}) {
            var url = form.getAttribute('action')
            var data = Fusion.Forms.serialize(form);
            fetch(url, {
                method: 'POST', // or 'PUT'
                body: data, // data can be `string` or {object}!
                headers: headers
            }).then(res => res.json())
                .then(response => callback(response))
                .catch(error => {
                    if (error.status == 404) {
                        console.log('not found')
                    }
                    console.error('Error:', error)
                });
        },

        loader: {
            addLoading: function (panel) {
                var loading = "<div class='loader'></div>";
                panel.innerHTML = loading;
            },

            removeLoading: function (panel) {
                if (panel.querySelector('.loader') !== null) {
                    panel.querySelector('.loader').remove();
                }
            }
        },
    }
}());

/**
 * Form Validator plugin
 * Created by Syed Mainul Hasan
 * github: mainul35
 * Email: mainuls18@gmail.com
 *
 * */
Fusion.Validator = function () {
    var requiredFields = [];
    var lengthCheckerArray = [];
    var typeCheckerArray = [];
    var valid = false;

    function initializeRequiredFields(form) {
        var requiredInputElements = form.querySelectorAll("input.required");
        Array.from(requiredInputElements).forEach((elem, i) => {
            var messageElem = form.querySelector(`.${elem.name}-invalid-message`);
            elem.addEventListener("focusout", function (e) {
                if (!this.value) {
                    this.style.border = "2px solid #FF0000";
                    if (messageElem) {
                        messageElem.innerText = 'Required';
                        messageElem.style.color = "#FF0000"
                    }
                    requiredFields[this.name] = false
                }
            });
            elem.addEventListener("input", function (e) {
                if (this.value) {
                    this.style.border = "2px solid #00CA00";
                    requiredFields[this.name] = true;
                    if (messageElem) {
                        messageElem.innerText = ''
                    }
                }
            })
        })
    }

    function initializeLengthBehaviors(form) {
        var inputElements = form.querySelectorAll("input");
        Array.from(inputElements).forEach((elem, i) => {
            var messageElem = form.querySelector(`.${elem.name}-invalid-message`);
            var length = elem.getAttribute("length");
            elem.addEventListener("keypress", function (e) {
                if (this.type === 'number') {
                    if (e.which < 48 || e.which > 57) {
                        e.preventDefault();
                    }
                }
            });
            if (length) {
                elem.addEventListener("input", function (e) {
                    var lengthArray = length.split(".");
                    var minLength = lengthArray[0];
                    var maxLength = lengthArray[lengthArray.length - 1];
                    if (this.type === 'number') {
                        if (Number(this.value) < Number(minLength) || Number(this.value) > Number(maxLength)) {
                            lengthCheckerArray[this.name] = false;
                            if (messageElem) {
                                messageElem.innerText = 'Range should be between ' + minLength + ' - ' + maxLength;
                                messageElem.style.color = "#FF0000";
                                elem.style.border = "2px solid #FF0000";
                            }
                        } else {
                            lengthCheckerArray[this.name] = true;
                            if (messageElem) {
                                messageElem.innerText = '';
                                elem.style.border = "2px solid #00CA00";
                            }
                        }
                    } else {
                        if (this.value.length < minLength || this.value.length > maxLength) {
                            lengthCheckerArray[this.name] = false;
                            if (messageElem) {
                                messageElem.innerText = 'Range should be between ' + minLength + ' - ' + maxLength
                                messageElem.style.color = "#FF0000";
                                elem.style.border = "2px solid #FF0000";

                            }
                        } else {
                            lengthCheckerArray[this.name] = true;
                            if (messageElem) {
                                messageElem.innerText = ''
                                elem.style.border = "2px solid #00CA00";
                            }
                        }
                    }
                    if (lengthCheckerArray[this.name] === true) {
                        this.style.border = "2px solid #00CA00";
                        messageElem.innerText = "";
                    } else {
                        this.style.border = "2px solid #FF0000";
                        if (messageElem) {
                            messageElem.color = "#FF0000";
                            messageElem.innerText = 'Range should be between ' + minLength + ' - ' + maxLength
                        }
                    }
                })
            }
        });
    }

    function initializeTypeBehavior(form) {
        var inputElements = form.querySelectorAll("input");
        Array.from(inputElements).forEach((elem, i) => {
            elem.addEventListener("focusout", function (e) {
                var messageElem = form.querySelector(`.${elem.name}-invalid-message`);
                if (elem.type === "email") {
                    var emailRegex = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
                    if (emailRegex.test(elem.value) === false) {
                        if (messageElem) {
                            messageElem.innerText = 'Invalid Email Address';
                            elem.style.border = "2px solid #FF0000";
                            messageElem.color = "#FF0000";
                            typeCheckerArray[elem.name] = false;
                        }
                    } else {
                        elem.style.border = "2px solid #00CA00";
                        messageElem.innerText = "";
                        typeCheckerArray[elem.name] = true;
                    }
                } else {
                    typeCheckerArray[elem.name] = true;
                }
            });
        });
    }
    function validateForm(form) {
        var requiredInputElements = form.querySelectorAll("input.required")
        var i = 0
        for (i; i < requiredInputElements.length; i++) {
            var messageElem = form.querySelector(`.${requiredInputElements[i].name}-invalid-message`)
            if (requiredFields[requiredInputElements[i].name] === true) {
                requiredInputElements[i].style.border = "2px solid #00CA00"
                valid = true
                if (messageElem) {
                    messageElem.innerText = ''
                }
            } else {
                requiredInputElements[i].style.border = "2px solid #FF0000"
                valid = false
                if (messageElem) {
                    messageElem.innerText = 'Required'
                    messageElem.style.color = "#FF0000"
                }
                break
            }
        }
        var inputElements = form.querySelectorAll("input")
        i = 0
        for (i; i < inputElements.length; i++) {
            var messageElem = form.querySelector(`.${inputElements[i].name}-invalid-message`)
            if (lengthCheckerArray[inputElements[i].name] === true) {
                inputElements[i].style.border = "2px solid #00CA00"
                if (messageElem) {
                    messageElem.innerText = ''
                }
                valid = true
            } else {
                inputElements[i].style.border = "2px solid #FF0000"
                if (messageElem) {
                    messageElem.innerText = 'Input does not satisfy range'
                    messageElem.style.color = "#FF0000"
                }
                valid = false
                break
            }
        }
        i = 0;
        for (i; i < inputElements.length; i++) {
            var messageElem = form.querySelector(`.${inputElements[i].name}-invalid-message`)
            if (typeCheckerArray[inputElements[i].name] === true) {
                inputElements[i].style.border = "2px solid #00CA00"
                if (messageElem) {
                    messageElem.innerText = ''
                }
                valid = true
            } else {
                inputElements[i].style.border = "2px solid #FF0000"
                if (messageElem) {
                    messageElem.innerText = 'Invalid Input.'
                    messageElem.style.color = "#FF0000"
                }
                valid = false
                break
            }
        }
        return valid === true
    }

    return {
        initialize: function (form) {
            initializeRequiredFields(form);
            initializeLengthBehaviors(form);
            initializeTypeBehavior(form);
        },

        validateForm: function (form) {
            return validateForm(form);
        }
    };
};