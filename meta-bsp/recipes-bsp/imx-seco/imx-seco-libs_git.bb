# Copyright 2019-20 NXP

SUMMARY = "NXP i.MX SECO library"
DESCRIPTION = "NXP IMX SECO library"
SECTION = "base"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=228c72f2a91452b8a03c4cab30f30ef9"

DEPENDS = "zlib"

SRCBRANCH = "master"
SECO_LIB_SRC ?= "git://github.com/NXP/imx-seco-libs.git;protocol=https"
SRC_URI = "${SECO_LIB_SRC};branch=${SRCBRANCH}"
SRCREV = "166039c61cafb0c45d2d658f719c86b3f7e10e5a"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install () {
	oe_runmake DESTDIR=${D} install
}

COMPATIBLE_MACHINE = "(mx8)"
COMPATIBLE_MACHINE_mx8m = "(^$)"
