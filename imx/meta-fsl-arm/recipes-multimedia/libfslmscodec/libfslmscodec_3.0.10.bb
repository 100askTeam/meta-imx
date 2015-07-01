# Copyright (C) 2013 Freescale Semiconductor

# Note this codec requires special licensing with Freescale marketing to use this codec
DESCRIPTION = "Microsoft component library, including WMA and WMV789 decoder libraries"
SECTION = "multimedia"
LICENSE = "Proprietary"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=93b784b1c11b3fffb1638498a8dde3f6"

DEPENDS = "libfslcodec"

inherit fsl-eula-unpack autotools pkgconfig

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"
S = "${WORKDIR}/${PN}-${PV}"

SRC_URI[md5sum] = "7fb9c530363372ab8cde96930d7aec0c"
SRC_URI[sha256sum] = "ef2c00bc1407a851bb1aa6ce518eaef7596a32635ff6f5405b2bce13add0a7cb"

# Choose between Soft Float-Point and Hard Float-Point
EXTRA_OECONF = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--enable-fhw', '', d)}"

do_install_append() {
    # LTIB move the files around or gst-fsl-plugin won't find them
    mv $p ${D}${libdir}/imx-mm/audio-codec/*.so* ${D}${libdir}
    mv $p ${D}${libdir}/imx-mm/video-codec/*.so* ${D}${libdir}
	rmdir ${D}${libdir}/imx-mm/video-codec
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGES += "${PN}-testapps"

python populate_packages_prepend() {
    # FIXME: All binaries lack GNU_HASH in elf binary but as we don't have
    # the source we cannot fix it. Disable the insane check for now.
    for p in d.getVar('PACKAGES', True).split():
        d.setVar("INSANE_SKIP_%s" % p, "ldflags textrel libdir")
}

FILES_${PN} += "${libdir}/imx-mm/audio-codec/wrap/*${SOLIBS} \
"
FILES_${PN}-dev += "${libdir}/imx-mm/audio-codec/wrap/*${SOLIBSDEV} \
	            ${libdir}/pkgconfig/*.pc ${includedir}/imx-mm/*"
# Add examples to -testapps PACKAGE
FILES_${PN}-testapps += "${datadir}/imx-mm/*"

COMPATIBLE_MACHINE = "(mx28|mx5|mx6)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
