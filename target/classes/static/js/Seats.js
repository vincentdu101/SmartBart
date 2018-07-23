var Seats = (function(){

    function parseDate(date) {
        var newDate = new Date(date.year, date.monthValue - 1, date.dayOfMonth, date.hour, date.minute, date.second);
        return newDate.toLocaleString();
    }

    function updateView(tableId, seatMap) {
        var $seats = $(".table-row[data-table-id='" + tableId + "']").find(".seat-table");
        $seats.empty().append("<div>" +
        "<h4>Seats Update</h4>" +
        "<h6>Special Reserved Seats - " + seatMap.SPECIAL_RESERVED_SEAT + "/6 Taken</h6>" +
        "<h6>Regular Seats - " + seatMap.REGULAR_SEAT + "/10 Taken</h6>" +
        "<h6>Table Seats - " + seatMap.TABLE_SEAT + "/4 Taken</h6>" +
        "</div>");
    }

    function updateTable(data) {
        var seatMap = {"SPECIAL_RESERVED_SEAT": 0, "REGULAR_SEAT": 0, "TABLE_SEAT": 0};
        for (var i = 0; i < data.length; i++) {
            var seat = data[i];
            if (seat.taken) {
                seatMap[seat.seatType] += 1;
            }
        }
        updateView(data[0].trainId, seatMap);
    }

    return {

        update: function(data) {
            updateTable(data);
        }

    };

}());