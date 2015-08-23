# Add needed Freescale packages and definitions

PACKAGES += " \
    ${PN}-fslcodec-testapps \
"

RDEPENDS_${PN}-fslcodec-testapps += " \
    libfslcodec-test-bin \
    libfslcodec-test-source \
"

ALLOW_EMPTY_${PN}-fslcodec-testapps = "1"

RDEPENDS_${PN} += " \
    ${SOC_TOOLS_TESTAPPS} \
    obexftp \
    procps \
    ptpd \
    linuxptp \
    iw \
    can-utils \
    cpufrequtils \
    nano \
    ntpdate \
    minicom \
    coreutils \
"

SOC_TOOLS_TESTAPPS = ""
SOC_TOOLS_TESTAPPS_mx6 += " \
    imx-kobs \
    vlan \
    cryptodev-module \
    cryptodev-tests \
    ${PN}-fslcodec-testapps \
"
SOC_TOOLS_TESTAPPS_mx7 += " \
    imx-kobs \
    vlan \
    cryptodev-module \
    cryptodev-tests \
    ${PN}-fslcodec-testapps \
"

