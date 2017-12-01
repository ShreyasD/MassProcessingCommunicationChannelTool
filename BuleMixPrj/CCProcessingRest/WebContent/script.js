/**
 * 
 */
var restURL="http://ccprocessingrest.mybluemix.net/rest/";
function UserAction() {
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET",restURL+"FetchChannelService/fetchChannels",false);
	xhttp.send();
	var response = JSON.parse(xhttp.responseText);
	// console.log(JSON.stringify(response.channels.channel[0]));
	var jResponseString = response.channels.channel;

	var tableRows = "";
	for (var i = 0; i < jResponseString.length; i++) {
		var obj = jResponseString[i];

		tableRows += '<tr class="odd gradeX">';
		tableRows += '<td>';
		tableRows += '<input type="checkbox" id="chkChannel' + i + '" index= '
				+ i + ' class=chkMchannel>';
		tableRows += '</td>';
		tableRows += '<td id="party' + i + '">';
		tableRows += obj.party;
		tableRows += '</td>';
		tableRows += '<td id="service' + i + '">';
		tableRows += obj.service;
		tableRows += '</td>';
		tableRows += '<td id="channelName' + i + '">';
		tableRows += obj.channelName;
		tableRows += '</td>';
		tableRows += '<td id="activationState' + i + '">';
		tableRows += obj.activationState;
		tableRows += '</td>';
		tableRows += '<td id="channelState' + i + '">';
		tableRows += obj.channelState;
		tableRows += '</td>';
		tableRows += '<td id="updateState' + i + '">';
		tableRows += '';
		tableRows += '</td>';
		tableRows += '</tr>';

		// console.log(tableRows);

	}
	document.getElementById('tableBody').innerHTML = tableRows;

}

/*
 * function setActivation(){ var cboxes =
 * document.getElementsByClassName('chkMchannel'); var len = cboxes.length; var
 * selectedData = ""; var channel="";
 * 
 * for (var i=0; i<len; i++) { if(cboxes[i].checked){ selectedData +=
 * '{"channels":{"channel":[{"party":"' +
 * document.getElementById("party"+i).innerHTML +',"service":'+
 * document.getElementById("service"+i).innerHTML
 * +',"channelName":'+document.getElementById("channelName"+i).innerHTML
 * +',"channelID":'
 * +',"activationState":'+document.getElementById("activationState"+i).innerHTML+',"shortLog":null}]}}';
 * 
 * 
 * channel+=document.getElementById("channelName"+i).innerHTML+"\n" ; } }
 * 
 * document.getElementById("channelList").innerHTML="The Channels Selected are:-<br>"+channel;
 * console.log("The Channels Selected are:-"+"\n"+channel); }
 */
// console.log(selectedData);
// console.log(JSON.stringify(selectedData));
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("setActive");

// Get the <span> element that closes the modal
var span = document.getElementById("closeActivation");

