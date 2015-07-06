# Copyright (C) 2014 Freescale Semiconductor
# Note this codec requires special licensing through Freescale marketing
DESCRIPTION = "This package provides REAL Media decoder and parser library"
SECTION = "multimedia"
LICENSE = "Proprietary"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=93b784b1c11b3fffb1638498a8dde3f6"

DEPENDS = "libfslcodec"

inherit fsl-eula-unpack autotools pkgconfig

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"

S = "${WORKDIR}/${PN}-${PV}"

SRC_URI[md5sum] = "cdc4de7acd105df9e4523e1df084f2dc"
SRC_URI[sha256sum] = "e846f51acd21819b52019be37c06cbcc0cb2aca2d1c8057371891f55a30619d2"

# Choose between Soft Float-Point and Hard Float-Point
EXTRA_OECONF = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--enable-fhw', '', d)}"

do_install_append() {
    # LTIB move the files around or gst-fsl-plugin won't find them
    mv $p ${D}${libdir}/imx-mm/audio-codec/*.so* ${D}${libdir}
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGES += "${PN}-testapps"

# FIXME: All binaries lack GNU_HASH in elf binary but as we don't have
# the source we cannot fix it. Disable the insane check for now.
INSANE_SKIP_${PN} = "ldflags textrel already-stripped"
INSANE_SKIP_${PN}-testapps = "ldflags"

FILES_${PN} += "${libdir}/imx-mm/audio-codec/wrap/*${SOLIBS} \
                ${libdir}/imx-mm/*/*${SOLIBS}"
FILES_${PN}-dev += "${libdir}/pkgconfig/*.pc ${includedir}/imx-mm/* \
                    ${libdir}/imx-mm/audio-codec/wrap/*${SOLIBSDEV} \
                    ${libdir}/imx-mm/*/*${SOLIBSDEV}"

# Add examples to -testapps PACKAGE
FILES_${PN}-testapps += "${datadir}/imx-mm/*"

COMPATIBLE_MACHINE = "(mx6)"
PACKAGE_ARCH = "${MACHINE_SOCARCH}"

