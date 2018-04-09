/**
 *  handler test
 *
 *  Copyright 2015 Keith Croshaw
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Door Unlocked Warning Light Rev A",
    namespace: "keithcroshaw",
    author: "Keith Croshaw",
    description: "Door Unlocked Warning Light",
    category: "My Apps",
    iconUrl: "https://graph.api.smartthings.com/api/devices/icons/st.Home.home3-icn?displaySize=2x",
    iconX2Url: "https://graph.api.smartthings.com/api/devices/icons/st.Home.home3-icn?displaySize=2x",
    iconX3Url: "https://graph.api.smartthings.com/api/devices/icons/st.Home.home3-icn?displaySize=2x")


preferences {
	section("Monitor this lock") {
        input "lock", "capability.lock", title:"Which lock?"
    }
    
    section("Turn on this switch") {
        input "switch1", "capability.switch", title:"Which switch?"
    }
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
 
 subscribe(lock, "lock", LockHandler)
 
 def lockState = lock.currentValue("lock")
    log.debug "lockState is ${lockState}"
    
    if (lockState == "locked") {
    	switch1?.off()
        log.debug "In Locked Logic"
    } else {
    	switch1?.on()
    }


}

def LockHandler(evt) {
	log.debug "Handler Fired"
    
    def lockState = lock.currentValue("lock")
    log.debug "lockState is ${lockState}"
    
    if (lockState == "locked") {
    	switch1?.off()
        log.debug "In Locked Logic"
    } else {
    	switch1?.on()
    }
}
