$(document).ready(function () {
    $('.select2').select2({
        minimumResultsForSearch:5
    });
    $('.select2-city').select2({
        placeholder: '---Click to select---',
        minimumResultsForSearch:10,
        allowClear:true
    });

});
