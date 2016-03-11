# Copyright (C) 2013-2016 Freescale Semiconductor

require recipes-bsp/imx-vpu/imx-vpu.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=8cf95184c220e247b9917e7244124c5a"

PE = "1"

SRC_URI[md5sum] = "547074f0bb12fbf123f7998f2640c279"
SRC_URI[sha256sum] = "2e670ef2337a5dbe6c32fa07128ad9aad32afdbf472c23d44133245ec6a66d46"

# imx-vpu can only support imx6q platform, in order to build out the vpu case in unit test,
# using a workaround to transfer "IMX6Q" on imx6ul & imx7d platform.
PLATFORM_mx6ul = "IMX6Q"
PLATFORM_mx7 = "IMX6Q"

COMPATIBLE_MACHINE = "(mx6|mx6ul|mx7)"
