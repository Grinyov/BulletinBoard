$(document).ready(function () {
    $('[data-toggle="offcanvas"]').click(function () {
        $('.row-offcanvas').toggleClass('active')
    });

    // adverts checkboxes
    var checkall = $('input#checkall');
    var check = $('input[name="checks"]');
    var fixedNotifyBlock = $("#bottom-fixed-notification");
    var singleDeleteButton = $("a.btn.btn-danger");
    var cancelCheckboxesButton = $("a#cancel_checkboxes");
    var submitCheckboxesButton = $("input#submit_checkboxes");
    var submitCheckboxesButtonValue = submitCheckboxesButton.val();

    checkall.change(function () {
        if (this.checked) {
            $(check).each(function () {
                $(this).prop('checked', true);
            })
        } else {
            $(check).each(function () {
                $(this).prop('checked', false);
            })
        }

        updateCount();
    });

    check.each(function () {
        $(this).change(function () {
            if (checkall.is(':checked')) {
                checkall.prop('checked', false);
            }

            updateCount();
        });
    });

    cancelCheckboxesButton.click(function () {
        checkall.prop("checked", false);
        $(check).each(function () {
            $(this).prop('checked', false);
        });
        updateCount();
    });


    function updateCount() {
        var count = $('input[name="checks"]:checked').size();
        var buttonCount = submitCheckboxesButtonValue + " (" + count + ")";

        if (count > 0) {
            fixedNotifyBlock.removeClass("hidden");
            submitCheckboxesButton.val(buttonCount);
            $(singleDeleteButton).each(function () {
                $(this).attr("disabled", "disabled");
            });

        } else {
            fixedNotifyBlock.addClass("hidden");
            $(singleDeleteButton).each(function () {
                $(this).removeAttr("disabled");
            });
        }
    }

});