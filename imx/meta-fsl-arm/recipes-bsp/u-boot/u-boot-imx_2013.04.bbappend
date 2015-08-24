
SRC_URI = "git://${FSL_ARM_GIT_SERVER}/uboot-imx.git;protocol=git;branch=imx_v2013.04_3.10.17_1.0.0_beta"

SRCREV = "${AUTOREV}"
SRCREV = "c6d4a5f9317102313188b58cd6e4ad39ac5e1724"

# save UBOOT_CONFIG as separate names
do_deploy_append() {
    install ${S}/${UBOOT_BINARY} ${DEPLOYDIR}/${UBOOT_IMAGE}_${UBOOT_CONFIG}
}
