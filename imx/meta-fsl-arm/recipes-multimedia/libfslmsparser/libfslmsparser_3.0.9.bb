# Copyright (C) 2013 Freescale Semiconductor

# This parser requires special licensing with Freescale marketing
DESCRIPTION = "Microsoft compnent library, including WMA and WMV789 decoder libraries"
SECTION = "multimedia"
LICENSE_FLAGS = "license_${PN}-${PV}"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=93b784b1c11b3fffb1638498a8dde3f6"

DEPENDS = "libfslparser"

inherit fsl-eula-unpack autotools pkgconfig

SRC_URI = "${FSL_MIRROR}/${PN}-3.10.9-1.0.0.bin;fsl-eula=true"
S = "${WORKDIR}/${PN}-3.10.9-1.0.0"

SRC_URI[md5sum] = "2c80729504a35c9e68455585fe5c2e7c"
SRC_URI[sha256sum] = "ec1483927084970e7e839ab42749bec2a96fec6b71ca2b5995d884e0ab1b9478"

# Choose between Soft Float-Point and Hard Float-Point
EXTRA_OECONF = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--enable-fhw', '--enable-fsw', d)}"

# FIXME: All binaries lack GNU_HASH in elf binary but as we don't have
# the source we cannot fix it. Disable the insane check for now.
INSANE_SKIP_${PN} = "ldflags textrel"
INSANE_SKIP_${PN}-dev = "ldflags"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# FIXME: gst-fsl-plugin looks for the .so files so we need to deploy those
FILES_${PN} += "${libdir}/imx-mm/*/*${SOLIBS}"
FILES_${PN}-dev += " ${libdir}/imx-mm/*/*${SOLIBSDEV}"

COMPATIBLE_MACHINE = "(mx28|mx5|mx6)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
