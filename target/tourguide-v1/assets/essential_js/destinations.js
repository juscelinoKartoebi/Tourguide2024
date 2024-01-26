loadDestinations();
function loadDestinations() {
	let DESTINATION_URL = "http://localhost:8085/tourguide/api/destination/all";

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {

			let destinationDataList = JSON.parse(this.responseText);
			let destinationList = "";


			let headers = ["Id", "Destination"];
			let table = document.createElement("table");
			table.setAttribute("id", "dataTable");
			let headerRow = document.createElement("tr");

			headers.forEach(header => {
				let thisHeader = document.createElement("th");
				let textNode = document.createTextNode(header);
				thisHeader.appendChild(textNode);
				headerRow.appendChild(thisHeader);
			});

			table.appendChild(headerRow);

			destinationDataList.forEach(destination => {
				let row = document.createElement("tr");
				Object.values(destination).forEach(text => {
					let cell = document.createElement("td");
					let textNode = document.createTextNode(text);
					cell.appendChild(textNode);
					row.appendChild(cell);
				});
				table.appendChild(row);
			});


			var divContainer = document.getElementById("destinationData");
			divContainer.appendChild(table);

	
		}
	};
	xhttp.open("GET", DESTINATION_URL, true);
	xhttp.send();
}



function submitDestination() {

	var verwerken = true;

	let destinationName = document.forms["destinationForm"]["fdestination"].value;
	if (verwerken) {
		if (destinationName == "") {
			document.destinationForm.fdestination.focus();
			alert("Please provide destination.");
			verwerken = false;
			return verwerken;
		}
	}


	if (verwerken) {
		let POST_URL = "http://localhost:8085/tourguide/api/destination/insert";

		var destination = {
			destination: destinationName
		};


		postAjax(POST_URL, destination);
		alert("Saved successfully.");

		//reset form
		var form = document.forms["destinationForm"];
		form.reset();

		refreshTable() ;

		//reload table
		// var div = document.getElementById("driverData");
		// var table = document.getElementById("driverTable");
		// table.remove();
		// div.removeChild(table);
		// createTable();
	}

}

function postAjax(url, data) {

	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	xhr.open('POST', url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify({ destination: data.destination}));
	return xhr;
}
function refreshTable() {
	let div = document.getElementById("destinationData");
	while (div.firstChild) {
		div.removeChild(div.lastChild);
	}
	loadDestinations();
}
