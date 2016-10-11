# Copyright (C) 2013-2016 Freescale Semiconductor

include recipes-bsp/imx-lib/imx-lib.inc

LIC_FILES_CHKSUM = "file://COPYING-LGPL-2.1;md5=fbc093901857fcd118f065f900982c24"

PE = "1"

SRC_URI[md5sum] = "a0da6a01698776f2df05130108c226c9"
SRC_URI[sha256sum] = "8d12c4a0a44d29569f1ead3a36adc28402b9c4036b53e11dd3ebf2295e3ce1d0"

COMPATIBLE_MACHINE = "(mx6|mx6ul|mx7)"

PLATFORM_mx7 = "IMX7"
PLATFORM_mx6ul = "IMX6UL"
