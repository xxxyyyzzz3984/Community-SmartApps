/**
 *  Pause Music When No Motion
 *
 *  Copyright 2018 Alan Moore
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
    name: "Pause Music When No Motion",
    namespace: "mrmoorey",
    author: "Alan Moore",
    description: "Pause music when no motion detected. This is the parent SmartApp allowing multiple automations",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Electronics/electronics16-icn@2x.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Electronics/electronics16-icn@2x.png",
    iconX3Url: "http://cdn.device-icons.smartthings.com/Electronics/electronics16-icn@2x.png",
    singleInstance: true)


preferences {
    page(name: "mainPage", title: "Existing Music Players", install: true, uninstall: true) {
        if(state?.installed) {
            section("Add a New Music Player Automation") {
                app(name: "pauseMusicChild", namespace: "mrmoorey", appName: "Pause Music Child", title: "New Music Player", page: "mainPage", multiple: true, install: true)
            }
        } else {
            section("Initial Install") {
                paragraph "This SmartApp installs Pause Music When No Motion so you can add multiple child music player automations. Click install / done, then go to SmartApps in the flyout menu to add or edit speakers."
            }
        }
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
    state.installed = true
}