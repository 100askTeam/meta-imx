# Copyright (C) 2016 Freescale Semiconductor
# Copyright 2017-2018 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "GPU G2D library and apps for imx6 and imx7ulp"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=75abe2fa1d16ca79f87cde926f05f72d"

DEPENDS += "libgal-imx"
PROVIDES += "virtual/libg2d"

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"

S="${WORKDIR}/${PN}-${PV}"

inherit fsl-eula-unpack

SRC_URI[md5sum] = "71776e469bce2af2a69c8372b70cbf89"
SRC_URI[sha256sum] = "9687698304808877058e43bb74d78243d3f893518c44eacaad2a5d10944a2482"

do_install () {

    install -d ${D}${libdir}
    install -d ${D}${includedir}

    cp ${S}/g2d/usr/lib/*.so* ${D}${libdir}
    cp -Pr ${S}/g2d/usr/include/* ${D}${includedir}
    cp -r ${S}/gpu-demos/opt ${D}
}

RDEPENDS_${PN} = "libgal-imx"

FILES_${PN} = "${libdir}/libg2d* /opt"
FILES_${PN}-dev = "${includedir}"
INSANE_SKIP_${PN} = "ldflags"

# Compatible only with i.MX DPU-blit
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_imxdpublit = "${MACHINE}"
