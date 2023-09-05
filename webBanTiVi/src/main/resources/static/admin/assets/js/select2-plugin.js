$(document).ready(function () {
    $('.select2-city').select2({
        placeholder: '---Click to select---',
        minimumResultsForSearch:10,
        allowClear:true,
    });
    $('.select2-gender').select2({
        minimumResultsForSearch:Infinity
    });
    $('.select2').select2({
        minimumResultsForSearch:5,
    });
});
