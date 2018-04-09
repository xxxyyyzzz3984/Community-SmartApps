/**
 *	Take out Dog SmartDevice Type
 *
 *	Author: Chuck Pearce
 *	Date: 2015-03-16
 */
metadata {
	definition (name: "Take Out Dog", namespace: "chuck-pearce", author: "Chuck Pearce") {
		capability "Polling"
		capability "Switch"
	}

	simulator {	}

	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state("off", label: 'Off', action: "switch.on", icon: "st.Home.home3-icn", backgroundColor: "#ffffff", nextState: "on")
			state("on", label: 'On', action: "switch.off", icon: "st.Home.home3-icn", backgroundColor: "#79b821", nextState: "off")
		}
		main "switch"
		details(["switch"])
	}
}

def parse(String description) { }

def on() {
	parent.enable()
	updateDeviceStatus(1)
}
def off() {
	parent.disable()
	updateDeviceStatus(0)
}

def poll() {

}

def updateDeviceStatus(status) {
	if (status == 0) { 
		sendEvent(name: "switch", value: "off", display: true, descriptionText: device.displayName + " is off") 
	}   
	if (status == 1) {
		sendEvent(name: "switch", value: "on", display: true, descriptionText: device.displayName + " is on") 
	}
}
