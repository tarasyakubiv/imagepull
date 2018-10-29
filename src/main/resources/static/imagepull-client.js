$(document).ready(function() {

    $("#range-form").submit(function( event ) {

        // Don't submit the form normally
        event.preventDefault();

        // Get some values from elements on the page
        var $form = $( this ),
            startDate = $form.find( "input[name='range-start']" ).val(),
            endDate = $form.find( "input[name='range-end']" ).val();

        // Compose the data in the format that the API is expecting
        var data = { start: startDate, end: endDate };

        // Send the data using post
        $.ajax({
            url: '/imagepull',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function(data){
         	   $('.images-row').empty();
               data.forEach(function(row) {
                   $('.images-row').append('<div style="float:left"><a href="' + row.image + '" target="_blank"><img src="'+ row.thumbImage + 
                		   '" alt="' + row.threadNumber + '_' + row.postNumber + '_' + row.name + '"></a></div>');
               });
            }
        });
    });
});
