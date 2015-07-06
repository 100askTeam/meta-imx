# Copyright (C) 2013-14 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

require gst-fsl-plugin.inc

EXTRA_OECONF += " CROSS_ROOT=${PKG_CONFIG_SYSROOT_DIR}"

SRC_URI = "${FSL_MIRROR}/gst-fsl-plugins-${PV}.tar.gz"
S = "${WORKDIR}/gst-fsl-plugins-${PV}"

SRC_URI[md5sum] = "2770724e98a85208be44cb43c7e58a6d"
SRC_URI[sha256sum] = "3c6c41a9f7fffd406e6b42d0e57658762d7cc0a7f1abd300a053705709804c4c"

DEPENDS_append = " gstreamer gst-plugins-base"

PACKAGECONFIG[wmv9mpdec] +="--enable-wmv9mpdec,--disable-wmv9mpdec,${MSDEPENDS},${MSDEPENDS}"
PACKAGECONFIG[wmv78dec] +="--enable-wmv78dec,--disable-wmv78dec,${MSDEPENDS},${MSDEPENDS}"

FILES_${PN} = "${libdir}/gstreamer-0.10/*.so ${datadir}"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.la ${libdir}/pkgconfig/*.pc"
FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/*.a"
FILES_${PN}-gplay = "${bindir}/gplay"
FILES_${PN}-libgplaycore = "${libdir}/libgplaycore${SOLIBS}"
FILES_${PN}-libgstfsl = "${libdir}/libgstfsl-0.10${SOLIBS}"

COMPATIBLE_MACHINE = "(mx28|mx5|mx6)"
