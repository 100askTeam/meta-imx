FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI_append_mx6 = " file://0001-Add-Vivante-EGL-and-GAL2D-compositor-support.patch \
                       file://0002-Fix-for-wrong-FPS-throttling-when-multib.patch \
                       file://0003-Performance-Optimisation-for-single-buff.patch \
                       file://weston.sh "

PACKAGECONFIG_append_mx6sx = " egl cairo-glesv2"

EXTRA_OEMAKE_append_mx6sx = " \
    COMPOSITOR_LIBS="-lGLESv2 -lEGL -lGAL -lwayland-server -lxkbcommon -lpixman-1" \
    FB_COMPOSITOR_LIBS="-lGLESv2 -lEGL -lwayland-server -lxkbcommon" \
    "

do_install_append_mx6 () {
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${WORKDIR}/weston.sh ${D}${sysconfdir}/profile.d/
}

FILES_${PN}_append_mx6 = " ${sysconfdir}/profile.d/weston.sh"

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
