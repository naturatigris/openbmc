SUMMARY = "OpenBMC Buttons"
DESCRIPTION = "OpenBMC All buttons"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"
DEPENDS += " \
    systemd \
    sdbusplus \
    phosphor-dbus-interfaces \
    phosphor-logging \
    "
SRCREV = "4250785723d92b6578176a2d961d47ab66e08124"
PACKAGECONFIG ??= "signals handler"
PACKAGECONFIG[signals] = ",,gpioplus nlohmann-json,"
PACKAGECONFIG[handler] = ",,,${VIRTUAL-RUNTIME_obmc-host-state-manager} ${VIRTUAL-RUNTIME_obmc-chassis-state-manager}"
PV = "1.0+git${SRCPV}"
PR = "r1"

SRC_URI += "git://github.com/openbmc/phosphor-buttons.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SYSTEMD_PACKAGES = "${BUTTON_PACKAGES}"
SYSTEMD_SERVICE:${PN}-signals = "xyz.openbmc_project.Chassis.Buttons.service"
SYSTEMD_SERVICE:${PN}-handler = "phosphor-button-handler.service"

inherit meson pkgconfig systemd

FILES:${PN}-signals = "${bindir}/buttons"
FILES:${PN}-handler = "${bindir}/button-handler"

ALLOW_EMPTY:${PN} = "1"

BUTTON_PACKAGES = "${PN}-signals ${PN}-handler"

PACKAGE_BEFORE_PN += "${BUTTON_PACKAGES}"
