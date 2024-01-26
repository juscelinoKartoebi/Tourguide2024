loadDrivers();
function loadDrivers() {
	let DRIVERS_URL = "http://localhost:8085/tourguide/api/driver/all";

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {

			let driverDataList = JSON.parse(this.responseText);

			let headers = ["Id", "Name", "Date of birth"];
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

			driverDataList.forEach(driver => {

				let row = document.createElement("tr");
				headers.forEach(header => {
					if (header == "Id") {
						let cell = document.createElement("td");
						let textNode = document.createTextNode(driver.id);
						cell.appendChild(textNode);
						row.appendChild(cell);
					}
					if (header == "Name") {
						let cell = document.createElement("td");
						let textNode = document.createTextNode(driver.name);
						cell.appendChild(textNode);
						row.appendChild(cell);
					}
					if (header == "Date of birth") {
						let cell = document.createElement("td");
						let textNode = document.createTextNode(driver.dob);
						cell.appendChild(textNode);
						row.appendChild(cell);
					}
				});
				// headers.forEach(header => {

				// });
				// headers.forEach(header => {

				// });





				// Object.values(driver).forEach(text => {
				// 	let cell = document.createElement("td");

				// 	let textNode = document.createTextNode(text);
				// 	cell.appendChild(textNode);
				// 	row.appendChild(cell);
				// });
				table.appendChild(row);
			});


			var divContainer = document.getElementById("driverData");
			divContainer.appendChild(table);

			//

			// for(let index = 0; index < driverDataList.length; index++){

			//     driverList += "<br/>" + "Name: " + driverDataList[index].name + "<br/>" + "Dob: " + driverDataList[index].dob + "<br/>"
			// }
			// document.getElementById("driverData").innerHTML = driverList;
		}
	};
	xhttp.open("GET", DRIVERS_URL, true);
	xhttp.send();
}



function submitDriver() {


	const file = document.forms["driverForm"]["myFile"].value;
	if (file == "") {
		var verwerken = true;

		let driverName = document.forms["driverForm"]["fname"].value;
		if (verwerken) {
			if (driverName == "") {
				document.driverForm.fname.focus();
				alert("Please provide name.");
				verwerken = false;
				return verwerken;
			}
		}

		let driverDob = document.forms["driverForm"]["dob"].value;
		if (verwerken) {
			if (driverDob == "") {
				document.driverForm.dob.focus();
				alert("Please provide date of birth.");
				verwerken = false;
				return verwerken;
			}
		}

		if (verwerken) {
			let POST_URL = "http://localhost:8085/tourguide/api/driver/insert";

			var driver = {
				name: driverName,
				dob: driverDob
			};


			postAjax(POST_URL, driver);
			alert("Saved successfully.");

			//reset form
			var form = document.forms["driverForm"];
			form.reset();
			refreshTable();


		}
	} else {

		const URL = "http://localhost:8085/tourguide/api/driver/file";

		const fileInput = document.getElementById("myFile");
		var files = fileInput.files;

		uploadFile(URL, files[0]);

		alert("File uploaded");
		refreshTable();
	}

}

function postAjax(url, data) {

	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	xhr.open('POST', url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify({ name: data.name, dob: data.dob }));
	return xhr;
}

function uploadFile(url, thisFile) {
	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	xhr.open('POST', url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(thisFile);
	return xhr;
}

function refreshTable() {
	console.log("here");
	let div = document.getElementById("driverData");
	while (div.firstChild) {
		div.removeChild(div.lastChild);
	}
	loadDrivers();
}