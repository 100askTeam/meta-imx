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

SOC_TOOLS_GPU = ""
SOC_TOOLS_GPU_mx5 = " \
    ${@base_contains('DISTRO_FEATURES', 'x11', 'amd-gpu-x11-bin-mx51', 'amd-gpu-bin-mx51', d)} \
    "

SOC_TOOLS_GPU_mx6 = " \
    gpu-viv-bin-mx6q \
    gpu-viv-g2d \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Backend-specific packages
# Direct FB packages
DFB_IMAGE_INSTALL = "${@base_contains('DISTRO_FEATURES', 'directfb', \
    'packagegroup-core-full-cmdline packagegroup-core-directfb libvivante-dfb-mx6', '', d)}"

# Wayland packages
WAYLAND_IMAGE_INSTALL = "${@base_contains('DISTRO_FEATURES', 'wayland', \
    base_contains('DISTRO_FEATURES', 'x11', '', \
    ' weston weston-init weston-examples gtk+3-demo clutter-1.0-examples', d),\
    '', d)}"

# X11 packages
X11_IMAGE_INSTALL = ""
X11_IMAGE_INSTALL_mx6 = "${@base_contains('DISTRO_FEATURES', 'x11', \
    'gst-plugins-gl-meta packagegroup-fsl-pulseaudio xserver-xorg-extension-viv-hdmi fsl-gpu-sdk ', '', d)}"

# Add in Graphics
X11_IMAGE_INSTALL_GRAPHICS = "${@base_contains('DISTRO_FEATURES', 'x11', \
    'glmark2 \
    glcompbench \
    packagegroup-fsl-gstreamer \
    packagegroup-core-x11-sato-games \
    xorg-minimal-fonts \
    liberation-fonts \
    gtkperf', '', d)}"

# set mm image install specific to SOC
MM_IMAGE_INSTALL = ""
MM_IMAGE_INSTALL_mx6 = "packagegroup-fsl-gstreamer1.0"

IMAGE_INSTALL += " \
    ${X11_IMAGE_INSTALL} \
    ${X11_IMAGE_INSTALL_GRAPHICS} \
    ${DFB_IMAGE_INSTALL} \
    ${WAYLAND_IMAGE_INSTALL} \
    ${SOC_TOOLS_GPU} \
    cpufrequtils \
    nano \
    ntpdate \
    ${MM_IMAGE_INSTALL} \
    packagegroup-fsl-tools-testapps \
    packagegroup-fsl-tools-benchmark \
    "

#pull this out of package group since v4l-utils now is x11 dependent
# and breaks non-x11 builds
RDEPENDS_${PN}-tools-testapps += "${@base_contains('DISTRO_FEATURES', 'x11', \
    ' v4l-utils ', '', d)}"

export IMAGE_BASENAME = "fsl-image-gui"


