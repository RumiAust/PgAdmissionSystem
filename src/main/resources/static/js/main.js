$(document).ready(function () {

    hasError = $('#errorCheck').data('has-error');
    if (hasError == true) {
        $('#formular').modal('toggle');
    }

    serial = $('#serialList').data('serial');
    pointer = $('#pointer').data('pointer');
    console.log("after loaded serial: " + serial + "pointer: " + pointer);
    rows = []

    rearrangeWorkDiv();

    for (var i = 0; i < 10; i++) {
        if ($('#currentWork-' + i).is(":checked")) {
            $('#todatediv' + i).css("display", "none");
        }
    }

    if ($('#declaration').is(":checked")) {
        $('#submitBtn').attr("disabled", false);
    }
    if ($('#presentDivisionId').val() !== "") {
        addDistrict();
    }
    if ($('#permanentDivisionId').val() !== "") {
        addPermanentDistrict();
    }


    function readPhotoURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#photo').attr('src', e.target.result);
                $('#photo-div').attr('style', 'padding-top: 10px');
            }

            reader.readAsDataURL(input.files[0]); // convert to base64 string
        } else {
            $('#photo-div').css('display', 'none');

        }
    }

    function readSignatureURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#signature').attr('src', e.target.result);
                $('#signature-div').attr('style', 'padding-top: 10px');
            }

            reader.readAsDataURL(input.files[0]); // convert to base64 string
        }
        else {
            $('#signature-div').css('display', 'none');
        }
    }

    $("#file_photo").change(function () {
        readPhotoURL(this);
    });
    $("#file_signature").change(function () {
        readSignatureURL(this);
    });

    //show modal
    //$('#myModal').modal('toggle');
});

//scroll into work experience
/*
    document.getElementById('workExperienceDivId').scrollIntoView(true);
*/

