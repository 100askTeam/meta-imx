require recipes-multimedia/gstreamer/gstreamer1.0.inc

FILESEXTRAPATHS_prepend := "${BSPDIR}/sources/poky/meta/recipes-multimedia/gstreamer/files:"

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d \
                    file://gst/gst.h;beginline=1;endline=21;md5=e059138481205ee2c6fc1c079c016d0d"

# Use i.MX fork of GST for customizations
GST1.0_SRC ?= "gitsm://source.codeaurora.org/external/imx/gstreamer.git;protocol=https"
SRCBRANCH = "imx-1.14.x"

SRC_URI = " ${GST1.0_SRC};branch=${SRCBRANCH}"
SRCREV = "f4e127a5e9a1eb977e023532d6636d939c8ccbc8"

EXTRA_AUTORECONF = ""

DEPENDS += " elfutils"

# Unrecognised options, need to remove them
EXTRA_OECONF_remove = " --disable-docbook --disable-trace"

PV = "1.14.0.imx"

S = "${WORKDIR}/git"

