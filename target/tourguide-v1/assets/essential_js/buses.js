loadBuses();
function loadBuses() {
    let BUSES_URL = "http://localhost:8085/tourguide/api/bus/all";

    let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let busesDataList = JSON.parse(this.responseText);

            let headers = ["Id", "License plate"];
			let table = document.createElement("table");
			table.setAttribute("id", "dataTable");
			table.setAttribute("class", "table table-striped");
			table.setAttribute("data-toggle", "table");
			let headerRow = document.createElement("tr");

			headers.forEach(header => {
				let thisHeader = document.createElement("th");
				let textNode = document.createTextNode(header);
				thisHeader.appendChild(textNode);
				headerRow.appendChild(thisHeader);
			});

			table.appendChild(headerRow);

            busesDataList.forEach(bus => {

				let row = document.createElement("tr");
				headers.forEach(header => {
					if (header == "Id") {
						let cell = document.createElement("td");
						let textNode = document.createTextNode(bus.id);
						cell.appendChild(textNode);
						row.appendChild(cell);
					}
					if (header == "License plate") {
						let cell = document.createElement("td");
						let textNode = document.createTextNode(bus.licensePlate);
						cell.appendChild(textNode);
						row.appendChild(cell);
					}
					
				});
				
				table.appendChild(row);
			});

            var divContainer = document.getElementById("busData");
            divContainer.appendChild(table);
        }
    };
    xhttp.open("GET", BUSES_URL, true);
	xhttp.send();
}

function submitBus() {

	var verwerken = true;

	let licensePlateValue = document.forms["busForm"]["fplate"].value;
	if (verwerken) {
		if (licensePlateValue == "") {
			document.busForm.fplate.focus();
			alert("Please provide license plate.");
			verwerken = false;
			return verwerken;
		}
	}


	if (verwerken) {
		let POST_URL = "http://localhost:8085/tourguide/api/bus/insert";

		var bus = {
			licensePlate: licensePlateValue
		};


		postAjax(POST_URL, bus);
		alert("Saved successfully.");

		//reset form
		var form = document.forms["busForm"];
		form.reset();

		refreshTable() ;
	}

}

function postAjax(url, data) {

	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	xhr.open('POST', url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify({ bus: data.licensePlate}));
	return xhr;
}

function refreshTable() {
	let div = document.getElementById("busData");
	while (div.firstChild) {
		div.removeChild(div.lastChild);
	}
	loadBuses();
}