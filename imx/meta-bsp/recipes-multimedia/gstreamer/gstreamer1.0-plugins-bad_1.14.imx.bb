require recipes-multimedia/gstreamer/gstreamer1.0-plugins-bad.inc

FILESEXTRAPATHS_prepend := "${BSPDIR}/sources/poky/meta/recipes-multimedia/gstreamer/files:"
FILESEXTRAPATHS_prepend := "${BSPDIR}/sources/poky/meta/recipes-multimedia/gstreamer/gstreamer1.0-plugins-bad:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 "

DEPENDS_append_imxgpu2d = " virtual/libg2d"
DEPENDS_append_mx8 = " libdrm"

PACKAGECONFIG_append_mx6q = " opencv"
PACKAGECONFIG_append_mx6qp = " opencv"
PACKAGECONFIG_append_mx8 = " opencv kms"

PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,wayland-native wayland wayland-protocols libdrm"

# Disable introspection to fix [GstGL-1.0.gir] Error
EXTRA_OECONF_append = " --disable-introspection"

EXTRA_OECONF_remove = " --disable-sdl --disable-nas --disable-libvisual --disable-xvid --disable-mimic \
                        --disable-pvr --disable-sdltest --disable-wininet --disable-timidity \
                        --disable-linsys --disable-sndio --disable-apexsink \
                        --disable-libssh2 --disable-qt --disable-schro --disable-cocoa --disable-gtk3 \
                        --enable-egl --disable-opengl --enable-gles2 \
"

SRC_URI_remove = "file://0001-Prepend-PKG_CONFIG_SYSROOT_DIR-to-pkg-config-output.patch"

EXTRA_OECONF_remove = "WAYLAND_PROTOCOLS_SYSROOT_DIR=${RECIPE_SYSROOT}"

GST1.0-PLUGINS-BAD_SRC ?= "gitsm://source.codeaurora.org/external/imx/gst-plugins-bad.git;protocol=https"
SRCBRANCH = "imx-1.14.x"

SRC_URI = " \
    ${GST1.0-PLUGINS-BAD_SRC};branch=${SRCBRANCH} \
"

SRCREV = "797029384ed24bce7da1b63dfea53b4d2357efb1"

# This remove "--exclude=autopoint" option from autoreconf argument to avoid
# configure.ac:30: error: required file './ABOUT-NLS' not found
EXTRA_AUTORECONF = ""

PACKAGE_ARCH_imxpxp = "${MACHINE_SOCARCH}"
PACKAGE_ARCH_mx8 = "${MACHINE_SOCARCH}"

# Fix libgstbadion-1.0.so.0 which is under built directory cannot be found
do_compile_prepend () {
    export GIR_EXTRA_LIBS_PATH="${B}/gst-libs/gst/ion/.libs"
}

PV = "1.14.0.imx"

S = "${WORKDIR}/git"

