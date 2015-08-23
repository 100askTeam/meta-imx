# Copyright (C) 2013, 2014 Freescale Semiconductor

SUMMARY = "Tool to trace gpu memory info"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://usr/include/g2d.h;endline=7;md5=53b61e015f8e1c386057c5ba8b081d53"

inherit fsl-eula-unpack

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

DEPENDS += "gpu-viv-bin-mx6q"

SRC_URI = "${FSL_MIRROR}/gpu-viv-g2d-${PV}.bin;fsl-eula=true"
S="${WORKDIR}/gpu-viv-g2d-${PV}"

SRC_URI[md5sum] = "bc55f8fb4752e8fd0cd60ceba4e65f3f"
SRC_URI[sha256sum] = "1beb977d77d2993c315ef69bbe062440c0ed2483790f79cb92bfd958544b04e1"

do_install () {
    install -d ${D}${bindir}
    cp -axr ${S}/usr/bin/* ${D}${bindir}
}

FILES_${PN} += "  ${bindir}/gmem_info "

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
COMPATIBLE_MACHINE = "(mx6)"
