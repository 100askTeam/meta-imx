# Copyright (C) 2013-2015 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by Freescale"
DESCRIPTION = "Linux Kernel provided and supported by Freescale with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "imx_3.14.38_6qp_ga"
LOCALVERSION = "-6QP_ga"
SRCREV = "75a01115a7f53d728e6abeb7b205676cc2a50bc0"
KERNEL_SRC ?= "git://git.freescale.com/imx/linux-2.6-imx.git"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

DEFAULT_PREFERENCE = "1"

do_configure_prepend() {
   # copy latest defconfig for imx_v7_defoonfig to use
   cp ${S}/arch/arm/configs/imx_v7_defconfig ${B}/.config
   cp ${S}/arch/arm/configs/imx_v7_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "(mx6|mx7)"
