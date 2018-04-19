FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Specify the opencv_extra source. The version should match the overall opencv version.
# Recording the opencv_extra version here allows us to raise a fatal error if the
# package version is updated but this section is not.
SRC_URI += "git://github.com/opencv/opencv_extra.git;destsuffix=opencv_extra;name=opencv_extra"
SRCREV_opencv_extra = "c533012eb214ec3db851586f74f9dc43ea20c065"
OPENCV_EXTRA_VERSION = "3.4.1"

SRC_URI_remove = "file://javagen.patch"
SRC_URI += "file://fix_openvx_samples.patch"
SRC_URI += "file://fix_python_bindings.patch"

PACKAGECONFIG_remove_imx   = "eigen"
PACKAGECONFIG_remove_mx8   = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland x11', 'gtk', '', d)}"
PACKAGECONFIG_append_mx8   = " opencl dnn text"
PACKAGECONFIG_append_mx8dv = " openvx"
PACKAGECONFIG_append_mx8qm = " openvx"

PACKAGECONFIG[openvx] = " \
    -DWITH_OPENVX=ON -DOPENVX_ROOT=${STAGING_LIBDIR} -DOPENVX_LIB_CANDIDATES='OpenVX;OpenVXU', \
    -DWITH_OPENVX=OFF, \
    virtual/libopenvx, \
    \
"
PACKAGECONFIG[qt5] = " \
    -DWITH_QT=ON -DWITH_GTK=OFF \
        -DOE_QMAKE_PATH_EXTERNAL_HOST_BINS=${STAGING_BINDIR_NATIVE}/qt5 \
        -DCMAKE_PREFIX_PATH=${STAGING_BINDIR_NATIVE}/cmake, \
    -DWITH_QT=OFF, \
    qtbase qtbase-native, \
    \
"
PACKAGECONFIG[test] = " \
    -DBUILD_TESTS=ON -DINSTALL_TESTS=ON -DOPENCV_TEST_DATA_PATH=${S}/../opencv_extra/testdata, \
    -DBUILD_TESTS=OFF -DINSTALL_TESTS=OFF, \
"

do_check_opencv_extra_version() {
    OPENCV_VERSION=`echo ${PV} |  cut -d '+' -f 1`
    if [ "${OPENCV_EXTRA_VERSION}" != "${OPENCV_VERSION}" ]; then
        bbfatal "The opencv_extra version ${OPENCV_EXTRA_VERSION} does not match the recipe version ${OPENCV_VERSION}."
    fi
}
addtask check_opencv_extra_version before do_fetch

do_compile_prepend() {
    # A build break occurs if dnn and python3 are configured. Work around
    # the problem by building opencv_dnn first. See
    # https://github.com/opencv/opencv/issues/10474.
    if ${@bb.utils.contains("PACKAGECONFIG", "dnn python3", "true", "false", d)}; then
        bbnote VERBOSE=1 cmake --build '${B}' --target opencv_dnn -- ${PARALLEL_MAKE}
        VERBOSE=1 cmake --build '${B}' --target opencv_dnn -- ${PARALLEL_MAKE}
    fi
}

do_install_append() {
    if ${@bb.utils.contains("PACKAGECONFIG", "samples", "true", "false", d)}; then
        install -d ${D}${datadir}/OpenCV/samples/data
        cp -r ${S}/samples/data/* ${D}${datadir}/OpenCV/samples/data

        install -d ${D}${datadir}/OpenCV/samples/bin/
        cp -f bin/example_* ${D}${datadir}/OpenCV/samples/bin/
    fi
}

RDEPENDS_opencv-apps += \
    "${@bb.utils.contains('PACKAGECONFIG', 'test', 'bash', '', d)}"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
