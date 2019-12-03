FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "59fcde2e722ea6f7342ac9bf956f5f0b2911e7fd"

SRC_URI_append = " file://0001-tst_client.cpp-Fix-no-opengl-build.patch \
"
SRC_URI_append_mx6sl = " file://0001-hardwareintegration-Do-not-include-shm-emulation-ser.patch \
"

PACKAGECONFIG_remove_mx6 = "xcomposite-egl xcomposite-glx"
PACKAGECONFIG_remove_mx6sl = "wayland-egl"
PACKAGECONFIG_remove_mx7 = "xcomposite-egl xcomposite-glx"
PACKAGECONFIG_remove_mx8 = "xcomposite-egl xcomposite-glx"

do_install_append() {
if ls ${D}${libdir}/pkgconfig/Qt5*.pc >/dev/null 2>&1; then
    sed -i 's,-L${STAGING_DIR_HOST}/usr/lib,,' ${D}${libdir}/pkgconfig/Qt5*.pc
fi
}
