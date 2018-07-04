definition(
    name: "Zone Motion Manager",
    singleInstance: true,
    namespace: "MikeMaxwell",
    author: "Mike Maxwell",
    description: "Installs and triggers a Simulated Motion Sensor using multiple physical motion sensors, optional inputs and triggers to enable zone.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Solution/areas.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Solution/areas@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Solution/areas@2x.png")


preferences {
    page(name: "mainPage", title: "Motion Control Zones", install: true, uninstall: true,submitOnChange: true) {
            section {
                    app(name: "childZones", appName: "zoneMotionChild", namespace: "MikeMaxwell", title: "Create New Motion Zone...", multiple: true)
            }
    }
}

def installed() {
    initialize()
}

def updated() {
    unsubscribe()
    initialize()
}

def initialize() {
    childApps.each {child ->
            log.info "Installed Zones: ${child.label}"
    }
}
