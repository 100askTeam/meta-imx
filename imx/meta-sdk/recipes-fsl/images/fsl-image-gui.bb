DESCRIPTION = "Freescale Image"
LICENSE = "MIT"

inherit core-image
# require recipes-fsl/images/fsl-image-multimedia.bb

IMAGE_FEATURES += " splash"
IMAGE_FEATURES += "${@base_contains('DISTRO_FEATURES', 'x11', \
                            ' package-management x11-base x11-sato hwcodecs', '', d)}"
IMAGE_FEATURES += "${@base_contains('DISTRO_FEATURES', 'wayland', \
                            base_contains('DISTRO_FEATURES', 'x11', '', ' package-management hwcodecs', d), \
                            '', d)}"
 
X11_EXTRA_IMAGE_FEATURES ?= "${@base_contains('DISTRO_FEATURES', 'x11', \
    ' tools-testapps', '', d)}"

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    ${X11_EXTRA_IMAGE_FEATURES} \
    nfs-server \
    tools-debug \
    tools-profile \
    ssh-server-dropbear \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Backend-specific packages
# Direct FB packages
DFB_INSTALL = "${@base_contains('DISTRO_FEATURES', 'directfb', \
    'packagegroup-core-full-cmdline packagegroup-core-directfb', '', d)}"
DFB_INSTALL_VIV = "${@base_contains('DISTRO_FEATURES', 'directfb', \
    'libvivante-dfb-mx6', '', d)}"

DFB_IMAGE_INSTALL       = "${DFB_INSTALL}"
DFB_IMAGE_INSTALL_mx6q  = "${DFB_INSTALL} ${DFB_INSTALL_VIV}"
DFB_IMAGE_INSTALL_mx6dl = "${DFB_INSTALL} ${DFB_INSTALL_VIV}"
DFB_IMAGE_INSTALL_mx6sx = "${DFB_INSTALL} ${DFB_INSTALL_VIV}"
DFB_IMAGE_INSTALL_mx6sl = "${DFB_INSTALL} ${DFB_INSTALL_VIV}"

# Wayland packages
WAYLAND_IMAGE_INSTALL = ""
WAYLAND_IMAGE_INSTALL_mx6 = "${@base_contains('DISTRO_FEATURES', 'wayland', \
                                      base_contains('DISTRO_FEATURES', 'x11',' \
                                          xterm weston weston-init weston-examples gtk+3-demo clutter-1.0-examples', 'weston weston-init weston-examples gtk+3-demo clutter-1.0-examples', d), '',d)}"
WAYLAND_IMAGE_INSTALL_remove_mx6sl = "clutter-1.0-examples"

# X11 packages
X11_IMAGE_INSTALL = ""
X11_IMAGE_INSTALL_mx6 = "${@base_contains('DISTRO_FEATURES', 'x11', \
    'packagegroup-fsl-pulseaudio', '', d)}"
X11_IMAGE_INSTALL_mx7 = "${@base_contains('DISTRO_FEATURES', 'x11', \
    'packagegroup-fsl-pulseaudio', '', d)}"

# Add in Graphics
X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
   'packagegroup-core-x11-sato-games \
    xorg-minimal-fonts \
    liberation-fonts', '', d)}"

# set mm image install specific to SOC
MM_IMAGE_INSTALL = ""
MM_IMAGE_INSTALL_mx6 = "packagegroup-fsl-multimedia-gstreamer1.0-core packagegroup-fsl-multimedia-gstreamer1.0-testapps"
MM_IMAGE_INSTALL_mx7 = "packagegroup-fsl-multimedia-gstreamer1.0-core packagegroup-fsl-multimedia-gstreamer1.0-testapps"

IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL} \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    ${DFB_IMAGE_INSTALL} \
    ${WAYLAND_IMAGE_INSTALL} \
    ${MM_IMAGE_INSTALL} \
    packagegroup-fsl-tools-bluetooth \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-fsl-tools-testapps \
    packagegroup-fsl-tools-benchmark \
    "
export IMAGE_BASENAME = "fsl-image-gui"


