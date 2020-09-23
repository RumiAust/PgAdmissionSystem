$(document).ready(function () {
    function readPhotoURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#photo').attr('src', e.target.result);
                $('#photo-div').attr('style', '');
            }

            reader.readAsDataURL(input.files[0]); // convert to base64 string
        }
    }

    function readSignatureURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#signature').attr('src', e.target.result);
                $('#signature-div').attr('style', '');
            }

            reader.readAsDataURL(input.files[0]); // convert to base64 string
        }
    }

    $("#file_photo").change(function () {
        readPhotoURL(this);
    });
    $("#file_signature").change(function () {
        readSignatureURL(this);
    });

    //show modal
    $('#myModal').modal('toggle');
});

//scroll into work experience
document.getElementById('workExperienceDivId').scrollIntoView(true);

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
                mySelect.append('<option selected="selected" value="">Select District</option>');
                $.each(data, function () {
                    console.log(this);
                    mySelect.append(
                        $('<option></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                });
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
                mySelect.append('<option selected="selected" value="">Select Thana</option>');
                $.each(data, function () {
                    console.log(this);
                    mySelect.append(
                        $('<option></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));
                });
            }
        });
    }else {
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
                mySelect.append('<option selected="selected" value="">Select District</option>');
                $.each(data, function () {
                    console.log(this);
                    mySelect.append(
                        $('<option></option>').val(this.id + "-" + this.districtName).html(this.districtName));
                });
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
                mySelect.append('<option selected="selected" value="">Select Thana</option>');
                $.each(data, function () {
                    console.log(this);
                    mySelect.append(
                        $('<option></option>').val(this.id + "-" + this.thanaName).html(this.thanaName));
                });
            }
        });
    }else {
        mySelect.find('option').remove();
    }
}