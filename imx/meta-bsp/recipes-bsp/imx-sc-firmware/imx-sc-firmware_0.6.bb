# Copyright (C) 2016 Freescale Semiconductor
# Copyright 2017-2018 NXP

DESCRIPTION = "i.MX System Controller Firmware"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "BSP"

inherit fsl-eula-unpack pkgconfig deploy

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"

SRC_URI[md5sum] = "6386b0f53cf3d1847515da923f1368aa"
SRC_URI[sha256sum] = "16702f29a6d2a9893eb49be9cc46ac64abfd56e9ab4854f8a616b609d2b36dbf"

S = "${WORKDIR}/${PN}-${PV}"

BOARD_TYPE ?= "mek"
SC_FIRMWARE_NAME ?= "mx8qm-mek-scfw-tcm.bin"
SC_FIRMWARE_NAME_mx8qm = "mx8qm-${BOARD_TYPE}-scfw-tcm.bin"
SC_FIRMWARE_NAME_mx8qxp = "mx8qx-${BOARD_TYPE}-scfw-tcm.bin"
symlink_name = "scfw_tcm.bin"

SYSROOT_DIRS += "/boot"

do_install () {
    install -d ${D}/boot
    install -m 0644 ${S}/${SC_FIRMWARE_NAME} ${D}/boot/
}

BOOT_TOOLS = "imx-boot-tools"

do_deploy () {
    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0644 ${S}/${SC_FIRMWARE_NAME} ${DEPLOYDIR}/${BOOT_TOOLS}/
    cd ${DEPLOYDIR}/${BOOT_TOOLS}/
    rm -f ${symlink_name}
    ln -sf ${SC_FIRMWARE_NAME} ${symlink_name}
    cd -
}

addtask deploy after do_install

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "/boot"

COMPATIBLE_MACHINE = "(mx8qm|mx8qxp)"
