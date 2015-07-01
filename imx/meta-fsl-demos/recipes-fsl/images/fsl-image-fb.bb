DESCRIPTION = "Freescale Image Frame Buffer Image"

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image
inherit distro_features_check

CONFLICT_DISTRO_FEATURES = "x11 wayland directfb"

DISTRO_FEATURES += "pulseaudio "
WEB = "web-webkit"

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    nfs-server \
    tools-debug \
    tools-profile \
    ssh-server-dropbear \
"

SOC_IMAGE_INSTALL = ""
SOC_IMAGE_INSTALL_mx6 = "gpu-viv-bin-mx6q gpu-viv-g2d"

IMAGE_INSTALL += " \
    ${SOC_IMAGE_INSTALL} \
    cpufrequtils \
    nano \
    packagegroup-fsl-tools-testapps \
    "

export IMAGE_BASENAME = "fsl-image-fb"