// When the user clicks on the button, open the modal
btn.onclick = function() {
	modal.style.display = "block";

	var cboxes = document.getElementsByClassName('chkMchannel');
	var len = cboxes.length;
	var channel = "";
	for (var i = 0; i < len; i++) {
		if (cboxes[i].checked) {
			channel += document.getElementById("channelName" + i).innerHTML
					+ "<br>";
		}
	}

	document.getElementById("channelList").innerHTML = "The Channels Selected are:-<br>"
			+ channel;
	console.log("The Channels Selected are:-" + "\n" + channel);
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

function activateStart() {
	callActivationAjax("STARTED");
}

function activateStop() {
	callActivationAjax("STOPPED");
}

function callActivationAjax(action) {
	var url = restURL+"ModifyChannelService/modifyChannels";

	var cboxes = document.getElementsByClassName('chkMchannel');
	var len = cboxes.length;

	var checkedIndex = 0;
	var selectedData = '{"channels":{"channel":[';
	for (var i = 0; i < len; i++) {
		if (cboxes[i].checked) {
			if (checkedIndex != 0) {
				selectedData += ",";
			}
			selectedData += '{"party":"'
					+ document.getElementById("party" + i).innerHTML
					+ '","service":"'
					+ document.getElementById("service" + i).innerHTML
					+ '","channelName":"'
					+ document.getElementById("channelName" + i).innerHTML
					+ '","channelID":"' + '","activationState":"' + action
					+ '","shortLog":null}';

			checkedIndex++;

		}
	}
	selectedData += "]}}"

	// var str =
	// '{"channels":{"channel":[{"party":"","service":"SFDC","channelName":"CC_SFDC_SOAP_USCP_Upsert_Rcv","channelID":"280151c86d1d3ca8953f9eb40683c521","activationState":"STARTED","channelState":"OK","shortLog":null,"updateState":null},{"party":"","service":"IP1_APPIAN","channelName":"Appian_SOAP_BoundarySetName_Snd","channelID":"f2ebcc351c3b33d394a2c7cb5f774f8c","activationState":"STARTED","channelState":"OK","shortLog":null,"updateState":null},{"party":"","service":"Test","channelName":"Test_JDBC_Rcv","channelID":"bb811819294c3a1d929bf478e8487405","activationState":"STARTED","channelState":"OK","shortLog":null,"updateState":null}]}}';

	console.log(selectedData);
	var xmlhttp = new XMLHttpRequest(); // new HttpRequest instance
	xmlhttp.open("POST", url, false);
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.setRequestHeader("Accept", "application/json");
	xmlhttp.send(selectedData);
	// console.log(xmlhttp.responseText);
	var response = JSON.parse(xmlhttp.responseText);

	for (var i = 0; i < len; i++) {
		for (var j = 0; j < response.channels.channel.length; j++) {
			if (cboxes[i].checked
					&& document.getElementById("channelName" + i).innerHTML === response.channels.channel[j].channelName) {
				console.log("response.channels.channel[j].channelName: "
						+ response.channels.channel[j].channelName);
				console
						.log('document.getElementById("channelName" + i).innerHTML: '
								+ document.getElementById("channelName" + i).innerHTML);
				console.log('response.channels.channel[j].updateState: '
						+ response.channels.channel[j].updateState);
				console
						.log('**************************************************'
								+ i);
				document.getElementById("updateState" + i).innerHTML = response.channels.channel[j].updateState;
				document.getElementById("activationState" + i).innerHTML = response.channels.channel[j].activationState;

				cboxes[i].checked = false;
				break;
			} else {
				// console.log('-------------------------------------------------'
				// + i);
				document.getElementById("updateState" + i).innerHTML = 'UPDATENOK';
			}
		}
	}

	var modal = document.getElementById('myModal');
	modal.style.display = "none";
	// console.log(JSON.stringify(response));
}

function saveToCsv() {
	var cboxes = document.getElementsByClassName('chkMchannel');
	var len = cboxes.length;
	var csvData = [ [ "Party", "Component", "Channel", "Activation State",
			"Channel State", "Update Result" ] ];
	for (var i = 0; i < len; i++) {
		var data = [ document.getElementById("party" + i).innerHTML,
				document.getElementById("service" + i).innerHTML,
				document.getElementById("channelName" + i).innerHTML,
				document.getElementById("activationState" + i).innerHTML,
				document.getElementById("channelState" + i).innerHTML,
				document.getElementById("updateState" + i).innerHTML ];

		csvData.push(data);
	}
	console.log(csvData.length);

	var csvContent = "data:text/csv;charset=utf-8,";
	csvData.forEach(function(infoArray, index) {
		dataString = infoArray.join(",");
		csvContent += index < csvData.length ? dataString + "\n" : dataString;

	});
	var encodedUri = encodeURI(csvContent);
	var link = document.createElement("a");
	link.setAttribute("href", encodedUri);
	link.setAttribute("download", "CCProcessing-" + Date.now() + ".csv");
	document.body.appendChild(link); // Required for FF

	console.log("Date.now(): " + Date.now());

	link.click();

}

function loadCsvFile() {
	console.log(document.getElementById("file input").files);
	var file = document.getElementById("file input").files[0];
	var reader = new FileReader();
	
	reader.onload = function(e) {
		bfile = e.target.result
		console.log('bfile' + bfile); // this shows bfile
		
		var tableRows = csvJSON(bfile);
		console.log('csvJsonData' + tableRows);
		
		document.getElementById('tableBody').innerHTML = "";
		document.getElementById('tableBody').innerHTML = tableRows;

		
	}
	var content = reader.readAsText(file);
	console.log('content' + content);
	
	var modal = document.getElementById('myModaCsv');
	modal.style.display = "none";
	

	// $.ajax({
	// url: "C:\Users\IBM_ADMIN\Downloads\CCProcessing-1494118786982.csv",
	// async: false,
	// success: function (csvd) {
	// var data = $.csv.toArrays(csvd);
	// console.log(data);
	// },
	// dataType: "text",
	// complete: function () {
	// // call a function on complete
	// }
	// });
}

function csvJSON(csv) {
    var lines = csv.split("\n");
    
    var tableRows = "";
	for (var i = 1; i < lines.length-1; i++) {
		var columns = lines[i].split(",");
			
			tableRows += '<tr class="odd gradeX">';
			tableRows += '<td>';
			tableRows += '<input type="checkbox" id="chkChannel' + i + '" index= '
					+ i + ' class=chkMchannel>';
			tableRows += '</td>';
			tableRows += '<td id="party' + i + '">';
			tableRows += columns[0];
			tableRows += '</td>';
			tableRows += '<td id="service' + i + '">';
			tableRows += columns[1];
			tableRows += '</td>';
			tableRows += '<td id="channelName' + i + '">';
			tableRows += columns[2];
			tableRows += '</td>';
			tableRows += '<td id="activationState' + i + '">';
			tableRows += columns[3];
			tableRows += '</td>';
			tableRows += '<td id="channelState' + i + '">';
			tableRows += columns[4];
			tableRows += '</td>';
			tableRows += '<td id="updateState' + i + '">';
			tableRows += columns[5];
			tableRows += '</td>';
			tableRows += '</tr>';
	}
		
	return tableRows;
 }

function clearAll(){
	document.getElementById('tableBody').innerHTML = "";
}



var modal1 = document.getElementById('myModaCsv');

//Get the button that opens the modal
var btn1 = document.getElementById("pickCsvFile");

//Get the <span> element that closes the modal
var span1 = document.getElementById("closeCsv");

//When the user clicks on the button, open the modal
btn1.onclick = function() {
	modal1.style.display = "block";

	var cboxes = document.getElementsByClassName('chkMchannel');
	var len = cboxes.length;
	var channel = "";
	for (var i = 0; i < len; i++) {
		if (cboxes[i].checked) {
			channel += document.getElementById("channelName" + i).innerHTML
					+ "<br>";
		}
	}

	document.getElementById("channelList").innerHTML = "The Channels Selected are:-<br>"
			+ channel;
	console.log("The Channels Selected are:-" + "\n" + channel);
}

//When the user clicks on <span> (x), close the modal
span1.onclick = function() {
	modal1.style.display = "none";
}