SUMMARY = "Tensors and Dynamic neural networks in Python with strong GPU acceleration"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9c57cfb31165de565a47b65b896391c2"

DEPENDS = "python3 python3-pip-native python3-wheel-native"
RDEPENDS_${PN} += "python3-core python3-numpy python3-future"

PV = "1.5.0"

PYTORCH_SRC ?= "git://github.com/nxpmicro/pytorch-release.git;protocol=https"
SRCBRANCH = "master"
SRCREV = "a5f8c69a3371d90aeef9b8e6fab3ec15f303e4e2"

SRC_URI = " \
    ${PYTORCH_SRC};branch=${SRCBRANCH} \
"
inherit python3native

S = "${WORKDIR}/git"

do_install(){
    install -d ${D}/${PYTHON_SITEPACKAGES_DIR}
    install -d ${D}${bindir}

    ${STAGING_BINDIR_NATIVE}/pip3 install --disable-pip-version-check -v \
        -t ${D}/${PYTHON_SITEPACKAGES_DIR} --no-cache-dir --no-deps \
        ${S}/whl/torch-*.whl

    for app in `ls ${D}${PYTHON_SITEPACKAGES_DIR}/bin/*`; do
        sed -i 's,^#!.*,#!/usr/bin/python3,g' $app
        mv $app ${D}${bindir}
    done

    rm -fr ${D}${PYTHON_SITEPACKAGES_DIR}/bin
}

FILES_${PN} += "${libdir}/python*"
