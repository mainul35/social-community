$(function () {
    $('.panel-google-plus > .panel-footer > .input-placeholder, .panel-google-plus > .panel-google-plus-comment > .panel-google-plus-textarea > button[type="reset"]').on('click', function(event) {
        var $panel = $(this).closest('.panel-google-plus');
        $comment = $panel.find('.panel-google-plus-comment');

        $comment.find('.btn:first-child').addClass('disabled');
        $comment.find('textarea').val('');

        $panel.toggleClass('panel-google-plus-show-comment');

        if ($panel.hasClass('panel-google-plus-show-comment')) {
            $comment.find('textarea').focus();
        }
    });
    $('.panel-google-plus-comment > .panel-google-plus-textarea > textarea').on('keyup', function(event) {
        var $comment = $(this).closest('.panel-google-plus-comment');

        $comment.find('button[type="submit"]').addClass('disabled')
        if ($(this).val().length >= 1) {
            $comment.find('button[type="submit"]').removeClass('disabled');
        }
    });
});

RegistrationForm = (function () {

    return {
        initialize: function (form) {
            var formValidator  = new Fusion.Validator();
            var valid = formValidator.initialize(form);
            if (valid) {
                form.onsubmit = function (e) {
                    e.preventDefault();
                    if (formValidator.validateForm(form)){
                        Fusion.RequestManager.post(form, function (response) {
                            console.log(response);
                        });
                    }
                };
            }
        }
    }
}().initialize(document.querySelector("form.registrationForm")));