function addDistrict() {
    var mySelect = $('#presentDistrictId');
    var mySelect2 = $('#presentThanaId');
    var divisionId = $('#presentDivisionId').val();
    if (divisionId !== "") {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "getDistrict/" + divisionId,
            data: JSON.stringify({'divId': divisionId}),
            dataType: 'json',
            success: function (data) {
                mySelect.find('option').remove();
                var value = mySelect.data('id');
                if (value !== undefined) {
                    mySelect.append('<option value="">Select District</option>');
                    $.each(data, function () {
                        if (value == (this.id + "-" + this.districtName)) {
                            mySelect.append($('<option selected="selected"></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                            addThana();
                        } else mySelect.append($('<option></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                    });
                } else {
                    mySelect.append('<option selected="selected" value="">Select District</option>');
                    $.each(data, function () {
                        mySelect.append(
                            $('<option></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                    });
                }
            }
        });
    } else {
        mySelect.find('option').remove();
        mySelect2.find('option').remove();
    }
}

function addThana() {
    var mySelect = $('#presentThanaId');
    var districtId = $('#presentDistrictId').val();
    if (districtId !== "") {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "getThana/" + districtId,
            success: function (data) {
                mySelect.find('option').remove();
                var value = mySelect.data('id');
                if (value !== undefined) {
                    mySelect.append('<option value="">Select Thana</option>');
                    $.each(data, function () {
                        if (value == (this.id + "-" + this.thanaName))
                            mySelect.append($('<option selected="selected"></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));
                        else mySelect.append($('<option></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));

                    });
                } else {
                    mySelect.append('<option selected="selected" value="">Select Thana</option>');
                    $.each(data, function () {
                        mySelect.append(
                            $('<option></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));
                    });
                }
                checkOther();
            }
        });
    } else {
        mySelect.find('option').remove();
    }
}


function addPermanentDistrict() {
    var mySelect = $('#permanentDistrictId');
    var mySelect2 = $('#permanentThanaId');
    var divisionId = $('#permanentDivisionId').val();
    if (divisionId !== "") {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "getDistrict/" + divisionId,
            data: JSON.stringify({'divId': divisionId}),
            dataType: 'json',
            success: function (data) {
                mySelect.find('option').remove();
                var value = mySelect.data('id');
                if (value !== undefined) {
                    mySelect.append('<option value="">Select District</option>');
                    $.each(data, function () {
                        if (value == (this.id + "-" + this.districtName)) {
                            mySelect.append($('<option selected="selected"></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                            addPermanentThana();
                        } else mySelect.append($('<option></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                    });
                } else {
                    mySelect.append('<option selected="selected" value="">Select District</option>');
                    $.each(data, function () {
                        mySelect.append(
                            $('<option></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                    });
                }
            }
        });
    } else {
        mySelect.find('option').remove();
        mySelect2.find('option').remove();
    }
}

function addPermanentThana() {
    var mySelect = $('#permanentThanaId');
    var districtId = $('#permanentDistrictId').val();
    if (districtId !== "") {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "getThana/" + districtId,
            success: function (data) {
                mySelect.find('option').remove();
                var value = mySelect.data('id');
                if (value !== undefined) {
                    mySelect.append('<option value="">Select Thana</option>');
                    $.each(data, function () {
                        if (value == (this.id + "-" + this.thanaName))
                            mySelect.append($('<option selected="selected"></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));
                        else mySelect.append($('<option></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));
                    });
                } else {
                    mySelect.append('<option selected="selected" value="">Select Thana</option>');
                    $.each(data, function () {
                        mySelect.append(
                            $('<option></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));
                    });
                }
                checkOther();
            }

        });
    } else {
        mySelect.find('option').remove();
    }
}


function checkOther() {
    var thanaId = $('#presentThanaId').val();
    if (thanaId == "9999-Others") {
        $('.otherPresentThana').css("display", "block");
    } else {
        $('.otherPresentThana').css("display", "none");
    }

    var permanentThanaId = $('#permanentThanaId').val();
    if (permanentThanaId == "9999-Others") {
        $('.otherPermanentThana').css("display", "block");
    } else {
        $('.otherPermanentThana').css("display", "none");
    }

}

function enableSubmit(th) {
    if ($(th).is(":checked")) {
        $('#submitBtn').attr("disabled", false);
    } else if ($('#declaration').prop("checked") == false) {
        $('#submitBtn').attr("disabled", true);
    }
}

function hideToDate(th) {
    var index = th.id.split("-");
    console.log(index[1]);
    if ($(th).is(":checked")) {
        $('#toDate' + index[1]).val("");
        $('#todatediv' + index[1]).css("display", "none");
    } else if ($(th).prop("checked") == false) {
        $('#todatediv' + index[1]).css("display", "block");
    }
}


function addRow() {
    $('#addbtn').off("click");
    pointer++;
    if (pointer < 10) {
        for (var i = 0; i < 10; i++) {
            if ($('#visibility' + i).val() == 0) {
                $('#' + i).css("display", "block");
                $('#visibility' + i).val(1);
                for (var j = pointer; j > 0; j--) {
                    serial[j] = serial[j - 1]
                }
                serial[0] = i;
                break;
            }
        }
        $('.workRow').each(function () {
            rows[$(this).data('id')] = $(this);
        });
        $.each(serial, function (index, value) {
            $('#container').append(rows[value]);
        });
    } else {
        alert("You reached maximum 10 work experience. Don't need to add more work experience.")
    }
    console.log(serial + " pointer:" + pointer);
    $('#serialList').val(serial);
    $('#pointer').val(pointer);
    $('#addbtn').on("click");

}

/*function replaceData(index) {

    for (var i = index; i >= 0; i--) {
        if ($('#visibility' + index - 1).val() == 1) {
            $('#organizationName' + index).val($('#organizationName' + index - 1).val());
            $('#designation' + index).val($('#organizationName' + index - 1).val());
            $('#jobResponsibility' + index).val($('#organizationName' + index - 1).val());
            $('#toDate' + index).val($('#organizationName' + index - 1).val());
            $('#fromDate' + index).val($('#organizationName' + index - 1).val());
            $('#experienceFile' + index).val($('#organizationName' + index - 1).val());
        } else if ($('#visibility' + index - 1).val() == 0) {
            break;
        }
    }

}*/


function removeRow(ths) {
    $(ths).off("click");
    var index = ths.id;
    var position = serial.indexOf(parseInt(index));
    if (position < pointer) {
        for (var i = position; i < pointer; i++) {
            serial[i] = serial[i + 1];
        }
        serial[pointer] = parseInt(index);
        pointer--;
    } else {
        pointer--;
    }
    console.log(serial + " pointer:" + pointer);
    $('#serialList').val(serial);
    $('#pointer').val(pointer);
    $('#organizationName' + index).val("");
    $('#designation' + index).val("");
    $('#jobResponsibility' + index).val("");
    $('#toDate' + index).val("");
    $('#fromDate' + index).val("");
    $('#experienceFile' + index).val("");
    $('#' + index).css("display", "none");
    $('#visibility' + index).val(0);
    $(ths).on("click");
}


function rearrangeWorkDiv() {
    $('.workRow').each(function () {
        rows[$(this).data('id')] = $(this);
    });
    $.each(serial, function (index, value) {
        $('#container').append(rows[value]);
    });
    for (var i = 0; i < 9; i++) {
        if ($('#visibility' + i).val() == 1) {
            $('#' + i).css("display", "block");
        }

    }
}